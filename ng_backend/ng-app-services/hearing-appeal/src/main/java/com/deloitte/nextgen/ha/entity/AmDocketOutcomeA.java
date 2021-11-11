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
 * AmDocketOutcomeA generated by hbm2java
 */
@Entity
@Table(name="AM_DOCKET_OUTCOME_A"
    ,schema="IE_APP_ONLINE"
)
public class AmDocketOutcomeA  implements java.io.Serializable {


     private AmDocketOutcomeAId id;
     private String docketStatus;
     private String docketOutcome;
     private String createUserId;
     private LocalDate createDt;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;
     private String auditUserId;
     private LocalDate auditDt;

    public AmDocketOutcomeA() {
    }

    public AmDocketOutcomeA(AmDocketOutcomeAId id, String docketStatus, String docketOutcome, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt) {
       this.id = id;
       this.docketStatus = docketStatus;
       this.docketOutcome = docketOutcome;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.auditUserId = auditUserId;
       this.auditDt = auditDt;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="outcomeSeqNum", column=@Column(name="OUTCOME_SEQ_NUM", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="historySeq", column=@Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0) ) } )
    public AmDocketOutcomeAId getId() {
        return this.id;
    }
    
    public void setId(AmDocketOutcomeAId id) {
        this.id = id;
    }

    
    @Column(name="DOCKET_STATUS", nullable=false, length=20)
    public String getDocketStatus() {
        return this.docketStatus;
    }
    
    public void setDocketStatus(String docketStatus) {
        this.docketStatus = docketStatus;
    }

    
    @Column(name="DOCKET_OUTCOME", nullable=false, length=50)
    public String getDocketOutcome() {
        return this.docketOutcome;
    }
    
    public void setDocketOutcome(String docketOutcome) {
        this.docketOutcome = docketOutcome;
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


