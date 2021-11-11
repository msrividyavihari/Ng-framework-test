package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.validator.annotations.Numeric;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

/**
 * @author nishmehta
 * @since
 */
public class NumericValidator extends AbstractStringValidator<Numeric> {
    @Override
    public Function<String, Boolean> condition() {
        return StringUtils::isNumeric;
    }
}
