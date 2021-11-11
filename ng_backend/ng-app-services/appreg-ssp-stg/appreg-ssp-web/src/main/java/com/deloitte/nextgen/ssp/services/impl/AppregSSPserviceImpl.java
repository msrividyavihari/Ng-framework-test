package com.deloitte.nextgen.ssp.services.impl;

import com.deloitte.nextgen.ssp.entities.entities.T1003_APP_PRIR_SRV;
import com.deloitte.nextgen.ssp.entities.entities.T1053_App_Program;
import com.deloitte.nextgen.ssp.entities.vo.SSPDataRequest;
import com.deloitte.nextgen.ssp.entities.T1004AppIndvDto;
import com.deloitte.nextgen.ssp.entities.T1053AppProgramDto;
import com.deloitte.nextgen.ssp.mappings.*;
import com.deloitte.nextgen.ssp.mappings.T1065AppAuthRepMapping;
import com.deloitte.nextgen.ssp.responses.SnapExpeditedResponse;
import com.deloitte.nextgen.ssp.repo.*;
import com.deloitte.nextgen.ssp.services.AppregSSPservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppregSSPserviceImpl implements AppregSSPservice
{
    @Autowired
    private AppRegSSPInterface repo;

    @Autowired
    private ExpeditedSnap exSnapRepo;

    @Autowired
    private AppDetailRepo appDetailRepo;

    @Autowired
    private AppRqustSSPInterface appRqustSSPInterface;

    @Autowired
    private AppIndvSSPInterface appIndvSSPInterface;

    @Autowired
    private AppRegSSPInterface appRegSSPInterface;

    @Autowired
    private AuthRepInterface authRep;

    private final T1002AppDtlMapping t1001AppDtlMapping;
    private final T1001AppRqstMapping t1002AppRqstMapping;
    private final T1004AppIndvMapping t1004AppIndvMapping;
    private final T1053AppProgramMapping t1053AppProgramMapping;
    private final T1065AppAuthRepMapping t1065AppAuthRepMapping;

    private static final String APPNUM = "AppNum";
    private static final String INDVID = "IndvId";
    private static final String APPINDV = "appIndv";
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static final String T1001APPDTL = "T1001_App_Dtl";
    private static final String T1002APPRQST = "T1002_AppRqst";
    private static final String T1053APPPROGRAM = "T1053_App_Program";
    private static final String T1065APPAUTHREP = "T1065_App_Auth_Rep";
    private static final String NOEXCEPTIONOCCURED = "No Exception Occurred";

    @Override
    public List<T1053_App_Program> findByAppNum(String appNum) {
        List<T1053_App_Program> appProgObj=repo.findByAppNum(appNum);
        return appProgObj;
    }

    @Override
    public String saveArProgram(T1053AppProgramDto ap) {
        return repo.save(t1053AppProgramMapping.toEntity(ap)).getAppNum();
    }

    @Override
    public List<T1053_App_Program> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Map<String, Map<String, Map<String, Object>>>> insertSSPData(List<SSPDataRequest> sspDataRequest){
        List<Map<String, Map<String, Map<String, Object>>>> outPutRes = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> exceptionMap = new HashMap<>();
        Map<String, Map<String, Object>> mainMap = new HashMap<>();
        Map<String, Map<String, Map<String, Object>>> outputMap = new HashMap<>();
        String appnumDtl =null;

        for(SSPDataRequest input : sspDataRequest) {

            try{
                    appnumDtl= appRegSSPInterface.getLatestSSAppNo();
                    input.getAppDtl().setAppNum(appnumDtl);
                    appnumDtl = appDetailRepo.save(t1001AppDtlMapping.toEntity(input.getAppDtl())).getAppNum();
                    response.put(T1001APPDTL, appnumDtl != null ? SUCCESS : FAILURE);
                    exceptionMap.put(T1001APPDTL, NOEXCEPTIONOCCURED);
                    log.info("T1001_App_Dtl received " + appnumDtl + APPNUM);


            }catch(Exception e){
                log.error("Error while inserting SSP data in T1001_App_Dtl ", e);
                response.put(T1001APPDTL, FAILURE);
                exceptionMap.put(T1001APPDTL, e.getMessage());
            }

            try{
                    input.getAppRqst().setAppNum(appnumDtl);
                    String appnumRqst = appRqustSSPInterface.save(t1002AppRqstMapping.toEntity(input.getAppRqst())).getAppNum();
                    response.put(T1002APPRQST,appnumRqst != null ? SUCCESS : FAILURE);
                    exceptionMap.put(T1002APPRQST, NOEXCEPTIONOCCURED);
                    log.info("T1002_AppRqst received " + appnumRqst + APPNUM);

            }catch(Exception e){
                log.error("Error while inserting SSP data in T1002_AppRqst ", e);
                response.put(T1002APPRQST, FAILURE);
                exceptionMap.put(T1002APPRQST, e.getMessage());
            }
            

                    Map<String, Object> indvData = new HashMap<>();
                    Map<String, Object> indvIdResponse = new HashMap<>();
                    List<T1004AppIndvDto> inputData = input.getAppIndv();
                    for(T1004AppIndvDto app_indv: inputData) {
                        try {
                            app_indv.setAppNum(appnumDtl);
                               indvIdResponse.put(INDVID + ": " + app_indv.getIndvSeqNum(), appIndvSSPInterface.save(t1004AppIndvMapping.toEntity(app_indv)).getIndvSeqNum() != null ?
                                       SUCCESS : FAILURE);
                                response.put(APPINDV, indvIdResponse);

                                indvData.put(INDVID + ": " + app_indv.getIndvSeqNum(), NOEXCEPTIONOCCURED);
                                exceptionMap.put(APPINDV, indvData);

                        }catch(Exception e){
                            indvIdResponse.put(INDVID + ": " + app_indv.getIndvSeqNum(), FAILURE);
                            response.put(APPINDV, indvIdResponse);
                            indvData.put(INDVID + ": " + app_indv.getIndvSeqNum(), e.getMessage());
                            exceptionMap.put(APPINDV, indvData);
                            log.error("Error while inserting SSP data in T1004_App_Indv ", e);
                            response.put("T1004_App_Indv", FAILURE);
                            exceptionMap.put("T1004_App_Indv", e.getMessage());
                        }
                    }


            try {
                input.getAppProgram().setAppNum(appnumDtl);
                    String appnumProgram = appRegSSPInterface.save(t1053AppProgramMapping.toEntity(input.getAppProgram())).getAppNum();
                    response.put(T1053APPPROGRAM, appnumProgram != null ? SUCCESS : FAILURE);
                    exceptionMap.put(T1053APPPROGRAM, NOEXCEPTIONOCCURED);

            }catch (Exception e){
                log.error("Error while inserting SSP data in T1053_App_Program ", e);
                response.put(T1053APPPROGRAM, FAILURE);
                exceptionMap.put(T1053APPPROGRAM, e.getMessage());
            }

            try {
                    input.getAuthRep().setAppNum(appnumDtl);
                    String appnumAuth = authRep.save(t1065AppAuthRepMapping.toEntity(input.getAuthRep())).getAppNum();
                    response.put(T1065APPAUTHREP, appnumAuth != null ? SUCCESS : FAILURE);
                    log.info("T1065_App_Auth_Rep received " + appnumAuth + APPNUM);
                    exceptionMap.put(T1065APPAUTHREP, NOEXCEPTIONOCCURED);

            }catch (Exception e){
                log.error("Error while inserting SSP data in T1065_App_Auth_Rep ", e);
                response.put(T1065APPAUTHREP, FAILURE);
                exceptionMap.put(T1065APPAUTHREP, e.getMessage());
            }
            
            mainMap.put("STATUS", response);
            mainMap.put("MESSAGE",exceptionMap);

            outputMap.put("APPNUM: " + appnumDtl, mainMap);
            response = new HashMap<>();
            exceptionMap = new HashMap<>();
            mainMap = new HashMap<>();

        }
        outPutRes.add(outputMap);
        return outPutRes;
    }

    @Override
    public SnapExpeditedResponse getSnapExpDetails(String appNum) {
        SnapExpeditedResponse snapExpeditedVO = new SnapExpeditedResponse();
        T1003_APP_PRIR_SRV obj = exSnapRepo.findByAppNum(appNum);
            if(null != obj) {
                snapExpeditedVO.setAppNum(appNum);
                snapExpeditedVO.setMigFarmWrkrSw(obj.getMigFarmWrkrSw());
                snapExpeditedVO.setMoGrIncmAmt(obj.getMoGrIncmAmt());
                snapExpeditedVO.setLqdAsetAmt(obj.getLqdAsetAmt());
                snapExpeditedVO.setMoRentMrtgAmt(obj.getMoRentMrtgAmt());
            }

        return snapExpeditedVO;
    }
}
