package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.validator.annotations.CaseNumber;

/**
 * @author nishmehta
 * @since 1.0.0
 */
public class CaseNumberValidator extends AbstractPatternValidator<CaseNumber> {
    @Override
    protected String getRegex() {
        return annotation.regex();
    }
}
