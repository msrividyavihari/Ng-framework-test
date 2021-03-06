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
 * AmCommPrefA generated by hbm2java
 */
@Entity
@Table(name="AM_COMM_PREF_A"
    ,schema="IE_APP_ONLINE"
)
public class AmCommPrefA  implements java.io.Serializable {


     private AmCommPrefAId id;
     private char deliveryMethod;
     private char alertReceiveType;
     private String emailId;
     private String preferredLang;
     private BigInteger cellPhNum;
     private String cellPhCarrier;
     private String createUserId;
     private LocalDate createDt;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;
     private String auditUserId;
     private LocalDate auditDt;

    public AmCommPrefA() {
    }

    public AmCommPrefA(AmCommPrefAId id, char deliveryMethod, char alertReceiveType, String emailId, String preferredLang, BigInteger cellPhNum, String cellPhCarrier, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt) {
       this.id = id;
       this.deliveryMethod = deliveryMethod;
       this.alertReceiveType = alertReceiveType;
       this.emailId = emailId;
       this.preferredLang = preferredLang;
       this.cellPhNum = cellPhNum;
       this.cellPhCarrier = cellPhCarrier;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.auditUserId = auditUserId;
       this.auditDt = auditDt;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="indvId", column=@Column(name="INDV_ID", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="historySeq", column=@Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0) ) } )
    public AmCommPrefAId getId() {
        return this.id;
    }
    
    public void setId(AmCommPrefAId id) {
        this.id = id;
    }

    
    @Column(name="DELIVERY_METHOD", nullable=false, length=1)
    public char getDeliveryMethod() {
        return this.deliveryMethod;
    }
    
    public void setDeliveryMethod(char deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    
    @Column(name="ALERT_RECEIVE_TYPE", nullable=false, length=1)
    public char getAlertReceiveType() {
        return this.alertReceiveType;
    }
    
    public void setAlertReceiveType(char alertReceiveType) {
        this.alertReceiveType = alertReceiveType;
    }

    
    @Column(name="EMAIL_ID", nullable=false, length=125)
    public String getEmailId() {
        return this.emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    
    @Column(name="PREFERRED_LANG", nullable=false, length=2)
    public String getPreferredLang() {
        return this.preferredLang;
    }
    
    public void setPreferredLang(String preferredLang) {
        this.preferredLang = preferredLang;
    }

    
    @Column(name="CELL_PH_NUM", nullable=false, precision=22, scale=0)
    public BigInteger getCellPhNum() {
        return this.cellPhNum;
    }
    
    public void setCellPhNum(BigInteger cellPhNum) {
        this.cellPhNum = cellPhNum;
    }

    
    @Column(name="CELL_PH_CARRIER", nullable=false, length=5)
    public String getCellPhCarrier() {
        return this.cellPhCarrier;
    }
    
    public void setCellPhCarrier(String cellPhCarrier) {
        this.cellPhCarrier = cellPhCarrier;
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


