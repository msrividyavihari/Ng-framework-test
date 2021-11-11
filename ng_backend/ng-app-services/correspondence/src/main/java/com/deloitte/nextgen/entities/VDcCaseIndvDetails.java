package com.deloitte.nextgen.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "V_DC_CASE_INDV_DETAILS")
@NoArgsConstructor
public class VDcCaseIndvDetails implements Serializable {
    @Id
    @Column(name = "T1_INDV_ID")
    private Long t1IndvId;
    @Column(name = "CASE_NUM")
    private Long caseNum;
    @Column(name = "T1_HISTORY_SEQ")
    private Long t1HistorySeq;
    @Column(name = "ACTIVE_IN_CASE_SW")
    private Character activeInCaseSw;
    @Column(name = "T1_CREATE_USER_ID")
    private java.lang.String t1CreateUserId;
    @Column(name = "T1_CREATE_DT")
    private java.sql.Timestamp t1CreateDt;
    @Column(name = "T1_UNIQUE_TRANS_ID")
    private Long t1UniqueTransId;
    @Column(name = "EFF_BEGIN_DT")
    private java.sql.Timestamp effBeginDt;
    @Column(name = "EFF_END_DT")
    private java.sql.Timestamp effEndDt;
    @Column(name = "HEAD_OF_HOUSEHOLD_SW")
    private Character headOfHouseholdSw;
    @Column(name = "EMPLOYEE_SW")
    private Character employeeSw;
    @Column(name = "T1_ARCHIVE_DT")
    private java.sql.Timestamp t1ArchiveDt;
    @Column(name = "T2_INDV_ID")
    private Long t2IndvId;
    @Column(name = "T2_HISTORY_SEQ")
    private Long t2HistorySeq;
    @Column(name = "T2_CREATE_USER_ID")
    private java.lang.String t2CreateUserId;
    @Column(name = "T2_CREATE_DT")
    private java.sql.Timestamp t2CreateDt;
    @Column(name = "T2_UNIQUE_TRANS_ID")
    private Long t2UniqueTransId;
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
    @Column(name = "T2_ARCHIVE_DT")
    private java.sql.Timestamp t2ArchiveDt;
    @Column(name = "DELETE_SW")
    private Character deleteSw;
    @Column(name = "CLAIMED_SSN")
    private Long claimedSsn;
    @Column(name = "SSA_VRF_CD")
    private java.lang.String ssaVrfCd;
    @Column(name = "TAX_STATUS")
    private String taxStatus;
}
