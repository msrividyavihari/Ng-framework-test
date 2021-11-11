package com.deloitte.nextgen.appreg.web.entities;


import com.deloitte.nextgen.appreg.web.entities.generated.ArAppProgramPK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import com.deloitte.nextgen.appreg.client.enums.Active;
import lombok.*;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = ArAppProgram.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
@IdClass(ArAppProgramPK.class)
public class ArAppProgram extends TypeOneBaseEntity<ArAppProgramPK> {

    @Transient
    public static final String TABLE_NAME = "AR_APP_PROGRAM";

    @Id
    private String appNum;
    @Id
    private String programCd;
    @Id
    private String priorMedicaidCd;
    private java.sql.Timestamp requestDt;
    @Convert(converter = ActiveConverter.class)
    private Active expeditedSw;
    private String progStatusCd;
    private java.sql.Timestamp progStatusDt;
    private String serServiceCd;
    private java.sql.Timestamp expScreenDt;
    private String medicaidTypeCd;
    private char waitlistScreeningSw;
    private String withdrwlReason;
    private char pckProgSw;
    private String clinicCd;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="app_num")
//    private ArApplicationForAid arApplicationForAid;
}



