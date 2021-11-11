package com.deloitte.nextgen.ha.appeals.overview.dto;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualDto;
import com.deloitte.nextgen.ha.appeals.dto.AppealInfoDto;
import com.deloitte.nextgen.ha.appeals.dto.ContactInfoDto;
import com.deloitte.nextgen.ha.common.dto.IndividualNameDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Data
public class AppealOverviewResponseDto {
    @NonNull
    private AppealInfoDto appealInfo;
    @NonNull
    private ContactInfoDto contactInfo;

    @NonNull
    private IndividualNameDto primaryAppellant;

    @Setter(AccessLevel.NONE)
    private final List<IndividualNameDto> additionalAppellants = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    private List<AuthRepDto> authReps = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    private List<AppealTimelineResponseDto> timelines = new ArrayList<>();

}
