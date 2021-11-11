package com.deloitte.nextgen.ha.appeals.dto;

import com.deloitte.nextgen.dc.individual.dto.IndividualDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class AppellantInfoDto {

    @NonNull
    @NotNull
    private IndividualDto primaryAppellant;

    @Setter(AccessLevel.NONE)
    private  List<IndividualDto> additionalAppellants = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    private final List<IndividualDto> nonAppellants = new ArrayList<>();
}
