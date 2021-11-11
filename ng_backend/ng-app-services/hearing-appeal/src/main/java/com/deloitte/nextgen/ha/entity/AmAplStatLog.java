package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * AmAplStatLog generated by hbm2java
 */
@Entity
@Table(name="AM_APL_STAT_LOG")
@EntityType(type= TypeEnum.ONE)
@EqualsAndHashCode(callSuper=false)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AmAplStatLogId.class)
@Embeddable
public class AmAplStatLog  extends TypeOneBaseEntity<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AM_APL_STAT_LOG_2SQ")
    @Column(name="STAT_LOG_SEQ")
    private BigInteger statLogSeq;
    @Id
    @Column(name="APL_NUM")
    private BigInteger aplNum;
    // private BigInteger historySeq;

   /*  @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AM_APL_STAT_LOG_2SQ")
     private AmAplStatLogId id;

     @Column(name="STAT_LOG_SEQ", nullable=false, precision=22, scale=0)
@Column(name="APL_NUM", nullable=false, precision=22, scale=0)
     */


    @Column(name="APL_STATUS")
    private String aplStatus;
    @Column(name="DELETE_IND")
    private String deleteInd;
    @Column(name="COMMENTS")
    private String comments;
    @Column(name="STATUS_EFF_DT")
    private LocalDate statusEffDt;
    /*@Column(name="HISTORY_SEQ")
     private BigInteger historySeq;
    @Column(name="CREATE_USER_ID")
     private String createUserId;
    @Column(name="CREATE_DT")
     private LocalDate createDt;
    @Column(name="UNIQUE_TRANS_ID")
     private BigInteger uniqueTransId;
    @Column(name="ARCHIVE_DT")
     private LocalDate archiveDt;*/
    @Column(name="APL_STATUS_RSN_CD")
    private String aplStatusRsnCd;
    @Column(name="PRIOR_HEARING_DT")
    private LocalDate priorHearingDt;
    @Column(name="SEND_ACK_LETTER_SW")
    private Character sendAckLetterSw;
    @Column(name="COB_REMOVED_SW")
    private Character cobRemovedSw;
    /*  @Column(name="UPDATE_DT")
       private LocalDate updateDt;*/
    @Column(name="UPDATE_USER_ID")
    private String updateUserId;
    @Column(name="STATUS_CREATE_DT")
    private LocalDate statusCreateDt;
    @Column(name="GRP_APL_SW")
    private Character grpAplSw;
    @Column(name="TERM_DT")
    private LocalDate termDt;

   /* public AmAplStatLog() {
    }


    public AmAplStatLog(AmAplStatLogId id, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
        this.id = id;
        this.historySeq = historySeq;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
    }
    public AmAplStatLog(AmAplStatLogId id, String aplStatus, String deleteInd, String comments, LocalDate statusEffDt, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String aplStatusRsnCd, LocalDate priorHearingDt, Character sendAckLetterSw, Character cobRemovedSw, LocalDate updateDt, String updateUserId, LocalDate statusCreateDt, Character grpAplSw, LocalDate termDt) {
       this.id = id;
       this.aplStatus = aplStatus;
       this.deleteInd = deleteInd;
       this.comments = comments;
       this.statusEffDt = statusEffDt;
       this.historySeq = historySeq;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.aplStatusRsnCd = aplStatusRsnCd;
       this.priorHearingDt = priorHearingDt;
       this.sendAckLetterSw = sendAckLetterSw;
       this.cobRemovedSw = cobRemovedSw;
       this.updateDt = updateDt;
       this.updateUserId = updateUserId;
       this.statusCreateDt = statusCreateDt;
       this.grpAplSw = grpAplSw;
       this.termDt = termDt;
    }

     @EmbeddedId


    @AttributeOverrides( {
        @AttributeOverride(name="statLogSeq", column=@Column(name="STAT_LOG_SEQ", nullable=false, precision=22, scale=0) ),
        @AttributeOverride(name="aplNum", column=@Column(name="APL_NUM", nullable=false, precision=22, scale=0) ) } )
    public AmAplStatLogId getId() {
        return this.id;
    }

    public void setId(AmAplStatLogId id) {
        this.id = id;
    }


    @Column(name="APL_STATUS", length=5)
    public String getAplStatus() {
        return this.aplStatus;
    }

    public void setAplStatus(String aplStatus) {
        this.aplStatus = aplStatus;
    }


    @Column(name="DELETE_IND", length=2)
    public String getDeleteInd() {
        return this.deleteInd;
    }

    public void setDeleteInd(String deleteInd) {
        this.deleteInd = deleteInd;
    }


    @Column(name="COMMENTS", length=4000)
    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    @Column(name="STATUS_EFF_DT", length=7)
    public LocalDate getStatusEffDt() {
        return this.statusEffDt;
    }

    public void setStatusEffDt(LocalDate statusEffDt) {
        this.statusEffDt = statusEffDt;
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


    @Column(name="APL_STATUS_RSN_CD", length=3)
    public String getAplStatusRsnCd() {
        return this.aplStatusRsnCd;
    }

    public void setAplStatusRsnCd(String aplStatusRsnCd) {
        this.aplStatusRsnCd = aplStatusRsnCd;
    }


    @Column(name="PRIOR_HEARING_DT", length=7)
    public LocalDate getPriorHearingDt() {
        return this.priorHearingDt;
    }

    public void setPriorHearingDt(LocalDate priorHearingDt) {
        this.priorHearingDt = priorHearingDt;
    }


    @Column(name="SEND_ACK_LETTER_SW", length=1)
    public Character getSendAckLetterSw() {
        return this.sendAckLetterSw;
    }

    public void setSendAckLetterSw(Character sendAckLetterSw) {
        this.sendAckLetterSw = sendAckLetterSw;
    }


    @Column(name="COB_REMOVED_SW", length=1)
    public Character getCobRemovedSw() {
        return this.cobRemovedSw;
    }

    public void setCobRemovedSw(Character cobRemovedSw) {
        this.cobRemovedSw = cobRemovedSw;
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


    @Column(name="STATUS_CREATE_DT", length=7)
    public LocalDate getStatusCreateDt() {
        return this.statusCreateDt;
    }

    public void setStatusCreateDt(LocalDate statusCreateDt) {
        this.statusCreateDt = statusCreateDt;
    }


    @Column(name="GRP_APL_SW", length=1)
    public Character getGrpAplSw() {
        return this.grpAplSw;
    }

    public void setGrpAplSw(Character grpAplSw) {
        this.grpAplSw = grpAplSw;
    }


    @Column(name="TERM_DT", length=7)
    public LocalDate getTermDt() {
        return this.termDt;
    }

    public void setTermDt(LocalDate termDt) {
        this.termDt = termDt;
    }
*/



}
