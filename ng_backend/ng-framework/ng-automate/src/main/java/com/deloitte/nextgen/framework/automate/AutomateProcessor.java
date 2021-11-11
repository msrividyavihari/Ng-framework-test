package com.deloitte.nextgen.framework.automate;

import com.deloitte.nextgen.framework.automate.annotations.*;
import com.deloitte.nextgen.framework.automate.exceptions.InvalidUsageException;
import com.deloitte.nextgen.framework.automate.exceptions.TypeNotFoundException;
import com.deloitte.nextgen.framework.automate.processor.EndpointProcessor;
import com.deloitte.nextgen.framework.automate.spi.impl.ProcessorContext;
import com.deloitte.nextgen.framework.automate.utils.AnnotationUtils;
import com.deloitte.nextgen.framework.commons.utils.WordUtils;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementKindVisitor6;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleAnnotationValueVisitor8;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author nishmehta
 * @since 25/06/2021 11:59 AM
 */

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions({AutomateProcessor.VERBOSE, AutomateProcessor.GENERATE_MODE})
@AutoService(Processor.class)
public class AutomateProcessor extends AbstractProcessor {

    protected static final String VERBOSE = "automate.verbose";
    protected static final String GENERATE_MODE = "automate.generateMode";
    private int counter = 1;
    private Options options;
    private Elements elementUtils;
    private Filer filer;
    private ProcessorContext processorContext;

    private final List<TypeElement> deferredEndpoints = new ArrayList<>();
    private PackageElement basePackage = null;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();

