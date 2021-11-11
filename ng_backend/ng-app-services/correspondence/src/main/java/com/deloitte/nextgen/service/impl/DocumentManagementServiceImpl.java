package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.DocumentInfo;
import com.deloitte.nextgen.dto.entities.DocumentInfoCollection;
import com.deloitte.nextgen.dto.entities.DocumentManagementSearchHistoryDTO;
import com.deloitte.nextgen.dto.vo.DocumentDetailsVO;
import com.deloitte.nextgen.dto.vo.DocumentManagementSearchHistoryVO;
import com.deloitte.nextgen.entities.DcIndv;
import com.deloitte.nextgen.entities.InDisDocMaster;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.repository.DcIndvRepository;
import com.deloitte.nextgen.repository.TransactionIdRepository;
import com.deloitte.nextgen.service.DocumentManagementService;
import com.deloitte.nextgen.util.CoUtil;
import com.deloitte.nextgen.util.DocumentManagementUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class DocumentManagementServiceImpl implements DocumentManagementService {

    @Autowired
    DocumentHandlerServiceImpl documentHandlerService;

    @Autowired
    TransactionIdRepository transactionIdRepository;

    @Autowired
    DocumentManagementUtil documentManagementUtil;

    @Autowired
    DcIndvRepository dcIndvRepository;

    public ArrayList<DocumentDetailsVO> uploadDocument(DocumentInfoCollection documentInfoCollection) throws FwApplicationException {
        // to be returned
        ArrayList<DocumentDetailsVO> documentDetailsVOList = new ArrayList<>();
        boolean isUploadSuccess = false;
        // to hold the collection of DocumentInfo objects
        ArrayList<DocumentInfo> documentInfoArrayList = documentInfoCollection.getDocumentInfoCollection();
        // looping
        for(DocumentInfo documentInfo: documentInfoArrayList) {
            DocumentDetailsVO documentDetailsVO = uploadDocumentToDocuEdge(documentInfo);
            documentDetailsVOList.add(documentDetailsVO);
        }
        return documentDetailsVOList;
    }



    public DocumentDetailsVO uploadDocumentToDocuEdge(DocumentInfo documentInfo) throws FwApplicationException {

        byte[] fileArray = documentInfo.getFileArray();
        String title = documentInfo.getTitle();
        ByteArrayResource file = getByteArrayResourceFromByteArray(fileArray, title);
        String metaData = getMetaDataFromDocumentInfo(documentInfo);
        String categoryId = "260";
        ResponseEntity<String> response = documentHandlerService.uploadDocumentToDocuEdge(title, categoryId, metaData, file);
        DocumentDetailsVO documentDetailsVO = documentHandlerService.getDocumentDetailsFromUpload(response);
        return documentDetailsVO;
    }

    private String getMetaDataFromDocumentInfo(DocumentInfo documentInfo) {
        HashMap<String, Object> metaData = new HashMap<>();
        String transactionId = getTransactionId();
        String currentDate = CoUtil.dateToString(CoUtil.getCurrentDate());

        metaData.put("Case Number", Integer.parseInt(documentInfo.getCaseNum()));
        metaData.put("Application Number", Integer.parseInt(documentInfo.getApplicationNum()));
        metaData.put("Client Id", Integer.parseInt(documentInfo.getClientId()));
        metaData.put("Reference Number", 0);
        metaData.put("Transaction Number", transactionId);
        metaData.put("Document Type", documentInfo.getDocumentType());
        metaData.put("Document Received Date – Begin Date", currentDate);
        metaData.put("Document Received Date – End Date", currentDate);

        String result = documentHandlerService.mapToJson(metaData);
        return result;
    }

    private ByteArrayResource getByteArrayResourceFromByteArray(byte[] fileArray, String title) {
        ByteArrayResource file = new ByteArrayResource(fileArray) {
            @Override
            public String getFilename() {
                return title;
            }
        };
        return file;

    }

    public String getTransactionId() {
        return transactionIdRepository.findByTransactionId();
    }


    public List<InDisDocMaster> searchDocuments(DocumentManagementSearchHistoryDTO documentManagementSearchHistoryDTO) throws ParseException, FwApplicationException {

        List<InDisDocMaster> inDisDocMasterList = null;
        String selectType  = documentManagementSearchHistoryDTO.getCaseApp();

        userAccessValidation(documentManagementSearchHistoryDTO);

        if (selectType.equals("C")) {

            inDisDocMasterList = documentManagementUtil.searchByCase(documentManagementSearchHistoryDTO);

        } else if (selectType.equals("O")) {

            inDisDocMasterList = documentManagementUtil.searchByOther(documentManagementSearchHistoryDTO);
        }

        return inDisDocMasterList;
    }

    public List<DocumentManagementSearchHistoryVO> processInDisDocMaster(List<InDisDocMaster> inDisDocMasterList) {
        List<DocumentManagementSearchHistoryVO> resultList = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        if(inDisDocMasterList.size() <= 0) { return resultList; }

        for(InDisDocMaster inDisDocMaster: inDisDocMasterList) {
            DocumentManagementSearchHistoryVO obj = new DocumentManagementSearchHistoryVO();
            Long indvId = inDisDocMaster.getIndvId();
            DcIndv dcIndv = dcIndvRepository.findByIndividualId(indvId);
            String fullName = dcIndv.getSufxName() !=null ? dcIndv.getSufxName() + " " : "";
            fullName += dcIndv.getFirstName() != null ? dcIndv.getFirstName() + " " : "";
            fullName += dcIndv.getMidName() != null ? dcIndv.getMidName() + " " : "";
            fullName +=  dcIndv.getLastName()  != null ? dcIndv.getLastName() : "";

            obj.setFullName(fullName);
            obj.setAge(CoUtil.calculateDOB(dcIndv.getDobDt()));
            obj.setGender(dcIndv.getGenderCd());
            obj.setDocuedgeDocumentId(inDisDocMaster.getDocuedgeDocumentId());
            obj.setDisDocMstrSeqNum(inDisDocMaster.getDisDocMstrSeqNum());
            obj.setTransactionId(inDisDocMaster.getTransactionId());
            obj.setDocId(inDisDocMaster.getDocId());
            obj.setDocType(inDisDocMaster.getDocType());
            obj.setDocUploadType(inDisDocMaster.getDocUploadType());
            obj.setEntryDt(inDisDocMaster.getEntryDt());
            obj.setProcessFlag(inDisDocMaster.getProcessFlag());
            obj.setCaseNum(inDisDocMaster.getCaseNum());
            obj.setIndvId(inDisDocMaster.getIndvId());
            obj.setAppNum(inDisDocMaster.getAppNum());
            obj.setIndvSeqNum(inDisDocMaster.getIndvSeqNum());
            obj.setTaskNum(inDisDocMaster.getTaskNum());
            obj.setCpHistoryFlag(inDisDocMaster.getCpHistoryFlag());
            obj.setDelinkInd(inDisDocMaster.getDelinkInd());
            obj.setDisUpdInd(inDisDocMaster.getDisUpdInd());
            obj.setSource(inDisDocMaster.getSource());
            obj.setProgram(inDisDocMaster.getProgram());
            obj.setCoReqSeq(inDisDocMaster.getCoReqSeq());
            obj.setFilePath(inDisDocMaster.getFilePath());
            obj.setCommentCd(inDisDocMaster.getCommentCd());
            obj.setDateOfReceipt(inDisDocMaster.getDateOfReceipt());
            obj.setUniqueTransId(inDisDocMaster.getUniqueTransId());
            resultList.add(obj);
        }

        return resultList;
    }

    private void userAccessValidation(DocumentManagementSearchHistoryDTO documentManagementSearchHistoryDTO) throws FwApplicationException {

        String caseAppNumber = documentManagementSearchHistoryDTO.getCaseAppNum();
        Long empId = documentManagementSearchHistoryDTO.getEmpId();
        if(StringUtils.isNotBlank(caseAppNumber)){
            if (caseAppNumber.startsWith("T")) {
                if (!documentManagementUtil.isWicDPHAccessibleByAppNum(empId, caseAppNumber)) {
//                    throewGenericValidationException(aMap, request, "21985");
                    throw new FwApplicationException("21985");
                }
            } else  {
                Long caseNum = Long.parseLong(caseAppNumber);
                if (!documentManagementUtil.isWicDPHAccessibleByCaseNum(empId, caseNum)) {
//                    throewGenericValidationException(aMap, request, "22001");
                    throw new FwApplicationException("22001");
                }
            }
        }

    }

}
