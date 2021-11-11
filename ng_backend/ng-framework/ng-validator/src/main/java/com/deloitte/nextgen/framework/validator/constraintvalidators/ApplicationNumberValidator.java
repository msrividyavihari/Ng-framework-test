package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.validator.annotations.ApplicationNumber;

/**
 * @author nishmehta
 * @since 1.0.0
 */
public class ApplicationNumberValidator extends AbstractPatternValidator<ApplicationNumber> {
    @Override
    protected String getRegex() {
        return annotation.regex();
    }
}
