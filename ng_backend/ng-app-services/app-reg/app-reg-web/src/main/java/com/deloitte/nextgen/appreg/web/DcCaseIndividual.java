package com.deloitte.nextgen.appreg.web;

import com.deloitte.nextgen.appreg.web.entities.DcCases;
import com.deloitte.nextgen.appreg.web.generated.DcCaseIndividualPK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = DcCaseIndividual.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
@IdClass(DcCaseIndividualPK.class)
public class DcCaseIndividual extends TypeOneBaseEntity<DcCaseIndividualPK> {

    @Transient
    public static final String TABLE_NAME = "DC_CASE_INDIVIDUAL";

    private char activeInCaseSw;
    @Id
    private long caseNum;
    private char employeeSw;
    private char headOfHouseholdSw;
    @Id
    private long indvId;
    @Id
    private java.sql.Timestamp effBeginDt;
    private java.sql.Timestamp effEndDt;
}
