package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.ArAppProgramId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.ArAppProgramRepository;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "AR_APP_PROGRAM")
@EntityType(type= TypeEnum.ONE, customRepository = ArAppProgramRepository.class)
@NoArgsConstructor
@IdClass(ArAppProgramId.class)
public class ArAppProgram extends TypeOneBaseEntity<String> implements Serializable {
    @Id
    private String appNum;
    @Id
    private String programCd;
    @Id
    private String priorMedicaidCd;
    private Timestamp requestDt;
    private Character expeditedSw;
    private String progStatusCd;
    private Timestamp progStatusDt;
    private String serServiceCd;
    private Timestamp expScreenDt;
    private String medicaidTypeCd;
    private Character waitlistScreeningSw;
    private String withdrwlReason;
    private Character pckProgSw;
    private String clinicCd;

}
