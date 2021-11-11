package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.*;
import com.deloitte.nextgen.dto.vo.DocumentDetailsVO;
import com.deloitte.nextgen.dto.vo.ViewPendingVO;
import com.deloitte.nextgen.entities.*;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.mapper.CoManualDataMapper;
import com.deloitte.nextgen.repository.CoRequestHistoryRepository;
import com.deloitte.nextgen.repository.InDisDocMasterRepository;
import com.deloitte.nextgen.repository.NoticeHistoryRepository;
import com.deloitte.nextgen.repository.VCoRequestRepository;
import com.deloitte.nextgen.service.CoViewPendingService;
import com.deloitte.nextgen.service.DocumentHandlerService;
import com.deloitte.nextgen.service.ICoOnline;
import com.deloitte.nextgen.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoViewPendingServiceImpl implements CoViewPendingService {

    @Autowired
    VCoRequestRepository repository;

    @Autowired
    CoRequestHistoryRepository requestRep;

    @Autowired
    CoDAOServices coDAOServices;

    @Autowired
    ICoOnline iCorrespondence;

    @Autowired
    ManualCorrespondenceManager manualCorrespondenceManager;

    @Autowired
    CoManualDataMapper coManualDataMapper;

    @Autowired
    NoticeHistoryRepository noticeHistoryRepository;

    @Autowired
    DocumentHandlerService docService;

    @Autowired
    InDisDocMasterRepository inDisDocMasterRepo;

    public List<ViewPendingVO> fetchByCaseNum(Long caseNum, String curDt) {
        List<Object[]> caseList = repository.findByCaseNumAndSort(caseNum, curDt);
        return caseList.stream().map(this::getViewPendingVO).collect(Collectors.toList());
    }

    public List<ViewPendingVO> fetchByAppNum(String appNum, String curDt) {
        List<Object[]> caseList = repository.findByAppNumAndSort(appNum, curDt);
        return caseList.stream().map(this::getViewPendingVO).collect(Collectors.toList());
    }

    public List<ViewPendingVO> fetchByClientId(Long clientId, String curDt) {
        List<Object[]> caseList = repository.findByClientIdAndSort(clientId, curDt);
        return caseList.stream().map(this::getViewPendingVO).collect(Collectors.toList());
    }


    public List<ViewPendingVO> fetchByWorkerName(String workerName, String curDt) {
        List<Object[]> caseList = repository.findByWorkerNameAndSort(workerName, curDt);
        return caseList.stream().map(this::getViewPendingVO).collect(Collectors.toList());
    }

    public List<ViewPendingVO> fetchByWorkerId(Long workerId, String curDt) {
        List<Object[]> caseList = repository.findByWorkerIdAndSort(workerId, curDt);
        return caseList.stream().map(this::getViewPendingVO).collect(Collectors.toList());
    }

    private ViewPendingVO getViewPendingVO(Object[] i) {
        ViewPendingVO vVO = new ViewPendingVO();
        if(null != i[0])
            vVO.setIndvId(new BigDecimal(String.valueOf(i[0])).longValueExact());
        vVO.setDocName((String) i[1]);
        vVO.setLanguageCd((String) i[2]);
        vVO.setReqDt((Date) i[3]);
        vVO.setGenerateDt((Date) i[4]);
        vVO.setPendingTrigSw((Character) i[5]);
        if(null != i[6])
            vVO.setCoReqSeq(new BigDecimal(String.valueOf(i[6])).longValueExact());
        vVO.setDocId((String)(i[7]));
        if(null!=i[8]) {
            vVO.setCcProviderId(new BigDecimal(String.valueOf(i[8])).longValueExact());
        } else {
            vVO.setCcProviderId(0L);
        }
        if(null!=i[9]){
            vVO.setCcProviderCertStartDt((Date) i[9]);
        }
        if(null!=i[10]){
            vVO.setCcProviderCertEndDt((Date)i[10]);
        }
        vVO.setCoStatusSw((Character) i[11]);
        vVO.setDraftSw((Character) i[12]);
        vVO.setWorkerName((String) i[13]);
        vVO.setCoDetSeq(new BigDecimal(String.valueOf(i[14])).longValueExact());
        vVO.setPrintMode((Character) i[15]);
        vVO.setPrintType((Character) i[16]);
        return vVO;
    }

    @Override
    public String viewPendingDetailService(Long coReqSeq, String docId, String previewVal,CoGenerateManualDTO generateManualDTO) throws Exception {
        CoRequestHistory coRequestHistory;
        String xml = null;
        List<CoRequestHistory> coRequestHist = requestRep.findByCoReqSeqOrderByCoReqSeq(coReqSeq);
        if (!CoUtil.isEmpty(coRequestHist)) {
            coRequestHistory = coRequestHist.get(0);
            final String miscParams = coRequestHistory.getMiscParms();
            final Long indivId = coRequestHistory.getIndvId();
            final String caseAppNum = coRequestHistory.getCaseNum() != null ? String.valueOf(coRequestHistory.getCaseNum()) : coRequestHistory.getAppNum();
            final COCorrespondence coCorrespondence = getCoCorrespondence(coReqSeq, docId, previewVal, coRequestHistory, miscParams, indivId, caseAppNum);
            coDAOServices.setCoOffDetails(coCorrespondence);//, userObj);
            if(previewVal!=null && previewVal.equalsIgnoreCase("true")){
                coCorrespondence.setPreviewMode(true);
            }
            xml=generateCorrespondence(coCorrespondence,generateManualDTO);
        }
        return xml;
    }

    @Override
    public List<ViewPendingVO> fetchPendingCO(ViewPendingDTO pendingDTO, String curDt) {
        List<ViewPendingVO> caseList = new ArrayList<>();
        switch (pendingDTO.getSearchCriteria()) {
            case CoConstants.CASE_SER:
                caseList = this.fetchByCaseNum(pendingDTO.getCaseNum().longValue(), curDt);
                this.addSearchDetails(caseList, pendingDTO.getCaseNum().toString(), CoConstants.CASE_SER_TEXT);
                Long[] requestIds = noticeHistoryRepository.findUniqueRequestId(pendingDTO.getCaseNum());
                List<NoticeRequestStatus> noticeReqList = new ArrayList<>();
                for(Long id : requestIds){
                    List<NoticeRequestStatus> noticeRequestStatusList = noticeHistoryRepository.findByNoticeRequestIdAndStatus(id);
                    if(!noticeRequestStatusList.isEmpty()){
                        noticeReqList.add(noticeRequestStatusList.get(0));
                    }
                }
                caseList.addAll(getVOFromNoticeRequest(noticeReqList));
                break;
            case CoConstants.APP_SER:
                caseList = this.fetchByAppNum(pendingDTO.getAppNum(), curDt);
                this.addSearchDetails(caseList, pendingDTO.getAppNum(), CoConstants.APP_SER_TEXT);
                break;
            case CoConstants.CLIENT_SER:
                caseList = this.fetchByClientId(pendingDTO.getClientId().longValue(), curDt);
                this.addSearchDetails(caseList, pendingDTO.getClientId().toString(), CoConstants.CLIENT_SER_TEXT);
                break;
            case CoConstants.WNAME_SER:
                caseList = this.fetchByWorkerName(pendingDTO.getWorkerName(), curDt);
                this.addSearchDetails(caseList, pendingDTO.getWorkerName(), CoConstants.WNAME_SER_TEXT);
                break;
            case CoConstants.WID_SER:
                caseList = this.fetchByWorkerId(pendingDTO.getWorkerId().longValue(), curDt);
                this.addSearchDetails(caseList, pendingDTO.getWorkerId().toString(), CoConstants.WID_SER_TEXT);
                break;
        }
        return caseList;
    }

    private List<ViewPendingVO> getVOFromNoticeRequest(List<NoticeRequestStatus> noticeRequestStatusList) {
        List<ViewPendingVO> voList = new ArrayList<>();

        for(NoticeRequestStatus i: noticeRequestStatusList) {
            ViewPendingVO vo = new ViewPendingVO();
            vo.setCaseAppNumber(String.valueOf(i.getCaseNum()));
            if(null != i.getHohId())
                vo.setIndvId(new BigDecimal(String.valueOf(i.getHohId())).longValueExact());
            vo.setDocName(i.getFormTitle());
            vo.setLanguageCd("EN");
            if(null != i.getRequestDate()) {
                vo.setReqDt(i.getRequestDate());
            }
            if(null !=i.getCreateDt()) {
                vo.setGenerateDt(Timestamp.valueOf(i.getCreateDt().now()));
            }
            if(null != i.getLogRequestId())
                vo.setCoReqSeq(new BigDecimal(String.valueOf(i.getLogRequestId())).longValueExact());
            vo.setDocId(i.getTemplateId());
            vo.setWorkerName(i.getClientName());
            vo.setOpentextInd(CoConstants.YES);
            vo.setStatus(CoUtil.getNoticeStatusValue(i.getStatus()));
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<ViewPendingDTO> suppressUnsuppress(ViewPendingDTO pendingDTO) throws Exception{

        List <ViewPendingDTO> listResp = new ArrayList<>();
        try {
            int commitFlag;
            pendingDTO.getGenerateManualDTO().setPageId(CoConstants.COVPC);
            final COViewPendingManager manager = new COViewPendingManager();
            final Long caseOrApplicationNumber = pendingDTO.getCaseNum();

            String supUserId = String.valueOf(pendingDTO.getWorkerId());
            final String actionVal = pendingDTO.getActionType();
            final String docId = pendingDTO.getDocId();
            final long coReqSeq = pendingDTO.getCoReqSeq();

            final List messageCode = new ArrayList();
            if ("save".equals(actionVal)) {
                commitFlag =  0 ;//manager.updateCoReqHistoryComments(pendingDTO);
            } else {
                commitFlag = manager.suppressUnsuppress(coReqSeq, actionVal, docId, supUserId);
            }

            if (commitFlag == 1) {
                messageCode.add("134");
                pendingDTO.setMessageCode(messageCode);
            } else {
                messageCode.add("137");
                pendingDTO.setMessageCode(messageCode);
            }
        } catch (Exception ae) {
            throw new Exception(ae);
        }
        listResp.add(pendingDTO);
        return listResp;
    }

    @Override
    public List<CoManualDataDTO> fetchManualData(Long coReqSeq) throws Exception {
        List<CoManualData> coManualData = coDAOServices.getManualData(coReqSeq);
        if(!CoUtil.isEmpty(coManualData)){
            List<CoManualDataDTO> coManualDataDTO = coManualDataMapper.toDto(coManualData);
            return coManualDataDTO;
        }
        return null;
    }

    @Override
    public String centralPrint(ViewPendingDTO pendingDTO) {
        COCorrespondence coCorrespondence = new COCorrespondence();
        List<CoRequestHistory> coRequestHist = requestRep.findByCoReqSeqOrderByCoReqSeq(pendingDTO.getCoReqSeq());
        if (!CoUtil.isEmpty(coRequestHist)) {
            String miscParams = coRequestHist.get(0).getMiscParms();
            Long indivId = coRequestHist.get(0).getIndvId();
            String caseAppNum = coRequestHist.get(0).getCaseNum() != null ? String.valueOf(coRequestHist.get(0).getCaseNum()) : coRequestHist.get(0).getAppNum();
            coCorrespondence.setCaseAppNumber(caseAppNum);
            coCorrespondence.setMiscParameters(miscParams);
            coCorrespondence.setIndvId(indivId);
            coCorrespondence.setCaseAppFlag(CoConstants.CASE);
            coCorrespondence.setDocId(coRequestHist.get(0).getDocId());
        }
        coCorrespondence.setDraftSwitch('N');
        coCorrespondence.setPrintMode('B');
        coCorrespondence.setPendingTrigSw('N');
        coCorrespondence.setActionValue(CoConstants.UPDATE_CPC);
        final CoManualFieldsDTO[] fieldsDTO = pendingDTO.getGenerateManualDTO().getCoManualFieldsMap().get("manualFields");
        final CoManualFieldsDTO[] areasDTO = pendingDTO.getGenerateManualDTO().getCoManualFieldsMap().get("manualAreas");

        final String docId = coCorrespondence.getDocId();
        final String docIdsubstring = docId.substring(3);
        Map<String,String> request =pendingDTO.getGenerateManualDTO().getRequest();
        if(!(AValidationDocs.noManualFieldFormsList)
                .contains(docId)){
            coCorrespondence.setFormData(getDataMap(docId, docIdsubstring,request));
        }
        return manualCorrespondenceManager.initiateCentralPrint(
                fieldsDTO, areasDTO, pendingDTO.getGenerateManualDTO(),coCorrespondence);
    }

    private Map getDataMap(final String docId, final String docIdSubstring,final Map request) {
        final String methodToCall = CoConstants.GET_FORM + docIdSubstring
                + CoConstants.DATA;
        Class c;
        Method m;
        Map<String, String> dataMap = null;
        try {
            c = Class.forName(CoConstants.PKG_CO_MAP_CONVERTOR);
            m = c.getMethod(methodToCall, Map.class);
            dataMap = (Map) m.invoke(docId,request);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
        return dataMap;
    }

    private void addSearchDetails(List<ViewPendingVO> caseList, String caseNumber, String caseSerText) {
        if (!caseList.isEmpty() && !caseList.equals(null)) {
            caseList.forEach(pendingDetail -> {
                pendingDetail.setCaseAppNumber(caseNumber);
                pendingDetail.setSearchCriteria(caseSerText);
            });
        }
    }

    private COCorrespondence getCoCorrespondence(Long coReqSeq, String docId, String previewVal, CoRequestHistory coRequestHistory, String miscParams, Long indivId, String caseAppNum) {
        final COCorrespondence coCorrespondence = new COCorrespondence();
        coCorrespondence.setCaseAppNumber(caseAppNum);
        coCorrespondence.setCaseAppFlag(CoConstants.CASE);
        coCorrespondence.setDocId(docId);
        coCorrespondence.setIndvId(indivId);
        coCorrespondence.setApptId(coRequestHistory.getApptId());
        coCorrespondence.setProviderId(coRequestHistory.getProviderId());
        coCorrespondence.setTypeOfAssistanceList(coRequestHistory.getAssistanceList());
        coCorrespondence.setEdgTraceId(coRequestHistory.getEdgTraceId() != null ? coRequestHistory.getEdgTraceId() : 0L);
        coCorrespondence.setPrintMode(CoConstants.PRINT_MODE_ONLINE);
        coCorrespondence.setPrevCoReqSeq(coReqSeq);
        coCorrespondence.setPreviewMode(Boolean.parseBoolean(previewVal));
        coCorrespondence.setEdgeNumber(coRequestHistory.getEdgNum());
        coCorrespondence.setActionValue(CoConstants.COVPC + CoConstants.GENERATECO);
        coCorrespondence.setMiscParameters(miscParams);
        coCorrespondence.setAssistanceProgramCode(coRequestHistory.getProgramCd());
        coCorrespondence.setGenerateDate(new Timestamp(coRequestHistory.getGenerateDt().getTime()));
        coCorrespondence.setRunId(coRequestHistory.getEdbcRunId());
        coCorrespondence.setSpecialNotes(coRequestHistory.getSpecialNotes());
        coCorrespondence.setLogId(coRequestHistory.getLogId());
        coCorrespondence.setLangCd(coRequestHistory.getLanguageCd());
        coCorrespondence.setRequestUserId(coRequestHistory.getCreateUserId());
        return coCorrespondence;
    }

    public String generateCorrespondence(COCorrespondence coCorrespondence, CoGenerateManualDTO generateManualDTO) throws Exception {
        String xmlStr = null;
        boolean status = true;
        final COCorrespondence coRequest = coCorrespondence;
        final CoManualFieldsDTO[] fieldsDTO = generateManualDTO.getCoManualFieldsMap().get("manualFields");
        final CoManualFieldsDTO[] areasDTO = generateManualDTO.getCoManualFieldsMap().get("manualAreas");
        final FwDataElementListDTO[] fieldsFw = generateManualDTO.getFwDataElementListMap().get("fieldsFw");
        final FwDataElementListDTO[] areasFw = generateManualDTO.getFwDataElementListMap().get("fieldsFw");

        if (coRequest.isPreviewMode()) {
            Map<String,String> request=generateManualDTO.getRequest();
            final String docId = coCorrespondence.getDocId();
            final String docIdSubstring = docId.substring(3);
            HashMap formData = (HashMap) getDataMap(docId, docIdSubstring, request);
            coCorrespondence.setFormData(formData);
            xmlStr = iCorrespondence.printXMLPreviewCo(coCorrespondence);
        } else {
            coRequest.setCoReqSeq(0L);
            coRequest.setCoDetSeq(0L);
            coRequest.setPrintMode(CoConstants.PRINT_MODE_ONLINE);
            coRequest.setPendingTrigSw(CoConstants.PENDING_TRIGGER_NO);
            coRequest.setDraftSwitch(CoConstants.DRAFT_TRIGGER_NO);
            coRequest.setDisId(null);
            coRequest.setCoReqSeqLangCdMap(new HashMap<>());
            int errorCode = coDAOServices.insertOriginalRequest(coRequest);
            if (errorCode != -1) {
                xmlStr = manualCorrespondenceManager.generateCorrespondence(generateManualDTO,fieldsDTO,areasDTO,fieldsFw,areasFw, coRequest);
            }
        }
        return xmlStr;
    }

    public NoticeCustomer getNoticeCustomer(ViewPendingDTO dto) {
        NoticeCustomer noticeCustomer = new NoticeCustomer();
        Customer customer = new Customer();
        NoticeRequest noticeRequest = new NoticeRequest();
        MetaData metaData = new MetaData();
        FormData formData = new FormData();

        noticeRequest.setOrigFlag(CoConstants.CHAR_O);
        HeadOfHouse headOfHouse = new HeadOfHouse();
        noticeRequest.setAction(dto.getAction());
        metaData.setLogRequestId(dto.getCoReqSeq());
        metaData.setRequestId(CoUtil.generateNoticeRequestId());
        metaData.setTemplateId(dto.getDocId());
        metaData.setNoticeType("");
        metaData.setMailDate(new Date(CoUtil.getCurrentDate().getTime()));
        metaData.setCorrNum(0L);
        metaData.setWatermark(dto.getWatermark());
        metaData.setFormNo(dto.getDocId());
        metaData.setFormVersion("1");
        metaData.setFormTitle(dto.getFormTitle());
        metaData.setPreferredLanguage(dto.getPreferredLanguage());
        metaData.setEnvelopeId("");
        metaData.setCaseNum(dto.getCaseNum());
        metaData.setGoGreen(dto.getGoGreen());
        metaData.setAgencyCode(dto.getAgencyCode());

        metaData.setClientName(dto.getFullName());
        metaData.setRequestDate(CoUtil.getCurrentDate().toString());
        metaData.setHohId(dto.getClientId());
        metaData.setSecurityFlag(dto.getSecurityFlag());
        noticeRequest.setMetaData(metaData);

        formData.setCaseName(dto.getFullName());
        formData.setCaseNum(dto.getCaseNum());
        formData.setClientName(dto.getFullName());
        formData.setClientId(dto.getClientId());
        formData.setSSN(dto.getSSN());
        formData.setClientDOB(dto.getClientDOB());
        headOfHouse.setHOHId(dto.getClientId());
        headOfHouse.setHOHName(dto.getFullName());
        formData.setHeadOfHouse(headOfHouse);
        MailingAdd mailingAddress = dto.getMailingAdd();
        formData.setMailingAdd(mailingAddress);
        formData.setSystemDate(CoUtil.getCurrentDate());
        MassHealthMedicaid MASSHealthMedicaid = dto.getMASSHealthMedicaid();
        formData.setMASSHealthMedicaid(MASSHealthMedicaid);
        noticeRequest.setFormData(formData);
        customer.setNoticeRequest(noticeRequest);
        noticeCustomer.setCustomer(customer);

        return noticeCustomer;
    }

    @Override
    public BatchNotice getBatchNotice(ViewPendingDTO dto) throws FwApplicationException {




        BatchNotice batchNotice = new BatchNotice();

        Batch b = new Batch();
        EnvelopeList envelopeList = new EnvelopeList();
        List<Envelope> envelopeArrayList = new ArrayList<>();
        Envelope envelope = new Envelope();

        CorrList corrList = new CorrList();
        Corr corr = new Corr();
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        NoticeRequest noticeRequest = new NoticeRequest();
        MetaData metaData = new MetaData();
        metaData.setLogRequestId(dto.getCoReqSeq());
        noticeRequest.setMetaData(metaData);
        noticeRequest.setAction(dto.getAction());
        customer.setNoticeRequest(noticeRequest);
        customerList.add(customer);
        corr.setCustomer(customerList);
        corrList.setCorr(corr);
        envelope.setCorrList(corrList);
        envelopeArrayList.add(envelope);
        envelopeList.setEnvelope(envelopeArrayList);

        b.setEnvelopeList(envelopeList);

//        b.setEnvelopeList(envelopeList);
//        envelopeArrayList.add(envelope);
//        envelopeList.setEnvelope(envelopeArrayList);

        batchNotice.setBatch(b);
        return batchNotice;
//        envelopeList.setEnvelope(envelope);
//        b.setEnvelopeList(envelopeList);
//        batchNotice.setBatch(b);
//        return batchNotice;

//        FormData formData = new FormData();
//        formData.setCaseName(dto.getFullName());
//        formData.setCaseNum(dto.getCaseNum());
//        formData.setClientName(dto.getFullName());
//        formData.setClientId(dto.getIndivId());
//        formData.setSSN(dto.getSSN());
//        formData.setClientDOB(dto.getClientDOB());
//        formData.setCaseWorkerID("");

//        HeadOfHouse headOfHouse = new HeadOfHouse();
////        headOfHouse.setHOHName(dto.getFullName());
////        headOfHouse.setHOHId(dto.getIndivId());
//
//        formData.setHeadOfHouse(headOfHouse);
////        formData.setMailingAdd(dto.getMailingAdd());
////        formData.setSystemDate(CoUtil.getCurrentDate());
//        MassHealthMedicaid massHealthMedicaid = new MassHealthMedicaid();
//        List<Individual> individualList = new ArrayList<>();
//        Individual individual = new Individual();
////        individual.setName("John Lewin");
////        individual.setMemberId("123");
////        individual.setIndividualDOB(CoUtil.stringToTimestamp("12/03/1980"));
////        individual.setNoticeSalutation(dto.getGenerateManualDTO().getCoManualData().getNoticeSalutation());
////        individual.setNoticeContent(dto.getGenerateManualDTO().getCoManualData().getCoPurposeOfNotice());
////        individual.setPolicyReference(dto.getGenerateManualDTO().getCoManualData().getCoPolicyManualReference());
//        individual.setProgram("MSPA");
//        individual.setEffectiveDate(CoUtil.stringToTimestamp("01/01/2020"));
//        individual.setClosureDate(CoUtil.stringToTimestamp("06/30/2020"));
//        individual.setProgramClosureReason("Age");
//
//        individualList.add(individual);
//
//        individual.setName("John Lewin");
//        individual.setMemberId("123");
//        individual.setIndividualDOB(CoUtil.stringToTimestamp("12/03/1980"));
////        individual.setNoticeSalutation(dto.getGenerateManualDTO().getCoManualData().getNoticeSalutation());
////        individual.setNoticeContent(dto.getGenerateManualDTO().getCoManualData().getCoPurposeOfNotice());
////        individual.setPolicyReference(dto.getGenerateManualDTO().getCoManualData().getCoPolicyManualReference());
//        individual.setProgram("MFPA");
//        individual.setEffectiveDate(CoUtil.stringToTimestamp("01/07/2020"));
//        individual.setClosureDate(CoUtil.stringToTimestamp("06/30/2020"));
//        individual.setProgramClosureReason("");
//
//        individualList.add(individual);
//
//        massHealthMedicaid.setIndividual(individualList);
//        massHealthMedicaid.setHouseholdsize("");
//        massHealthMedicaid.setHHFPLLimit("");
//        StandardRenewalLetter standardRenewalLetter = new StandardRenewalLetter();
//        StandardRenewalLetterDetails standardRenewalLetterDetails = new StandardRenewalLetterDetails();
//        standardRenewalLetterDetails.setDisplayFlagForWaysToRenewCoverage(true);
//        standardRenewalLetterDetails.setRenewCoverageRenewalIndividual("HELENA PCR#PETER CCW#ROBERT QMB#KIARA FOA");
//        standardRenewalLetterDetails.setRenewCoverageDueDt(CoUtil.stringToTimestamp("03/10/2021"));
//        standardRenewalLetterDetails.setDisplayFlagForAppendixC(true);
//        Part1Details part1Details = new Part1Details();
//        part1Details.setPrimaryContactName("HELENA PCR");
//        part1Details.setPrimaryContactDOB(CoUtil.stringToTimestamp("10/07/1989"));
//        part1Details.setResidenceAddressLine1("246 fix lane");
//        part1Details.setResidenceAddressLine2("246 fix lane");
//        part1Details.setResidenceAddressCity("Baton Rouge");
//        part1Details.setResidenceAddressState("LA");
//        part1Details.setResidenceAddressZipCode(70801L);
//        part1Details.setDisplayFlagForCellNumber(false);
//        part1Details.setCellNumber(1234567890L);
//        part1Details.setDisplayFlagForLandlineNumber(false);
//        part1Details.setLandlineNumber(1234567890L);
//        part1Details.setDisplayFlagForWorkNumber(false);
//        part1Details.setWorkNumber(1234567890L);
//        part1Details.setDisplayFlagForOtherNumber(false);
//        part1Details.setOtherNumber(1234567890L);
//        part1Details.setDisplayFlagForPersonalEmail(false);
//        part1Details.setPersonalEmail("personalEmail@email.com");
//        part1Details.setDisplayFlagForWorkEmail(false);
//        part1Details.setWorkEmail("workEmail@email.com");
//
//        List<Part2Details> part2DetailsList = new ArrayList<>();
//
//        Part2Details part2Details = new Part2Details();
//        part2Details.setIndividualName("HELENA PCR");
//        part2Details.setIndividualDOB(CoUtil.stringToTimestamp("07/10/1989"));
//        part2Details.setIndividualGender('F');
//        part2Details.setDisplayFlagForIndvUpForRenewal(true);
//        part2Details.setCheckboxSSNAvailable(true);
//        part2Details.setCheckboxPersonEnrolledInMedicaid(true);
//
//        part2DetailsList.add(part2Details);
//
//        part2Details.setIndividualName("ROBERT QMB");
//        part2Details.setIndividualDOB(CoUtil.stringToTimestamp("07/10/1994"));
//        part2Details.setIndividualGender('M');
//        part2Details.setDisplayFlagForIndvUpForRenewal(true);
//        part2Details.setCheckboxSSNAvailable(true);
//        part2Details.setCheckboxPersonEnrolledInMedicaid(true);
//
//        part2DetailsList.add(part2Details);
//
//        part2Details.setIndividualName("PETER CCW");
//        part2Details.setIndividualDOB(CoUtil.stringToTimestamp("11/114/2011"));
//        part2Details.setIndividualGender('M');
//        part2Details.setDisplayFlagForIndvUpForRenewal(true);
//        part2Details.setCheckboxSSNAvailable(true);
//        part2Details.setCheckboxPersonEnrolledInMedicaid(true);
//
//        part2DetailsList.add(part2Details);
//
//        part2Details.setIndividualName("KIARA FOA");
//        part2Details.setIndividualDOB(CoUtil.stringToTimestamp("10/12/2012"));
//        part2Details.setIndividualGender('F');
//        part2Details.setDisplayFlagForIndvUpForRenewal(true);
//        part2Details.setCheckboxSSNAvailable(true);
//        part2Details.setCheckboxPersonEnrolledInMedicaid(true);
//
//        part2DetailsList.add(part2Details);
//
//        Part3Details part3Details = new Part3Details();
//        part3Details.setDisplayFlagForTaxFiler(false);
//        part3Details.setTaxFiler("TAX FILER");
//        part3Details.setDisplayFlagForSpouseOnReturn(false);
//        part3Details.setSpouseOnReturn("SPOUSE ON RETURN");
//        part3Details.setDisplayFlagForDependantOnReturn(false);
//        part3Details.setDependantOnReturn("DEPENDANT ON RETURN");
//
//        Part6Details part6Details = new Part6Details();
//        part6Details.setDisplayFlagForPart6ResourcesSection(true);
//        part6Details.setDisplayFlagForOngoingResourcesSection(true);
//        part6Details.setDisplayFlagForLTCWaiverDisabilitySection(true);
//
//        OngoingResourcesDetails ongoingResourcesDetails = new OngoingResourcesDetails("PETER CCW", CoUtil.stringToTimestamp("11/14/2011"), "Bank Account/Direct Express Account#holder#Personal");
//        part6Details.setOngoingResourcesDetails(ongoingResourcesDetails);
//
//        Part7Details part7Details = new Part7Details(true, new OngoingEarnedIncomeDetails("ROBERT QM", CoUtil.stringToTimestamp("02/06/1994"), 700.00, "higher"));
//
//        Part9Details part9Details= new Part9Details();
//        part9Details.setDisplayFlagForOngoingUnearnedIncomeSection(true);
//        List<OngoingUnearnedIncomeDetails> ongoingUnearnedIncomeDetailsList = new ArrayList<>();
//        ongoingUnearnedIncomeDetailsList.add(new OngoingUnearnedIncomeDetails("HELENA PCR",
//                CoUtil.stringToTimestamp("07/10/1989"), 60.00, "Capital Gains"));
//        ongoingUnearnedIncomeDetailsList.add(new OngoingUnearnedIncomeDetails("PETER CCW",
//                CoUtil.stringToTimestamp("11/14/2011"), 45.00, "Social Security RSDI"));
//        ongoingUnearnedIncomeDetailsList.add(new OngoingUnearnedIncomeDetails("KIARA FOA",
//                CoUtil.stringToTimestamp("12/10/2012"), 3000.00, "Capital Gains"));
//        part9Details.setOngoingUnearnedIncomeDetails(ongoingUnearnedIncomeDetailsList);
//
//
//        standardRenewalLetterDetails.setPart1Details(part1Details);
//        standardRenewalLetterDetails.setPart2Details(part2DetailsList);
//        standardRenewalLetterDetails.setPart3Details(part3Details);
//        standardRenewalLetterDetails.setPart6Details(part6Details);
//        standardRenewalLetterDetails.setPart7Details(part7Details);
//        standardRenewalLetterDetails.setPart9Details(part9Details);
//
//        standardRenewalLetter.setStandardRenewalLetterDetails(standardRenewalLetterDetails);
//
//        massHealthMedicaid.setStandardRenewalLetter(standardRenewalLetter);
//
//        formData.setMASSHealthMedicaid(massHealthMedicaid);
//
//
//        noticeRequest.setFormData(formData);
//
//        customer.setNoticeRequest(noticeRequest);
//        customerList.add(customer);
//        corr.setCustomer(customerList);
//
//        corrList.setCorr(corr);
//
//        envelope.setCorrList(corrList);
//
//        CoverSheetDetail coverSheetDetail = new CoverSheetDetail();
//        coverSheetDetail.setCaseNum(dto.getCaseNum());
//        coverSheetDetail.setRecipientName(dto.getFullName());
////        coverSheetDetail.setStreet1(dto.getMailingAdd().getStreet1());
////        coverSheetDetail.setStreet2(dto.getMailingAdd().getStreet2());
////        coverSheetDetail.setCity(dto.getMailingAdd().getCity());
////        coverSheetDetail.setState(dto.getMailingAdd().getState());
////        coverSheetDetail.setZip4(dto.getMailingAdd().getZip4());
////        coverSheetDetail.setZip5(dto.getMailingAdd().getZip5());
//        coverSheetDetail.setSenderName("Health Insurance Processing Center");
//        coverSheetDetail.setSenderAddLine1("PO BOX 4405");
//        coverSheetDetail.setSenderAddLine2("Taunton, MA 02780-0419");
//
//        envelope.setCoverSheetDetail(coverSheetDetail);
//
//        envelopeArrayList.add(envelope);
//        envelopeList.setEnvelope(envelopeArrayList);
//
//        b.setEnvelopeList(envelopeList);
//
//        b.setEnvelopeList(envelopeList);
//        envelopeArrayList.add(envelope);
//        envelopeList.setEnvelope(envelopeArrayList);
//
//        batchNotice.setBatch(b);
//        return batchNotice;
    }

    @Override
    public DocumentDetailsVO uploadFileToEDMS(byte[] byteArray) {
        FileInputStream fis = null;
        DocumentDetailsVO docVO = null;
        try {
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(byteArray);
            RestTemplate newRestTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList((MediaType.MULTIPART_FORM_DATA)));
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            Path tempFile = Files.createTempFile("upload-file", ".pdf");
            Files.write(tempFile, decodedBytes);
            FileSystemResource fs = new FileSystemResource(tempFile.toFile());
            MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
            requestMap.add("title", "TestDoc15678");
            requestMap.add("category_id", "260");
            requestMap.add("meta_data", "{\"Case Number\":123,\"Application Number\":123,\"Client Id\":124,\"Reference Number\":567,\"Transaction Number\":\"789\",\"Document Type\":\"CaseDocument\",\"Document Received Date – Begin Date\":\"02/22/2021\",\"Document Received Date – End Date\":\"02/26/2021\"}");
            requestMap.add("file", fs);
            final ResponseEntity<DocumentDetailsVO> responseFromEDMS = newRestTemplate.exchange("http://localhost:8086/co/api/documentHandler/uploadDocument",
                    HttpMethod.POST, new HttpEntity<>(requestMap, headers), DocumentDetailsVO.class);
            System.out.println("responseFromEDMS : " + responseFromEDMS.getBody());
           // docVO = docService.getDocumentDetailsFromUpload(responseFromEDMS);
            if(null != responseFromEDMS.getBody())
                return responseFromEDMS.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertDocumentDetails(DocumentDetailsVO docVO) {
        InDisDocMaster inDisDoc = new InDisDocMaster();
        inDisDoc.setDocuedgeDocumentId(docVO.getDocumentId());
        inDisDoc.setCaseNum(docVO.getCaseNumber());
        inDisDoc.setAppNum(String.valueOf(docVO.getApplicationNumber()));
        inDisDoc.setTransactionId(docVO.getTransactionNumber());
        //DocType corresponding to CaseDocument doesn't exist
        inDisDoc.setDocType("10");
        inDisDoc.setDocId(2L);
        inDisDoc.setDelinkInd('N');
        inDisDoc.setIndvId(Long.parseLong("1024124621"));
        inDisDoc.setDocUploadType('C');
        inDisDoc.setProcessFlag('P');
        inDisDoc.setSource("IES");
        inDisDoc.setEntryDt(docVO.getDocumentReceivedDateBeginDate());
        inDisDocMasterRepo.save(inDisDoc);
    }
}