package com.deloitte.nextgen.dc.common.mapstruct.mappers;

import com.deloitte.nextgen.dc.common.dto.ProgramDto;
import com.deloitte.nextgen.dc.entities.DcCaseProgram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProgramDtoMapper {

    @Mapping(source="casePrograms.progCd", target = "programCd")
    @Mapping(source="casePrograms.progStatusCd", target = "programStatusCd")
    public ProgramDto map(DcCaseProgram casePrograms);

    public List<ProgramDto> mapPrograms(List<DcCaseProgram> casePrograms);
}
