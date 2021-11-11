package com.deloitte.nextgen.ssp.mappings;

import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.ssp.entities.entities.T1053_App_Program;
import com.deloitte.nextgen.ssp.entities.T1053AppProgramDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface T1053AppProgramMapping extends EntityMapper<T1053AppProgramDto, T1053_App_Program> {
}
