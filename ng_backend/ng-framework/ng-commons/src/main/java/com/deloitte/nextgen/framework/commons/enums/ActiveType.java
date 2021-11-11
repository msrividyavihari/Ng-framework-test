package com.deloitte.nextgen.framework.commons.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author nishmehta
 * @since 05/07/2021 10:36 AM
 */


public enum ActiveType {
    YES('Y'),
    NO('N');

    private final Character code;

    ActiveType(Character code) {
        this.code = code;
    }

    @JsonValue
    public Character getCode() {
        return code;
    }
}
