package com.deloitte.nextgen.ha.appeals.overview.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppealEditStatusRequestDto {
    private LocalDate effectiveDate;
    private String newStatusCd;
    private String resolvedReasonCd;
    private String notes;
}
