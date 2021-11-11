package com.deloitte.nextgen.appreg.web.mappings;

import com.deloitte.nextgen.appreg.client.entities.DcAliasDto;
import com.deloitte.nextgen.appreg.web.entities.DcAlias;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DcAliasMapping {
    DcAlias map(DcAliasDto dcAliasDto);
    DcAliasDto map(DcAlias dcAlias);

}
