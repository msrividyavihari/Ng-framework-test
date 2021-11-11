package com.deloitte.nextgen.ha.edit.dto;

import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;


@Data
@ToString
@EqualsAndHashCode
@Builder
public class EditAppealDto {
    @NonNull
    private BigInteger appealNum;
    @NonNull
    private String appealStatusCd;
    @NonNull
    private Timestamp appealStatusEffectiveDt;
    private String appealNotes;
    private String resolvedReason;
    private LocalDate resolvedDt;
    private String appealStatusCdOld;
}