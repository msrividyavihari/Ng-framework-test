package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AmKidsCltStaging generated by hbm2java
 */
@Entity
@Table(name="AM_KIDS_CLT_STAGING"
    ,schema="IE_APP_ONLINE"
)
public class AmKidsCltStaging  implements java.io.Serializable {


     private BigInteger cltId;
     private BigInteger kidsSummaryId;
     private String cltNameLst;
     private String cltNameFst;
     private Character cltNameMi;
     private String cltSsn;
     private String cltCaseId;
     private String cltRegionName;
     private String cltCaseStatus;
     private BigInteger cltHomePhone;
     private BigInteger cltBusinessPhone;
     private BigInteger cltBusinessExtn;
     private BigInteger cltCellPhone;
     private Character cltFamilyViolence;
     private String cltMailAdrLine1;
     private String cltMailAdrLine2;
     private String cltMailAdrCity;
     private String cltMailAdrState;
     private String cltMailAdrZip;
     private String cltResdAdrLine1;
     private String cltResdAdrLine2;
     private String cltResdAdrCity;
     private String cltResdAdrState;
     private String cltResdAdrZip;
     private String cltErrCd;
     private String kidsCaseType;
     private BigInteger historySeq;
     private String createUserId;
     private LocalDate createDt;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;

    public AmKidsCltStaging() {
    }

	
    public AmKidsCltStaging(BigInteger cltId, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
        this.cltId = cltId;
        this.historySeq = historySeq;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
    }
    public AmKidsCltStaging(BigInteger cltId, BigInteger kidsSummaryId, String cltNameLst, String cltNameFst, Character cltNameMi, String cltSsn, String cltCaseId, String cltRegionName, String cltCaseStatus, BigInteger cltHomePhone, BigInteger cltBusinessPhone, BigInteger cltBusinessExtn, BigInteger cltCellPhone, Character cltFamilyViolence, String cltMailAdrLine1, String cltMailAdrLine2, String cltMailAdrCity, String cltMailAdrState, String cltMailAdrZip, String cltResdAdrLine1, String cltResdAdrLine2, String cltResdAdrCity, String cltResdAdrState, String cltResdAdrZip, String cltErrCd, String kidsCaseType, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
       this.cltId = cltId;
       this.kidsSummaryId = kidsSummaryId;
       this.cltNameLst = cltNameLst;
       this.cltNameFst = cltNameFst;
       this.cltNameMi = cltNameMi;
       this.cltSsn = cltSsn;
       this.cltCaseId = cltCaseId;
       this.cltRegionName = cltRegionName;
       this.cltCaseStatus = cltCaseStatus;
       this.cltHomePhone = cltHomePhone;
       this.cltBusinessPhone = cltBusinessPhone;
       this.cltBusinessExtn = cltBusinessExtn;
       this.cltCellPhone = cltCellPhone;
       this.cltFamilyViolence = cltFamilyViolence;
       this.cltMailAdrLine1 = cltMailAdrLine1;
       this.cltMailAdrLine2 = cltMailAdrLine2;
       this.cltMailAdrCity = cltMailAdrCity;
       this.cltMailAdrState = cltMailAdrState;
       this.cltMailAdrZip = cltMailAdrZip;
       this.cltResdAdrLine1 = cltResdAdrLine1;
       this.cltResdAdrLine2 = cltResdAdrLine2;
       this.cltResdAdrCity = cltResdAdrCity;
       this.cltResdAdrState = cltResdAdrState;
       this.cltResdAdrZip = cltResdAdrZip;
       this.cltErrCd = cltErrCd;
       this.kidsCaseType = kidsCaseType;
       this.historySeq = historySeq;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
    }
   
     @Id 

    
    @Column(name="CLT_ID", unique=true, nullable=false, precision=22, scale=0)
    public BigInteger getCltId() {
        return this.cltId;
    }
    
    public void setCltId(BigInteger cltId) {
        this.cltId = cltId;
    }

    
    @Column(name="KIDS_SUMMARY_ID", precision=22, scale=0)
    public BigInteger getKidsSummaryId() {
        return this.kidsSummaryId;
    }
    
