package com.deloitte.nextgen.appreg.web.mappings;

import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.appreg.client.response.ArAppProgramResponse;
import com.deloitte.nextgen.appreg.web.entities.ArAppProgram;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ArAppProgramMapping extends EntityMapper<ArAppProgramResponse, ArAppProgram> {
}
