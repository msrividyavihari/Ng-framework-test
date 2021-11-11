package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.ArApplicationForAidRepository;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "AR_APPLICATION_FOR_AID")
@EntityType(type= TypeEnum.ONE, customRepository = ArApplicationForAidRepository.class)
@NoArgsConstructor
public class ArApplicationForAid extends TypeOneBaseEntity<String> implements Serializable {

    @Id
    private String appNum;
    private Timestamp appRecvdDt;
    private String applicationStatusCd;
    private Long officeNum;
    private String saverrAppNum;
    private Long caseNum;
    private Long empId;
    private Character expeditedSw;
    private Character scheduledSw;
    private Character authRepSw;
    private Timestamp expeditedDetDt;
    private Long workPhNumExt;
    private String appTypeCd;
    private Timestamp statusUpdateDt;
    private Timestamp applicationStatusDt;
    private Character appForaidSw;
    private String agencyName;
    private Long companyId;
    private String appModeCd;
    private Character programAddInd;
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
    private Timestamp appSbmtTms;
    private Timestamp appTms;
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
}
