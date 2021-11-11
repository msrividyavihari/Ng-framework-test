package com.deloitte.nextgen.dc.common.mapstruct.mappers;

import com.deloitte.nextgen.dc.common.dto.AddressDto;
import com.deloitte.nextgen.dc.entities.DcCaseAddresses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class AddressDtoMapper {

    @Mapping(target="phoneNum1", source="caseAddress.phoneNum")
    @Mapping(target="phoneNum2", source="caseAddress.phoneNum")
    //TODO: Fix the phone number mappings...
    public abstract AddressDto map(DcCaseAddresses caseAddress);
}
