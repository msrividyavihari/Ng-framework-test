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
 * AmPreHearingMeeting generated by hbm2java
 */
@Entity
@Table(name="AM_PRE_HEARING_MEETING"
    ,schema="IE_APP_ONLINE"
)
public class AmPreHearingMeeting  implements java.io.Serializable {


     private AmPreHearingMeetingId id;
     private LocalDate phMeetingDt;
     private String phMeetingTime;
     private Character suppressNoticeInd;
     private Character phReviewConductedInd;
     private Character phMeetingConductedInd;
     private String phMeetingTypeCd;
     private BigInteger phMeetingLocCd;
     private String phMeetingPhNum;
     private String phMeetingOutcomeCd;
     private String caseComments;
     private BigInteger historySeq;
     private String createUserId;
     private LocalDate createDt;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;

    public AmPreHearingMeeting() {
    }

	
    public AmPreHearingMeeting(AmPreHearingMeetingId id, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
        this.id = id;
        this.historySeq = historySeq;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
    }
    public AmPreHearingMeeting(AmPreHearingMeetingId id, LocalDate phMeetingDt, String phMeetingTime, Character suppressNoticeInd, Character phReviewConductedInd, Character phMeetingConductedInd, String phMeetingTypeCd, BigInteger phMeetingLocCd, String phMeetingPhNum, String phMeetingOutcomeCd, String caseComments, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
       this.id = id;
       this.phMeetingDt = phMeetingDt;
       this.phMeetingTime = phMeetingTime;
       this.suppressNoticeInd = suppressNoticeInd;
       this.phReviewConductedInd = phReviewConductedInd;
       this.phMeetingConductedInd = phMeetingConductedInd;
       this.phMeetingTypeCd = phMeetingTypeCd;
       this.phMeetingLocCd = phMeetingLocCd;
       this.phMeetingPhNum = phMeetingPhNum;
       this.phMeetingOutcomeCd = phMeetingOutcomeCd;
       this.caseComments = caseComments;
       this.historySeq = historySeq;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="amPreHearingMeetingSeqNum", column=@Column(name="AM_PRE_HEARING_MEETING_SEQ_NUM", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="aplNum", column=@Column(name="APL_NUM", nullable=false, precision=22, scale=0) ) } )
    public AmPreHearingMeetingId getId() {
        return this.id;
    }
    
    public void setId(AmPreHearingMeetingId id) {
        this.id = id;
    }

    
    @Column(name="PH_MEETING_DT", length=7)
    public LocalDate getPhMeetingDt() {
        return this.phMeetingDt;
    }
    
    public void setPhMeetingDt(LocalDate phMeetingDt) {
        this.phMeetingDt = phMeetingDt;
    }

    
    @Column(name="PH_MEETING_TIME", length=30)
    public String getPhMeetingTime() {
        return this.phMeetingTime;
    }
    
    public void setPhMeetingTime(String phMeetingTime) {
        this.phMeetingTime = phMeetingTime;
    }

    
    @Column(name="SUPPRESS_NOTICE_IND", length=1)
    public Character getSuppressNoticeInd() {
        return this.suppressNoticeInd;
    }
    
    public void setSuppressNoticeInd(Character suppressNoticeInd) {
        this.suppressNoticeInd = suppressNoticeInd;
    }

    
    @Column(name="PH_REVIEW_CONDUCTED_IND", length=1)
    public Character getPhReviewConductedInd() {
        return this.phReviewConductedInd;
    }
    
    public void setPhReviewConductedInd(Character phReviewConductedInd) {
        this.phReviewConductedInd = phReviewConductedInd;
    }

    
    @Column(name="PH_MEETING_CONDUCTED_IND", length=1)
    public Character getPhMeetingConductedInd() {
        return this.phMeetingConductedInd;
    }
    
    public void setPhMeetingConductedInd(Character phMeetingConductedInd) {
        this.phMeetingConductedInd = phMeetingConductedInd;
    }

    
    @Column(name="PH_MEETING_TYPE_CD", length=3)
    public String getPhMeetingTypeCd() {
        return this.phMeetingTypeCd;
    }
    
    public void setPhMeetingTypeCd(String phMeetingTypeCd) {
        this.phMeetingTypeCd = phMeetingTypeCd;
    }

    
    @Column(name="PH_MEETING_LOC_CD", precision=22, scale=0)
    public BigInteger getPhMeetingLocCd() {
        return this.phMeetingLocCd;
    }
    
    public void setPhMeetingLocCd(BigInteger phMeetingLocCd) {
        this.phMeetingLocCd = phMeetingLocCd;
    }

    
    @Column(name="PH_MEETING_PH_NUM", length=10)
    public String getPhMeetingPhNum() {
        return this.phMeetingPhNum;
    }
    
    public void setPhMeetingPhNum(String phMeetingPhNum) {
        this.phMeetingPhNum = phMeetingPhNum;
    }

    
    @Column(name="PH_MEETING_OUTCOME_CD", length=2)
    public String getPhMeetingOutcomeCd() {
        return this.phMeetingOutcomeCd;
    }
    
    public void setPhMeetingOutcomeCd(String phMeetingOutcomeCd) {
        this.phMeetingOutcomeCd = phMeetingOutcomeCd;
    }

    
    @Column(name="CASE_COMMENTS", length=4000)
    public String getCaseComments() {
        return this.caseComments;
    }
    
    public void setCaseComments(String caseComments) {
        this.caseComments = caseComments;
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


