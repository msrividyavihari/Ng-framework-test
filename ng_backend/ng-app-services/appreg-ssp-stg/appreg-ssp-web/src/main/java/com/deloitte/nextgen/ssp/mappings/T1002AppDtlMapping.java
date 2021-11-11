package com.deloitte.nextgen.ssp.mappings;

import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.ssp.entities.entities.T1002_App_Dtl;
import com.deloitte.nextgen.ssp.entities.T1002AppDtlDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface T1002AppDtlMapping extends EntityMapper<T1002AppDtlDto, T1002_App_Dtl> {
}
