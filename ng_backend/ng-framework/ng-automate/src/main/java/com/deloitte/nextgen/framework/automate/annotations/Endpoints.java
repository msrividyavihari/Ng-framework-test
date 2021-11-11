package com.deloitte.nextgen.framework.automate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nishmehta
 * @since 29/06/2021 11:34 AM
 */


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Endpoints {
    Endpoint[] value();
}
