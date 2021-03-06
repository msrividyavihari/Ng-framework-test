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
 * AmDocketDetailsA generated by hbm2java
 */
@Entity
@Table(name="AM_DOCKET_DETAILS_A"
    ,schema="IE_APP_ONLINE"
)
public class AmDocketDetailsA  implements java.io.Serializable {


     private AmDocketDetailsAId id;
     private BigInteger officeTimeSlotId;
     private BigInteger hearingOfficerId;
     private String typeCd;
     private LocalDate staggeredDurationTime;
     private String docketOutcomeCd;
     private String appointmentTypeCd;
     private LocalDate hearingDt;
     private String statusCd;
     private String createUserId;
     private LocalDate createDt;
     private BigInteger uniqueTransId;
     private LocalDate archiveDt;
     private String auditUserId;
     private LocalDate auditDt;
     private Character autoScheduledInd;
     private BigInteger docketTypeId;
     private LocalDate docketCreateDt;
     private String legacyTripId;
     private Character conversionInd;
     private BigInteger attorneyEmpId;
     private BigInteger witnessEmpId;
     private String hearingLocationCd;
     private String hrngLocZoneCd;
     private String updateUserId;
     private LocalDate updateDt;

    public AmDocketDetailsA() {
    }

	
    public AmDocketDetailsA(AmDocketDetailsAId id, BigInteger officeTimeSlotId, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt) {
        this.id = id;
        this.officeTimeSlotId = officeTimeSlotId;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.uniqueTransId = uniqueTransId;
        this.archiveDt = archiveDt;
        this.auditUserId = auditUserId;
        this.auditDt = auditDt;
    }
    public AmDocketDetailsA(AmDocketDetailsAId id, BigInteger officeTimeSlotId, BigInteger hearingOfficerId, String typeCd, LocalDate staggeredDurationTime, String docketOutcomeCd, String appointmentTypeCd, LocalDate hearingDt, String statusCd, String createUserId, LocalDate createDt, BigInteger uniqueTransId, LocalDate archiveDt, String auditUserId, LocalDate auditDt, Character autoScheduledInd, BigInteger docketTypeId, LocalDate docketCreateDt, String legacyTripId, Character conversionInd, BigInteger attorneyEmpId, BigInteger witnessEmpId, String hearingLocationCd, String hrngLocZoneCd, String updateUserId, LocalDate updateDt) {
       this.id = id;
       this.officeTimeSlotId = officeTimeSlotId;
       this.hearingOfficerId = hearingOfficerId;
       this.typeCd = typeCd;
       this.staggeredDurationTime = staggeredDurationTime;
       this.docketOutcomeCd = docketOutcomeCd;
       this.appointmentTypeCd = appointmentTypeCd;
       this.hearingDt = hearingDt;
       this.statusCd = statusCd;
       this.createUserId = createUserId;
       this.createDt = createDt;
       this.uniqueTransId = uniqueTransId;
       this.archiveDt = archiveDt;
       this.auditUserId = auditUserId;
       this.auditDt = auditDt;
       this.autoScheduledInd = autoScheduledInd;
       this.docketTypeId = docketTypeId;
       this.docketCreateDt = docketCreateDt;
       this.legacyTripId = legacyTripId;
       this.conversionInd = conversionInd;
       this.attorneyEmpId = attorneyEmpId;
       this.witnessEmpId = witnessEmpId;
       this.hearingLocationCd = hearingLocationCd;
       this.hrngLocZoneCd = hrngLocZoneCd;
       this.updateUserId = updateUserId;
       this.updateDt = updateDt;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="docketId", column=@Column(name="DOCKET_ID", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="historySeq", column=@Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0) ) } )
    public AmDocketDetailsAId getId() {
        return this.id;
    }
    
    public void setId(AmDocketDetailsAId id) {
        this.id = id;
    }

    
    @Column(name="OFFICE_TIME_SLOT_ID", nullable=false, precision=22, scale=0)
    public BigInteger getOfficeTimeSlotId() {
        return this.officeTimeSlotId;
    }
    
    public void setOfficeTimeSlotId(BigInteger officeTimeSlotId) {
        this.officeTimeSlotId = officeTimeSlotId;
    }

    
    @Column(name="HEARING_OFFICER_ID", precision=22, scale=0)
    public BigInteger getHearingOfficerId() {
        return this.hearingOfficerId;
    }
    
    public void setHearingOfficerId(BigInteger hearingOfficerId) {
        this.hearingOfficerId = hearingOfficerId;
    }

    
    @Column(name="TYPE_CD", length=2)
    public String getTypeCd() {
        return this.typeCd;
    }
    
    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    
    @Column(name="STAGGERED_DURATION_TIME", length=7)
    public LocalDate getStaggeredDurationTime() {
        return this.staggeredDurationTime;
    }
    
    public void setStaggeredDurationTime(LocalDate staggeredDurationTime) {
        this.staggeredDurationTime = staggeredDurationTime;
    }

    
    @Column(name="DOCKET_OUTCOME_CD", length=4)
    public String getDocketOutcomeCd() {
        return this.docketOutcomeCd;
    }
    
    public void setDocketOutcomeCd(String docketOutcomeCd) {
        this.docketOutcomeCd = docketOutcomeCd;
    }

    
    @Column(name="APPOINTMENT_TYPE_CD", length=2)
    public String getAppointmentTypeCd() {
        return this.appointmentTypeCd;
    }
    
    public void setAppointmentTypeCd(String appointmentTypeCd) {
        this.appointmentTypeCd = appointmentTypeCd;
    }

    
    @Column(name="HEARING_DT", length=7)
    public LocalDate getHearingDt() {
        return this.hearingDt;
    }
    
    public void setHearingDt(LocalDate hearingDt) {
        this.hearingDt = hearingDt;
    }

    
    @Column(name="STATUS_CD", length=5)
    public String getStatusCd() {
        return this.statusCd;
    }
    
    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
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

    
    @Column(name="AUTO_SCHEDULED_IND", length=1)
    public Character getAutoScheduledInd() {
        return this.autoScheduledInd;
    }
    
    public void setAutoScheduledInd(Character autoScheduledInd) {
        this.autoScheduledInd = autoScheduledInd;
    }

    
    @Column(name="DOCKET_TYPE_ID", precision=22, scale=0)
    public BigInteger getDocketTypeId() {
        return this.docketTypeId;
    }
    
    public void setDocketTypeId(BigInteger docketTypeId) {
        this.docketTypeId = docketTypeId;
    }

    
    @Column(name="DOCKET_CREATE_DT", length=7)
    public LocalDate getDocketCreateDt() {
        return this.docketCreateDt;
    }
    
    public void setDocketCreateDt(LocalDate docketCreateDt) {
        this.docketCreateDt = docketCreateDt;
    }

    
    @Column(name="LEGACY_TRIP_ID", length=10)
    public String getLegacyTripId() {
        return this.legacyTripId;
    }
    
    public void setLegacyTripId(String legacyTripId) {
        this.legacyTripId = legacyTripId;
    }

    
    @Column(name="CONVERSION_IND", length=1)
    public Character getConversionInd() {
        return this.conversionInd;
    }
    
    public void setConversionInd(Character conversionInd) {
        this.conversionInd = conversionInd;
    }

    
    @Column(name="ATTORNEY_EMP_ID", precision=22, scale=0)
    public BigInteger getAttorneyEmpId() {
        return this.attorneyEmpId;
    }
    
    public void setAttorneyEmpId(BigInteger attorneyEmpId) {
        this.attorneyEmpId = attorneyEmpId;
    }

    
    @Column(name="WITNESS_EMP_ID", precision=22, scale=0)
    public BigInteger getWitnessEmpId() {
        return this.witnessEmpId;
    }
    
    public void setWitnessEmpId(BigInteger witnessEmpId) {
        this.witnessEmpId = witnessEmpId;
    }

    
    @Column(name="HEARING_LOCATION_CD", length=2)
    public String getHearingLocationCd() {
        return this.hearingLocationCd;
    }
    
    public void setHearingLocationCd(String hearingLocationCd) {
        this.hearingLocationCd = hearingLocationCd;
    }

    
    @Column(name="HRNG_LOC_ZONE_CD", length=3)
    public String getHrngLocZoneCd() {
        return this.hrngLocZoneCd;
    }
    
    public void setHrngLocZoneCd(String hrngLocZoneCd) {
        this.hrngLocZoneCd = hrngLocZoneCd;
    }

    
    @Column(name="UPDATE_USER_ID", length=20)
    public String getUpdateUserId() {
        return this.updateUserId;
    }
    
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    
    @Column(name="UPDATE_DT", length=7)
    public LocalDate getUpdateDt() {
        return this.updateDt;
    }
    
    public void setUpdateDt(LocalDate updateDt) {
        this.updateDt = updateDt;
    }




}


