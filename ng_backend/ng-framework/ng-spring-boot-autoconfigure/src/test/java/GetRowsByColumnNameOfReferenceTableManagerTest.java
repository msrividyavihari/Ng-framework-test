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
 * The class <code> GetRowsByColumnNameOfReferenceTableManagerTest </code> is used
 * to test getRowsByColumnName Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/08/2020 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */

@Slf4j
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetRowsByColumnNameOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTable referenceTable;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getRowsByColumnName Method for GENDER Reference Table.
     */
    @Test
    void getRowsByColumnNameMethodTestforGenderRT() throws Exception {

        Map<String, Map<String, String>> testMapGender = new HashMap<>();

        Map<String, String> testRowMap1 = new HashMap<>();
        Map<String, String> testRowMap2 = new HashMap<>();
        Map<String, String> testRowMap3 = new HashMap<>();

        testRowMap1.put("CODE", "F");
        testRowMap1.put("DESCRIPTION", "Female");
        testRowMap1.put("RANK", "3");

        testMapGender.put("1", testRowMap1);

        testRowMap2.put("CODE", "M");
        testRowMap2.put("DESCRIPTION", "Male");
        testRowMap2.put("RANK", "2");

        testMapGender.put("2", testRowMap2);

        testRowMap3.put("CODE", "U");
        testRowMap3.put("DESCRIPTION", "Unknown");
        testRowMap3.put("RANK", "4");

        testMapGender.put("3", testRowMap3);

        System.out.println("testMapGender:" + testMapGender);

        String[] columns = {"CODE"};

        Map resultMapGender = referenceTable.getRowsByColumnName("GENDER", columns);

        System.out.println("resultMapGender:" + resultMapGender);

        Assert.isTrue(testMapGender.equals(resultMapGender));
    }

    /**
     * Tests getRowsByColumnName Method for HOLIDAY Reference Table.
     */
    @Test
    void getRowsByColumnNameMethodTestforHolidayRT() throws Exception {
        Map<String, Map<String, String>> testMapHoliday = new HashMap<>();

        Map<String, String> testRowHMap1 = new HashMap<>();
        Map<String, String> testRowHMap2 = new HashMap<>();
        Map<String, String> testRowHMap3 = new HashMap<>();
        Map<String, String> testRowHMap4 = new HashMap<>();
        Map<String, String> testRowHMap5 = new HashMap<>();
        Map<String, String> testRowHMap6 = new HashMap<>();
        Map<String, String> testRowHMap7 = new HashMap<>();
        Map<String, String> testRowHMap8 = new HashMap<>();
        Map<String, String> testRowHMap9 = new HashMap<>();
        Map<String, String> testRowHMap10 = new HashMap<>();
        Map<String, String> testRowHMap11 = new HashMap<>();
        Map<String, String> testRowHMap12 = new HashMap<>();
        Map<String, String> testRowHMap13 = new HashMap<>();
        Map<String, String> testRowHMap14 = new HashMap<>();
        Map<String, String> testRowHMap15 = new HashMap<>();
        Map<String, String> testRowHMap16 = new HashMap<>();
        Map<String, String> testRowHMap17 = new HashMap<>();
        Map<String, String> testRowHMap18 = new HashMap<>();
        Map<String, String> testRowHMap19 = new HashMap<>();
        Map<String, String> testRowHMap20 = new HashMap<>();
        Map<String, String> testRowHMap21 = new HashMap<>();
        Map<String, String> testRowHMap22 = new HashMap<>();


        testRowHMap1.put("CODE","01/01/2016");
        testRowHMap1.put("DESCRIPTION","New Years");

        testMapHoliday.put("1", testRowHMap1);

        testRowHMap2.put("CODE","01/01/2018");
        testRowHMap2.put("DESCRIPTION","New Year's Day");

        testMapHoliday.put("2", testRowHMap2);

        testRowHMap3.put("CODE","01/01/2020");
        testRowHMap3.put("DESCRIPTION","New Year&#39;s Day");

        testMapHoliday.put("3", testRowHMap3);

        testRowHMap4.put("CODE","01/02/2017");
        testRowHMap4.put("DESCRIPTION","New Years Day - Observed");

        testMapHoliday.put("4", testRowHMap4);

        testRowHMap5.put("CODE","01/20/2020");
        testRowHMap5.put("DESCRIPTION","Martin Luther King, Jr.&#39;s Birthday");

        testMapHoliday.put("5", testRowHMap5);

        testRowHMap6.put("CODE","01/21/2019");
        testRowHMap6.put("DESCRIPTION","Martin Luther King Day");

        testMapHoliday.put("6", testRowHMap6);

        testRowHMap7.put("CODE","04/17/2020");
        testRowHMap7.put("DESCRIPTION","State Holiday");

        testMapHoliday.put("7", testRowHMap7);

        testRowHMap8.put("CODE","04/22/2019");
        testRowHMap8.put("DESCRIPTION","State Holiday - Observed");

        testMapHoliday.put("8", testRowHMap8);

        testRowHMap9.put("CODE","04/27/2015");
        testRowHMap9.put("DESCRIPTION","Confederate Memorial Day");

        testMapHoliday.put("9", testRowHMap9);

        testRowHMap10.put("CODE","05/19/2020");
        testRowHMap10.put("DESCRIPTION","Holiday");

        testMapHoliday.put("10", testRowHMap10);

        testRowHMap11.put("CODE","05/25/2020");
        testRowHMap11.put("DESCRIPTION","Memorial Day");

        testMapHoliday.put("11", testRowHMap11);

        testRowHMap12.put("CODE","07/03/2015");
        testRowHMap12.put("DESCRIPTION","Independence Day - observed");

        testMapHoliday.put("12", testRowHMap12);

        testRowHMap13.put("CODE","07/04/2019");
        testRowHMap13.put("DESCRIPTION","Independence Day");

        testMapHoliday.put("13", testRowHMap13);

        testRowHMap14.put("CODE","09/02/2019");
        testRowHMap14.put("DESCRIPTION","Labor Day");

        testMapHoliday.put("14", testRowHMap14);

        testRowHMap15.put("CODE","10/14/2019");
        testRowHMap15.put("DESCRIPTION","Columbus Day");

        testMapHoliday.put("15", testRowHMap15);

        testRowHMap16.put("CODE","11/12/2018");
        testRowHMap16.put("DESCRIPTION","Veterans Day");

        testMapHoliday.put("16", testRowHMap16);

        testRowHMap17.put("CODE","11/23/2017");
        testRowHMap17.put("DESCRIPTION","Thanksgiving Day");

        testMapHoliday.put("17", testRowHMap17);

        testRowHMap18.put("CODE","11/25/2016");
        testRowHMap18.put("DESCRIPTION","Robert E Lees Birthday - observed");

        testMapHoliday.put("18", testRowHMap18);

        testRowHMap19.put("CODE","12/24/2015");
        testRowHMap19.put("DESCRIPTION","Observed Holiday");

        testMapHoliday.put("19", testRowHMap19);

        testRowHMap20.put("CODE","12/24/2019");
        testRowHMap20.put("DESCRIPTION","Washington's Birthday - Observed");

        testMapHoliday.put("20", testRowHMap20);

        testRowHMap21.put("CODE","12/25/2019");
        testRowHMap21.put("DESCRIPTION","Christmas Day");

        testMapHoliday.put("21", testRowHMap21);

        testRowHMap22.put("CODE","12/26/2016");
        testRowHMap22.put("DESCRIPTION","Christmas Day - observed");

        testMapHoliday.put("22", testRowHMap22);

        System.out.println("testMapHoliday:" + testMapHoliday);

        String[] columns = {"CODE"};

        Map resultMapHoliday = referenceTable.getRowsByColumnName("HOLIDAY",columns);

        System.out.println("resultMapHoliday:" + resultMapHoliday);

        Assert.isTrue (testMapHoliday.equals(resultMapHoliday));
    }

}
