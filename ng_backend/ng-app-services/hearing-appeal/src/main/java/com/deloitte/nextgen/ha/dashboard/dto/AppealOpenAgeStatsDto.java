package com.deloitte.nextgen.ha.dashboard.dto;

import lombok.*;

@Builder
@Value
@ToString
@EqualsAndHashCode
public class AppealOpenAgeStatsDto {
    private int under30Days;
    private int between31And60Days;
    private int between61And90Days;
    private int above90Days;
    private int averageOpenDays;
}
