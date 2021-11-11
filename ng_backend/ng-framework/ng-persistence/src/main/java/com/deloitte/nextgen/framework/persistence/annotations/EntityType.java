package com.deloitte.nextgen.framework.persistence.annotations;

import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityType {

    TypeEnum type() default TypeEnum.ZERO;
    Class customRepository() default void.class;
}
