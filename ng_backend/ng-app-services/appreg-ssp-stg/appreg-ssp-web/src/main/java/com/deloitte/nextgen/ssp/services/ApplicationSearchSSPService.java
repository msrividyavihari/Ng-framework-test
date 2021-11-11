package com.deloitte.nextgen.ssp.services;

import com.deloitte.nextgen.ssp.responses.AppSearchSSPResponse;
import com.deloitte.nextgen.ssp.request.AppSearchSSPRequest;

import java.util.List;

public interface ApplicationSearchSSPService
{
    public List<AppSearchSSPResponse> findApplications(AppSearchSSPRequest appSearchVO);
    public String deleteSSPApp(String appNum);

    public String findByAppNum(String appNum);
}
