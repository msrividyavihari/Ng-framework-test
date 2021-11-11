package com.deloitte.nextgen.dc.individual.dto;

import com.deloitte.nextgen.dc.common.dto.IndividualNameDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class IndividualAssociatedCasesResponseDto {

    @NonNull
    private Long individualId;
    @NonNull
    private IndividualNameDto individualName;
    private List<IndividualAssociatedCasesDto> associatedCases =  new ArrayList<>();
}
