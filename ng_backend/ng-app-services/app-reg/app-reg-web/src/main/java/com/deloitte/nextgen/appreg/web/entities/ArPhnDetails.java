package com.deloitte.nextgen.appreg.web.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = ArPhnDetails.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
public class ArPhnDetails extends TypeOneBaseEntity<Long> {

    @Transient
    public static final String TABLE_NAME = "AR_PHN_DETAILS";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "AR_PHN_DETAILS_1SQ")
    private Long phnSeqNum;
    private String appNum;
    private String phnTypeCd;
    private String phnNum;
    private String phnComments;
    private String phoneExtn;
    private String phoneSrcTyp;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="app_num")
//    private ArApplicationForAid arApplicationForAid;
}
