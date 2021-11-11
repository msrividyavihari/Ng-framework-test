package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AmAppealPenalties generated by hbm2java
 */
@Entity
@Table(name="AM_APPEAL_PENALTIES"
    ,schema="IE_APP_ONLINE"
)
public class AmAppealPenalties  implements java.io.Serializable {


     private AmAppealPenaltiesId id;
     private String penaltyTypeCd;
     private BigDecimal penaltyAmount;
     private Character actualHarmInd;
     private BigInteger historySeq;
     private String createUserId;
     private LocalDate createDt;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;
     private LocalDate implementedDate;
     private LocalDate calcultedDate;

    public AmAppealPenalties() {
    }

	
    public AmAppealPenalties(AmAppealPenaltiesId id, String penaltyTypeCd, BigDecimal penaltyAmount, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
        this.id = id;
        this.penaltyTypeCd = penaltyTypeCd;
        this.penaltyAmount = penaltyAmount;
        this.historySeq = historySeq;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
    }
    public AmAppealPenalties(AmAppealPenaltiesId id, String penaltyTypeCd, BigDecimal penaltyAmount, Character actualHarmInd, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, LocalDate implementedDate, LocalDate calcultedDate) {
       this.id = id;
       this.penaltyTypeCd = penaltyTypeCd;
       this.penaltyAmount = penaltyAmount;
       this.actualHarmInd = actualHarmInd;
       this.historySeq = historySeq;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.implementedDate = implementedDate;
       this.calcultedDate = calcultedDate;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="amAppealPenaltiesSeqNum", column=@Column(name="AM_APPEAL_PENALTIES_SEQ_NUM", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="aplNum", column=@Column(name="APL_NUM", nullable=false, precision=22, scale=0) ) } )
    public AmAppealPenaltiesId getId() {
        return this.id;
    }
    
    public void setId(AmAppealPenaltiesId id) {
        this.id = id;
    }

    
    @Column(name="PENALTY_TYPE_CD", nullable=false, length=3)
    public String getPenaltyTypeCd() {
        return this.penaltyTypeCd;
    }
    
    public void setPenaltyTypeCd(String penaltyTypeCd) {
        this.penaltyTypeCd = penaltyTypeCd;
    }

    
    @Column(name="PENALTY_AMOUNT", nullable=false)
    public BigDecimal getPenaltyAmount() {
        return this.penaltyAmount;
    }
    
    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    
    @Column(name="ACTUAL_HARM_IND", length=1)
    public Character getActualHarmInd() {
        return this.actualHarmInd;
    }
    
    public void setActualHarmInd(Character actualHarmInd) {
        this.actualHarmInd = actualHarmInd;
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

    
    @Column(name="IMPLEMENTED_DATE", length=7)
    public LocalDate getImplementedDate() {
        return this.implementedDate;
    }
    
    public void setImplementedDate(LocalDate implementedDate) {
        this.implementedDate = implementedDate;
    }

    
    @Column(name="CALCULTED_DATE", length=7)
    public LocalDate getCalcultedDate() {
        return this.calcultedDate;
    }
    
    public void setCalcultedDate(LocalDate calcultedDate) {
        this.calcultedDate = calcultedDate;
    }




}


