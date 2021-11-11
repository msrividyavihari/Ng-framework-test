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
 * AmDocketTypeHearingLocsA generated by hbm2java
 */
@Entity
@Table(name="AM_DOCKET_TYPE_HEARING_LOCS_A"
    ,schema="IE_APP_ONLINE"
)
public class AmDocketTypeHearingLocsA  implements java.io.Serializable {


     private AmDocketTypeHearingLocsAId id;
     private LocalDate efectEndDt;
     private String createUserId;
     private LocalDate createDt;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;
     private String auditUserId;
     private LocalDate auditDt;

    public AmDocketTypeHearingLocsA() {
    }

	
    public AmDocketTypeHearingLocsA(AmDocketTypeHearingLocsAId id, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt) {
        this.id = id;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
        this.auditUserId = auditUserId;
        this.auditDt = auditDt;
    }
    public AmDocketTypeHearingLocsA(AmDocketTypeHearingLocsAId id, LocalDate efectEndDt, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt) {
       this.id = id;
       this.efectEndDt = efectEndDt;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.auditUserId = auditUserId;
       this.auditDt = auditDt;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="docketTypeId", column=@Column(name="DOCKET_TYPE_ID", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="efectStartDt", column=@Column(name="EFECT_START_DT", nullable=false, length=7) ), 
        @AttributeOverride(name="hearingEmpId", column=@Column(name="HEARING_EMP_ID", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="historySeq", column=@Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0) ) } )
    public AmDocketTypeHearingLocsAId getId() {
        return this.id;
    }
    
    public void setId(AmDocketTypeHearingLocsAId id) {
        this.id = id;
    }

    
    @Column(name="EFECT_END_DT", length=7)
    public LocalDate getEfectEndDt() {
        return this.efectEndDt;
    }
    
    public void setEfectEndDt(LocalDate efectEndDt) {
        this.efectEndDt = efectEndDt;
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


