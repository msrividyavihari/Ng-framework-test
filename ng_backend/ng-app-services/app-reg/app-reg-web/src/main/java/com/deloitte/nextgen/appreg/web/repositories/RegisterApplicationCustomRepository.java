package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.client.response.ApplicantsResponse;
import com.deloitte.nextgen.appreg.client.response.ArFetchProgressResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegisterApplicationCustomRepository {

    List<ArFetchProgressResponse> fetchProgressForApp(String appNum);

    List<ApplicantsResponse> fetchApplInfoPanelDetails(String appNum);

    List<ApplicantsResponse> fetchApplicantsInfoForAddApp(Long indvId);

    List<ApplicantsResponse> fetchApplicantsInfoForPrimApp (Long indvId);
}
