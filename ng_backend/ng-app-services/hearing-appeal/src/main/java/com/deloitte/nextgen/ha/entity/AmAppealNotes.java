package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Clob;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * AmAppealNotes generated by hbm2java
 */
@Entity
@Table(name="AM_APPEAL_NOTES")
@EntityType(type= TypeEnum.ONE)
@EqualsAndHashCode(callSuper=false)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AmAppealNotesId.class)
@Embeddable
public class AmAppealNotes  extends TypeOneBaseEntity<String> implements Serializable {


     //private AmAppealNotesId id;
    @Id
    @Column(name="APL_NUM", nullable=false, precision=22, scale=0)
     private BigInteger aplNum;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AM_APPEAL_NOTES_2SQ")
    @Column(name="APL_NOTES_SEQ_NUM", nullable=false, precision=22, scale=0)
     private BigInteger aplNotesSeqNum;
    @Column(name="DESCRIPTION_CD", length=70)
     private String descriptionCd;
    @Column(name="NOTES_TXT")
     private String  notesTxt;
    @Column(name="CATEGORY_CD", nullable=false, length=2)
     private String categoryCd;
     /*private BigInteger historySeq;
     private String createUserId;
     private LocalDate createDt;
     private LocalDate updateDt;
     private String updateUserId;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;*/
     @Column(name="PAGE_ID", length=10)
     private String pageId;

    /*public AmAppealNotes() {
    }

	
    public AmAppealNotes(AmAppealNotesId id, String categoryCd, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
        this.id = id;
        this.categoryCd = categoryCd;
        this.historySeq = historySeq;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
    }
    public AmAppealNotes(AmAppealNotesId id, String descriptionCd, String notesTxt, String categoryCd, BigInteger historySeq, String createUserId, LocalDate createDt, LocalDate updateDt, String updateUserId, BigInteger uniqueTransId, LocalDate archiveDt, String pageId) {
       this.id = id;
       this.descriptionCd = descriptionCd;
       this.notesTxt = notesTxt;
       this.categoryCd = categoryCd;
       this.historySeq = historySeq;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.updateDt = updateDt;
       this.updateUserId = updateUserId;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.pageId = pageId;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="aplNum", column=@Column(name="APL_NUM", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="aplNotesSeqNum", column=@Column(name="APL_NOTES_SEQ_NUM", nullable=false, precision=22, scale=0) ) } )
    public AmAppealNotesId getId() {
        return this.id;
    }
    
    public void setId(AmAppealNotesId id) {
        this.id = id;
    }

    
    @Column(name="DESCRIPTION_CD", length=70)
    public String getDescriptionCd() {
        return this.descriptionCd;
    }
    
    public void setDescriptionCd(String descriptionCd) {
        this.descriptionCd = descriptionCd;
    }

    
    @Column(name="NOTES_TXT")
    public String getNotesTxt() {
        return this.notesTxt;
    }
    
    public void setNotesTxt(String notesTxt) {
        this.notesTxt = notesTxt;
    }

    
    @Column(name="CATEGORY_CD", nullable=false, length=2)
    public String getCategoryCd() {
        return this.categoryCd;
    }
    
    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
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

    
    @Column(name="PAGE_ID", length=10)
    public String getPageId() {
        return this.pageId;
    }
    
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

*/


}

