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
 * The class <code> GetRowsForCodeAndColumnOfReferenceTableManagerTest </code> is used
 * to test getRowsForCodeAndColumn Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/08/2020 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */

@Slf4j
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetRowsForCodeAndColumnOfReferenceTableManagerTest {


    @Autowired
    private ReferenceTable referenceTable;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getRowsForCodeAndColumn Method for GENDER Reference Table.
     */
    @Test
    void getRowsForCodeAndColumnMethodTestforGenderRT() throws Exception {

        Map<String, Map<String, String>> testMapGender = new HashMap<>();

        Map<String, String> testRowMapG = new HashMap<>();

        testRowMapG.put("CODE", "U");
        testRowMapG.put("DESCRIPTION", "Unknown");
        testRowMapG.put("RANK", "4");

        testMapGender.put("0", testRowMapG);

        System.out.println("testMapGender:" + testMapGender);

        String[] columns = {"CODE"};
        Map resultMapGender = referenceTable.getRowsForCodeAndColumn("GENDER", "U", columns);

        System.out.println("resultMapGender:" + resultMapGender);

        Assert.isTrue(testMapGender.equals(resultMapGender));

    }

    /**
     * Tests getRowsForCodeAndColumn Method for HOLIDAY Reference Table.
     */
    @Test
    void getRowsForCodeAndColumnMethodTestforHolidayRT() throws Exception {

        Map<String, Map<String, String>> testMapHoliday = new HashMap<>();

        Map<String, String> testRowMapH = new HashMap<>();

        testRowMapH.put("CODE", "12/24/2015");
        testRowMapH.put("DESCRIPTION", "Observed Holiday");

        testMapHoliday.put("0", testRowMapH);

        System.out.println("testMapHoliday:" + testMapHoliday);

        String[] columns = {"CODE"};

        Map resultMapHoliday = referenceTable.getRowsForCodeAndColumn("HOLIDAY", "12/24/2015", columns);

        System.out.println("resultMapHoliday:" + resultMapHoliday);

        Assert.isTrue (testMapHoliday.equals(resultMapHoliday));

    }

}
