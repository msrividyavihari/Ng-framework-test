package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name ="NOTICE_REQUEST_STATUS")
@EntityType(type= TypeEnum.ZERO)
@NoArgsConstructor
@Data
public class NoticeRequestStatus extends TypeZeroBaseEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_REQUEST_STATUS_1SQ")
    @SequenceGenerator(name="NOTICE_REQUEST_STATUS_1SQ", initialValue = 200, allocationSize = 50)
    private Long logRequestId;
    private Long noticeRequestId;
    private String templateId;
    private Long hohId;
    private String noticeType;
    private Date mailDate;
    private Character waterMark;
    private Character securityFlag;
    private String formTitle;
    private Long caseNum;
    private Character goGreen;
    private String language;
    private String status;
    private String agencyName;
    private String agencyId;
    private Date requestDate;
    private Character addressUpdated;
    private String clientName;
    private String agencyCode;
    @Lob
    private String requestJson;
    private Character sentToEdms;
    private Character edeliveryStatus;
    private Character retryProcess;
    private Character emailDeliveryStatus;
    private Character textNotificationStatus;
    private String reasonForFailure;
    private String emailId;
    private Long phoneNumber;
    private Character preferredCommunication;
    private Character regenerated;
    private String printVendor;
}

