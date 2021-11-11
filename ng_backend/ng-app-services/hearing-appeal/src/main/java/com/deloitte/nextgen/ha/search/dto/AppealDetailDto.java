package com.deloitte.nextgen.ha.search.dto;

import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@ToString
@EqualsAndHashCode
@Builder
public class AppealDetailDto {
    private BigInteger appealNum;
    private String appealStatusCd;
    private LocalDate appealLastUpdatedDt;
}