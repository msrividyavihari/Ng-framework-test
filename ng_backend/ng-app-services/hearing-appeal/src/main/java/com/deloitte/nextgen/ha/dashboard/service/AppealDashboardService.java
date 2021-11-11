package com.deloitte.nextgen.ha.dashboard.service;


import com.deloitte.nextgen.ha.dashboard.dto.AppealOpenActionStatsDto;
import com.deloitte.nextgen.ha.dashboard.dto.AppealOpenAgeStatsDto;
import com.deloitte.nextgen.ha.dashboard.entity.AppealFiledResolvedStatsEntity;
import com.deloitte.nextgen.ha.dashboard.mapper.AppealDashboardMapper;
import com.deloitte.nextgen.ha.dashboard.repository.AppealDashboardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppealDashboardService {

    private final AppealDashboardRepository dashBoardRepository;
    private final AppealDashboardMapper appealDashboardMapper;

    @Value("${hearing-appeal.api-config.dashboard.filed-resolved-stats.months:5}")
    private final int noOfMonths;

    public AppealOpenAgeStatsDto getOpenAppealsStats() {
        Map<String, Integer> resultMap = dashBoardRepository.getOpenAppealsStats();
        return appealDashboardMapper.map(resultMap);
    }


    public AppealOpenActionStatsDto getOpenAppealActionStats() {
        return dashBoardRepository
                .getOpenAppealsActionStats()
                .get(0);
    }

    public List<AppealFiledResolvedStatsEntity> getFiledResolvedAppealsStats(Optional<LocalDate> fromDateArg, Optional<LocalDate> endDateArg) {
        LocalDate endDate = endDateArg.orElseGet(() -> LocalDate.now().with(lastDayOfMonth()));
        LocalDate fromDate = (fromDateArg.isPresent()) ? fromDateArg.get() : endDate.minusMonths(noOfMonths).withDayOfMonth(1);

        if (fromDate.isAfter(endDate)) {
            LocalDate temp = fromDate;
            fromDate = endDate;
            endDate = temp;
        }
        List<AppealFiledResolvedStatsEntity> resultList = dashBoardRepository.getFiledResolvedAppealsStats(fromDate, endDate);
        Set<YearMonth> dbDates = resultList
                .stream()
                .map(AppealFiledResolvedStatsEntity::getMonthYear)
                .map(YearMonth::from)
                .collect(Collectors.toSet());

        List<AppealFiledResolvedStatsEntity> notInDBResults = new ArrayList<>();
        while (fromDate.isBefore(endDate)) {
            if (!dbDates.contains(YearMonth.from(fromDate))) {
                notInDBResults.add(AppealFiledResolvedStatsEntity.of(fromDate));
            }
            fromDate = fromDate.plusMonths(1);
        }

        return Stream.concat(resultList.stream(), notInDBResults.stream())
                .sorted(Comparator.comparing(AppealFiledResolvedStatsEntity::getMonthYear, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }
}