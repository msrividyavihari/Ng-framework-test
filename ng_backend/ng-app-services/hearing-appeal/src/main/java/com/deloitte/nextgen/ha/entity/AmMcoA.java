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
 * AmMcoA generated by hbm2java
 */
@Entity
@Table(name="AM_MCO_A"
    ,schema="IE_APP_ONLINE"
)
public class AmMcoA  implements java.io.Serializable {


     private AmMcoAId id;
     private String mcoPlan;
     private LocalDate serviceStartDt;
     private LocalDate serviceEndDt;
     private String mcoNum;
     private LocalDate actionDt;
     private LocalDate filedDt;
     private String issueApl;
     private LocalDate decisionDt;
     private String trackNum;
     private String mcoNotes;
     private Character extRevReqInd;
     private LocalDate expDecDt;
     private String createUserId;
     private LocalDate createDt;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;
     private String auditUserId;
     private LocalDate auditDt;

    public AmMcoA() {
    }

	
    public AmMcoA(AmMcoAId id, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt) {
        this.id = id;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
        this.auditUserId = auditUserId;
        this.auditDt = auditDt;
    }
    public AmMcoA(AmMcoAId id, String mcoPlan, LocalDate serviceStartDt, LocalDate serviceEndDt, String mcoNum, LocalDate actionDt, LocalDate filedDt, String issueApl, LocalDate decisionDt, String trackNum, String mcoNotes, Character extRevReqInd, LocalDate expDecDt, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt) {
       this.id = id;
       this.mcoPlan = mcoPlan;
       this.serviceStartDt = serviceStartDt;
       this.serviceEndDt = serviceEndDt;
       this.mcoNum = mcoNum;
       this.actionDt = actionDt;
       this.filedDt = filedDt;
       this.issueApl = issueApl;
       this.decisionDt = decisionDt;
       this.trackNum = trackNum;
       this.mcoNotes = mcoNotes;
       this.extRevReqInd = extRevReqInd;
       this.expDecDt = expDecDt;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.auditUserId = auditUserId;
       this.auditDt = auditDt;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="mcoSeqNum", column=@Column(name="MCO_SEQ_NUM", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="aplNum", column=@Column(name="APL_NUM", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="historySeq", column=@Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0) ) } )
    public AmMcoAId getId() {
        return this.id;
    }
    
    public void setId(AmMcoAId id) {
        this.id = id;
    }

    
    @Column(name="MCO_PLAN", length=10)
    public String getMcoPlan() {
        return this.mcoPlan;
    }
    
    public void setMcoPlan(String mcoPlan) {
        this.mcoPlan = mcoPlan;
    }

    
    @Column(name="SERVICE_START_DT", length=7)
    public LocalDate getServiceStartDt() {
        return this.serviceStartDt;
    }
    
    public void setServiceStartDt(LocalDate serviceStartDt) {
        this.serviceStartDt = serviceStartDt;
    }

    
    @Column(name="SERVICE_END_DT", length=7)
    public LocalDate getServiceEndDt() {
        return this.serviceEndDt;
    }
    
    public void setServiceEndDt(LocalDate serviceEndDt) {
        this.serviceEndDt = serviceEndDt;
    }

    
    @Column(name="MCO_NUM", length=10)
    public String getMcoNum() {
        return this.mcoNum;
    }
    
    public void setMcoNum(String mcoNum) {
        this.mcoNum = mcoNum;
    }

    
    @Column(name="ACTION_DT", length=7)
    public LocalDate getActionDt() {
        return this.actionDt;
    }
    
    public void setActionDt(LocalDate actionDt) {
        this.actionDt = actionDt;
    }

    
    @Column(name="FILED_DT", length=7)
    public LocalDate getFiledDt() {
        return this.filedDt;
    }
    
    public void setFiledDt(LocalDate filedDt) {
        this.filedDt = filedDt;
    }

    
    @Column(name="ISSUE_APL", length=10)
    public String getIssueApl() {
        return this.issueApl;
    }
    
    public void setIssueApl(String issueApl) {
        this.issueApl = issueApl;
    }

    
    @Column(name="DECISION_DT", length=7)
    public LocalDate getDecisionDt() {
        return this.decisionDt;
    }
    
    public void setDecisionDt(LocalDate decisionDt) {
        this.decisionDt = decisionDt;
    }

    
    @Column(name="TRACK_NUM", length=10)
    public String getTrackNum() {
        return this.trackNum;
    }
    
    public void setTrackNum(String trackNum) {
        this.trackNum = trackNum;
    }

    
    @Column(name="MCO_NOTES", length=4000)
    public String getMcoNotes() {
        return this.mcoNotes;
    }
    
    public void setMcoNotes(String mcoNotes) {
        this.mcoNotes = mcoNotes;
    }

    
    @Column(name="EXT_REV_REQ_IND", length=1)
    public Character getExtRevReqInd() {
        return this.extRevReqInd;
    }
    
    public void setExtRevReqInd(Character extRevReqInd) {
        this.extRevReqInd = extRevReqInd;
    }

    
    @Column(name="EXP_DEC_DT", length=7)
    public LocalDate getExpDecDt() {
        return this.expDecDt;
    }
    
    public void setExpDecDt(LocalDate expDecDt) {
        this.expDecDt = expDecDt;
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


