package com.deloitte.nextgen.dc.cases.dto;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.common.dto.AddressDto;
import com.deloitte.nextgen.dc.common.dto.ProgramDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class CaseSearchResponseDto {
    @NonNull
    private Long caseNum;
    private List<ProgramDto> programs;
    @NonNull
    private String caseStatusCd;
    @NonNull
    private IndividualDto headOfHouseHold;
    private List<IndividualDto> members;
    @NonNull
    private LocalDate lastUpdatedDt;
    @NonNull
    private AddressDto address;
    @NonNull
    private AuthRepDto authRep;
}
