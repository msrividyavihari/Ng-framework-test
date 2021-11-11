package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.DcCaseAddressesId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.DcCaseAddressesRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="DC_CASE_ADDRESSES")
@EntityType(type= TypeEnum.ONE, customRepository = DcCaseAddressesRepository.class)
@Data
@NoArgsConstructor
@IdClass(DcCaseAddressesId.class)
public class DcCaseAddresses extends TypeOneBaseEntity<Long> implements Serializable {

    @Id
    @Column(name = "CASE_NUM")
    private Long caseNum;
    @Column(name = "CASE_ADDR_SEQ_NUM")
    private Long caseAddrSeqNum;
    @Column(name = "ADDR_ST_NUM")
    private String addrStNum;
    @Column(name = "ADDR_ST_NUM_FRAC")
    private String addrStNumFrac;
    @Column(name = "ADDR_ST_NM")
    private String addrStNm;
    @Column(name = "ADDR_APT_NUM")
    private String addrAptNum;
    @Column(name = "ADDR_LINE")
    private String addrLine;
    @Column(name = "ADDR_CITY")
    private String addrCity;
    @Column(name = "ADDR_ZIP5")
    private String addrZip5;
    @Column(name = "ADDR_ZIP4")
    private String addrZip4;
    @Column(name = "REPORT_DT")
    private java.sql.Timestamp reportDt;
    @Column(name = "DISCOVERY_DT")
    private java.sql.Timestamp discoveryDt;
    @Column(name = "TIMELY_SW")
    private Character timelySw;
    @Column(name = "ADDR_TYPE_CD")
    private String addrTypeCd;
    @Column(name = "ADDR_DWELLING_TYPE_CD")
    private String addrDwellingTypeCd;
    @Column(name = "ADDR_ST_DIR_CD")
    private String addrStDirCd;
    @Column(name = "ADDR_ST_TYPE_CD")
    private String addrStTypeCd;
    @Column(name = "ADDR_COUNTY_CD")
    private String addrCountyCd;
    @Column(name = "ADDR_STATE_CD")
    private String addrStateCd;
    @Column(name = "ADDR_VRF_CD")
    private String addrVrfCd;
    @Column(name = "VERF_RECEIVED_DT")
    private java.sql.Timestamp verfReceivedDt;
    @Column(name = "ADDR_POST_DIR_CD")
    private String addrPostDirCd;
    @Column(name = "ADDR_CARE_OF_LINE")
    private String addrCareOfLine;
    @Column(name = "ADDR_FORMAT")
    private String addrFormat;
    @Column(name = "ADDR_LINE1")
    private String addrLine1;
    @Column(name = "ADDR_LINE2")
    private String addrLine2;
    @Column(name = "ADDR_STATE_MILITARY_CD")
    private String addrStateMilitaryCd;
    @Column(name = "ADDR_MILITARY_PO_CD")
    private String addrMilitaryPoCd;
    @Column(name = "ADDR_COUNTRY_CD")
    private String addrCountryCd;
    @Column(name = "ADDR_SOURCE_TYPE")
    private String addrSourceType;
    @Column(name = "ADDRESS_RES_SW")
    private Character addressResSw;
    @Column(name = "ADDR_DRIVING_INST")
    private String addrDrivingInst;
    @Column(name = "ADDR_LIVING_RES")
    private Character addrLivingRes;
    @Column(name = "PHONE_NUM")
    private String phoneNum;
    @Column(name = "FUNDING_COUNTY_CD")
    private String fundCountyCd;
}
