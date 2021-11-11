package com.deloitte.nextgen.framework.automate.processor;

import com.deloitte.nextgen.framework.automate.Context;
import com.deloitte.nextgen.framework.automate.GeneratorFactory;
import com.deloitte.nextgen.framework.automate.exceptions.TypeNotFoundException;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.squareup.javapoet.TypeName;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor8;
import javax.lang.model.util.SimpleTypeVisitor8;
import javax.persistence.Id;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author nishmehta
 * @since 06/08/2021 1:47 PM
 */
public class EndpointProcessor {

    private final TypeElement element;
    private final Context roundContext;
    private final Messager messager;
    private final boolean isVerbose;
    private final SimpleAnnotationValueVisitor8<String, Void> stringValueVisitor8 = new SimpleAnnotationValueVisitor8<String, Void>() {

        @Override
        public String visitString(String s, Void aVoid) {
            return s;
        }
    };
    private final SimpleAnnotationValueVisitor8<TypeMirror, Void> typeMirrorValueVisitor8 = new SimpleAnnotationValueVisitor8<TypeMirror, Void>() {

        @Override
        public TypeMirror visitType(TypeMirror t, Void aVoid) {
            return t;
        }
    };
    private final SimpleTypeVisitor8<DeclaredType, Void> declaredTypeVisitor = new SimpleTypeVisitor8<DeclaredType, Void>() {

        @Override
        public DeclaredType visitDeclared(DeclaredType t, Void aVoid) {
            return t;
        }
    };
    private final AnnotationMirror mirror;

    public EndpointProcessor(TypeElement element, AnnotationMirror mirror, Context roundContext) {
        this.element = element;
        this.mirror = mirror;
        this.roundContext = roundContext;
        this.messager = roundContext.getProcessorContext().getMessager();
        this.isVerbose = roundContext.getProcessorContext().isVerbose();
    }

    public void process() {

        EndpointDefaultValue defaultValue = processAnnotationMirror(mirror);
        TypeElement entityElement = (TypeElement) roundContext.getProcessorContext().getTypeUtils().asElement(defaultValue.entity());
        if (isVerbose) {
            messager.printMessage(Diagnostic.Kind.NOTE, "> name " + defaultValue.name());
        }
        if (!roundContext.getProcessorContext().isProcessed(entityElement.getQualifiedName().toString())) {
            if (isVerbose) {
                messager.printMessage(Diagnostic.Kind.NOTE, ">> type: " + defaultValue.type());
                messager.printMessage(Diagnostic.Kind.NOTE, ">> name: " + defaultValue.name());
                messager.printMessage(Diagnostic.Kind.NOTE, ">> entity: " + defaultValue.entity());
                messager.printMessage(Diagnostic.Kind.NOTE, ">> request: " + defaultValue.request());
                messager.printMessage(Diagnostic.Kind.NOTE, ">> response: " + defaultValue.response());
                messager.printMessage(Diagnostic.Kind.NOTE, ">> mapper: " + defaultValue.mapper());
                messager.printMessage(Diagnostic.Kind.NOTE, ">> idClass: " + defaultValue.primaryKey());
            }

            GeneratorFactory generatorFactory = new GeneratorFactory(roundContext.getProcessorContext(), defaultValue);
            generatorFactory.generate();

            messager.printMessage(Diagnostic.Kind.NOTE, "added to processed " + entityElement.getQualifiedName().toString());
            roundContext.getProcessorContext().addProcessed(entityElement.getQualifiedName().toString());
        } else {
            if (isVerbose) {
                messager.printMessage(Diagnostic.Kind.NOTE, ">> Files already for generated in previous round skipping generation." + entityElement.getQualifiedName().toString());
            }
        }
    }

    private EndpointDefaultValue processAnnotationMirror(AnnotationMirror mirror) {
        final EndpointDefaultValue.EndpointDefaultValueBuilder builder = new EndpointDefaultValue.EndpointDefaultValueBuilder();
        Iterator<? extends Map.Entry<? extends ExecutableElement, ? extends AnnotationValue>> it = mirror.getElementValues().entrySet().iterator();
        it.forEachRemaining(entry -> {
            AnnotationValue value = entry.getValue();
            String ee = entry.getKey().getSimpleName().toString();
            switch (ee) {
                case "name":
                    builder.name(value.accept(stringValueVisitor8, null));
                    break;
                case "entity":
                    TypeMirror t = value.accept(typeMirrorValueVisitor8, null);
                    final List<IdField> idFields = getIdFields(t);

                    builder.className(roundContext.getProcessorContext().getTypeUtils().asElement(t).getSimpleName().toString());
                    builder.entity(t);
                    TypeEnum typeEnum = roundContext.getProcessorContext().getTypeUtils().asElement(t).getAnnotation(EntityType.class).type();
                    builder.type(typeEnum);
                    builder.fields(idFields);
                    builder.idClass(getIdClass(t));
                    break;
                case "request":
                    builder.request(value.accept(typeMirrorValueVisitor8, null));
                    break;
                case "response":
                    builder.response(value.accept(typeMirrorValueVisitor8, null));
                    break;
                case "mapper":
                    builder.mapper(value.accept(typeMirrorValueVisitor8, null));
                    break;
                default:
                    break;
            }
        });

        return builder.build();
    }

    private TypeMirror getIdClass(TypeMirror t) {

        List<? extends TypeMirror> supertypes = roundContext.getProcessorContext().getTypeUtils().directSupertypes(t);
        return supertypes.stream().map(typeMirror -> {
            List<? extends TypeMirror> typeArguments = typeMirror.accept(declaredTypeVisitor, null).getTypeArguments();
            TypeMirror idClass = null;
            if (CollectionUtils.isNotEmpty(typeArguments)) {
                idClass = typeArguments.get(0);
                if (!canBeProcessed(idClass)) {
                    throw new TypeNotFoundException("Type is not yet created " + idClass);
                }
            }
            return idClass;
        }).findFirst().orElse(null);
    }

    private List<IdField> getIdFields(TypeMirror typeMirror) {
        TypeElement te = (TypeElement) roundContext.getProcessorContext().getTypeUtils().asElement(typeMirror);
        List<IdField> idFields = new ArrayList<>();
        te.getEnclosedElements().forEach(ele -> {
            if (ele.getAnnotation(Id.class) != null) {
                String fieldName = StringUtils.capitalize(ele.getSimpleName().toString());
                idFields.add(new IdField(fieldName, TypeName.get(ele.asType())));
            }
        });

        return idFields;
    }

    private boolean canBeProcessed(TypeMirror kindType) {
        switch (kindType.getKind()) {
            case DECLARED:
                return true;
            case ERROR:
                return false;
            default:
                break;
        }
        return false;
    }
}
