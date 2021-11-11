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
@Table(name = ArAppAddr.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
public class ArAppAddr extends TypeOneBaseEntity<Long> {

    @Transient
    public static final String TABLE_NAME = "AR_APP_ADDR";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "AR_APP_ADDR_1SQ")
    private Long appAddrSeqNum;
    private String appNum;
    private String addrTypeCd;
    private String addrLine;
    private String addrStNum;
    private String addrStNumFrac;
    private String addrAptNum;
    private String addrStNm;
    private String addrCity;
    private String addrZip5;
    private String addrZip4;
    private String addrDwellingTypeCd;
    private String addrCountyCd;
    private String addrStDirCd;
    private String addrStTypeCd;
    private String addrStateCd;
    private String addrPostDirCd;
    private String addrCareOfLine;
    private Long addrHashNum;
    private String addrFormatCd;
    private String addrLine1;
    private String addrLine3;
    private String apoFpoAddr;
    private String addrMilitary;
    private String addrCntry;
    @Convert(converter = ActiveConverter.class)
    private Active resAddrSw;
    private String drivInst;
    private Character livingResSw;
    private Character addrProgramReqCd;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="app_num")
//    private ArApplicationForAid arApplicationForAid;
}