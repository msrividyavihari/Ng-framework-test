package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.DcCaseProgramId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.DcCaseProgramRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="DC_CASE_PROGRAM")
@EntityType(type= TypeEnum.ONE, customRepository = DcCaseProgramRepository.class)
@Data
@NoArgsConstructor
@IdClass(DcCaseProgramId.class)
public class DcCaseProgram extends TypeOneBaseEntity<Long> implements Serializable {

    @Id
    @Column(name = "CASE_NUM")
    private Long caseNum;
    @Column(name = "PROG_CD")
    private String progCd;
    @Column(name = "PRIOR_MEDICAID_CD")
    private String priorMedicaidCd;
    @Column(name = "PRIOR_MA_SEQ_NUM")
    private Long priorMaSeqNum;
    @Column(name = "PROG_STATUS_CD")
    private String progStatusCd;
    @Column(name = "PROG_STATUS_DT")
    private Timestamp progStatusDt;
    @Column(name = "PROG_RESCIND_DT")
    private Timestamp progRescindDt;
    @Column(name = "PROG_RESCIND_CD")
    private String progRescindCd;
    @Column(name = "REQUEST_DT")
    private Timestamp requestDt;
    @Column(name = "EXPEDITED_SW")
    private Character expeditedSw;
    @Column(name = "APPLICATION_DT")
    private Timestamp applicationDt;
    @Column(name = "CONVERSION_DT")
    private Timestamp conversionDt;
    @Column(name = "WITHDRAW_DT")
    private Timestamp withdrawDt;
    @Column(name = "WITHDRAW_SW")
    private Character withdrawSw;
    @Column(name = "REQUEST_DUE_DT")
    private Timestamp requestDueDt;
    @Column(name = "DELAY_RSN_DT")
    private Timestamp delayRsnDt;
    @Column(name = "DELAY_RSN_CD")
    private String delayRsnCd;
    @Column(name = "OTTANF_REQUESTED_SW")
    private Character ottanfRequestedSw;
    @Column(name = "INTERVIEW_DT")
    private Timestamp interviewDt;
    @Column(name = "DENIAL_REASON_CD")
    private String denialReasonCd;
    @Column(name = "PROCESS_ID")
    private String processId;
    @Column(name = "SER_SERVICE_CD")
    private String serServiceCd;
    @Column(name = "FIRST_EXP_SCREEN_RESULT_SW")
    private Character firstExpScreenResultSw;
    @Column(name = "LATEST_EXP_SCREEN_RESULT_SW")
    private Character latestExpScreenResultSw;
    @Column(name = "LATEST_EXP_SCREEN_DT")
    private Timestamp latestExpScreenDt;
    @Column(name = "CV_MODE_SW")
    private Character cvModeSw;
    @Column(name = "DENIAL_DT")
    private Timestamp denialDt;
    @Column(name = "APPLICATION_RECEIVED_DT")
    private Timestamp applicationReceivedDt;
    @Column(name = "RECERT_REVIEW_DUE_DT")
    private Timestamp recertReviewDueDt;
    @Column(name = "PROG_WITHDRAW_CD")
    private String progWithdrawCd;
    @Column(name = "OP_BENEFIT_AMT")
    private Double opBenefitAmt;
    @Column(name = "MEDICAID_TYPE_CD")
    private String medicaidTypeCd;
    @Column(name = "WAITLIST_SCREENING_SW")
    private Character waitlistScreeningSw;
    @Column(name = "REINSTATE_SW")
    private Character reinstateSw;
    @Column(name = "WITHDRAW_ADDTIONAL_INFO")
    private String withdrawAddtionalInfo;
    @Column(name = "WAITLIST_SCREENING_DT")
    private Timestamp waitlistScreeningDt;
    @Column(name = "CLOSURE_REASON_CD")
    private String closureReasonCd;
    @Column(name = "PRIORITY_GROUP")
    private String priorityGroup;
    @Column(name = "PRIOR_MEDICAID_APP_DATE")
    private Timestamp priorMedicaidAppDate;
    @Column(name = "REOPEN_REASON")
    private String reopenReason;
    @Column(name = "PCK_PROG_SW")
    private Character pckProgSw;
    @Column(name = "REQUESTING_GRG_SW")
    private Character requestingGrgSw;
    @Column(name = "REPORT_DT")
    private Timestamp reportDt;
    @Column(name = "VERF_RECEIVED_DT")
    private Timestamp verfReceivedDt;
    @Column(name = "PCK_KIDS_CNT")
    private Long pckKidsCnt;
    @Column(name = "CLOSURE_REPORTED_ON_DT")
    private Timestamp closureReportedOnDt;
}
