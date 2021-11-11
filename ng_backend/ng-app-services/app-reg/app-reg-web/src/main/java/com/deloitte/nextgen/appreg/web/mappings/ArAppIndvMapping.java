package com.deloitte.nextgen.appreg.web.mappings;

import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.appreg.client.request.ArAppIndvReqAndResp;
import com.deloitte.nextgen.appreg.web.entities.ArAppIndv;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ArAppIndvMapping extends EntityMapper<ArAppIndvReqAndResp, ArAppIndv> {
}
