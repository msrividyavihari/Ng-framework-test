package com.deloitte.nextgen.ha.dashboard.dto;

import com.deloitte.nextgen.dc.common.dto.IndividualNameDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class AppealSearchResponseDto {

    @EqualsAndHashCode.Include
    @NonNull
    private BigInteger appealId;
    @NonNull
    private String appealTypeCd;
    private String appealStatusCd;
    private Integer ageInDays;
    private BigInteger caseNum;
    private IndividualNameDto primaryAppellant;
    private Long individualId;
    private Date lastUpdatedDt;
    private BigInteger docketId;

    @QueryProjection
    public AppealSearchResponseDto(@NonNull BigInteger appealId, @NonNull String appealTypeCd, String appealStatusCd
            , int ageInDays, BigInteger caseNo
            , BigInteger individualId, Date lastUpdatedDt, BigInteger docketId) {
        this.appealId = appealId;
        this.appealTypeCd = appealTypeCd;
        this.ageInDays = ageInDays;
        this.caseNum = caseNo;
        this.individualId = Optional.ofNullable(individualId).orElse(BigInteger.ZERO).longValue();
        this.lastUpdatedDt = lastUpdatedDt;
        this.docketId = docketId;
        this.appealStatusCd = appealStatusCd;
    }
}
