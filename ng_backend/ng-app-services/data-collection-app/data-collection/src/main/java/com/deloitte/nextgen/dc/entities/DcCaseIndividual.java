package com.deloitte.nextgen.dc.entities;


import com.deloitte.nextgen.dc.entities.composite.DcCaseIndividualCargoId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.two.TypeTwoBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.dc.repository.DcCaseIndividualRepository;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data // getter and setters
@Entity
@Table(name = "DC_CASE_INDIVIDUAL")
@EntityType(type= TypeEnum.TWO, customRepository = DcCaseIndividualRepository.class)
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@IdClass(DcCaseIndividualCargoId.class)
public class DcCaseIndividual extends TypeTwoBaseEntity<String> {

    private char activeInCaseSw;
    @Id
    private long caseNum;
    private char employeeSw;
    private char headOfHouseholdSw;
    @Id
    private long indvId;
    private java.lang.String rowid;
    private java.sql.Timestamp effBeginDt;
    private java.sql.Timestamp effEndDt;

}



