package com.deloitte.nextgen.ha.repository;

import com.deloitte.nextgen.ha.common.repository.AmAplGrpRepository;
import com.deloitte.nextgen.ha.entity.AmAplGrp;
import com.deloitte.nextgen.ha.entity.AmAplGrpId;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(RandomBeansExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnableAutoConfiguration()
public class AmAplGrpRepositoryTest {

    @Autowired
    private AmAplGrpRepository amAplGrpRepository;

    @BeforeEach
    public void setUp() {
        assertNotNull(this.amAplGrpRepository);
    }


    @Test
    @DisplayName("Saving a Random Appeal and Retrieving Saved Appeal")
    public void testSaveAppeal(@Random AmAplGrp actual) {
        //1. Saving the HeAppeal Entity object
        amAplGrpRepository.save(actual);

        //2. Retrieving the HeAppeal Entity object from the database.
        AmAplGrpId amAplGrpId = new AmAplGrpId();
        amAplGrpId.setAplNum(actual.getAplNum());
        amAplGrpId.setAplGroupNum(actual.getAplGroupNum());

        AmAplGrp expected = amAplGrpRepository
                .findById(amAplGrpId)
                .get();
        //3. Comparing the retrieved object with the saved object using reflection
        assertThat(expected)
                .usingRecursiveComparison()
                .isEqualTo(actual);
    }
}
