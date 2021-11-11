package com.deloitte.nextgen.framework.commons.enums;

/**
 * @author nishmehta
 * @since 05/07/2021 10:36 AM
 */
public enum BooleanType {
    TRUE('T'),
    FALSE('F');

    private final Character code;

    BooleanType(Character code) {
        this.code = code;
    }

    public Character getCode() {
        return code;
    }
}
