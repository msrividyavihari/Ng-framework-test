package com.deloitte.nextgen.appreg.web.entities;


import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import com.deloitte.nextgen.appreg.client.entities.converters.ApplicationStatusConverter;
import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.enums.ApplicationStatus;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import lombok.*;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = ArApplicationForAid.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
public class ArApplicationForAid extends TypeOneBaseEntity<String>{

    @Transient
    public static final String TABLE_NAME = "AR_APPLICATION_FOR_AID";

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AR_APPLICATION_FOR_AID_1SQ")
//    @GenericGenerator(name = "AR_APPLICATION_FOR_AID_1SQ",
//            strategy = NgSequenceGenerator.CLASS_PATH,
//            parameters = {
//                    @Parameter(name = NgSequenceGenerator.INCREMENT_PARAM, value = "1"),
//                    @Parameter(name = NgSequenceGenerator.SEQUENCE_PREFIX, value = "T"),
//                    @Parameter(name = NgSequenceGenerator.NUMBER_FORMAT, value = "%08d")
//            }
//    )
    private String appNum;
    private java.sql.Timestamp appRecvdDt;
    @Convert(converter = ApplicationStatusConverter.class)
    private ApplicationStatus applicationStatusCd;
    private Long officeNum;
    private String saverrAppNum;
    private Long caseNum;
    private Long empId;
    private Character expeditedSw;
    private Character scheduledSw;
    @Convert(converter = ActiveConverter.class)
    private Active authRepSw;
    private java.sql.Timestamp expeditedDetDt;
    private Long workPhNumExt;
    private String appTypeCd;
    private java.sql.Timestamp statusUpdateDt;
    private java.sql.Timestamp applicationStatusDt;
    @Convert(converter = ActiveConverter.class)
    private Active appForaidSw;
    private String agencyName;
    private Long companyId;
    private String appModeCd;
    private Character programAddInd;
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
    private String ebtCardSw;
    private Character caseOrTask;
    private String caseType;
    private String interpreterSw;
    private String communicationAsst;
    private String assignSelfSw;
    @Convert(converter = ActiveConverter.class)
    private Active abdCheckSw;
    private java.sql.Timestamp appSbmtTms;
    private java.sql.Timestamp appTms;
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

//    @Valid
//    @Setter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "arApplicationForAid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<ArAppAddr> arAppAddr;
//
//    @Valid
//    @Setter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "arApplicationForAid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<ArAppIndv> arAppIndv;
//
//    @Valid
//    @Setter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "arApplicationForAid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<ArAppProgram> arAppProgram;
//
//    @Valid
//    @Setter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "arApplicationForAid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<ArAppProgramIndv> arAppProgramIndv;
//
//    @Valid
//    @Setter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "arApplicationForAid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<ArEmailDetails> arEmailDetails;
//
//    @Valid
//    @Setter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "arApplicationForAid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<ArExpScreenResp> arExpScreenResp;
//
//    @Valid
//    @Setter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "arApplicationForAid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<ArPhnDetails> arPhnDetails;
}
