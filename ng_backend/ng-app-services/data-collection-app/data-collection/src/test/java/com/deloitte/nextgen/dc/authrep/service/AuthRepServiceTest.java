package com.deloitte.nextgen.dc.authrep.service;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.authrep.mapper.AuthRepSearchResponseMapperImpl;
import com.deloitte.nextgen.dc.entities.DcAuthRep;
import com.deloitte.nextgen.dc.repository.DcCaseAddressesRepository;
import com.deloitte.nextgen.framework.web.configuration.WebAutoConfiguration;
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

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AuthRepService.class, AuthRepSearchResponseMapperImpl.class})
@EnableAutoConfiguration(exclude = {ReferenceTableAutoConfiguration.class, WebAutoConfiguration.class})
@Slf4j
@EnableJpaRepositories(basePackageClasses = {DcCaseAddressesRepository.class})
@EntityScan(basePackageClasses = {DcAuthRep.class})
public class AuthRepServiceTest {

    @Autowired
    private AuthRepService authRepService;

    @Test
    public void testFindByCaseNum() {
        Optional<AuthRepDto> authRepDto = authRepService.findAuthRepByCaseNum(122256186L);
        log.info("AuthRep --> {}", authRepDto);
    }
}
