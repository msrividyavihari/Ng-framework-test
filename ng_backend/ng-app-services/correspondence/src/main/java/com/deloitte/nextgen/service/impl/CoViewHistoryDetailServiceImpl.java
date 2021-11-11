package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.DocumentIdDTO;
import com.deloitte.nextgen.dto.entities.ViewHistoryDetailDTO;
import com.deloitte.nextgen.dto.vo.ViewHistoryDetailVO;
import com.deloitte.nextgen.entities.CoMaster;
import com.deloitte.nextgen.entities.CoRequestRecipients;
import com.deloitte.nextgen.entities.InDisDocMaster;
import com.deloitte.nextgen.entities.VCoRequest;
import com.deloitte.nextgen.repository.CoMasterRepository;
import com.deloitte.nextgen.repository.CoRequestRecipientsRepository;
import com.deloitte.nextgen.repository.InDisDocMasterRepository;
import com.deloitte.nextgen.repository.VCoRequestRepository;
import com.deloitte.nextgen.service.CoViewHistoryDetailService;
import com.deloitte.nextgen.util.COPrintPreviewManager;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


@Service
@Slf4j
@RequiredArgsConstructor
public class CoViewHistoryDetailServiceImpl implements CoViewHistoryDetailService {

    @Autowired
    VCoRequestRepository vCoRequestRepository;

    @Autowired
    CoRequestRecipientsRepository coRequestRecipientsRepository;

    @Autowired
    CoMasterRepository coMasterRepository;

    @Autowired
    COPrintPreviewManager coPrintPreviewManager;

    @Autowired
    InDisDocMasterRepository inDisDocMasterRepository;

    @Override
    public byte[] viewHistoryDetailService(String disDocMstrSeqNum) {
        byte[] fileArray = null;
        try {
            fileArray = downloadFile(disDocMstrSeqNum);
        } catch (final Exception ex) {
            log.debug(ex.getMessage(), ex);
        }
        return fileArray;
    }

    private byte[] downloadFile(String disDocMstrSeqNum) {
        List<InDisDocMaster> inDisDocMasterCargo = inDisDocMasterRepository.findByDisDocMstrSeqNum(Long
                .valueOf(disDocMstrSeqNum));
        if(!CoUtil.isEmpty(inDisDocMasterCargo)) {
            DocumentIdDTO documentIdDTO = new DocumentIdDTO();
            documentIdDTO.setDocId(inDisDocMasterCargo.get(0).getDocuedgeDocumentId());
            documentIdDTO.setAction("");
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<DocumentIdDTO> httpEntity = new HttpEntity<>(
                    documentIdDTO, headers);
            ResponseEntity<byte[]> responseObj = restTemplate.exchange("http://15.207.142.199/co/api/documentHandler/getDocumentStream",
                    HttpMethod.POST, httpEntity, byte[].class);
            return responseObj.getBody();
        }else{
            log.error("No row in IN_DIS_DOC_MASTER for seq"+disDocMstrSeqNum);
        }
        return null;
    }

