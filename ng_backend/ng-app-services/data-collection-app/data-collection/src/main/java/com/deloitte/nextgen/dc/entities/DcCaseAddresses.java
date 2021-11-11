package com.deloitte.nextgen.dc.entities;


import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.two.TypeTwoBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.dc.entities.composite.DcCaseAddressesCargoId;
import com.deloitte.nextgen.dc.repository.DcCaseAddressesRepository;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data // getter and setters
@Entity
@Table(name = "DC_CASE_ADDRESSES")
@EntityType(type= TypeEnum.TWO, customRepository = DcCaseAddressesRepository.class)
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@IdClass(DcCaseAddressesCargoId.class)
public class DcCaseAddresses extends TypeTwoBaseEntity<String> {

    @Id
    private Long caseNum;
    @Id
    private Long caseAddrSeqNum;
    private String addrStNum;
    private String addrStNumFrac;
    private String addrStNm;
    private String addrAptNum;
    private String addrLine;
    private String addrCity;
    private String addrZip5;
    private String addrZip4;
    private java.sql.Timestamp reportDt;
    private java.sql.Timestamp discoveryDt;
    private Character timelySw;
    private String addrTypeCd;
    private String addrDwellingTypeCd;
    private String addrStDirCd;
    private String addrStTypeCd;
    private String addrCountyCd;
    private String addrStateCd;
    private java.sql.Timestamp effBeginDt;
    private java.sql.Timestamp effEndDt;
    private String addrVrfCd;
    private java.sql.Timestamp verfReceivedDt;
    private String addrPostDirCd;
    private String addrCareOfLine;
    private String addrFormat;
    private String addrLine1;
    private String addrLine2;
    private String addrStateMilitaryCd;
    private String addrMilitaryPoCd;
    private String addrCountryCd;
    private String addrSourceType;
    private Character addressResSw;
    private String addrDrivingInst;
    private Character addrLivingRes;
    private String phoneNum;
    private String rowid;
    private String fundingCountyCd;

}



