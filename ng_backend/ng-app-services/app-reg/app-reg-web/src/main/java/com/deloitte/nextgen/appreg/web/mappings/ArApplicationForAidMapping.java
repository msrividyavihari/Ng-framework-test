package com.deloitte.nextgen.appreg.web.mappings;


import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.appreg.client.request.ArApplicationForAidReqAndResp;
import com.deloitte.nextgen.appreg.web.entities.ArApplicationForAid;
import org.mapstruct.*;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ArApplicationForAidMapping extends EntityMapper<ArApplicationForAidReqAndResp,ArApplicationForAid > {

}
