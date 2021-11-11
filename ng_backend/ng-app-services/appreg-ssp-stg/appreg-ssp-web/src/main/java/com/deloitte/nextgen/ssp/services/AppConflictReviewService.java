package com.deloitte.nextgen.ssp.services;

import com.deloitte.nextgen.ssp.responses.*;

import java.util.List;

public interface AppConflictReviewService {

    List<ApplicantsResponse> findByAppNum(String appNum);

    ContactConflictsResponse findContactDtl(String appNum);

    AppContactDetailsResponse fetchContactFromSSP(String appNum);

    AuthRepConflictsResponse findAuthRepDtl(String appNum);

    AuthRepResponse findAuthRepSSP(String appNum);
}
