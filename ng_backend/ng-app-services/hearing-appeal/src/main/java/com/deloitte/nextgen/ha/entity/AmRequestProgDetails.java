package com.deloitte.nextgen.ha.entity;
// Generated May 16, 2021 11:46:32 PM by Hibernate Tools 5.4.22.Final


import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * AmRequestProgDetails generated by hbm2java
 */
@Entity
@Table(name="AM_REQUEST_PROG_DETAILS")
@EntityType(type= TypeEnum.ONE)
@EqualsAndHashCode(callSuper=false)
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AmRequestProgDetailsId.class)
public class AmRequestProgDetails  extends TypeOneBaseEntity<String> {


     //private AmRequestProgDetailsId id;
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AM_REQUEST_PROG_DETAILS_1SQ")
     private BigInteger amPrgDtlSeq;
     @Id
     private BigInteger aplNum;

    @Column(name="PROGRAM_CD", nullable=false, length=4)
    private String programCd;
    @Column(name="AGENCY_CD", length=4)
     private String agencyCd;
    @Column(name="CATEGORY_CD", length=4)
     private String categoryCd;
    @Column(name="PROG_CLARIFICATION", length=10)
     private String progClarification;
    @Column(name="CONTINUE_BENEFITS_IND", length=2)
     private String continueBenefitsInd;
    @Column(name="OFFICE_NUM", precision=22, scale=0)
     private BigInteger officeNum;
    @Column(name="PAY_BACK_TO_DEPT_IND", length=1)
     private Character payBackToDeptInd;
    @Column(name="DISPOSITION_CD", length=4)
     private String dispositionCd;
    @Column(name="APL_NOTES", length=4000)
     private String aplNotes;
    @Column(name="IES_CASE_NUM", precision=22, scale=0)
     private BigInteger iesCaseNum;
    @Column(name="IES_APP_NUM", length=10)
     private String iesAppNum;
    @Column(name="NON_IES_CASE_NUM", length=20)
     private String nonIesCaseNum;
    @Column(name="MG_CARE_PLAN_CD", length=5)
     private String mgCarePlanCd;
    @Column(name="MG_CARE_PLAN_NME", length=5)
     private String mgCarePlanNme;
    @Column(name="DEAFNESS_NEED_CD", length=5)
     private String deafnessNeedCd;
    @Column(name="BLINDNESS_NEED_CD", length=5)
     private String blindnessNeedCd;
  /*  @Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0)
     private BigInteger historySeq;
    @Column(name="CREATE_USER_ID", nullable=false, length=20)
     private String createUserId;
    @Column(name="CREATE_DT", nullable=false, length=7)
     private LocalDate createDt;
    @Column(name="UNIQUE_TRANS_ID", nullable=false, precision=22, scale=0)
     private BigInteger uniqueTransId;
    @Column(name="ARCHIVE_DT", nullable=false, length=7)
     private LocalDate archiveDt;*/
  @Column(name="APL_TYPE", length=7)
     private String aplType;
    @Column(name="DATE_OF_NOTICE", length=7)
     private LocalDate dateOfNotice;
    @Column(name="APL2_NOTES_TXT", length=4000)
     private String apl2NotesTxt;
    @Column(name="APL_DNL_NEW_APP_SW", length=1)
     private Character aplDnlNewAppSw;
    @Column(name="APL_ELIG_BEG_DT", length=7)
     private LocalDate aplEligBegDt;
    @Column(name="APL_APPLY_DT", length=7)
     private LocalDate aplApplyDt;
    @Column(name="APL_ACTUAL_APPLY_DT", length=7)
     private LocalDate aplActualApplyDt;
    @Column(name="APL_APPLY_TXT", length=25)
     private String aplApplyTxt;
    @Column(name="APL_APPLY_LOC_TXT", length=50)
     private String aplApplyLocTxt;
    @Column(name="CALLER_PRVD_CAT_SW", length=1)
     private Character callerPrvdCatSw;
    @Column(name="APL_POT_ASSC_SW", length=1)
     private Character aplPotAsscSw;
    @Column(name="APL_OTHER_INDV_SW", length=1)
     private Character aplOtherIndvSw;
    @Column(name="APL_NB_SW", length=1)
     private Character aplNbSw;
    @Column(name="APL_WAIVER_CD", length=3)
     private String aplWaiverCd;
    @Column(name="GOOD_CAUSE_COB_SW", length=1)
     private Character goodCauseCobSw;
    @Column(name="GOOD_CAUSE_UNTIMLEY_SW", length=1)
     private Character goodCauseUntimleySw;
    @Column(name="APL_FILED_TIMELY_SW", length=1)
     private Character aplFiledTimelySw;
    @Column(name="APL_LOC_TXT", length=25)
     private String aplLocTxt;
    @Column(name="APL_TIMELY_NOTES_TXT", length=4000)
    private String aplTimelyNotesTxt;
    @Column(name="GOOD_CAUSE_COB_DTLS_TXT", length=200)
    private String goodCauseCobDtlsTxt;
    @Column(name="COB_RQST_TIMLEY_SW", length=1)
     private Character cobRqstTimleySw;
    @Column(name="GOOD_CAUSE_TIMELY_DTLS_TXT", length=200)
     private String goodCauseTimelyDtlsTxt;
//    @Column(name="UPDATE_DT", length=7)
//     private LocalDate updateDt;
//    @Column(name="UPDATE_USER_ID", length=20)
//     private String updateUserId;
@Column(name="COB_BATCH_PROCESS_IND", length=1)
     private Character cobBatchProcessInd;
    @Column(name="FROM_COE_CD", length=3)
     private String fromCoeCd;
    @Column(name="COB_RSN_DTL_TXT", length=100)
     private String cobRsnDtlTxt;
    @Column(name="PCKT_MAIL_SW", nullable=false, length=1)
     private Character pcktMailSw;
    @Column(name="CMPLT_PCKT_SENT_SW", nullable=false, length=1)
     private Character cmpltPcktSentSw;
    @Column(name="OT_INDV_PCKT_SW", nullable=false, length=1)
     private Character otIndvPcktSw;
    @Column(name="INDV_PCKT_TXT", length=500)
     private String indvPcktTxt;
    @Column(name="PCKT_MTHD_CD", nullable=false, length=3)
     private String pcktMthdCd;
    @Column(name="RN_AI_RCVD_SW", nullable=false, length=1)
     private Character rnAiRcvdSw;
    @Column(name="RN_AI_RTN_SW", nullable=false, length=1)
     private Character rnAiRtnSw;
    @Column(name="RN_AI_RTN_MTHD_CD", nullable=false, length=3)
     private String rnAiRtnMthdCd;
    @Column(name="AI_TERM_DT", length=7)
     private LocalDate aiTermDt;
    @Column(name="AI_90_DAY_DT", length=7)
     private LocalDate ai90DayDt;
    @Column(name="IN_90_DAY_CD", length=3)
     private String in90DayCd;
    @Column(name="AI_RECV_DT", length=7)
     private LocalDate aiRecvDt;
    @Column(name="APL_RECV_DTLS_TXT", length=25)
     private String aplRecvDtlsTxt;
    @Column(name="RES_REVIEW_CD", length=2)
     private String resReviewCd;
    @Column(name="COVERAGE_END_CAT_CD", length=4)
     private String coverageEndCatCd;
    @Column(name="APPEAL_DECISION", length=4)
     private String appealDecision;


