package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AmDocketInfo generated by hbm2java
 */
@Entity
@Table(name="AM_DOCKET_INFO"
    ,schema="IE_APP_ONLINE"
)
public class AmDocketInfo  implements java.io.Serializable {


     private BigInteger aplNum;
     private BigInteger docketAplNum;
     private BigInteger docketId;
     private LocalDate beginTime;
     private LocalDate createDt;
     private LocalDate updateDt;
     private String createUserId;
     private String updateUserId;
     private LocalDate archiveDt;
     private BigInteger uniqueTransId;

    public AmDocketInfo() {
    }

	
    public AmDocketInfo(BigInteger aplNum, LocalDate createDt, String createUserId, LocalDate archiveDt) {
        this.aplNum = aplNum;
        this.createDt = createDt;
        this.createUserId = createUserId;
        this.archiveDt = archiveDt;
    }
    public AmDocketInfo(BigInteger aplNum, BigInteger docketAplNum, BigInteger docketId, LocalDate beginTime, LocalDate createDt, LocalDate updateDt, String createUserId, String updateUserId, LocalDate archiveDt, BigInteger uniqueTransId) {
       this.aplNum = aplNum;
       this.docketAplNum = docketAplNum;
       this.docketId = docketId;
       this.beginTime = beginTime;
       this.createDt = createDt;
       this.updateDt = updateDt;
       this.createUserId = createUserId;
       this.updateUserId = updateUserId;
       this.archiveDt = archiveDt;
       this.uniqueTransId = uniqueTransId;
    }
   
     @Id 

    
    @Column(name="APL_NUM", unique=true, nullable=false, precision=22, scale=0)
    public BigInteger getAplNum() {
        return this.aplNum;
    }
    
    public void setAplNum(BigInteger aplNum) {
        this.aplNum = aplNum;
    }

    
    @Column(name="DOCKET_APL_NUM", precision=22, scale=0)
    public BigInteger getDocketAplNum() {
        return this.docketAplNum;
    }
    
    public void setDocketAplNum(BigInteger docketAplNum) {
        this.docketAplNum = docketAplNum;
    }

    
    @Column(name="DOCKET_ID", precision=22, scale=0)
    public BigInteger getDocketId() {
        return this.docketId;
    }
    
    public void setDocketId(BigInteger docketId) {
        this.docketId = docketId;
    }

    
    @Column(name="BEGIN_TIME", length=7)
    public LocalDate getBeginTime() {
        return this.beginTime;
    }
    
    public void setBeginTime(LocalDate beginTime) {
        this.beginTime = beginTime;
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

    
    @Column(name="CREATE_USER_ID", nullable=false, length=128)
    public String getCreateUserId() {
        return this.createUserId;
    }
    
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    
    @Column(name="UPDATE_USER_ID", length=128)
    public String getUpdateUserId() {
        return this.updateUserId;
    }
    
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    
    @Column(name="ARCHIVE_DT", nullable=false, length=7)
    public LocalDate getArchiveDt() {
        return this.archiveDt;
    }
    
    public void setArchiveDt(LocalDate archiveDt) {
        this.archiveDt = archiveDt;
    }

    
    @Column(name="UNIQUE_TRANS_ID", precision=22, scale=0)
    public BigInteger getUniqueTransId() {
        return this.uniqueTransId;
    }
    
    public void setUniqueTransId(BigInteger uniqueTransId) {
        this.uniqueTransId = uniqueTransId;
    }




}

