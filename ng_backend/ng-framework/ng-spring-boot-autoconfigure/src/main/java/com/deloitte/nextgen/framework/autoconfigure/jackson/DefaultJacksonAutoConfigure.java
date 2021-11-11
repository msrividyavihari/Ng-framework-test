package com.deloitte.nextgen.framework.autoconfigure.jackson;

import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Configuration
@AutoConfigureAfter({JacksonAutoConfiguration.class, ApplicationProperties.class})
public class DefaultJacksonAutoConfigure {

    private JacksonProperties jacksonProperties;
    private ApplicationProperties applicationProperties;

    public DefaultJacksonAutoConfigure(JacksonProperties jacksonProperties, ApplicationProperties applicationProperties) {
        this.jacksonProperties = jacksonProperties;
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public JavaTimeModule javaTimeModule() {
        JavaTimeModule jtm = new JavaTimeModule();

        String dateFormat = applicationProperties.getJackson().getDatePattern();
        String timeFormat = applicationProperties.getJackson().getTimePattern();

        LocalDateTimeDeserializer localDateTimeDeserializer;
        if (StringUtils.isNotEmpty(jacksonProperties.getDateFormat())) {
            localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(jacksonProperties.getDateFormat()));
        } else {
            localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME);
        }

        jtm.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);

        LocalDateDeserializer localDateDeserializer = new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat));
        jtm.addDeserializer(LocalDate.class, localDateDeserializer);

        LocalTimeDeserializer localTimeDeserializer = new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timeFormat));
        jtm.addDeserializer(LocalTime.class, localTimeDeserializer);

        return jtm;
    }

    @Bean
    public Jdk8Module jdk8TimeModule() {
        return new Jdk8Module();
    }


}
