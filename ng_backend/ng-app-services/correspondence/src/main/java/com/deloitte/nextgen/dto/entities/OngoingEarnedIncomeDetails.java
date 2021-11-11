package com.deloitte.nextgen.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OngoingEarnedIncomeDetails {
    private String earnedIncomeIndividual;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp earnedIncomeIndividualDOB;
    private Double earnedIncomeAmount;
    private String employerName;
}
