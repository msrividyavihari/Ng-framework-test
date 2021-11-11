package com.deloitte.nextgen.framework.automate.source.operations.impl;

import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.deloitte.nextgen.framework.automate.source.operations.Operations;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.framework.commons.payload.response.PagedData;
import com.squareup.javapoet.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author nishmehta
 * @since 07/07/2021 3:07 PM
 */
public abstract class AbstractControllerOperations implements Operations {

    protected final EndpointDefaultValue endpointDefaultValue;

    private final TypeName response;
    private final TypeName request;

    protected AbstractControllerOperations(EndpointDefaultValue endpointDefaultValue) {

        this.endpointDefaultValue = endpointDefaultValue;

        response = TypeName.get(endpointDefaultValue.response());
        request = TypeName.get(endpointDefaultValue.request());
    }

    protected MethodSpec.Builder createMethod() {

        return MethodSpec.methodBuilder("persist")
                .returns(
                        ParameterizedTypeName.get(
                                ClassName.get(ResponseEntity.class),
                                ParameterizedTypeName.get(ClassName.get(ApiResponse.class), response))
                )
                .addParameter(ParameterSpec.builder(request, "request")
                        .addAnnotation(Valid.class)
                        .addAnnotation(RequestBody.class)
                        .build());

    }

    protected MethodSpec.Builder updateMethod() {

        return MethodSpec.methodBuilder("update")
                .returns(
                        ParameterizedTypeName.get(
                                ClassName.get(ResponseEntity.class),
                                ParameterizedTypeName.get(ClassName.get(ApiResponse.class), response))
                )
                .addParameter(ParameterSpec.builder(request, "request")
                        .addAnnotation(Valid.class)
                        .addAnnotation(RequestBody.class).build());

    }

    protected MethodSpec.Builder deleteMethod() {

        MethodSpec.Builder delete = MethodSpec.methodBuilder("delete")
                .returns(
                        ParameterizedTypeName.get(
                                ClassName.get(ResponseEntity.class),
                                ParameterizedTypeName.get(ClassName.get(ApiResponse.class), response))
                );

        if (endpointDefaultValue.idFields().size() > 1) {
            delete.addParameter(ParameterSpec.builder(String.class, "id")
                    .addAnnotation(NotNull.class)
                    .addAnnotation(PathVariable.class)
                    .build());
        } else {

            delete.addParameter(ParameterSpec.builder(Long.class, "id")
                    .addAnnotation(NotNull.class)
                    .addAnnotation(PathVariable.class)
                    .build());
        }

        return delete;
    }

    protected MethodSpec.Builder getOneMethod() {

        MethodSpec.Builder getOne = MethodSpec.methodBuilder("getOne")
                .returns(
                        ParameterizedTypeName.get(
                                ClassName.get(ResponseEntity.class),
                                ParameterizedTypeName.get(ClassName.get(ApiResponse.class), TypeName.get(endpointDefaultValue.response())))
                );

        if (endpointDefaultValue.idFields().size() > 1) {
            getOne.addParameter(ParameterSpec.builder(String.class, "id")
                    .addAnnotation(NotNull.class)
                    .addAnnotation(PathVariable.class)
                    .build());
        } else {

            getOne.addParameter(ParameterSpec.builder(Long.class, "id")
                    .addAnnotation(NotNull.class)
                    .addAnnotation(PathVariable.class)
                    .build());
        }

        return getOne;
    }

    protected MethodSpec.Builder getAllMethod() {

        return MethodSpec.methodBuilder("getAll")
                .returns(ParameterizedTypeName.get(ClassName.get(ResponseEntity.class),
                            ParameterizedTypeName.get(ClassName.get(ApiResponse.class),
                                ParameterizedTypeName.get(ClassName.get(PagedData.class), TypeName.get(endpointDefaultValue.response())))
                        )
                )
                .addParameter(ParameterSpec.builder(Pageable.class, "pageable").addAnnotation(PageableDefault.class).build());

    }

}
