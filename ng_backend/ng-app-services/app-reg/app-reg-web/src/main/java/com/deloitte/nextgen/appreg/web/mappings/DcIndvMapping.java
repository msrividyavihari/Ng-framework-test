package com.deloitte.nextgen.appreg.web.mappings;

import com.deloitte.nextgen.appreg.client.request.DcIndvFileClearReqAndResp;
import com.deloitte.nextgen.appreg.web.entities.DcIndv;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DcIndvMapping {
    DcIndv map(DcIndvFileClearReqAndResp dcIndvFileClearReqAndResp);
    DcIndvFileClearReqAndResp map(DcIndv dcIndv);

}
