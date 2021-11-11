package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.commons.constants.PatternConstants;
import com.deloitte.nextgen.framework.validator.annotations.PhoneNumber;

/**
 * @author nishmehta
 * @since
 */

public class PhoneNumberValidator extends AbstractPatternValidator<PhoneNumber> {

    @Override
    protected String getRegex() {

        if (!annotation.regex().equals(".*")) {
            return annotation.regex();
        }

        if (annotation.type() == null) {
            return PatternConstants.US.PHONE_NUMBER;
        }

        return PatternConstants.Phone.INTL_NUM;
    }
}
