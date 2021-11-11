package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.entities.*;
import com.deloitte.nextgen.framework.commons.exceptions.ApplicationException;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.repository.*;
import com.deloitte.nextgen.util.AValidationDocs;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.serial.SerialBlob;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
@Slf4j
public class CoDAOServices {

    @Autowired
    CoMasterRepository masterRepository;
    @Autowired
    CoRequestRecipientsRepository requestRecipientsRepository;
    @Autowired
    CoRequestHistoryRepository requestHistoryRepository;
    @Autowired
    CoRequestHistoryDetailRepository historyDetailRepository;
    @Autowired
    CorrespondenceServices correspondenceServices;
    @Autowired
    CoRecipientsManager coRecipientsManager;
    @Autowired
    DcCasesRepository dcCasesRepository;
    @Autowired
    DcIndvRepository dcIndvRepository;
    @Autowired
    DcHeadOfHouseholdRepository dcHeadOfHouseholdRepository;
    @Autowired
    MoEmployeesCasesRepository moEmployeesCasesRepository;
    @Autowired
    MoEmployeesRepository moEmployeesRepository;
    @Autowired
    ArAppIndvRepository arAppIndvRepository;
    @Autowired
    DcCaseAddressesRepository dcCaseAddressesRepository;
    @Autowired
    ArApplicationForAidRepository arApplicationForAidRepository;
    @Autowired
    ArAppProgramIndvRepository arAppProgramIndvRepository;
    @Autowired
    MoOfficesRepository moOfficesRepository;
    @Autowired
    MoOfficeAddressesRepository moOfficeAddressesRepository;
    @Autowired
    CoManualFieldsRepository coManualFieldsRepository;
    @Autowired
    CoManualDataRepository coManualDataRepository;
    @Autowired
    FwDataElementListRepository fwDataElementListRepository;
    @Autowired
    T1004AppIndvRepository t1004AppIndvRepository;
    @Autowired
    VArApplicationIndvRepository vArApplicationIndvRepository;
    @Autowired
    DcCaseProgramIndvRepository dcCaseProgramIndvRepository;
    @Autowired
    CoMassMailingRepository coMassMailingRepository;
    @Autowired
    VDcCaseIndvDetailsRepository vDcCaseIndvDetailsRepository;

    private static final String ED = "ED";
    private boolean isLocalCentralPrintedFgg551 = false;
    private Long edFirstCoReqSeq = 0L;

