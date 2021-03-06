package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AmProgramParamsA generated by hbm2java
 */
@Entity
@Table(name="AM_PROGRAM_PARAMS_A"
    ,schema="IE_APP_ONLINE"
)
public class AmProgramParamsA  implements java.io.Serializable {


     private AmProgramParamsAId id;
     private BigInteger netDaysManualSched;
     private BigInteger daysFirstHearing;
     private BigInteger continuanceLimit;
     private BigInteger maxWeeksHearing;
     private String createUserId;
     private LocalDate createDt;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;
     private String auditUserId;
     private LocalDate auditDt;

    public AmProgramParamsA() {
    }

	
    public AmProgramParamsA(AmProgramParamsAId id, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt) {
        this.id = id;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
        this.auditUserId = auditUserId;
        this.auditDt = auditDt;
    }
    public AmProgramParamsA(AmProgramParamsAId id, BigInteger netDaysManualSched, BigInteger daysFirstHearing, BigInteger continuanceLimit, BigInteger maxWeeksHearing, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt) {
       this.id = id;
       this.netDaysManualSched = netDaysManualSched;
       this.daysFirstHearing = daysFirstHearing;
       this.continuanceLimit = continuanceLimit;
       this.maxWeeksHearing = maxWeeksHearing;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.auditUserId = auditUserId;
       this.auditDt = auditDt;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="programCd", column=@Column(name="PROGRAM_CD", nullable=false, length=5) ), 
        @AttributeOverride(name="historySeq", column=@Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0) ) } )
    public AmProgramParamsAId getId() {
        return this.id;
    }
    
    public void setId(AmProgramParamsAId id) {
        this.id = id;
    }

    
    @Column(name="NET_DAYS_MANUAL_SCHED", precision=22, scale=0)
    public BigInteger getNetDaysManualSched() {
        return this.netDaysManualSched;
    }
    
    public void setNetDaysManualSched(BigInteger netDaysManualSched) {
        this.netDaysManualSched = netDaysManualSched;
    }

    
    @Column(name="DAYS_FIRST_HEARING", precision=22, scale=0)
    public BigInteger getDaysFirstHearing() {
        return this.daysFirstHearing;
    }
    
    public void setDaysFirstHearing(BigInteger daysFirstHearing) {
        this.daysFirstHearing = daysFirstHearing;
    }

    
    @Column(name="CONTINUANCE_LIMIT", precision=22, scale=0)
    public BigInteger getContinuanceLimit() {
        return this.continuanceLimit;
    }
    
    public void setContinuanceLimit(BigInteger continuanceLimit) {
        this.continuanceLimit = continuanceLimit;
    }

    
    @Column(name="MAX_WEEKS_HEARING", precision=22, scale=0)
    public BigInteger getMaxWeeksHearing() {
        return this.maxWeeksHearing;
    }
    
    public void setMaxWeeksHearing(BigInteger maxWeeksHearing) {
        this.maxWeeksHearing = maxWeeksHearing;
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

    
    @Column(name="AUDIT_USER_ID", nullable=false, length=20)
    public String getAuditUserId() {
        return this.auditUserId;
    }
    
    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    
    @Column(name="AUDIT_DT", nullable=false, length=7)
    public LocalDate getAuditDt() {
        return this.auditDt;
    }
    
    public void setAuditDt(LocalDate auditDt) {
        this.auditDt = auditDt;
    }




}


