package com.deloitte.nextgen.ssp.responses;

import com.deloitte.nextgen.ssp.entities.converters.ApplicationStatusConverter;
import com.deloitte.nextgen.ssp.enums.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class ArApplicationForAidResponse {
    @NotNull
    private String appNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp appRecvdDt;
    @Convert(converter = ApplicationStatusConverter.class)
    private ApplicationStatus applicationStatusCd;
    private Long officeNum;
    @NotNull
    private Long caseNum;
    private Long empId;
    private Character expeditedSw;
    private Character scheduledSw;
    private Character authRepSw;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp expeditedDetDt;
    private Long workPhNumExt;
    private String appTypeCd;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp statusUpdateDt;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp applicationStatusDt;
    private Character appForaidSw;
    private String agencyName;
    private Long companyId;
    private String appModeCd;
    private Character programAddInd;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp dateTimeRegistered;
    private Character appSignedSw;
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
    private String ebtCardSw;
    private Character caseOrTask;
    private String caseType;
    private String interpreterSw;
    private String communicationAsst;
    private String assignSelfSw;
    private String abdCheckSw;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp appSbmtTms;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp appTms;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp appRecvdTms;
    private String qTrackSw;
    private String workFlowStatusCd;
    private String revMaxSw;
    private String refugeeSw;
    private String srSnapSw;
    private String pregnancySw;
    private String wicDisclosureSw;
    private String nursingHomeSw;
    private String waiverSw;
    private String rowid;
}
