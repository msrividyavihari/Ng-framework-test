package com.deloitte.nextgen.dc.entities;


import com.deloitte.nextgen.dc.entities.composite.DcCaseProgramCargoId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.two.TypeTwoBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.dc.repository.DcCaseProgramRepository;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@Data // getter and setters
@Entity
@Table(name = "DC_CASE_PROGRAM")
@EntityType(type= TypeEnum.TWO, customRepository = DcCaseProgramRepository.class)
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@IdClass(DcCaseProgramCargoId.class)
public class DcCaseProgram extends TypeTwoBaseEntity<String> {

    @Id
    private Long caseNum;
    @Id
    private String progCd;
    @Id
    private String priorMedicaidCd;
    @Id
    private Long priorMaSeqNum;
    private String progStatusCd;
    private Date progStatusDt;
    private Date progRescindDt;
    private String progRescindCd;
    private Date effBeginDt;
    private Date effEndDt;
    private Date requestDt;
    private Character expeditedSw;
    private Date applicationDt;
    private Date conversionDt;
    private Date withdrawDt;
    private Character withdrawSw;
    private Date requestDueDt;
    private Date delayRsnDt;
    private String delayRsnCd;
    private Character ottanfRequestedSw;
    private Date interviewDt;
    private String denialReasonCd;
    private String processId;
    private String serServiceCd;
    private Character firstExpScreenResultSw;
    private Character latestExpScreenResultSw;
    private Date latestExpScreenDt;
    private Character cvModeSw;
    private Date denialDt;
    private Date applicationReceivedDt;
    private Date recertReviewDueDt;
    private String progWithdrawCd;
    private Double opBenefitAmt;
    private String medicaidTypeCd;
    private Character waitlistScreeningSw;
    private Character reinstateSw;
    private String withdrawAddtionalInfo;
    private Date waitlistScreeningDt;
    private String closureReasonCd;
    private String priorityGroup;
    private Date priorMedicaidAppDate;
    private String reopenReason;
    private Character pckProgSw;
    private Character requestingGrgSw;
    private Date reportDt;
    private Date verfReceivedDt;
    private Long pckKidsCnt;
    private Date closureReportedOnDt;

}



