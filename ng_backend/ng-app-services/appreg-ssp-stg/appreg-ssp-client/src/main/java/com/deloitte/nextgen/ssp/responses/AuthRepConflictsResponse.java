package com.deloitte.nextgen.ssp.responses;

import lombok.*;

@Data
@NoArgsConstructor
public class AuthRepConflictsResponse {
    private AuthIndividualInformationResponse authIndividualInformationResponse;
    private AuthPhnDetailsResponse authPhnDetailsResponse;
}
