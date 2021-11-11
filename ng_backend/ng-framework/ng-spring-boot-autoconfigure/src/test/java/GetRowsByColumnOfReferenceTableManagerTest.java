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
 * The class <code> GetRowsByColumnOfReferenceTableManagerTest </code> is used
 * to test getRowsByColumn Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/13/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetRowsByColumnOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getRowsByColumn Method for GENDER Reference Table.
     */
    @Test
    void getRowsByColumnMethodTestforGenderRT() throws Exception {

        RefTableData testGenderData1 = new RefTableData();
        RefTableData testGenderData2 = new RefTableData();
        RefTableData testGenderData3 = new RefTableData();

        Map<String, RefTableData> testGenderRt = new HashMap<String, RefTableData>();

        Map<String, String> rowMap1 = new HashMap<String, String>();
        Map<String, String> rowMap2 = new HashMap<String, String>();
        Map<String, String> rowMap3 = new HashMap<String, String>();

        rowMap1.put("RANK","3");
        rowMap2.put("RANK","2");
        rowMap3.put("RANK","4");

        testGenderData1.setRefrTableCode("F");
        testGenderData1.setRefrTableDesc("Female");
        testGenderData1.setRefrTableAddiData(rowMap1);

        testGenderData2.setRefrTableCode("M");
        testGenderData2.setRefrTableDesc("Male");
        testGenderData2.setRefrTableAddiData(rowMap2);

        testGenderData3.setRefrTableCode("U");
        testGenderData3.setRefrTableDesc("Unknown");
        testGenderData3.setRefrTableAddiData(rowMap3);

        System.out.println("testGenderData1: " + testGenderData1);
        System.out.println("testGenderData2: " + testGenderData2);
        System.out.println("testGenderData3: " + testGenderData3);

        RefTableData[] resultGenderRt = referenceTableManager.getRowsByColumn("GENDER","CODE");

        System.out.println("resultGenderRt[0]:" + resultGenderRt[0]);
        System.out.println("resultGenderRt[1]:" + resultGenderRt[1]);
        System.out.println("resultGenderRt[2]:" + resultGenderRt[2]);

        Assert.isTrue(testGenderData1.equals(resultGenderRt[0]));
        Assert.isTrue(testGenderData2.equals(resultGenderRt[1]));
        Assert.isTrue(testGenderData3.equals(resultGenderRt[2]));
    }

    /**
     * Tests getRowsByColumn Method for APPSOPDUE Reference Table.
     */
    @Test
    void getRowsByColumnMethodTestforAppsopdueRT() throws Exception {

        RefTableData testAppsopdueData1 = new RefTableData();
        RefTableData testAppsopdueData2 = new RefTableData();
        RefTableData testAppsopdueData3 = new RefTableData();
        RefTableData testAppsopdueData4 = new RefTableData();
        RefTableData testAppsopdueData5 = new RefTableData();


        Map<String, String> rowMap1 = new HashMap<String, String>();
        Map<String, String> rowMap2 = new HashMap<String, String>();
        Map<String, String> rowMap3 = new HashMap<String, String>();
        Map<String, String> rowMap4 = new HashMap<String, String>();
        Map<String, String> rowMap5 = new HashMap<String, String>();

        rowMap1.put("APPRCVDDT","0");
        rowMap1.put("POLICYSOP","30");

        rowMap2.put("APPRCVDDT","1");
        rowMap2.put("POLICYSOP","60");

        rowMap3.put("APPRCVDDT","0");
        rowMap3.put("POLICYSOP","7");

        rowMap4.put("APPRCVDDT","1");
        rowMap4.put("POLICYSOP","10");

        rowMap5.put("APPRCVDDT","1");
        rowMap5.put("POLICYSOP","45");

        testAppsopdueData1.setRefrTableCode("CC");
        testAppsopdueData1.setRefrTableDesc("30");
        testAppsopdueData1.setRefrTableAddiData(rowMap1);


        testAppsopdueData2.setRefrTableCode("DISABLEDNOTAGED");
        testAppsopdueData2.setRefrTableDesc("59");
        testAppsopdueData2.setRefrTableAddiData(rowMap2);


        testAppsopdueData3.setRefrTableCode("EXPEDITEDFS");
        testAppsopdueData3.setRefrTableDesc("7");
        testAppsopdueData3.setRefrTableAddiData(rowMap3);


        testAppsopdueData4.setRefrTableCode("QTRACK");
        testAppsopdueData4.setRefrTableDesc("9");
        testAppsopdueData4.setRefrTableAddiData(rowMap4);


        testAppsopdueData5.setRefrTableCode("TF");
        testAppsopdueData5.setRefrTableDesc("44");
        testAppsopdueData5.setRefrTableAddiData(rowMap5);


        System.out.println("testAppsopdueData1: " + testAppsopdueData1);
        System.out.println("testAppsopdueData2: " + testAppsopdueData2);
        System.out.println("testAppsopdueData3: " + testAppsopdueData3);
        System.out.println("testAppsopdueData4: " + testAppsopdueData4);
        System.out.println("testAppsopdueData5: " + testAppsopdueData5);

        RefTableData[] resultGenderRt = referenceTableManager.getRowsByColumn("APPSOPDUE","CODE");

        System.out.println("resultGenderRt[0]:" + resultGenderRt[0]);
        System.out.println("resultGenderRt[1]:" + resultGenderRt[1]);
        System.out.println("resultGenderRt[2]:" + resultGenderRt[2]);
        System.out.println("resultGenderRt[3]:" + resultGenderRt[3]);
        System.out.println("resultGenderRt[4]:" + resultGenderRt[4]);


        Assert.isTrue(testAppsopdueData1.equals(resultGenderRt[0]));
        Assert.isTrue(testAppsopdueData2.equals(resultGenderRt[1]));
        Assert.isTrue(testAppsopdueData3.equals(resultGenderRt[2]));
        Assert.isTrue(testAppsopdueData4.equals(resultGenderRt[3]));
        Assert.isTrue(testAppsopdueData5.equals(resultGenderRt[4]));
    }

    /**
     * Tests getRowsByColumn Method for Invalid Reference Table name.
     */
    @Test
    void getRowsByColumnMethodTestforInvalidRT() throws Exception {

        Exception testRtException = new Exception("0");

        System.out.println("testRtException:" + testRtException.getMessage());

        RefTableData[] resultRt = null;
        Exception resultRtException = null;

        try {
            resultRt = referenceTableManager.getRowsByColumn("RAHUL", "CODE");
            System.out.println("resultRt[0]:" + resultRt[0]);
        } catch (Exception e){
            resultRtException = e;
            System.out.println("resultRtException: " + resultRtException.getMessage());
        }

        Assert.isTrue(testRtException.getMessage().equals(resultRtException.getMessage() ));
    }

    /**
     * Tests getRowsByColumn Method for Invalid column name.
     */
    @Test
    void getRowsByColumnMethodTestforInvalidColumn() throws Exception {

        Exception testRtException = new Exception("0");

        System.out.println("testRtException:" + testRtException.getMessage());

        RefTableData[] resultRt = null;
        Exception resultRtException = null;

        try {
            resultRt = referenceTableManager.getRowsByColumn("GENDER", "RAHUL");
            System.out.println("resultRt[0]:" + resultRt[0]);
        } catch (Exception e){
            resultRtException = e;
            System.out.println("resultRtException: " + resultRtException.getMessage());
        }

        Assert.isTrue(testRtException.getMessage().equals(resultRtException.getMessage() ));
    }

}


