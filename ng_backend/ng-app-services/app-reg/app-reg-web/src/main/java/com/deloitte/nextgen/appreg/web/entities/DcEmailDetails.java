package com.deloitte.nextgen.appreg.web.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data // getter and setters
@Entity
@Table(name = DcEmailDetails.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
public class DcEmailDetails extends TypeOneBaseEntity<Long> {

    @Transient
    public static final String TABLE_NAME = "DC_EMAIL_DETAILS";

    @Id
    private Long emailSeqNum;
    private Long authrepSeqNum;
    private String emailTypeCd;
    private String email;
    private String emailComments;
    private String emailSrcTyp;
}
