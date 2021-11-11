package com.deloitte.nextgen.framework.commons.validator;

import java.time.LocalDate;

/**
 * @author nishmehta on 01/04/2021 2:36 PM
 * @project ng-libs
 */
public interface IEffectiveDate {

    LocalDate getEffBeginDt();

    LocalDate getEffEndDt();
}
