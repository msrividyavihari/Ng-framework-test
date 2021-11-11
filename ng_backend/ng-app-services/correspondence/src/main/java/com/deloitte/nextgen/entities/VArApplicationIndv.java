package com.deloitte.nextgen.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="V_AR_APPLICATION_INDV")
@Data
@NoArgsConstructor
public class VArApplicationIndv implements Serializable {
    @Id
    @Column(name = "T1_APP_NUM")
    private java.lang.String t1AppNum;
    @Column(name = "PROGRAM_ADD_IND")
    private Character programAddInd;
    @Column(name = "T1_HISTORY_SEQ")
    private Long t1HistorySeq;
    @Column(name = "APP_RECVD_DT")
    private java.sql.Timestamp appRecvdDt;
    @Column(name = "APPLICATION_STATUS_CD")
    private java.lang.String applicationStatusCd;
    @Column(name = "OFFICE_NUM")
    private Long officeNum;
    @Column(name = "T1_CREATE_USER_ID")
    private java.lang.String t1CreateUserId;
    @Column(name = "T1_CREATE_DT")
    private java.sql.Timestamp t1CreateDt;
    @Column(name = "T1_UNIQUE_TRANS_ID")
    private Long t1UniqueTransId;
    @Column(name = "SAVERR_APP_NUM")
    private java.lang.String saverrAppNum;
    @Column(name = "CASE_NUM")
    private Long caseNum;
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "EXPEDITED_SW")
    private Character expeditedSw;
    @Column(name = "SCHEDULED_SW")
    private Character scheduledSw;
    @Column(name = "AUTH_REP_SW")
    private Character authRepSw;
    @Column(name = "EXPEDITED_DET_DT")
    private java.sql.Timestamp expeditedDetDt;
    @Column(name = "T1_ARCHIVE_DT")
    private java.sql.Timestamp t1ArchiveDt;
    @Column(name = "WORK_PH_NUM_EXT")
    private Long workPhNumExt;
    @Column(name = "APP_TYPE_CD")
    private java.lang.String appTypeCd;
    @Column(name = "STATUS_UPDATE_DT")
    private java.sql.Timestamp statusUpdateDt;
    @Column(name = "APPLICATION_STATUS_DT")
    private java.sql.Timestamp applicationStatusDt;
    @Column(name = "APP_FORAID_SW")
    private Character appForaidSw;
    @Column(name = "AGENCY_NAME")
    private java.lang.String agencyName;
    @Column(name = "COMPANY_ID")
    private Long companyId;
    @Column(name = "APP_MODE_CD")
    private java.lang.String appModeCd;
    @Column(name = "T2_APP_NUM")
    private java.lang.String t2AppNum;
    @Column(name = "T2_INDV_ID")
    private Long t2IndvId;
    @Column(name = "T2_HISTORY_SEQ")
    private Long t2HistorySeq;
    @Column(name = "HEAD_OF_HOUSEHOLD_SW")
    private Character headOfHouseholdSw;
    @Column(name = "EMPLOYEE_SW")
    private Character employeeSw;
    @Column(name = "T2_CREATE_USER_ID")
    private java.lang.String t2CreateUserId;
    @Column(name = "T2_CREATE_DT")
    private java.sql.Timestamp t2CreateDt;
    @Column(name = "T2_UNIQUE_TRANS_ID")
    private Long t2UniqueTransId;
    @Column(name = "T2_ARCHIVE_DT")
    private java.sql.Timestamp t2ArchiveDt;
    @Column(name = "T3_INDV_ID")
    private Long t3IndvId;
    @Column(name = "T3_HISTORY_SEQ")
    private Long t3HistorySeq;
    @Column(name = "T3_CREATE_USER_ID")
    private java.lang.String t3CreateUserId;
    @Column(name = "T3_CREATE_DT")
    private java.sql.Timestamp t3CreateDt;
    @Column(name = "T3_UNIQUE_TRANS_ID")
    private Long t3UniqueTransId;
    @Column(name = "SSN")
    private Long ssn;
    @Column(name = "DOB_DT")
    private java.sql.Timestamp dobDt;
    @Column(name = "LAST_NAME")
    private java.lang.String lastName;
    @Column(name = "FIRST_NAME")
    private java.lang.String firstName;
    @Column(name = "SUFX_NAME")
    private java.lang.String sufxName;
    @Column(name = "MID_NAME")
    private java.lang.String midName;
    @Column(name = "DOB_VRF_CD")
    private java.lang.String dobVrfCd;
    @Column(name = "RACE_CD")
    private java.lang.String raceCd;
    @Column(name = "SSA_VALIDATION_SW")
    private Character ssaValidationSw;
    @Column(name = "SSN_VRF_CD")
    private java.lang.String ssnVrfCd;
    @Column(name = "ETHNICITY_CD")
    private java.lang.String ethnicityCd;
    @Column(name = "GENDER_CD")
    private Character genderCd;
    @Column(name = "INACTIVE_IND")
    private Character inactiveInd;
    @Column(name = "FILE_CLEARANCE_SW")
    private Character fileClearanceSw;
    @Column(name = "MERGE_SEPARATE_IND")
    private java.lang.String mergeSeparateInd;
    @Column(name = "DEATH_DT")
    private java.sql.Timestamp deathDt;
    @Column(name = "MHMR_CASE_NUM")
    private java.lang.String mhmrCaseNum;
    @Column(name = "DECEASED_DT_VRF_CD")
    private java.lang.String deceasedDtVrfCd;
    @Column(name = "T3_ARCHIVE_DT")
    private java.sql.Timestamp t3ArchiveDt;
    @Column(name = "DELETE_SW")
    private Character deleteSw;
    @Column(name = "CLAIMED_SSN")
    private Long claimedSsn;
    @Column(name = "SSA_VRF_CD")
    private java.lang.String ssaVrfCd;
    @Column(name = "T4_CASE_NUM")
    private Long t4CaseNum;
    @Column(name = "T4_HISTORY_SEQ")
    private Long t4HistorySeq;
    @Column(name = "T4_CREATE_USER_ID")
    private java.lang.String t4CreateUserId;
    @Column(name = "T4_CREATE_DT")
    private java.sql.Timestamp t4CreateDt;
    @Column(name = "T4_UNIQUE_TRANS_ID")
    private Long t4UniqueTransId;
    @Column(name = "CASE_CLOSED_DT")
    private java.sql.Timestamp caseClosedDt;
    @Column(name = "CASE_STATUS_CD")
    private java.lang.String caseStatusCd;
    @Column(name = "CASE_MODE_CD")
    private java.lang.String caseModeCd;
    @Column(name = "REACTIVATION_IND")
    private Character reactivationInd;
    @Column(name = "REACTIVATION_DT")
    private java.sql.Timestamp reactivationDt;
    @Column(name = "INTERVIEW_DT")
    private java.sql.Timestamp interviewDt;
    @Column(name = "T4_APP_RECVD_DT")
    private java.sql.Timestamp t4AppRecvdDt;
    @Column(name = "T4_OFFICE_NUM")
    private Long t4OfficeNum;
    @Column(name = "T4_AUTH_REP_SW")
    private Character t4AuthRepSw;
    @Column(name = "T4_ARCHIVE_DT")
    private java.sql.Timestamp t4ArchiveDt;
    @Column(name = "INTERVIEW_TYPE_CD")
    private java.lang.String interviewTypeCd;
    @Column(name = "UNABLE_TO_LOCATE_SW")
    private Character unableToLocateSw;
    @Column(name = "ACTION_DT")
    private java.sql.Timestamp actionDt;
    @Column(name = "T4_RELATIONSHIP_INDV_CD")
    private java.lang.String t4RelationshipIndvCd;
    @Column(name = "PROCESS_ID")
    private java.lang.String processId;
    @Column(name = "USER_ACTION_CD")
    private java.lang.String userActionCd;
    //    @Column(name = "AR_APPLICATION_FOR_AID_RID")
//    private oracle.sql.ROWID arApplicationForAidRid;
//    @Column(name = "AR_APP_INDV_RID")
//    private oracle.sql.ROWID arAppIndvRid;
//    @Column(name = "DC_INDV_RID")
//    private oracle.sql.ROWID dcIndvRid;
//    @Column(name = "DC_CASES_RID")
//    private oracle.sql.ROWID dcCasesRid;
    @Column(name = "DATE_TIME_REGISTERED")
    private java.sql.Timestamp dateTimeRegistered;
    @Column(name = "ESTIMATED_DOB_SW")
    private Character estimatedDobSw;
    @Column(name = "TRIBAL_CASE_SW")
    private java.lang.String tribalCaseSw;
}
