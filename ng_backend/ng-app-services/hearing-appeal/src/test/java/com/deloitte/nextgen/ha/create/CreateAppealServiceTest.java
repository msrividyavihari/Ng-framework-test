package com.deloitte.nextgen.ha.create;


import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude= ReferenceTableAutoConfiguration.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class CreateAppealServiceTest{

    @Autowired
    public CreateAppealService createAppealService;

    @BeforeEach
    public void setUp() {
        assertNotNull(this.createAppealService);
    }

    @Test
    @DisplayName("Test - CreateAppeal")
    public void testCreateAppeal() {
        /*FillingCreateAppealDto appealDto = new FillingCreateAppealDto();
        appealDto.setFilingMethodCd("fx");
        appealDto.setPreferredLangCd("en");
        appealDto.setHomePhNum(new BigInteger("3212312232"));
        appealDto.setWorkPhNum(new BigInteger("2132312321"));
        appealDto.setPreferredCntctCd("eaa");
        appealDto.setStreetName("1609 centre creek");
        appealDto.setAddressLine2("dr");
        appealDto.setCityName("Austin");
        appealDto.setStateCd("TX");
        appealDto.setZipCd("78754");
        appealDto.setCountyCd("021");*/

       // createAppealService.createAppeal(appealDto);

    }
}
