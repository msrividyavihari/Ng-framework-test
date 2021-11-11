package com.deloitte.nextgen.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part9Details {
    private Boolean displayFlagForOngoingUnearnedIncomeSection;
    private List<OngoingUnearnedIncomeDetails> ongoingUnearnedIncomeDetails;
}