    @PrePersist
    void prePersist() {
        if (this.pcktMailSw == null) this.pcktMailSw = 'N';
        if (this.cmpltPcktSentSw == null) this.cmpltPcktSentSw = 'N';
        if (this.otIndvPcktSw == null) this.otIndvPcktSw = 'N';
        if (this.pcktMthdCd == null) this.pcktMthdCd = "N";
        if (this.rnAiRcvdSw == null) this.rnAiRcvdSw = 'N';
        if (this.rnAiRtnSw == null) this.rnAiRtnSw = 'N';
        if (this.rnAiRtnMthdCd == null) this.rnAiRtnMthdCd = "N";
    }

   /* public AmRequestProgDetails() {
    }

	
    public AmRequestProgDetails(AmRequestProgDetailsId id, String programCd, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, char pcktMailSw, char cmpltPcktSentSw, char otIndvPcktSw, String pcktMthdCd, char rnAiRcvdSw, char rnAiRtnSw, String rnAiRtnMthdCd) {
        this.id = id;
        this.programCd = programCd;
        this.historySeq = historySeq;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
        this.pcktMailSw = pcktMailSw;
        this.cmpltPcktSentSw = cmpltPcktSentSw;
        this.otIndvPcktSw = otIndvPcktSw;
        this.pcktMthdCd = pcktMthdCd;
        this.rnAiRcvdSw = rnAiRcvdSw;
        this.rnAiRtnSw = rnAiRtnSw;
        this.rnAiRtnMthdCd = rnAiRtnMthdCd;
    }
    public AmRequestProgDetails(AmRequestProgDetailsId id, String programCd, String agencyCd, String categoryCd, String progClarification, String continueBenefitsInd, BigInteger officeNum, Character payBackToDeptInd, String dispositionCd, String aplNotes, BigInteger iesCaseNum, String iesAppNum, String nonIesCaseNum, String mgCarePlanCd, String mgCarePlanNme, String deafnessNeedCd, String blindnessNeedCd, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String aplType, LocalDate dateOfNotice, String apl2NotesTxt, Character aplDnlNewAppSw, LocalDate aplEligBegDt, LocalDate aplApplyDt, LocalDate aplActualApplyDt, String aplApplyTxt, String aplApplyLocTxt, Character callerPrvdCatSw, Character aplPotAsscSw, Character aplOtherIndvSw, Character aplNbSw, String aplWaiverCd, Character goodCauseCobSw, Character goodCauseUntimleySw, Character aplFiledTimelySw, String aplLocTxt, String aplTimelyNotesTxt, String goodCauseCobDtlsTxt, Character cobRqstTimleySw, String goodCauseTimelyDtlsTxt, LocalDate updateDt, String updateUserId, Character cobBatchProcessInd, String fromCoeCd, String cobRsnDtlTxt, char pcktMailSw, char cmpltPcktSentSw, char otIndvPcktSw, String indvPcktTxt, String pcktMthdCd, char rnAiRcvdSw, char rnAiRtnSw, String rnAiRtnMthdCd, LocalDate aiTermDt, LocalDate ai90DayDt, String in90DayCd, LocalDate aiRecvDt, String aplRecvDtlsTxt, String resReviewCd, String coverageEndCatCd, String appealDecision) {
       this.id = id;
       this.programCd = programCd;
       this.agencyCd = agencyCd;
       this.categoryCd = categoryCd;
       this.progClarification = progClarification;
       this.continueBenefitsInd = continueBenefitsInd;
       this.officeNum = officeNum;
       this.payBackToDeptInd = payBackToDeptInd;
       this.dispositionCd = dispositionCd;
       this.aplNotes = aplNotes;
       this.iesCaseNum = iesCaseNum;
       this.iesAppNum = iesAppNum;
       this.nonIesCaseNum = nonIesCaseNum;
       this.mgCarePlanCd = mgCarePlanCd;
       this.mgCarePlanNme = mgCarePlanNme;
       this.deafnessNeedCd = deafnessNeedCd;
       this.blindnessNeedCd = blindnessNeedCd;
       this.historySeq = historySeq;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.aplType = aplType;
       this.dateOfNotice = dateOfNotice;
       this.apl2NotesTxt = apl2NotesTxt;
       this.aplDnlNewAppSw = aplDnlNewAppSw;
       this.aplEligBegDt = aplEligBegDt;
       this.aplApplyDt = aplApplyDt;
       this.aplActualApplyDt = aplActualApplyDt;
       this.aplApplyTxt = aplApplyTxt;
       this.aplApplyLocTxt = aplApplyLocTxt;
       this.callerPrvdCatSw = callerPrvdCatSw;
       this.aplPotAsscSw = aplPotAsscSw;
       this.aplOtherIndvSw = aplOtherIndvSw;
       this.aplNbSw = aplNbSw;
       this.aplWaiverCd = aplWaiverCd;
       this.goodCauseCobSw = goodCauseCobSw;
       this.goodCauseUntimleySw = goodCauseUntimleySw;
       this.aplFiledTimelySw = aplFiledTimelySw;
       this.aplLocTxt = aplLocTxt;
       this.aplTimelyNotesTxt = aplTimelyNotesTxt;
       this.goodCauseCobDtlsTxt = goodCauseCobDtlsTxt;
       this.cobRqstTimleySw = cobRqstTimleySw;
       this.goodCauseTimelyDtlsTxt = goodCauseTimelyDtlsTxt;
       this.updateDt = updateDt;
       this.updateUserId = updateUserId;
       this.cobBatchProcessInd = cobBatchProcessInd;
       this.fromCoeCd = fromCoeCd;
       this.cobRsnDtlTxt = cobRsnDtlTxt;
       this.pcktMailSw = pcktMailSw;
       this.cmpltPcktSentSw = cmpltPcktSentSw;
       this.otIndvPcktSw = otIndvPcktSw;
       this.indvPcktTxt = indvPcktTxt;
       this.pcktMthdCd = pcktMthdCd;
       this.rnAiRcvdSw = rnAiRcvdSw;
       this.rnAiRtnSw = rnAiRtnSw;
       this.rnAiRtnMthdCd = rnAiRtnMthdCd;
       this.aiTermDt = aiTermDt;
       this.ai90DayDt = ai90DayDt;
       this.in90DayCd = in90DayCd;
       this.aiRecvDt = aiRecvDt;
       this.aplRecvDtlsTxt = aplRecvDtlsTxt;
       this.resReviewCd = resReviewCd;
       this.coverageEndCatCd = coverageEndCatCd;
       this.appealDecision = appealDecision;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="amPrgDtlSeq", column=@Column(name="AM_PRG_DTL_SEQ", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="aplNum", column=@Column(name="APL_NUM", nullable=false, precision=22, scale=0) ) } )
    public AmRequestProgDetailsId getId() {
        return this.id;
    }
    
    public void setId(AmRequestProgDetailsId id) {
        this.id = id;
    }

    
    @Column(name="PROGRAM_CD", nullable=false, length=4)
    public String getProgramCd() {
        return this.programCd;
    }
    
    public void setProgramCd(String programCd) {
        this.programCd = programCd;
    }

    
    @Column(name="AGENCY_CD", length=4)
    public String getAgencyCd() {
        return this.agencyCd;
    }
    
    public void setAgencyCd(String agencyCd) {
        this.agencyCd = agencyCd;
    }

    
    @Column(name="CATEGORY_CD", length=4)
    public String getCategoryCd() {
        return this.categoryCd;
    }
    
    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    
    @Column(name="PROG_CLARIFICATION", length=10)
    public String getProgClarification() {
        return this.progClarification;
    }
    
    public void setProgClarification(String progClarification) {
        this.progClarification = progClarification;
    }

    
    @Column(name="CONTINUE_BENEFITS_IND", length=2)
    public String getContinueBenefitsInd() {
        return this.continueBenefitsInd;
    }
    
    public void setContinueBenefitsInd(String continueBenefitsInd) {
        this.continueBenefitsInd = continueBenefitsInd;
    }

    
    @Column(name="OFFICE_NUM", precision=22, scale=0)
    public BigInteger getOfficeNum() {
        return this.officeNum;
    }
    
    public void setOfficeNum(BigInteger officeNum) {
        this.officeNum = officeNum;
    }

    
    @Column(name="PAY_BACK_TO_DEPT_IND", length=1)
    public Character getPayBackToDeptInd() {
        return this.payBackToDeptInd;
    }
    
    public void setPayBackToDeptInd(Character payBackToDeptInd) {
        this.payBackToDeptInd = payBackToDeptInd;
    }

    
    @Column(name="DISPOSITION_CD", length=4)
    public String getDispositionCd() {
        return this.dispositionCd;
    }
    
    public void setDispositionCd(String dispositionCd) {
        this.dispositionCd = dispositionCd;
    }

    
    @Column(name="APL_NOTES", length=4000)
    public String getAplNotes() {
        return this.aplNotes;
    }
    
    public void setAplNotes(String aplNotes) {
        this.aplNotes = aplNotes;
    }

    
    @Column(name="IES_CASE_NUM", precision=22, scale=0)
    public BigInteger getIesCaseNum() {
        return this.iesCaseNum;
    }
    
    public void setIesCaseNum(BigInteger iesCaseNum) {
        this.iesCaseNum = iesCaseNum;
    }

    
    @Column(name="IES_APP_NUM", length=10)
    public String getIesAppNum() {
        return this.iesAppNum;
    }
    
    public void setIesAppNum(String iesAppNum) {
        this.iesAppNum = iesAppNum;
    }

    
    @Column(name="NON_IES_CASE_NUM", length=20)
    public String getNonIesCaseNum() {
        return this.nonIesCaseNum;
    }
    
    public void setNonIesCaseNum(String nonIesCaseNum) {
        this.nonIesCaseNum = nonIesCaseNum;
    }

    
    @Column(name="MG_CARE_PLAN_CD", length=5)
    public String getMgCarePlanCd() {
        return this.mgCarePlanCd;
    }
    
    public void setMgCarePlanCd(String mgCarePlanCd) {
        this.mgCarePlanCd = mgCarePlanCd;
    }

    
    @Column(name="MG_CARE_PLAN_NME", length=5)
    public String getMgCarePlanNme() {
        return this.mgCarePlanNme;
    }
    
    public void setMgCarePlanNme(String mgCarePlanNme) {
        this.mgCarePlanNme = mgCarePlanNme;
    }

    
    @Column(name="DEAFNESS_NEED_CD", length=5)
    public String getDeafnessNeedCd() {
        return this.deafnessNeedCd;
    }
    
    public void setDeafnessNeedCd(String deafnessNeedCd) {
        this.deafnessNeedCd = deafnessNeedCd;
    }

    
    @Column(name="BLINDNESS_NEED_CD", length=5)
    public String getBlindnessNeedCd() {
        return this.blindnessNeedCd;
    }
    
    public void setBlindnessNeedCd(String blindnessNeedCd) {
        this.blindnessNeedCd = blindnessNeedCd;
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

    
    @Column(name="APL_TYPE", length=7)
    public String getAplType() {
        return this.aplType;
    }
    
    public void setAplType(String aplType) {
        this.aplType = aplType;
    }

    
    @Column(name="DATE_OF_NOTICE", length=7)
    public LocalDate getDateOfNotice() {
        return this.dateOfNotice;
    }
    
    public void setDateOfNotice(LocalDate dateOfNotice) {
        this.dateOfNotice = dateOfNotice;
    }

    
    @Column(name="APL2_NOTES_TXT", length=4000)
    public String getApl2NotesTxt() {
        return this.apl2NotesTxt;
    }
    
    public void setApl2NotesTxt(String apl2NotesTxt) {
        this.apl2NotesTxt = apl2NotesTxt;
    }

    
    @Column(name="APL_DNL_NEW_APP_SW", length=1)
    public Character getAplDnlNewAppSw() {
        return this.aplDnlNewAppSw;
    }
    
    public void setAplDnlNewAppSw(Character aplDnlNewAppSw) {
        this.aplDnlNewAppSw = aplDnlNewAppSw;
    }

    
    @Column(name="APL_ELIG_BEG_DT", length=7)
    public LocalDate getAplEligBegDt() {
        return this.aplEligBegDt;
    }
    
    public void setAplEligBegDt(LocalDate aplEligBegDt) {
        this.aplEligBegDt = aplEligBegDt;
    }

    
    @Column(name="APL_APPLY_DT", length=7)
    public LocalDate getAplApplyDt() {
        return this.aplApplyDt;
    }
    
    public void setAplApplyDt(LocalDate aplApplyDt) {
        this.aplApplyDt = aplApplyDt;
    }

    
    @Column(name="APL_ACTUAL_APPLY_DT", length=7)
    public LocalDate getAplActualApplyDt() {
        return this.aplActualApplyDt;
    }
    
    public void setAplActualApplyDt(LocalDate aplActualApplyDt) {
        this.aplActualApplyDt = aplActualApplyDt;
    }

    
    @Column(name="APL_APPLY_TXT", length=25)
    public String getAplApplyTxt() {
        return this.aplApplyTxt;
    }
    
    public void setAplApplyTxt(String aplApplyTxt) {
        this.aplApplyTxt = aplApplyTxt;
    }

    
    @Column(name="APL_APPLY_LOC_TXT", length=50)
    public String getAplApplyLocTxt() {
        return this.aplApplyLocTxt;
    }
    
    public void setAplApplyLocTxt(String aplApplyLocTxt) {
        this.aplApplyLocTxt = aplApplyLocTxt;
    }

    
    @Column(name="CALLER_PRVD_CAT_SW", length=1)
    public Character getCallerPrvdCatSw() {
        return this.callerPrvdCatSw;
    }
    
    public void setCallerPrvdCatSw(Character callerPrvdCatSw) {
        this.callerPrvdCatSw = callerPrvdCatSw;
    }

    
    @Column(name="APL_POT_ASSC_SW", length=1)
    public Character getAplPotAsscSw() {
        return this.aplPotAsscSw;
    }
    
    public void setAplPotAsscSw(Character aplPotAsscSw) {
        this.aplPotAsscSw = aplPotAsscSw;
    }

    
    @Column(name="APL_OTHER_INDV_SW", length=1)
    public Character getAplOtherIndvSw() {
        return this.aplOtherIndvSw;
    }
    
    public void setAplOtherIndvSw(Character aplOtherIndvSw) {
        this.aplOtherIndvSw = aplOtherIndvSw;
    }

    
    @Column(name="APL_NB_SW", length=1)
    public Character getAplNbSw() {
        return this.aplNbSw;
    }
    
    public void setAplNbSw(Character aplNbSw) {
        this.aplNbSw = aplNbSw;
    }

    
    @Column(name="APL_WAIVER_CD", length=3)
    public String getAplWaiverCd() {
        return this.aplWaiverCd;
    }
    
    public void setAplWaiverCd(String aplWaiverCd) {
        this.aplWaiverCd = aplWaiverCd;
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

    
    @Column(name="APL_FILED_TIMELY_SW", length=1)
    public Character getAplFiledTimelySw() {
        return this.aplFiledTimelySw;
    }
    
    public void setAplFiledTimelySw(Character aplFiledTimelySw) {
        this.aplFiledTimelySw = aplFiledTimelySw;
    }

    
    @Column(name="APL_LOC_TXT", length=25)
    public String getAplLocTxt() {
        return this.aplLocTxt;
    }
    
    public void setAplLocTxt(String aplLocTxt) {
        this.aplLocTxt = aplLocTxt;
    }

    
    @Column(name="APL_TIMELY_NOTES_TXT", length=4000)
    public String getAplTimelyNotesTxt() {
        return this.aplTimelyNotesTxt;
    }
    
    public void setAplTimelyNotesTxt(String aplTimelyNotesTxt) {
        this.aplTimelyNotesTxt = aplTimelyNotesTxt;
    }

    
    @Column(name="GOOD_CAUSE_COB_DTLS_TXT", length=200)
    public String getGoodCauseCobDtlsTxt() {
        return this.goodCauseCobDtlsTxt;
    }
    
    public void setGoodCauseCobDtlsTxt(String goodCauseCobDtlsTxt) {
        this.goodCauseCobDtlsTxt = goodCauseCobDtlsTxt;
    }

    
    @Column(name="COB_RQST_TIMLEY_SW", length=1)
    public Character getCobRqstTimleySw() {
        return this.cobRqstTimleySw;
    }
    
    public void setCobRqstTimleySw(Character cobRqstTimleySw) {
        this.cobRqstTimleySw = cobRqstTimleySw;
    }

    
    @Column(name="GOOD_CAUSE_TIMELY_DTLS_TXT", length=200)
    public String getGoodCauseTimelyDtlsTxt() {
        return this.goodCauseTimelyDtlsTxt;
    }
    
    public void setGoodCauseTimelyDtlsTxt(String goodCauseTimelyDtlsTxt) {
        this.goodCauseTimelyDtlsTxt = goodCauseTimelyDtlsTxt;
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

    
    @Column(name="COB_BATCH_PROCESS_IND", length=1)
    public Character getCobBatchProcessInd() {
        return this.cobBatchProcessInd;
    }
    
    public void setCobBatchProcessInd(Character cobBatchProcessInd) {
        this.cobBatchProcessInd = cobBatchProcessInd;
    }

    
    @Column(name="FROM_COE_CD", length=3)
    public String getFromCoeCd() {
        return this.fromCoeCd;
    }
    
    public void setFromCoeCd(String fromCoeCd) {
        this.fromCoeCd = fromCoeCd;
    }

    
    @Column(name="COB_RSN_DTL_TXT", length=100)
    public String getCobRsnDtlTxt() {
        return this.cobRsnDtlTxt;
    }
    
    public void setCobRsnDtlTxt(String cobRsnDtlTxt) {
        this.cobRsnDtlTxt = cobRsnDtlTxt;
    }

    
    @Column(name="PCKT_MAIL_SW", nullable=false, length=1)
    public char getPcktMailSw() {
        return this.pcktMailSw;
    }
    
    public void setPcktMailSw(char pcktMailSw) {
        this.pcktMailSw = pcktMailSw;
    }

    
    @Column(name="CMPLT_PCKT_SENT_SW", nullable=false, length=1)
    public char getCmpltPcktSentSw() {
        return this.cmpltPcktSentSw;
    }
    
    public void setCmpltPcktSentSw(char cmpltPcktSentSw) {
        this.cmpltPcktSentSw = cmpltPcktSentSw;
    }

    
    @Column(name="OT_INDV_PCKT_SW", nullable=false, length=1)
    public char getOtIndvPcktSw() {
        return this.otIndvPcktSw;
    }
    
    public void setOtIndvPcktSw(char otIndvPcktSw) {
        this.otIndvPcktSw = otIndvPcktSw;
    }

    
    @Column(name="INDV_PCKT_TXT", length=500)
    public String getIndvPcktTxt() {
        return this.indvPcktTxt;
    }
    
    public void setIndvPcktTxt(String indvPcktTxt) {
        this.indvPcktTxt = indvPcktTxt;
    }

    
    @Column(name="PCKT_MTHD_CD", nullable=false, length=3)
    public String getPcktMthdCd() {
        return this.pcktMthdCd;
    }
    
    public void setPcktMthdCd(String pcktMthdCd) {
        this.pcktMthdCd = pcktMthdCd;
    }

    
    @Column(name="RN_AI_RCVD_SW", nullable=false, length=1)
    public char getRnAiRcvdSw() {
        return this.rnAiRcvdSw;
    }
    
    public void setRnAiRcvdSw(char rnAiRcvdSw) {
        this.rnAiRcvdSw = rnAiRcvdSw;
    }

    
    @Column(name="RN_AI_RTN_SW", nullable=false, length=1)
    public char getRnAiRtnSw() {
        return this.rnAiRtnSw;
    }
    
    public void setRnAiRtnSw(char rnAiRtnSw) {
        this.rnAiRtnSw = rnAiRtnSw;
    }

    
    @Column(name="RN_AI_RTN_MTHD_CD", nullable=false, length=3)
    public String getRnAiRtnMthdCd() {
        return this.rnAiRtnMthdCd;
    }
    
    public void setRnAiRtnMthdCd(String rnAiRtnMthdCd) {
        this.rnAiRtnMthdCd = rnAiRtnMthdCd;
    }

    
    @Column(name="AI_TERM_DT", length=7)
    public LocalDate getAiTermDt() {
        return this.aiTermDt;
    }
    
    public void setAiTermDt(LocalDate aiTermDt) {
        this.aiTermDt = aiTermDt;
    }

    
    @Column(name="AI_90_DAY_DT", length=7)
    public LocalDate getAi90DayDt() {
        return this.ai90DayDt;
    }
    
    public void setAi90DayDt(LocalDate ai90DayDt) {
        this.ai90DayDt = ai90DayDt;
    }

    
    @Column(name="IN_90_DAY_CD", length=3)
    public String getIn90DayCd() {
        return this.in90DayCd;
    }
    
    public void setIn90DayCd(String in90DayCd) {
        this.in90DayCd = in90DayCd;
    }

    
    @Column(name="AI_RECV_DT", length=7)
    public LocalDate getAiRecvDt() {
        return this.aiRecvDt;
    }
    
    public void setAiRecvDt(LocalDate aiRecvDt) {
        this.aiRecvDt = aiRecvDt;
    }

    
    @Column(name="APL_RECV_DTLS_TXT", length=25)
    public String getAplRecvDtlsTxt() {
        return this.aplRecvDtlsTxt;
    }
    
    public void setAplRecvDtlsTxt(String aplRecvDtlsTxt) {
        this.aplRecvDtlsTxt = aplRecvDtlsTxt;
    }

    
    @Column(name="RES_REVIEW_CD", length=2)
    public String getResReviewCd() {
        return this.resReviewCd;
    }
    
    public void setResReviewCd(String resReviewCd) {
        this.resReviewCd = resReviewCd;
    }

    
    @Column(name="COVERAGE_END_CAT_CD", length=4)
    public String getCoverageEndCatCd() {
        return this.coverageEndCatCd;
    }
    
    public void setCoverageEndCatCd(String coverageEndCatCd) {
        this.coverageEndCatCd = coverageEndCatCd;
    }

    
    @Column(name="APPEAL_DECISION", length=4)
    public String getAppealDecision() {
        return this.appealDecision;
    }
    
    public void setAppealDecision(String appealDecision) {
        this.appealDecision = appealDecision;
    }
*/



}

