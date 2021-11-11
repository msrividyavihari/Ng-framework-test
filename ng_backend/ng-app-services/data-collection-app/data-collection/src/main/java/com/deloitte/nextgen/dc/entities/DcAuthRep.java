package com.deloitte.nextgen.dc.entities;

import com.deloitte.nextgen.dc.entities.composite.DcAuthRepId;
import com.deloitte.nextgen.dc.repository.DcAuthRepRepository;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data // getter and setters
@Entity
@Table(name = "DC_AUTH_REP")
@EntityType(type= TypeEnum.ONE, customRepository = DcAuthRepRepository.class)
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@IdClass(DcAuthRepId.class)
public class DcAuthRep extends TypeOneBaseEntity<String> {
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
    private java.lang.String rowid;
}
