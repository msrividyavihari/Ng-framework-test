package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.DcHeadOfHouseholdId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.DcCasesRepository;
import com.deloitte.nextgen.repository.DcHeadOfHouseholdRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="DC_HEAD_OF_HOUSEHOLD")
@EntityType(type= TypeEnum.ONE, customRepository = DcHeadOfHouseholdRepository.class)
@Data
@NoArgsConstructor
@IdClass(DcHeadOfHouseholdId.class)
public class DcHeadOfHousehold extends TypeOneBaseEntity<Long> implements Serializable {
    @Id
    @Column(name = "CASE_NUM")
    private Long caseNum;
    @Column(name = "INDV_ID")
    private Long indvId;
    @Column(name = "HOH_BEGIN_DT")
    private Timestamp hohBeginDt;
    @Column(name = "HOH_END_DT")
    private Timestamp hohEndDt;
    @Id
    @Column(name = "SEQUENCE_NUM")
    private Long sequenceNum;
}
