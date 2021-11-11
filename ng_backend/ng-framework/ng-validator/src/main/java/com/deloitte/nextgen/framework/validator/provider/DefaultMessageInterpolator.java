package com.deloitte.nextgen.framework.validator.provider;

import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;

import javax.validation.MessageInterpolator;
import java.util.Locale;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
public class DefaultMessageInterpolator implements MessageInterpolator {

    private MessageService messageService;

    public DefaultMessageInterpolator(MessageService messageService) {
        this.messageService = messageService;
        log.info(LogMarker.VALIDATOR, "initialized default message interpolator");
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        return interpolate(messageTemplate, context, null);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        String code = context.getConstraintDescriptor().getMessageTemplate();
        return messageService.getMessage(code, locale);
    }
}
