package com.deloitte.nextgen.ssp.entities.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name = T1003_APP_PRIR_SRV.TABLE_NAME)
@EntityType(type= TypeEnum.ZERO)
@NoArgsConstructor
public class T1003_APP_PRIR_SRV extends TypeZeroBaseEntity<String> {
    @Transient
    public static final String TABLE_NAME = "T1003_APP_PRIR_SRV";

    @Id
    private String appNum;
    private Character incmStopSw;
    private Double lqdAsetAmt;
    private Character migFarmWrkrSw;
    private Double moGrIncmAmt;
    private Double moRentMrtgAmt;
    private Double moUtilAmt;
    private Character newIncmSw;
    private java.sql.Timestamp prirSrvcDterDt;
    private Character rcvFsCurMoSw;
    private Character wmenChldShltSw;
    private Long lqdAsetAmtInd;
    private Long moGrIncmAmtInd;
    private Long moUtilAmtInd;
    private String nonHeatEleExp;
    private String trashRemovalExp;
    private String cookFuelExp;
    private String watsewExp;
    private String teleExp;
    private String heatUtilityExp;
    private Character incmSameGrwrSw;
    private java.sql.Timestamp dtIncmSameGrwr;
    private Double amtSameGrwr;
    private Character incmCrntMnthSw;
    private java.sql.Timestamp dtIncmCrntMnth;
    private Double amtCrntMnth;
    private Character trvlAdvcSw;
    private java.sql.Timestamp dtTrvlAdvc;
    private Double amtTrvlAdvc;
    private Character lstOnlySrcIncmSw;
    private java.sql.Timestamp dtFinalPayChk;
    private Double amtFnalPayChk;
    private Character countGrsAssetsIncmResp;
    private String heatIndicator;
    private String coolingIndicator;
    private String electricityAcIndicator;
    private String waterIndicator;
    private String sewerIndicator;
    private String garbageIndicator;
    private String telephoneIndicator;

}
