package com.deloitte.nextgen.ha.search.dto;

import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@ToString
@EqualsAndHashCode

public class AppealStatusDto {
    public BigDecimal getAppealCount() {
        return appealCount;
    }

    public void setAppealCount(BigDecimal appealCount) {
        this.appealCount = appealCount;
    }

    public String getAppealStatus() {
        return appealStatus;
    }

    public void setAppealStatus(String appealStatus) {
        this.appealStatus = appealStatus;
    }

    private BigDecimal appealCount;
    private String appealStatus;
}