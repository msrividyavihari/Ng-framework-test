package com.deloitte.nextgen.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part7Details {
    private Boolean displayFlagForOngoingEarnedIncomeSection;
    private OngoingEarnedIncomeDetails ongoingEarnedIncomeDetails;
}
