package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.framework.web.response.ApiResponse;
import com.deloitte.nextgen.persistence.dto.vo.AppSearchResponseVO;
import com.deloitte.nextgen.persistence.dto.vo.AppSearchVO;
import com.deloitte.nextgen.services.ApplicationSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appsearch")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class AppSearchController {

    private final ApplicationSearchService appSearchService;

    RestTemplate restTemplate=new RestTemplate();

    @PostMapping("/findApps")
    public ResponseEntity<ApiResponse<List<AppSearchResponseVO>>> findApplications(@RequestBody AppSearchVO appSearchVO)  {
        log.debug("Input Values : appNum="+appSearchVO.getAppNum() + ",ssn="+appSearchVO.getApplicantSSN() +
                ",firstName="+appSearchVO.getApplicantFirstName()+",lastName="+appSearchVO.getApplicantLastName());
        List<AppSearchResponseVO> appSearchResponseVOS = appSearchService.findApplications(appSearchVO);
        List<AppSearchResponseVO> sspResponse=findAppsFromSSP(appSearchVO);
        appSearchResponseVOS.addAll(sspResponse);
        updateSourceCode(appSearchResponseVOS);
        return ApiResponse.success(100).data(appSearchResponseVOS);
    }

    public List<AppSearchResponseVO> updateSourceCode(List<AppSearchResponseVO>input)
    {
        List<AppSearchResponseVO> output=input;
        for(AppSearchResponseVO temp:output)
        {
            if(temp.getSource()!=null)
            {
                if(temp.getSource().equalsIgnoreCase("SS"))
                {
                    temp.setSource("Customer Portal");
                    continue;
                }
                if(temp.getSource().equalsIgnoreCase("DBS"))
                {
                    temp.setSource("Disaster SNAP");
                    continue;
                }
                if(temp.getSource().equalsIgnoreCase("DTF"))
                {
                    temp.setSource("Disaster TANF");
                    continue;
                }
                if(temp.getSource().equalsIgnoreCase("EXP"))
                {
                    temp.setSource("Ex Parte");
                    continue;
                }
                if(temp.getSource().equalsIgnoreCase("FFM"))
                {
                    temp.setSource("Federally Facilitated Marketplace");
                    continue;
                }
                if(temp.getSource().equalsIgnoreCase("INP"))
                {
                    temp.setSource("In Person");
                    continue;
                }
                if(temp.getSource().equalsIgnoreCase("LIS"))
                {
                    temp.setSource("Low Income Subsidy");
                    continue;
                }
                if(temp.getSource().equalsIgnoreCase("PAP"))
                {
                    temp.setSource("Paper");
                    continue;
                }
                if(temp.getSource().equalsIgnoreCase("TP"))
                {
                    temp.setSource("Phone-In");
                    continue;
                }
                if(temp.getSource().equalsIgnoreCase("SHI"))
                {
                    temp.setSource("Shines");
                    continue;
                }
            }
        }
        return output;
    }

    public List<AppSearchResponseVO> findAppsFromSSP(@RequestBody AppSearchVO appSearchVO) {
        List<AppSearchResponseVO>al=null;
        try {
            //AWS URL
            //String url = "http://15.207.142.199/ar-ssp-api/appsearch/findApps";
            //Local URL
            String url = "http://localhost:8085/ar-ssp-api/appsearch/findApps";
            AppSearchVO request=appSearchVO;
            ResponseEntity<AppSearchResponseVO[]> response=
                    //restTemplate.getForObject(url1,String.class);
                    restTemplate.postForEntity(url,request,AppSearchResponseVO[].class);
            System.out.println(response.getBody());
            al= Arrays.asList(response.getBody());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return al;
    }


    @GetMapping("/deleteApp/{appNum}")
    public ResponseEntity<ApiResponse<String>> deleteApp(@PathVariable String appNum){
        log.debug("App Number to be withdrawn = " + appNum );
        String status = appSearchService.deleteApp(appNum);
        if(status.equalsIgnoreCase("SUCCESS")){
            return ApiResponse.success(100).data(status);
        }
        return ApiResponse.error(300).data(status);
    }
}

