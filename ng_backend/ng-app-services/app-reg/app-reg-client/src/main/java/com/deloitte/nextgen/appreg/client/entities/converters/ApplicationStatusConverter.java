package com.deloitte.nextgen.appreg.client.entities.converters;

import com.deloitte.nextgen.appreg.client.enums.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply=true)
public class ApplicationStatusConverter implements AttributeConverter<ApplicationStatus, String> {

    @Override
    public String convertToDatabaseColumn(ApplicationStatus type) {
        if (type == null) {
            return null;
        }
        return type.getFormatted();
    }

    @Override
    @JsonCreator
    public ApplicationStatus convertToEntityAttribute(final String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(ApplicationStatus.values()).filter(t -> t.getFormatted().equals(code)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
