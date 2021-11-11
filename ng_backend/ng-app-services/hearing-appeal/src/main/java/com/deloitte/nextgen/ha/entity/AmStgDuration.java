package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AmStgDuration generated by hbm2java
 */
@Entity
@Table(name="AM_STG_DURATION"
    ,schema="IE_APP_ONLINE"
)
public class AmStgDuration  implements java.io.Serializable {


     private BigInteger stgDurSeqNum;
     private BigInteger docketLength;
     private String stagDuration;
     private String createUserId;
     private LocalDate createDt;
     private LocalDate updateDt;
     private String updateUserId;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;

    public AmStgDuration() {
    }

	
    public AmStgDuration(BigInteger stgDurSeqNum, BigInteger docketLength, String stagDuration, LocalDate createDt) {
        this.stgDurSeqNum = stgDurSeqNum;
        this.docketLength = docketLength;
        this.stagDuration = stagDuration;
        this.createDt = createDt;
    }
    public AmStgDuration(BigInteger stgDurSeqNum, BigInteger docketLength, String stagDuration, String createUserId, LocalDate createDt, LocalDate updateDt, String updateUserId, BigInteger uniqueTransId, LocalDate archiveDt) {
       this.stgDurSeqNum = stgDurSeqNum;
       this.docketLength = docketLength;
       this.stagDuration = stagDuration;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.updateDt = updateDt;
       this.updateUserId = updateUserId;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
    }
   
     @Id 

    
    @Column(name="STG_DUR_SEQ_NUM", unique=true, nullable=false, precision=22, scale=0)
    public BigInteger getStgDurSeqNum() {
        return this.stgDurSeqNum;
    }
    
    public void setStgDurSeqNum(BigInteger stgDurSeqNum) {
        this.stgDurSeqNum = stgDurSeqNum;
    }

    
    @Column(name="DOCKET_LENGTH", nullable=false, precision=22, scale=0)
    public BigInteger getDocketLength() {
        return this.docketLength;
    }
    
    public void setDocketLength(BigInteger docketLength) {
        this.docketLength = docketLength;
    }

    
    @Column(name="STAG_DURATION", nullable=false, length=20)
    public String getStagDuration() {
        return this.stagDuration;
    }
    
    public void setStagDuration(String stagDuration) {
        this.stagDuration = stagDuration;
    }

    
    @Column(name="CREATE_USER_ID", length=128)
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

    
    @Column(name="UPDATE_DT", length=7)
    public LocalDate getUpdateDt() {
        return this.updateDt;
    }
    
    public void setUpdateDt(LocalDate updateDt) {
        this.updateDt = updateDt;
    }

    
    @Column(name="UPDATE_USER_ID", length=128)
    public String getUpdateUserId() {
        return this.updateUserId;
    }
    
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    
    @Column(name="UNIQUE_TRANS_ID", precision=22, scale=0)
    public BigInteger getUniqueTransId() {
        return this.uniqueTransId;
    }
    
    public void setUniqueTransId(BigInteger uniqueTransId) {
        this.uniqueTransId = uniqueTransId;
    }

    
    @Column(name="ARCHIVE_DT", length=7)
    public LocalDate getArchiveDt() {
        return this.archiveDt;
    }
    
    public void setArchiveDt(LocalDate archiveDt) {
        this.archiveDt = archiveDt;
    }




}

