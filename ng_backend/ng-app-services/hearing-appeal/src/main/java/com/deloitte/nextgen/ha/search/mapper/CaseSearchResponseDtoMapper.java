package com.deloitte.nextgen.ha.search.mapper;

import com.deloitte.nextgen.ha.search.dto.AppealDetailDto;
import com.deloitte.nextgen.ha.search.dto.CaseSearchResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CaseSearchResponseDtoMapper {

    CaseSearchResponseDto map(com.deloitte.nextgen.dc.cases.dto.CaseSearchResponseDto caseDetailDto, List<AppealDetailDto> appeals);
}
