package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.*;


@Entity
@Table(name="AM_APL_GRP")
@EntityType(type= TypeEnum.ONE)
@EqualsAndHashCode(callSuper=false)
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AmAplGrpId.class)
public class AmAplGrp  extends TypeOneBaseEntity<String> {

     //private AmAplGrpId id;


   //  @GeneratedValue(strategy = GenerationType.IDENTITY)

     @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
     //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AM_APL_GRP_1SQ")
     @Column(name="APL_GROUP_NUM")
     private BigInteger aplGroupNum;
     @Id
     @Column(name="APL_NUM")
     private BigInteger aplNum;

     @Column(name="ACTIVE_IN_GROUP_SW", length=1)
     private Character activeInGroupSw;
     @Column(name="PRIMARY_SW", length=1)
     private Character primarySw;

   /*  private BigInteger historySeq;
     private String createUserId;
     private LocalDate createDt;
     private LocalDate updateDt;
     private String updateUserId;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;*/

    /*public AmAplGrp() {
    }

	
    public AmAplGrp(AmAplGrpId id, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
        this.id = id;
        this.historySeq = historySeq;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
    }
    public AmAplGrp(AmAplGrpId id, Character activeInGroupSw, Character primarySw, BigInteger historySeq, String createUserId, LocalDate createDt, LocalDate updateDt, String updateUserId, BigInteger uniqueTransId, LocalDate archiveDt) {
       this.id = id;
       this.activeInGroupSw = activeInGroupSw;
       this.primarySw = primarySw;
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
        @AttributeOverride(name="aplGroupNum", column=@Column(name="APL_GROUP_NUM", nullable=false, length=11) ), 
        @AttributeOverride(name="aplNum", column=@Column(name="APL_NUM", nullable=false, precision=38, scale=0) ) } )
    public AmAplGrpId getId() {
        return this.id;
    }
    
    public void setId(AmAplGrpId id) {
        this.id = id;
    }

    
    @Column(name="ACTIVE_IN_GROUP_SW", length=1)
    public Character getActiveInGroupSw() {
        return this.activeInGroupSw;
    }
    
    public void setActiveInGroupSw(Character activeInGroupSw) {
        this.activeInGroupSw = activeInGroupSw;
    }

    
    @Column(name="PRIMARY_SW", length=1)
    public Character getPrimarySw() {
        return this.primarySw;
    }
    
    public void setPrimarySw(Character primarySw) {
        this.primarySw = primarySw;
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
    }*/




}


