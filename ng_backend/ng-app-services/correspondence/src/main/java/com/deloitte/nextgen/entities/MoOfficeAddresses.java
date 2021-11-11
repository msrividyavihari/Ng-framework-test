package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.MoOfficeAddressesId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.MoOfficeAddressesRepository;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="MO_OFFICE_ADDRESSES")
@EntityType(type= TypeEnum.ONE, customRepository = MoOfficeAddressesRepository.class)
@Data
@NoArgsConstructor
@IdClass(MoOfficeAddressesId.class)
public class MoOfficeAddresses extends TypeOneBaseEntity<Long> implements Serializable {
    private String addrAptNum;
    private String addrCity;
    private String addrCountyCd;
    private String addrDwellingTypeCd;
    private String addrLine;
    private String addrStDirCd;
    private String addrStNm;
    private String addrStNum;
    private String addrStNumFrac;
    private String addrStTypeCd;
    private String addrStateCd;
    private String addrZip4;
    private String addrZip5;
    @Id
    private String addressTypeCd;
    private String drivingDirections;
    @Id
    private Long officeNum;
    private String addrCareOfLine;
    private String addrLine1;
    private String addrLine2;
    private String addrLine3;
    private String addrCountryCd;
}
