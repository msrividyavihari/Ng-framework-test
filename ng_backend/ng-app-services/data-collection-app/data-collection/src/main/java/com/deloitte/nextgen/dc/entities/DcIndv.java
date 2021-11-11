package com.deloitte.nextgen.dc.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data // getter and setters
@Entity
@Table(name = "DC_INDV")
@EntityType(type= TypeEnum.ONE)
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DcIndv extends TypeOneBaseEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "DC_INDV_1SQ")
    @Column(name = "INDV_ID")
    private Long indvId;
    private Long ssn;
    private LocalDate dobDt;
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
    @Builder.Default
    private Character inactiveInd = 'N'; // default to No ;
    private Character fileClearanceSw;
    private String mergeSeparateInd;
    private java.sql.Timestamp deathDt;
    private String mhmrCaseNum;
    private String deceasedDtVrfCd;
    @Builder.Default
    private Character deleteSw = 'N'; // default to No;
    private Long claimedSsn;
    private String ssaVrfCd;
    private Character estimatedDobSw;
    private Character authRepSw;
    private String citizenshipVrf;
    private String identityVrf;
    private LocalDate citizenshipDt;
    private LocalDate identityDt;
    private LocalDate fileClearanceDt;
    private Character ebtSw;
    private String prefixName;
    private Character voteRegWishSw;
    private Character resAddrSw;
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
    private String interpreterSw;
    private String communicationAsst;
    private String primaryLang;
    private String otherLanguage;
    private String disabilityAccom;
    private String otherAccomodation;
    private String representativeSw;
    private String ebtCardSw;
    private String organizationName;
    private String organizationId;
    private LocalDate verfReceivedDt;
    private Long gmwdFmonthCnt;
    private String rowid;

    @PrePersist
    void prePersist() {
        if (this.deleteSw == null) this.deleteSw = 'N';
        if (this.inactiveInd == null) this.inactiveInd = 'N';
    }
}
