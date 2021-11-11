package com.deloitte.nextgen.ha.individuals.dto;

import com.deloitte.nextgen.dc.common.dto.ProgramDto;
import com.deloitte.nextgen.ha.search.dto.AppealDetailDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Value
public class IndividualAssociatedCasesDto {
    @NonNull
    private Long caseNum;
    @Builder.Default
    private List<ProgramDto> programs = new ArrayList<>();
    @Builder.Default
    private List<AppealDetailDto> appeals = new ArrayList<>();
}
