package com.deloitte.nextgen.ha.individuals.dto;

import com.deloitte.nextgen.dc.common.dto.IndividualNameDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Value
public class IndividualAssociatedCasesResponseDto {
    @NonNull
    private Long individualId;
    @NonNull
    private IndividualNameDto individualName;
    @Builder.Default
    private List<IndividualAssociatedCasesDto> associatedCases = new ArrayList<>();
}
