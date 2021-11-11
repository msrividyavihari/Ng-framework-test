package com.deloitte.nextgen.framework.automate.processor;

import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;

import javax.lang.model.type.TypeMirror;
import java.util.List;

/**
 * @author nishmehta
 * @since 03/08/2021 4:40 PM
 */
public interface EndpointValue {

    TypeEnum type();

    String name();

    TypeMirror entity();

    TypeMirror mapper();

    TypeMirror response();

    TypeMirror request();

    TypeMirror primaryKey();

    List<IdField> idFields();

    String className();
}
