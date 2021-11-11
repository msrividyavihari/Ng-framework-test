import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import com.deloitte.ng.reftables.RefTableData;
import com.deloitte.ng.reftables.ReferenceTableManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * The class <code> GetValueForDescriptionOfReferenceTableManagerTest </code> is used
 * to test getValueForDescription Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/13/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */

@Slf4j
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetValueForDescriptionOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getValueForDescription Method for GENDER Reference Table.
     */
    @Test
    void getValueForDescriptionMethodTestforGenderRT() throws Exception {

        RefTableData testGenderMap = new RefTableData();

        Map<String, String> rowMap1 = new HashMap<String, String>();

        rowMap1.put("RANK","2");

        testGenderMap.setRefrTableCode("M");
        testGenderMap.setRefrTableDesc("Male");
        testGenderMap.setRefrTableAddiData(rowMap1);

        System.out.println("testGenderMap: " + testGenderMap);


        RefTableData resultGenderMap = referenceTableManager.getValueForDescription("GENDER","Male");

        System.out.println("resultGenderMap:" + resultGenderMap);

        Assert.isTrue(testGenderMap.equals(resultGenderMap));
    }

    /**
     * Tests getValueForDescription Method for APPSOPDUE Reference Table.
     */
    @Test
    void getValueForDescriptionMethodTestforAppsopdueRT() throws Exception {

        RefTableData testAppsopdueMap = new RefTableData();

        Map<String, String> rowMap1 = new HashMap<String, String>();

        rowMap1.put("APPRCVDDT","1");
        rowMap1.put("POLICYSOP","10");

        testAppsopdueMap.setRefrTableCode("QTRACK");
        testAppsopdueMap.setRefrTableDesc("9");
        testAppsopdueMap.setRefrTableAddiData(rowMap1);

        System.out.println("testAppsopdueMap: " + testAppsopdueMap);


        RefTableData resultAppsopdueMap = referenceTableManager.getValueForDescription("APPSOPDUE","9");

        System.out.println("resultAppsopdueMap:" + resultAppsopdueMap);

        Assert.isTrue(testAppsopdueMap.equals(resultAppsopdueMap));
    }

    /**
     * Tests getValueForDescription Method for Invalid Reference Table name.
     */
    @Test
    void getValueForDescriptionMethodTestforInvalidRT() throws Exception {

        RefTableData resultRt = referenceTableManager.getValueForDescription("RAHUL","9");

        System.out.println("resultRt:" + resultRt);

        Assert.isNull(resultRt);
    }

    /**
     * Tests getValueForDescription Method for Invalid Description.
     */
    @Test
    void getValueForDescriptionMethodTestforInvalidDescription() throws Exception {

        RefTableData resultRt = referenceTableManager.getValueForDescription("RAHUL","9");

        System.out.println("resultRt:" + resultRt);

        Assert.isNull(resultRt);
    }
}
