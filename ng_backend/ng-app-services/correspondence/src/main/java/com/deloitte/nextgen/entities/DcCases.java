package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.DcCasesRepository;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="DC_CASES")
@EntityType(type= TypeEnum.ONE, customRepository = DcCasesRepository.class)
@Data
@NoArgsConstructor
public class DcCases extends TypeOneBaseEntity<Long> implements Serializable {
    @Id
    @Column(name = "CASE_NUM")
    private Long caseNum;
    @Column(name = "CASE_CLOSED_DT")
    private Timestamp caseClosedDt;
    @Column(name = "CASE_STATUS_CD")
    private String caseStatusCd;
    @Column(name = "CASE_MODE_CD")
    private String caseModeCd;
    @Column(name = "REACTIVATION_IND")
    private Character reactivationInd;
    @Column(name = "REACTIVATION_DT")
    private Timestamp reactivationDt;
    @Column(name = "INTERVIEW_DT")
    private Timestamp interviewDt;
    @Column(name = "APP_RECVD_DT")
    private Timestamp appRecvdDt;
    @Column(name = "OFFICE_NUM")
    private Long officeNum;
    @Column(name = "AUTH_REP_SW")
    private Character authRepSw;
    @Column(name = "INTERVIEW_TYPE_CD")
    private String interviewTypeCd;
    @Column(name = "UNABLE_TO_LOCATE_SW")
    private Character unableToLocateSw;
    @Column(name = "ACTION_DT")
    private Timestamp actionDt;
    @Column(name = "RELATIONSHIP_INDV_CD")
    private String relationshipIndvCd;
    @Column(name = "PROCESS_ID")
    private String processId;
    @Column(name = "USER_ACTION_CD")
    private String userActionCd;
    @Column(name = "CONVERSION_DT")
    private Timestamp conversionDt;
    @Column(name = "CASE_STATUS_DT")
    private Timestamp caseStatusDt;
    @Column(name = "TRIBAL_CASE_SW")
    private Character tribalCaseSw;
    @Column(name = "APP_SIGNED_SW")
    private Character appSignedSw;
    @Column(name = "APP_MODE_CD")
    private String appModeCd;
    @Column(name = "ASSIGN_SELF_SW")
    private String assignSelfSw;
    @Column(name = "PH_NUM")
    private String phNum;
    @Column(name = "WORK_PH_NUM")
    private String workPhNum;
    @Column(name = "OTHER_PH_NUM")
    private String otherPhNum;
    @Column(name = "WAITLIST_SCREENING_SW")
    private String waitlistScreeningSw;
    @Column(name = "UCM_TRAN_NUM")
    private String ucmTranNum;
    @Column(name = "CONFIDENTIAL_CASE_SW")
    private String confidentialCaseSw;
    @Column(name = "RESTRICT_ACCS_CUST_PORTAL_SW")
    private String restrictAccsCustPortalSw;
    @Column(name = "PREF_CNTC_MTHD_CD")
    private String prefCntcMthdCd;
    @Column(name = "PREF_CNTC_EMAIL_ADR")
    private String prefCntcEmailAdr;
    @Column(name = "CASE_OR_TASK")
    private Character caseOrTask;
    @Column(name = "MEDICAID_FORM_SW")
    private Character medicaidFormSw;
    @Column(name = "MEDICAID_FORM_DT")
    private Timestamp medicaidFormDt;
    @Column(name = "WORK_FLOW_STATUS_CD")
    private String workFlowStatusCd;
    @Column(name = "WIC_DISCLOSURE_CD")
    private String wicDisclosureCd;
    @Column(name = "FIRST_APP_NUM")
    private String firstAppNum;

}
