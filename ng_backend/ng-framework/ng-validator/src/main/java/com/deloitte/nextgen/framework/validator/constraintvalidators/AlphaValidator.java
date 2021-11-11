package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.validator.annotations.Alpha;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

/**
 * @author nishmehta
 * @since
 */
public class AlphaValidator extends AbstractStringValidator<Alpha> {
    @Override
    public Function<String, Boolean> condition() {
        return StringUtils::isAlpha;
    }
}
