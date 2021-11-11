package com.deloitte.nextgen.framework.validator.constraintvalidators;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorInitializationContext;

import javax.validation.ClockProvider;
import javax.validation.ConstraintValidatorContext;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.Annotation;
import java.time.Clock;
import java.time.Duration;
import java.time.temporal.TemporalAccessor;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
public abstract class AbstractKairosValidator<C extends Annotation, T extends TemporalAccessor & Comparable<? super T>>
        implements HibernateConstraintValidator<C, T> {

    private ClockProvider clockProvider;

    @Override
    public void initialize(ConstraintDescriptor<C> constraintDescriptor, HibernateConstraintValidatorInitializationContext initializationContext) {
        clockProvider = initializationContext.getClockProvider();
    }

    @Override
    public boolean isValid(T value, ConstraintValidatorContext context) {
        if ( value == null ) {
            return true;
        }

        int result = value.compareTo( getReferenceValue( clockProvider.getClock() ) );

        return isValid( result );
    }

    protected abstract Duration getEffectiveTemporalValidationTolerance(Duration absoluteTemporalValidationTolerance);

    protected abstract T getReferenceValue(Clock reference);

    protected abstract boolean isValid(int result);

}
