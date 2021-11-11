package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.commons.validator.IEffectiveDate;
import com.deloitte.nextgen.framework.validator.annotations.EffectiveDates;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorInitializationContext;

import javax.validation.ClockProvider;
import javax.validation.ConstraintValidatorContext;
import javax.validation.metadata.ConstraintDescriptor;
import java.time.LocalDate;

/**
 * @author nishmehta on 30/03/2021 1:01 PM
 * @project ng-validator
 */
public class EffectiveDatesValidator implements HibernateConstraintValidator<EffectiveDates, IEffectiveDate> {


    private ClockProvider clockProvider;

    @Override
    public void initialize(ConstraintDescriptor<EffectiveDates> constraintDescriptor, HibernateConstraintValidatorInitializationContext initializationContext) {
        clockProvider = initializationContext.getClockProvider();
    }

    @Override
    public boolean isValid(IEffectiveDate effDate, ConstraintValidatorContext context) {

        if (effDate.getEffBeginDt() == null) {
            return true;
        }

        if (effDate.getEffEndDt() == null) {
            return true;
        }
        LocalDate localDate = LocalDate.now(clockProvider.getClock());

        if(effDate.getEffBeginDt().isAfter(localDate)) {

            context.buildConstraintViolationWithTemplate("EffectiveBeginDate is after system date.")
                    .addPropertyNode("effBeginDt")
                    .addConstraintViolation();

            return false;
        }

        if (effDate.getEffEndDt().isBefore(effDate.getEffBeginDt())) {

            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("effEndDt")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
