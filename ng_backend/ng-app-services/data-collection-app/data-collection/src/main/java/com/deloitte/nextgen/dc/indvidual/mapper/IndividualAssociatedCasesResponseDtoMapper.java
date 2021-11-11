package com.deloitte.nextgen.dc.indvidual.mapper;

import com.deloitte.nextgen.dc.common.mapstruct.mappers.IndividualNameDtoMapper;
import com.deloitte.nextgen.dc.common.mapstruct.mappers.ProgramDtoMapper;
import com.deloitte.nextgen.dc.entities.DcCaseProgram;
import com.deloitte.nextgen.dc.entities.DcIndv;
import com.deloitte.nextgen.dc.individual.dto.IndividualAssociatedCasesDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualAssociatedCasesResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {IndividualNameDtoMapper.class})
@Slf4j
public abstract class IndividualAssociatedCasesResponseDtoMapper {

    @Autowired
    private ProgramDtoMapper programDtoMapper;

    @Mapping(target = "individualName", source = "individual")
    @Mapping(target = "individualId", source = "individual.indvId")
    @Mapping(ignore = true, target = "associatedCases")
    public abstract IndividualAssociatedCasesResponseDto map(DcIndv individual, List<DcCaseProgram> casePrograms);

    @AfterMapping
    public void afterMapping(@MappingTarget IndividualAssociatedCasesResponseDto responseDto, List<DcCaseProgram> casePrograms) {
        if (casePrograms == null) return;
        Map<Long, List<DcCaseProgram>> groupedProgMap = casePrograms
                .stream()
                .collect(Collectors.groupingBy(DcCaseProgram::getCaseNum));
        for (Map.Entry<Long, List<DcCaseProgram>> groupedProgEntry : groupedProgMap.entrySet()) {
            IndividualAssociatedCasesDto dto = new IndividualAssociatedCasesDto();
            dto.setCaseNum(groupedProgEntry.getKey());
            dto.setPrograms(programDtoMapper.mapPrograms(groupedProgEntry.getValue()));
            responseDto.getAssociatedCases().add(dto);
        }
    }
}
