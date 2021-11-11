package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.DcAuthRepId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.DcAuthRepRepository;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="DC_AUTH_REP")
@EntityType(type= TypeEnum.ONE, customRepository = DcAuthRepRepository.class)
@Data
@NoArgsConstructor
@IdClass(DcAuthRepId.class)
public class DcAuthRep extends TypeOneBaseEntity<Long> implements Serializable {

    @Id
    private Long caseNum;
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
    private Character snapEbtCardSw;
    private Long caseAddrSeqNum;
    private String ebtPwd;
    private String authrepOrgId;
    private String programCdList;
    private String careGiverSw;
    private String identificationVrfCd;

}
