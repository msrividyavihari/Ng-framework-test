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
 * AmImplementationReview generated by hbm2java
 */
@Entity
@Table(name="AM_IMPLEMENTATION_REVIEW"
    ,schema="IE_APP_ONLINE"
)
public class AmImplementationReview  implements java.io.Serializable {


     private AmImplementationReviewId id;
     private LocalDate finalOrderDt;
     private String implementionActionCd;
     private LocalDate implementationDt;
     private LocalDate aiSentDt;
     private LocalDate aiRcvdDt;
     private Character cobGrantedSw;
     private Character cobRemovedSw;
     private Character aplRsltEligSw;
     private String impNotesTxt;
     private BigInteger historySeq;
     private String createUserId;
     private LocalDate createDt;
     private LocalDate updateDt;
     private String updateUserId;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;

    public AmImplementationReview() {
    }

	
    public AmImplementationReview(AmImplementationReviewId id, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
        this.id = id;
        this.historySeq = historySeq;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
    }
    public AmImplementationReview(AmImplementationReviewId id, LocalDate finalOrderDt, String implementionActionCd, LocalDate implementationDt, LocalDate aiSentDt, LocalDate aiRcvdDt, Character cobGrantedSw, Character cobRemovedSw, Character aplRsltEligSw, String impNotesTxt, BigInteger historySeq, String createUserId, LocalDate createDt, LocalDate updateDt, String updateUserId, BigInteger uniqueTransId, LocalDate archiveDt) {
       this.id = id;
       this.finalOrderDt = finalOrderDt;
       this.implementionActionCd = implementionActionCd;
       this.implementationDt = implementationDt;
       this.aiSentDt = aiSentDt;
       this.aiRcvdDt = aiRcvdDt;
       this.cobGrantedSw = cobGrantedSw;
       this.cobRemovedSw = cobRemovedSw;
       this.aplRsltEligSw = aplRsltEligSw;
       this.impNotesTxt = impNotesTxt;
       this.historySeq = historySeq;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.updateDt = updateDt;
       this.updateUserId = updateUserId;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="aplNum", column=@Column(name="APL_NUM", nullable=false, precision=9, scale=0) ), 
        @AttributeOverride(name="impSeqNum", column=@Column(name="IMP_SEQ_NUM", nullable=false, precision=22, scale=0) ) } )
    public AmImplementationReviewId getId() {
        return this.id;
    }
    
    public void setId(AmImplementationReviewId id) {
        this.id = id;
    }

    
    @Column(name="FINAL_ORDER_DT", length=7)
    public LocalDate getFinalOrderDt() {
        return this.finalOrderDt;
    }
    
    public void setFinalOrderDt(LocalDate finalOrderDt) {
        this.finalOrderDt = finalOrderDt;
    }

    
    @Column(name="IMPLEMENTION_ACTION_CD", length=3)
    public String getImplementionActionCd() {
        return this.implementionActionCd;
    }
    
    public void setImplementionActionCd(String implementionActionCd) {
        this.implementionActionCd = implementionActionCd;
    }

    
    @Column(name="IMPLEMENTATION_DT", length=7)
    public LocalDate getImplementationDt() {
        return this.implementationDt;
    }
    
    public void setImplementationDt(LocalDate implementationDt) {
        this.implementationDt = implementationDt;
    }

    
    @Column(name="AI_SENT_DT", length=7)
    public LocalDate getAiSentDt() {
        return this.aiSentDt;
    }
    
    public void setAiSentDt(LocalDate aiSentDt) {
        this.aiSentDt = aiSentDt;
    }

    
    @Column(name="AI_RCVD_DT", length=7)
    public LocalDate getAiRcvdDt() {
        return this.aiRcvdDt;
    }
    
    public void setAiRcvdDt(LocalDate aiRcvdDt) {
        this.aiRcvdDt = aiRcvdDt;
    }

    
    @Column(name="COB_GRANTED_SW", length=1)
    public Character getCobGrantedSw() {
        return this.cobGrantedSw;
    }
    
    public void setCobGrantedSw(Character cobGrantedSw) {
        this.cobGrantedSw = cobGrantedSw;
    }

    
    @Column(name="COB_REMOVED_SW", length=1)
    public Character getCobRemovedSw() {
        return this.cobRemovedSw;
    }
    
    public void setCobRemovedSw(Character cobRemovedSw) {
        this.cobRemovedSw = cobRemovedSw;
    }

    
    @Column(name="APL_RSLT_ELIG_SW", length=1)
    public Character getAplRsltEligSw() {
        return this.aplRsltEligSw;
    }
    
    public void setAplRsltEligSw(Character aplRsltEligSw) {
        this.aplRsltEligSw = aplRsltEligSw;
    }

    
    @Column(name="IMP_NOTES_TXT", length=4000)
    public String getImpNotesTxt() {
        return this.impNotesTxt;
    }
    
    public void setImpNotesTxt(String impNotesTxt) {
        this.impNotesTxt = impNotesTxt;
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




}


