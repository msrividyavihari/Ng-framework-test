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
 * The class <code> GetRowForDescriptionMethodOfReferenceTableManagerTest </code> is used
 * to test getRowForDescription Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/08/2020 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@Slf4j
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})

class GetRowForDescriptionMethodOfReferenceTableManagerTest {
    @Autowired
    private ReferenceTable referenceTable;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getRowForDescription Method for HOLIDAY Reference Table with Valid Description.
     */
    @Test
    void getRowForDescriptionMethodTestforHolidayRTWithValidDescription() throws Exception {

        Map testMapHoliday = new HashMap();

        testMapHoliday.put("CODE", "01/02/2017");
        testMapHoliday.put("DESCRIPTION", "New Years Day - Observed");

        System.out.println("testMapHoliday:" + testMapHoliday);

        Map resultMapHoliday = referenceTable.getRowForDescription("HOLIDAY", "New Years Day - Observed");

        System.out.println("resultMapHoliday:" + resultMapHoliday);

        Assert.isTrue(testMapHoliday.equals(resultMapHoliday));
    }

    /**
     * Tests getRowForDescription Method for HOLIDAY Reference Table with Duplicate Description.
     */
    @Test
    void getRowForDescriptionMethodTestforHolidayRTWithDuplicateDescription() throws Exception {

        Map testMapHoliday2 = new HashMap();

        testMapHoliday2.put("CODE", "05/25/2020");
        testMapHoliday2.put("DESCRIPTION", "Memorial Day");

        System.out.println("testMapHoliday2:" + testMapHoliday2);

        Map resultMapHoliday2 = referenceTable.getRowForDescription("HOLIDAY", "Memorial Day");

        System.out.println("resultMapHoliday2:" + resultMapHoliday2);

        Assert.isTrue (testMapHoliday2.equals(resultMapHoliday2));
    }

    /**
     * Tests getRowForDescription Method for GENDER Reference Table with Valid Description.
     */
    @Test
    void getRowForDescriptionMethodTestforGenderRTWithValidDescription() throws Exception {
        Map testMapGender = new HashMap();

        testMapGender.put("CODE", "U");
        testMapGender.put("DESCRIPTION","Unknown");
        testMapGender.put("RANK","4");

        System.out.println("testMapGender:" + testMapGender);

        Map resultMapGender = referenceTable.getRowForDescription("GENDER","Unknown");

        System.out.println("resultMapGender:" + resultMapGender);

        Assert.isTrue (testMapGender.equals(resultMapGender));
    }

}
