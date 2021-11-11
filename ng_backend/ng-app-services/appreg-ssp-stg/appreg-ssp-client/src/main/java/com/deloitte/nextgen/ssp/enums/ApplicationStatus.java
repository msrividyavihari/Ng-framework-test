package com.deloitte.nextgen.ssp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ApplicationStatus {

    APPLICATION_PENDING("AP"),
    CASE_PENDING("CP"),
    DENIED("DE"),
    DISPOSED("DI"),
    APPLICATION_COMPLETE("AC"),
    PENDING_APPLICATION_DENIED("DP"),
    CASE_DENIED("DN"),
    FILED_IN_ERROR("FI");

    private static Map<String, ApplicationStatus> FORMAT_MAP = Stream
            .of(ApplicationStatus.values())
            .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

    @JsonValue
    public String getFormatted() {
        return formatted;
    }

    private final String formatted;

    ApplicationStatus(String formatted) {
        this.formatted = formatted;
    }

    @JsonCreator // This is the factory method and must be static
    public static ApplicationStatus fromChar(String c) {
        return Optional
                .ofNullable(FORMAT_MAP.get(c))
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(c)));
    }
}
