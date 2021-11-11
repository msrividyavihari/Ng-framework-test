package com.deloitte.nextgen.ha.create.mappers;

import com.deloitte.nextgen.dc.common.dto.AddressDto;
import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.ha.appeals.dto.ContactInfoDto;
import com.deloitte.nextgen.ha.create.dto.AddressInfoDto;
import com.deloitte.nextgen.ha.entity.AmAddress;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import javax.persistence.Column;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN)
public interface   AmAddressMapping {

    @Mapping(target="streetName", source="addressDto.addrLine1")
    @Mapping(target="addressLine2", source="addressDto.addrLine2")
    @Mapping(target="cityName", source="addressDto.addrCity")
    @Mapping(target="stateCd", source="addressDto.addrStateCd")
    @Mapping(target="zipCd", source="addressDto.addrZip5")
   // @Mapping(target="regionCd", source="addressDto.addrZip4")
    @Mapping(target="countyCd", source="addressDto.addrCountyCd")
    public abstract AmAddress  mapDtoToEntity(AddressDto addressDto);

    @Mapping(target="addrLine1",source="amAddress.streetName" )
    @Mapping( target="addrLine2",source="amAddress.addressLine2")
    @Mapping(target="addrCity",source="amAddress.cityName" )
    @Mapping(target="addrStateCd",source="amAddress.stateCd" )
    @Mapping(target="addrZip5",source="amAddress.zipCd" )
    //@Mapping(target="addrZip4",source="amAddress.regionCd")
    @Mapping(target="addrCountyCd",source="amAddress.countyCd")
    public abstract AddressDto entityToDto(AmAddress amAddress);


    @Mapping(target="amAddress.streetName", source="addressDto.addrLine1")
    @Mapping(target="amAddress.addressLine2", source="addressDto.addrLine2")
    @Mapping(target="amAddress.cityName", source="addressDto.addrCity")
    @Mapping(target="amAddress.stateCd", source="addressDto.addrStateCd")
    @Mapping(target="amAddress.zipCd", source="addressDto.addrZip5")
    // @Mapping(target="regionCd", source="addressDto.addrZip4")
    @Mapping(target="amAddress.countyCd", source="addressDto.addrCountyCd")
    void mergeEntity(AddressDto addressDto,@MappingTarget AmAddress amAddress);
}

