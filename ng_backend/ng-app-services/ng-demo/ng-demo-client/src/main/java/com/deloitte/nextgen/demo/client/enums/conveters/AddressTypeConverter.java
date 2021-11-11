package com.deloitte.nextgen.demo.client.enums.conveters;

import com.deloitte.nextgen.demo.client.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Converter(autoApply = true)
public class AddressTypeConverter implements AttributeConverter<AddressType, String> {

    @Override
    public String convertToDatabaseColumn(AddressType type) {
        if (type == null) {
            return null;
        }
        return type.getCode();
    }

    @Override
    @JsonCreator
    public AddressType convertToEntityAttribute(final String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(AddressType.values())
                .filter(t -> t.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}