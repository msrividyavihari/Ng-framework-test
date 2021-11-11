package com.deloitte.nextgen.ha.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseSearchResponseDto {
    @JsonProperty("case")
    private com.deloitte.nextgen.dc.cases.dto.CaseSearchResponseDto caseDetailDto;
    private List<AppealDetailDto> appeals;
}