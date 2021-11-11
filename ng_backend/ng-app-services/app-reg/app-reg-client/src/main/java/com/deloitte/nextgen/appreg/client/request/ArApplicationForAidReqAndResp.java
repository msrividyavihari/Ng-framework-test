package com.deloitte.nextgen.appreg.client.request;

import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import com.deloitte.nextgen.appreg.client.entities.converters.ApplicationStatusConverter;
import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.enums.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Convert;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArApplicationForAidReqAndResp {
    private String appNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp appRecvdDt;
    @Convert(converter = ApplicationStatusConverter.class)
    private ApplicationStatus applicationStatusCd ;
    private Long officeNum;
    private Long caseNum;
    private Long empId;
    private Character expeditedSw;
    private Character scheduledSw;
    @Convert(converter = ActiveConverter.class)
    private Active authRepSw;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private java.sql.Timestamp expeditedDetDt;
    private Long workPhNumExt;
    private String appTypeCd;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private java.sql.Timestamp statusUpdateDt;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private java.sql.Timestamp applicationStatusDt;
    @Convert(converter = ActiveConverter.class)
    private Active appForaidSw;
    private String agencyName;
    private Long companyId;
    private String appModeCd;
    private Character programAddInd;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private java.sql.Timestamp dateTimeRegistered;
    @Convert(converter = ActiveConverter.class)
    private Active appSignedSw;
    private String teleConvRefNum;
    private String ucmTranNum;
    private String primLang;
    private String hearImpairSw;
    private String visualImpairSw;
    private String weekdayContMethSw;
    private String hearImpairContMethSw;
    private String weekdayContTime;
    private String otherLang;
    private String interpreterNeedSw;
    private String commuAssisNeedSw;
    private String authrepFirstName;
    private String authrepLastName;
    private String authrepMidName;
    private String authrepSufxName;
    private String careGiverSw;
    private String relCd;
    private String disabReqAccomSw;
    private String typeAccommodationCd;
    private String otherTypeAccommodation;
    @Convert(converter = ActiveConverter.class)
    private Active ebtCardSw;
    private Character caseOrTask;
    private String caseType;
    private String interpreterSw;
    private String communicationAsst;
    private String assignSelfSw;
    @Convert(converter = ActiveConverter.class)
    private Active abdCheckSw;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private java.sql.Timestamp appSbmtTms;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private java.sql.Timestamp appTms;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private java.sql.Timestamp appRecvdTms;
    @Convert(converter = ActiveConverter.class)
    private Active qTrackSw;
    private String workFlowStatusCd;
    @Convert(converter = ActiveConverter.class)
    private Active revMaxSw;
    @Convert(converter = ActiveConverter.class)
    private Active refugeeSw;
    @Convert(converter = ActiveConverter.class)
    private Active srSnapSw;
    @Convert(converter = ActiveConverter.class)
    private Active pregnancySw;
    private String wicDisclosureSw;
    @Convert(converter = ActiveConverter.class)
    private Active nursingHomeSw;
    @Convert(converter = ActiveConverter.class)
    private Active waiverSw;
    private String rowid;
}
