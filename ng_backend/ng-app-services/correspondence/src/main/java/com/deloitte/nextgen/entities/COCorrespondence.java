package com.deloitte.nextgen.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class COCorrespondence implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long coReqSeq = 0L;
    private Long coDetSeq = 0L;
    private String caseAppNumber = null;
    private Character caseAppFlag = 0;
    private String docId = null;
    private Character docType = 0;
    private Long indvId;
    private String assistanceProgramCode = null;
    private Character actionCode = 0;
    private String reasonCdList = null;
    private Character draftSwitch = 0;
    private String langCd = null;
    private String goGreen = null;
    private Character historySwitch = 0;
    private Long empId =0L;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp generateDate = null;
    private String requestUserId = null;
    private String miscParameters = null;
    private Character printMode = 0;
    private Character pendingTrigSw = 0;
    private Character reprintSw = 0;
    private String runMode = "O";
    private boolean isManuallyGenerated = false;
    private Long apptId = 0L;
    private Long edgeNumber = 0L;
    private String benefitNumber;
    private Long officeNumber = 0L;
    private String actionValue = null;
    private Timestamp printDate = null;
    private Character massGeneratedSw = 'N';
    private Timestamp payMonth = null;
    private boolean printReturnName = true;
    private boolean isManualCorrespondence = false;
    private String chipAppNum;
    private String xmlFileName;
    private String jobId;
    private String fileLocation = null;
    private String docName = null;
    private Long massMailingId;
    private Character securitySw;
    private String asOfDate = "";
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp asOfDateTimeStamp = null;
    private String xmlStr = null;
    private Connection connection;
    private boolean previewMode = false;
    private Map<String, String> formData = null;
    private Map<String, Object> fomData1=null;
    private String disId = null;
    private Long prevCoReqSeq = 0L;
    private String actionCd = null;
    private String runId = null;
    private String specialNotes = null;
    private Map<Long, String> coReqSeqLangCdMap  = new HashMap<>();
    private Long logId = 0L;
    private String edbcRunId = null;
    private boolean isDualEligible;
    private String nursingHomeNm ;
    private Long ccProviderId ;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp ccCertStartDate = null;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp ccCertEndDate = null;
    List<Object> individualList = new ArrayList<>();
    private DcIndv hohIndvCargo;
    private String typeOfAssistanceList = null;
    private Long coRptSeq = 0L;
    private ArrayList coRptSeqList = null;
    private CoRecipients coRecipients = null;
    private boolean updateRequestHistoryBlob = false;
    private Long edgTraceId = 0L;
    private Long cancelledEdgTraceId = 0L;
    private boolean viewSw;
    private boolean isSkipReferralSw;
    private Long providerId = 0L;
    private Long serviceId = 0L;
    private Long serviceReqSeqNum = 0L;
    private String attachedDocs = null;

    /**
     * Method validateRequest.
     *
     * @return boolean
     */
    public boolean validateRequest() {

        boolean validationFlag;
        if (caseAppNumber == null || docId == null || docId.trim().equals("")) {
            validationFlag = false;
        } else if ((historySwitch == 0)
                || ((historySwitch == 'Y') && (coReqSeq == 0))) {
            validationFlag = false;
        } else {
            validationFlag = draftSwitch != 0;
        }
        return validationFlag;
    }

    public String getRecipientType(){
        String recipientType ="CT";
        if(("NGGA0048".equalsIgnoreCase(this.getDocId())
                || "NGGA0049".equalsIgnoreCase(this.getDocId())) && this.getProviderId()>0){
            recipientType = "NH";
        }
        return recipientType;

    }
}
