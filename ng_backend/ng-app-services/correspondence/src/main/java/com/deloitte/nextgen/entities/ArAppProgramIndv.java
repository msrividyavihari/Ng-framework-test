package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.ArAppProgramIndvId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.ArAppProgramIndvRepository;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "AR_APP_PROGRAM_INDV")
@EntityType(type= TypeEnum.ONE, customRepository = ArAppProgramIndvRepository.class)
@NoArgsConstructor
@IdClass(ArAppProgramIndvId.class)
public class ArAppProgramIndv extends TypeOneBaseEntity<Long> implements Serializable {
    @Id
    private String appNum;
    @Id
    private String programCd;
    @Id
    private long indvId;
    @Id
    private String priorMedicaidCd;
    private java.sql.Timestamp requestDt;
    private char aidRequestSw;
    private char dchApprovedMaEligSw;
    private String maFormCd;
    private char pckProgSw;
}
