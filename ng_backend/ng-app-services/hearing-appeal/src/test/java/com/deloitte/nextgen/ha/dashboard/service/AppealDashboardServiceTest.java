package com.deloitte.nextgen.ha.dashboard.service;

import com.deloitte.nextgen.framework.web.configuration.WebAutoConfiguration;
import com.deloitte.nextgen.ha.dashboard.dto.AppealOpenActionStatsDto;
import com.deloitte.nextgen.ha.dashboard.entity.AppealFiledResolvedStatsEntity;
import com.deloitte.nextgen.ha.dashboard.mapper.AppealDashboardMapperImpl;
import com.deloitte.nextgen.ha.dashboard.repository.AppealDashboardRepository;
import com.deloitte.nextgen.ha.entity.AmAppealInfo;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AppealDashboardService.class, AppealDashboardRepository.class, AppealDashboardMapperImpl.class})
@EnableAutoConfiguration(exclude = {ReferenceTableAutoConfiguration.class, WebAutoConfiguration.class})
@Slf4j
@EnableJpaRepositories(basePackageClasses = {AppealDashboardRepository.class})
@EntityScan(basePackageClasses = {AmAppealInfo.class, AppealFiledResolvedStatsEntity.class, AppealOpenActionStatsDto.class})
public class AppealDashboardServiceTest {

    @Autowired
    private AppealDashboardService dashboardService;

    @Test
    public void testFiledResolvedAppealsStats() {
        List<AppealFiledResolvedStatsEntity> resolvedStats = dashboardService.getFiledResolvedAppealsStats(Optional.empty(), Optional.empty());
        log.info("AppealFiledResolvedStatsEntity --> {} ", resolvedStats);
    }
}
