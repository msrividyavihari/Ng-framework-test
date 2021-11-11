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
 * The class <code> GetMapOfReferenceTableManagerTest </code> is used
 * to test getMap Method Of ReferenceTableManager class.
 *
 * @author rarathore on 15/09/2021 8:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@Slf4j
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetMapOfReferenceTableManagerTest {
    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getMap Method for filter of GENDER Reference Table.
     */
    @Test
    void getMapMethodTestforGenderRT() throws Exception {

        Map<String, RefTableData> testGenderMap = new HashMap<>();

        RefTableData columnMap1 = new RefTableData();
        RefTableData columnMap2 = new RefTableData();

        Map<String, String> rowMap1 = new HashMap();
        Map<String, String> rowMap2 = new HashMap();

        rowMap1.put("RANK","2");
        rowMap2.put("RANK","3");

        columnMap1.setRefrTableCode("M");
        columnMap1.setRefrTableDesc("Male");
        columnMap1.setRefrTableAddiData(rowMap1);

        columnMap2.setRefrTableCode("F");
        columnMap2.setRefrTableDesc("Female");
        columnMap2.setRefrTableAddiData(rowMap2);

        testGenderMap.put("00002",columnMap1);
        testGenderMap.put("00003",columnMap2);

        System.out.println("testGenderMap:" + testGenderMap);


        Map aRowMap1 = new HashMap();
        Map aRowMap2 = new HashMap();
        Map aColumnMap = new HashMap();

        aRowMap1.put("CODE","M");
        aRowMap1.put("DESCRIPTION","Male");
        aRowMap1.put("RANK","2");

        aRowMap2.put("CODE","F");
        aRowMap2.put("DESCRIPTION","Female");
        aRowMap2.put("RANK","3");

        aColumnMap.put("00002",aRowMap1);
        aColumnMap.put("00003",aRowMap2);

        Map resultGenderMap = referenceTableManager.getMap(aColumnMap);

        System.out.println("resultGenderMap:" + resultGenderMap);

        Assert.isTrue (testGenderMap.equals(resultGenderMap));
    }

    /**
     * Tests getMap Method for filter of APPSOPDUE Reference Table.
     */
    @Test
    void getMapMethodTestforAppsopdueRT() throws Exception {

        Map<String, RefTableData> testAppsopdueMap = new HashMap<>();

        RefTableData columnMap1 = new RefTableData();
        RefTableData columnMap2 = new RefTableData();

        Map<String, String> rowMap1 = new HashMap();
        Map<String, String> rowMap2 = new HashMap();

        rowMap1.put("APPRCVDDT","1");
        rowMap1.put("POLICYSOP","45");

        rowMap2.put("APPRCVDDT","0");
        rowMap2.put("POLICYSOP","30");

        columnMap1.setRefrTableCode("AGEDNOTQTRACK");
        columnMap1.setRefrTableDesc("44");
        columnMap1.setRefrTableAddiData(rowMap1);

        columnMap2.setRefrTableCode("CC");
        columnMap2.setRefrTableDesc("30");
        columnMap2.setRefrTableAddiData(rowMap2);

        testAppsopdueMap.put("44",columnMap1);
        testAppsopdueMap.put("30",columnMap2);

        System.out.println("testAppsopdueMap:" + testAppsopdueMap);


        Map aRowMap1 = new HashMap();
        Map aRowMap2 = new HashMap();
        Map aColumnMap = new HashMap();

        aRowMap1.put("CODE","AGEDNOTQTRACK");
        aRowMap1.put("DESCRIPTION","44");
        aRowMap1.put("APPRCVDDT","1");
        aRowMap1.put("POLICYSOP","45");

        aRowMap2.put("CODE","CC");
        aRowMap2.put("DESCRIPTION","30");
        aRowMap2.put("APPRCVDDT","0");
        aRowMap2.put("POLICYSOP","30");

        aColumnMap.put("44",aRowMap1);
        aColumnMap.put("30",aRowMap2);

        Map resultAppsopdueMap = referenceTableManager.getMap(aColumnMap);

        System.out.println("resultAppsopdueMap:" + resultAppsopdueMap);

        Assert.isTrue (testAppsopdueMap.equals(resultAppsopdueMap));
    }
}
