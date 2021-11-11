package com.deloitte.nextgen.appreg.web.entities;


import com.deloitte.nextgen.appreg.web.entities.generated.ArAppProgramIndvPK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import com.deloitte.nextgen.appreg.client.enums.Active;
import lombok.*;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = ArAppProgramIndv.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
@IdClass(ArAppProgramIndvPK.class)
public class ArAppProgramIndv extends TypeOneBaseEntity<ArAppProgramIndvPK> {

    @Transient
    public static final String TABLE_NAME = "AR_APP_PROGRAM_INDV";

    @Id
    private String appNum;
    @Id
    private String programCd;
    @Id
    private long indvId;
    @Id
    private String priorMedicaidCd;
    private java.sql.Timestamp requestDt;
    @Convert(converter = ActiveConverter.class)
    private Active aidRequestSw;
    private char dchApprovedMaEligSw;
    private String maFormCd;
    private char pckProgSw;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="app_num")
//    private ArApplicationForAid arApplicationForAid;
}



