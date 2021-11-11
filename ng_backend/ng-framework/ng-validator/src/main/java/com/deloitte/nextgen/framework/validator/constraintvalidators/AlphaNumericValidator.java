package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.validator.annotations.AlphaNumeric;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

/**
 * @author nishmehta
 * @since 1.0.0
 */

public class AlphaNumericValidator extends AbstractStringValidator<AlphaNumeric> {
    @Override
    public Function<String, Boolean> condition() {
        return StringUtils::isAlphanumeric;
    }
}
