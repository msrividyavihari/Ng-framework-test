package com.deloitte.nextgen.framework.automate.source;

import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.deloitte.nextgen.framework.automate.source.operations.Operations;
import com.deloitte.nextgen.framework.automate.source.operations.impl.DefaultResourceServiceOperations;
import com.deloitte.nextgen.framework.automate.utils.FileType;
import com.squareup.javapoet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nishmehta
 * @since 05/07/2021 7:15 PM
 */
public class DefaultResourceSource extends AbstractSourceFile {

    public static final String SERVICE = "service";
    private final TypeSpec serviceInterface;
    private final TypeMirror resourceEndpoint;

    public DefaultResourceSource(String basePackage, EndpointDefaultValue endpointDefaultValue, TypeSpec serviceInterface, TypeMirror resourceEndpoint) {
        super(basePackage, endpointDefaultValue);
        this.serviceInterface = serviceInterface;
        this.resourceEndpoint = resourceEndpoint;
    }

    @Override
    public FileType type() {
        return FileType.RESOURCE_IMPL;
    }

    @Override
    public List<AnnotationSpec> annotations() {
        List<AnnotationSpec> annotationSpecs = new ArrayList<>();

        AnnotationSpec slf4j = AnnotationSpec.builder(Slf4j.class).build();
        annotationSpecs.add(slf4j);

        AnnotationSpec restController = AnnotationSpec.builder(RestController.class).build();
        annotationSpecs.add(restController);

        AnnotationSpec requestMapping = AnnotationSpec.builder(RequestMapping.class)
                .addMember("value", String.format("\"%s\"", "/"))
                .build();
        annotationSpecs.add(requestMapping);

        return annotationSpecs;
    }

    @Override
    public List<FieldSpec> members() {
        List<FieldSpec> fieldSpecs = new ArrayList<>();

        TypeName repositoryField = ClassName.get(getPackageName(FileType.SERVICE_INTERFACE), serviceInterface.name);
        FieldSpec fieldSpec = FieldSpec.builder(repositoryField, NameConstants.SERVICE_FIELD_NAME, Modifier.PROTECTED, Modifier.FINAL)
                .build();
        fieldSpecs.add(fieldSpec);

        return fieldSpecs;

    }

    @Override
    public List<MethodSpec> methods() {
        List<MethodSpec> methodSpecs = super.methods();
        methodSpecs.addAll(constructors());
        return methodSpecs;
    }

    public List<MethodSpec> constructors() {

        List<MethodSpec> constructorSpecs = new ArrayList<>();
        MethodSpec.Builder constructors = MethodSpec.constructorBuilder();
        TypeName repositoryField = ClassName.get(getPackageName(FileType.SERVICE_INTERFACE), serviceInterface.name);
        constructors.addModifiers(Modifier.PUBLIC)
                .addParameter(repositoryField, SERVICE)
                .addStatement("this.$N = $N", SERVICE, SERVICE);

        constructorSpecs.add(constructors.build());

        return constructorSpecs;
    }

    @Override
    public Operations operations() {
        return new DefaultResourceServiceOperations(endpointDefaultValue, resourceEndpoint);
    }
}