    public ViewHistoryDetailVO retrieveHistoryDetails(ViewHistoryDetailDTO viewHistoryDetailDTO)  {
        Long reqSeq = viewHistoryDetailDTO.getT2CoReqSeq();
        Long detSeq = viewHistoryDetailDTO.getCoDetSeq();

        List<VCoRequest> vCoRequestList = null;

        if(reqSeq != null && detSeq != null) {
            vCoRequestList = vCoRequestRepository.findByReqSeqAndDetSeq(reqSeq, detSeq);
        }
        List<CoRequestRecipients> coRequestRecipients = null;

        long coRptSeq = 0L;
        long coReqSeq = 0L;
        if(!CoUtil.isEmpty(vCoRequestList)) {
            coRptSeq = vCoRequestList.get(0).getCoRptSeq() != null ? vCoRequestList.get(0).getCoRptSeq() : 0L;
            coReqSeq = vCoRequestList.get(0).getT2CoReqSeq() != null ? vCoRequestList.get(0).getT2CoReqSeq() : 0L;
        }

        if (coRptSeq == 0){
            /*
              find recipients
              recipients found for all stage III PRODUCTION run and docs are meant for Clients
              recipients not found if stage II PRODUCTION run and STAGE III docs meant for agencies
             */
            coRequestRecipients = coRequestRecipientsRepository.findByAllRecipients(coReqSeq);

        } else if (coRptSeq != 0) {
            coRequestRecipients = coRequestRecipientsRepository.findByCoReqSeqAndCoRptSeq(coReqSeq, coRptSeq);
        }

        System.out.println("coRequestRecipients: "  + coRequestRecipients);


        List recipientWithCoRptSeqList = new ArrayList();
        List attachedDocNames = null;
        int length;
        String originalRecipient = null;
        String recipientData;
        boolean recipientFound = false;
        char recipientTypeId;

        if(coRequestRecipients != null) {
            length = coRequestRecipients.size();
            if (length > 0) {
                recipientFound = true;
            }
            StringTokenizer tokenizer;
            int j;
            String temp;
            for (int i = 0; i < length; i++) {

                recipientData = StringUtils.isNotEmpty(coRequestRecipients.get(i).getRecipientData())? coRequestRecipients.get(i).getRecipientData():CoConstants.EMPTY_STRING;
                recipientTypeId = coRequestRecipients.get(i).getRecipientTypeId();
                // if no recipient's name is found then append empty space
                if(recipientData.indexOf("|") == recipientData.length() - 1){
                    recipientData = recipientData+" ";
                }

                tokenizer =
                        new StringTokenizer(recipientData, String.valueOf(CoConstants.PIPE));
                j = 0;

                while (tokenizer.hasMoreTokens()) {
                    temp = tokenizer.nextToken();
                    if (j == 0) {
                        j++;
                    } else if (j == 1) {
                        if(temp.contains(CoConstants.RECIPIENT_DATA_NAME_SEPARATOR)) {
                            temp = temp.substring(0, temp.indexOf(CoConstants.RECIPIENT_DATA_NAME_SEPARATOR));
                            if(recipientTypeId == CoConstants.RECIPIENT_ORIGINAL_ID) {
                                originalRecipient = temp;
                            } else if(recipientTypeId == CoConstants.RECIPIENT_COPY_ID) {
                                recipientWithCoRptSeqList.add(temp);
                            }
                            break;
                        }

                    }
                }
                //for DHS-1605 History Print only
                StringTokenizer tokens =
                        new StringTokenizer(recipientData, String.valueOf(CoConstants.PIPE));
                String attachedDocIdsFor1605 = null;
                int separatorCount = 1;
                while(tokens.hasMoreTokens()) {
                    if(separatorCount < 3) {
                        //fetch next token only
                        tokens.nextToken();
                    } else{
                        attachedDocIdsFor1605 = tokens.nextToken();
                    }
                    separatorCount++;
                }
                //see if there are attached documents
                String attachedDocId = CoConstants.EMPTY_STRING;

                if(attachedDocIdsFor1605 != null && attachedDocIdsFor1605.length() > 3) {
                    if(attachedDocIdsFor1605.indexOf(CoConstants.COMMA) > 0) {
                        //more docs are attached

                        int count = 0;
                        StringTokenizer docIds =
                                new StringTokenizer(attachedDocIdsFor1605, CoConstants.COMMA);
                        int tokenCount = docIds.countTokens();
                        while(docIds.hasMoreTokens()) {
                            if(count == tokenCount - 1) {
                                attachedDocId += "'"+docIds.nextToken()+"'";
                            } else {
                                attachedDocId += "'"+docIds.nextToken()+"',";
                            }
                            count++;
                        }

                    } else {
                        attachedDocId = "'"+ attachedDocIdsFor1605 + "'";
                        //attacheDocName = getDocNameForDocId(attacheDocId);
                    }
                    attachedDocNames = getAttached1605DocName(attachedDocId, attachedDocIdsFor1605);
                }

            }
        }
        return getViewHistoryDetailVO(attachedDocNames, originalRecipient, recipientWithCoRptSeqList, recipientFound);
    }

    @Override
    public byte[] generate(ViewHistoryDetailDTO viewHistoryDetailDTO)
            throws Exception {
            InDisDocMaster cargo;
            List<InDisDocMaster> inDisDocMasterList = inDisDocMasterRepository.findByDisDocMstrSeqNum(
                    viewHistoryDetailDTO.getDisDocMstrSeqNum());
            if(inDisDocMasterList != null && inDisDocMasterList.size() > 0)
                 cargo = inDisDocMasterList.get(0);
            else
                throw new Exception("Error while retrieving file for seqNum = " + viewHistoryDetailDTO.getDisDocMstrSeqNum());
            DocumentIdDTO documentIdDTO = new DocumentIdDTO();
            documentIdDTO.setDocId(cargo.getDocuedgeDocumentId());
            documentIdDTO.setAction("");
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<DocumentIdDTO> httpEntity = new HttpEntity<>(
                    documentIdDTO, headers);
            ResponseEntity<byte[]> responseObj = restTemplate.exchange("http://15.207.142.199/co/api/documentHandler/getDocumentStream",
                    HttpMethod.POST, httpEntity, byte[].class);
            byte[] fileArray = responseObj.getBody();
            if(coPrintPreviewManager.reprintHistory(viewHistoryDetailDTO))
                return fileArray;
        return null;
    }

    private ViewHistoryDetailVO getViewHistoryDetailVO(List attachedDocNames, String originalRecipient, List recipientWithCoRptSeqList, boolean recipientFound) {
        ViewHistoryDetailVO viewHistoryDetailVO = new ViewHistoryDetailVO();
        viewHistoryDetailVO.setAttachedDocNames(attachedDocNames);
        viewHistoryDetailVO.setOriginalRecipient(originalRecipient);
        viewHistoryDetailVO.setRecipientWithCoRptSeqList(recipientWithCoRptSeqList);
        viewHistoryDetailVO.setRecipientFound(recipientFound);
        return viewHistoryDetailVO;
    }

    /**
     * gets List of attached doc names for 1605
     * @param docIds String
     * @param originalDocIds String
     * @return List
     */
    public ArrayList getAttached1605DocName(String docIds, String originalDocIds) {
        ArrayList docNames = new ArrayList();
        List<CoMaster> coMasterList = coMasterRepository.findByDocIds(docIds);
        if(coMasterList != null && coMasterList.size() > 0) {

            StringTokenizer orderedDocs = new StringTokenizer(originalDocIds, CoConstants.COMMA);
            String doc_Id;
            while(orderedDocs.hasMoreTokens()){
                doc_Id = (orderedDocs.nextToken()).trim();
                for (CoMaster coMaster : coMasterList) {
                    if (coMaster.getDocId().equalsIgnoreCase(doc_Id) && coMaster.getDocName() != null) {
                        docNames.add(coMaster.getDocName());
                    }
                }
            }
        }
        return docNames;
    }

}
