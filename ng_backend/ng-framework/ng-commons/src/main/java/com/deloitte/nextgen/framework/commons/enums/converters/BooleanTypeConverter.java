package com.deloitte.nextgen.framework.commons.enums.converters;

import com.deloitte.nextgen.framework.commons.enums.BooleanType;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Converter(autoApply = true)
public class BooleanTypeConverter implements AttributeConverter<BooleanType, Character> {

    @Override
    public Character convertToDatabaseColumn(BooleanType type) {
        if (type == null) {
            return null;
        }
        return type.getCode();
    }

    @Override
    @JsonCreator
    public BooleanType convertToEntityAttribute(final Character code) {
        if (code == null) {
            return null;
        }

        return Stream.of(BooleanType.values())
                .filter(t -> t.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}