package com.deloitte.nextgen.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaData {
    private String templateId;
    private String NoticeType;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date mailDate;
    private Long corrNum;
    private Character watermark;
    private Character securityFlag;
    private String formNo;
    private String formVersion;
    private String formTitle;
    private String preferredLanguage;
    private String envelopeId;
    private Long caseNum;
    private Character goGreen;
    private String agencyCode;
    private String caseworkerName;
    private String workerName;
    private String workeremailAddress;
    private Long workerPhoneNumber;
    private Long workerFaxNumber;
    private String documentId;
    private String salutation;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Timestamp noticeDate;
    private Long phoneNumber;
    private Long reciepientSeqNum;
    private Boolean displayFlagForPreviewSwitch;
    private Character edmsSw;
    private Character communicationMode;
    private Long orderNum;
    private String emailRecipientData;

    private String agencyId;
    private String agencyName;
    private String jobName;
    private Long requestId;
    private Long logRequestId;
    private String status;
    private String requestDate;
    private Character addressUpdated;
    private String clientName;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private java.sql.Timestamp startDt;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private java.sql.Timestamp endDt;
    private Date createDt;
    private Long hohId;
    private Character emailDeliveryStatus;
    private Character textNotificationStatus;
    private String reasonForFailure;
    private Character regenerated;
    private String printVendor;
    private String recipientName;



}
