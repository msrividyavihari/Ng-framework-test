package com.deloitte.nextgen.appreg.web.entities;

import com.deloitte.nextgen.appreg.web.entities.generated.DcCaseAddressesPK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.entity.type.two.TypeTwoBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = DcCaseAddresses.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
@IdClass(DcCaseAddressesPK.class)
public class DcCaseAddresses extends TypeOneBaseEntity<DcCaseAddressesPK> {

    @Transient
    public static final String TABLE_NAME = "DC_CASE_ADDRESSES";

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
    @Id
    private java.sql.Timestamp effBeginDt;
    private java.sql.Timestamp effEndDt;
    private String addrVrfCd;
    private String addrPostDirCd;
    private String addrCareOfLine;
    private String addrFormat;
    private String addrLine1;
    private String addrLine2;

}
