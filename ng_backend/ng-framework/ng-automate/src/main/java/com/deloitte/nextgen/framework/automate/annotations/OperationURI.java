package com.deloitte.nextgen.framework.automate.annotations;

import java.lang.annotation.*;

/**
 * @author nishmehta
 * @since 09/07/2021 11:21 AM
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Repeatable(OperationURIs.class)
public @interface OperationURI {

    String value();
}
