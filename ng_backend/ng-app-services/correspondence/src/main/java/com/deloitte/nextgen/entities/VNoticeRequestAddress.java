package com.deloitte.nextgen.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "V_NOTICE_REQUEST_ADDRESS")
@NoArgsConstructor
public class VNoticeRequestAddress implements Serializable {
    @Id
    private Long logRequestId;
    private Long noticeRequestId;
    private Long hohId;
    private String templateId;
    private String formTitle;
    private Long caseNum;
    private Timestamp createDt;
    private String agencyName;
    private String agencyId;
    private String agencyCode;
    private Timestamp requestDate;
    private Character addressUpdated;
    private String clientName;
    private Character emailDeliveryStatus;
    private Character textNotificationStatus;
    private String reasonForFailure;
    private String origStreet1;
    private String origStreet2;
    private String origCity;
    private String origState;
    private Long origZip5;
    private Long origZip4;
    private String updStreet1;
    private String updStreet2;
    private String updCity;
    private String updState;
    private Long updZip5;
    private Long updZip4;
    private String status;
    private String emailId;
    private Long phoneNumber;
    private Character preferredCommunication;

}
