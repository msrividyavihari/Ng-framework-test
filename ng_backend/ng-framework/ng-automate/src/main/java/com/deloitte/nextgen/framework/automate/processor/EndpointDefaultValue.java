package com.deloitte.nextgen.framework.automate.processor;

import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Builder;
import lombok.ToString;

import javax.lang.model.type.TypeMirror;
import java.util.List;

/**
 * @author nishmehta
 * @since 05/07/2021 6:41 PM
 */

@ToString
@Builder
public class EndpointDefaultValue implements EndpointValue {

    private final String name;

    private final TypeMirror entity;

    private final TypeMirror request;

    private final TypeMirror response;

    private final TypeMirror mapper;

    private final TypeMirror idClass;

    private final TypeEnum type;

    private final List<IdField> fields;

    private final String className;

    @Override
    public TypeEnum type() {
        return type;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public TypeMirror entity() {
        return entity;
    }

    @Override
    public TypeMirror mapper() {
        return mapper;
    }

    @Override
    public TypeMirror response() {
        return response;
    }

    @Override
    public TypeMirror request() {
        return request;
    }

    @Override
    public TypeMirror primaryKey() {
        return idClass;
    }

    @Override
    public List<IdField> idFields() {
        return fields;
    }

    @Override
    public String className() {
        return className;
    }
}

