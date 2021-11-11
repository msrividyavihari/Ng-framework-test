package com.deloitte.nextgen.util;

import com.deloitte.nextgen.dto.entities.DocumentInfo;
import com.deloitte.nextgen.dto.entities.DocumentManagementSearchHistoryDTO;
import com.deloitte.nextgen.dto.vo.DocumentDetailsVO;
import com.deloitte.nextgen.entities.*;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.repository.*;
import com.deloitte.nextgen.service.impl.DocumentHandlerServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class DocumentManagementUtil {

    @Autowired
    DcCasesRepository dcCasesRepository;

    @Autowired
    DcCaseProgramRepository dcCaseProgramRepository;

    @Autowired
    DcCaseIndividualRepository dcCaseIndividualRepository;

    @Autowired
    ArAppProgramRepository arAppProgramRepository;

    @Autowired
    InDisDocMasterRepository inDisDocMasterRepository;

    @Autowired
    DocumentHandlerServiceImpl documentHandlerService;

    @Autowired
    TransactionIdRepository transactionIdRepository;

    @Autowired
    MoEmployeesRepository moEmployeesRepository;

    @Autowired
    ArApplicationForAidRepository arApplicationForAidRepository;

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
        HashMap<String, Object> metaData = new HashMap<String, Object>();
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



    public List<InDisDocMaster> searchByCase(DocumentManagementSearchHistoryDTO documentManagementSearchHistoryDTO) throws ParseException {
        String indvListStr = "";

        boolean isIndvSearch = false;
        boolean isShinesCcCase = false;
        boolean isCcCaseApp = false;
        final String caseAppNum = String.valueOf(documentManagementSearchHistoryDTO.getCaseAppNum());
        String indvID = documentManagementSearchHistoryDTO.getClientId();
        String reference = documentManagementSearchHistoryDTO.getReference();
        String fromDt = documentManagementSearchHistoryDTO.getBeginDate();
        String toDt = documentManagementSearchHistoryDTO.getToDate();
        String fromDtString;
        String toDtString;

        if (Pattern.matches("[0-9]+", caseAppNum)) {

            documentManagementSearchHistoryDTO.setCaseNum(Long.parseLong(caseAppNum) );

            // for shines cases, the document uploaded to application of case with source IESED also should be displayed
            isShinesCcCase = isShinesCcCase(documentManagementSearchHistoryDTO.getCaseNum());

            //defect fix 102054  Not Display Docs Over 90 Days Old only for CC case/app
            isCcCaseApp = isCcCase(documentManagementSearchHistoryDTO.getCaseNum());

            //CR 620908 -  to display documents uploaded against all individuals on the case when user searches by case number
            indvListStr = getAllIndv(documentManagementSearchHistoryDTO.getCaseNum());

            if(StringUtils.isNotBlank(indvListStr)) {
                isIndvSearch = true;
            }
        } else if (Pattern.matches("[a-zA-Z0-9]+", caseAppNum)) {
            documentManagementSearchHistoryDTO.setAppNum(caseAppNum);

            //defect fix 102054  Not Display Docs Over 90 Days Old only for CC case/app
            isCcCaseApp = isCcApp(caseAppNum);
        }

        if (indvID != null && !indvID.equals("")) {
            documentManagementSearchHistoryDTO.setIndvId(Long.parseLong(indvID));
            isIndvSearch = false;
        }

        documentManagementSearchHistoryDTO.setTransactionId((String) documentManagementSearchHistoryDTO.getTransactionNum());

        documentManagementSearchHistoryDTO.setDocType((String) documentManagementSearchHistoryDTO.getDocumentType());
        if (reference != null && !"".equals(reference)) {
            documentManagementSearchHistoryDTO.setDisDocMstrSeqNum(Long.parseLong(reference));
        } else {
            documentManagementSearchHistoryDTO.setCoReqSeq(new Integer(0).longValue());
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        // beginDate1 is set to EntryDt but in sql it is compared with
        // EntryDt
        if (!"".equals(fromDt) && (fromDt != null)) {
            try {
                fromDtString = CoUtil.getDateForWhereClauseANSI(fromDt);
            } catch (FwApplicationException e) {
                e.printStackTrace();
                fromDtString = null;
            }
            documentManagementSearchHistoryDTO.setEntryDt(fromDtString);

            //when worker searches for specific date for CC case, should not restrict for 90 days.
            isCcCaseApp = false;
        }
        //endDateTR is set to CreateDt but in sql it is compared with EntryDt
        if (!"".equals(toDt) && (toDt != null)) {
            try {
                toDtString = CoUtil.getDateForWhereClauseANSI(fromDt);
            } catch (FwApplicationException e) {
                e.printStackTrace();
                toDtString = null;
            }
            documentManagementSearchHistoryDTO.setCreateDate(toDtString);
            //when worker searches for specific date for CC case, should not restrict for 90 days.
            isCcCaseApp = false;
        }
        List<InDisDocMaster> inDisDocMasterList = inDisDocMasterRepository.findByDisQuickSearch(documentManagementSearchHistoryDTO, isShinesCcCase, isIndvSearch, indvListStr, isCcCaseApp);
        return inDisDocMasterList;

    }

    public boolean isShinesCcCase(Long caseNum) {
        boolean isShinesCcCase = false;

        List<DcCases> dcCaseList = dcCasesRepository.findByCaseNum(caseNum);
        for(DcCases dcCase: dcCaseList) {
            if("SHI".equalsIgnoreCase(dcCase.getAppModeCd())) {
                isShinesCcCase =true;
                break;
            }
        }
        return isShinesCcCase;
    }


    private  boolean isCcCase(long caseNum) {
        boolean isCcCase = false;
        List<DcCaseProgram> dcCaseProgramList =  dcCaseProgramRepository.findByProgCdAndCaseNumOrderByApplicationDt("CC", caseNum);
        if(dcCaseProgramList.size() > 0) {
            isCcCase = true;
        }
        return isCcCase;
    }

    private  String  getAllIndv(long caseNum) {
        String indvListStr = "";
        List<DcCaseIndividual> dcCaseIndividualList = dcCaseIndividualRepository.findByCaseNum(caseNum);
        if (dcCaseIndividualList != null && dcCaseIndividualList.size() > 0) {
            Set<String> indvList = dcCaseIndividualList.stream()
                    .map(caseIndv -> String.valueOf(caseIndv.getIndvId()))
                    .collect(Collectors.toSet());
            indvListStr = String.join(",", indvList);
        }
        return indvListStr;
    }

    private  boolean isCcApp(String appNum) {
        boolean isCcApp = false;
        List<ArAppProgram> arAppProgramList = arAppProgramRepository.findByAppNumberForCC(appNum);
        if(arAppProgramList.size() > 0) {
            isCcApp =true;
        }
        return isCcApp;
    }

    public List<InDisDocMaster> searchByOther(DocumentManagementSearchHistoryDTO documentManagementSearchHistoryDTO) throws FwApplicationException {
        List<InDisDocMaster> inDisDocMasterList = inDisDocMasterRepository.findByIndividualDetails(documentManagementSearchHistoryDTO);
        return inDisDocMasterList;
    }

    public boolean isWicDPHAccessibleByAppNum(Long empId, String caseAppNumber) throws FwApplicationException {

        //getting does user has Wic DPH Access
        boolean isWicDPHAccessible = validateDphRole(empId);
        // if user has wic access no need to check  wicDisclosureCode retruning true
        if(isWicDPHAccessible){
            return true;
        }

        //As user do not have access to wic, coming here to check wicDisclosureCode at App level. does this case selected not to disclose ?

        String wicDisclosureCode = null;
        List<ArApplicationForAid> arApplicationForAidList =  arApplicationForAidRepository.findByAppNum(caseAppNumber);
        if(arApplicationForAidList != null && arApplicationForAidList.size() > 0) {
            Long caseNum = arApplicationForAidList.get(0).getCaseNum();
            if(caseNum != null && caseNum > 0) {
                List<DcCases> dcCases = dcCasesRepository.findByCaseNum(caseNum);
                if(dcCases != null && dcCases.size() > 0) {
                    wicDisclosureCode = dcCases.get(0).getWicDisclosureCd();
                }
            }

            if("N".equalsIgnoreCase(wicDisclosureCode)) {
                return false;
            } else if (StringUtils.isBlank(wicDisclosureCode) &&
                    "N".equalsIgnoreCase(arApplicationForAidList.get(0).getWicDisclosureSw())){
                return false;
            }
        }
        return true;
    }

    public boolean validateDphRole(Long empId)  throws FwApplicationException{
        boolean dphRole = false;
        if (empId != 0 && null != empId) {
            try {
                MoEmployees moEmployeesList = moEmployeesRepository.findByDphUserUsingEmpId(empId);
                System.out.println("moEmployeeList: " + moEmployeesList);
                if (moEmployeesList != null) {
                    dphRole = true;
                }
            } catch (Exception e) {
//                System.out.println(" Error while retrieving employees for DPH OfficeType " +
//                        e.getMessage());
                throw new FwApplicationException("Error while retrieving employees for DPH OfficeType " + e.getMessage());
            }
        } else {
            System.out.println(" Employee Id is coming as null or 0: " + empId);
        }
        return dphRole;
    }

    public boolean isWicDPHAccessibleByCaseNum(Long empId, Long caseNum) throws FwApplicationException {
        //getting does user has Wic DPH Access
        boolean isWicDPHAccessible = validateDphRole(empId);
        // if user has wic access no need to check  wicDisclosureCode retruning true
        if(isWicDPHAccessible){
            return true;
        }

        //As user do not have access to wic, coming here to check wicDisclosureCode at case level. does this case selected not to disclose ?
        String wicDisclosureCode = null;
        List<DcCases> dcCases = dcCasesRepository.findByCaseNum(caseNum);
        if(dcCases != null && dcCases.size() > 0) {
            wicDisclosureCode = dcCases.get(0).getWicDisclosureCd();
        }

        if ("N".equalsIgnoreCase(wicDisclosureCode) ) {
            return false;
        } else  if(StringUtils.isBlank(wicDisclosureCode)){
            // if case level is not available, checking at App level, app of this case do not want to disclose ?
            List<ArApplicationForAid> arApplicationForAidList =  arApplicationForAidRepository.findByCaseNum(caseNum);
            if((arApplicationForAidList != null
                    && arApplicationForAidList.size() > 0
                    && "N".equalsIgnoreCase(arApplicationForAidList.get(0).getWicDisclosureSw()))) {
                return false;
            }
        }
        return true;

    }
}
