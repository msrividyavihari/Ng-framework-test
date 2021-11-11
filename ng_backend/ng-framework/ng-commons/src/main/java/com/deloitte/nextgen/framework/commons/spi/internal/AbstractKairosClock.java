package com.deloitte.nextgen.framework.commons.spi.internal;

import com.deloitte.nextgen.framework.commons.constants.Constants;
import com.deloitte.nextgen.framework.commons.spi.KairosClock;
import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
public class AbstractKairosClock implements KairosClock {

    private HttpServletRequest request;

    public AbstractKairosClock(HttpServletRequest request){
        this.request = request;
    }

    private static Clock now() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Override
    public Clock getClock() {
        Clock clock = now();
        String ttd = request.getHeader(Constants.Headers.KAIROS_DATE);
        if (StringUtils.isNotEmpty(ttd)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Constants.DateTimeFormat.MMddyyyy);
            Instant instant = LocalDate.parse(ttd, dtf).atStartOfDay().toInstant(ZoneOffset.UTC);
            clock = Clock.fixed(instant, ZoneId.systemDefault());
            log.info(LogMarker.COMMONS, "Found time travel date in header. Updating application date to {}", clock);
        }
        return clock;
    }

    @Override
    public LocalDate localDate() {
        return LocalDate.now(getClock());
    }

    @Override
    public LocalTime localTime() {
        return LocalTime.now(getClock());
    }

    @Override
    public LocalDateTime localDateTime() {
        return LocalDateTime.now(getClock());
    }
}
