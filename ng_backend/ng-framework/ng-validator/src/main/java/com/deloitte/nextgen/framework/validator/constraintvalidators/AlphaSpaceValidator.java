package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.validator.annotations.AlphaSpace;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

/**
 * @author nishmehta
 * @since
 */
public class AlphaSpaceValidator extends AbstractStringValidator<AlphaSpace> {
    @Override
    public Function<String, Boolean> condition() {
        return StringUtils::isAlphaSpace;
    }
}
