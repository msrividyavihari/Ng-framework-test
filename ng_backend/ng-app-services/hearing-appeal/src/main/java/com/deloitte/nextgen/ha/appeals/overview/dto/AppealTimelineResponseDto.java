package com.deloitte.nextgen.ha.appeals.overview.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class AppealTimelineResponseDto {

    private boolean editStatus;
    private String statusCd;
    private LocalDate statusEffDt;
    @Setter(AccessLevel.NONE)
    private List<String> notes = new ArrayList<>();

}
