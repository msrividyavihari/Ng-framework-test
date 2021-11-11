package com.deloitte.nextgen.framework.automate.source.operations.impl;

import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.deloitte.nextgen.framework.commons.exceptions.IdentifierException;
import com.squareup.javapoet.*;
import org.springframework.data.domain.Page;

import javax.lang.model.element.Modifier;

import static com.deloitte.nextgen.framework.automate.source.NameConstants.*;

/**
 * @author nishmehta
 * @since 07/07/2021 7:40 PM
 */
public class DefaultServiceImplServiceOperations extends AbstractServiceOperations {

    public static final String RETURN_L_TO_RESPONSE_L = "return $L.toResponse($L)";

    private final TypeName entity;

    public DefaultServiceImplServiceOperations(EndpointDefaultValue endpointDefaultValue) {
        super(endpointDefaultValue);

        entity = TypeName.get(endpointDefaultValue.entity());
    }

    @Override
    public MethodSpec create() {

        CodeBlock.Builder cb = CodeBlock.builder();
        cb.beginControlFlow("if(" + getNullCheckSeparatedIdMethodNames(false) + ")")
                .addStatement("throw new $T(100, $S)", IdentifierException.class, "Id should be null to persist the entity")
                .endControlFlow()
                .addStatement("$T entity = $L.toEntity($L)", entity, MAPPER_FIELD_NAME, REQUEST_FIELD_NAME)
                .addStatement("$T persistedEntity = $L.save($L)", entity, REPOSITORY_FIELD_NAME, ENTITY_FIELD_NAME)
                .addStatement(RETURN_L_TO_RESPONSE_L, MAPPER_FIELD_NAME, "persistedEntity");

        return createMethod()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addCode(cb.build())
                .build();

    }

    @Override
    public MethodSpec update() {
        CodeBlock.Builder cb = CodeBlock.builder();

        cb.beginControlFlow("if(" + getNullCheckSeparatedIdMethodNames(true) + ")")
                .addStatement("throw new $T(100, $S)", IdentifierException.class, "Please provide non null id")
                .endControlFlow();

        if (endpointDefaultValue.idFields().size() > 1) {

            cb.addStatement("$T pk = new $T($L)", endpointDefaultValue.primaryKey(), endpointDefaultValue.primaryKey(), getCommaSeparatedIdMethodNames());
            cb.addStatement("$T entity = $L.getOne(pk)", entity, REPOSITORY_FIELD_NAME);
        } else {
            cb.addStatement("$T entity = $L.getOne($L.get$L())", entity, REPOSITORY_FIELD_NAME, REQUEST_FIELD_NAME, endpointDefaultValue.idFields().get(0).getName());
        }

        cb.addStatement("$L.updateEntity($L, $L)", MAPPER_FIELD_NAME, REQUEST_FIELD_NAME, ENTITY_FIELD_NAME)
                .addStatement("$T updatedPerson = $L.save($L)", entity, REPOSITORY_FIELD_NAME, ENTITY_FIELD_NAME)
                .addStatement(RETURN_L_TO_RESPONSE_L, MAPPER_FIELD_NAME, "updatedPerson");

        return updateMethod()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addCode(cb.build())

                .build();

    }

    private String getCommaSeparatedIdMethodNames() {
        StringBuilder sb = new StringBuilder();
        endpointDefaultValue.idFields().forEach(fn -> {
            sb.append("request.get");
            sb.append(fn.getName());
            sb.append("()");
            sb.append(", ");
        });

        return sb.substring(0, sb.length() - 2);
    }

    private String getNullCheckSeparatedIdMethodNames(boolean addNullableCheck) {
        StringBuilder sb = new StringBuilder();
        endpointDefaultValue.idFields().forEach(fn -> {
            sb.append("request.get");
            sb.append(fn.getName());
            sb.append(addNullableCheck ? "() == null" : "() != null");
            sb.append(" && ");
        });

        return sb.substring(0, sb.length() - 4);
    }

    @Override
    public MethodSpec delete() {
        return deleteMethod()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addStatement("$L.deleteById($L)", REPOSITORY_FIELD_NAME, "id")
                .build();

    }

    @Override
    public MethodSpec getOne() {
        return getOneMethod()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addStatement("$T entity = $L.getOne($L)", entity, REPOSITORY_FIELD_NAME, "id")
                .addStatement(RETURN_L_TO_RESPONSE_L, MAPPER_FIELD_NAME, ENTITY_FIELD_NAME)
                .build();

    }

    @Override
    public MethodSpec getAll() {
        ParameterizedTypeName page = ParameterizedTypeName.get(ClassName.get(Page.class), entity);
        return getAllMethod()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addStatement("$T entities = $L.findAll($L)", page, REPOSITORY_FIELD_NAME, "pageable")
                .addStatement("return $L.map(mapper::toResponse)", "entities")
                .build();

    }
}
