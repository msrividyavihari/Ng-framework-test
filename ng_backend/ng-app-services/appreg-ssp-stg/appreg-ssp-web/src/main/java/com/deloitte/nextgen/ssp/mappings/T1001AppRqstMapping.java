package com.deloitte.nextgen.ssp.mappings;

import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.ssp.entities.entities.T1001_AppRqst;
import com.deloitte.nextgen.ssp.entities.T1001AppRqstDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface T1001AppRqstMapping extends EntityMapper<T1001AppRqstDto, T1001_AppRqst> {
}
