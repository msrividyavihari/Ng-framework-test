import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
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
 * The class <code> GetReferenceTableRowOfReferenceTableManagerTest </code> is used
 * to test getReferenceTableRow Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/13/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetReferenceTableRowOfReferenceTableManagerTest {


    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getReferenceTableRow Method for GENDER Reference Table.
     */
    @Test
    void getReferenceTableRowMethodTestforGenderRT() throws Exception {

        RefTableData testGenderData1 = new RefTableData();
        RefTableData testGenderData2 = new RefTableData();
        RefTableData testGenderData3 = new RefTableData();

        Map<String, RefTableData> testGenderRt = new HashMap<String, RefTableData>();

        Map<String, String> rowMap1 = new HashMap<String, String>();
        Map<String, String> rowMap2 = new HashMap<String, String>();
        Map<String, String> rowMap3 = new HashMap<String, String>();

        rowMap1.put("RANK","2");
        rowMap2.put("RANK","3");
        rowMap3.put("RANK","4");

        testGenderData1.setRefrTableCode("M");
        testGenderData1.setRefrTableDesc("Male");
        testGenderData1.setRefrTableAddiData(rowMap1);

        testGenderData2.setRefrTableCode("F");
        testGenderData2.setRefrTableDesc("Female");
        testGenderData2.setRefrTableAddiData(rowMap2);

        testGenderData3.setRefrTableCode("U");
        testGenderData3.setRefrTableDesc("Unknown");
        testGenderData3.setRefrTableAddiData(rowMap3);

        testGenderRt.put("00002",testGenderData1);
        testGenderRt.put("00003",testGenderData2);
        testGenderRt.put("00004",testGenderData3);

        System.out.println("testGenderRt: " + testGenderRt);

        Map resultGenderRt = referenceTableManager.getReferenceTableRow(true,"GENDER");

        System.out.println("resultGenderRt:" + resultGenderRt);

        Assert.isTrue(testGenderRt.equals(resultGenderRt));
    }

    /**
     * Tests getReferenceTableRow Method for APPSOPDUE Reference Table.
     */
    @Test
    void getReferenceTableRowMethodTestforAppsopdueRT() throws Exception {

        RefTableData testAppsopdueData1 = new RefTableData();
        RefTableData testAppsopdueData2 = new RefTableData();
        RefTableData testAppsopdueData3 = new RefTableData();
        RefTableData testAppsopdueData4 = new RefTableData();
        RefTableData testAppsopdueData5 = new RefTableData();

        Map<String, RefTableData> testAppsopdueRt = new HashMap<String, RefTableData>();

        Map<String, String> rowMap1 = new HashMap<String, String>();
        Map<String, String> rowMap2 = new HashMap<String, String>();
        Map<String, String> rowMap3 = new HashMap<String, String>();
        Map<String, String> rowMap4 = new HashMap<String, String>();
        Map<String, String> rowMap5 = new HashMap<String, String>();

        rowMap1.put("APPRCVDDT","0");
        rowMap1.put("POLICYSOP","30");

        rowMap2.put("APPRCVDDT","1");
        rowMap2.put("POLICYSOP","45");

        rowMap3.put("APPRCVDDT","1");
        rowMap3.put("POLICYSOP","60");

        rowMap4.put("APPRCVDDT","0");
        rowMap4.put("POLICYSOP","7");

        rowMap5.put("APPRCVDDT","1");
        rowMap5.put("POLICYSOP","10");

        testAppsopdueData1.setRefrTableCode("CC");
        testAppsopdueData1.setRefrTableDesc("30");
        testAppsopdueData1.setRefrTableAddiData(rowMap1);

        testAppsopdueData2.setRefrTableCode("TF");
        testAppsopdueData2.setRefrTableDesc("44");
        testAppsopdueData2.setRefrTableAddiData(rowMap2);

        testAppsopdueData3.setRefrTableCode("DISABLEDNOTAGED");
        testAppsopdueData3.setRefrTableDesc("59");
        testAppsopdueData3.setRefrTableAddiData(rowMap3);

        testAppsopdueData4.setRefrTableCode("EXPEDITEDFS");
        testAppsopdueData4.setRefrTableDesc("7");
        testAppsopdueData4.setRefrTableAddiData(rowMap4);

        testAppsopdueData5.setRefrTableCode("QTRACK");
        testAppsopdueData5.setRefrTableDesc("9");
        testAppsopdueData5.setRefrTableAddiData(rowMap5);

        testAppsopdueRt.put("30",testAppsopdueData1);
        testAppsopdueRt.put("44",testAppsopdueData2);
        testAppsopdueRt.put("59",testAppsopdueData3);
        testAppsopdueRt.put("7",testAppsopdueData4);
        testAppsopdueRt.put("9",testAppsopdueData5);

        System.out.println("testAppsopdueRt: " + testAppsopdueRt);

        Map resultAppsopdueRt = referenceTableManager.getReferenceTableRow(true,"APPSOPDUE");

        System.out.println("resultAppsopdueRt:" + resultAppsopdueRt);

        Assert.isTrue(testAppsopdueRt.equals(resultAppsopdueRt));
    }

    /**
     * Tests getReferenceTableRow Method for Invalid Reference Table name.
     */
    @Test
    void getReferenceTableRowMethodTestforInvalidRT() throws Exception {

        Exception testRtException = new Exception("Reference table RAHUL not found");

        System.out.println("testRtException:" + testRtException.getMessage());


        Map resultRt = null;

        Exception resultRtException = null;

        try {
            resultRt = referenceTableManager.getReferenceTableRow(true, "RAHUL");
        } catch (FwApplicationException e){
                resultRtException = e;
                System.out.println("resultRtException: " + e);
            }

        System.out.println("resultRt:" + resultRt);

        Assert.isTrue ((testRtException.getMessage()).equals(resultRtException.getMessage()));

    }
}