        options = createOptions();
        processorContext = new ProcessorContext(
                processingEnv.getElementUtils(),
                processingEnv.getTypeUtils(),
                processingEnv.getMessager(),
                processingEnv.getFiler(),
                options.isVerbose()
        );
    }

    private Options createOptions() {
        String generateMode = processingEnv.getOptions().get(GENERATE_MODE);
        return new Options(
                Boolean.valueOf(processingEnv.getOptions().get(VERBOSE)),
                StringUtils.isEmpty(generateMode) ? "default" : generateMode
        );
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(Endpoints.class.getCanonicalName());
        set.add(Endpoint.class.getCanonicalName());
        set.add(OperationURIs.class.getCanonicalName());
        set.add(OperationURI.class.getCanonicalName());
        set.add(ImportOperations.class.getCanonicalName());
        return set;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(SpringBootApplication.class)) {
            TypeElement classElement = (TypeElement) annotatedElement;
            basePackage = elementUtils.getPackageOf(classElement);
        }

        if (!roundEnvironment.processingOver()) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "----------------------------------");
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Automate Processing Round : " + counter++);


            if (options.getGenerateMode().equals("uri")) {
                autoGenerateApi(roundEnvironment);
            } else {
                processImportOperations(annotations, roundEnvironment);
                Context roundContext = new Context(processorContext);

                Set<TypeElement> deferredLocalEndpoints = getAndResetEndpoints();
                processEndpoints(deferredLocalEndpoints, roundContext);
                processEndpoint(deferredLocalEndpoints, roundContext);

                Set<TypeElement> endpoints = getEndpoints(annotations, roundEnvironment);
                processEndpoints(endpoints, roundContext);
                processEndpoint(endpoints, roundContext);
            }


        } else if (!deferredEndpoints.isEmpty()) {
            Context roundContext = new Context(processorContext);
            Set<TypeElement> deferredLocalEndpoints = getAndResetEndpoints();
            processEndpoints(deferredLocalEndpoints, roundContext);
            processEndpoint(deferredLocalEndpoints, roundContext);
        }

        return true;
    }

    private void processImportOperations(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

        if (StringUtils.isNotEmpty(processorContext.packageName()) || processorContext.importOperation() != null) {
            return;
        }

        Set<TypeElement> operations = new HashSet<>();
        Set<? extends Element> annotatedElements = annotations.stream().filter(annotation -> annotation.getQualifiedName().toString().equals(ImportOperations.class.getName()))
                .map(roundEnvironment::getElementsAnnotatedWith)
                .findFirst().orElse(null);

        if (CollectionUtils.isEmpty(annotatedElements)) {
            String str = "No ImportOperations annotation found. Please make sure you have annotated an element with ImportOperations.";
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, str + " Aborting file generation.");
            throw new InvalidUsageException(str);

        } else if (annotatedElements.size() > 1) {
            String str = "Multiple ImportOperations annotation found. Please ensure it is used only once.";
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, str + " Aborting file generation.");
            throw new InvalidUsageException(str);
        }

        for (Element element : annotatedElements) {
            operations.add(asTypeElement(element));

        }

        operations.stream().findFirst().ifPresent(element -> {

            AnnotationValue value = element.getAnnotationMirrors().stream()
                    .filter(annotationMirror -> annotationMirror.getAnnotationType().toString().equals(ImportOperations.class.getName()))
                    .map(AnnotationUtils::getAnnotationValueOfAttribute)
                    .findFirst()
                    .orElseThrow(() -> new NullPointerException("AnnotationValue is null"));

            TypeMirror importOps = value.accept(new SimpleAnnotationValueVisitor8<TypeMirror, Void>() {
                @Override
                public TypeMirror visitType(TypeMirror t, Void aVoid) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, t.toString());
                    if (t.getKind() == TypeKind.ERROR) {
                        throw new TypeNotFoundException("Type is not yet created. Skipping it to next round");
                    }
                    return t;
                }
            }, null);


            processorContext.setPackageName(element.getEnclosingElement().toString());
            processorContext.setImportOperation(importOps);
        });
    }

    private Set<TypeElement> getAndResetEndpoints() {
        Set<TypeElement> deferred = new HashSet<>(deferredEndpoints.size());

        for (TypeElement element : deferredEndpoints) {
            deferred.add(processingEnv.getElementUtils().getTypeElement(element.getQualifiedName()));
        }

        deferredEndpoints.clear();
        return deferred;
    }

    private void processEndpoints(final Set<TypeElement> endpoints, Context roundContext) {

        for (TypeElement element : endpoints) {

            List<AnnotationMirror> mirrors = elementUtils.getAllAnnotationMirrors(element).stream()
                    .filter(annotationMirror -> annotationMirror.getAnnotationType().toString().equals(Endpoints.class.getName()))
                    .map(annotationMirror -> {
                        List<AnnotationValue> annotationValues = new ArrayList<>();
                        AnnotationValue value = AnnotationUtils.getAnnotationValueOfAttribute(annotationMirror);
                        Object obj = value.getValue();
                        if (obj instanceof List) {
                            annotationValues = (List<AnnotationValue>) obj;
                        }
                        return annotationValues;
                    })
                    .map(annotationValues ->

                            annotationValues.stream().map(annotationValue -> annotationValue.accept(new SimpleAnnotationValueVisitor8<AnnotationMirror, Void>() {

                                        @Override
                                        public AnnotationMirror visitAnnotation(AnnotationMirror a, Void aVoid) {
                                            return a;
                                        }
                                    }, null)
                            ).collect(Collectors.toList())
                    ).findFirst().orElse(new ArrayList<>());

            if (!mirrors.isEmpty()) {
                mirrors.forEach(annotationMirror -> process(roundContext, element, annotationMirror));
            }
        }
    }

    private void process(Context roundContext, TypeElement element, AnnotationMirror mirror) {
        if (roundContext.getProcessorContext().isVerbose()) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "> processing element " + element.toString());
        }
        try {
            EndpointProcessor ep = new EndpointProcessor(element, mirror, roundContext);
            ep.process();
        } catch (TypeNotFoundException tnfe) {
            processingEnv.getMessager().printMessage(
                    Diagnostic.Kind.WARNING, "Referred type not available (yet), deferring to next round: " + tnfe.getMessage());
            deferredEndpoints.add(element);
        }
    }

    private void processEndpoint(final Set<TypeElement> endpoints, Context roundContext) {

        for (TypeElement element : endpoints) {
            elementUtils.getAllAnnotationMirrors(element).stream()
                    .filter(annotationMirror -> annotationMirror.getAnnotationType().toString().equals(Endpoint.class.getName()))
                    .findFirst().ifPresent(mirror -> process(roundContext, element, mirror));


        }
    }

    private Set<TypeElement> getEndpoints(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        Set<TypeElement> mapperTypes = new HashSet<>();

        for (TypeElement annotation : annotations) {

            if (annotation.getKind() != ElementKind.ANNOTATION_TYPE
                    || annotation.getQualifiedName().toString().equals(ImportOperations.class.getName())) {
                continue;
            }

            try {
                Set<? extends Element> annotatedMappers = roundEnvironment.getElementsAnnotatedWith(annotation);
                for (Element mapperElement : annotatedMappers) {
                    TypeElement mapperTypeElement = asTypeElement(mapperElement);
                    mapperTypes.add(mapperTypeElement);

                }
            } catch (Exception exception) { // whenever that may happen, but just to stay on the safe side
                handleUncaughtException(annotation, exception);
            }
        }
        return mapperTypes;
    }

    private TypeElement asTypeElement(Element element) {
        return element.accept(
                new ElementKindVisitor6<TypeElement, Void>() {
                    @Override
                    public TypeElement visitTypeAsInterface(TypeElement e, Void p) {
                        return e;
                    }

                    @Override
                    public TypeElement visitTypeAsClass(TypeElement e, Void p) {
                        return e;
                    }

                }, null
        );
    }

    private void handleUncaughtException(Element element, Exception exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));

        String reportableStacktrace = sw.toString().replace(System.lineSeparator(), "  ");

        processingEnv.getMessager().printMessage(
                Diagnostic.Kind.ERROR, "Internal error in the mapping processor: " + reportableStacktrace, element);
    }

    private void autoGenerateApi(RoundEnvironment roundEnv) {
        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(OperationURIs.class);

        for (Element annotatedElement : set) {

            TypeElement classElement = (TypeElement) annotatedElement;
            basePackage = elementUtils.getPackageOf(classElement);
            OperationURI[] generateAnnotations = annotatedElement.getAnnotationsByType(OperationURI.class);
            TypeSpec.Builder typeSpec = TypeSpec.classBuilder("Default" + annotatedElement.getSimpleName().toString() + "Operations")
                    .addModifiers(Modifier.PUBLIC);
            for (OperationURI operationURIAnnotation : generateAnnotations) {

                String className = operationURIAnnotation.value();
                String uri = WordUtils.pluralize(className);

                TypeSpec ts = TypeSpec.classBuilder(StringUtils.capitalize(className))
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addField(FieldSpec.builder(String.class, "V1_CREATE", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                                .initializer("$S", "v1/" + uri)
                                .build())
                        .addField(FieldSpec.builder(String.class, "V1_UPDATE", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                                .initializer("$S", "v1/" + uri)
                                .build())
                        .addField(FieldSpec.builder(String.class, "V1_DELETE", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                                .initializer("$S", "v1/" + uri + "/{id}")
                                .build())
                        .addField(FieldSpec.builder(String.class, "V1_GET", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                                .initializer("$S", "v1/" + uri + "/{id}")
                                .build())
                        .addField(FieldSpec.builder(String.class, "V1_GET_ALL", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                                .initializer("$S", "v1/" + uri)
                                .build())
                        .build();

                typeSpec.addType(ts);
            }

            JavaFile jf = JavaFile.builder(basePackage.toString(), typeSpec.build()).build();

            try {
                jf.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

