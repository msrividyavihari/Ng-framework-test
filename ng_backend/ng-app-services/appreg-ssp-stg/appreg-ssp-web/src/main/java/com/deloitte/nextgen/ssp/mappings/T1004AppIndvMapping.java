package com.deloitte.nextgen.ssp.mappings;

import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.ssp.entities.entities.T1004_App_Indv;
import com.deloitte.nextgen.ssp.entities.T1004AppIndvDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface T1004AppIndvMapping extends EntityMapper<T1004AppIndvDto, T1004_App_Indv> {
}
