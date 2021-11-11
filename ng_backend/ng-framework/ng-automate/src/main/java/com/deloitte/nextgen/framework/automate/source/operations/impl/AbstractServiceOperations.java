package com.deloitte.nextgen.framework.automate.source.operations.impl;

import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.deloitte.nextgen.framework.automate.source.operations.Operations;
import com.squareup.javapoet.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author nishmehta
 * @since 07/07/2021 3:07 PM
 */
public abstract class AbstractServiceOperations implements Operations {

    protected final EndpointDefaultValue endpointDefaultValue;

    private final TypeName response;
    private final TypeName request;
    private final TypeName idClass;

    protected AbstractServiceOperations(EndpointDefaultValue endpointDefaultValue) {
        this.endpointDefaultValue = endpointDefaultValue;

        response = TypeName.get(endpointDefaultValue.response());
        request = TypeName.get(endpointDefaultValue.request());
        idClass = TypeName.get(endpointDefaultValue.primaryKey());
    }

    protected MethodSpec.Builder createMethod() {

        return MethodSpec.methodBuilder("persist")
                .returns(response)
                .addParameter(ParameterSpec.builder(request, "request")
                        .addAnnotation(NotNull.class)
                        .addAnnotation(Valid.class).build());

    }

    protected MethodSpec.Builder updateMethod() {

        return MethodSpec.methodBuilder("update")
                .returns(response)
                .addParameter(ParameterSpec.builder(request, "request")
                        .addAnnotation(NotNull.class)
                        .addAnnotation(Valid.class)
                        .build());
    }

    protected MethodSpec.Builder deleteMethod() {

        return MethodSpec.methodBuilder("delete")
                .addParameter(ParameterSpec.builder(idClass, "id")
                        .addAnnotation(NotNull.class)
                        .build());

    }

    protected MethodSpec.Builder getOneMethod() {

        return MethodSpec.methodBuilder("getOne")
                .returns(response)
                .addParameter(ParameterSpec.builder(idClass, "id")
                        .addAnnotation(NotNull.class)
                        .build());

    }

    protected MethodSpec.Builder getAllMethod() {

        return MethodSpec.methodBuilder("getAll")
                .returns(ParameterizedTypeName.get(ClassName.get(Page.class), response))
                .addParameter(ParameterSpec.builder(Pageable.class, "pageable").addAnnotation(PageableDefault.class).build());

    }

}
