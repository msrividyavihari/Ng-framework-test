package com.deloitte.nextgen.appreg.client.entities.converters;

import com.deloitte.nextgen.appreg.client.enums.Active;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply=true)
public class ActiveConverter implements AttributeConverter<Active, Character> {

    @Override
    public Character convertToDatabaseColumn(Active type) {
        if (type == null) {
            return null;
        }
        return type.getFormatted();
    }

    @Override
    @JsonCreator
    public Active convertToEntityAttribute(final Character code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Active.values()).filter(t -> t.getFormatted().equals(code)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
