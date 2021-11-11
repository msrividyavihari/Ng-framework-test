package com.deloitte.nextgen.mapper;

import com.deloitte.nextgen.dto.entities.CoManualDataDTO;
import com.deloitte.nextgen.entities.CoManualData;
import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CoManualDataMapper extends EntityMapper<CoManualDataDTO, CoManualData> {
}
