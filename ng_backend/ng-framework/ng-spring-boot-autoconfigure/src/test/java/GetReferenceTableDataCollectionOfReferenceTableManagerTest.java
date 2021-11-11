import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import com.deloitte.ng.reftables.RefTableData;
import com.deloitte.ng.reftables.ReferenceTableCollection;
import com.deloitte.ng.reftables.ReferenceTableManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The class <code> GetReferenceTableDataCollectionOfReferenceTableManagerTest </code> is used
 * to test getReferenceTableDataCollection Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/15/2021 8:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetReferenceTableDataCollectionOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getReferenceTableDataCollection Method for GENDER Reference Table.
     */
    @Test
    void getReferenceTableDataCollectionMethodTestforGenderRT() throws Exception {

        RefTableData testGenderRt1 = new RefTableData();

        Map<String, String> rowMap1 = new HashMap<String, String>();

        rowMap1.put("RANK","2");

        testGenderRt1.setRefrTableCode("M");
        testGenderRt1.setRefrTableDesc("Male");
        testGenderRt1.setRefrTableAddiData(rowMap1);

        RefTableData testGenderRt2 = new RefTableData();

        Map<String, String> rowMap2 = new HashMap<String, String>();

        rowMap2.put("RANK","3");

        testGenderRt2.setRefrTableCode("F");
        testGenderRt2.setRefrTableDesc("Female");
        testGenderRt2.setRefrTableAddiData(rowMap2);

        RefTableData testGenderRt3 = new RefTableData();

        Map<String, String> rowMap3 = new HashMap<String, String>();

        rowMap3.put("RANK","4");

        testGenderRt3.setRefrTableCode("U");
        testGenderRt3.setRefrTableDesc("Unknown");
        testGenderRt3.setRefrTableAddiData(rowMap3);

        System.out.println("testGenderRt1: " + testGenderRt1);
        System.out.println("testGenderRt2: " + testGenderRt2);
        System.out.println("testGenderRt3: " + testGenderRt3);


        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        ReferenceTableCollection resultGenderRt = referenceTableManager.getReferenceTableDataCollection("GENDER",timestamp);

        System.out.println("resultGenderRt:" + resultGenderRt.toString());

//        ReferenceTableCollection class does not have Getter and Setter methods.
//        Assert.isTrue(testGenderRt1.equals(resultGenderRt.getRefTableData()[0]));
//        Assert.isTrue(testGenderRt2.equals(resultGenderRt.getRefTableData()[1]));
//        Assert.isTrue(testGenderRt3.equals(resultGenderRt.getRefTableData()[2]));
    }

    /**
     * Tests getReferenceTableDataCollection Method for APPSOPDUE Reference Table.
     */
    @Test
    void getReferenceTableDataCollectionMethodTestforAppsopdueRT() throws Exception {

        RefTableData testAppsopdueRt1 = new RefTableData();

        Map<String, String> rowMap1 = new HashMap<String, String>();

        rowMap1.put("APPRCVDDT","0");
        rowMap1.put("POLICYSOP","30");

        testAppsopdueRt1.setRefrTableCode("CC");
        testAppsopdueRt1.setRefrTableDesc("30");
        testAppsopdueRt1.setRefrTableAddiData(rowMap1);

        RefTableData testAppsopdueRt2 = new RefTableData();

        Map<String, String> rowMap2 = new HashMap<String, String>();

        rowMap2.put("APPRCVDDT","1");
        rowMap2.put("POLICYSOP","45");

        testAppsopdueRt2.setRefrTableCode("TF");
        testAppsopdueRt2.setRefrTableDesc("44");
        testAppsopdueRt2.setRefrTableAddiData(rowMap2);

        RefTableData testAppsopdueRt3 = new RefTableData();

        Map<String, String> rowMap3 = new HashMap<String, String>();

        rowMap3.put("APPRCVDDT","1");
        rowMap3.put("POLICYSOP","60");

        testAppsopdueRt3.setRefrTableCode("DISABLEDNOTAGED");
        testAppsopdueRt3.setRefrTableDesc("59");
        testAppsopdueRt3.setRefrTableAddiData(rowMap3);

        RefTableData testAppsopdueRt4 = new RefTableData();

        Map<String, String> rowMap4 = new HashMap<String, String>();

        rowMap4.put("APPRCVDDT","0");
        rowMap4.put("POLICYSOP","7");

        testAppsopdueRt4.setRefrTableCode("EXPEDITEDFS");
        testAppsopdueRt4.setRefrTableDesc("7");
        testAppsopdueRt4.setRefrTableAddiData(rowMap4);

        RefTableData testAppsopdueRt5 = new RefTableData();

        Map<String, String> rowMap5 = new HashMap<String, String>();

        rowMap5.put("APPRCVDDT","1");
        rowMap5.put("POLICYSOP","10");

        testAppsopdueRt5.setRefrTableCode("QTRACK");
        testAppsopdueRt5.setRefrTableDesc("9");
        testAppsopdueRt5.setRefrTableAddiData(rowMap5);

        System.out.println("testAppsopdueRt1: " + testAppsopdueRt1);
        System.out.println("testAppsopdueRt2: " + testAppsopdueRt2);
        System.out.println("testAppsopdueRt3: " + testAppsopdueRt3);
        System.out.println("testAppsopdueRt4: " + testAppsopdueRt4);
        System.out.println("testAppsopdueRt5: " + testAppsopdueRt5);


        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        ReferenceTableCollection resultGenderRt = referenceTableManager.getReferenceTableDataCollection("APPSOPDUE",timestamp);

        System.out.println("resultGenderRt:" + resultGenderRt.toString());

        //        ReferenceTableCollection class does not have Getter and Setter methods.
//        Assert.isTrue(testAppsopdueRt1.equals(resultGenderRt.getRefTableData()[0]));
//        Assert.isTrue(testAppsopdueRt2.equals(resultGenderRt.getRefTableData()[1]));
//        Assert.isTrue(testAppsopdueRt3.equals(resultGenderRt.getRefTableData()[2]));
//        Assert.isTrue(testAppsopdueRt4.equals(resultGenderRt.getRefTableData()[3]));
//        Assert.isTrue(testAppsopdueRt5.equals(resultGenderRt.getRefTableData()[4]));
    }
}
