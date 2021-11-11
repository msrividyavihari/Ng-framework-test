package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.DcIndvStgRepository;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="DC_INDV_STG")
@EntityType(type= TypeEnum.ZERO, customRepository = DcIndvStgRepository.class)
@Data
@NoArgsConstructor
public class DcIndvStg  extends TypeZeroBaseEntity<Long> implements Serializable {
    @Id
    @Column(name = "INDV_ID")
    private Long indvId;
    @Column(name = "SSN")
    private Long ssn;
    @Column(name = "DOB_DT")
    private Timestamp dobDt;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "SUFX_NAME")
    private String sufxName;
    @Column(name = "MID_NAME")
    private String midName;
    @Column(name = "DOB_VRF_CD")
    private String dobVrfCd;
    @Column(name = "RACE_CD")
    private String raceCd;
    @Column(name = "SSA_VALIDATION_SW")
    private Character ssaValidationSw;
    @Column(name = "SSN_VRF_CD")
    private String ssnVrfCd;
    @Column(name = "ETHNICITY_CD")
    private String ethnicityCd;
    @Column(name = "GENDER_CD")
    private Character genderCd;
    @Column(name = "INACTIVE_IND")
    private Character inactiveInd;
    @Column(name = "FILE_CLEARANCE_SW")
    private Character fileClearanceSw;
    @Column(name = "MERGE_SEPARATE_IND")
    private String mergeSeparateInd;
    @Column(name = "DEATH_DT")
    private Timestamp deathDt;
    @Column(name = "MHMR_CASE_NUM")
    private String mhmrCaseNum;
    @Column(name = "DECEASED_DT_VRF_CD")
    private String deceasedDtVrfCd;
    @Column(name = "DELETE_SW")
    private Character deleteSw;
    @Column(name = "CLAIMED_SSN")
    private Long claimedSsn;
    @Column(name = "SSA_VRF_CD")
    private String ssaVrfCd;
    @Column(name = "ESTIMATED_DOB_SW")
    private Character estimatedDobSw;
    @Column(name = "AUTH_REP_SW")
    private Character authRepSw;
    @Column(name = "CITIZENSHIP_VRF")
    private String citizenshipVrf;
    @Column(name = "IDENTITY_VRF")
    private String identityVrf;
    @Column(name = "CITIZENSHIP_DT")
    private Timestamp citizenshipDt;
    @Column(name = "IDENTITY_DT")
    private Timestamp identityDt;
    @Column(name = "FILE_CLEARANCE_DT")
    private Timestamp fileClearanceDt;
    @Column(name = "EBT_SW")
    private Character ebtSw;
    @Column(name = "PREFIX_NAME")
    private String prefixName;
    @Column(name = "VOTE_REG_WISH_SW")
    private Character voteRegWishSw;
    @Column(name = "RES_ADDR_SW")
    private Character resAddrSw;
    @Column(name = "ADDR_FORMAT")
    private String addrFormat;
    @Column(name = "ADDR_LINE1")
    private String addrLine1;
    @Column(name = "ADDR_LINE2")
    private String addrLine2;
    @Column(name = "ADDR_LINE3")
    private String addrLine3;
    @Column(name = "ADDR_COUNTY_CD")
    private String addrCountyCd;
    @Column(name = "ADDR_CITY")
    private String addrCity;
    @Column(name = "ADDR_STATE_CD")
    private String addrStateCd;
    @Column(name = "ADDR_MILITARY")
    private String addrMilitary;
    @Column(name = "APO_FPO_ADDR")
    private String apoFpoAddr;
    @Column(name = "ADDR_ZIP5")
    private String addrZip5;
    @Column(name = "ADDR_ZIP4")
    private String addrZip4;
    @Column(name = "ADDR_CARE_OF_LINE")
    private String addrCareOfLine;
    @Column(name = "TAX_STATUS")
    private String taxStatus;
    @Column(name = "TAX_STATUS_VERF")
    private String taxStatusVerf;
    @Column(name = "INTERPRETER_SW")
    private String interpreterSw;
    @Column(name = "COMMUNICATION_ASST")
    private String communicationAsst;
    @Column(name = "PRIMARY_LANG")
    private String primaryLang;
    @Column(name = "OTHER_LANGUAGE")
    private String otherLanguage;
    @Column(name = "DISABILITY_ACCOM")
    private String disabilityAccom;
    @Column(name = "OTHER_ACCOMODATION")
    private String otherAccomodation;
    @Column(name = "REPRESENTATIVE_SW")
    private String representativeSw;
    @Column(name = "EBT_CARD_SW")
    private String ebtCardSw;
    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;
    @Column(name = "ORGANIZATION_ID")
    private String organizationId;
    @Column(name = "VERF_RECEIVED_DT")
    private Timestamp verfReceivedDt;
    @Column(name = "GMWD_FMONTH_CNT")
    private Long gmwdFmonthCnt;
}
