package com.deloitte.nextgen.ssp.mappings;

import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.ssp.entities.entities.T1065_App_Auth_Rep;
import com.deloitte.nextgen.ssp.entities.T1065AppAuthRepDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface T1065AppAuthRepMapping extends EntityMapper<T1065AppAuthRepDto, T1065_App_Auth_Rep> {
}
