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
 * The class <code> GetRowsForColumnFilterOfReferenceTableManagerTest </code> is used
 * to test getRowsForColumnFilter Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/08/2020 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@Slf4j
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetRowsForColumnFilterOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTable referenceTable;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getRowsForColumnFilter Method for GENDER Reference Table with RANK and DESCRIPTION Filter.
     */
    @Test
    void getRowsForColumnFilterMethodTestforGenderRTWithRankAndDescriptionFilter() throws Exception {

        Map<String, Map<String, String>> testMapGender1 = new HashMap<>();

        Map<String, String> testRowMapG = new HashMap<>();

        testRowMapG.put("CODE", "U");
        testRowMapG.put("DESCRIPTION", "Unknown");
        testRowMapG.put("RANK", "4");

        testMapGender1.put("00004", testRowMapG);

        System.out.println("testMapGender1:" + testMapGender1);

        Map<String, String> aFilterG = new HashMap<>();
        aFilterG.put("RANK", "4");
        aFilterG.put("DESCRIPTION", "Unknown");

        Map resultMapGender1 = referenceTable.getRowsForColumnFilter("GENDER", aFilterG);

        System.out.println("resultMapGender1:" + resultMapGender1);

        Assert.isTrue(testMapGender1.equals(resultMapGender1));

    }

    /**
     * Tests getRowsForColumnFilter Method for GENDER Reference Table with RANK Filter.
     */
    @Test
    void getRowsForColumnFilterMethodTestforGenderRTWithRankFilter() throws Exception {

        Map<String, Map<String, String>> testMapGender2 = new HashMap<>();

        Map<String, String> testRowMapG = new HashMap<>();

        testRowMapG.clear();

        testRowMapG.put("CODE", "M");
        testRowMapG.put("DESCRIPTION", "Male");
        testRowMapG.put("RANK", "2");

        testMapGender2.put("00002", testRowMapG);

        System.out.println("testMapGender2:" + testMapGender2);

        Map<String, String> aFilterG2 = new HashMap<>();
        aFilterG2.put("RANK", "2");

        Map resultMapGender2 = referenceTable.getRowsForColumnFilter("GENDER", aFilterG2);

        System.out.println("resultMapGender2:" + resultMapGender2);

        Assert.isTrue(testMapGender2.equals(resultMapGender2));

    }

    /**
     * Tests getRowsForColumnFilter Method for HOLIDAY Reference Table with DESCRIPTION Filter.
     */
    @Test
    void getRowsForColumnFilterMethodTestforHolidayRTWithDescriptionFilter() throws Exception {

        Map<String, Map<String, String>> testMapHoliday = new HashMap<>();

        Map<String, String> testRowMapH = new HashMap<>();

        testRowMapH.put("CODE", "12/24/2015");
        testRowMapH.put("DESCRIPTION","Observed Holiday");

        testMapHoliday.put("Observed Holiday",testRowMapH);

        System.out.println("testMapHoliday:" + testMapHoliday);

        Map<String, String> aFilterH= new HashMap<>();
        aFilterH.put("DESCRIPTION","Observed Holiday");

        Map resultMapHoliday = referenceTable.getRowsForColumnFilter("HOLIDAY", aFilterH);

        System.out.println("resultMapHoliday:" + resultMapHoliday);

        Assert.isTrue (testMapHoliday.equals(resultMapHoliday));

    }

}
