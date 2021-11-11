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
 * AmDigRec generated by hbm2java
 */
@Entity
@Table(name="AM_DIG_REC"
    ,schema="IE_APP_ONLINE"
)
public class AmDigRec  implements java.io.Serializable {


     private AmDigRecId id;
     private String filePath;
     private char deleteInd;
     private BigInteger historySeq;
     private String createUserId;
     private LocalDate createDt;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;

    public AmDigRec() {
    }

    public AmDigRec(AmDigRecId id, String filePath, char deleteInd, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
       this.id = id;
       this.filePath = filePath;
       this.deleteInd = deleteInd;
       this.historySeq = historySeq;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="aplNum", column=@Column(name="APL_NUM", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="fileName", column=@Column(name="FILE_NAME", nullable=false, length=300) ), 
        @AttributeOverride(name="seqNum", column=@Column(name="SEQ_NUM", nullable=false, precision=22, scale=0) ) } )
    public AmDigRecId getId() {
        return this.id;
    }
    
    public void setId(AmDigRecId id) {
        this.id = id;
    }

    
    @Column(name="FILE_PATH", nullable=false, length=200)
    public String getFilePath() {
        return this.filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    
    @Column(name="DELETE_IND", nullable=false, length=1)
    public char getDeleteInd() {
        return this.deleteInd;
    }
    
    public void setDeleteInd(char deleteInd) {
        this.deleteInd = deleteInd;
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

