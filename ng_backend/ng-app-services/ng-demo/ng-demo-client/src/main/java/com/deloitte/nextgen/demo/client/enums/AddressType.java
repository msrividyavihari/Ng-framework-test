package com.deloitte.nextgen.demo.client.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author nishmehta
 * @since 16/08/2021 2:31 PM
 */
public enum AddressType {

    MAILING_ADDRESS("MA"),
    RESIDENCE_ADDRESS("PA"),
    WORK_ADDRESS("WA");

    private final String code;

    AddressType(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
