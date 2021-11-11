package com.deloitte.nextgen.ssp.responses;

import lombok.*;

@Data
@NoArgsConstructor
public class WeekDayPreferenceConflictsResponse {
    private String panelName;
    private String weekdayContMethSw;
    private String weekdayContTime;
}
