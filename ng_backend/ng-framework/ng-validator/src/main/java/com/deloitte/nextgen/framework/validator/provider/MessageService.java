package com.deloitte.nextgen.framework.validator.provider;

import java.util.Locale;

/**
 * @author nishmehta
 * @since 1.0.0
 */
public interface MessageService {

    String getMessage(String code, Locale locale);
}
