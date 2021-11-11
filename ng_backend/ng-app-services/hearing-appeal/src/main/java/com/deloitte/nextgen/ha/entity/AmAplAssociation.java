package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;

/**
 * AmAplAssociation generated by hbm2java
 */

@Entity
@Table(name="AM_APL_ASSOCIATION")
@EntityType(type= TypeEnum.ONE)
@EqualsAndHashCode(callSuper=false)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AmAplAssociation   extends TypeOneBaseEntity<BigInteger> implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AM_APL_ASSOCIATION_1SQ")
    @Column(name="APL_ASSOC_SEQ", unique=true, nullable=false, precision=22, scale=0)
     private BigInteger aplAssocSeq;
    @Column(name="APL_NUM", nullable=false, precision=22, scale=0)
     private BigInteger aplNum;
    @Column(name="DEPT_REP_ID", precision=22, scale=0)
     private BigInteger deptRepId;
    @Column(name="CLIENT_REP_ID", precision=22, scale=0)
     private BigInteger clientRepId;

/*    @Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0)
     private BigInteger historySeq;
    @Column(name="CREATE_USER_ID", nullable=false, length=20)
     private String createUserId;

    @Column(name="CREATE_DT", nullable=false, length=7)
     private LocalDate createDt;
    @Column(name="UNIQUE_TRANS_ID", nullable=false, precision=22, scale=0)
     private BigInteger uniqueTransId;
    @Column(name="ARCHIVE_DT", nullable=false, length=7)
     private LocalDate archiveDt;
    @Column(name="UPDATE_DT", length=7)
     private LocalDate updateDt;
    @Column(name="UPDATE_USER_ID", length=20)
     private String updateUserId;*/

    /*public AmAplAssociation() {
    }

	
    public AmAplAssociation(BigInteger aplAssocSeq, BigInteger aplNum, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt) {
        this.aplAssocSeq = aplAssocSeq;
        this.aplNum = aplNum;
        this.historySeq = historySeq;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
    }
    public AmAplAssociation(BigInteger aplAssocSeq, BigInteger aplNum, BigInteger deptRepId, BigInteger clientRepId, BigInteger historySeq, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, LocalDate updateDt, String updateUserId) {
       this.aplAssocSeq = aplAssocSeq;
       this.aplNum = aplNum;
       this.deptRepId = deptRepId;
       this.clientRepId = clientRepId;
       this.historySeq = historySeq;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.updateDt = updateDt;
       this.updateUserId = updateUserId;
    }
   
     @Id 

    
    @Column(name="APL_ASSOC_SEQ", unique=true, nullable=false, precision=22, scale=0)
    public BigInteger getAplAssocSeq() {
        return this.aplAssocSeq;
    }
    
    public void setAplAssocSeq(BigInteger aplAssocSeq) {
        this.aplAssocSeq = aplAssocSeq;
    }

    
    @Column(name="APL_NUM", nullable=false, precision=22, scale=0)
    public BigInteger getAplNum() {
        return this.aplNum;
    }
    
    public void setAplNum(BigInteger aplNum) {
        this.aplNum = aplNum;
    }

    
    @Column(name="DEPT_REP_ID", precision=22, scale=0)
    public BigInteger getDeptRepId() {
        return this.deptRepId;
    }
    
    public void setDeptRepId(BigInteger deptRepId) {
        this.deptRepId = deptRepId;
    }

    
    @Column(name="CLIENT_REP_ID", precision=22, scale=0)
    public BigInteger getClientRepId() {
        return this.clientRepId;
    }
    
    public void setClientRepId(BigInteger clientRepId) {
        this.clientRepId = clientRepId;
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
*/



}


