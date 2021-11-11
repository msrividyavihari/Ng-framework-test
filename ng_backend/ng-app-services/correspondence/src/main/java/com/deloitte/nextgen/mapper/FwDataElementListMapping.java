package com.deloitte.nextgen.mapper;

import com.deloitte.nextgen.dto.entities.FwDataElementListDTO;
import com.deloitte.nextgen.entities.FwDataElementList;
import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN)
public interface FwDataElementListMapping extends EntityMapper<FwDataElementListDTO, FwDataElementList> {
}
