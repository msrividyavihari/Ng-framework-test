import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import com.deloitte.ng.reftables.RefTableData;
import com.deloitte.ng.reftables.ReferenceTableManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * The class <code> GetValuesForColumnFilterOfReferenceTableManagerTest </code> is used
 * to test getValuesForColumnFilter Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/08/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetValuesForColumnFilterOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getValuesForColumnFilter Method for GENDER Reference Table with RANK Filter.
     */
    @Test
    void getValuesForColumnFilterMethodTestforGenderRTWithRankFilter() throws Exception {

        Map<String, RefTableData> testGenderMap = new HashMap<String, RefTableData>();

        RefTableData testGenderData = new RefTableData();

        Map<String, String> rowMap1 = new HashMap<String, String>();

        rowMap1.put("RANK","2");

        testGenderData.setRefrTableCode("M");
        testGenderData.setRefrTableDesc("Male");
        testGenderData.setRefrTableAddiData(rowMap1);


        testGenderMap.put("00002",testGenderData);

        System.out.println("testGenderMap:" + testGenderMap);

        Map resultGenderMap = referenceTableManager.getValuesForColumnFilter(true,"GENDER","RANK","2");

        System.out.println("resultGenderMap:" + resultGenderMap);

        Assert.isTrue(testGenderMap.equals(resultGenderMap));

    }

    /**
     * Tests getValuesForColumnFilter Method for APPSOPDUE Reference Table with DESCRIPTION Filter.
     */
    @Test
    void getValuesForColumnFilterMethodTestforAppsopdueRTWithDescriptionFilter() throws Exception {

        Map<String, RefTableData> testAppsopdueMap = new HashMap<String, RefTableData>();

        RefTableData testAppsopdueData = new RefTableData();

        Map<String, String> rowMap1 = new HashMap<String, String>();
        Map<String, String> rowMap2 = new HashMap<String, String>();

        rowMap1.put("APPRCVDDT","1");
        rowMap1.put("POLICYSOP","60");

        testAppsopdueData.setRefrTableCode("DISABLEDNOTAGED");
        testAppsopdueData.setRefrTableDesc("59");
        testAppsopdueData.setRefrTableAddiData(rowMap1);


        testAppsopdueMap.put("59",testAppsopdueData);

        System.out.println("testAppsopdueMap:" + testAppsopdueMap);

        Map resultAppsopdueMap = referenceTableManager.getValuesForColumnFilter(true,"APPSOPDUE","DESCRIPTION","59");

        System.out.println("resultAppsopdueMap:" + resultAppsopdueMap);

        Assert.isTrue(testAppsopdueMap.equals(resultAppsopdueMap));

    }

    /**
     * Tests getValuesForColumnFilter Method for Invalid Filter.
     */
    @Test
    void getValuesForColumnFilterMethodTestforInvalidFilter() throws Exception {

        Map testMap = new HashMap<>();

        System.out.println("testMap:" + testMap);

        Map resultMap = referenceTableManager.getValuesForColumnFilter(true,"GENDER","RANK","5");

        System.out.println("resultMap:" + resultMap);

        Assert.isTrue(testMap.equals(resultMap));

    }
}
