package com.deloitte.nextgen.mapper;

import com.deloitte.nextgen.dto.entities.CoRequestHistoryDTO;
import com.deloitte.nextgen.entities.CoRequestHistory;
import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CoRequestHistoryMapping extends EntityMapper<CoRequestHistoryDTO, CoRequestHistory> {
}
