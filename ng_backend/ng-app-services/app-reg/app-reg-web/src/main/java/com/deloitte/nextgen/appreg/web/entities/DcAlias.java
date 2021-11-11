package com.deloitte.nextgen.appreg.web.entities;

import com.deloitte.nextgen.appreg.web.entities.generated.DcAliasPK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = DcAlias.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
@IdClass(DcAliasPK.class)
public class DcAlias extends TypeOneBaseEntity<DcAliasPK> {

    @Transient
    public static final String TABLE_NAME = "DC_ALIAS";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "DC_ALIAS_1SQ")
    @Column(name = "ALIAS_IND_SEQ_NUM")
    private Long aliasIndSeqNum;
    @Id
    private Long indvId;
    private String lastName;
    private String firstName;
    private String midName;
    private String sufxName;
    private Long ssn;
    private java.sql.Timestamp dobDt;
    private Character genderCd;
    private Character piIndvSw;
    private Character primarySw;
    private String rowid;
}
