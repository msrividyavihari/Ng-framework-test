package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.commons.constants.PatternConstants;
import com.deloitte.nextgen.framework.validator.annotations.ZipCode;

/**
 * @author nishmehta
 * @since
 */

public class ZipCodeValidator extends AbstractPatternValidator<ZipCode> {

    @Override
    protected String getRegex() {
        return PatternConstants.US.POSTAL_CODE;
    }
}
