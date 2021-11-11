package com.deloitte.nextgen.dc.individual.dto;

import com.deloitte.nextgen.dc.common.dto.ProgramDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class IndividualAssociatedCasesDto {
    @NonNull
    private Long caseNum;
    private List<ProgramDto> programs = new ArrayList<>();
}