    public void setKidsSummaryId(BigInteger kidsSummaryId) {
        this.kidsSummaryId = kidsSummaryId;
    }

    
    @Column(name="CLT_NAME_LST", length=17)
    public String getCltNameLst() {
        return this.cltNameLst;
    }
    
    public void setCltNameLst(String cltNameLst) {
        this.cltNameLst = cltNameLst;
    }

    
    @Column(name="CLT_NAME_FST", length=12)
    public String getCltNameFst() {
        return this.cltNameFst;
    }
    
    public void setCltNameFst(String cltNameFst) {
        this.cltNameFst = cltNameFst;
    }

    
    @Column(name="CLT_NAME_MI", length=1)
    public Character getCltNameMi() {
        return this.cltNameMi;
    }
    
    public void setCltNameMi(Character cltNameMi) {
        this.cltNameMi = cltNameMi;
    }

    
    @Column(name="CLT_SSN", length=9)
    public String getCltSsn() {
        return this.cltSsn;
    }
    
    public void setCltSsn(String cltSsn) {
        this.cltSsn = cltSsn;
    }

    
    @Column(name="CLT_CASE_ID", length=9)
    public String getCltCaseId() {
        return this.cltCaseId;
    }
    
    public void setCltCaseId(String cltCaseId) {
        this.cltCaseId = cltCaseId;
    }

    
    @Column(name="CLT_REGION_NAME", length=11)
    public String getCltRegionName() {
        return this.cltRegionName;
    }
    
    public void setCltRegionName(String cltRegionName) {
        this.cltRegionName = cltRegionName;
    }

    
    @Column(name="CLT_CASE_STATUS", length=4)
    public String getCltCaseStatus() {
        return this.cltCaseStatus;
    }
    
    public void setCltCaseStatus(String cltCaseStatus) {
        this.cltCaseStatus = cltCaseStatus;
    }

    
    @Column(name="CLT_HOME_PHONE", precision=22, scale=0)
    public BigInteger getCltHomePhone() {
        return this.cltHomePhone;
    }
    
    public void setCltHomePhone(BigInteger cltHomePhone) {
        this.cltHomePhone = cltHomePhone;
    }

    
    @Column(name="CLT_BUSINESS_PHONE", precision=22, scale=0)
    public BigInteger getCltBusinessPhone() {
        return this.cltBusinessPhone;
    }
    
    public void setCltBusinessPhone(BigInteger cltBusinessPhone) {
        this.cltBusinessPhone = cltBusinessPhone;
    }

    
    @Column(name="CLT_BUSINESS_EXTN", precision=22, scale=0)
    public BigInteger getCltBusinessExtn() {
        return this.cltBusinessExtn;
    }
    
    public void setCltBusinessExtn(BigInteger cltBusinessExtn) {
        this.cltBusinessExtn = cltBusinessExtn;
    }

    
    @Column(name="CLT_CELL_PHONE", precision=22, scale=0)
    public BigInteger getCltCellPhone() {
        return this.cltCellPhone;
    }
    
    public void setCltCellPhone(BigInteger cltCellPhone) {
        this.cltCellPhone = cltCellPhone;
    }

    
    @Column(name="CLT_FAMILY_VIOLENCE", length=1)
    public Character getCltFamilyViolence() {
        return this.cltFamilyViolence;
    }
    
    public void setCltFamilyViolence(Character cltFamilyViolence) {
        this.cltFamilyViolence = cltFamilyViolence;
    }

    
    @Column(name="CLT_MAIL_ADR_LINE1", length=40)
    public String getCltMailAdrLine1() {
        return this.cltMailAdrLine1;
    }
    
    public void setCltMailAdrLine1(String cltMailAdrLine1) {
        this.cltMailAdrLine1 = cltMailAdrLine1;
    }

    
    @Column(name="CLT_MAIL_ADR_LINE2", length=40)
    public String getCltMailAdrLine2() {
        return this.cltMailAdrLine2;
    }
    
    public void setCltMailAdrLine2(String cltMailAdrLine2) {
        this.cltMailAdrLine2 = cltMailAdrLine2;
    }

    
    @Column(name="CLT_MAIL_ADR_CITY", length=20)
    public String getCltMailAdrCity() {
        return this.cltMailAdrCity;
    }
    
