package com.deloitte.nextgen.framework.commons.spi;

import javax.validation.ClockProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author nishmehta
 * @since 1.0.0
 */


public interface KairosClock extends ClockProvider {

    LocalDate localDate();

    LocalTime localTime();

    LocalDateTime localDateTime();
}
