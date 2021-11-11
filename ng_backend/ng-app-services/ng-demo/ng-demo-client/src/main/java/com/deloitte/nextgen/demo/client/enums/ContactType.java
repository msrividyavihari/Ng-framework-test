package com.deloitte.nextgen.demo.client.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author nishmehta
 * @since 16/08/2021 2:15 PM
 */
public enum ContactType {

    HOME('H'),
    WORK('W'),
    OTHER('O');

    private final Character code;

    ContactType(Character code) {
        this.code = code;
    }

    @JsonValue
    public Character getCode() {
        return code;
    }
}
