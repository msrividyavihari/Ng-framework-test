package com.deloitte.nextgen.dc.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.dc.repository.DcIndividualRepository;
import lombok.*;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = "DC_CASES")
@EntityType(type= TypeEnum.ONE, customRepository = DcIndividualRepository.class)
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DcCases extends TypeOneBaseEntity<String> {
    @Id
    private Long caseNum;
    private java.sql.Timestamp caseClosedDt;
    private String caseStatusCd;
    private String caseModeCd;
    private Character reactivationInd;
    private java.sql.Timestamp reactivationDt;
    private java.sql.Timestamp interviewDt;
    private java.sql.Timestamp appRecvdDt;
    private Long officeNum;
    private Character authRepSw;
    private String interviewTypeCd;
    private Character unableToLocateSw;
    private java.sql.Timestamp actionDt;
    private String relationshipIndvCd;
    private String processId;
    private String userActionCd;
    private java.sql.Timestamp conversionDt;
    private java.sql.Timestamp caseStatusDt;
    private Character tribalCaseSw;
    private Character appSignedSw;
    private String appModeCd;
    private String phNum;
    private String workPhNum;
    private String otherPhNum;
    private String waitlistScreeningSw;
    private String ucmTranNum;
    private String confidentialCaseSw;
    private String restrictAccsCustPortalSw;
    private String prefCntcMthdCd;
    private String prefCntcEmailAdr;
    private Character caseOrTask;
    private Character medicaidFormSw;
    private java.sql.Timestamp medicaidFormDt;
    private String workFlowStatusCd;
    private String wicDisclosureCd;
    private String firstAppNum;
    private java.lang.String rowid;
}
