package com.deloitte.nextgen.entities;


import com.deloitte.nextgen.entities.composite.MoOfficesId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.MoOfficesRepository;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="MO_OFFICES")
@EntityType(type= TypeEnum.ONE, customRepository = MoOfficesRepository.class)
@Data
@NoArgsConstructor
@IdClass(MoOfficesId.class)
public class MoOffices extends TypeOneBaseEntity<Long> implements Serializable {
    @Id
    private Long officeNum;
    private String officeTypeCd;
    private String officeName;
    private Timestamp hoursFrom;
    private Timestamp hoursTo;
    private String faxNum;
    private String phNum;
    private String tddNum;
    private Long overbookApptPercent;
    private String caseAssignmentCd;
    private String regionCd;
    private Long contactEmpId;
    private Long multiplePgms;
    private String assignmentCriteriaCd;
    private Character specialAssignmentSw;
    private Timestamp effBeginDt;
    private Timestamp effEndDt;
    private String legalAidOfficeCd;
    private Long ssAfbOfficeNum;
    private Long ssRmcOfficeNum;
    private Character ssChangeReportToSw;
    private String officeContact;
    private Long phnNum2;
    private Long faxNum2;
    private String assignableCd;
    private String tribalNameCd;
    private String medicaidIssueAuthCd;
    private Character jurisidictionSw;
    private String activeStatusCd;
    private String areaCd;
}
