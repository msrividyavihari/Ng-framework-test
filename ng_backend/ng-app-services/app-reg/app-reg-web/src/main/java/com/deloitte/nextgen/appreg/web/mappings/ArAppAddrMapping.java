package com.deloitte.nextgen.appreg.web.mappings;

import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.appreg.client.entities.ArAppAddrDto;
import com.deloitte.nextgen.appreg.web.entities.ArAppAddr;
import org.mapstruct.*;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArAppAddrMapping extends EntityMapper<ArAppAddrDto, ArAppAddr> {


    void mergeEntity(ArAppAddrDto var1, @MappingTarget ArAppAddr var2);

}

