package com.deloitte.nextgen.appreg.web.mappings;

import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.appreg.client.entities.ArEmailDetailsDto;
import com.deloitte.nextgen.appreg.web.entities.ArEmailDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArEmailDetailsMapping extends EntityMapper<ArEmailDetailsDto, ArEmailDetails> {

    void mergeEntity(ArEmailDetailsDto var1, @MappingTarget ArEmailDetails var2);
}

