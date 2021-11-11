package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.*;
import com.deloitte.nextgen.dto.vo.CoManualDataVO;
import com.deloitte.nextgen.entities.*;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.mapper.CoManualDataMapper;
import com.deloitte.nextgen.mapper.CoManualFieldsMapping;
import com.deloitte.nextgen.mapper.FwDataElementListMapping;
import com.deloitte.nextgen.repository.CoRequestHistoryRepository;
import com.deloitte.nextgen.service.CoGenerateManualService;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import com.deloitte.nextgen.util.DisableCPCButtonDocs;
import com.deloitte.nextgen.util.ManualCorrespondenceManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CoGenerateManualServiceImpl implements CoGenerateManualService {

    @Autowired
    ManualCorrespondenceManager manualCorrespondenceManager;

    @Autowired
    CoRequestHistoryRepository requestHistoryRepository;

    final CoManualFieldsMapping coManualFieldsMapping;
    final FwDataElementListMapping fwDataElementListMapping;

    @Autowired
    CoDAOServices coDAOServices;

    @Autowired
    CoManualDataMapper coManualDataMapper;

    private static final String AUTHDATE400 = "authDate400";

    @Override
    public List<CoGenerateManualDTO> saveManualCorrDetails(CoGenerateManualDTO generateManualReqDTO) throws Exception {
        final COCorrespondence coCorrespondence = new COCorrespondence();
        final DisableCPCButtonDocs disableCPCButtonDocs = new DisableCPCButtonDocs();
        boolean status;
        if (generateManualReqDTO != null) {

            coCorrespondence.setDraftSwitch('Y');
            // does save as draft
            if (!disableCPCButtonDocs.disableCPCButtonPageIdList
                    .contains(generateManualReqDTO.getPageId())) {
                coCorrespondence.setPrintMode('B');
            }

            if ((generateManualReqDTO.getAppealId() != null)
                    && (generateManualReqDTO.getAppealId()
                    .length() != 0)) {
                coCorrespondence.setMiscParameters((generateManualReqDTO.getAppealId()).trim());
            }

            final String docId = generateManualReqDTO.getDocId();
            coCorrespondence.setDocId(docId);
            final String docIdSubstring = docId.substring(3);
            coCorrespondence.setFormData((HashMap<String, String>) generateManualReqDTO.getRequest());

            coCorrespondence.setCaseAppFlag(CoConstants.CASE);
            coCorrespondence.setCaseAppNumber(generateManualReqDTO.getCaseAppNumber());

            coCorrespondence.setActionValue(CoConstants.SAVEDATA);

            final CoManualFieldsDTO[] fields = generateManualReqDTO.getCoManualFieldsMap().get("manualFields") != null ? generateManualReqDTO.getCoManualFieldsMap().get("manualFields")
                    : null;
            final CoManualFieldsDTO[] areas = generateManualReqDTO.getCoManualFieldsMap().get("manualAreas") != null ? generateManualReqDTO.getCoManualFieldsMap().get("manualAreas")
                    : null;
            final FwDataElementListDTO[] fieldsFw = generateManualReqDTO.getFwDataElementListMap().get("fieldsFw") != null ? generateManualReqDTO.getFwDataElementListMap().get("fieldsFw")
                    : null;
            final FwDataElementListDTO[] areasFw = generateManualReqDTO.getFwDataElementListMap().get("areasFw") != null ? generateManualReqDTO.getFwDataElementListMap().get("areasFw")
                    : null;
            status = manualCorrespondenceManager.saveFieldData(fields, areas, coCorrespondence, fieldsFw, areasFw);

            generateManualReqDTO.setResponse(status);
            coCorrespondence.setActionValue(CoConstants.SAVEDATA);

            coCorrespondence.setCoReqSeq(0L);
            coCorrespondence.setCoRptSeq(0L);
        }

        ArrayList<CoGenerateManualDTO> coGenerateManualRespDTOList = new ArrayList<>();
        coGenerateManualRespDTOList.add(generateManualReqDTO);
        //coGenerateManualRespDTOList.add(coGenerateManualRespDTO);
        return coGenerateManualRespDTOList;
    }

    @Override
    public String manualCorr(CoGenerateManualDTO generateManualReqDTO) throws Exception {
        COCorrespondence coCorrespondence = new COCorrespondence();
        Long indivId = generateManualReqDTO.getIndivId();
        String caseAppNum = generateManualReqDTO.getCaseAppNumber() != null ? String.valueOf(generateManualReqDTO.getCaseAppNumber()) : generateManualReqDTO.getCaseAppNumber();
        coCorrespondence.setCaseAppNumber(caseAppNum);
        coCorrespondence.setIndvId(indivId);
        coCorrespondence.setCaseAppFlag(CoConstants.CASE);
        String docType =generateManualReqDTO.getDocId();
        if (docType != null) {
            coCorrespondence.setDocType(docType.charAt(0));
        }
        if (generateManualReqDTO.getAppealId() != null
                && generateManualReqDTO.getAppealId().length() != 0) {
            coCorrespondence.setMiscParameters(generateManualReqDTO.getAppealId().trim());
        }
        if (generateManualReqDTO.getAppealId() != null) {
            coCorrespondence.setMiscParameters(generateManualReqDTO.getAppealId());
        }
        coCorrespondence.setDocId(generateManualReqDTO.getDocId());
        final String docId = coCorrespondence.getDocId();
        final String docIdSubstring = docId.substring(3);
        Map<String,String> request;
        request=generateManualReqDTO.getRequest();
        if(null==coCorrespondence.getGenerateDate()){
            coCorrespondence.setGenerateDate(CoUtil.getCurrentDate());
        }

        if(CoConstants.FGG962.equals(docId)){
           coCorrespondence.setFomData1(getDataMap(docId, docIdSubstring,request));
        }else if(CoConstants.FGG400.equals(docId)){
            String providerNameValue400 = "providerNameValue400";
            String fdlAmtValue400 ="fdlAmtValue400";
            HashMap formData = (HashMap) getDataMap(docId, docIdSubstring, request);

            includeDateOfService(request, formData);
            if(formData!=null && request.get(providerNameValue400) != null && StringUtils.isNotBlank(request.get(providerNameValue400))){
                String providerNameValue= request.get(providerNameValue400);
                providerNameValue = providerNameValue.replace("&amp;nbsp;", " ");
                formData.put("providerName", providerNameValue);
            }
            if(formData!=null && request.get(fdlAmtValue400) != null && !request.get(fdlAmtValue400).isEmpty() ){
                String fdlAmtValue= request.get(fdlAmtValue400);
                fdlAmtValue = fdlAmtValue.replace("&amp;nbsp;", " ");
                formData.put("fdlAmt", fdlAmtValue);
            }

            coCorrespondence.setFormData(formData);
        }else{
            if(!(docId.equals(CoConstants.FGG551)||docId.equals(CoConstants.FGGA0003)||docId.equals(CoConstants.FGG5460)
                    ||docId.equals(CoConstants.FGG297M))){
                coCorrespondence.setFormData(getDataMap(docId, docIdSubstring,
                        request));
            }
        }
        coCorrespondence.setActionValue(CoConstants.GENERATECO);
        final CoManualFieldsDTO[] fieldsDTO = generateManualReqDTO.getCoManualFieldsMap().get("manualFields");
        final CoManualFieldsDTO[] areasDTO = generateManualReqDTO.getCoManualFieldsMap().get("manualAreas");
        final FwDataElementListDTO[] fieldsFw = generateManualReqDTO.getFwDataElementListMap().get("fieldsFw");
        final FwDataElementListDTO[] areasFw = generateManualReqDTO.getFwDataElementListMap().get("areasFw");
        coCorrespondence.setDraftSwitch('N');
        coCorrespondence.setCoReqSeq(0L);
        coCorrespondence.setCoRptSeq(0L);
        final String preview = generateManualReqDTO.getPreview();
        String xmlString;
        if (StringUtils.isNotEmpty(preview)
                && "true".equalsIgnoreCase(preview)) {
            xmlString = manualCorrespondenceManager.generateCorrespondence(generateManualReqDTO,fieldsDTO, areasDTO,fieldsFw,areasFw,coCorrespondence);
        } else if (CoConstants.ZERO_STRING.equals(coCorrespondence.getCaseAppNumber())) {
            xmlString = manualCorrespondenceManager.initiateLocalPrintNoMark(fieldsDTO, areasDTO,generateManualReqDTO,coCorrespondence);
        } else {
            xmlString = manualCorrespondenceManager.initiateLocalPrint(fieldsDTO, areasDTO,coCorrespondence,generateManualReqDTO);
        }
        return xmlString;
    }

    @Override
    public CoGenerateManualDTO initialize(CoGenerateManualDTO coGenerateManualDTO) throws Exception {
        return initializeData(coGenerateManualDTO.getDocId(),coGenerateManualDTO.getCoReqSeq(), coGenerateManualDTO.getParentPageId());
    }

    private CoGenerateManualDTO initializeData(String docId, Long reqSeq, String parentPageId) throws Exception {
        final List<CoManualFields> results = coDAOServices.getManualFields(docId);
        FwDataElementList fwDataElementListCargo;
        CoManualFields manualFieldsCargo;
        List<CoManualFieldsDTO> fields = new ArrayList<>();
        List<CoManualFieldsDTO> areas = new ArrayList<>();
        List<FwDataElementListDTO> fieldsFw =new ArrayList<>();
        List<FwDataElementListDTO> areasFw =new ArrayList<>();
        CoManualFieldsDTO coManualFieldsDTO;
        FwDataElementListDTO fwDataElementListDTO;
        for (CoManualFields result : results) {
            manualFieldsCargo = result;
            if (manualFieldsCargo != null && manualFieldsCargo.getDeElementId() > 0) {
                fwDataElementListCargo = coDAOServices.getManualFields(manualFieldsCargo.getDeElementId()).get(0);
                coManualFieldsDTO = coManualFieldsMapping.toDto(manualFieldsCargo);
                fwDataElementListDTO = fwDataElementListMapping.toDto(fwDataElementListCargo);
                if (fwDataElementListCargo.getDeWidth() < 10000) {
                    fields.add(coManualFieldsDTO);
                    fieldsFw.add(fwDataElementListDTO);
                } else {
                    areas.add(coManualFieldsDTO);
                    areasFw.add(fwDataElementListDTO);
                }

            }
        }
        Map<String,CoManualFieldsDTO[]> stringMap = new HashMap<>();
        stringMap.put("manualFields", fields.toArray(new CoManualFieldsDTO[0]));
        stringMap.put("manualAreas", areas.toArray(new CoManualFieldsDTO[0]));
        Map<String,FwDataElementListDTO[]> fwMap = new HashMap<>();
        fwMap.put("fieldsFw", fieldsFw.toArray(new FwDataElementListDTO[0]));
        fwMap.put("areasFw", areasFw.toArray(new FwDataElementListDTO[0]));

        CoGenerateManualDTO generateManualDTO = new CoGenerateManualDTO();
        generateManualDTO.setCoManualFieldsMap(stringMap);
        generateManualDTO.setFwDataElementListMap(fwMap);
        generateManualDTO.setParentPageId(parentPageId);
        if(parentPageId.equals("COVPD")) {
            generateManualDTO.setCoManualData(this.fetchManualData(generateManualDTO, reqSeq));
        }
        return generateManualDTO;
    }

    private CoManualDataVO fetchManualData(CoGenerateManualDTO generateManualDTO, Long reqSeq) {
        List<CoManualData> coManualData = null;
        try {
            coManualData = coDAOServices.getManualData(reqSeq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!CoUtil.isEmpty(coManualData)){
            List<CoManualDataDTO> coManualDataDTO = coManualDataMapper.toDto(coManualData);
            CoManualDataVO manualDataVO = new CoManualDataVO();
            // TODO: Need to change the logic to populate the Additional info screen
            manualDataVO.setNoticeSalutation(coManualDataDTO.get(0).getFieldContent());
            manualDataVO.setCoPurposeOfNotice(coManualDataDTO.get(1).getFieldContent());
            manualDataVO.setCoPolicyManualReference(coManualDataDTO.get(2).getFieldContent());
            System.out.println("gmanualDataVO is "+manualDataVO);

            return manualDataVO;
        }

        return null;
    }

    private Map getDataMap(final String docId, final String docIdSubstring,
                           final Map request) {
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

    private void includeDateOfService(final Map request, HashMap formData) {
        if (formData != null
                && StringUtils.isNotBlank((String) request
                .get(AUTHDATE400))) {
            String authDate400Value = (String) request
                    .get(AUTHDATE400);
            authDate400Value = authDate400Value.replace(
                    CoConstants.SLASH, CoConstants.EMPTY_STRING);
            formData.put(AUTHDATE400, authDate400Value);
        }
    }

    @Override
    public NoticeCustomer getNoticeRequest(CoGenerateManualDTO dto) {
        NoticeCustomer noticeCustomer = new NoticeCustomer();
        Customer customer = new Customer();
        NoticeRequest noticeRequest = new NoticeRequest();
        MetaData metaData = new MetaData();
        FormData formData = new FormData();
//		MassHealthMedicaid MASSHealthMedicaid = new MassHealthMedicaid();


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
        metaData.setCaseNum(Long.parseLong(dto.getCaseAppNumber()));
        metaData.setGoGreen(dto.getGoGreen());
        metaData.setAgencyCode(dto.getAgencyCode());
        metaData.setAgencyName(dto.getAgencyName());

        metaData.setClientName(dto.getFullName());
        metaData.setRequestDate(CoUtil.getCurrentDate().toString());
        metaData.setHohId(dto.getIndivId());
        metaData.setSecurityFlag(dto.getSecurityFlag());

        noticeRequest.setMetaData(metaData);

        formData.setCaseName(dto.getFullName());
        formData.setCaseNum(Long.parseLong(dto.getCaseAppNumber()));
        formData.setClientName(dto.getFullName());
        formData.setClientId(dto.getIndivId());
        formData.setSSN(dto.getSSN());
        formData.setClientDOB(dto.getClientDOB());
        headOfHouse.setHOHName(dto.getFullName());
        headOfHouse.setHOHId(dto.getIndivId());
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

    private Long generateNoticeRequestId() {
        return ThreadLocalRandom.current().nextLong(9999999);
    }

    public BatchNotice getBatchNotice(CoGenerateManualDTO dto) throws FwApplicationException {
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
//        metaData.setTemplateId(dto.getTemplateId());
        metaData.setTemplateId("MA-MANUAL-001");
        metaData.setNoticeType("");
        metaData.setMailDate(dto.getMailDate());
        metaData.setCorrNum(0L);
        metaData.setWatermark(dto.getWatermark());
        metaData.setSecurityFlag(dto.getSecurityFlag());
        metaData.setFormNo(dto.getFormNo());
        metaData.setFormVersion(dto.getFormVersion());
        metaData.setFormTitle(dto.getFormTitle());
        metaData.setPreferredLanguage(dto.getPreferredLanguage());
        metaData.setEnvelopeId("");
        metaData.setCaseNum(dto.getCaseNum());
        metaData.setGoGreen(dto.getGoGreen());
        metaData.setAgencyCode(dto.getAgencyCode());
        metaData.setCaseworkerName(dto.getFullName());
        metaData.setWorkerName("Long Term Care Medicaid");
        metaData.setWorkeremailAddress("laltc.processingcenter@la.gov");
        metaData.setWorkerPhoneNumber(18002300690L);
        metaData.setWorkerFaxNumber(1-225-389-8019L);
        metaData.setDocumentId(dto.getDocumentId());
        metaData.setSalutation(dto.getRequest().get("noticeSalutation"));
        metaData.setNoticeDate(dto.getNoticeDate());
        metaData.setPhoneNumber(0L);
        metaData.setReciepientSeqNum(dto.getReciepientSeqNum());
        metaData.setDisplayFlagForPreviewSwitch(false);
        metaData.setEdmsSw('Y');
        metaData.setCommunicationMode('P');
        metaData.setOrderNum(3L);
        metaData.setEmailRecipientData("");
        metaData.setRequestId(CoUtil.generateNoticeRequestId());
        metaData.setPrintVendor("P1");
        noticeRequest.setMetaData(metaData);
        noticeRequest.setAction(dto.getAction());

        FormData formData = new FormData();
        formData.setCaseName(dto.getFullName());
        formData.setCaseNum(dto.getCaseNum());
        formData.setClientName(dto.getFullName());
        formData.setClientId(dto.getIndivId());
        formData.setSSN(dto.getSSN());
        formData.setClientDOB(dto.getClientDOB());
        formData.setCaseWorkerID("");

        HeadOfHouse headOfHouse = new HeadOfHouse();
        headOfHouse.setHOHName(dto.getFullName());
        headOfHouse.setHOHId(dto.getIndivId());

        formData.setHeadOfHouse(headOfHouse);
        formData.setMailingAdd(dto.getMailingAdd());
        formData.setSystemDate(CoUtil.getCurrentDate());
        MassHealthMedicaid massHealthMedicaid = new MassHealthMedicaid();
        List<Individual> individualList = new ArrayList<>();
        Individual individual = new Individual();
        individual.setName("John Lewin");
        individual.setMemberId("123");
        individual.setIndividualDOB(CoUtil.stringToTimestamp("12/03/1980"));
        individual.setNoticeSalutation(dto.getRequest().get("noticeSalutation"));
        individual.setNoticeContent(dto.getRequest().get("coPurposeOfNotice"));
        individual.setPolicyReference(dto.getRequest().get("coPolicyManualReference"));
        individual.setProgram("MSPA");
        individual.setEffectiveDate(CoUtil.stringToTimestamp("01/01/2020"));
        individual.setClosureDate(CoUtil.stringToTimestamp("06/30/2020"));
        individual.setProgramClosureReason("Age");

        individualList.add(individual);

//        individual.setName("John Lewin");
//        individual.setMemberId("123");
//        individual.setIndividualDOB(CoUtil.stringToTimestamp("12/03/1980"));
//        individual.setNoticeSalutation(dto.getRequest().get("noticeSalutation"));
//        individual.setNoticeContent(dto.getRequest().get("coPurposeOfNotice"));
//        individual.setPolicyReference(dto.getRequest().get("coPolicyManualReference"));
//        individual.setProgram("MFPA");
//        individual.setEffectiveDate(CoUtil.stringToTimestamp("01/07/2020"));
//        individual.setClosureDate(CoUtil.stringToTimestamp("06/30/2020"));
//        individual.setProgramClosureReason("");
//
//        individualList.add(individual);

        massHealthMedicaid.setIndividual(individualList);
        massHealthMedicaid.setHouseholdsize("");
        massHealthMedicaid.setHHFPLLimit("");
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

        formData.setMASSHealthMedicaid(massHealthMedicaid);


        noticeRequest.setFormData(formData);

        customer.setNoticeRequest(noticeRequest);
        customerList.add(customer);
        corr.setCustomer(customerList);

        corrList.setCorr(corr);

        envelope.setCorrList(corrList);

        CoverSheetDetail coverSheetDetail = new CoverSheetDetail();
        coverSheetDetail.setCaseNum(dto.getCaseNum());
        coverSheetDetail.setRecipientName(dto.getFullName());
        coverSheetDetail.setStreet1(dto.getMailingAdd().getStreet1());
        coverSheetDetail.setStreet2(dto.getMailingAdd().getStreet2());
        coverSheetDetail.setCity(dto.getMailingAdd().getCity());
        coverSheetDetail.setState(dto.getMailingAdd().getState());
        coverSheetDetail.setZip4(dto.getMailingAdd().getZip4());
        coverSheetDetail.setZip5(dto.getMailingAdd().getZip5());
        coverSheetDetail.setSenderName("Health Insurance Processing Center");
        coverSheetDetail.setSenderAddLine1("PO BOX 4405");
        coverSheetDetail.setSenderAddLine2("Taunton, MA 02780-0419");

        envelope.setCoverSheetDetail(coverSheetDetail);

        envelopeArrayList.add(envelope);
        envelopeList.setEnvelope(envelopeArrayList);

        b.setEnvelopeList(envelopeList);

        b.setEnvelopeList(envelopeList);
        envelopeArrayList.add(envelope);
        envelopeList.setEnvelope(envelopeArrayList);

        batchNotice.setBatch(b);
        return batchNotice;
    }

}
