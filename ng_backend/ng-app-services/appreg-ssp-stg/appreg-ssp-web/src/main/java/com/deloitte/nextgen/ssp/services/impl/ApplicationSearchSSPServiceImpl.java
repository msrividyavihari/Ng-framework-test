package com.deloitte.nextgen.ssp.services.impl;

import com.deloitte.nextgen.ssp.exceptionhandlers.ARSSPStgCustomException;
import com.deloitte.nextgen.ssp.responses.AppSearchSSPResponse;
import com.deloitte.nextgen.ssp.request.AppSearchSSPRequest;
import com.deloitte.nextgen.ssp.repo.AppSearchSSPCustomRepository;
import com.deloitte.nextgen.ssp.services.ApplicationSearchSSPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationSearchSSPServiceImpl implements ApplicationSearchSSPService {

    @Autowired
    private AppSearchSSPCustomRepository repo;

    @Override
    public List<AppSearchSSPResponse> findApplications(AppSearchSSPRequest appSearchVO) {
        List<AppSearchSSPResponse> appSearchResponseVOs = new ArrayList<>();
        List<Object[]> appSearchDetails = new ArrayList<>();
        try
        {
            if(null !=appSearchVO){
                appSearchDetails.addAll(repo.findApplications(appSearchVO));
            }

            if(!appSearchDetails.isEmpty()){
                appSearchResponseVOs = appSearchDetails.stream().map(index->{
                    AppSearchSSPResponse responseVO = new AppSearchSSPResponse();
                    responseVO.setFirstName((String)index[0]);
                    responseVO.setLastName((String)index[1]);
                    responseVO.setAppRecvdDt((Date)index[2]);
                    responseVO.setSource((String)index[3]);
                    responseVO.setAppNum((String)index[4]);
                    responseVO.setAppStatCd("AP");
                    responseVO.setFlag("SSP");
                    return responseVO;
                }).collect(Collectors.toList());
            }
        }
        catch (Exception e)
        {
            log.error("Error occurred in findApplications method ", e);
            throw new ARSSPStgCustomException(e.toString());
        }
        return appSearchResponseVOs;
    }

    @Override
    public String deleteSSPApp(String appNum) {
        return null;
    }

    @Override
    public String findByAppNum(String appNum) {
        List<String> appSearchDetails = new ArrayList<>();
        String response=null;
        try
        {
            if(appNum==null){
                return null;
            } else {
                appSearchDetails=(repo.findByAppNum(appNum));
                for(String s: appSearchDetails)
                {
                    response=s;
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error occurred in findByAppNum method ", e);
            throw new ARSSPStgCustomException(e.toString());
        }
        return response;
    }
}
