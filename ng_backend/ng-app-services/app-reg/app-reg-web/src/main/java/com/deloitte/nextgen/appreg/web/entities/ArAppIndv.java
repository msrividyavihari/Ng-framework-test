package com.deloitte.nextgen.appreg.web.entities;
import com.deloitte.nextgen.appreg.web.entities.generated.ArAppIndvPK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = ArAppIndv.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
@IdClass(ArAppIndvPK.class)
@Transactional
public class ArAppIndv extends TypeOneBaseEntity<ArAppIndvPK> {

    @Transient
    public static final String TABLE_NAME = "AR_APP_INDV";

    @Id
    private String appNum;
    @Id
    private Long indvId;
    private Character employeeSw;
    @Convert(converter = ActiveConverter.class)
    private Active headOfHouseholdSw;
    private Character indvStatusSw;


//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="app_num")
//    private ArApplicationForAid arApplicationForAid;

}



