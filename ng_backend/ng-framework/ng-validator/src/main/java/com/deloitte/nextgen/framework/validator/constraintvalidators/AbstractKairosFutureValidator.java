package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.validator.annotations.KairosFuture;

import java.time.Duration;
import java.time.temporal.TemporalAccessor;

/**
 * @author nishmehta
 * @since 1.0.0
 */
public abstract class AbstractKairosFutureValidator<T extends TemporalAccessor & Comparable<? super T>>
        extends AbstractKairosValidator<KairosFuture, T> {

    @Override
    protected boolean isValid(int result) {
        return result > 0;
    }

    @Override
    protected Duration getEffectiveTemporalValidationTolerance(Duration absoluteTemporalValidationTolerance) {
        return absoluteTemporalValidationTolerance;
    }
}
