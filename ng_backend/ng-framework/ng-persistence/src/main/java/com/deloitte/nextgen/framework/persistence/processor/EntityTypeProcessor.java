package com.deloitte.nextgen.framework.persistence.processor;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.entity.type.two.TypeTwoBaseEntity;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @author mukepatel on 8/14/2021 15:48 PM
 * @project ng-framework
 */

@SupportedAnnotationTypes("com.deloitte.nextgen.framework.persistence.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class EntityTypeProcessor extends AbstractProcessor {

    private boolean isProcessing;
    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (isProcessing) {
            return true;
        }
        //Iterate through each annotation received. Should ideally receive only EntityType.class
        for (TypeElement annotation : annotations) {

            if (!annotation.getQualifiedName().toString().equals(EntityType.class.getCanonicalName())) {
                continue;
            }

            roundEnv.getElementsAnnotatedWith(annotation)           // Get all elements annotated with EntityType.class
                    .stream()                                       // Open Stream
                    .map(TypeElement.class::cast)                   // Type cast to TypeElement
                    .filter(this::isValidClass)                     // Filter only valid elements
                    .map(this::createGenerator)                     // Map to  EntityGenerator
                    .map(EntityGenerator::generate)                 // Map to List<JavaFiles> to be generated
                    .forEach(fileList ->
                            fileList.forEach(file -> {
                                try {
                                    file.writeTo(filer);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            })
                    );
        }

        return true;
    }

    private EntityGenerator createGenerator(TypeElement typeElement) {

        TypeEnum typeEnum = TypeEnum.NONE;
        if (typeElement.getAnnotation(EntityType.class) != null) {
            typeEnum = typeElement.getAnnotation(EntityType.class).type();
        }
        EntityTypeItem item = new EntityTypeItem(typeElement, elementUtils);

        switch (typeEnum) {
            case ZERO:
                return new EntityZeroGenerator(item);
            case ONE:
                return new EntityOneGenerator(item);
            case TWO:
                return new EntityTwoGenerator(item);
            default:
                throw new IllegalArgumentException("No such type exist " + typeEnum);
        }

    }

    private boolean isValidClass(TypeElement classElement) {

        // Cast to TypeElement, has more type specific methods
        EntityType entityType = classElement.getAnnotation(EntityType.class);

        if (!classElement.getModifiers().contains(Modifier.PUBLIC)) {
            error(classElement, "The class %s is not public.",
                    classElement.getQualifiedName().toString());
            return false;
        }

        // Check if it's an abstract class
        if (classElement.getModifiers().contains(Modifier.ABSTRACT)) {
            error(classElement, "The class %s is abstract. You can't annotate abstract classes with @%s",
                    classElement.getQualifiedName().toString(), EntityType.class.getSimpleName());
            return false;
        }

        if (!isExtendingEntityTypeSuperClass(classElement, entityType.type())) {
            return false;
        }

        // Check if an empty public constructor is given
        for (Element enclosed : classElement.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.CONSTRUCTOR) {
                ExecutableElement constructorElement = (ExecutableElement) enclosed;
                if (constructorElement.getParameters().isEmpty() && constructorElement.getModifiers()
                        .contains(Modifier.PUBLIC)) {
                    // Found an empty constructor
                    return true;
                }
            }
        }

        // No empty constructor found
        error(classElement, "The class %s must provide an public empty default constructor",
                classElement.getQualifiedName().toString());
        return false;
    }

    private boolean isExtendingEntityTypeSuperClass(TypeElement classElement, TypeEnum type) {

        final String ENTITY_CLASS_EXTEND_ERROR_MSG = "The class %s must extend %s";

        TypeElement superClassTypeElement = (TypeElement) ((DeclaredType) classElement.getSuperclass()).asElement();

        if (type == TypeEnum.TWO) {
            if (superClassTypeElement.getQualifiedName().toString().equals(TypeTwoBaseEntity.class.getName())) {
                return true;
            }
            error(classElement, ENTITY_CLASS_EXTEND_ERROR_MSG, classElement.getSimpleName(), TypeTwoBaseEntity.class.getSimpleName());
        } else if (type == TypeEnum.ONE) {

            if (superClassTypeElement.getQualifiedName().toString().equals(TypeOneBaseEntity.class.getName())) {
                return true;
            }
            error(classElement, ENTITY_CLASS_EXTEND_ERROR_MSG, classElement.getSimpleName(), TypeOneBaseEntity.class.getSimpleName());

        } else if (type == TypeEnum.ZERO) {

            if (superClassTypeElement.getQualifiedName().toString().equals(TypeZeroBaseEntity.class.getName())) {
                return true;
            }
            error(classElement, ENTITY_CLASS_EXTEND_ERROR_MSG, classElement.getSimpleName(), TypeZeroBaseEntity.class.getSimpleName());
        }

        return false;
    }

    private void error(Element e, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }
}

