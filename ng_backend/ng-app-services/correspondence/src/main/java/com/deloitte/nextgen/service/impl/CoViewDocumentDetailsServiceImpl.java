package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.DocumentUpdateDTO;
import com.deloitte.nextgen.dto.entities.ViewDocumentDetailsDTO;
import com.deloitte.nextgen.dto.vo.ClientsVO;
import com.deloitte.nextgen.dto.vo.ViewDocumentDetailsVO;
import com.deloitte.nextgen.entities.*;
import com.deloitte.nextgen.repository.DcIndvRepository;
import com.deloitte.nextgen.repository.InDisDocMasterRepository;
import com.deloitte.nextgen.repository.T1004AppIndvRepository;
import com.deloitte.nextgen.repository.VArApplicationIndvRepository;
import com.deloitte.nextgen.service.CoViewDocumentDetailsService;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import com.deloitte.nextgen.util.DocumentTaskServiceBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoViewDocumentDetailsServiceImpl implements CoViewDocumentDetailsService {

//    ReferenceTable referenceTableManager;

    @Autowired
    DcIndvRepository dcIndvRepository;

    @Autowired
    T1004AppIndvRepository t1004AppIndvRepository;

    @Autowired
    VArApplicationIndvRepository vArApplicationIndvRepository;

    @Autowired
    CorrespondenceServices correspondenceServices;

    @Autowired
    InDisDocMasterRepository inDisDocMasterRepository;

    @Autowired
    DocumentTaskServiceBO documentTaskServiceBO;

    @Autowired
    CoDAOServices coDAOServices;

    @Transactional
    public ViewDocumentDetailsVO metaDataChange(ViewDocumentDetailsDTO dto) throws Exception {
        Long disDocMstrSeqNo = dto.getReferenceNo();
        String caseAppNum = dto.getCaseAppNum();
        String documentType = "";
        Long ssn = 0L;
        Long indvId = dto.getIndvId();
        String submitType = dto.getActionType();
        RestTemplate restTemplate = new RestTemplate();
        ViewDocumentDetailsVO vo = new ViewDocumentDetailsVO();
        ArrayList<String> messageCodes = new ArrayList<>();
        boolean isDataSaved = false;

        if(submitType == null) {
            documentType = dto.getOriginalDocumentType();
            ssn = getSsn(indvId,caseAppNum);
        }

        if(CoConstants.UPDATE_METADATA.equalsIgnoreCase(submitType)) {

            documentType = StringUtils.isNotEmpty( dto.getModifiedDocumentType()) ?
                    dto.getModifiedDocumentType() : dto.getOriginalDocumentType();

            // TODO: uncomment below line whenever Reference Table Manager is accessible
//        String indvCaseInd = referenceTableManager.getValueByColumn(
//                "RT_DISDOCTYPE_MV" , documentType,
//                "INDIVIDUALORCASEINDICATOR");
            String indvCaseInd = "I";
            ssn =  dto.getModifiedSsn() != null ? dto.getModifiedSsn() : dto.getOriginalSsn();
            Object[] isValidInput = isValidInput(indvCaseInd, ssn,caseAppNum);
            int commit = 0;
            if((Boolean) isValidInput[0]) {
                List<InDisDocMaster> inDisDocMasterList =
                        correspondenceServices.getAllLinkedRecord(disDocMstrSeqNo);

                for (InDisDocMaster inDisDocMaster : inDisDocMasterList) {
                    if (StringUtils.isNotEmpty(documentType)) {
                        inDisDocMaster.setDocType(documentType);
                        if (StringUtils.isNumeric(caseAppNum)) {
                            inDisDocMaster.setIndvId((Long) isValidInput[1]);
                        } else {
                            inDisDocMaster.setIndvSeqNum((Long) isValidInput[1]);
                        }
                        inDisDocMaster.setDisUpdInd('N');
                        if ("IESUK".equalsIgnoreCase(inDisDocMaster.getSource())) {
                            inDisDocMaster.setCpHistoryFlag('N');
                        }
                        inDisDocMaster.setSource("IESUW");
                    }
                    inDisDocMasterRepository.save(inDisDocMaster);
                    isDataSaved = true;
                }
                if(isDataSaved) {
                    //calling for Docu Edge Meta Data Change action.
                    DocumentUpdateDTO documentUpdateDTO = getDocumentUpdateDTO(dto, isValidInput);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<DocumentUpdateDTO> httpEntity = new HttpEntity<>(documentUpdateDTO, headers);
                    ResponseEntity<String> response = restTemplate
                            .postForEntity("http://15.207.142.199/co/api/documentHandler/documentDetailsUpdate",
                            httpEntity, String.class);
                    String responseString = response.getBody();
                    if( responseString != null && responseString.equals("\"success\"")) {
                        commit = 1;
                    } else {
                        throw new Exception("cannot update the document details in DocuEdge Server");
                    }
                    InDisDocMaster inDisDocMaster = inDisDocMasterList.get(0);
                    inDisDocMaster.setDisUpdInd('Y');
                    inDisDocMasterRepository.save(inDisDocMaster);
                } else {
                    log.error("Update a record into IN_DIS_DOC_MASTER table failed:");
                }

            } else {
                if(CoConstants.C.equalsIgnoreCase(indvCaseInd)){
                    messageCodes.add("The SSN - "+ssn+" entered is not same as individual's primary record.");
                }else{
                    messageCodes.add("DM102, Invalid ssn/document type");
                }
                log.error("Invalid ssn/document type");
                documentType = dto.getOriginalDocumentType();
                ssn = dto.getOriginalSsn();
            }

            if(commit == 1) {
                messageCodes.add("Successfully updated");
            }
//            else {
//                messageCodes.add("Something went wrong");
//            }
            vo.setMessageCodes(messageCodes);
        }
        vo.setDisDocMstrSeqNo(dto.getDisDocMstrSeqNum());
        vo.setOriginalDocumentType(documentType);
        vo.setCaseAppNum(caseAppNum);
        vo.setSsn(ssn);
        return vo;
    }

    @Override
    public ViewDocumentDetailsVO delinkAndLink(ViewDocumentDetailsDTO dto) throws Exception {
        InDisDocMaster insertDisDocCargo;
        ArrayList<String> messageCodes = new ArrayList<>();
        int commitFlag = 0;
        String caseAppNum;
        Long personId;
        Long documentId = dto.getReferenceNo();
        String submitType = dto.getActionType();
        List<InDisDocMaster> inDisDocMasterList;
        String documentDescription = dto.getDocumentDescription();
        String documentTypeCd = dto.getDocumentTypeCd();
        IndividualInformationCargo indvInfoCargo;
        ArrayList<DocumentDynaCargo> documentDynaCargoList = new ArrayList<>();

        if (dto.getPageId().equals(CoConstants.PAGE_ID_DMVED)) {
            commitFlag = 1;
        } else if (dto.getPageId().equals(CoConstants.PAGE_ID_DMVLD)) {
            if(submitType.equals(CoConstants.RETRIEVE)) {
                ViewDocumentDetailsVO vo;
                vo = fetchClientDetails(dto);
                return vo;
            } else if (submitType.equals(CoConstants.DIS_DELINK)
                    || submitType.equals(CoConstants.DIS_LINK)) {

                inDisDocMasterList = correspondenceServices.getdisId(documentId);
                if (submitType.equals(CoConstants.DIS_DELINK)) {
                    insertDisDocCargo = inDisDocMasterList.get(0);
                    insertDisDocCargo.setDelinkInd(CoConstants.YES);
                    insertDisDocCargo.setDisUpdInd(CoConstants.NO);
                    if ("IESUK".equalsIgnoreCase(insertDisDocCargo.getSource())) {
                        insertDisDocCargo.setCpHistoryFlag(CoConstants.NO);
                    }
                    if (inDisDocMasterList.get(0).getArchiveDt() == null) {
                        insertDisDocCargo.setArchiveDt((Timestamp) DateUtils.parseDate("12-31-2999",
                                "MM-dd-yyyy"));
                    }
                    inDisDocMasterRepository.save(insertDisDocCargo);
                    commitFlag = 1;
                } else if (submitType.equals(CoConstants.DIS_LINK)) {
                    boolean isInvdModified;
                    insertDisDocCargo = new InDisDocMaster();
                    InDisDocMaster temp = inDisDocMasterList.get(0);
                    insertDisDocCargo.setDocId(temp.getDocId());
                    insertDisDocCargo.setDocType(temp.getDocType());
                    caseAppNum = dto.getCaseNo() != null ? dto.getCaseNo() : dto.getApplication();
                    personId = dto.getClientId();

                    if (Pattern.matches("[0-9]+", caseAppNum)) {
                        insertDisDocCargo.setCaseNum(Long
                                .parseLong(caseAppNum));
                        insertDisDocCargo.setDocUploadType('C');
                        insertDisDocCargo.setIndvId(personId);
                    } else {
                        insertDisDocCargo.setAppNum(caseAppNum);
                        insertDisDocCargo.setDocUploadType('A');
                        insertDisDocCargo.setIndvSeqNum(personId);
                    }
                    isInvdModified = isInvdModifed(temp, caseAppNum, personId);


                    insertDisDocCargo
                            .setTransactionId(temp.getTransactionId());
                    insertDisDocCargo.setEntryDt(CoUtil.getCurrentDate());
                    insertDisDocCargo.setProcessFlag(temp.getProcessFlag());
                    insertDisDocCargo.setDelinkInd(CoConstants.NO);
                    if ("IESUK".equalsIgnoreCase(temp.getSource())) {
                        insertDisDocCargo.setCpHistoryFlag(CoConstants.NO);
                    }

                    insertDisDocCargo.setTaskNum(temp.getTaskNum());
                    insertDisDocCargo.setFilePath(temp.getFilePath());

                    insertDisDocCargo.setSource(temp.getSource());
                    insertDisDocCargo.setCreateUserId(temp.getCreateUserId());
//                    insertDisDocCargo.setUpdateUserId(dto.getUserId());
//                    insertDisDocCargo.setUpdateDt(CoUtil.getCurrentDate());

                    if (!documentTaskServiceBO
                            .isDuplicate(insertDisDocCargo)) {
                        insertDisDocCargo.setDisUpdInd(CoConstants.NO);
                        if (temp.getArchiveDt() == null) {
                            insertDisDocCargo.setArchiveDt((Timestamp) DateUtils.parseDate("12-31-2999",
                                    "MM-dd-yyyy"));
                        } else {
                            insertDisDocCargo.setArchiveDt(temp.getArchiveDt());
                        }
                        insertDisDocCargo.setHistorySeq(temp.getHistorySeq());
                        if (isInvdModified) {
                            inDisDocMasterList = correspondenceServices.getAllLinkedRecord(documentId);
                            for (InDisDocMaster inDisDocMasterCargo : inDisDocMasterList) {
                                inDisDocMasterCargo.setDelinkInd(CoConstants.YES);
                                inDisDocMasterCargo.setDisUpdInd(CoConstants.NO);
                                if ("IESUK".equalsIgnoreCase(inDisDocMasterList.get(0).getSource())) {
                                    insertDisDocCargo.setCpHistoryFlag(CoConstants.NO);
                                }
                                if (inDisDocMasterList.get(0).getArchiveDt() == null) {
                                    insertDisDocCargo.setArchiveDt((Timestamp) DateUtils.parseDate("12-31-2999",
                                            "MM-dd-yyyy"));
                                }
                                inDisDocMasterRepository.save(inDisDocMasterCargo);
                            }
                        }

                        try {
                            inDisDocMasterRepository.save(insertDisDocCargo);
                            commitFlag = 1;
                        } catch (Exception e) {
                            log.error(e.getMessage());
                            commitFlag = -1;
                        }

                    } else {
                        commitFlag = 2;
                    }
                }
                if (commitFlag == 1) {
                    String str = submitType.equals(CoConstants.DIS_LINK) ?
                            CoConstants.DIS_LINK : CoConstants.DIS_DELINK;
                    messageCodes.add("SuccessFully completed the " + str + " operation");
                } else if (commitFlag == 2) {
                    messageCodes.add("Duplicate Record, " + CoConstants.DUPLICATE_RECORD_CD);
                } else if (commitFlag == -1) {
                    messageCodes.add("Update Failed, " + CoConstants.UPDATE_FAILED_CD);
                    log.error("Insert a record into IN_DIS_DOC_MASTER table failed:");
                }

                ViewDocumentDetailsVO vo = new ViewDocumentDetailsVO();
                vo.setMessageCodes(messageCodes);
                return vo;
            }
        }

        if(commitFlag == 1) {
            inDisDocMasterList = correspondenceServices.getAllLinkedRecord(documentId);
            if(inDisDocMasterList != null && inDisDocMasterList.size() > 0) {
                DocumentDynaCargo vo;
                for (InDisDocMaster temp : inDisDocMasterList) {
                    vo = new DocumentDynaCargo();
                    documentTypeCd = temp.getDocType();
                    vo.setDId(String.valueOf(temp.getDisDocMstrSeqNum()));
                    vo.setRecvdDate(CoUtil.dateToString(temp.getEntryDt()));

                    if (null != temp.getDateOfReceipt()) {
                        vo.setDocRecvdDate(
                                CoUtil.dateToString(temp.getDateOfReceipt()));
                    }

                    vo.setCaseAppNum((temp.getCaseNum() == null)
                            ? temp.getAppNum() : String.valueOf(temp.getCaseNum()));

                    if(temp.getIndvId()!=null){
                        if (temp.getIndvId() > 0) {
                            indvInfoCargo = correspondenceServices
                                    .getIndvDetails(temp
                                            .getIndvId());

                            vo.setPersonId(Long
                                    .toString(indvInfoCargo.getIndividualID()));
                            vo.setClientName(indvInfoCargo
                                    .getName());
                        } else {

                            final List<T1004AppIndv> t1004AppIndvList = coDAOServices
                                    .findByAppNumAndIndvSeq(temp.getAppNum(), temp.getIndvSeqNum());

                            if (t1004AppIndvList.size() == 0) {
                                List<VArApplicationIndv> vArApplicationIndvList = coDAOServices
                                        .getAppIndvDtlsByIndvIdAppNum(temp.getIndvSeqNum(), temp.getAppNum());
                                vo.setPersonId(Long.toString(vArApplicationIndvList.get(0).getT2IndvId()));
                                vo.setClientName(vArApplicationIndvList.get(0).getFirstName()
                                        + " "
                                        + vArApplicationIndvList.get(0).getLastName());
                            } else {
                                vo.setPersonId(Long.toString(t1004AppIndvList.get(0)
                                        .getIndvSeqNum()));
                                vo.setClientName(t1004AppIndvList.get(0).getFstNam() + " "
                                        + t1004AppIndvList.get(0).getLastNam());
                            }

                        }
                    }
                    documentDynaCargoList.add(vo);
                }

            }
        }
        ViewDocumentDetailsVO vo = new ViewDocumentDetailsVO();
        vo.setDocumentId(documentId);
        vo.setDocumentType(documentDescription);
        vo.setDocumentTypeCd(documentTypeCd);
        vo.setPageId("DMVLD");
        vo.setDocumentDynaCargos(documentDynaCargoList);
        return  vo;
    }

    private ViewDocumentDetailsVO fetchClientDetails(ViewDocumentDetailsDTO dto) throws Exception {
        ViewDocumentDetailsVO vo = new ViewDocumentDetailsVO();
        String caseApplication = dto.getCaseAppNum();
        String docTypeCode = dto.getDocumentTypeCd();

        // TODO have to uncomment the below code, when ever we can access Reference Table
//        docTypeCode = FwReferenceTableManager.getValueByColumn(true, CoConstants.REF_TABLE_DISDOCTYPE, docTypeCode,
//                "INDIVIDUALORCASEINDICATOR");
        docTypeCode = "I";
        if (docTypeCode.equals(CoConstants.DOC_TYPE_CASEINDICATOR)) {
            // case level doc should be uploaded to hoh, so setting only hoh info
            if (Pattern.matches("[0-9]+", caseApplication)) {
                vo = setCaseHohInfo(caseApplication);
            }else {
                vo = setAppHohInfo(caseApplication);
            }
        } else if (docTypeCode.equals(CoConstants.DOC_TYPE_INDIVIDUALINDICATOR)) {
            // individual level doc can be uploaded to respective indv, so setting all indv of case/app, for user to select respective indv
            if (Pattern.matches("[0-9]+", caseApplication)) {
                vo = setCaseIndvsInfo(caseApplication);
            }else{
                vo = setAppIndvsInfo(caseApplication);
            }
        }
        return vo;
    }

    private ViewDocumentDetailsVO setAppIndvsInfo(String caseApplication) {
        ViewDocumentDetailsVO vo = new ViewDocumentDetailsVO();
        List<ClientsVO> clients = new ArrayList<>();
        final ArrayList<String> errorCodeList = new ArrayList<>();
        List<T1004AppIndv> t1004AppIndvList = coDAOServices.findByAppNum(caseApplication);
        if ((t1004AppIndvList != null)
                && (t1004AppIndvList.size() > 0)) {
            for (int i = 0; i < t1004AppIndvList.size(); i++) {
                ClientsVO cvo = new ClientsVO();
                String fullName = getFullName(
                        t1004AppIndvList.get(i).getFstNam(),
                        t1004AppIndvList.get(i).getMidInit(),
                        t1004AppIndvList.get(i).getLastNam(),
                        t1004AppIndvList.get(i).getSfxNam());
                cvo.setClientName(fullName);
                cvo.setClientId(t1004AppIndvList.get(i).getIndvSeqNum());
                clients.add(cvo);
            }
            vo.setClients(clients);
        } else if ((t1004AppIndvList == null)
                || (t1004AppIndvList.size() == 0)) {
            List<VArApplicationIndv> vArApplicationIndvList = null;
            vArApplicationIndvList =  coDAOServices
                    .getArApplicationIndvs(caseApplication);

            if ((vArApplicationIndvList != null)
                    && (vArApplicationIndvList.size() > 0)) {
                for (int i = 0; i < vArApplicationIndvList.size(); i++) {
                    ClientsVO cvo = new ClientsVO();
                    String fullName = getFullName(
                            vArApplicationIndvList.get(i).getFirstName(),
                            vArApplicationIndvList.get(i).getMidName(),
                            vArApplicationIndvList.get(i).getLastName(),
                            vArApplicationIndvList.get(i).getSufxName());
                    cvo.setClientName(fullName);
                    cvo.setClientId(vArApplicationIndvList.get(i)
                            .getT2IndvId());
                    clients.add(cvo);
                }
                vo.setClients(clients);
            } else {
                errorCodeList.add("DM758");
                vo.setMessageCodes(errorCodeList);
            }
        }
        return vo;
    }

    private ViewDocumentDetailsVO setCaseIndvsInfo( String caseApplication) {
        ViewDocumentDetailsVO vo = new ViewDocumentDetailsVO();
        List<ClientsVO> clients = new ArrayList<>();
        final ArrayList<String> errorCodeList = new ArrayList<>();
        List<VDcCaseIndvDetails> vDcCaseIndvDetailsList = coDAOServices.
                getAllIndvByCaseNum(Long.parseLong(caseApplication));
        if ((vDcCaseIndvDetailsList != null)
                && (vDcCaseIndvDetailsList.size() > 0)) {
            for (int i = 0; i < vDcCaseIndvDetailsList.size(); i++) {
                ClientsVO cvo = new ClientsVO();
                String fullName = getFullName(
                        vDcCaseIndvDetailsList.get(i).getFirstName(),
                        vDcCaseIndvDetailsList.get(i).getMidName(),
                        vDcCaseIndvDetailsList.get(i).getLastName(),
                        vDcCaseIndvDetailsList.get(i).getSufxName());
                cvo.setClientName(fullName);
                cvo.setClientId(vDcCaseIndvDetailsList.get(i).getT1IndvId());
                clients.add(cvo);
            }
            vo.setClients(clients);
        } else {
            errorCodeList.add("DM757");
            vo.setMessageCodes(errorCodeList);
        }
        return vo;
    }

    private ViewDocumentDetailsVO setAppHohInfo(String caseApplication) {
        ViewDocumentDetailsVO vo = new ViewDocumentDetailsVO();
        List<ClientsVO> clients = new ArrayList<>();
        final ArrayList<String> errorCodeList = new ArrayList<>();

        List<T1004AppIndv> t1004AppIndvList = coDAOServices.findByAppNum(caseApplication);
        if(t1004AppIndvList != null && t1004AppIndvList.size() > 0) {
            ClientsVO cvo = new ClientsVO();
            String fullName = getFullName(
                    t1004AppIndvList.get(0).getFstNam(),
                    t1004AppIndvList.get(0).getMidInit(),
                    t1004AppIndvList.get(0).getLastNam(),
                    t1004AppIndvList.get(0).getSfxNam());
            cvo.setClientName(fullName);
            cvo.setClientId(t1004AppIndvList.get(0).getIndvSeqNum());
            clients.add(cvo);
            vo.setClients(clients);
        } else if ((t1004AppIndvList == null)
                || (t1004AppIndvList.size() == 0)) {
            List<VArApplicationIndv> vArApplicationIndvList = null;
            vArApplicationIndvList = coDAOServices
                    .getArApplicationIndv(caseApplication);

            if ((vArApplicationIndvList != null)
                    && (vArApplicationIndvList.size() > 0)) {
                ClientsVO cvo = new ClientsVO();
                String fullName = getFullName(
                        vArApplicationIndvList.get(0).getFirstName(),
                        vArApplicationIndvList.get(0).getMidName(),
                        vArApplicationIndvList.get(0).getLastName(),
                        vArApplicationIndvList.get(0).getSufxName());
                cvo.setClientName(fullName);
                cvo.setClientId(vArApplicationIndvList.get(0).getT2IndvId());
                clients.add(cvo);
                vo.setClients(clients);
            } else {
                errorCodeList.add("DM758");
                vo.setMessageCodes(errorCodeList);
            }
        }
        return vo;
    }

    private ViewDocumentDetailsVO setCaseHohInfo(String caseApplication) {
        List<ClientsVO> clients = new ArrayList<>();
        final ArrayList<String> errorCodeList = new ArrayList<>();
        ViewDocumentDetailsVO vo = new ViewDocumentDetailsVO();
        List<DcHeadOfHousehold> dcHeadOfHouseholdList = coDAOServices
                .getHoHIndividual(Long.parseLong(caseApplication));
        if(dcHeadOfHouseholdList != null && dcHeadOfHouseholdList.size() > 0) {
            ClientsVO cvo = new ClientsVO();
            IndividualInformationCargo indvInfoCargo = correspondenceServices
                    .getIndvDetails(dcHeadOfHouseholdList.get(0).getIndvId());
            cvo.setClientName(indvInfoCargo.getName());
            cvo.setClientId(indvInfoCargo.getIndividualID());
            clients.add(cvo);
            vo.setClients(clients);
        } else {
            errorCodeList.add("DM757");
            vo.setMessageCodes(errorCodeList);
        }
        return vo;
    }

    /**
     * used to valid ssn
     * @param inDisDocMaster inDisDocMaster
     * @param caseAppNum caseAppNum
     * @param personId personId
     * @return boolean ssn
     * @throws NumberFormatException NumberFormatException
     * @throws Exception Exception
     */
    private boolean isInvdModifed(InDisDocMaster inDisDocMaster, String caseAppNum, Long personId) throws Exception {
        long ssn1;
        if(inDisDocMaster.getCaseNum()>0){
            IndividualInformationCargo indvInfoCargo = correspondenceServices
                    .getIndvDetails(inDisDocMaster.getIndvId());
            ssn1 = indvInfoCargo.getSsn();
        }else{
            ssn1 = getApplicationSsn(inDisDocMaster.getIndvSeqNum(),inDisDocMaster.getAppNum());
        }

        long ssn2 = getSsn(personId,caseAppNum);

        return ssn1 != ssn2;

    }

    /**
     * gets ssn for case/AppNum
     *
     * @param indvId indvId
     * @param caseAppNum caseAppNum
     * @return long long
     * @throws Exception Exception
     */
    private long getSsn(Long indvId, String caseAppNum) throws Exception {
        long ssn;
        try{
            if(StringUtils.isNumeric(caseAppNum)){
                IndividualInformationCargo indvInfoCargo = correspondenceServices
                        .getIndvDetails((indvId));

                ssn = indvInfoCargo.getSsn();
            }else{
                ssn = getApplicationSsn(indvId,caseAppNum);
            }
        } catch (Exception e) {
            log.error("Unable to get ssn " + e);
            throw new Exception(
                    "Unable to get ssn " + e );
        }

        return ssn;
    }


    /**
     * gets ssn for AppNum
     * @param indvId indvId
     * @param appNum appNum
     * @return long long
     * @throws NumberFormatException NumberFormatException
     * @throws Exception Exception
     */
    private long getApplicationSsn(Long indvId,String appNum) throws  Exception{
        long ssn = 0;
        List<T1004AppIndv> t1004AppIndvCargoCol = coDAOServices
                .findByAppNumAndIndvSeq(appNum, indvId);
        if(t1004AppIndvCargoCol.size() > 0){
            ssn =  Long.parseLong(t1004AppIndvCargoCol.get(0).getSsnNum());
        }else{
            List<VArApplicationIndv>  vArApplicationIndvCargo =  coDAOServices
                    .getAppIndvDtlsByIndvIdAppNum(indvId,appNum);
            if(vArApplicationIndvCargo.size() > 0){
                ssn =  vArApplicationIndvCargo.get(0).getSsn();
            }

        }
        return ssn;
    }

    private DocumentUpdateDTO getDocumentUpdateDTO(ViewDocumentDetailsDTO dto, Object[] objArray) {
        DocumentUpdateDTO documentUpdateDTO = new DocumentUpdateDTO();
        documentUpdateDTO.setDocuedgeDocumentId(dto.getDocuedgeDocumentId());
        documentUpdateDTO.setClientId((long)objArray[1]);
        documentUpdateDTO.setDocumentDescription(dto.getDocumentDescription());
        return documentUpdateDTO;
    }

    private Object[] isValidInput(String indvCaseInd, long ssn, String caseAppNum) throws Exception{
        Object[] output =new Object[3];
        boolean isValid =false;
        Long indvId = 0L;
        String name = CoConstants.EMPTY_STRING;
        try {
            if(StringUtils.isNumeric(caseAppNum)){
                if(CoConstants.C.equalsIgnoreCase(indvCaseInd)){
                    List<DcIndv> dcIndvList = dcIndvRepository.findHohByCaseNum(caseAppNum);
                    DcIndv temp = dcIndvList.get(0);
                    if(temp.getSsn() == ssn){
                        isValid =true;
                        indvId = temp.getIndvId();
                        name = CorrespondenceServices.getName(temp.getFirstName(),
                                temp.getMidName(), temp.getLastName(),temp.getSufxName());
                    }
                }else{
                    List<DcIndv> dcIndvList = dcIndvRepository.findByCaseNum(Long.parseLong(caseAppNum));
                    for(DcIndv dcIndv :dcIndvList){
                        if(dcIndv.getSsn() == ssn){
                            isValid= true;
                            indvId = dcIndv.getIndvId();
                            name = CorrespondenceServices.getName(dcIndv.getFirstName(),
                                    dcIndv.getMidName(), dcIndv.getLastName(),dcIndv.getSufxName());
                            break;
                        }
                    }
                }
            }else{
                if(CoConstants.C.equalsIgnoreCase(indvCaseInd)){
                    List<T1004AppIndv> t1004AppIndvList = t1004AppIndvRepository.findHeadofHousehold(caseAppNum);
                    if(t1004AppIndvList.size() == 0){
                        List<VArApplicationIndv> VArApplicationIndvList = vArApplicationIndvRepository.findByHeadOfHousehold(caseAppNum, 'Y');
                        if(VArApplicationIndvList!= null && VArApplicationIndvList.size() > 0) {
                            VArApplicationIndv vAr = VArApplicationIndvList.get(0);
                            if(vAr.getSsn() == ssn){
                                isValid =true;
                                indvId = vAr.getT2IndvId();
                                name = CorrespondenceServices.getName(vAr.getFirstName(),
                                        vAr.getMidName(), vAr.getLastName(),vAr.getSufxName());

                            }
                        }
                    }else{
                        T1004AppIndv t1 = t1004AppIndvList.get(0);
                        if(t1.getSsnNum().equalsIgnoreCase(String.valueOf(ssn))){
                            isValid =true;
                            indvId = t1.getIndvSeqNum();
                            name = CorrespondenceServices.getName(t1.getFstNam(),
                                    t1.getMidInit(), t1.getLastNam(), t1.getSfxNam());

                        }
                    }
                }else{
                    List<T1004AppIndv> t1004AppIndvList = t1004AppIndvRepository.findByAppNum(caseAppNum);
                    System.out.println("t1004AppIndvList: " + t1004AppIndvList);
                    if(t1004AppIndvList.size() == 0){
                        List<VArApplicationIndv> VArApplicationIndvList = vArApplicationIndvRepository.findByAppNum(caseAppNum);
                        for(VArApplicationIndv vAr : VArApplicationIndvList){
                            if(vAr.getSsn()== ssn){
                                isValid =true;
                                indvId = vAr.getT2IndvId();
                                name = CorrespondenceServices.getName(vAr.getFirstName(),
                                        vAr.getMidName(), vAr.getLastName(), vAr.getSufxName());
                                break;
                            }
                        }
                    }else{
                        for(T1004AppIndv t1 : t1004AppIndvList){
                            if(t1.getSsnNum().equalsIgnoreCase(String.valueOf(ssn))){
                                isValid =true;
                                indvId = t1.getIndvSeqNum();
                                name = CorrespondenceServices.getName(t1.getFstNam(),
                                        t1.getMidInit(), t1.getLastNam(),t1.getSfxNam());
                                break;
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Unable to validate input " + e);
            throw new Exception(
                    "Unable to validate input due to " + e.getMessage() );
        }

        output[0] = isValid;
        output[1] = indvId;
        output[2] = name;

        return output;

    }

    private String getFullName(String firstName, String middleName,
                               String lastName, String suffix) {
        StringBuilder sb = new StringBuilder("");
        if (StringUtils.isNotBlank(firstName)) {
            sb.append(firstName);
        }
        if (StringUtils.isNotBlank(middleName)) {
            sb.append(" ");
            sb.append(middleName.charAt(0));
        }
        if (StringUtils.isNotBlank(lastName)) {
            sb.append(" ");
            sb.append(lastName);
        }
        if (StringUtils.isNotBlank(suffix)) {
            try {
                //TODO:code related to reference table
//                String suffixdocTypeCode = FwReferenceTableManager
//                        .getValueByColumn(true, "NAMESUFFIX", suffix,
//                                "DESCRIPTION");
//                sb.append(" ");
//                sb.append(suffixdocTypeCode);
            } catch (Exception e) {
                log.error("Exception occured while searching suffix name"
                        + e);
            }
        }
        return sb.toString();
    }
}
