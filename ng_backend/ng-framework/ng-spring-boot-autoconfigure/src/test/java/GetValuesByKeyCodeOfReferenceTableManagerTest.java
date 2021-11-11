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
 * The class <code> GetValuesByKeyCodeOfReferenceTableManagerTest </code> is used
 * to test getValuesByKeyCode Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/13/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */

@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetValuesByKeyCodeOfReferenceTableManagerTest {


    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getValuesByKeyCode Method for GENDER Reference Table.
     */
    @Test
    void getValuesByKeyCodeMethodTestforGenderRT() throws Exception {

        RefTableData testGenderRt = new RefTableData();

        Map<String, String> rowMap1 = new HashMap<String, String>();

        rowMap1.put("RANK","4");

        testGenderRt.setRefrTableCode("U");
        testGenderRt.setRefrTableDesc("Unknown");
        testGenderRt.setRefrTableAddiData(rowMap1);

        System.out.println("testGenderRt: " + testGenderRt);


        RefTableData resultGenderRt = referenceTableManager.getValuesByKeyCode("GENDER","U","CODE");

        System.out.println("resultGenderRt:" + resultGenderRt);

        Assert.isTrue(testGenderRt.equals(resultGenderRt));
    }

    /**
     * Tests getValuesByKeyCode Method for APPSOPDUE Reference Table.
     */
    @Test
    void getValuesByKeyCodeMethodTestforAppsopdueRT() throws Exception {

        RefTableData testAppsopdueRt = new RefTableData();

        Map<String, String> rowMap1 = new HashMap<String, String>();

        rowMap1.put("APPRCVDDT","1");
        rowMap1.put("POLICYSOP","10");

        testAppsopdueRt.setRefrTableCode("QTRACK");
        testAppsopdueRt.setRefrTableDesc("9");
        testAppsopdueRt.setRefrTableAddiData(rowMap1);

        System.out.println("testAppsopdueRt: " + testAppsopdueRt);


        RefTableData resultAppsopdueRt = referenceTableManager.getValuesByKeyCode("APPSOPDUE","QTRACK","CODE");

        System.out.println("resultAppsopdueRt:" + resultAppsopdueRt);

        Assert.isTrue(testAppsopdueRt.equals(resultAppsopdueRt));
    }

    /**
     * Tests getValuesByKeyCode Method for Invalid Reference Table name.
     */
    @Test
    void getValuesByKeyCodeMethodTestforInvalidRT() throws Exception {

        RefTableData testRt = new RefTableData();

        System.out.println("testRt:" + testRt);

        RefTableData resultRt = referenceTableManager.getValuesByKeyCode("RAHUL","QTRACK","CODE");

        System.out.println("resultRt:" + resultRt);

        Assert.isTrue(testRt.equals(resultRt));
    }

    /**
     * Tests getValuesByKeyCode Method for Invalid Code.
     */
    @Test
    void getValuesByKeyCodeMethodTestforInvalidCode() throws Exception {

        RefTableData testRt = new RefTableData();

        System.out.println("testRt:" + testRt);

        RefTableData resultRt = referenceTableManager.getValuesByKeyCode("APPSOPDUE","RAHUL","CODE");

        System.out.println("resultRt:" + resultRt);

        Assert.isTrue(testRt.equals(resultRt));
    }
}
