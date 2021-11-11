package com.deloitte.nextgen.appreg.web.services;

import com.deloitte.nextgen.appreg.client.response.AppSearchResponse;
import com.deloitte.nextgen.appreg.client.request.AppSearchRequest;

import java.util.List;

public interface ApplicationSearchService {

    public List<AppSearchResponse> findApplications(AppSearchRequest appSearchRequest);
    public String deleteApp(String appNum);
}
