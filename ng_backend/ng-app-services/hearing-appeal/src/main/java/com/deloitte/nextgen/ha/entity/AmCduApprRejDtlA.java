package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AmCduApprRejDtlA generated by hbm2java
 */
@Entity
@Table(name="AM_CDU_APPR_REJ_DTL_A"
    ,schema="IE_APP_ONLINE"
)
public class AmCduApprRejDtlA  implements java.io.Serializable {


     private AmCduApprRejDtlAId id;
     private int aplNum;
     private LocalDate hearingDt;
     private String petitionTypCd;
     private String outcomeTypCd;
     private LocalDate petitionDt;
     private String petitionRsnTxt;
     private Character petRqstTimlySw;
     private Character orderSignedSw;
     private String orderTypCd;
     private LocalDate orderSignedDt;
     private LocalDate breifDueDt;
     private String createUserId;
     private LocalDate createDt;
     private LocalDate updateDt;
     private String updateUserId;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;
     private String auditUserId;
     private LocalDate auditDt;
     private LocalDateTime decisionDt;

    public AmCduApprRejDtlA() {
    }

	
    public AmCduApprRejDtlA(AmCduApprRejDtlAId id, int aplNum, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
        this.id = id;
        this.aplNum = aplNum;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
    }
    public AmCduApprRejDtlA(AmCduApprRejDtlAId id, int aplNum, LocalDate hearingDt, String petitionTypCd, String outcomeTypCd, LocalDate petitionDt, String petitionRsnTxt, Character petRqstTimlySw, Character orderSignedSw, String orderTypCd, LocalDate orderSignedDt, LocalDate breifDueDt, String createUserId, LocalDate createDt, LocalDate updateDt, String updateUserId, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt, LocalDateTime decisionDt) {
       this.id = id;
       this.aplNum = aplNum;
       this.hearingDt = hearingDt;
       this.petitionTypCd = petitionTypCd;
       this.outcomeTypCd = outcomeTypCd;
       this.petitionDt = petitionDt;
       this.petitionRsnTxt = petitionRsnTxt;
       this.petRqstTimlySw = petRqstTimlySw;
       this.orderSignedSw = orderSignedSw;
       this.orderTypCd = orderTypCd;
       this.orderSignedDt = orderSignedDt;
       this.breifDueDt = breifDueDt;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.updateDt = updateDt;
       this.updateUserId = updateUserId;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.auditUserId = auditUserId;
       this.auditDt = auditDt;
       this.decisionDt = decisionDt;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="cduSeqNum", column=@Column(name="CDU_SEQ_NUM", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="historySeq", column=@Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0) ) } )
    public AmCduApprRejDtlAId getId() {
        return this.id;
    }
    
    public void setId(AmCduApprRejDtlAId id) {
        this.id = id;
    }

    
    @Column(name="APL_NUM", nullable=false, precision=9, scale=0)
    public int getAplNum() {
        return this.aplNum;
    }
    
    public void setAplNum(int aplNum) {
        this.aplNum = aplNum;
    }

    
    @Column(name="HEARING_DT", length=7)
    public LocalDate getHearingDt() {
        return this.hearingDt;
    }
    
    public void setHearingDt(LocalDate hearingDt) {
        this.hearingDt = hearingDt;
    }

    
    @Column(name="PETITION_TYP_CD", length=3)
    public String getPetitionTypCd() {
        return this.petitionTypCd;
    }
    
    public void setPetitionTypCd(String petitionTypCd) {
        this.petitionTypCd = petitionTypCd;
    }

    
    @Column(name="OUTCOME_TYP_CD", length=3)
    public String getOutcomeTypCd() {
        return this.outcomeTypCd;
    }
    
    public void setOutcomeTypCd(String outcomeTypCd) {
        this.outcomeTypCd = outcomeTypCd;
    }

    
    @Column(name="PETITION_DT", length=7)
    public LocalDate getPetitionDt() {
        return this.petitionDt;
    }
    
    public void setPetitionDt(LocalDate petitionDt) {
        this.petitionDt = petitionDt;
    }

    
    @Column(name="PETITION_RSN_TXT", length=250)
    public String getPetitionRsnTxt() {
        return this.petitionRsnTxt;
    }
    
    public void setPetitionRsnTxt(String petitionRsnTxt) {
        this.petitionRsnTxt = petitionRsnTxt;
    }

    
    @Column(name="PET_RQST_TIMLY_SW", length=1)
    public Character getPetRqstTimlySw() {
        return this.petRqstTimlySw;
    }
    
    public void setPetRqstTimlySw(Character petRqstTimlySw) {
        this.petRqstTimlySw = petRqstTimlySw;
    }

    
    @Column(name="ORDER_SIGNED_SW", length=1)
    public Character getOrderSignedSw() {
        return this.orderSignedSw;
    }
    
    public void setOrderSignedSw(Character orderSignedSw) {
        this.orderSignedSw = orderSignedSw;
    }

    
    @Column(name="ORDER_TYP_CD", length=3)
    public String getOrderTypCd() {
        return this.orderTypCd;
    }
    
    public void setOrderTypCd(String orderTypCd) {
        this.orderTypCd = orderTypCd;
    }

    
    @Column(name="ORDER_SIGNED_DT", length=7)
    public LocalDate getOrderSignedDt() {
        return this.orderSignedDt;
    }
    
    public void setOrderSignedDt(LocalDate orderSignedDt) {
        this.orderSignedDt = orderSignedDt;
    }

    
    @Column(name="BREIF_DUE_DT", length=7)
    public LocalDate getBreifDueDt() {
        return this.breifDueDt;
    }
    
    public void setBreifDueDt(LocalDate breifDueDt) {
        this.breifDueDt = breifDueDt;
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

    
    @Column(name="UPDATE_DT", length=7)
    public LocalDate getUpdateDt() {
        return this.updateDt;
    }
    
    public void setUpdateDt(LocalDate updateDt) {
        this.updateDt = updateDt;
    }

    
    @Column(name="UPDATE_USER_ID", length=20)
    public String getUpdateUserId() {
        return this.updateUserId;
    }
    
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
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

    
    @Column(name="AUDIT_USER_ID", length=20)
    public String getAuditUserId() {
        return this.auditUserId;
    }
    
    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    
    @Column(name="AUDIT_DT", length=7)
    public LocalDate getAuditDt() {
        return this.auditDt;
    }
    
    public void setAuditDt(LocalDate auditDt) {
        this.auditDt = auditDt;
    }

    
    @Column(name="DECISION_DT", length=11)
    public LocalDateTime getDecisionDt() {
        return this.decisionDt;
    }
    
    public void setDecisionDt(LocalDateTime decisionDt) {
        this.decisionDt = decisionDt;
    }




}


