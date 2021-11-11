package com.deloitte.nextgen.framework.commons.enums.converters;

import com.deloitte.nextgen.framework.commons.enums.ActiveType;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Converter(autoApply = true)
public class ActiveTypeConverter implements AttributeConverter<ActiveType, Character> {

    @Override
    public Character convertToDatabaseColumn(ActiveType type) {
        if (type == null) {
            return null;
        }
        return type.getCode();
    }

    @Override
    @JsonCreator
    public ActiveType convertToEntityAttribute(final Character code) {
        if (code == null) {
            return null;
        }

        return Stream.of(ActiveType.values())
                .filter(t -> t.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}