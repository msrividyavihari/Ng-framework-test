package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.ha.common.repository.AmAppealInfoRepository;
import com.deloitte.nextgen.ha.repository.HaAppealInfoRepository;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name="HA_APPEAL_INFO")
@EntityType(type= TypeEnum.ZERO, customRepository = HaAppealInfoRepository.class)
@EqualsAndHashCode(callSuper=false)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HaAppealInfo extends TypeZeroBaseEntity<BigInteger>  {

     @Id
     @Column(name="APL_NUM")
     private BigInteger aplNum;
     @Column(name="GROSS_DAYS")
     private BigInteger grossDays;
     @Column(name="NET_DAYS")
     private BigInteger netDays;
     @Column(name="DELAY_DAYS")
     private BigInteger delayDays;
     @Column(name="AGENCY_CD")
     private String agencyCd;
     @Column(name="CONTINUANCES")
     private BigInteger continuances;
     @Column(name="DAYS_LEFT_OPEN")
     private BigInteger daysLeftOpen;
     @Column(name="FAD_DUE_DAYS")
     private BigInteger fadDueDays;
     @Column(name="FAD_DUE_DT")
     private LocalDate fadDueDt;
     @Column(name="FAD_ISSUED")
     private LocalDate fadIssued;
     @Column(name="FAD_IMPLEMENTED")
     private LocalDate fadImplemented;
     @Column(name="HO_FAD_DUE_DT")
     private LocalDate hoFadDueDt;
     @Column(name="LAST_HEARD_DT")
     private LocalDate lastHeardDt;
     @Column(name="LAST_CONTINUED_DT")
     private LocalDate lastContinuedDt;
     @Column(name="FIRST_SCHEDULED_DT")
     private LocalDate firstScheduledDt;
     @Column(name="LAST_SCHEDULED_DT")
     private java.sql.Timestamp lastScheduledDt;
     @Column(name="LAST_STATUS")
     private String lastStatus;
     @Column(name="LAST_STATUS_EFF_DT")
     private java.sql.Timestamp lastStatusEffDt;
     @Column(name="FIRST_STATUS_EFF_DT")
     private LocalDate firstStatusEffDt;
     @Column(name="RECORD_DAYS_NUM")
     private Integer recordDaysNum;
     @Column(name="RESOLVED_DT")
     private LocalDate resolvedDt;
    /* @Column(name="ARCHIVE_DT")
     private java.sql.Timestamp archiveDt;
     @Column(name="CREATE_USER_ID")
     private String createUserId;
     @Column(name="UNIQUE_TRANS_ID")
     private BigInteger uniqueTransId;
     @Column(name="CREATE_DT")
     private java.sql.Timestamp createDt;
     @Column(name="UPDATE_DT")
     private LocalDate updateDt;
     @Column(name="UPDATE_USER_ID")
     private String updateUserId;*/
}


