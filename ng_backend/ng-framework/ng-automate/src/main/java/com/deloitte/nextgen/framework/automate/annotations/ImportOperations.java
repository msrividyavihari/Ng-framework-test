package com.deloitte.nextgen.framework.automate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nishmehta
 * @since 26/07/2021 11:39 AM
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ImportOperations {

    Class<?> value();
}
