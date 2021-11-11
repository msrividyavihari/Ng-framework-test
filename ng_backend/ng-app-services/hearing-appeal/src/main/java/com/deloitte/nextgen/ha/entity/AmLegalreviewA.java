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
 * AmLegalreviewA generated by hbm2java
 */
@Entity
@Table(name="AM_LEGALREVIEW_A"
    ,schema="IE_APP_ONLINE"
)
public class AmLegalreviewA  implements java.io.Serializable {


     private AmLegalreviewAId id;
     private Character filedTimelySw;
     private Character hearingHeldSw;
     private Character fairIssueSw;
     private Character validFactSw;
     private Character vfdAiSw;
     private Character vfdAiRcvdSw;
     private Character vfdAiFactSw;
     private Character phoneContactSw;
     private Character cobTimelySw;
     private String resNotesTxt;
     private String goodCauseTimelyTxt;
     private String goodCauseCobTxt;
     private String vfdAiNoticeTxt;
     private LocalDate phoneContactDt;
     private String phoneContactTxt;
     private Character resReviewSw;
     private String createUserId;
     private LocalDate createDt;
     private LocalDate updateDt;
     private String updateUserId;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;
     private String auditUserId;
     private LocalDate auditDt;
     private Character failSubmitRedetSw;
     private Character cobRvwQlfySw;
     private Character goodCauseCobSw;
     private Character goodCauseUntimleySw;

