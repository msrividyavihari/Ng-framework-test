package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.commons.constants.PatternConstants;
import com.deloitte.nextgen.framework.validator.annotations.SSN;

import java.util.regex.Pattern;

/**
 * @author mukepatel on 13/07/2021 1:01 PM
 * @project ng-validator
 */
public class SSNValidator extends AbstractPatternValidator<SSN> {

    private static final Pattern pattern = Pattern.compile(PatternConstants.US.SSN);

    @Override
    protected String getRegex() {
        return PatternConstants.US.SSN;
    }
}
