package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.CoRequestHistoryRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

@Entity
@Table(name ="CO_REQUEST_HISTORY")
@EntityType(type= TypeEnum.ZERO, customRepository = CoRequestHistoryRepository.class)
@Data
@NoArgsConstructor
public class CoRequestHistory extends TypeZeroBaseEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "CO_REQUEST_HISTORY_1SQ")
    @Column(name = "CO_REQ_SEQ")
    private Long coReqSeq;

    @Column(name = "DOC_ID")
    private String docId;

    @Column(name = "INDV_ID")
    private Long indvId;

    @Column(name ="ACTION_CD")
    private Character actionCd;

    @Column(name ="REASON_CD_LIST")
    private String reasonCdList;

    @Column(name ="DRAFT_SW")
    private Character draftSw;

    @Column(name ="LANGUAGE_CD")
    private String languageCd;

    @Column(name ="EMP_ID")
    private Long empId;

    @Column(name = "GENERATE_DT")
    private Timestamp generateDt;

    @Column(name = "MISC_PARMS")
    private String miscParms;

    @Column(name = "HISTORY_SW")
    private Character historySw;

    @Column(name = "PENDING_TRIG_SW")
    private Character pendingTrigSw;

    @Lob
    @Column(name = "HST_PRINT_STRING")
    private Blob hstPrintString;

    @Column(name = "DOC_TYPE_CD")
    private Character docTypeCd;

    @Column(name ="REQUEST_TYPE_CD")
    private Character requestTypeCd;

    @Column(name = "PROGRAM_CD")
    private String programCd;

    @Column(name = "ORIG_PRINT_DT")
    private Timestamp origPrintDt;

    @Column(name = "APPT_ID")
    private Long apptId;

    @Column(name ="OFFICE_NUM")
    private Long officeNum;

    @Column(name = "EDG_NUM")
    private Long edgNum;

    @Column(name = "BENEFIT_NUM")
    private String benefitNum;

    @Column(name="MANUALLY_GENERATED_SW")
    private Character manuallyGeneratedSw;

    @Column(name = "MASS_GENERATED_SW")
    private Character massGeneratedSw;

    @Column(name = "ASSISTANCE_LIST")
    private String assistanceList;

    @Column(name = "CASE_NUM")
    private Long caseNum;

    @Column(name = "APP_NUM")
    private String appNum;

    @Column(name = "CHIP_APP_NUM")
    private String chipAppNum;

    @Column(name = "LOG_ID")
    private Long logId;

    @Column(name = "EDG_TRACE_ID")
    private Long edgTraceId;

    @Column(name = "MASS_MAILING_ID")
    private Long massMailingId;

    @Column(name = "PROVIDER_ID")
    private Long providerId;

    @Column(name = "LOCATION_ID")
    private String locationId;

    @Column(name = "DIS_ID")
    private String disId;

    @Column(name = "SPECIAL_NOTES")
    private String specialNotes;

    @Column(name = "GO_GREEN")
    private String goGreen;

    @Column(name = "CO_STATUS_SW")
    private Character coStatusSw;

    @Column(name = "EDBC_RUN_ID")
    private String edbcRunId;

    @Column(name = "MEDICAID_INDV_ID")
    private String medicaidIndvId;

    @Column(name = "PAGE_NUM")
    private Long pageNum;

    @Column(name = "CC_PROVIDER_ID")
    private Long ccProviderId;

    @Column(name = "CC_PROVIDER_CERT_START_DT")
    private Timestamp ccProviderCertStartDt;

    @Column(name = "CC_PROVIDER_CERT_END_DT")
    private Timestamp ccProviderCertEndDt;
}