    public AmLegalreviewA() {
    }

	
    public AmLegalreviewA(AmLegalreviewAId id, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
        this.id = id;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
    }
    public AmLegalreviewA(AmLegalreviewAId id, Character filedTimelySw, Character hearingHeldSw, Character fairIssueSw, Character validFactSw, Character vfdAiSw, Character vfdAiRcvdSw, Character vfdAiFactSw, Character phoneContactSw, Character cobTimelySw, String resNotesTxt, String goodCauseTimelyTxt, String goodCauseCobTxt, String vfdAiNoticeTxt, LocalDate phoneContactDt, String phoneContactTxt, Character resReviewSw, String createUserId, LocalDate createDt, LocalDate updateDt, String updateUserId, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt, Character failSubmitRedetSw, Character cobRvwQlfySw, Character goodCauseCobSw, Character goodCauseUntimleySw) {
       this.id = id;
       this.filedTimelySw = filedTimelySw;
       this.hearingHeldSw = hearingHeldSw;
       this.fairIssueSw = fairIssueSw;
       this.validFactSw = validFactSw;
       this.vfdAiSw = vfdAiSw;
       this.vfdAiRcvdSw = vfdAiRcvdSw;
       this.vfdAiFactSw = vfdAiFactSw;
       this.phoneContactSw = phoneContactSw;
       this.cobTimelySw = cobTimelySw;
       this.resNotesTxt = resNotesTxt;
       this.goodCauseTimelyTxt = goodCauseTimelyTxt;
       this.goodCauseCobTxt = goodCauseCobTxt;
       this.vfdAiNoticeTxt = vfdAiNoticeTxt;
       this.phoneContactDt = phoneContactDt;
       this.phoneContactTxt = phoneContactTxt;
       this.resReviewSw = resReviewSw;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.updateDt = updateDt;
       this.updateUserId = updateUserId;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.auditUserId = auditUserId;
       this.auditDt = auditDt;
       this.failSubmitRedetSw = failSubmitRedetSw;
       this.cobRvwQlfySw = cobRvwQlfySw;
       this.goodCauseCobSw = goodCauseCobSw;
       this.goodCauseUntimleySw = goodCauseUntimleySw;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="aplNum", column=@Column(name="APL_NUM", nullable=false, precision=10, scale=0) ), 
        @AttributeOverride(name="historySeq", column=@Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0) ) } )
    public AmLegalreviewAId getId() {
        return this.id;
    }
    
    public void setId(AmLegalreviewAId id) {
        this.id = id;
    }

    
    @Column(name="FILED_TIMELY_SW", length=1)
    public Character getFiledTimelySw() {
        return this.filedTimelySw;
    }
    
    public void setFiledTimelySw(Character filedTimelySw) {
        this.filedTimelySw = filedTimelySw;
    }

    
    @Column(name="HEARING_HELD_SW", length=1)
    public Character getHearingHeldSw() {
        return this.hearingHeldSw;
    }
    
    public void setHearingHeldSw(Character hearingHeldSw) {
        this.hearingHeldSw = hearingHeldSw;
    }

    
    @Column(name="FAIR_ISSUE_SW", length=1)
    public Character getFairIssueSw() {
        return this.fairIssueSw;
    }
    
    public void setFairIssueSw(Character fairIssueSw) {
        this.fairIssueSw = fairIssueSw;
    }

    
    @Column(name="VALID_FACT_SW", length=1)
    public Character getValidFactSw() {
        return this.validFactSw;
    }
    
    public void setValidFactSw(Character validFactSw) {
        this.validFactSw = validFactSw;
    }

    
    @Column(name="VFD_AI_SW", length=1)
    public Character getVfdAiSw() {
        return this.vfdAiSw;
    }
    
    public void setVfdAiSw(Character vfdAiSw) {
        this.vfdAiSw = vfdAiSw;
    }

    
    @Column(name="VFD_AI_RCVD_SW", length=1)
    public Character getVfdAiRcvdSw() {
        return this.vfdAiRcvdSw;
    }
    
    public void setVfdAiRcvdSw(Character vfdAiRcvdSw) {
        this.vfdAiRcvdSw = vfdAiRcvdSw;
    }

    
    @Column(name="VFD_AI_FACT_SW", length=1)
    public Character getVfdAiFactSw() {
        return this.vfdAiFactSw;
    }
    
    public void setVfdAiFactSw(Character vfdAiFactSw) {
        this.vfdAiFactSw = vfdAiFactSw;
    }

    
    @Column(name="PHONE_CONTACT_SW", length=1)
    public Character getPhoneContactSw() {
        return this.phoneContactSw;
    }
    
    public void setPhoneContactSw(Character phoneContactSw) {
        this.phoneContactSw = phoneContactSw;
    }

    
    @Column(name="COB_TIMELY_SW", length=1)
    public Character getCobTimelySw() {
        return this.cobTimelySw;
    }
    
    public void setCobTimelySw(Character cobTimelySw) {
        this.cobTimelySw = cobTimelySw;
    }

    
    @Column(name="RES_NOTES_TXT", length=4000)
    public String getResNotesTxt() {
        return this.resNotesTxt;
    }
    
    public void setResNotesTxt(String resNotesTxt) {
        this.resNotesTxt = resNotesTxt;
    }

    
    @Column(name="GOOD_CAUSE_TIMELY_TXT", length=250)
    public String getGoodCauseTimelyTxt() {
        return this.goodCauseTimelyTxt;
    }
    
    public void setGoodCauseTimelyTxt(String goodCauseTimelyTxt) {
        this.goodCauseTimelyTxt = goodCauseTimelyTxt;
    }

    
    @Column(name="GOOD_CAUSE_COB_TXT", length=250)
    public String getGoodCauseCobTxt() {
        return this.goodCauseCobTxt;
    }
    
    public void setGoodCauseCobTxt(String goodCauseCobTxt) {
        this.goodCauseCobTxt = goodCauseCobTxt;
    }

    
    @Column(name="VFD_AI_NOTICE_TXT", length=2000)
    public String getVfdAiNoticeTxt() {
        return this.vfdAiNoticeTxt;
    }
    
    public void setVfdAiNoticeTxt(String vfdAiNoticeTxt) {
        this.vfdAiNoticeTxt = vfdAiNoticeTxt;
    }

    
    @Column(name="PHONE_CONTACT_DT", length=7)
    public LocalDate getPhoneContactDt() {
        return this.phoneContactDt;
    }
    
    public void setPhoneContactDt(LocalDate phoneContactDt) {
        this.phoneContactDt = phoneContactDt;
    }

    
    @Column(name="PHONE_CONTACT_TXT", length=4000)
    public String getPhoneContactTxt() {
        return this.phoneContactTxt;
    }
    
    public void setPhoneContactTxt(String phoneContactTxt) {
        this.phoneContactTxt = phoneContactTxt;
    }

    
    @Column(name="RES_REVIEW_SW", length=1)
    public Character getResReviewSw() {
        return this.resReviewSw;
    }
    
    public void setResReviewSw(Character resReviewSw) {
        this.resReviewSw = resReviewSw;
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

    
    @Column(name="FAIL_SUBMIT_REDET_SW", length=1)
    public Character getFailSubmitRedetSw() {
        return this.failSubmitRedetSw;
    }
    
    public void setFailSubmitRedetSw(Character failSubmitRedetSw) {
        this.failSubmitRedetSw = failSubmitRedetSw;
    }

    
    @Column(name="COB_RVW_QLFY_SW", length=1)
    public Character getCobRvwQlfySw() {
        return this.cobRvwQlfySw;
    }
    
    public void setCobRvwQlfySw(Character cobRvwQlfySw) {
        this.cobRvwQlfySw = cobRvwQlfySw;
    }

    
    @Column(name="GOOD_CAUSE_COB_SW", length=1)
    public Character getGoodCauseCobSw() {
        return this.goodCauseCobSw;
    }
    
    public void setGoodCauseCobSw(Character goodCauseCobSw) {
        this.goodCauseCobSw = goodCauseCobSw;
    }

    
    @Column(name="GOOD_CAUSE_UNTIMLEY_SW", length=1)
    public Character getGoodCauseUntimleySw() {
        return this.goodCauseUntimleySw;
    }
    
    public void setGoodCauseUntimleySw(Character goodCauseUntimleySw) {
        this.goodCauseUntimleySw = goodCauseUntimleySw;
    }




}