    public void setCltMailAdrCity(String cltMailAdrCity) {
        this.cltMailAdrCity = cltMailAdrCity;
    }

    
    @Column(name="CLT_MAIL_ADR_STATE", length=2)
    public String getCltMailAdrState() {
        return this.cltMailAdrState;
    }
    
    public void setCltMailAdrState(String cltMailAdrState) {
        this.cltMailAdrState = cltMailAdrState;
    }

    
    @Column(name="CLT_MAIL_ADR_ZIP", length=11)
    public String getCltMailAdrZip() {
        return this.cltMailAdrZip;
    }
    
    public void setCltMailAdrZip(String cltMailAdrZip) {
        this.cltMailAdrZip = cltMailAdrZip;
    }

    
    @Column(name="CLT_RESD_ADR_LINE1", length=40)
    public String getCltResdAdrLine1() {
        return this.cltResdAdrLine1;
    }
    
    public void setCltResdAdrLine1(String cltResdAdrLine1) {
        this.cltResdAdrLine1 = cltResdAdrLine1;
    }

    
    @Column(name="CLT_RESD_ADR_LINE2", length=40)
    public String getCltResdAdrLine2() {
        return this.cltResdAdrLine2;
    }
    
    public void setCltResdAdrLine2(String cltResdAdrLine2) {
        this.cltResdAdrLine2 = cltResdAdrLine2;
    }

    
    @Column(name="CLT_RESD_ADR_CITY", length=20)
    public String getCltResdAdrCity() {
        return this.cltResdAdrCity;
    }
    
    public void setCltResdAdrCity(String cltResdAdrCity) {
        this.cltResdAdrCity = cltResdAdrCity;
    }

    
    @Column(name="CLT_RESD_ADR_STATE", length=2)
    public String getCltResdAdrState() {
        return this.cltResdAdrState;
    }
    
    public void setCltResdAdrState(String cltResdAdrState) {
        this.cltResdAdrState = cltResdAdrState;
    }

    
    @Column(name="CLT_RESD_ADR_ZIP", length=11)
    public String getCltResdAdrZip() {
        return this.cltResdAdrZip;
    }
    
    public void setCltResdAdrZip(String cltResdAdrZip) {
        this.cltResdAdrZip = cltResdAdrZip;
    }

    
    @Column(name="CLT_ERR_CD", length=3)
    public String getCltErrCd() {
        return this.cltErrCd;
    }
    
    public void setCltErrCd(String cltErrCd) {
        this.cltErrCd = cltErrCd;
    }

    
    @Column(name="KIDS_CASE_TYPE", length=8)
    public String getKidsCaseType() {
        return this.kidsCaseType;
    }
    
    public void setKidsCaseType(String kidsCaseType) {
        this.kidsCaseType = kidsCaseType;
    }

    
    @Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0)
    public BigInteger getHistorySeq() {
        return this.historySeq;
    }
    
    public void setHistorySeq(BigInteger historySeq) {
        this.historySeq = historySeq;
    }

    
    @Column(name="CREATE_USER_ID", nullable=false, length=20)
    public String getCreateUserId() {
        return this.createUserId;
    }
    
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    
    @Column(name="CREATE_DT", nullable=false, length=7)
    public LocalDate getCreateDt() {
        return this.createDt;
    }
    
    public void setCreateDt(LocalDate createDt) {
        this.createDt = createDt;
    }

    
    @Column(name="UNIQUE_TRANS_ID", nullable=false, precision=22, scale=0)
    public BigInteger getUniqueTransId() {
        return this.uniqueTransId;
    }
    
    public void setUniqueTransId(BigInteger uniqueTransId) {
        this.uniqueTransId = uniqueTransId;
    }

    
    @Column(name="ARCHIVE_DT", nullable=false, length=7)
    public LocalDate getArchiveDt() {
        return this.archiveDt;
    }
    
    public void setArchiveDt(LocalDate archiveDt) {
        this.archiveDt = archiveDt;
    }




}


