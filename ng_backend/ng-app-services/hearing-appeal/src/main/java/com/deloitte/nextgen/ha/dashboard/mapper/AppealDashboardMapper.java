package com.deloitte.nextgen.ha.dashboard.mapper;

import com.deloitte.nextgen.ha.dashboard.dto.AppealOpenAgeStatsDto;
import org.mapstruct.*;

import java.util.Map;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppealDashboardMapper {

    @Mapping(target = "averageOpenDays", expression = "java(openAppealsAgeMap.getOrDefault(\"AVG_OPEN_DAYS\", 0))")
    @Mapping(target = "under30Days",  expression = "java(openAppealsAgeMap.getOrDefault(\"UNDER_30_DAYS\", 0))")
    @Mapping(target = "between31And60Days", expression = "java(openAppealsAgeMap.getOrDefault(\"BETWEEN_31_AND_60_DAYS\", 0))")
    @Mapping(target = "between61And90Days", expression = "java(openAppealsAgeMap.getOrDefault(\"BETWEEN_61_AND_90_DAYS\", 0))")
    @Mapping(target = "above90Days",  expression = "java(openAppealsAgeMap.getOrDefault(\"ABOVE_90_DAYS\", 0))")
    AppealOpenAgeStatsDto map(Map<String, Integer> openAppealsAgeMap);
}
