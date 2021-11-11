package com.deloitte.nextgen.dc.repository;

import com.deloitte.nextgen.dc.entities.DcCaseAddresses;
import com.deloitte.nextgen.framework.web.configuration.WebAutoConfiguration;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableAutoConfiguration(exclude = {ReferenceTableAutoConfiguration.class, WebAutoConfiguration.class})
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DcCaseAddressesRepositoryTest {

    @Autowired
    private DcCaseAddressesRepository dcCaseAddressesRepository;

    @Test
    public void testFindAuthRepAddressByCaseNum() {
        Optional<DcCaseAddresses> address = dcCaseAddressesRepository.findAuthRepAddressByCaseNum(122256186L);
        log.info("DcCaseAddresses --> {}", address);
    }

    @Test
    public void testFindByCaseNum() {
        List<DcCaseAddresses> address = dcCaseAddressesRepository.findByCaseNum(122256186L);
        log.info("DcCaseAddresses List --> {}", address);
    }
}
