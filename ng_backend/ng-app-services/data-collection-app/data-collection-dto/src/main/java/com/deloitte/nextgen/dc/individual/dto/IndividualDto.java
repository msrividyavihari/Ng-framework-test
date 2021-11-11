package com.deloitte.nextgen.dc.individual.dto;

import com.deloitte.nextgen.dc.common.dto.IndividualNameDto;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class IndividualDto {

    @NonNull
    private Long individualId;
    @NonNull
    @NotNull
    @Valid
    private IndividualNameDto name;

    private String relationShipCd;
    private Long ssn;
    private Character genderCd;
    @NonNull
    @NotNull
    private LocalDate dob;
}
