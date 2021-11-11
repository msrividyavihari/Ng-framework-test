package com.deloitte.nextgen.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part6Details {
    private Boolean displayFlagForPart6ResourcesSection;
    private Boolean displayFlagForOngoingResourcesSection;
    private Boolean displayFlagForLTCWaiverDisabilitySection;
    private OngoingResourcesDetails ongoingResourcesDetails;
}
