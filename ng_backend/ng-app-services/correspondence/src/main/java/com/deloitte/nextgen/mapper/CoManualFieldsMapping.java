package com.deloitte.nextgen.mapper;

import com.deloitte.nextgen.dto.entities.CoManualFieldsDTO;
import com.deloitte.nextgen.entities.CoManualFields;
import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CoManualFieldsMapping extends EntityMapper<CoManualFieldsDTO, CoManualFields> {
}
