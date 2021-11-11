package com.deloitte.nextgen.ssp.entities;

import com.deloitte.nextgen.ssp.entities.converters.ActiveConverter;
import com.deloitte.nextgen.ssp.enums.Active;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;

@Data
@NoArgsConstructor
public class T1004AppIndvDto {
    private String appNum;
    private Long indvSeqNum;
    private Character alnSponserSw;
    @Convert(converter = ActiveConverter.class)
    private Active bcRqstSw;
    @JsonFormat(pattern = "MM/dd/yyyy")
    @JsonProperty("dob")
    private java.sql.Timestamp brthDt;
    @Convert(converter = ActiveConverter.class)
    private Active fpwRqstSw;
    @Convert(converter = ActiveConverter.class)
    private Active fsRqstSw;
    @JsonProperty("firstName")
    private String fstNam;
    private Character indvEstbStatInd;
    @JsonProperty("lastName")
    private String lastNam;
    @Convert(converter = ActiveConverter.class)
    private Active maRqstSw;
    @JsonProperty("middleName")
    private String midInit;
    private Long pinNum;
    @JsonProperty("primaryApplicantSw")
    @Convert(converter = ActiveConverter.class)
    private Active primPrsnSw;
    @JsonProperty("gender")
    private Character sexInd;
    @JsonProperty("sufxName")
    private String sfxNam;
    private Character sndxNum;
    @JsonProperty("ssn")
    private Long ssnNum;
    @Convert(converter = ActiveConverter.class)
    private Active rlvnInd;
    @Convert(converter = ActiveConverter.class)
    private Active indvExistsInBridgesSw;
    private Long bridgesIndvId;
    private Character seRqstSw;
    private Character veteranSw;
    @JsonProperty("aliasFirstName")
    private String aliasFstNam;
    @JsonProperty("aliasSw")
    private String aliasInd;
    @JsonProperty("aliasLastName")
    private String aliasLastNam;
    @JsonProperty("aliasMiddleName")
    private String aliasMidInit;
    @JsonProperty("aliasSuffix")
    private String aliasSuffixName;
    @Convert(converter = ActiveConverter.class)
    private Active commAsstBailleInd;
    private String commAsstEmailInd;
    private String commAsstLgPrintInd;
    private String commAsstNoneInd;
    private String commAsstSlInterpInd;
    private String commAsstTtyInd;
    private String commAsstVedrelInd;
    private java.sql.Timestamp brstCerCancerDiagDt;
    private String hspcDscCd;
    private String ifOutArrangement;
    private String interOthDsc;
    private String brstCerCancerDiagInd;
    private String langOthDsc;
    private String liveAtSameAddrInd;
    private String raceChineseInd;
    private String raceFilipinoInd;
    private String raceGuamInd;
    private String raceJapaneseInd;
    private String raceKoreanInd;
    private String raceMembFedRecTrbInd;
    private String raceNhpiInd;
    private String raceOthAsianInd;
    private String raceOthInd;
    private String raceSamoanInd;
    private String raceVieInd;
    @JsonProperty("interpreterSw")
    private String reqInterpInd;
    private String ssnInfoAckInd;
    private String taxDpOutsideHomeInd;
    private String taxJointFileInd;
    private String tribeName;
    private String blndDablInd;
    private String isMapped;
    private String isChanged;
    private String commInterpResp;
    private String commInterpCd;
    private String commAsstFlInterpInd;
    private String lawPresFedHubInd;
    private String nonCtznFedHubInd;
    private String usCtznFedHubInd;
    private String ssnVrfnFedHubInd;
    private String dobFedHubInd;
    private String unearnedIncDolInd;
    private String earnedIncDolInd;
    private String taxClOutsideHomeInd;
    private String taxFilingStatus;
    private String raceEastAsian;
    private String racePersian;
    private String raceUnknown;
    private String referralId;
    private java.sql.Timestamp chgDt;
    private Character courtOrder;
    private Character childInDfcsCustory;
    @JsonProperty("age")
    private Long clientId;
    private Character veteranTypeSw;
    private String dlNumber;
    private String dlStateCd;
    private java.sql.Timestamp dlExpirationDt;
    @JsonProperty("includeApplicantSw")
    private String dlValidSw;
}
