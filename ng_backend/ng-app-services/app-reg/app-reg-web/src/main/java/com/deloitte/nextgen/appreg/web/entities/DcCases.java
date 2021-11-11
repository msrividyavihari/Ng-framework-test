package com.deloitte.nextgen.appreg.web.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data // getter and setters
@Entity
@Table(name = DcCases.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
public class DcCases extends TypeOneBaseEntity<Long> {

    @Transient
    public static final String TABLE_NAME = "DC_CASES";

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

}
