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
 * The class <code> GetValuesForCodeOfReferenceTableManager </code> is used
 * to test getValuesForCode Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/13/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetValuesForCodeOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getValuesForCode Method for GENDER Reference Table.
     */
    @Test
    void getValuesForCodeMethodTestforGenderRT() throws Exception {

        RefTableData testGenderRt = new RefTableData();

        Map<String, String> rowMap1 = new HashMap<String, String>();

        rowMap1.put("RANK","2");

        testGenderRt.setRefrTableCode("M");
        testGenderRt.setRefrTableDesc("Male");
        testGenderRt.setRefrTableAddiData(rowMap1);

        System.out.println("testGenderRt: " + testGenderRt);


        RefTableData resultGenderRt = referenceTableManager.getValuesForCode(true,"GENDER","M");

        System.out.println("resultGenderRt:" + resultGenderRt);

        Assert.isTrue(testGenderRt.equals(resultGenderRt));

    }

    /**
     * Tests getValuesForCode Method for APPSOPDUE Reference Table.
     */
    @Test
    void getValuesForCodeMethodTestforAppsopdueRT() throws Exception {

        RefTableData testAppsopdueRt = new RefTableData();

        Map<String, String> rowMap1 = new HashMap<String, String>();

        rowMap1.put("APPRCVDDT","1");
        rowMap1.put("POLICYSOP","10");

        testAppsopdueRt.setRefrTableCode("QTRACK");
        testAppsopdueRt.setRefrTableDesc("9");
        testAppsopdueRt.setRefrTableAddiData(rowMap1);

        System.out.println("testAppsopdueRt: " + testAppsopdueRt);


        RefTableData resultAppsopdueRt = referenceTableManager.getValuesForCode(true,"APPSOPDUE","QTRACK");

        System.out.println("resultAppsopdueRt:" + resultAppsopdueRt);

        Assert.isTrue(testAppsopdueRt.equals(resultAppsopdueRt));

    }

    /**
    * Tests getValuesForCode Method for Invalid Reference Table name.
    */
    @Test
    void getValuesForCodeMethodTestforInvalidRT() throws Exception {

        RefTableData resultRt = referenceTableManager.getValuesForCode(true,"RAHUL","QTRACK");

        System.out.println("resultRt:" + resultRt);

        Assert.isNull(resultRt);
    }

    /**
     * Tests getValuesForCode Method for Invalid Code.
     */
    @Test
    void getValuesForCodeMethodTestforInvalidCode() throws Exception {

        RefTableData resultRt = referenceTableManager.getValuesForCode(true,"GENDER","X");

        System.out.println("resultRt:" + resultRt);

        Assert.isNull(resultRt);
    }
}
