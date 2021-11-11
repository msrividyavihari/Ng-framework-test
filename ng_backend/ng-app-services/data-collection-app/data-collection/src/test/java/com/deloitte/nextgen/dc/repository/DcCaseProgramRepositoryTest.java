package com.deloitte.nextgen.dc.repository;

import com.deloitte.nextgen.dc.entities.DcCaseProgram;
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

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableAutoConfiguration(exclude = {ReferenceTableAutoConfiguration.class, WebAutoConfiguration.class})
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DcCaseProgramRepositoryTest {

    @Autowired
    private DcCaseProgramRepository programRepository;

    @Test
    public void testFindByCaseNumIn(){
        long caseNum = 122256186L;
        List<DcCaseProgram> programs =  programRepository.findByCaseNumIn(Collections.singletonList(caseNum));
        log.info("Case # {} --> Programs {} ", caseNum, programs );
    }

}
