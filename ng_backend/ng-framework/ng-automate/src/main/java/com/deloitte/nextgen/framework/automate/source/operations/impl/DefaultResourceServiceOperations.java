package com.deloitte.nextgen.framework.automate.source.operations.impl;

import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.squareup.javapoet.*;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author nishmehta
 * @since 07/07/2021 7:40 PM
 */
public class DefaultResourceServiceOperations extends AbstractControllerOperations {

    private static final String PATH = "path";
    private static final String CONSUMES = "consumes";
    private static final String PRODUCES = "produces";
    private final TypeMirror resourceEndpoint;

    public DefaultResourceServiceOperations(EndpointDefaultValue endpointDefaultValue, TypeMirror resourceEndpoint) {
        super(endpointDefaultValue);
        this.resourceEndpoint = resourceEndpoint;

    }

    @Override
    public MethodSpec create() {

        CodeBlock cb = CodeBlock.builder()
                .addStatement("$T response = service.persist(request)", endpointDefaultValue.response())
                .addStatement("return $T.created(100).data(response)", ApiResponse.class)
                .build();

        CodeBlock path = CodeBlock.builder()
                .add("$T.$T.V1_CREATE", resourceEndpoint, endpointDefaultValue.entity())
                .build();

        return createMethod()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(
                        AnnotationSpec.builder(PutMapping.class)
                                .addMember(PATH, path)
                                .addMember(CONSUMES, "$S", MediaType.APPLICATION_JSON_VALUE)
                                .addMember(PRODUCES, "$S", MediaType.APPLICATION_JSON_VALUE)
                                .build()
                )
                .addCode(cb)
                .build();

    }

    @Override
    public MethodSpec update() {
        CodeBlock cb = CodeBlock.builder()
                .addStatement("$T response = service.update(request)", endpointDefaultValue.response())
                .addStatement("return $T.success(100).data(response)", ApiResponse.class)
                .build();

        CodeBlock path = CodeBlock.builder().add("$T.$T.V1_UPDATE", resourceEndpoint, endpointDefaultValue.entity()).build();
        return updateMethod()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(PostMapping.class)
                        .addMember(PATH, path)
                        .addMember(CONSUMES, "$S", MediaType.APPLICATION_JSON_VALUE)
                        .addMember(PRODUCES, "$S", MediaType.APPLICATION_JSON_VALUE)
                        .build())
                .addCode(cb)
                .build();

    }

    private String getCommaSeparatedId(final String keyName) {

        final StringBuilder sb = new StringBuilder();

        AtomicInteger i= new AtomicInteger();
        endpointDefaultValue.idFields().forEach(idField -> {
            if (idField.getType().isBoxedPrimitive()) {
                sb.append(idField.getType().box());
                sb.append(".valueOf(");
                sb.append(keyName);
                sb.append("[");
                sb.append(i.getAndIncrement());
                sb.append("])");
                sb.append(", ");
            } else {
                sb.append(keyName);
                sb.append("[");
                sb.append(i.getAndIncrement());
                sb.append("]");
                sb.append(", ");
            }
        });

        return sb.substring(0, sb.length() - 2);
    }

    @Override
    public MethodSpec delete() {
        CodeBlock.Builder cb = CodeBlock.builder();
        if (endpointDefaultValue.idFields().size() > 1) {
            cb.addStatement("String extractKeys[] = id.split(\":\")", endpointDefaultValue.response())
                    .addStatement("$T key = new $T(" + getCommaSeparatedId("extractKeys") + ")", endpointDefaultValue.primaryKey(), endpointDefaultValue.primaryKey())
                    .addStatement("service.delete(key)", endpointDefaultValue.response());
        } else {
            cb.addStatement("service.delete(id)", endpointDefaultValue.response());
        }


        cb.addStatement("return $T.deleted(100)", ApiResponse.class);

        CodeBlock path = CodeBlock.builder().add("$T.$T.V1_DELETE", resourceEndpoint, endpointDefaultValue.entity()).build();
        return deleteMethod()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(DeleteMapping.class)
                        .addMember(PATH, path)
                        .addMember(PRODUCES, "$S", MediaType.APPLICATION_JSON_VALUE)
                        .build())
                .addCode(cb.build())
                .build();

    }

    @Override
    public MethodSpec getOne() {
        CodeBlock.Builder cb = CodeBlock.builder();
        if (endpointDefaultValue.idFields().size() > 1) {

            cb.addStatement("String extractKeys[] = id.split(\":\")", endpointDefaultValue.response())
                    .addStatement("$T key = new $T(" + getCommaSeparatedId("extractKeys") + ")", endpointDefaultValue.primaryKey(), endpointDefaultValue.primaryKey())
                    .addStatement("$T dto = service.getOne(key)", endpointDefaultValue.response());
        } else {
            cb.addStatement("$T dto = service.getOne(id)", endpointDefaultValue.response());
        }


        cb.addStatement("return $T.success(100).data(dto)", ApiResponse.class);

        CodeBlock path = CodeBlock.builder().add("$T.$T.V1_GET", resourceEndpoint, endpointDefaultValue.entity()).build();
        return getOneMethod()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(GetMapping.class)
                        .addMember(PATH, path)
                        .addMember(PRODUCES, "$S", MediaType.APPLICATION_JSON_VALUE)
                        .build())
                .addCode(cb.build())
                .build();

    }

    @Override
    public MethodSpec getAll() {
        ParameterizedTypeName page = ParameterizedTypeName.get(ClassName.get(Page.class), TypeName.get(endpointDefaultValue.response()));
        CodeBlock path = CodeBlock.builder().add("$T.$T.V1_GET_ALL", resourceEndpoint, endpointDefaultValue.entity()).build();
        return getAllMethod()
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(GetMapping.class)
                        .addMember(PATH, path)
                        .addMember(PRODUCES, "$S", MediaType.APPLICATION_JSON_VALUE)
                        .build())
                .addStatement("$T dtos = service.getAll($L)", page, "pageable")
                .addStatement("return $T.pageable(dtos)", ApiResponse.class)
                .build();

    }
}
