package com.deloitte.nextgen.appreg.web.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import com.deloitte.nextgen.appreg.client.enums.Active;
import lombok.*;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = DcIndv.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
public class DcIndv extends TypeOneBaseEntity<Long> {

    @Transient
    public static final String TABLE_NAME = "DC_INDV";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "DC_INDV_1SQ")
    private Long indvId;
    private Long ssn;
    private java.sql.Timestamp dobDt;
    private String lastName;
    private String firstName;
    private String sufxName;
    private String midName;
    private String dobVrfCd;
    private String raceCd;
    private Character ssaValidationSw;
    private String ssnVrfCd;
    private String ethnicityCd;
    private Character genderCd;
    private Character inactiveInd;
    private Character fileClearanceSw;
    private String mergeSeparateInd;
    private java.sql.Timestamp deathDt;
    private String mhmrCaseNum;
    private String deceasedDtVrfCd;
    private Character deleteSw;
    private Long claimedSsn;
    private String ssaVrfCd;
    private Character estimatedDobSw;
    @Convert(converter = ActiveConverter.class)
    private Active authRepSw;
    private String citizenshipVrf;
    private String identityVrf;
    private java.sql.Timestamp citizenshipDt;
    private java.sql.Timestamp identityDt;
    private java.sql.Timestamp fileClearanceDt;
    private Character ebtSw;
    private String prefixName;
    @Convert(converter = ActiveConverter.class)
    private Active voteRegWishSw;
    @Convert(converter = ActiveConverter.class)
    private Active resAddrSw;
    private String addrFormat;
    private String addrLine1;
    private String addrLine2;
    private String addrLine3;
    private String addrCountyCd;
    private String addrCity;
    private String addrStateCd;
    private String addrMilitary;
    private String apoFpoAddr;
    private String addrZip5;
    private String addrZip4;
    private String addrCareOfLine;
    private String taxStatus;
    private String taxStatusVerf;
    @Convert(converter = ActiveConverter.class)
    private Active interpreterSw;
    private String communicationAsst;
    private String primaryLang;
    private String otherLanguage;
    private String disabilityAccom;
    private String otherAccomodation;
    @Convert(converter = ActiveConverter.class)
    private Active representativeSw;
    @Convert(converter = ActiveConverter.class)
    private Active ebtCardSw;
    private String organizationName;
    private String organizationId;
    private java.sql.Timestamp verfReceivedDt;
    private Long gmwdFmonthCnt;
}
