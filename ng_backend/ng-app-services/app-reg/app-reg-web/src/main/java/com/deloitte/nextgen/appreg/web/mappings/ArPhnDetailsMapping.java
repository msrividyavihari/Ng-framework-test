package com.deloitte.nextgen.appreg.web.mappings;

import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.appreg.client.entities.ArPhnDetailsDto;
import com.deloitte.nextgen.appreg.web.entities.ArPhnDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArPhnDetailsMapping extends EntityMapper<ArPhnDetailsDto, ArPhnDetails> {

    void mergeEntity(ArPhnDetailsDto var1, @MappingTarget ArPhnDetails var2);
}

