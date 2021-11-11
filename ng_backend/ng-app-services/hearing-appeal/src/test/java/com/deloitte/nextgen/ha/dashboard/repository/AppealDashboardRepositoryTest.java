package com.deloitte.nextgen.ha.dashboard.repository;

import com.deloitte.nextgen.framework.web.configuration.WebAutoConfiguration;
import com.deloitte.nextgen.ha.dashboard.dto.AppealOpenActionStatsDto;
import com.deloitte.nextgen.ha.dashboard.entity.AppealFiledResolvedStatsEntity;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableAutoConfiguration(exclude = {ReferenceTableAutoConfiguration.class, WebAutoConfiguration.class})
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppealDashboardRepositoryTest {
    @Autowired
    private AppealDashboardRepository dashboardRepository;

    @Value("${hearing-appeal.api-config.dashboard.filed-resolved-stats.months:5}")
    private int noOfMonths;

    @Test
    public void testGetOpenAppealsActionStats() {
        List<AppealOpenActionStatsDto> openActionStatsDtos = dashboardRepository.getOpenAppealsActionStats();
        log.info("AppealOpenActionStatsDto --> {} ", openActionStatsDtos);
    }

    @Test
    public void getFiledResolvedAppealsStats() {
        LocalDate endDate = LocalDate.now().with(lastDayOfMonth());
        LocalDate fromDate = endDate.minusMonths(noOfMonths).withDayOfMonth(1);
        List<AppealFiledResolvedStatsEntity> resolvedStats = dashboardRepository.getFiledResolvedAppealsStats(fromDate, endDate);
        log.info("AppealFiledResolvedStatsEntity --> {} ", resolvedStats);
    }
}
