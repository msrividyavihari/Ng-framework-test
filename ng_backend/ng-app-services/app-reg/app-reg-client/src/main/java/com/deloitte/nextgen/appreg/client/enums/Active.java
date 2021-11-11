package com.deloitte.nextgen.appreg.client.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Active {
    YES('Y'),
    NO('N');

    private static Map<Character, Active> FORMAT_MAP = Stream
            .of(Active.values())
            .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

    @JsonValue
    public Character getFormatted() {
        return formatted;
    }

    private final Character formatted;

    Active(char formatted) {
        this.formatted = formatted;
    }

    @JsonCreator // This is the factory method and must be static
    public static Active fromChar(Character c) {
        return Optional
                .ofNullable(FORMAT_MAP.get(c))
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(c)));
    }
}
