package com.deloitte.nextgen.appreg.client.response;

import lombok.*;

@Data
@NoArgsConstructor
public class WeekDayPreferenceConflictsResponse {
    private String panelName;
    private String weekdayContMethSw;
    private String weekdayContTime;
}