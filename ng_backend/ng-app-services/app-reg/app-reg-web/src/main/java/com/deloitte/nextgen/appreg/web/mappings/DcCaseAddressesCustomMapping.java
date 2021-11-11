package com.deloitte.nextgen.appreg.web.mappings;

import com.deloitte.nextgen.appreg.client.entities.ArAppAddrDto;
import com.deloitte.nextgen.appreg.web.entities.ArAppAddr;
import com.deloitte.nextgen.appreg.web.entities.DcCaseAddresses;
import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DcCaseAddressesCustomMapping extends EntityMapper<ArAppAddrDto, DcCaseAddresses> {
}
