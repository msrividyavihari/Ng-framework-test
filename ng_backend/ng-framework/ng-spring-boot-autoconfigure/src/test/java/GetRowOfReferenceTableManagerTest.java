import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import com.deloitte.ng.reftables.RefTableData;
import com.deloitte.ng.reftables.ReferenceTableManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * The class <code> GetRowOfReferenceTableManagerTest </code> is used
 * to test getRow Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/08/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@Slf4j
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetRowOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getRow Method for filter of GENDER Reference Table.
     */
    @Test
    void getRowMethodTestforGenderRT() throws Exception {

        RefTableData testGenderMap = new RefTableData();

        Map<String, String> rowMap = new HashMap();

        rowMap.put("RANK","2");

        testGenderMap.setRefrTableCode("M");
        testGenderMap.setRefrTableDesc("Male");
        testGenderMap.setRefrTableAddiData(rowMap);

        System.out.println("testGenderMap:" + testGenderMap);


        Map aColumnMap = new HashMap();

        aColumnMap.put("CODE","M");
        aColumnMap.put("DESCRIPTION","Male");
        aColumnMap.put("RANK","2");

//        getRow method has private access in ReferenceTableManager class.
//        RefTableData resultGenderMap = referenceTableManager.getRow(aColumnMap);

//        System.out.println("resultGenderMap:" + resultGenderMap);
//
//        Assert.isTrue (testGenderMap.equals(resultGenderMap));
    }

    /**
     * Tests getRow Method for filter of APPSOPDUE Reference Table.
     */
    @Test
    void getRowMethodTestforAppsopdueRT() throws Exception {

        RefTableData testAppsopdueMap = new RefTableData();

        Map<String, String> rowMap = new HashMap();

        rowMap.put("APPRCVDDT","1");
        rowMap.put("POLICYSOP","45");

        testAppsopdueMap.setRefrTableCode("AGEDNOTQTRACK");
        testAppsopdueMap.setRefrTableDesc("44");
        testAppsopdueMap.setRefrTableAddiData(rowMap);

        System.out.println("testAppsopdueMap:" + testAppsopdueMap);


        Map aColumnMap = new HashMap();

        aColumnMap.put("CODE","AGEDNOTQTRACK");
        aColumnMap.put("DESCRIPTION","44");
        aColumnMap.put("APPRCVDDT","1");
        aColumnMap.put("POLICYSOP","45");

//        getRow method has private access in ReferenceTableManager class.
//        RefTableData resultAppsopdueMap = referenceTableManager.getRow(aColumnMap);
//
//        System.out.println("resultAppsopdueMap:" + resultAppsopdueMap);
//
//        Assert.isTrue (testAppsopdueMap.equals(resultAppsopdueMap));
    }
}
