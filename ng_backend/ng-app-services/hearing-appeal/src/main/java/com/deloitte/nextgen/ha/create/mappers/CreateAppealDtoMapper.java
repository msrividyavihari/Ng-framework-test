package com.deloitte.nextgen.ha.create.mappers;


import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.common.dto.AddressDto;
import com.deloitte.nextgen.ha.appeals.dto.ContactInfoDto;
import com.deloitte.nextgen.ha.create.dto.CreateAppealResonseDto;
import com.deloitte.nextgen.ha.entity.AmRequestDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigInteger;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CreateAppealDtoMapper {

    @Mapping(source = "authRepDto", target = "authRepInfo")
    @Mapping(source = "appealNum", target = "primaryAppealNum")
    public CreateAppealResonseDto map(BigInteger appealNum, AuthRepDto authRepDto);

}
