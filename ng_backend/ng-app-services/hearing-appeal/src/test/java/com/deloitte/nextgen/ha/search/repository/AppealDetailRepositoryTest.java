package com.deloitte.nextgen.ha.search.repository;

import com.deloitte.nextgen.ha.entity.AmAppealInfo;
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


@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableAutoConfiguration(exclude= {ReferenceTableAutoConfiguration.class})
@Slf4j
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class AppealDetailRepositoryTest {

    @Autowired
    private AppealDetailRepository appealDetailRepository;

    @Test
    public void testFindByCaseNum() {
        long caseNum=122256186l;
        List<AmAppealInfo> amAppealInfos = appealDetailRepository.findByCaseNum(caseNum);
        log.debug("CaseNum:{} No of Appeals: {}", caseNum, amAppealInfos.size());
    }
}
