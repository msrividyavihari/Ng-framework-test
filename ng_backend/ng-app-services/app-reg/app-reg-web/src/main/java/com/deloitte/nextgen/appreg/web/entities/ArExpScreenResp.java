package com.deloitte.nextgen.appreg.web.entities;

import com.deloitte.nextgen.appreg.web.entities.generated.ArExpScreenRespPK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = ArExpScreenResp.TABLE_NAME)
@EntityType(type= TypeEnum.ZERO)
@NoArgsConstructor
@IdClass(ArExpScreenRespPK.class)
public class ArExpScreenResp extends TypeZeroBaseEntity<ArExpScreenRespPK> {

    @Transient
    public static final String TABLE_NAME = "AR_EXP_SCREEN_RESP";

    @Id
    private String appNum;
    @Id
    private String questCd;
    private String response;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="app_num")
//    private ArApplicationForAid arApplicationForAid;
}
