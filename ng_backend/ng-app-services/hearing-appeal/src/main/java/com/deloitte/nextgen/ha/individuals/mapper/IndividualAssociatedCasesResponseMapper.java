package com.deloitte.nextgen.ha.individuals.mapper;

import com.deloitte.nextgen.ha.individuals.dto.IndividualAssociatedCasesDto;
import com.deloitte.nextgen.ha.individuals.dto.IndividualAssociatedCasesResponseDto;
import com.deloitte.nextgen.ha.search.dto.AppealDetailDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IndividualAssociatedCasesResponseMapper {


    IndividualAssociatedCasesResponseDto map(com.deloitte.nextgen.dc.individual.dto.IndividualAssociatedCasesResponseDto dto, @Context Map<Long, List<AppealDetailDto>> appealsMap);

    @Mapping(target = "appeals", expression = "java(appealsMap != null ? appealsMap.get(dto.getCaseNum()) : null)")
    IndividualAssociatedCasesDto map(com.deloitte.nextgen.dc.individual.dto.IndividualAssociatedCasesDto dto, @Context Map<Long, List<AppealDetailDto>> appealsMap);

    List<IndividualAssociatedCasesDto> map(List<com.deloitte.nextgen.dc.individual.dto.IndividualAssociatedCasesDto> dtos, @Context Map<Long, List<AppealDetailDto>> appealsMap);
}
