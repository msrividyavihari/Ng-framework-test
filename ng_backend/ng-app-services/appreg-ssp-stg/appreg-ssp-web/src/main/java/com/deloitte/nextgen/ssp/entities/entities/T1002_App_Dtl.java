package com.deloitte.nextgen.ssp.entities.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = T1002_App_Dtl.TABLE_NAME)
@EntityType(type= TypeEnum.ZERO)
@NoArgsConstructor
public class T1002_App_Dtl extends TypeZeroBaseEntity<String> {

    @Transient
    public static final String TABLE_NAME = "T1002_APP_DTL";

    @Id
    private String appNum ;
    private Long agcyNum ;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp appFileDt ;
    private String appSrcCd ;
    private Long caseNum ;
    private Long cnssTrctNum ;
    private Character emerMaSw ;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp fpwFileDt ;
    private Character fpwRqstSw ;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp fsFileDt ;
    private Character fsRqstSw ;
    private Character hlessSw ;
    private String hshlAptAdr ;
    private String hshlCellPhnNum ;
    private String hshlCityAdr ;
    private String hshlDirAdr ;
    private String hshlEmailAdr ;
    @Column(name="HSHL_L2_ADR")
    private String hshlL2Adr ;
    private String hshlNumAdr ;
    private String hshlPhnNum ;
    private String hshlSfxAdr ;
    private String hshlSfxDirAdr ;
    private String hshlStAdr ;
    private String hshlStaAdr ;
    private String hshlUnitAdr ;
    private String hshlWorkPhnNum ;
    private String hshlZipAdr ;
    private String ipFstNam ;
    private String ipLastNam ;
    private String ipMidInit ;
    private Character ipResSw ;
    private String ipRltCd ;
    private String ipSfxNam ;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp maFileDt ;
    private Character maRqstSw ;
    private String msgPhnExtnNum ;
    private String msgPhnNum ;
    private Character onlnScrnrResp ;
    private String phnNumTyp ;
    private Long prefCntcInd ;
    private String prefCntcTmTxt ;
    private Long rfaNum ;
    private Long rgnNum ;
    private Character rtroMaResp ;
    private String signIndvTyp ;
    private Long signVldInd ;
    private Long spsSignInd ;
    private Character tbMaSw ;
    private String workPhnExtnNum ;
    private String cmtTxt ;
    private String addrTypeInd ;
    private Character seRqstSw ;
    private String fuelTypeCd ;
    @Column(name="HSHL_L1_ADR")
    private String hshlL1Adr ;
    private String hshlMailCityAdr ;
    private String hshlMailStAdr ;
    private Long hshlMailZipAdr ;
    private String hshlWithinGeoBndsOfIrFlg ;
    private String hshlNoStAddressDescr ;
    private String hshlAddrFormat ;
    private String hshlStNum ;
    private String hshlStFrac ;
    private String hshlStType ;
    private String hshlPostDir ;
    private String hshlDwlType ;
    @Column(name="HSHL_ADDRLINE_2")
    private String hshlAddrline2 ;
    @Column(name="HSHL_ZIP5_ADR")
    private String hshlZip5Adr ;
    @Column(name="HSHL_ZIP4_ADR")
    private String hshlZip4Adr ;
    private Character hshlLorSw ;
    private String hshlCareOf ;
    private String hshlDrivInst ;
    private String hshlMailAddrFormat ;
    private String hshlMailStNum ;
    private String hshlMailStFrac ;
    private String hshlMailStDir ;
    private String hshlMailStreetAdr ;
    private String hshlMailStType ;
    private String hshlMailPostDir ;
    private String hshlMailDwlType ;
    private String hshlMailAptAdr ;
    @Column(name="HSHL_MAIL_ZIP5_ADR")
    private String hshlMailZip5Adr ;
    @Column(name="HSHL_MAIL_ZIP4_ADR")
    private String hshlMailZip4Adr ;
    private String hshlMailCareOf ;
    private String livingArrangementCd ;
    private String spclArrgmentCountyCityName ;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp spclArrgmentStrtDt ;
    private String beforeMovingSpclArrgmentCd ;
    private String outOfHomeSpouseInd ;
    private String outOfHomeSpouseName ;
    private String outOfHomeSpouseAdr ;
    private String outOfHomeSpouseCity ;
    @Column(name="OUT_OF_HOME_SPOUSE_L1_STATE")
    private String outOfHomeSpouseL1State ;
    private String outOfHomeSpouseZip ;
    private Character voterRegistrationSw ;
    private String infoExchangeConsentInd ;
    private String eSignInd ;
    private String eSignFstNam ;
    private Character eSignMidInit ;
    private String eSignLastNam ;
    private String unableSignInd ;
    private String eSignAuthInd ;
    private String eSignAuthFstNam ;
    private Character eSignAuthMidInit ;
    private String eSignAuthLastNam ;
    private String spclArrgmentByGovAgncyInd ;
    private Character tanfRqstSw ;
    private Character ccRqstSw ;
    private String spouseLivingArrgCd ;
    private String spouseLivingArrgOth ;
    private String liheapRqstInd ;
    private String wicRqstInd ;
    private String hshlPastAddrInd ;
    private String pastCountyCd ;
    private String pastStateCd ;
    private String prefContMethodCd ;
    private String prefContTimeCd ;
    private String prefDeafContMethodCd ;
    private String applMyCerti ;
    private String applSomCerti ;
    private String applyMedicaid ;
    private String renwOfCovg ;
    private String helpIndividualCd ;
    private String arAgencyName ;
    private String arConName ;
    @Column(name="AR_LINE1_ADR")
    private String arLine1Adr ;
    @Column(name="AR_LINE2_ADR")
    private String arLine2Adr ;
    private String arCityAdr ;
    private String arStateAdr ;
    private String arZipAdr ;
    private String arPhoneNum ;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp homeAddrChgBeginDt ;
    private Long peopleInHomeCt ;
    private String isMapped ;
    private String medicaidAbdInd ;
    private String newBornInd ;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp chgDt ;
    private String qTrack ;
    private String wicClncCnty ;
    private String wicClncCd ;
    private String srSnapInd ;
    private String refugeeInd ;
    private String revmaxInd ;
    private String pregInd ;
    private String nhInd ;
    private String waiverInd ;
    private String wicDsclsr ;
    private String ccProviderInd ;
    private String ccActivityInd ;
    private String catCode ;
}