package com.deloitte.nextgen.appreg.client.response;

import lombok.*;

@Data
@NoArgsConstructor
public class AuthRepConflictsResponse {
    private AuthIndividualInformationResponse authIndividualInformationResponse;
    private AuthPhnDetailsResponse authPhnDetailsResponse;
}
