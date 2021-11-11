package com.deloitte.nextgen.appreg.web.services.impl;


import com.deloitte.nextgen.appreg.client.request.*;
import com.deloitte.nextgen.appreg.client.response.*;
import com.deloitte.nextgen.appreg.web.entities.*;
import com.deloitte.nextgen.appreg.web.exceptionhandlers.AppRegCustomException;
import com.deloitte.nextgen.appreg.client.entities.ArExpScreenRespDto;
import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.enums.ApplicationStatus;
import com.deloitte.nextgen.appreg.web.mappings.*;
import com.deloitte.nextgen.appreg.web.repositories.*;
import com.deloitte.nextgen.appreg.web.response.utility.MockValidateAddress;
import com.deloitte.nextgen.appreg.web.services.ApplicationRegistrationService;
import com.deloitte.nextgen.appreg.web.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ApplicationRegistrationServiceImpl implements ApplicationRegistrationService {

    private final ArApplicationForAidRepository arApplicationForAidRepository;
    private final ArApplicationForAidMapping arApplicationForAidMapping;
    private final ArExpScreenRespMapping arExpScreenRespMapping;
    private final ArAppIndvRepository arAppIndvRepository;
    private final ArAppIndvMapping arAppIndvMapping;
    private final DcIndvRepository dcIndvRepository;
    private final DcAliasRepository dcAliasRepository;
    private final ArExpScreenRespRepository arExpScreenRespRepository;
    private final ArAppProgramMapping arAppProgramMapping;
    private final ArAppProgramRepository arAppProgramRepository;
    private final ArAppProgramIndvMapping arAppProgramIndvMapping;
    private final ArAppProgramIndvRepository arAppProgramIndvRepository;
    private final DcIndvCustomRepository dcIndvCustomRepository;
    private final RegisterApplicationCustomRepository registerApplicationCustomRepository;


    @Autowired
    private MockValidateAddress mockValidateAddress;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ArApplicationForAidReqAndResp associateCase(String appNum, Long caseNum) {
        ArApplicationForAidReqAndResp arApplicationForAidReqAndResp = new ArApplicationForAidReqAndResp();
        try {
            log.info("Inside ApplicationRegistrationServiceImpl associateCase method call");
            ArApplicationForAid arApplicationForAid = arApplicationForAidRepository.findByAppNum(appNum);
            arApplicationForAid.setCaseNum(caseNum);
            arApplicationForAid = arApplicationForAidRepository.save(arApplicationForAid);
            log.info("Object arApplicationForAid has been Saved into DataBase");
            log.info("DTO Conversion Started for arApplicationForAid");
            arApplicationForAidReqAndResp = arApplicationForAidMapping.toDto(arApplicationForAid);
            log.info("DTO Conversion Completed for arApplicationForAid");
        } catch (Exception e) {
            log.error("Error while associating to a case ",e);
            throw new AppRegCustomException(e.toString());
        }
        return arApplicationForAidReqAndResp;
    }

    @Override
    public List<AssociateCaseResponse> fetchAssociateCases(Long indvId, String appNum) {

        List<AssociateCaseResponse> associateCases = null;
        try {
            log.info("Inside ApplicationRegistrationServiceImpl fetchAssociateCases method call");
            List<Object[]> caseList = arApplicationForAidRepository.findAssociateCasesByIndvId(indvId, appNum);

            associateCases = caseList.stream().map(i -> {

                AssociateCaseResponse acVO = new AssociateCaseResponse();
                acVO.setLastUpdated((Date) i[0]);
                acVO.setHoh((String) i[1]);
                acVO.setPrograms((String) i[2]);
                acVO.setStatus((String) i[3]);
                acVO.setCaseNum(new BigDecimal(String.valueOf(i[4])).longValueExact());
                acVO.setOffice(String.valueOf(i[5]));
                return acVO;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error while Fetching the associate case ", e);
            throw new AppRegCustomException(e.toString());
        }
        return associateCases;
    }

    @Override
    public String validateAddress(String street1, String street2, String city, String state, String zipCode) {
        //TODO: Create a booleaan flag to enable real time AddressValidation Service call.
//        boolean isServiceCallRequired = true;
        String responseAddress = "";
        try {
//            if (isServiceCallRequired) {
                log.info("Inside ApplicationRegistrationServiceImpl validateAddress method call");
                responseAddress = mockValidateAddress.inputAddressRequest(street1, street2, city, state, zipCode);
//            } else {
//                responseAddress = mockValidateAddress.getValidAddressOne();
//            }

        } catch (Exception e) {
            log.error("Error inside validateAddress method ", e);
            throw new AppRegCustomException(e.toString());
        }

        return responseAddress;
    }

    @Override
    public ArApplicationForAidReqAndResp insertIntoArAppFrAid(ArApplicationForAidReqAndResp arApplicationForAidReqAndResp) {
        ArApplicationForAidReqAndResp arApplicationForAidReqAndResp1 = null;
        try {
            if (null != arApplicationForAidReqAndResp && null != arApplicationForAidReqAndResp.getAppNum()) {

                ArApplicationForAid arApplicationForAid1 = arApplicationForAidRepository.findByAppNum(arApplicationForAidReqAndResp.getAppNum());
                if(arApplicationForAid1!=null) {
                    arApplicationForAid1.setAppRecvdDt(arApplicationForAidReqAndResp.getAppRecvdDt());
                    arApplicationForAid1.setAppModeCd(arApplicationForAidReqAndResp.getAppModeCd());
                    if (null != arApplicationForAidReqAndResp.getAppSignedSw()) {
                        arApplicationForAid1.setAppSignedSw(arApplicationForAidReqAndResp.getAppSignedSw());
                    }
                    ArApplicationForAid arApplicationForAid1Final = arApplicationForAidRepository.save(arApplicationForAid1);

                    return arApplicationForAidMapping.toDto(arApplicationForAid1Final);
                }else {
                    ArApplicationForAid arApplicationForAid = arApplicationForAidMapping.toEntity(arApplicationForAidReqAndResp);

                    ArApplicationForAid arApplicationForAidFinal = arApplicationForAidRepository.save(arApplicationForAid);

                    arApplicationForAidReqAndResp1 = arApplicationForAidMapping.toDto(arApplicationForAidFinal);
                    return arApplicationForAidReqAndResp1;
                }
            }
            if (
//                    !"SS".equalsIgnoreCase(arApplicationForAidDto.getAppModeCd()) &&
                    null != arApplicationForAidReqAndResp && null == arApplicationForAidReqAndResp.getAppNum()
            ) {
                arApplicationForAidReqAndResp.setAppNum(generateAppNumber());

            }

            ArApplicationForAid arApplicationForAid = arApplicationForAidMapping.toEntity(arApplicationForAidReqAndResp);

            ArApplicationForAid arApplicationForAidFinal = arApplicationForAidRepository.save(arApplicationForAid);

            arApplicationForAidReqAndResp1 = arApplicationForAidMapping.toDto(arApplicationForAidFinal);

        } catch (Exception e) {
            log.error("Error while inserting into ArAppFrAid ", e);
            throw new AppRegCustomException(e.toString());
        }

        return arApplicationForAidReqAndResp1;
    }


    @Override

    public ArAppIndvReqAndResp insertArAppIndv(ArAppIndvReqAndResp arAppIndvReqAndResp) {
        ArAppIndvReqAndResp arAppIndvReqAndResp1 = null;
        try {
            ArAppIndv arAppIndv = arAppIndvMapping.toEntity(arAppIndvReqAndResp);

            ArAppIndv arAppIndvFinal = arAppIndvRepository.save(arAppIndv);

            arAppIndvReqAndResp1 = arAppIndvMapping.toDto(arAppIndvFinal);
        } catch (Exception e) {
            log.error("Error while inserting ArAppIndv ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arAppIndvReqAndResp1;

    }

    @Override
    public ArApplicationForAidReqAndResp findByAppNum(String appNum) {

        ArApplicationForAidReqAndResp arApplicationForAidReqAndResp = null;
        try {
            log.info("Inside ApplicationRegistrationServiceImpl findByAppNum method call");
            ArApplicationForAid arApplicationForAid = arApplicationForAidRepository.findByAppNum(appNum);
            log.info("arApplicationForAid Data Base Search is completed");
            arApplicationForAidReqAndResp = arApplicationForAidMapping.toDto(arApplicationForAid);
        } catch (Exception e) {
            log.error("Error while searching by appNum ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arApplicationForAidReqAndResp;
    }

    @Override
    public List<ApplicantsResponse> fetchApplicantsInfo(ApplicantsRequest applicantsRequestVO) {
        List<ApplicantsResponse> applicantsResponse = null;
        List<Object[]> applicantResp = null;
        try {
            log.info("Inside ApplicationRegistrationServiceImpl fetchApplicantsInfo method call");
            if (applicantsRequestVO.getApplicantType().equals("P")) {
                applicantsResponse = registerApplicationCustomRepository.fetchApplicantsInfoForPrimApp(applicantsRequestVO.getIndvId());
            } else if (applicantsRequestVO.getApplicantType().equals("A")) {
                applicantsResponse = registerApplicationCustomRepository.fetchApplicantsInfoForAddApp(applicantsRequestVO.getIndvId());
            }
           /* if(null != applicantResp) {
                applicantsResponse = applicantResp.stream().map(i -> {
                    ApplicantsResponse apVO = new ApplicantsResponse();
                    apVO.setIndvId(((BigDecimal) i[0]).longValue());
                    apVO.setPrimaryApplicantSw((String) i[1]);
                    apVO.setIncludeApplicantSw(Constants.YES_CAPITALIZED);
                    apVO.setFirstName((String) i[2]);
                    apVO.setMiddleName((String) i[3]);
                    apVO.setLastName((String) i[4]);
                    apVO.setSufxName((String) i[5]);
                    apVO.setGender((Character) i[6]);
                    apVO.setAge(((BigDecimal) i[7]).intValue());
                    apVO.setDob((Timestamp) i[8]);
                    apVO.setSsn(((BigDecimal) i[9]).longValue());
                    apVO.setRace((String) i[10]);
                    apVO.setEthnicity((String) i[11]);
                    if (null != i[12] || null != i[13] || null != i[14]
                            || null != i[15] || null != i[16]) {
                        apVO.setAliasSw(Active.YES);
                    } else {
                        apVO.setAliasSw(Active.NO);
                    }
                    apVO.setAliasFirstName((String) i[12]);
                    apVO.setAliasMiddleName((String) i[13]);
                    apVO.setAliasLastName((String) i[14]);
                    apVO.setAliasSuffix((String) i[15]);
                    apVO.setAliasGender((Character) i[16]);
                    if (null == i[17] && Constants.YES_CAPITALIZED.equalsIgnoreCase(apVO.getPrimaryApplicantSw())) {
                        apVO.setInterpreterSw(Active.NO);
                    } else {
                        apVO.setInterpreterSw((String) i[19] != null && ((String) i[19]).equalsIgnoreCase("Y") ? Active.YES : Active.NO);
                    }
                    apVO.setPrimaryLanguage((String) i[18]);
                    if (null == i[19] && Constants.YES_CAPITALIZED.equalsIgnoreCase(apVO.getPrimaryApplicantSw())) {
                        apVO.setAccommodationSw(Active.NO);
                    } else {
                        apVO.setAccommodationSw((String) i[19] != null && ((String) i[19]).equalsIgnoreCase("Y") ? Active.YES : Active.NO);
                    }
                    if (Constants.YES_CAPITALIZED.equalsIgnoreCase(apVO.getPrimaryApplicantSw())) {
                        apVO.setTypeAccommodation((String) i[20]);
                    }
                    if (null == i[21] && Constants.YES_CAPITALIZED.equalsIgnoreCase(apVO.getPrimaryApplicantSw())) {
                        apVO.setAuthRepresentativeSw(Active.NO);
                    } else {
                        apVO.setAuthRepresentativeSw((Character) i[21] != null && ((Character) i[21]).toString().equalsIgnoreCase("Y") ? Active.YES : Active.NO);
                    }
                    if (null == i[22] && Constants.YES_CAPITALIZED.equalsIgnoreCase(apVO.getPrimaryApplicantSw())) {
                        apVO.setEbtcardSw(Active.NO);
                    } else {
                        apVO.setEbtcardSw((String) i[22] != null && ((String) i[22]).equalsIgnoreCase("Y") ? Active.YES : Active.NO);
                    }
                    apVO.setRegistervoteSw((Character) i[23] != null && ((Character) i[23]).toString().equalsIgnoreCase("Y") ? Active.YES : Active.NO);

                    return apVO;
                }).collect(Collectors.toList());
            }*/
        } catch (Exception e) {
            log.error("Error while Fetching applicants info ", e);
            throw new AppRegCustomException(e.toString());
        }
        return applicantsResponse;
    }

    @Override
    public List<ApplicantsResponse> fetchApplInfoPanelDetails(String appNum) {
        List<ApplicantsResponse> applicantsResponse = null;
        try {
            log.info("Inside ApplicationRegistrationServiceImpl fetchApplInfoPanelDetails method call");
            applicantsResponse = registerApplicationCustomRepository.fetchApplInfoPanelDetails(appNum);
           } catch (Exception e) {
            log.error("Error while fetching from AppInfoPanelDetails ", e);
            throw new AppRegCustomException(e.toString());
        }

        return applicantsResponse;

    }

    @Override
    public List<ApplicantsResponse> insertUpdateApplicants(String appNum, List<ApplicantRequest> applicants) {
        List<ApplicantsResponse> updatedApplicants = new ArrayList<>();
        try {
            log.info("Inside ApplicationRegistrationServiceImpl insertUpdateApplicants method call");

            for (ApplicantRequest applicant : applicants) {
                if (null == applicant.getIndvId()) { //This is a new Applicant.
                    //sonar changes added
                    updatedApplicants.add(insertNewApplicant(applicant,appNum));
                } else if (null != applicant.getIndvId() && null != applicant.getIndvStatusSw() && 'T' == applicant.getIndvStatusSw()) {
                    //Check is state on the application has changed, an update it accordingly.
                   updatedApplicants.add(checkAndProcessAppStateChange(applicant,appNum));
                } else if (null != applicant.getIndvId() && null == applicant.getIndvStatusSw()) {
                    //We only can update individual status on application and if is head of household.
                    DcIndv individual = mapApplicantRequestAndUpdateDcIndv(applicant);
                    Long indvId = individual.getIndvId();

                    ArAppIndvReqAndResp savedArAppIndv = updateArAppIndv(appNum, applicant, indvId);
                    if (null != applicant.getIncludeApplicantSw() && !"".equals(applicant.getIncludeApplicantSw())
                            && !Constants.NO_CAPITALIZED.equals(applicant.getIncludeApplicantSw())) {
                        //If person is not included on application we don't return it as member of it.
                        updatedApplicants.add(personNotIncluded(applicant,savedArAppIndv,individual));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error inside insertUpdateApplicants ", e);
            throw new AppRegCustomException(e.toString());
        }

        return updatedApplicants;
    }

    private ArAppIndvReqAndResp updateArAppIndv(String appNum, ApplicantRequest applicant, Long indvId) {
        ArAppIndvReqAndResp arAppIndvReqAndResp = null;
        try {
            ArAppIndv arAppIndv = arAppIndvRepository.findByAppNumAndIndvId(appNum, indvId);
            if (null == arAppIndv) {
                arAppIndv = new ArAppIndv();
                arAppIndv.setIndvId(indvId);
                arAppIndv.setAppNum(appNum);
            }
            if (Constants.YES_CAPITALIZED.equalsIgnoreCase(applicant.getPrimaryApplicantSw())) {
                arAppIndv.setHeadOfHouseholdSw(Active.YES);
            } else {
                arAppIndv.setHeadOfHouseholdSw(Active.NO);
            }
            if (null != applicant.getIndvStatusSw()) {
                arAppIndv.setIndvStatusSw(applicant.getIndvStatusSw());
            }

            if (null == applicant.getIncludeApplicantSw() || "No".equalsIgnoreCase(applicant.getIncludeApplicantSw())) {
                arAppIndvRepository.delete(arAppIndv);
                arAppIndvReqAndResp = arAppIndvMapping.toDto(arAppIndv);
                List<ArAppProgramIndv> arAppProgramIndvs = arAppProgramIndvRepository.findByAppNumAndIndvId(appNum, indvId);
                if(null != arAppProgramIndvs && !arAppProgramIndvs.isEmpty()){
                    arAppProgramIndvRepository.deleteAll(arAppProgramIndvs);
                }

            } else {
                ArAppIndv arAppIndvFinal = arAppIndvRepository.save(arAppIndv);
                arAppIndvReqAndResp = arAppIndvMapping.toDto(arAppIndvFinal);

            }
        } catch (Exception e) {
            log.error("Error inside updateArAppIndv ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arAppIndvReqAndResp;
    }


    private ArAppIndvReqAndResp createNewArAppIndv(String appNum, ApplicantRequest applicant, DcIndv savedIndividual) {
        ArAppIndvReqAndResp arAppIndvReqAndResp = new ArAppIndvReqAndResp();
        arAppIndvReqAndResp.setIndvId(savedIndividual.getIndvId());
        arAppIndvReqAndResp.setAppNum(appNum);
        arAppIndvReqAndResp.setEmployeeSw('Y');
        if (Constants.YES_CAPITALIZED.equalsIgnoreCase(applicant.getPrimaryApplicantSw())) {
            arAppIndvReqAndResp.setHeadOfHouseholdSw(Active.YES);
        } else {
            arAppIndvReqAndResp.setHeadOfHouseholdSw(Active.NO);
        }
        if (null != applicant.getIndvStatusSw()) {
            arAppIndvReqAndResp.setIndvStatusSw(applicant.getIndvStatusSw());
        }
        return insertArAppIndv(arAppIndvReqAndResp);
    }

    private DcIndv mapApplicantRequestToNewDcIndv(ApplicantRequest applicant) {
        DcIndv individual = new DcIndv();
        individual.setFirstName(applicant.getFirstName());
        individual.setLastName(applicant.getLastName());
        individual.setMidName(applicant.getMiddleName());
        individual.setSufxName(applicant.getSufxName());
        individual.setGenderCd(applicant.getGender());
        individual.setDobDt(applicant.getDob());
        individual.setSsn(applicant.getSsn());
        individual.setRaceCd(applicant.getRace());
        individual.setEthnicityCd(applicant.getEthnicity());
        individual.setInterpreterSw(applicant.getInterpreterSw());
        individual.setPrimaryLang(applicant.getPrimaryLanguage());
        individual.setOtherLanguage(applicant.getSpecificPrimaryLanguage());
        individual.setFileClearanceSw('Y');

        if (Active.YES == applicant.getAccommodationSw()) {
            individual.setDisabilityAccom(applicant.getTypeAccommodation());
        }
        individual.setAuthRepSw(applicant.getAuthRepresentativeSw());
        individual.setEbtCardSw(applicant.getEbtcardSw());
        individual.setVoteRegWishSw(applicant.getRegistervoteSw());

        //Mandatory status indicators.
        individual.setInactiveInd('N');
        individual.setDeleteSw('N');
        return individual;
    }

    private DcIndv mapApplicantRequestAndUpdateDcIndv(ApplicantRequest applicant) {
        DcIndv individual = new DcIndv();
        if (null != applicant.getIndvId()) {
            individual = dcIndvRepository.findByIndvId(applicant.getIndvId());
        }

        individual.setIndvId(applicant.getIndvId());
        individual.setFirstName(applicant.getFirstName());
        individual.setLastName(applicant.getLastName());
        individual.setMidName(applicant.getMiddleName());
        individual.setSufxName(applicant.getSufxName());
        individual.setGenderCd(applicant.getGender());
        individual.setDobDt(applicant.getDob());
        individual.setSsn(applicant.getSsn());
        individual.setRaceCd(applicant.getRace());
        individual.setEthnicityCd(applicant.getEthnicity());
        individual.setInterpreterSw(applicant.getInterpreterSw());
        individual.setPrimaryLang(applicant.getPrimaryLanguage());
        individual.setOtherLanguage(applicant.getSpecificPrimaryLanguage());

        if (null != applicant.getAccommodationSw() && Active.YES == applicant.getAccommodationSw()) {
            individual.setDisabilityAccom(applicant.getTypeAccommodation());
        }
        individual.setAuthRepSw(applicant.getAuthRepresentativeSw());
        individual.setEbtCardSw(applicant.getEbtcardSw());
        individual.setVoteRegWishSw(applicant.getRegistervoteSw());

        //Mandatory status indicators.
        individual.setInactiveInd('N');
        individual.setDeleteSw('N');
        return individual;
    }

    private DcAlias mapApplicantRequestToNewDcAlias(ApplicantRequest applicant, Long indvId) {
        DcAlias alias = new DcAlias();
        if(applicant.getAliasIndSeqNum() != null){
            alias = dcAliasRepository.findByIndvIdAndAliasIndSeqNum(applicant.getIndvId(), applicant.getAliasIndSeqNum());
        }
        alias.setIndvId(indvId);
        alias.setFirstName(applicant.getAliasFirstName());
        alias.setLastName(applicant.getAliasLastName());
        alias.setGenderCd(applicant.getAliasGender() == null ? applicant.getGender() : applicant.getAliasGender());
        alias.setMidName(applicant.getAliasMiddleName());
        alias.setSufxName(applicant.getAliasSuffix());
        alias.setAliasIndSeqNum(applicant.getAliasIndSeqNum());
        return alias;
    }

    private ApplicantsResponse mapDcIndvtoApplicantResponse(DcIndv individual) {
        ApplicantsResponse responseVO = new ApplicantsResponse();
        responseVO.setIndvId(individual.getIndvId());
        responseVO.setIncludeApplicantSw(Constants.YES_CAPITALIZED);
        responseVO.setFirstName(individual.getFirstName());
        responseVO.setLastName(individual.getLastName());
        responseVO.setMiddleName(individual.getMidName());
        responseVO.setSufxName(individual.getSufxName());
        responseVO.setGender(individual.getGenderCd());
        responseVO.setDob(individual.getDobDt());
        LocalDate birthDate = individual.getDobDt().toLocalDateTime().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        responseVO.setAge(Period.between(birthDate, currentDate).getYears());
        responseVO.setSsn(individual.getSsn());
        responseVO.setRace(individual.getRaceCd());
        responseVO.setEthnicity(individual.getEthnicityCd());

        responseVO.setInterpreterSw(individual.getInterpreterSw());
        responseVO.setPrimaryLanguage(individual.getPrimaryLang());
        responseVO.setSpecificPrimaryLanguage(individual.getOtherLanguage());

        responseVO.setTypeAccommodation(individual.getDisabilityAccom());
        if (null != individual.getDisabilityAccom() && !"".equals(individual.getDisabilityAccom())) {
            responseVO.setAccommodationSw(Active.YES);
        } else {
            responseVO.setAccommodationSw(Active.NO);
        }

        responseVO.setAuthRepresentativeSw(individual.getAuthRepSw());
        responseVO.setEbtcardSw(individual.getEbtCardSw());
        responseVO.setRegistervoteSw(individual.getVoteRegWishSw());

        return responseVO;
    }

    private ApplicantsResponse attachNewArAppIndv(ArAppIndvReqAndResp savedArAppIndv, ApplicantsResponse applicantResponse) {
        if (Active.YES == savedArAppIndv.getHeadOfHouseholdSw()) {
            applicantResponse.setPrimaryApplicantSw(Constants.YES_CAPITALIZED);
        } else {
            applicantResponse.setPrimaryApplicantSw(Constants.NO_CAPITALIZED);
        }

        applicantResponse.setIndvStatusSw(savedArAppIndv.getIndvStatusSw());

        return applicantResponse;
    }

    private ApplicantsResponse attachNewDcAlias(DcAlias savedAlias, ApplicantsResponse applicantResponse) {
        applicantResponse.setAliasSw(Active.YES);

        if (null != savedAlias.getFirstName()) {
            applicantResponse.setAliasFirstName(savedAlias.getFirstName());
        }

        if (null != savedAlias.getMidName()) {
            applicantResponse.setAliasMiddleName(savedAlias.getMidName());
        }

        if (null != savedAlias.getLastName()) {
            applicantResponse.setAliasLastName(savedAlias.getLastName());
        }

        if (null != savedAlias.getSufxName()) {
            applicantResponse.setAliasSuffix(savedAlias.getSufxName());
        }

        if (null != savedAlias.getGenderCd()) {
            applicantResponse.setAliasGender(savedAlias.getGenderCd());
        }

        if(null != savedAlias.getAliasIndSeqNum()){
            applicantResponse.setAliasIndSeqNum(savedAlias.getAliasIndSeqNum());
        }
        return applicantResponse;
    }

    @Override
    public List<ArExpScreenRespDto> insertArExpResp(List<ArExpScreenRespDto> arExpScreenRespDtoList, String appNum) {

        List<ArExpScreenRespDto> arExpScreenRespDtoFinalList = new ArrayList<>();
        ArExpScreenResp arExpScreenResp = null;

        try {
            log.info("Inside ApplicationRegistrationServiceImpl insertArExpResp method call");
           for (ArExpScreenRespDto inp : arExpScreenRespDtoList) {
                arExpScreenResp = arExpScreenRespRepository.findByAppNumAndQuestCd(appNum, inp.getQuestCd());
                if (null == arExpScreenResp) {
                    arExpScreenResp = new ArExpScreenResp();
                }
                arExpScreenResp.setAppNum(appNum);
                arExpScreenResp.setQuestCd(inp.getQuestCd());
                arExpScreenResp.setResponse(inp.getResponse());
                arExpScreenRespDtoFinalList.add(arExpScreenRespMapping.toDto(insertIntoArExpScreenResp(arExpScreenResp)));
            }
        } catch (Exception e) {
            log.error("Error while inserting into ArExpResp ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arExpScreenRespDtoFinalList;
    }

    public ArExpScreenResp insertIntoArExpScreenResp(ArExpScreenResp arExpScreenResp) {
        ArExpScreenResp arExpScreenRespFinal = null;
        try {
             arExpScreenRespFinal = arExpScreenRespRepository.save(arExpScreenResp);
            log.info("Response For arExpScreenResp is Saved into Data Base");

        } catch (Exception e) {
            log.error("Error while inserting into ArExpScreenResp");
            throw new AppRegCustomException(e.toString());
        }
        return arExpScreenRespFinal;
    }

    @Override
    public List<ArExpScreenRespDto> fetchExpInfo(String appNUm) {
        List<ArExpScreenRespDto> arExpScreenRespDto = null;
        try {
            log.info("Inside ApplicationRegistrationServiceImpl fetchExpInfo method call");
            List<ArExpScreenResp> arExpScreenResp = arExpScreenRespRepository.findByAppNum(appNUm);
            if(arExpScreenResp.isEmpty()){
                AppIndvListRequest appIndvListRequest = new AppIndvListRequest();
                appIndvListRequest.setAppNum(appNUm);
                ResponseEntity<SnapExpeditedRequest> responseEntity=
                        restTemplate.postForEntity(Constants.fetchSSPSnapDetailsURL, appIndvListRequest,SnapExpeditedRequest.class);
                SnapExpeditedRequest snapExp=responseEntity.getBody();
                if(null != snapExp && null != snapExp.getAppNum()){
                    arExpScreenResp.addAll(populateArExpScreenResponse(snapExp,appNUm));

                }

            }
            arExpScreenRespDto = arExpScreenRespMapping.toDto(arExpScreenResp);
        } catch (Exception e) {
            log.error("Error while fetching ExpInfo ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arExpScreenRespDto;
    }

    @Override
    public ArApplicationForAidReqAndResp insertPriorityAppStatus(PriorityStatusRequest priorityStatusRequest) {
        ArApplicationForAidReqAndResp arApplicationForAidReqAndResp = null;
        try {
            log.info("Inside ApplicationRegistrationServiceImpl insertPriorityAppStatus method call");

            if (null != priorityStatusRequest.getAppNum()) {
                ArApplicationForAid arApplicationForAid = arApplicationForAidRepository.findByAppNum(priorityStatusRequest.getAppNum());

                List<String> priorityAppStatus = priorityStatusRequest.getPriorityAppStatus();
                if(null != priorityAppStatus) {
                    arApplicationForAid = processPriorityAppStatus(arApplicationForAid,priorityAppStatus);
                }
                arApplicationForAid.setAbdCheckSw(priorityStatusRequest.getAbdCheckSw());

                ArApplicationForAid arApplicationForAidFinal = arApplicationForAidRepository.save(arApplicationForAid);
                log.info("ArApplicationForAidFinal Entry into DataBase is completed");
                arApplicationForAidReqAndResp = arApplicationForAidMapping.toDto(arApplicationForAidFinal);
                return arApplicationForAidReqAndResp;

            }
        } catch (Exception e) {
            log.error("Error while inserting into priorityAppStatus ", e);
            throw new AppRegCustomException(e.toString());
        }
        return null;
    }

    private ArAppProgramResponse insertArAppProgramDB(ArAppProgramResponse arAppProgramResponse) {
        ArAppProgramResponse arAppProgramResponse1 = null;
        try {
            log.info("Inside ApplicationRegistrationServiceImpl insertArAppProgramDB method call");

            ArAppProgram arAppProgram = arAppProgramMapping.toEntity(arAppProgramResponse);

            ArAppProgram arAppProgramFinal = arAppProgramRepository.save(arAppProgram);

            arAppProgramResponse1 = arAppProgramMapping.toDto(arAppProgramFinal);
            log.info("arAppProgramDto is saved into data base");
        } catch (Exception e) {
            log.error("Error inside insertArAppProgramDB ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arAppProgramResponse1;

    }

    @Override
    public List<ArAppProgramResponse> insertArAppProgram(ArAppProgramRequest arAppProgramRequest) {

        ArAppProgramResponse arAppProgramResponse = null;
        List<ArAppProgramResponse> arAppProgramResponseList = new ArrayList<>();
        List<ArAppProgramResponse> arAppProgramExistingPrograms;
        boolean fsExists = false;
        boolean tfExists = false;
        boolean maExists = false;
        arAppProgramExistingPrograms = findArAppProgramByAppNum(arAppProgramRequest.getAppNum());

        for (int i = 0; i < arAppProgramExistingPrograms.size(); i++) {
            if(arAppProgramExistingPrograms.get(i) != null){
                if(arAppProgramExistingPrograms.get(i).getProgramCd().equalsIgnoreCase("FS")){
                    fsExists = true;
                }else if(arAppProgramExistingPrograms.get(i).getProgramCd().equalsIgnoreCase("TF")){
                    tfExists = true;
                }else if(arAppProgramExistingPrograms.get(i).getProgramCd().equalsIgnoreCase("MA")){
                    maExists = true;
                }
            }
        }
        List<ArAppProgramResponse> tempResponseList = new ArrayList<>();
        tempResponseList = insertArAppProgram(arAppProgramRequest,fsExists,tfExists,maExists);
        if(!tempResponseList.isEmpty()){
            arAppProgramResponseList.addAll(tempResponseList);
        }

        deleteUnwantedProgramCd(arAppProgramRequest, arAppProgramExistingPrograms);
        return arAppProgramResponseList;

    }


    public List<ArAppProgramResponse> findArAppProgramByAppNum(String appNum) {
        List<ArAppProgramResponse> arAppProgramResponseList = new ArrayList<>();
        try {
            log.info("Inside ApplicationRegistrationServiceImpl findArAppProgramByAppNum method call");
            List<ArAppProgram> arAppProgramList = arAppProgramRepository.findByAppNum(appNum);

            arAppProgramResponseList = arAppProgramMapping.toDto(arAppProgramList);
        } catch (Exception e) {
            log.error("Error inside findArAppProgramByAppNum ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arAppProgramResponseList;
    }

    private void deleteUnwantedProgramCd(ArAppProgramRequest arAppProgramRequest, List<ArAppProgramResponse> arAppProgramExistingPrograms) {
        deleteFsRecord(arAppProgramRequest,arAppProgramExistingPrograms);
        deleteTfRecord(arAppProgramRequest,arAppProgramExistingPrograms);
        deleteMaRecord(arAppProgramRequest,arAppProgramExistingPrograms);

    }

    public List<ArAppProgramResponse> fetchArAppProgramByAppNum(String appNum) {
        List<ArAppProgramResponse> arAppProgramResponseList = new ArrayList<>();
        try {
            log.info("Inside ApplicationRegistrationServiceImpl fetchArAppProgramByAppNum method call");

            List<ArAppProgram> arAppProgramList = arAppProgramRepository.findByAppNum(appNum);
            if(arAppProgramList.isEmpty()){
                AppIndvListRequest appIndvListRequest = new AppIndvListRequest();
                appIndvListRequest.setAppNum(appNum);
                ResponseEntity<AppProgramSSPRequest[]> responseEntity=
                        restTemplate.postForEntity(Constants.fetchSSPProgramURL, appIndvListRequest,AppProgramSSPRequest[].class);
                AppProgramSSPRequest[] progLst=responseEntity.getBody();

                Arrays.stream(progLst).forEach(i ->{
                    ArAppProgram  arAppProgram=new ArAppProgram();
                    arAppProgram.setProgramCd(i.getProgram_cd());
                    arAppProgramList.add(arAppProgram);
                });

            }
            arAppProgramResponseList = arAppProgramMapping.toDto(arAppProgramList);
        } catch (Exception e) {
            log.error("Error while fetchArAppProgramByAppNum ",e);
            throw new AppRegCustomException(e.toString());
        }
        return arAppProgramResponseList;
    }

    @Override
    public List<ArAppProgramIndvResponse> insertArAppProgramIndv(List<ArAppProgramIndvRequest> arAppProgramIndvRequest) {
        List<ArAppProgramIndvResponse> arAppProgramIndvResponseList = new ArrayList<>();
        List<ArAppProgramIndvResponse> arAppProgramIndvExistingList = new ArrayList<>();
        List<ArAppProgramIndvResponse> existingList = new ArrayList<>();
        List<ArAppProgramIndvResponse> inputList = new ArrayList<>();
        List<ArAppProgramIndvResponse> sameList = new ArrayList<>();
        List<ArAppProgramIndvResponse> removeList = new ArrayList<>();
        List<ArAppProgramIndvResponse> insertList = new ArrayList<>();
        try {
            arAppProgramIndvExistingList = findArAppProgramIndvByAppNum(arAppProgramIndvRequest.get(0).getAppNum());
            for (ArAppProgramIndvResponse existingElement : arAppProgramIndvExistingList) {
                existingList.add(existingElement);
            }

            for (int i = 0; i < arAppProgramIndvRequest.size(); i++) {
                ArAppProgramIndvResponse tempElement = null;
                List<String> indvProgramCodes = arAppProgramIndvRequest.get(i).getProgramCd();
                List<String> priorMedicaidCd = arAppProgramIndvRequest.get(i).getPriorMedicaidCd();
                int j = 0;
                int k = 0;
                while (j < indvProgramCodes.size()) {
                    insertList.addAll(populateArAppProgramIndv(arAppProgramIndvRequest.get(i),indvProgramCodes.get(j),priorMedicaidCd,k,tempElement));
                    j++;
                }
                indvProgramCodes.clear();
            }

            for (ArAppProgramIndvResponse insertElement : insertList) {
                inputList.add(insertElement);
            }

            for (ArAppProgramIndvResponse sameElement : inputList) {
                sameList.add(sameElement);
            }

            sameList.removeAll(existingList);


            for (ArAppProgramIndvResponse removeElement : existingList) {
                removeList.add(removeElement);
            }

            removeList.removeAll(inputList);

            for (ArAppProgramIndvResponse insertElement : sameList) {
                arAppProgramIndvResponseList.add(insertArAppProgramIndvDB(insertElement));
            }

            for (ArAppProgramIndvResponse deleteElement : removeList) {
                arAppProgramIndvRepository.delete(arAppProgramIndvMapping.toEntity(deleteElement));
            }

        } catch (Exception e) {
            log.error("Error while inserting ArAppProgramIndv ", e);
            throw new AppRegCustomException(e.toString());
        }

        return arAppProgramIndvResponseList;
    }

    public List<ArAppProgramIndvResponse> findArAppProgramIndvByAppNum(String appNum) {
        List<ArAppProgramIndvResponse> arAppProgramIndvResponseList = new ArrayList<>();
        try {
            List<ArAppProgramIndv> arAppProgramIndvList = arAppProgramIndvRepository.findByAppNum(appNum);

            arAppProgramIndvResponseList = arAppProgramIndvMapping.toDto(arAppProgramIndvList);
        } catch (Exception e) {
            log.error("Error inside findArAppProgramIndvByAppNum ", e);
            throw new AppRegCustomException(e.toString());
        }

        return arAppProgramIndvResponseList;
    }


    private ArAppProgramIndvResponse insertArAppProgramIndvDB(ArAppProgramIndvResponse arAppProgramIndvResponse) {
        ArAppProgramIndvResponse arAppProgramIndvResponse1 = null;
        try {
            ArAppProgramIndv arAppProgramIndv = arAppProgramIndvMapping.toEntity(arAppProgramIndvResponse);
            ArAppProgramIndv arAppProgramIndvFinal = arAppProgramIndvRepository.save(arAppProgramIndv);
            arAppProgramIndvResponse1 = arAppProgramIndvMapping.toDto(arAppProgramIndvFinal);
        } catch (Exception e) {
            log.error("Error inside insertArAppProgramIndvDB ", e);
            throw new AppRegCustomException(e.toString());
        }

        return arAppProgramIndvResponse1;
    }

    @Override
    public java.util.List<AppIndvListResponse> fetchAppIndv(AppIndvListRequest appIndvListRequest) {
        List<AppIndvListResponse> appIndvListResponse = new ArrayList<>();
        List<AppIndvListResponse> appIndvList = new ArrayList<>();
        try
        {
            if (appIndvListRequest == null) {
                return null;
            } else {
                //Get indv list of application number
                String appNum = appIndvListRequest.getAppNum();
                if (appNum != null && !appNum.isEmpty()) {
                    appIndvList.addAll(dcIndvCustomRepository.findAppIndvByAppNum(appNum));
                } else {
                    return null;
                }
            }
            if (!appIndvList.isEmpty()) {
                appIndvListResponse = appIndvList.stream().sorted(Comparator.comparing(AppIndvListResponse::getHeadOfHousehold).reversed()).collect(Collectors.toList());
            }
        }catch (Exception e)
        {
            log.error("Error while fetching AppIndv ", e);
            throw new AppRegCustomException(e.toString());
        }
        return appIndvListResponse;
    }

    @Override
    public Integer fetchProgressForApp(String appNum) {
        List<ArFetchProgressResponse> progressResp = registerApplicationCustomRepository.fetchProgressForApp(appNum);
        int progress = 10;
        if (null != progressResp.get(0).getApplicationStatusCd() && "AC".equals(progressResp.get(0).getApplicationStatusCd())) {
            progress = 100;
        } else if (null != progressResp.get(0).getProgram_cd()) {
            progress = 90;
        } else if (null != progressResp.get(0).getAddrLine()) {
            progress = 80;
        } else if (null != progressResp.get(0).getCaseNum()) {
            progress = 60;
        } else if (null != progressResp.get(0).getIndvId()) {
            progress = 40;
        }
        return progress;
    }

    @Override
    public Boolean submitApplication(String appNum) {
        try {
            ArApplicationForAid arApplicationForAid = arApplicationForAidRepository.findByAppNum(appNum);
            arApplicationForAid.setApplicationStatusCd(ApplicationStatus.APPLICATION_COMPLETE);
            arApplicationForAidRepository.save(arApplicationForAid);
        } catch (Exception e) {
            log.error("Error while submitting the Application ", e);
            throw new AppRegCustomException(e.toString());
        }
        return true;
    }

    private String generateAppNumber() {
        return  arApplicationForAidRepository.getLatestNonSSAppNo();
    }

    private List<ArAppProgramResponse> insertArAppProgram(ArAppProgramRequest arAppProgramRequest,Boolean fsExists, Boolean tfExists, Boolean maExists){
        ArAppProgramResponse arAppProgramResponse = null;
        List<ArAppProgramResponse> arAppProgramResponseList = new ArrayList<>();
        for (int i = 0; i < arAppProgramRequest.getProgramCd().size(); i++) {
            arAppProgramResponse = new ArAppProgramResponse();
            arAppProgramResponse.setPriorMedicaidCd("P0");
            arAppProgramResponse.setRequestDt(arAppProgramRequest.getRequestDt());
            arAppProgramResponse.setExpeditedSw(Active.NO);
            arAppProgramResponse.setProgStatusCd("PE");
            arAppProgramResponse.setAppNum(arAppProgramRequest.getAppNum());
            switch (arAppProgramRequest.getProgramCd().get(i)) {
                case "FS":
                    if (fsExists) {
                        break;
                    } else {
                        arAppProgramResponse.setProgramCd("FS");
                        arAppProgramResponseList.add(insertArAppProgramDB(arAppProgramResponse));
                        break;
                    }
                case "TF":
                    if (tfExists) {
                        break;
                    } else {
                        arAppProgramResponse.setProgramCd("TF");
                        arAppProgramResponseList.add(insertArAppProgramDB(arAppProgramResponse));
                        break;
                    }
                case "MA":
                    if (maExists) {
                        break;
                    } else {
                        arAppProgramResponse.setProgramCd("MA");
                        arAppProgramResponseList.add(insertArAppProgramDB(arAppProgramResponse));
                        break;
                    }
                default:
                    log.info("Inside insertArAppProgram (submethod) ");
            }
        }
        return arAppProgramResponseList;
    }

    private ApplicantsResponse insertNewApplicant(ApplicantRequest applicant,String appNum){
        DcIndv individual = mapApplicantRequestToNewDcIndv(applicant);

        DcIndv savedIndividual = dcIndvRepository.save(individual);
        log.info("Saved Entry into DataBase = " + savedIndividual);
        ArAppIndvReqAndResp savedArAppIndv = createNewArAppIndv(appNum, applicant, savedIndividual);

        DcAlias savedAlias = null;
        DcAlias alias = mapApplicantRequestToNewDcAlias(applicant, savedIndividual.getIndvId());
        if (Active.YES == applicant.getAliasSw()) {
            savedAlias = dcAliasRepository.save(alias);
        }else{
            dcAliasRepository.delete(alias);
        }

        ApplicantsResponse applicantResponse = mapDcIndvtoApplicantResponse(savedIndividual);
        if (null != savedArAppIndv) {
            applicantResponse = attachNewArAppIndv(savedArAppIndv, applicantResponse);
        }
        if (null != savedAlias) {
            applicantResponse = attachNewDcAlias(savedAlias, applicantResponse);
        } else {
            applicantResponse.setAliasSw(Active.NO);
        }
        return applicantResponse;
    }

    private ApplicantsResponse personNotIncluded(ApplicantRequest applicant,ArAppIndvReqAndResp savedArAppIndv,DcIndv individual){
        DcAlias alias = new DcAlias();
        if (Active.YES == applicant.getAliasSw()) {
            alias = dcAliasRepository.findByIndvId(applicant.getIndvId());
        }

        ApplicantsResponse applicantResponse = mapDcIndvtoApplicantResponse(individual);
        if (null != savedArAppIndv) {
            applicantResponse = attachNewArAppIndv(savedArAppIndv, applicantResponse);
        }
        if (null != alias) {
            applicantResponse = attachNewDcAlias(alias, applicantResponse);
        } else {
            applicantResponse.setAliasSw(Active.NO);
        }
        return applicantResponse;
    }

    private ApplicantsResponse includeApplicant(DcAlias savedAlias,DcIndv savedIndividual,ArAppIndvReqAndResp savedArAppIndv ){
        ApplicantsResponse applicantResponse = mapDcIndvtoApplicantResponse(savedIndividual);
        if (null != savedArAppIndv) {
            applicantResponse = attachNewArAppIndv(savedArAppIndv, applicantResponse);
        }
        if (null != savedAlias) {
            applicantResponse = attachNewDcAlias(savedAlias, applicantResponse);
        } else {
            applicantResponse.setAliasSw(Active.NO);
        }
        return applicantResponse;
    }

    private List<ArExpScreenResp> populateArExpScreenResponse(SnapExpeditedRequest snapExp,String appNUm){
        List<ArExpScreenResp> arExpScreenResp = new ArrayList<>();
        ArExpScreenResp arExpResp1 = new ArExpScreenResp();
        arExpResp1.setAppNum(appNUm);
        arExpResp1.setQuestCd("EG");
        arExpResp1.setResponse(snapExp.getMoGrIncmAmt() != null ? snapExp.getMoGrIncmAmt().toString() : "");
        arExpScreenResp.add(arExpResp1);
        arExpResp1 = new ArExpScreenResp();
        arExpResp1.setAppNum(appNUm);
        arExpResp1.setQuestCd("EH");
        arExpResp1.setResponse(snapExp.getLqdAsetAmt() !=null ?snapExp.getLqdAsetAmt().toString() :"");
        arExpScreenResp.add(arExpResp1);
        arExpResp1 = new ArExpScreenResp();
        arExpResp1.setAppNum(appNUm);
        arExpResp1.setQuestCd("ER");
        arExpResp1.setResponse(snapExp.getMoRentMrtgAmt() !=null ? snapExp.getMoRentMrtgAmt().toString() : "");
        arExpScreenResp.add(arExpResp1);
        arExpResp1 = new ArExpScreenResp();
        arExpResp1.setAppNum(appNUm);
        arExpResp1.setQuestCd("EM");
        arExpResp1.setResponse(snapExp.getMigFarmWrkrSw() !=null ? snapExp.getMigFarmWrkrSw().toString() : "");
        arExpScreenResp.add(arExpResp1);

        return arExpScreenResp;
    }

    private void deleteFsRecord(ArAppProgramRequest arAppProgramRequest, List<ArAppProgramResponse> arAppProgramExistingPrograms){
        boolean deleteFS = true;
        ArAppProgram fsObject = new ArAppProgram();

        for (int i = 0; i < arAppProgramExistingPrograms.size(); i++) {
            for (int j = 0; j < arAppProgramRequest.getProgramCd().size(); j++) {

                if (arAppProgramExistingPrograms.get(i) != null && arAppProgramExistingPrograms.get(i).getProgramCd().equalsIgnoreCase("FS")) {
                    fsObject = arAppProgramMapping.toEntity(arAppProgramExistingPrograms.get(i));
                    if (arAppProgramExistingPrograms.get(i).getProgramCd().equalsIgnoreCase(arAppProgramRequest.getProgramCd().get(j))) {
                        deleteFS = false;
                    }
                }

            }
        }

        if (deleteFS) {
            arAppProgramRepository.delete(fsObject);
        }
    }

    private void deleteTfRecord(ArAppProgramRequest arAppProgramRequest, List<ArAppProgramResponse> arAppProgramExistingPrograms){
        boolean deleteTF = true;
        ArAppProgram tfObject = new ArAppProgram();
        for (int i = 0; i < arAppProgramExistingPrograms.size(); i++) {
            for (int j = 0; j < arAppProgramRequest.getProgramCd().size(); j++) {

                if (arAppProgramExistingPrograms.get(i) != null && arAppProgramExistingPrograms.get(i).getProgramCd().equalsIgnoreCase("TF")) {
                    tfObject = arAppProgramMapping.toEntity(arAppProgramExistingPrograms.get(i));
                    if (arAppProgramExistingPrograms.get(i).getProgramCd().equalsIgnoreCase(arAppProgramRequest.getProgramCd().get(j))) {
                        deleteTF = false;
                    }
                }

            }
        }
        if (deleteTF) {
            arAppProgramRepository.delete(tfObject);
        }
    }

    private void deleteMaRecord(ArAppProgramRequest arAppProgramRequest, List<ArAppProgramResponse> arAppProgramExistingPrograms){
        boolean deleteMA = true;
        ArAppProgram maObject = new ArAppProgram();
        for (int i = 0; i < arAppProgramExistingPrograms.size(); i++) {
            for (int j = 0; j < arAppProgramRequest.getProgramCd().size(); j++) {

                if (arAppProgramExistingPrograms.get(i) != null && arAppProgramExistingPrograms.get(i).getProgramCd().equalsIgnoreCase("MA")) {
                    maObject = arAppProgramMapping.toEntity(arAppProgramExistingPrograms.get(i));
                    if (arAppProgramExistingPrograms.get(i).getProgramCd().equalsIgnoreCase(arAppProgramRequest.getProgramCd().get(j))) {
                        deleteMA = false;
                    }
                }

            }
        }
        if (deleteMA) {
            arAppProgramRepository.delete(maObject);
        }
    }

    private List<ArAppProgramIndvResponse> populateArAppProgramIndv(ArAppProgramIndvRequest arAppProgramIndvRequest,String indvProgramCodes,List<String> priorMedicaidCd, int k,ArAppProgramIndvResponse tempElement){
        List<ArAppProgramIndvResponse> insertList = new ArrayList<>();
        if (indvProgramCodes.contains("FS")) {
            tempElement = new ArAppProgramIndvResponse();
            tempElement.setAppNum(arAppProgramIndvRequest.getAppNum());
            tempElement.setIndvId(arAppProgramIndvRequest.getIndvId());
            tempElement.setProgramCd("FS");
            tempElement.setPriorMedicaidCd("P0");
            tempElement.setAidRequestSw(Active.YES);
            tempElement.setRequestDt(arAppProgramIndvRequest.getRequestDt());
            insertList.add(tempElement);
        }
        if (indvProgramCodes.contains("TF")) {
            tempElement = new ArAppProgramIndvResponse();
            tempElement.setAppNum(arAppProgramIndvRequest.getAppNum());
            tempElement.setIndvId(arAppProgramIndvRequest.getIndvId());
            tempElement.setProgramCd("TF");
            tempElement.setPriorMedicaidCd("P0");
            tempElement.setAidRequestSw(Active.YES);
            tempElement.setRequestDt(arAppProgramIndvRequest.getRequestDt());
            insertList.add(tempElement);
        }
        if (indvProgramCodes.contains("MA")) {
            tempElement = new ArAppProgramIndvResponse();
            tempElement.setAppNum(arAppProgramIndvRequest.getAppNum());
            tempElement.setIndvId(arAppProgramIndvRequest.getIndvId());
            tempElement.setProgramCd("MA");
            tempElement.setPriorMedicaidCd("P0");
            tempElement.setAidRequestSw(Active.YES);
            tempElement.setRequestDt(arAppProgramIndvRequest.getRequestDt());
            insertList.add(tempElement);


            while (k < priorMedicaidCd.size()) {
                if (priorMedicaidCd.get(k).contains("P1")) {
                    tempElement = new ArAppProgramIndvResponse();
                    tempElement.setAppNum(arAppProgramIndvRequest.getAppNum());
                    tempElement.setIndvId(arAppProgramIndvRequest.getIndvId());
                    tempElement.setProgramCd("MA");
                    tempElement.setPriorMedicaidCd("P1");
                    tempElement.setAidRequestSw(Active.YES);
                    tempElement.setRequestDt(arAppProgramIndvRequest.getRequestDt());
                    insertList.add(tempElement);
                }
                if (priorMedicaidCd.get(k).contains("P2")) {
                    tempElement = new ArAppProgramIndvResponse();
                    tempElement.setAppNum(arAppProgramIndvRequest.getAppNum());
                    tempElement.setIndvId(arAppProgramIndvRequest.getIndvId());
                    tempElement.setProgramCd("MA");
                    tempElement.setPriorMedicaidCd("P2");
                    tempElement.setAidRequestSw(Active.YES);
                    tempElement.setRequestDt(arAppProgramIndvRequest.getRequestDt());
                    insertList.add(tempElement);
                }
                if (priorMedicaidCd.get(k).contains("P3")) {
                    tempElement = new ArAppProgramIndvResponse();
                    tempElement.setAppNum(arAppProgramIndvRequest.getAppNum());
                    tempElement.setIndvId(arAppProgramIndvRequest.getIndvId());
                    tempElement.setProgramCd("MA");
                    tempElement.setPriorMedicaidCd("P3");
                    tempElement.setAidRequestSw(Active.YES);
                    tempElement.setRequestDt(arAppProgramIndvRequest.getRequestDt());
                    insertList.add(tempElement);
                }
                k++;
            }
            priorMedicaidCd.clear();
        }
        return insertList;
    }

    private ArApplicationForAid processPriorityAppStatus(ArApplicationForAid arApplicationForAid, List<String> priorityAppStatus){
        arApplicationForAid.setQTrackSw(priorityAppStatus.contains("QTrack") ? Active.YES : Active.NO);
        arApplicationForAid.setPregnancySw(priorityAppStatus.contains("Pregnancy") ? Active.YES : Active.NO);
        arApplicationForAid.setRevMaxSw(priorityAppStatus.contains("RevMax") ? Active.YES : Active.NO);
        arApplicationForAid.setSrSnapSw(priorityAppStatus.contains("SrSNAP") ? Active.YES : Active.NO);
        arApplicationForAid.setWaiverSw(priorityAppStatus.contains("Waiver") ? Active.YES : Active.NO);
        arApplicationForAid.setNursingHomeSw(priorityAppStatus.contains("NursingHome") ? Active.YES : Active.NO);
        arApplicationForAid.setRefugeeSw(priorityAppStatus.contains("Refugee") ? Active.YES : Active.NO);

        return arApplicationForAid;
    }

    private ApplicantsResponse checkAndProcessAppStateChange(ApplicantRequest applicant,String appNum){
        ApplicantsResponse applicantsResponse = new ApplicantsResponse();
        DcIndv individual = mapApplicantRequestAndUpdateDcIndv(applicant);

        DcIndv savedIndividual = dcIndvRepository.save(individual);
        Long indvId = savedIndividual.getIndvId();
        ArAppIndvReqAndResp savedArAppIndv = updateArAppIndv(appNum, applicant, indvId);

        if (null != applicant.getIncludeApplicantSw() && !"".equals(applicant.getIncludeApplicantSw())
                && !Constants.NO_CAPITALIZED.equals(applicant.getIncludeApplicantSw())) {
            //If person is not included on application we don't return it as member of it.
            DcAlias savedAlias = null;
            DcAlias alias = mapApplicantRequestToNewDcAlias(applicant, savedIndividual.getIndvId());
            if (Active.YES == applicant.getAliasSw()) {
                savedAlias = dcAliasRepository.save(alias);
            }else {
                dcAliasRepository.delete(alias);
            }

            if (null != applicant.getIncludeApplicantSw() || Constants.YES_CAPITALIZED.equalsIgnoreCase(applicant.getIncludeApplicantSw())) {


                applicantsResponse=includeApplicant(savedAlias,savedIndividual,savedArAppIndv);
            }
        }

        return applicantsResponse;
    }

}
