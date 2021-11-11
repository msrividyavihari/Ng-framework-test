package com.deloitte.nextgen.appreg.web.services.impl;

import com.deloitte.nextgen.appreg.web.services.ApplicationSearchService;
import com.deloitte.nextgen.appreg.web.exceptionhandlers.AppRegCustomException;
import com.deloitte.nextgen.appreg.client.enums.ApplicationStatus;
import com.deloitte.nextgen.appreg.client.response.AppSearchResponse;
import com.deloitte.nextgen.appreg.client.request.AppSearchRequest;
import com.deloitte.nextgen.appreg.web.entities.ArApplicationForAid;
import com.deloitte.nextgen.appreg.web.mappings.ArApplicationForAidMapping;
import com.deloitte.nextgen.appreg.web.repositories.ArApplicationForAidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationSearchServiceImpl implements ApplicationSearchService {

    private final ArApplicationForAidRepository arApplicationForAidRepository;
    private final ArApplicationForAidMapping arApplicationForAidMapping;

    @Override
    public List<AppSearchResponse> findApplications(AppSearchRequest appSearchRequest) {
        List<AppSearchResponse> appSearchResponses = new ArrayList<>();
        List<Object[]> appSearchDetails = new ArrayList<>();
        try
        {
            if(appSearchRequest !=null){
                appSearchDetails.addAll(arApplicationForAidRepository.findApplications(appSearchRequest));
            }

            if(!appSearchDetails.isEmpty()){
                appSearchResponses = appSearchDetails.stream().map(index->{
                    AppSearchResponse responseVO = new AppSearchResponse();
                    responseVO.setFirstName((String)index[0]);
                    responseVO.setLastName((String)index[1]);
                    responseVO.setAppRecvdDt((Date)index[2]);
                    responseVO.setSource((String)index[3]);
                    responseVO.setAppNum((String)index[4]);
                    responseVO.setAppStatCd((String)index[5]);
                    responseVO.setFlag("AR");
                    return responseVO;
                }).collect(Collectors.toList());
            }
        }
        catch (Exception e)
        {
            log.error("Error inside findApplications ", e);
            throw new AppRegCustomException(e.toString());
        }
        return appSearchResponses;
    }

    @Override
    public String deleteApp(String appNum) {
        String status = "FAILURE";
        try
        {
        ArApplicationForAid arApplicationForAid  = arApplicationForAidRepository.findByAppNum(appNum);
        arApplicationForAid.setApplicationStatusCd(ApplicationStatus.FILED_IN_ERROR);
        arApplicationForAid = arApplicationForAidRepository.save(arApplicationForAid);
        if(arApplicationForAid.getApplicationStatusCd() == ApplicationStatus.FILED_IN_ERROR){
            status = "SUCCESS";
        }
        }
        catch (Exception e)
        {
            log.error("Error while deleting the app ", e);
            throw new AppRegCustomException(e.toString());
        }
        return status;
    }
}
