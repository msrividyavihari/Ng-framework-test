package com.deloitte.nextgen.ssp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class T1065AppAuthRepDto {
    private String appNum;
    private long seqNum;
    @JsonProperty("l1Adr")
    private String L1_ADR;
    @JsonProperty("l2Adr")
    private String L2_ADR;
    private String cityAdr;
    private String emailAdr;
    private String phnExtnNum;
    private String phnNum;
    private String staAdr;
    private String zipAdr;
    private String authRepCd;
    private Character careGiverSw;
    private String filingRepFirstName;
    private String filingRepLastName;
    private String filingRepMidName;
    private long applEsignInd;
    private String applFirstName;
    private String applLastName;
    private String applMidName;
    private long authRepEsignInd;
    private String authRepFirstName;
    private String authRepLastName;
    private String authRepMidName;
    private String maFsAuthRepName;
    private long witnEsignInd;
    private String witnFirstName;
    private String witnLastName;
    private String witnMidName;
    private Character brgCrdRcvInd;
    private String snapAuthRepInd;
    private String tanfAuthRepInd;
    private String authRepMedicalAssistInd;
    private String authRepInfoShareInd;
    private String authRepDutyAfbInd;
    private String authRepDutyBenefitsInd;
    private String authRepDutyReceiveInd;
    private String authRepDutyRequestInd;
    private String authRepDutyOtherInd;
    private String authRepIdNum;
    private String authRepNam;
    private String authRepOrgNam;
    private String authRepSuffixNam;
    private String relToAuthRepTypCd;
    private String isMapped;
    private Character hasAuthRepSw;
}
