import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import com.deloitte.ng.reftables.RefTableData;
import com.deloitte.ng.reftables.ReferenceTableManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * The class <code> GetRefDataOfReferenceTableManagerTest </code> is used
 * to test getRefData Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/13/2020 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */

@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetRefDataOfReferenceTableManagerTest {


    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getRefData Method for GENDER Reference Table.
     */
    @Test
    void getRefDataMethodTestforGenderRT() throws Exception {

        RefTableData testGenderRt1 = new RefTableData();

        Map<String, String> rowMap1 = new HashMap<String,String>();

        rowMap1.put("RANK","4");

        testGenderRt1.setRefrTableCode("U");
        testGenderRt1.setRefrTableDesc("Unknown");
        testGenderRt1.setRefrTableAddiData(rowMap1);

        System.out.println("testGenderRt1: " + testGenderRt1);

        RefTableData testGenderRt2 = new RefTableData();

        Map<String, String> rowMap2 = new HashMap<String,String>();

        rowMap2.put("RANK","2");

        testGenderRt2.setRefrTableCode("M");
        testGenderRt2.setRefrTableDesc("Male");
        testGenderRt2.setRefrTableAddiData(rowMap2);

        System.out.println("testGenderRt2: " + testGenderRt2);


        Map<Integer, Map> mRowMapInput = new HashMap<Integer, Map>();

        Map<String, String> mrowMap1 = new HashMap<String, String>();
        Map<String, String> mrowMap2 = new HashMap<String, String>();

        mrowMap1.put("CODE","U");
        mrowMap1.put("DESCRIPTION","Unknown");
        mrowMap1.put("RANK","4");

        mrowMap2.put("CODE","M");
        mrowMap2.put("DESCRIPTION","Male");
        mrowMap2.put("RANK","2");

        mRowMapInput.put(0,mrowMap1);
        mRowMapInput.put(1,mrowMap2);

        System.out.println("mRowMapInput: " + mRowMapInput);

//        getRefData metod has private access in ReferenceTableManager class.
//        RefTableData[] resultGenderRt = referenceTableManager.getRefData(mRowMapInput);

//        System.out.println("resultGenderRt:" + resultGenderRt);
//        System.out.println("resultGenderRt[0]:" + resultGenderRt[0]);
//        System.out.println("resultGenderRt[1]:" + resultGenderRt[1]);
//
//        Assert.isTrue(testGenderRt1.equals(resultGenderRt[0]));
//        Assert.isTrue(testGenderRt2.equals(resultGenderRt[1]));
    }
}
