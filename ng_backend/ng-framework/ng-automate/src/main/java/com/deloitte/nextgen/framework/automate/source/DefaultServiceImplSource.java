package com.deloitte.nextgen.framework.automate.source;

import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.deloitte.nextgen.framework.automate.source.operations.Operations;
import com.deloitte.nextgen.framework.automate.source.operations.impl.DefaultServiceImplServiceOperations;
import com.deloitte.nextgen.framework.automate.utils.FileType;
import com.squareup.javapoet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nishmehta
 * @since 05/07/2021 7:15 PM
 */
public class DefaultServiceImplSource extends AbstractSourceFile {

    public static final String REPOSITORY = "repository";
    public static final String MAPPER = "mapper";
    private final TypeSpec serviceInterface;
    private final TypeSpec repositoryInterface;


    public DefaultServiceImplSource(String basePackage, EndpointDefaultValue endpointDefaultValue, TypeSpec serviceInterface, TypeSpec repositoryInterface) {
        super(basePackage, endpointDefaultValue);
        this.serviceInterface = serviceInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public FileType type() {
        return FileType.SERVICE_IMPL;
    }

    @Override
    public String packageName() {
        return getPackageName(type());
    }

    @Override
    public String className() {
        return getClassName(type());
    }

    @Override
    public List<AnnotationSpec> annotations() {
        List<AnnotationSpec> annotationSpecs = new ArrayList<>();

        AnnotationSpec slf4j = AnnotationSpec.builder(Slf4j.class).build();
        annotationSpecs.add(slf4j);

        AnnotationSpec restController = AnnotationSpec.builder(Service.class).build();
        annotationSpecs.add(restController);

        AnnotationSpec validated = AnnotationSpec.builder(Validated.class).build();
        annotationSpecs.add(validated);

        return annotationSpecs;
    }

    @Override
    public List<FieldSpec> members() {
        List<FieldSpec> fieldSpecs = new ArrayList<>();

        TypeName repositoryField = ClassName.get(getPackageName(FileType.REPOSITORY_INTERFACE), repositoryInterface.name);
        FieldSpec fieldSpec = FieldSpec.builder(repositoryField, NameConstants.REPOSITORY_FIELD_NAME, Modifier.PROTECTED, Modifier.FINAL)
                .build();
        fieldSpecs.add(fieldSpec);

        TypeName mapperField = TypeName.get(endpointDefaultValue.mapper());
        FieldSpec mapperFieldSpec = FieldSpec.builder(mapperField, NameConstants.MAPPER_FIELD_NAME, Modifier.PROTECTED, Modifier.FINAL)
                .build();
        fieldSpecs.add(mapperFieldSpec);

        return fieldSpecs;
    }

    public List<MethodSpec> constructors() {
        List<MethodSpec> constructorSpecs = new ArrayList<>();
        MethodSpec.Builder constructors = MethodSpec.constructorBuilder();
        TypeName repositoryField = ClassName.get(getPackageName(FileType.REPOSITORY_INTERFACE), repositoryInterface.name);
        TypeName mapperField = TypeName.get(endpointDefaultValue.mapper());
        constructors.addModifiers(Modifier.PUBLIC)
                .addParameter(repositoryField, REPOSITORY)
                .addParameter(mapperField, MAPPER)
                .addStatement("this.$N = $N", REPOSITORY, REPOSITORY)
                .addStatement("this.$N = $N", MAPPER, MAPPER);

        constructorSpecs.add(constructors.build());

        return constructorSpecs;
    }

    @Override
    public Operations operations() {
        return new DefaultServiceImplServiceOperations(endpointDefaultValue);
    }

    @Override
    public List<MethodSpec> methods() {
        List<MethodSpec> methodSpecs = super.methods();
        methodSpecs.addAll(constructors());
        return methodSpecs;
    }

    @Override
    public List<TypeName> superInterfaces() {

        List<TypeName> typeNames = new ArrayList<>();

        ClassName si = ClassName.get(getPackageName(FileType.SERVICE_INTERFACE), serviceInterface.name);
        typeNames.add(si);

        return typeNames;
    }
}
