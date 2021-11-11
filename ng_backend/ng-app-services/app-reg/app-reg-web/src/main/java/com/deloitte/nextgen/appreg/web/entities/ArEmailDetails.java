package com.deloitte.nextgen.appreg.web.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = ArEmailDetails.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
public class ArEmailDetails extends TypeOneBaseEntity<Long> {

    @Transient
    public static final String TABLE_NAME = "AR_EMAIL_DETAILS";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "AR_EMAIL_DETAILS_1SQ")
    private Long emailSeqNum;
    private String appNum;
    private String emailTypeCd;
    private String email;
    private String emailComments;
    private String emailSrcTyp;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="app_num")
//    private ArApplicationForAid arApplicationForAid;
}
