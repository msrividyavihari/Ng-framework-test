package com.deloitte.nextgen.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OngoingUnearnedIncomeDetails {
    private String unearnedIncomeIndividual;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp unearnedIncomeIndividualDOB;
    private Double unearnedIncomeAmount;
    private String unearnedIncomeType;
}
