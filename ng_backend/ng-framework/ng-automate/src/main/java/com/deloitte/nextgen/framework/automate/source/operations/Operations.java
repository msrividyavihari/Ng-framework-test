package com.deloitte.nextgen.framework.automate.source.operations;

import com.squareup.javapoet.MethodSpec;

/**
 * @author nishmehta
 * @since 05/07/2021 7:15 PM
 */
public interface Operations {

    MethodSpec create();

    MethodSpec update();

    MethodSpec delete();

    MethodSpec getOne();

    MethodSpec getAll();

}
