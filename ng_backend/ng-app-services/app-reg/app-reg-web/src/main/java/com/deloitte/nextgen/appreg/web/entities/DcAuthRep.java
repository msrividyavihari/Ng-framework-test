package com.deloitte.nextgen.appreg.web.entities;

import com.deloitte.nextgen.appreg.web.entities.generated.DcAuthRepPK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = DcAuthRep.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
@IdClass(DcAuthRepPK.class)
public class DcAuthRep extends TypeOneBaseEntity<DcAuthRepPK> {

    @Transient
    public static final String TABLE_NAME = "DC_AUTH_REP";

    @Id
    private Long caseNum;
    @Id
    private Long authrepSeqNum;
    private String authrepFirstName;
    private String authrepMidName;
    private String authrepLastName;
    private String authrepSufxName;
    private String authrepOrgName;
    private String authrepIndvIdVrfCd;
    private Character authorizedSnapRepSw;
    private String rcvsNonMedicaidNoticesCd;
    private Character rcvsSnapCertFormsSw;
    private Character rcvsTanfRedetFormsSw;
    @Id
    private java.sql.Timestamp effBeginDt;
    private java.sql.Timestamp effEndDt;
    private Character snapEbtCardSw;
    private Long caseAddrSeqNum;
    private String ebtPwd;
    private String authrepOrgId;
    private String programCdList;
    private String careGiverSw;
    private String identificationVrfCd;
    private Character histNavInd;
}
