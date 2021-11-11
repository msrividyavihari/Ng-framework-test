import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * The class <code> GetRowForCodeMethodOfReferenceTableManagerTest </code> is used
 * to test getRowForCode Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/08/2020 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@Slf4j
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetRowForCodeMethodOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTable referenceTable;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getRowForCode Method for GENDER Reference Table with valid code.
     */
    @Test
    void getRowForCodeMethodTestforGenderRTWithValidCode() throws Exception {

        Map testMapGender = new HashMap();

        testMapGender.put("CODE", "U");
        testMapGender.put("DESCRIPTION","Unknown");
        testMapGender.put("RANK","4");

        System.out.println("testMapGender:" + testMapGender);

        Map resultMapGender = referenceTable.getRowForCode("GENDER","U");

        System.out.println("resultMapGender:" + resultMapGender);

        Assert.isTrue (testMapGender.equals(resultMapGender));
    }

    /**
     * Tests getRowForCode Method for HOLIDAY Reference Table with valid code.
     */
    @Test
    void getRowForCodeMethodTestforHolidayRTWithValidCode() throws Exception {

        Map testMapHoliday = new HashMap();

        testMapHoliday.put("CODE", "01/02/2017");
        testMapHoliday.put("DESCRIPTION","New Years Day - Observed");

        System.out.println("testMapHoliday:" + testMapHoliday);

        Map resultMapHoliday = referenceTable.getRowForCode("HOLIDAY","01/02/2017");

        System.out.println("resultMapHoliday:" + resultMapHoliday);

        Assert.isTrue (testMapHoliday.equals(resultMapHoliday));

    }

    /**
     * Tests getRowForCode Method for GENDER Reference Table with Invalid code.
     */
    @Test
    void getRowForCodeMethodTestforGenderRTWithInvalidCode() throws Exception {

        Map resultMapGenderInvalid = null;

        Exception testGenderRTException = new Exception("Row not found in reference table GENDER for CODE value R");

        Exception testGenderResultException = null;

        try {
            resultMapGenderInvalid = referenceTable.getRowForCode("GENDER", "R");
        } catch (FwApplicationException e){
            testGenderResultException = e;
            System.out.println("Exception: " + e);
        }

        System.out.println("resultMapGenderInvalid:" + resultMapGenderInvalid);

        System.out.println("testGenderRTException:" + testGenderRTException.getMessage());

        System.out.println("testGenderResultException:" + testGenderResultException.getMessage());

        Assert.isTrue ((testGenderRTException.getMessage()).equals(testGenderResultException.getMessage()));
    }

}
