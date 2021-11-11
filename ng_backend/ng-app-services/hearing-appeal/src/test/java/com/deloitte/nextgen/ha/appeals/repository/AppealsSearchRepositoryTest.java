package com.deloitte.nextgen.ha.appeals.repository;

import com.deloitte.nextgen.ha.dashboard.dto.AppealSearchCriteria;
import com.deloitte.nextgen.ha.dashboard.dto.AppealSearchResponseDto;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@SpringBootTest
@EnableJpaRepositories(basePackageClasses = {AppealSearchRepository.class}, includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {AppealSearchRepository.class})})
@EntityScan(basePackages={"com.deloitte.nextgen.ha.entity"})
@ContextConfiguration(classes = {AppealSearchRepository.class})
@Slf4j
public class AppealsSearchRepositoryTest {

    @Autowired
    private AppealSearchRepository appealSearchRepository;
    @Test
    public void testFindAppeals(){
        AppealSearchCriteria dto = new AppealSearchCriteria();
        dto.setAppealTypeCds(ImmutableList.of("APD", "APE", "ADH", "APK"));
        dto.setAppealStatusCds(ImmutableList.of());
        dto.setFiledDtFrom(LocalDate.of(2021,01,01));
        Page<AppealSearchResponseDto> results =  appealSearchRepository.findAppeals(dto, PageRequest.of(0, 10));
        log.info("Appeals: {} ", results);
    }
}