    public List<CoMaster> getCoMasterData(String uDocId) throws FwApplicationException {
        String curDt = CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate());
        return masterRepository.findByDocId(uDocId,curDt);
    }

    public int insertOriginalRequest(final COCorrespondence coCorrespondence) {
        int commitFlag = 0;
        List<Object> indvListFinal = new ArrayList<>();
        //List<DcAbdNursingHomeDtls> dcAbdNursingHomeDtlsCargoList = new ArrayList<>();
        int index = 0;
        boolean insert = true;
        COCorrespondence coRequest = coCorrespondence;
        try {
            correspondenceServices.checkDocumentValidity(coCorrespondence.getDocId());
            final Timestamp tempPrintDate = getGenerateDate(coCorrespondence);
            populateLangGoGreenInfo(coCorrespondence);

            if(CoConstants.PRINT_MODE_BATCH == coCorrespondence.getPrintMode()) {
                if (CoConstants.NGGA0034.equalsIgnoreCase(coCorrespondence.getDocId())) {
                    suppressPrevTrig(coCorrespondence, tempPrintDate);
                } else if (CoConstants.FGG5460.equalsIgnoreCase(coCorrespondence.getDocId())) {
                    indvListFinal = getIndvIds(coCorrespondence,tempPrintDate);
                    if(indvListFinal.isEmpty()) {
                        insert = false;
                    }
                } else if (CoConstants.NGGA0048.equalsIgnoreCase(coCorrespondence.getDocId())
                        || CoConstants.NGGA0049.equalsIgnoreCase(coCorrespondence.getDocId())) {
                    //dcAbdNursingHomeDtlsCargoList = getNursingHomeDtls(coRequest);
                    // insert = insertNtcRequestUpdateMethod(coRequest, tempPrintDate,dcAbdNursingHomeDtlsCargoList);
                }else if(CoConstants.NGGA0049B.equalsIgnoreCase(coCorrespondence.getDocId())){
                    insert = checkFor49BExistsSameDay(coCorrespondence, tempPrintDate);
                }else if(CoConstants.FGG191.equalsIgnoreCase(coCorrespondence.getDocId())) {
                   // insert = check191ExistsExtendedTFHardship(coRequest, tempPrintDate);
                }else if(CoConstants.NGGA0021.equalsIgnoreCase(coCorrespondence.getDocId())) {
                    final String miscParams = coCorrespondence.getMiscParameters();
                    Map misParamsMap = null;
                    String certificateNum = null;
                    //EdIndvProviderDetailCargo[] indvProviders = null;
                    if (miscParams != null) {
                        List<Object> misc = getDataFromMiscParameters(miscParams);
                        misParamsMap = (Map) misc.get(0);
                    }
                    if ((misParamsMap != null)
                            && misParamsMap.containsKey(CoConstants.CERTIFICATE_NUM)) {
                        certificateNum = (String) misParamsMap.get(CoConstants.CERTIFICATE_NUM);
                    }
                    if (certificateNum != null) {
                        long caseNum = !"".equals(coCorrespondence.getCaseAppNumber()) ? Long
                                .parseLong(coCorrespondence.getCaseAppNumber()) : 0;
                        /*indvProviders = getCCCertificateDetails(Long.parseLong(certificateNum),
                                caseNum);*/
                    }
                    /*if(indvProviders!=null && indvProviders.length>0) {
                        coRequest.setCcProviderId(indvProviders[0].getProviderId());
                        coRequest.setCcCertStartDate(indvProviders[0].getStartDate());
                        coRequest.setCcCertEndDate(indvProviders[0].getEndDate());
                    }*/
                }
            }

            if (insert) {
                //skipping fgg551  insert if fgg551 only local/central printed
                // insertFGG551Trigger(coRequest, tempPrintDate);
                isLocalCentralPrintedFgg551 = CoConstants.ATTACHMENT_DOC_ID_FGG551.equalsIgnoreCase(coCorrespondence.getDocId());
                do {
                    if (!indvListFinal.isEmpty()) {
                        /*if(indvListFinal.get(index) instanceof EdEligibility) {
                            EdEligibility cargo = (EdEligibility) indvListFinal.get(index);
                            coRequest.setIndvId(cargo.getMedicaidIndvId());
                            coRequest.setAssistanceProgramCode(cargo.getProgramCd());
                        }else */
                        if(indvListFinal.get(index) instanceof ArAppProgramIndv){
                            ArAppProgramIndv cargo = (ArAppProgramIndv) indvListFinal.get(index);
                            coRequest.setIndvId(cargo.getIndvId() );
                            coRequest.setAssistanceProgramCode(cargo.getProgramCd());
                        }/*else if(indvListFinal.get(index) instanceof DcCaseProgramIndv){
                            DcCaseProgramIndv cargo = (DcCaseProgramIndv) indvListFinal.get(index);
                            coRequest.setIndvId(cargo.getIndvId() );
                            coRequest.setAssistanceProgramCode(cargo.getProgCd());
                        }*/
                        coRequest.setMiscParameters(getMissParams(coRequest));
                        index++;
                    }

                    final String langCd = coCorrespondence.getLangCd();
                    coCorrespondence.setLangCd(CoConstants.LANG_ENGLISH);
                    coCorrespondence.setGenerateDate(tempPrintDate);
                    commitFlag = insertTriggerDetails(coRequest, tempPrintDate);
                    coCorrespondence.getCoReqSeqLangCdMap().clear();
                    if (coCorrespondence.getLogId() == 0) {
                        coCorrespondence.setLogId(coCorrespondence.getCoReqSeq());
                    }
                    coCorrespondence.getCoReqSeqLangCdMap().put(
                            coCorrespondence.getCoReqSeq(), CoConstants.LANG_ENGLISH);

                    if (CoConstants.CONST_SPANISH_LANG_CD
                            .equalsIgnoreCase(langCd) && (commitFlag > 0)) {
                        coCorrespondence.setLangCd(CoConstants.CONST_SPANISH_LANG_CD);
                        commitFlag = insertTriggerDetails(coRequest, tempPrintDate);
                        coCorrespondence.getCoReqSeqLangCdMap().put(
                                coCorrespondence.getCoReqSeq(),
                                CoConstants.CONST_SPANISH_LANG_CD);
                    }
                    /*if(!dcAbdNursingHomeDtlsCargoList.isEmpty()){
                        for(DcAbdNursingHomeDtlsCargo dcAbdNursingHomeDtlsCargo:dcAbdNursingHomeDtlsCargoList){
                            insertTriggerToNursingHome(coRequest,dcAbdNursingHomeDtlsCargo,tempPrintDate);
                        }
                    }*/
                    updateCoRequestHistoryLogIds(coRequest.getLogId(), coRequest.getCoReqSeqLangCdMap());
                } while (index < indvListFinal.size());
            }
            edFirstCoReqSeq = coCorrespondence.getLogId();

        } catch (final Exception e) {
            log.debug(e.getMessage(), e);
            commitFlag = -1;
        }
        return commitFlag;
    }

    public void transferRequestedOffice(COCorrespondence aCoObj) {
    }

    public void transferRequestedIndividual(COCorrespondence aCoObj) {
    }

    public void transferRequestedEmployee(COCorrespondence aCoObj) {
    }

    public int cancel3797PendingTrigger(COCorrespondence aCoObj) {
        return 0;
    }

    public int cancelRequestedTrigger(COCorrespondence aCoObj) {
        return 0;
    }

    private String getMissParams(final COCorrespondence coRequest) {
        final StringBuilder missParams = new StringBuilder();
        if(StringUtils.isNotBlank(coRequest.getActionCd())) {
            missParams.append(CoConstants.ACTION_CD);
            missParams.append(CoConstants.EQUAL);
            missParams.append(coRequest.getActionCd());
        }
        return missParams.toString();
    }

    public int updateCoReqRecipientFileName(final Long coReqSeq,final String fileName) throws Exception {
        int success = -1;
        List<CoRequestRecipients> result=getCoRequestAllRecipients(coReqSeq);
        final CoRequestRecipients coRequestRecipientsCargo = result.get(0);
        coRequestRecipientsCargo.setLocationPath(fileName);
        try {
            requestRecipientsRepository.save(coRequestRecipientsCargo);
            success = 1;
        } catch (final Exception e) {
            throw new Exception(" Error updating CO_REQUEST_RECIPIENTS table for File Name "
                            + e.getMessage(), e);
        }
        return success;
    }

    public List<CoRequestRecipients> getCoRequestAllRecipients(final Long coReqSeq) throws Exception {
        List<CoRequestRecipients> result;
        final CoRequestRecipients coRequestRecipientsCargo = new CoRequestRecipients();
        coRequestRecipientsCargo.setCoReqSeq(coReqSeq);
        try {
            result = requestRecipientsRepository.findByCoReqSeqOrderByCoRptSeq(coReqSeq);
        } catch (final Exception e) {
            throw new Exception(
                    "Select failed for CO_REQUEST_RECIPIENTS for coReqSeq: "
                            + coReqSeq, e);
        }
        return result;
    }

    public List<Object> getDataFromMiscParameters(final String misc)
            throws Exception {

        final List<Object> finalList = new ArrayList<>();
        String[] strArr;
        String[] strTempArr;
        ArrayList<String> ar = new ArrayList<>();

        if (misc != null) {
            // for more than one field separated by "|" in misc params
            if (misc.contains("|")) {
                final Map<String, Object> map = new HashMap<>();

                strArr = misc.split("\\|");
                for(String str:strArr){
                    if(!str.isEmpty()){
                        ar.add(str);
                    }
                }
                for (String string :ar ) {
                    if (string.contains("=")) {
                        strTempArr = string.split("=");
                        map.put(strTempArr[0], strTempArr[1]);
                    } else {
                        throw new Exception("No data in Misc");
                    }
                }
                finalList.add(map);
            } // if ends

            // for a single value in misc params
            if (!misc.contains("|")) {
                final Map<String, Object> map = new HashMap<>();

                if (misc.contains("=")) {
                    strTempArr = misc.split("=");
                    map.put(strTempArr[0], strTempArr[1]);

                } else {
                    throw new Exception("No data in Misc");
                }
                finalList.add(map);
            }
        }
        return finalList;
    }

    private Timestamp getGenerateDate(COCorrespondence coRequest) {
        Timestamp tempPrintDate = null;

        if(CoConstants.INTENT_TO_INTERCEPT_DOC_ID.equals(coRequest.getDocId())){
            tempPrintDate = coRequest.getGenerateDate();
        }else if (StringUtils.isNotEmpty(coRequest.getRequestUserId()) &&  !edPatternCheck(coRequest.getRequestUserId())) {
            /*final BatchJobExecutionParamsCargo batchJobExecutionParamsCargo = new BatchJobExecutionParamsCargo();
            String sqlQuery = CoConstants.EMPTY_STRING;

            sqlQuery = "findAsOfDtByJobName";
            batchJobExecutionParamsCargo.setCreateUserId(coRequest
                    .getRequestUserId());

            try {
                Object[] result = CoDAOFactory.select(
                        GOV_STATE_NEXTGEN_FRAMEWORK_COLLECTION_CUSTOM,
                        BATCH_JOB_EXECUTION_PARAMS, sqlQuery,
                        batchJobExecutionParamsCargo, null, conn);
                BatchJobExecutionParamsCargo[] batchJobExecutionParamsCargoArray = (BatchJobExecutionParamsCargo[]) result;
                if (null != batchJobExecutionParamsCargoArray
                        && batchJobExecutionParamsCargoArray.length > 0) {
                    tempPrintDate = getAsOfDateFromJobProps(batchJobExecutionParamsCargoArray[0].getStringVal());
                }
            } catch (final Exception e) {
                CoDebugger.debugException(e.getMessage(), e);
            }*/
        } else if (StringUtils.isNotEmpty(coRequest.getRequestUserId())
                && edPatternCheck(coRequest.getRequestUserId())) {
           // tempPrintDate = setEdDiDate();
        }

        if( null == tempPrintDate ){
            tempPrintDate = new Timestamp(CoUtil.getCurrentDate().getTime());//defaultGenDate();
        }
        return tempPrintDate;
    }

    private boolean edPatternCheck(String userID) {
        boolean isEdJobIdMatch = false;
        if (null != userID && userID.toUpperCase().startsWith(ED)
                && userID.length() > 7
                && StringUtils.isNumeric(userID.substring(userID.length() - 6))) {
            isEdJobIdMatch = true;
        }
        return isEdJobIdMatch;
    }

    public long populateLangGoGreenInfo(final COCorrespondence coRequest) throws FwApplicationException {
        String source = StringUtils.EMPTY;
        String lang = StringUtils.EMPTY;
        String goGreen = StringUtils.EMPTY;
        long hohIndvId=0;
        long officeNum = 0;
        List<DcIndv> hohIndvCargo = null;
        String curDt = CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate());
        if (coRequest.getCaseAppFlag() == CoConstants.CHAR_C) {
            final List<DcCases> cases = dcCasesRepository.findByCaseNum(Long.parseLong(coRequest.getCaseAppNumber()));
            if (!CoUtil.isEmpty(cases)) {
                goGreen = StringUtils.isNotEmpty(cases.get(0).getPrefCntcMthdCd())
                        ? cases.get(0).getPrefCntcMthdCd() : CoConstants.MAIL;
                officeNum = cases.get(0).getOfficeNum();
                source = cases.get(0).getAppModeCd();
            }
            List<DcHeadOfHousehold> dcHeadOfHousehold =dcHeadOfHouseholdRepository.findIndvByCaseNumHohSwNotices(Long.parseLong(coRequest.getCaseAppNumber()), curDt);
           if(!CoUtil.isEmpty(dcHeadOfHousehold)){
               hohIndvCargo = dcIndvRepository.findByIndvId(dcHeadOfHousehold.get(0).getIndvId());
           }
        } else {
            List<ArApplicationForAid> arApplicationForAidCargos =arApplicationForAidRepository.findByAppNum(coRequest.getCaseAppNumber());
            if (!CoUtil.isEmpty(arApplicationForAidCargos)) {
                officeNum = arApplicationForAidCargos.get(0).getOfficeNum();
                source = arApplicationForAidCargos.get(0).getAppModeCd();
            }
            hohIndvCargo=dcIndvRepository.findIndvByAppNumHohSw(coRequest.getCaseAppNumber());
            goGreen = CoConstants.MAIL;
        }
        if (!CoUtil.isEmpty(hohIndvCargo)) {
            coRequest.setHohIndvCargo(hohIndvCargo.get(0));
            hohIndvId = hohIndvCargo.get(0).getIndvId();
            if (coRequest.getIndvId() == null || coRequest.getIndvId() == 0) {
                coRequest.setIndvId(hohIndvId);
            }
            if (StringUtils.isNotEmpty(hohIndvCargo.get(0).getPrimaryLang())) {
                lang = hohIndvCargo.get(0).getPrimaryLang();
            } else if (StringUtils.isNotEmpty(hohIndvCargo.get(0).getOtherLanguage())) {
                lang = hohIndvCargo.get(0).getOtherLanguage();
            }
        }

        if (AValidationDocs.mailOnlyNoticeList.contains(coRequest.getDocId())) {
            goGreen = CoConstants.MAIL;
        }

        if(CoConstants.SHINES_SOURE_CD.equalsIgnoreCase(source) && !CoConstants.PROGRAM_CC.equalsIgnoreCase(coRequest.getAssistanceProgramCode())){
            goGreen = CoConstants.NO_MAIL_EMAIL;
        }

        if(CoConstants.SHINES_SOURE_CD.equalsIgnoreCase(source) && CoConstants.PROGRAM_CC.equalsIgnoreCase(coRequest.getAssistanceProgramCode())
                && !coRequest.getDocId().equalsIgnoreCase(CoConstants.FGG60)){
            goGreen = CoConstants.MAIL;
        }

        if (AValidationDocs.noSpanishFormList.contains(coRequest.getDocId())) {
            coRequest.setLangCd(CoConstants.LANG_ENGLISH);
        }else{
            coRequest.setLangCd(getLanguageCd(lang));
        }
        coRequest.setGoGreen(goGreen);
        if (checkNotAppointment(coRequest)){
            coRequest.setOfficeNumber(officeNum);
        }
        return hohIndvId;
    }

    private String getLanguageCd(final String lang) {
        String legacyCd = CoConstants.EMPTY_STRING;
        String languageCd = CoConstants.LANG_ENGLISH;
        //TODO: have to uncomment the below line
        /*try {
            legacyCd = tableManager.getValueByColumn("LANGUAGE", lang, "DESCRIPTION");
        } catch (final Exception nde) {
            log.error("No data found", nde);
        }*/
        if ("Spanish".equalsIgnoreCase(legacyCd)) {
            languageCd = CoConstants.CONST_SPANISH_LANG_CD;
        }
        return languageCd;
    }

    private boolean checkNotAppointment(final COCorrespondence coRequest) {
        return (null != coRequest && null != coRequest.getDocId() && (!coRequest
                .getDocId().equalsIgnoreCase("NGG0014") || coRequest
                .getOfficeNumber() == 0L));
    }

    private void suppressPrevTrig(final COCorrespondence coRequest,
                                  final java.sql.Timestamp tempPrintDate) {
        try {
            String curDt = CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate());
            final List<CoRequestHistory> prevCoRequestHistory = requestHistoryRepository.
                    previousPendingTriggerIfExists(coRequest.getDocId(), Long.parseLong(coRequest.getCaseAppNumber()),curDt);
            if (!CoUtil.isEmpty(prevCoRequestHistory)) {
                for (final CoRequestHistory prev : prevCoRequestHistory) {
                    prev.setCoStatusSw('S');
                    prev.setPendingTrigSw('N');
                    prev.setEdbcRunId(coRequest.getRunId());
//                    prev.setUpdateDt(tempPrintDate);
                    prev.setUpdateUserId(coRequest.getRequestUserId());
                    requestHistoryRepository.save(prev);
                }
            }
        } catch (final Exception e) {
            log.debug("Exception during suppressPrevTrig insert or update method", e);
        }
    }

    private List<Object> getIndvIds(final COCorrespondence coRequest,Timestamp tempPrintDate) throws Exception {
        List<Object> indvListFinal = new ArrayList<>();
        if (CoConstants.ACTION_CD_IN.equals(coRequest.getActionCd())) {

            indvListFinal = getArAppProgramIndvByAppNum(coRequest
                    .getCaseAppNumber(),tempPrintDate);

        }else if (CoConstants.FGG5460.equalsIgnoreCase(coRequest.getDocId())
                && coRequest.getCaseAppFlag() == CoConstants.CHAR_C) {
            if(coRequest.getRunId() != null ) {
                if(StringUtils.isNotBlank(coRequest.getRequestUserId()) &&
                        coRequest.getRequestUserId().contains("IN058")) {
                    // WIC PROGRAM AUTHORIZED TO AP FROM PN, ONLY WHILE PROCESSING IN058
                    indvListFinal = getAll18PlusIndvByCase(
                            Long.parseLong(coRequest.getCaseAppNumber()),"getWicIndvByCaseForHIPPA",
                            Long.parseLong(coRequest.getRunId()),tempPrintDate);
                }else {
                    indvListFinal = getAll18PlusIndvByCase(
                            Long.parseLong(coRequest.getCaseAppNumber()),"getAllIndvByCaseForHIPPA",
                            Long.parseLong(coRequest.getRunId()),tempPrintDate);
                }
            }else {
                indvListFinal = getIndv18plusByCaseAndIndvId(Long.parseLong(coRequest.getCaseAppNumber()), coRequest.getIndvId(),tempPrintDate);
            }
        }
        return indvListFinal;
    }

    private List<Object> getArAppProgramIndvByAppNum(final String appNum, Timestamp tempPrintDate) throws Exception {
        final ArAppProgramIndv cargo = new ArAppProgramIndv();
        final List<Object> indvList = new ArrayList<>();
        cargo.setAppNum(appNum);
        cargo.setAidRequestSw('Y');
//        cargo.setCreateDt(tempPrintDate);
        try {
            final List<ArAppProgramIndv> arAppProgramIndvCargo = arAppProgramIndvRepository.findByAppAndProgramCd(appNum,tempPrintDate);
            indvList.addAll(arAppProgramIndvCargo);
        } catch (final Exception e) {
            throw new Exception("Select failed for AR_APP_PROGRAM_INDV",e);
        }
        return indvList;
    }

    private List<Object> getAll18PlusIndvByCase(final long caseNum,String sql,
                                                final long runId,Timestamp tempPrintDate){
        final List<Object> indvList = new ArrayList<>();
        /*try {
            final EdEligibilityCollection coll = new EdEligibilityCollection();
            final Object[] params = new Object[] { caseNum, runId, tempPrintDate };
            final EdEligibilityCargo[] edEligibility = (EdEligibilityCargo[]) coll
                    .select(sql, params);

            for (EdEligibilityCargo edCargo : edEligibility) {
                indvList.add(edCargo);
            }

        } catch (FrameworkException | ApplicationException e) {
            CoDebugger.debugException(e.getMessage(), e);
            throw new CoException("Select failed for Ed_Eligibility", 16369);
        }*/
        return indvList;
    }

    private List<Object> getIndv18plusByCaseAndIndvId(final long caseNum,
                                                      final long indvId, Timestamp tempPrintDate){

        /*final DcCaseProgramIndvCollection coll = new DcCaseProgramIndvCollection();
        DcCaseProgramIndvCargo dcCaseProgramIndvCargo =new DcCaseProgramIndvCargo();
        dcCaseProgramIndvCargo.setCaseNum(caseNum);
        dcCaseProgramIndvCargo.setIndvId(indvId);
        dcCaseProgramIndvCargo.setCreateDt(tempPrintDate);
        final Object[] params = new Object[] { dcCaseProgramIndvCargo };*/
        final List<Object> indvList = new ArrayList<>();
        /*try {
            final DcCaseProgramIndvCargo[] dcCaseProgramIndvCargos = (DcCaseProgramIndvCargo[]) coll
                    .select("findProgramCdByCaseIndvForHippa", params);

            for (DcCaseProgramIndvCargo dcCargo : dcCaseProgramIndvCargos) {
                indvList.add(dcCargo);
            }

        } catch (FrameworkException | ApplicationException e) {
            CoDebugger.debugException(e.getMessage(), e);
            throw new CoException("Select failed for DcCaseProgramIndv", 16369);
        }*/

        List<Object[]> list = dcCaseProgramIndvRepository.findProgramCdByCaseIndvForHippa(caseNum, indvId, tempPrintDate);
        for(Object obj : list) {
            Object[] array = (Object[]) obj;
            indvList.add(new BigInteger(String.valueOf(array[0])).longValue());
        }

        return indvList;
    }

    private boolean checkFor49BExistsSameDay(COCorrespondence newReq,
                                             final Timestamp currentDate) {
        boolean insert = true;
        try {
            final List<CoRequestHistory> prevReq = requestHistoryRepository.getPrevReqsInTheSameDay49B(newReq.getCaseAppNumber(),
                    currentDate);
            if (!CoUtil.isEmpty(prevReq)) {
                insert = false;
            }
        } catch (final Exception e) {
           log.debug("Exception during checkFor49BExistsSameDay trigger insert or update method", e);
        }
        return insert;
    }

    public void setCoOffDetails(final COCorrespondence coCorrespondence){ //final User userObj) {
        List<MoEmployeeCases> moEmpCaseCargos = null;
        List<MoEmployees> moEmPCargos = null;
        try {
            final String caseAppNumber = coCorrespondence.getCaseAppNumber().trim();
            if (StringUtils.isNumeric(caseAppNumber)) {
                moEmpCaseCargos = getMoEmployeeCasesFromRequest(Long
                        .parseLong(coCorrespondence.getCaseAppNumber()
                                .trim()));
                if(!CoUtil.isEmpty(moEmpCaseCargos)) {
                    moEmPCargos = getAdvisorInfo(moEmpCaseCargos.get(0).getEmpId());
                }
            } else {
                coCorrespondence.setCaseAppFlag('A');
            }
        } catch (final Exception e) {
            log.debug("No Employee ID, Use default logged in worker "
                  + Long.parseLong(coCorrespondence.getCaseAppNumber().trim()), e);
        }

        if (!CoUtil.isEmpty(moEmPCargos)) {
            coCorrespondence.setEmpId(moEmpCaseCargos.get(0).getEmpId());
            coCorrespondence.setOfficeNumber(moEmPCargos.get(0).getPriOfficeNum());
        } /*else {
            coCorrespondence.setEmpId(userObj.getEmpId());
            coCorrespondence.setOfficeNumber(userObj.getLoggedInOffice());
            CoDebugger.debugInformation("Used default logged in worker "
                    + userObj.getEmpId());
        }*/
    }

    public List<MoEmployeeCases> getMoEmployeeCasesFromRequest(final Long caseAppNo) {
        List<MoEmployeeCases>result = null;
        try {
            result =moEmployeesCasesRepository.findByCaseNumberWithRowNum(caseAppNo);
        } catch (final Exception e) {
            log.debug(e.getMessage(), e);
        }
        return result;
    }

    public List<MoEmployees> getAdvisorInfo(final Long empID) {
        List<MoEmployees> result = null;
        try {
            String curDt = CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate());
            result = moEmployeesRepository.findByEmpIdDates(empID,curDt);
        } catch (final Exception e) {
            log.debug(e.getMessage(), e);
        }
        return result;
    }
    private int insertTriggerDetails(final COCorrespondence coRequest,
                                     final Timestamp tempPrintDate) throws ApplicationException {
        // inserting CoRequestHistory
        int commitFlag;
        boolean finalResult = insertCoRequestHistory(coRequest, tempPrintDate,
                coRequest.getDocId());

        if (finalResult) {
            // inserting CoRequestRecipients
            coRecipientsManager.configureRecipientsForCoReqSeq(coRequest);
            // inserting CoRequestHistoryDetail
            insertCoRequestHistoryDetail(coRequest,
                    tempPrintDate, coRequest.getDocId());
            commitFlag = 1;
        } else {
            commitFlag = -1;
        }
        return commitFlag;
    }

    private void insertCoRequestHistoryDetail(
            final COCorrespondence coRequest, final Timestamp tempPrintDate,
            String docId) {
        CoRequestHistoryDetail coRequestHistoryDetail=getCoRequestHistoryDetailCargo(coRequest, tempPrintDate,docId);
        coRequestHistoryDetail=historyDetailRepository.save(coRequestHistoryDetail);
        coRequest.setCoDetSeq(coRequestHistoryDetail.getCoDetSeq());
    }

    public CoRequestHistoryDetail getCoRequestHistoryDetailCargo(
            final COCorrespondence coRequest, final Timestamp tempPrintDate,
            String docId){
        final CoRequestHistoryDetail coRequestHistoryDetail = new CoRequestHistoryDetail();
        coRequestHistoryDetail
                .setPrintMode(coRequest.getPrintMode() != CoConstants.DEFAULT_CHAR_VALUE ? coRequest
                        .getPrintMode() : CoConstants.CHAR_N);
        coRequestHistoryDetail.setCoReqSeq(coRequest.getCoReqSeq());
        coRequestHistoryDetail.setCreateUserId(coRequest.getRequestUserId());
//        coRequestHistoryDetail.setCreateDt(tempPrintDate);
        coRequestHistoryDetail.setReqDt(tempPrintDate);
        coRequestHistoryDetail.setReprintSw(CoConstants.PRINT_ORIGINAL);
        coRequestHistoryDetail.setEnvelopeSw(CoConstants.N);

        if (CoConstants.PRINT_MODE_ONLINE == coRequest.getPrintMode()) {
            coRequestHistoryDetail.setPrintDt(tempPrintDate);
        }

        if ((CoConstants.PRINT_MODE_BATCH == coRequest.getPrintMode())
                && (CoConstants.MAIL.equalsIgnoreCase(coRequest.getGoGreen()) || CoConstants.MAIL_EMAIL
                .equalsIgnoreCase(coRequest.getGoGreen()))) {
            coRequestHistoryDetail.setEnvelopeSw(CoConstants.Y);
        }

        if (CoConstants.PRINT_REPRINT == coRequest.getReprintSw()) {
            coRequestHistoryDetail.setPrintDt(tempPrintDate);
            coRequestHistoryDetail.setReprintSw(CoConstants.PRINT_REPRINT);
        }
        if (CoConstants.ATTACHMENT_DOC_ID_FGG551.equalsIgnoreCase(docId) && !isLocalCentralPrintedFgg551) {
            coRequestHistoryDetail.setPrintDt(null);
            coRequestHistoryDetail.setPrintMode(CoConstants.PRINT_MODE_BATCH);
        }
        return coRequestHistoryDetail;
    }
    
    private boolean insertCoRequestHistory(final COCorrespondence coRequest,
                                           final Timestamp tempPrintDate, final String docId){
        CoRequestHistory coRequestHistory = requestHistoryRepository.save(getCoRequestHistory( coRequest, tempPrintDate, docId));
        coRequest.setCoReqSeq(coRequestHistory.getCoReqSeq());
        return true;
    }

    public CoRequestHistory getCoRequestHistory(final COCorrespondence coRequest,
                                                          final Timestamp tempPrintDate, final String docId){
        final CoRequestHistory coRequestHistory = new CoRequestHistory();
        CorrespondenceServices.formatCoObject(coRequestHistory, coRequest);
        coRequestHistory.setRequestTypeCd(coRequest.getCaseAppFlag() != CoConstants.DEFAULT_CHAR_VALUE ? coRequest
                        .getCaseAppFlag() : CoConstants.CHAR_N);

        coRequestHistory.setDocId(docId);
        coRequestHistory.setDocTypeCd(coRequest.getDocType() != CoConstants.DEFAULT_CHAR_VALUE ? coRequest
                        .getDocType() : CoConstants.CHAR_N);
        coRequestHistory.setIndvId(coRequest.getIndvId());
        coRequestHistory.setOfficeNum(coRequest.getOfficeNumber());
        coRequestHistory.setProgramCd(coRequest.getAssistanceProgramCode());
        coRequestHistory.setActionCd(coRequest.getActionCode() != CoConstants.DEFAULT_CHAR_VALUE ? coRequest
                        .getActionCode() : CoConstants.CHAR_N);
        coRequestHistory.setReasonCdList(coRequest.getReasonCdList());
        coRequestHistory.setCaseNum(coRequest.getCaseAppNumber() != null ? Long.parseLong(coRequest.getCaseAppNumber()) : 0L);

        /*
         * EmpId is not set when the request is from Batch(i.e action value
         * would be empty).
         */
        if ((coRequest.getActionValue() != null)
                && !CoConstants.EMPTY_STRING.equals(coRequest.getActionValue())) {
            coRequestHistory.setEmpId(coRequest.getEmpId());
        }
        coRequestHistory.setMiscParms(coRequest.getMiscParameters());
        coRequestHistory.setDraftSw(coRequest.getDraftSwitch() != CoConstants.DEFAULT_CHAR_VALUE ? coRequest
                        .getDraftSwitch() : CoConstants.CHAR_N);

        // Draft & Co Status Switch to determine the Correspondence status in
        // View Pending Correspondence Screen.
        if (coRequestHistory.getDraftSw() != CoConstants.DRAFT_TRIGGER_YES) {
            coRequestHistory.setCoStatusSw(CoConstants.CHAR_P);
        } else {
            coRequestHistory.setCoStatusSw(CoConstants.CHAR_D);
        }

        // Pending Trigger Switch to determine the Correspondence status for
        // batch process.
        // History Switch to view Correspondence in View History Screen.
        if (coRequest.getPrintMode() == CoConstants.PRINT_MODE_ONLINE) {
            coRequestHistory.setPendingTrigSw(CoConstants.CHAR_N);
            coRequestHistory.setHistorySw(CoConstants.CHAR_Y);
        } else {
            coRequestHistory.setPendingTrigSw(coRequest.getPendingTrigSw() != CoConstants.DEFAULT_CHAR_VALUE ? coRequest
                            .getPendingTrigSw() : CoConstants.CHAR_N);
            coRequestHistory.setHistorySw(coRequest.getHistorySwitch() != CoConstants.DEFAULT_CHAR_VALUE ? coRequest
                            .getHistorySwitch() : CoConstants.CHAR_N);
        }
        if (coRequestHistory.getHistorySw() == CoConstants.CHAR_Y) {
            coRequestHistory.setOrigPrintDt(tempPrintDate);
        }
        coRequestHistory.setLanguageCd(coRequest.getLangCd());
        coRequestHistory.setGoGreen(coRequest.getGoGreen());
        coRequestHistory.setOfficeNum(coRequest.getOfficeNumber());
        coRequestHistory.setApptId(coRequest.getApptId());
        coRequestHistory.setBenefitNum(coRequest.getBenefitNumber());
        coRequestHistory.setAssistanceList(coRequest.getTypeOfAssistanceList());
//        coRequestHistory.setCreateDt(tempPrintDate);
        coRequestHistory.setCreateUserId(coRequest.getRequestUserId());
        coRequestHistory.setMassMailingId(coRequest.getMassMailingId());
        coRequestHistory.setDisId(coRequest.getDisId());
        coRequestHistory.setManuallyGeneratedSw(coRequest.isManuallyGenerated() ?
                CoConstants.CHAR_Y:CoConstants.CHAR_N);
        coRequestHistory.setGenerateDt(tempPrintDate);
        coRequestHistory.setMassGeneratedSw(coRequest.getMassGeneratedSw() != CoConstants.DEFAULT_CHAR_VALUE ?
                coRequest.getMassGeneratedSw() : CoConstants.CHAR_N);

        if (coRequest.getEdgTraceId() != null && coRequest.getEdgTraceId() != 0) {
            coRequestHistory.setEdgTraceId(coRequest.getEdgTraceId());
            coRequestHistory.setEdgNum(coRequest.getEdgeNumber());
        }
        // Check if doc_id exists 
        if(coRequest.getLogId()>0){
            coRequestHistory.setLogId(coRequest.getLogId());
        }
        coRequestHistory.setProviderId(coRequest.getProviderId());
        coRequestHistory.setEdbcRunId(coRequest.getRunId());
        if (CoConstants.NGGA0021.equalsIgnoreCase(coRequest.getDocId())
                && CoConstants.NGGA0021.equalsIgnoreCase(docId)) {
            if (coRequest.getCcProviderId() != 0) {
                coRequestHistory.setCcProviderId(coRequest.getCcProviderId());
            }
            if (coRequest.getCcCertStartDate() != null) {
                coRequestHistory.setCcProviderCertStartDt(coRequest.getCcCertStartDate());
            }
            if (coRequest.getCcCertEndDate() != null) {
                coRequestHistory.setCcProviderCertEndDt(coRequest.getCcCertEndDate());
            }
        }

        setHstStringInCoRequestHistory(coRequestHistory, coRequest);

        if (CoConstants.ATTACHMENT_DOC_ID_FGG551.equalsIgnoreCase(docId) && !isLocalCentralPrintedFgg551) {
            coRequestHistory.setPendingTrigSw(CoConstants.CHAR_B);
            coRequestHistory.setHistorySw(CoConstants.CHAR_N);
        }
        return coRequestHistory;
    }

    private void setHstStringInCoRequestHistory(final CoRequestHistory coRequestHistory,
            final COCorrespondence coRequest) {
        try {
            final String xmlStr = coRequest.getXmlStr();
            if (xmlStr != null) {
                Blob bd = new SerialBlob(xmlStr.getBytes());
                coRequestHistory.setHstPrintString(bd);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<DcHeadOfHousehold> getHoHIndividual(final Long caseNum)  {
        List<DcHeadOfHousehold> result = null;
        try {
            result = dcHeadOfHouseholdRepository.findHoHbyCaseNum(caseNum,CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate()));
        } catch (final Exception e) {
            log.debug("Exception while fetching data from DC_HEAD_OF_HOUSEHOLD: "
                    + e.getMessage(), e);
        }
        if ((result == null) || (result.size() == 0)) {
           log.warn("No data in DC_HEAD_OF_HOUSEHOLD");
        }
        return result;
    }

    public List<ArAppIndv> getHoHIndividual(final String caseAppNumber) throws Exception {
        List<ArAppIndv> result;
        try {
            result = arAppIndvRepository.findByAppHOH(caseAppNumber);
        } catch (final Exception e) {
            log.debug(e.getMessage(), e);
            throw new Exception("Select failed for AR_APP_INDV", e);
        }
        if ((result == null) || (result.size() == 0)) {
            throw new Exception("No data in AR_APP_INDV");
        }
        return result;
    }

    public List<DcIndv> getHOHIndv(final String caseAppNo,char caseAppFlag) throws Exception {
        List<DcIndv> result = null;
        try {
            if(CoConstants.APPLICATION == caseAppFlag){
                //result = dcIndvRepository.findIndvByAppNumHohSw(caseAppNo);
            }else{
                List<DcHeadOfHousehold> dcHeadOfHouseholds =dcHeadOfHouseholdRepository.
                        findIndvByCaseNumHohSwNotices(Long.parseLong(caseAppNo),
                                CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate()));
                if(dcHeadOfHouseholds.size()>0){
                    result = dcIndvRepository.findByIndvId(dcHeadOfHouseholds.get(0).getIndvId());
                }
            }
        } catch (final Exception e) {
            throw new Exception("Select failed for DC_INDV",e);
        }
        return result;
    }
    public List<DcHeadOfHousehold> getHoHIndividualName(final long caseNum,
                                         final Timestamp generateDt)  {
        List<DcHeadOfHousehold> result = null;
        try {
            result = dcHeadOfHouseholdRepository.findHoHbyCaseNum(caseNum,CoUtil.getDateForWhereClauseANSI(generateDt));
        } catch (final Exception e) {
            log.debug("Exception while fetching data from DC_HEAD_OF_HOUSEHOLD: "
                   + e.getMessage(), e);
        }
        if ((result == null) || (result.size() == 0)) {
            log.warn("No data in DC_HEAD_OF_HOUSEHOLD");
        }
        return result;
    }
    public List<DcIndv> getDcIndividual(final Long individualID) throws Exception {
        List<DcIndv> result;
        List<Object[]> objectList;
        try {
            result = dcIndvRepository.findByIndividualID(individualID);
        } catch (final Exception e) {
            log.debug("Error " + individualID
                   + " detailed message" + e.getMessage(), e);
            throw new Exception("Select failed for DC_INDV for indv ID "
                    + individualID, e);
        }
        return result;
    }

    @Transactional
    private void updateCoRequestHistoryLogIds(final Long logId, final Map<Long, String> coReqSeqMap) {
        final Set<Long> coReqSeqSet = coReqSeqMap.keySet();
        final StringBuilder coReqSeqIds = new StringBuilder();
        for (final Long key : coReqSeqSet) {
            if (coReqSeqIds.length() > 0) {
                coReqSeqIds.append(",").append(key);
            } else {
                coReqSeqIds.append(key);
            }
        }
        requestHistoryRepository.updateCoRequestHistoryLogIds(coReqSeqIds.toString(),logId);
    }

    public List<DcCaseAddresses> getCaseAddressDetails(Long caseNum, String addTypeCdPa, Timestamp generateDate) throws Exception {
        List<DcCaseAddresses> result;
        try {
            String curDt=CoUtil.getDateForWhereClauseANSI(generateDate);
            result = dcCaseAddressesRepository.findByCaseEffectiveDates(caseNum,addTypeCdPa,curDt);
            if(CoUtil.isEmpty(result)){
                result = dcCaseAddressesRepository.findCountyByCaseNumber(caseNum,addTypeCdPa);
            }
        } catch (final Exception e) {
            log.debug(e.getMessage(), e);
            throw new Exception("Select failed for DC_CASE_ADDRESSES");
        }
        if (CoUtil.isEmpty(result)) {
            throw new Exception("No data in DC_CASE_ADDRESSES");
        }
        return result;
    }

    public List<ArApplicationForAid> getArApplication(Long caseNum) throws Exception {
        List<ArApplicationForAid> result;
        try {
            result = arApplicationForAidRepository.findByCaseNum(caseNum);
        } catch (final Exception e) {
            throw new Exception("Select failed for AR_APPLICATION_FOR_AID",e);
        }
        if (CoUtil.isEmpty(result)) {
            throw new Exception("No data in AR_APPLICATION_FOR_AID");
        }
        return result;
    }

    public List<MoOffices> getOfficeInfo(final Long aOfficeNum) throws Exception {
        List<MoOffices> result;
        String curDt=CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate());
        try {
            result = moOfficesRepository.findByOfficeNumber(aOfficeNum,curDt);
        } catch (final Exception e) {
            throw new Exception("Select failed for MO_OFFICES", e);
        }
        if ((result == null) || (result.size() == 0)) {
            throw new Exception("No data in MO_OFFICES");
        }
        return result;
    }

    public List<MoOfficeAddresses> getOfficeAddress(final Long officeNo) throws Exception {
        List<MoOfficeAddresses> result;
        try {
            result = moOfficeAddressesRepository.findByOfficeNum(officeNo);
        } catch (final Exception e) {
            throw new Exception("Select failed for MO_OFFICE_ADDRESSES",e);
        }
        if ((result == null) || (result.size() == 0)) {
            throw new Exception("No data in MO_OFFICE_ADDRESSES");
        }
        return result;
    }

    public List<CoManualFields> getManualFields(final String docId) throws Exception {
        List<CoManualFields>  result;
        try {
            result = coManualFieldsRepository.findByDocIdOrderByFieldOrderNum(docId);
        } catch (final Exception e) {
            throw new Exception("No data in CO_MANUAL_FIELDS");
        }
        return result == null ? Collections.emptyList() : result;
    }

    public List<FwDataElementList> getManualFields(final Long elementId) throws Exception {
        List<FwDataElementList> result;
        try {
            result = fwDataElementListRepository.findByElementId(elementId);
        } catch (final Exception e) {
            log.error(e.getMessage());
            throw new Exception("No data in FW_DATA_ELEMENT_LIST");
        }
        return result;
    }

    public List<CoManualData> getManualData(final Long coReqSeq,
                                            final Long fieldOrderNum) throws Exception {
        List<CoManualData> result;
        try {
            result = coManualDataRepository.findByCoReqSeqAndFieldOrderNum(coReqSeq,fieldOrderNum);
        } catch (final Exception e) {
            throw new Exception("Error in getting data CO_MANUAL_DATA");
        }
        return result;
    }

    public List<CoManualData> getManualData(final Long coReqSeq) throws Exception {
        List<CoManualData> result;
        try {
            result = coManualDataRepository.findByCoReqSeq(coReqSeq);
        } catch (final Exception e) {
            throw new Exception("Error in getting data CO_MANUAL_DATA");
        }
        return result;
    }


    /**
     * This method is used to update CO_REQUEST_HISTORY table.
     *
     * @author Siva aluri
     * @param coReqSeq
     *            long - CO history sequence number.
     */

    public int updateCoReqHistoryStatusSwfornotice(final long coReqSeq,
                                                   final String actionValue,final String supUserId) throws Exception {

        char actionFlag;
        int commitFlag = 0;

        if (actionValue.equalsIgnoreCase("suppress")) {
            actionFlag = CoConstants.CHAR_S;
        } else {
            actionFlag = CoConstants.CHAR_P;
        }

/*        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String sysDate = formatter.format(calendar.getTime());*/

        try {
            requestHistoryRepository.updateCoStatusSwitch(actionFlag , coReqSeq, supUserId);
            commitFlag = 1;
        } catch (final Exception e) {
            commitFlag = -1;
        }
        return commitFlag;

    }

    /**
     * @author Method to get data from T1004_APP_INDV based
     *         on APP_NUM and INDV_SEQ_NUM
     *
     * @param appNum
     *            long
     * @param indvSeqNum
     *            long
     * @return List<T1004AppIndv>
     * @throws Exception
     *
     */
    public List<T1004AppIndv> findByAppNumAndIndvSeq(final String appNum,
                                           final long indvSeqNum) throws Exception {
        List<T1004AppIndv> result = null;
        try {
            result = t1004AppIndvRepository.findByAppNumAndIndvSeqNum(appNum, indvSeqNum);
        } catch (final Exception e) {
            log.error("Exception while fetching data from T1004_APP_INDV: "
                    + e.getMessage());
        }
        if ((result == null) || (result.size() == 0)) {
            log.error("No data in T1004_APP_INDV");
        }
        return result;
    }

    /**
     * This method get data from AR_APPLICATION_FOR_AID by Application Number.
     *
     * @param indvId
     *            long
     * @param appNum
     *            String
     * @return List<VArApplicationIndv>
     * @throws Exception
     */
    public List<VArApplicationIndv> getAppIndvDtlsByIndvIdAppNum(long indvId, String appNum) throws Exception {
        List<VArApplicationIndv> result;
        try {
            result = vArApplicationIndvRepository.findByAppNumberAndIndvId(appNum, indvId);
        } catch (final Exception e) {
            log.error("Select failed for AR_APPLICATION_FOR_AID, 16741");
            throw new Exception("Select failed for AR_APPLICATION_FOR_AID "+ 16741);
        }
        if ((result == null) || (result.size() == 0)) {
            System.out.println("No data in AR_APPLICATION_FOR_AID");
        }
        return result;
    }

    /**
     * Method is used to insert a mass mailing request
     *
     * @param cargo
     * @return int commitFlag
     * @throws Exception
     */
    @Transactional
    public int insertMassMailing(CoMassMailingReq cargo) throws Exception {
        int commitFlag = 0;
        try{

            if (cargo.getMassMailingSeqNum() == null || cargo.getMassMailingSeqNum() == 0) {
                coMassMailingRepository.save(cargo);
            } else {
                cargo.setHistorySeq(cargo.getMassMailingSeqNum());
                coMassMailingRepository.save(cargo);
            }
            commitFlag = 1;

        } catch (Exception e) {
            log.debug("Insert Failed");
            throw new Exception("Insert Failed");
        }
        return commitFlag;
    }

    /**
     * Method is used to delete a mass mailing request
     *
     * @param cargo
     * @return int commitFlag
     * @throws Exception
     */
    @Transactional
    public int deleteMassMailing(CoMassMailingReq cargo) throws Exception {
        int commitFlag = 0;
        try {
            coMassMailingRepository.delete(cargo);
            commitFlag = 1;
        } catch (Exception e) {
            log.debug("Delete Failed");
            throw new Exception("Delete Failed");
        }
        return commitFlag;
    }


    public List<T1004AppIndv> findByAppNum(String caseApplication) {
        List<T1004AppIndv> result = null;
        try{
            result = t1004AppIndvRepository.findByAppNum(caseApplication);
        } catch(Exception e) {
           log.error("Exception while fetching data from T1004_APP_INDV: "
                   + e);
        }
        if((result == null) || (result.size() == 0)) {
            log.error("No data in T1004_APP_INDV");
        }
        return result;
    }

    public List<VArApplicationIndv> getArApplicationIndv(String caseApplication) {
        List<VArApplicationIndv> result = null;
        try {
            result = vArApplicationIndvRepository.findByHeadOfHousehold(caseApplication, 'Y');
        } catch (Exception e) {
            log.error("Exception while fetching data from V_AR_APPLICATION_INDV: "
                    + e.getMessage());
        }
        if((result == null) || (result.size() == 0)) {
            log.error("No data in V_AR_APPLICATION_INDV");
        }
        return result;
    }

    public List<VDcCaseIndvDetails> getAllIndvByCaseNum(Long caseApplication) {
        List<VDcCaseIndvDetails> result = null;
        try {
            result = vDcCaseIndvDetailsRepository.findByCaseNumber(caseApplication);
        } catch(Exception e) {
            log.error("Select failed for V_DC_CASE_INDV_DETAILS " + e.getMessage());
        }
        if((result == null) || (result.size() == 0)) {
            log.error("No data in V_DC_CASE_INDV_DETAILS");
        }
        return result;
    }

    public List<VArApplicationIndv> getArApplicationIndvs(String caseApplication) {
        List<VArApplicationIndv> result = null;
        try {
            result = vArApplicationIndvRepository.findByAppNum(caseApplication);
        } catch(Exception e) {
            log.error("Exception while fetching data from V_AR_APPLICATION_INDV: " + e.getMessage());
        }
        if((result == null) || (result.size() == 0)) {
            log.error("No data in V_AR_APPLICATION_INDV");
        }
        return result;
    }
}

