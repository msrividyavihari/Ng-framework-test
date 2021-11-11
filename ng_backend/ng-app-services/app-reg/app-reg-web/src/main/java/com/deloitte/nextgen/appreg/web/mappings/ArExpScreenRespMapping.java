package com.deloitte.nextgen.appreg.web.mappings;

import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.appreg.client.entities.ArExpScreenRespDto;
import com.deloitte.nextgen.appreg.web.entities.ArExpScreenResp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ArExpScreenRespMapping extends EntityMapper<ArExpScreenRespDto, ArExpScreenResp> {
}
