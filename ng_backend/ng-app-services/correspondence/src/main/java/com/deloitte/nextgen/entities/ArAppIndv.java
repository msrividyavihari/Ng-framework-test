package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.ArAppIndvId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.ArAppIndvRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "AR_APP_INDV")
@EntityType(type= TypeEnum.ONE, customRepository = ArAppIndvRepository.class)
@NoArgsConstructor
@IdClass(ArAppIndvId.class)
public class ArAppIndv extends TypeOneBaseEntity<Long> implements Serializable {
    @Id
    @Column(name = "APP_NUM")
    private String appNum;
    @Column(name = "EMPLOYEE_SW")
    private Character employeeSw;
    @Column(name = "HEAD_OF_HOUSEHOLD_SW")
    private Character headOfHouseholdSw;
    @Id
    @Column(name = "INDV_ID")
    private Long indvId;
}
