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
@Table(name = DcPhnXref.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
public class DcPhnXref extends TypeOneBaseEntity<Long> {

    @Transient
    public static final String TABLE_NAME = "DC_PHN_XREF";

    @Id
    private Long phnSeqNum;
    private Long phnSrcId;
    private String phnSrcTypeCd;
    private Long phnXrefSeqNum;
}
