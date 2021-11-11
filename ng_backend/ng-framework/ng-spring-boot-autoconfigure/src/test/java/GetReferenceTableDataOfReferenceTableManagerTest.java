import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * The class <code> GetReferenceTableDataOfReferenceTableManagerTest </code> is used
 * to test getReferenceTableData Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/08/2020 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */

@Slf4j
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetReferenceTableDataOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTable referenceTable;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getReferenceTableData Method for GENDER Reference Table.
     */
    @Test
    void getReferenceTableDataMethodTestforGenderRT() throws Exception {

        Map<String, Map<String, Map<String, String>>> testMapGender = new HashMap();
        Map<String, Map<String, String>> mRowMap1 = new HashMap();
        Map<String, Map<String, String>> mRowMap2 = new HashMap();

        Map<String, String> rowMap1 = new HashMap();
        Map<String, String> rowMap2 = new HashMap();
        Map<String, String> rowMap3 = new HashMap();

        rowMap1.put("CODE", "U");
        rowMap1.put("DESCRIPTION","Unknown");
        rowMap1.put("RANK","4");

        rowMap2.put("CODE", "F");
        rowMap2.put("DESCRIPTION","Female");
        rowMap2.put("RANK","3");

        rowMap3.put("CODE", "M");
        rowMap3.put("DESCRIPTION","Male");
        rowMap3.put("RANK","2");

        mRowMap1.put("U",rowMap1);
        mRowMap1.put("F",rowMap2);
        mRowMap1.put("M",rowMap3);

        mRowMap2.put("00004",rowMap1);
        mRowMap2.put("00003",rowMap2);
        mRowMap2.put("00002",rowMap3);

        testMapGender.put("unsorteddata",mRowMap1);
        testMapGender.put("sorteddata",mRowMap2);

        System.out.println("testMapGender:" + testMapGender);

        Map resultMapGender = referenceTable.getReferenceTableData("GENDER");

        System.out.println("resultMapGender:" + resultMapGender);

        Assert.isTrue (testMapGender.equals(resultMapGender));
    }

    /**
     * Tests getReferenceTableData Method for APPSOPDUE Reference Table.
     */
    @Test
    void getReferenceTableMethodTestforAppsopdueRt() throws Exception {

        Map<String, Map<String, Map<String, String>>> testMapAppsopdue = new HashMap();
        Map<String, Map<String, String>> mRowMap1 = new HashMap();
        Map<String, Map<String, String>> mRowMap2 = new HashMap();


        Map<String, String> rowMap1 = new HashMap<>();
        Map<String, String> rowMap2 = new HashMap<>();
        Map<String, String> rowMap3 = new HashMap<>();
        Map<String, String> rowMap4 = new HashMap<>();
        Map<String, String> rowMap5 = new HashMap<>();
        Map<String, String> rowMap6 = new HashMap<>();
        Map<String, String> rowMap7 = new HashMap<>();
        Map<String, String> rowMap8 = new HashMap<>();
        Map<String, String> rowMap9 = new HashMap<>();
        Map<String, String> rowMap10 = new HashMap<>();
        Map<String, String> rowMap11 = new HashMap<>();
        Map<String, String> rowMap12 = new HashMap<>();
        Map<String, String> rowMap13 = new HashMap<>();
        Map<String, String> rowMap14 = new HashMap<>();


        rowMap1.put("CODE","LI");
        rowMap1.put("DESCRIPTION","44");
        rowMap1.put("APPRCVDDT","1");
        rowMap1.put("POLICYSOP","45");


        rowMap2.put("CODE","MA");
        rowMap2.put("DESCRIPTION","44");
        rowMap2.put("APPRCVDDT","1");
        rowMap2.put("POLICYSOP","45");


        rowMap3.put("CODE","LAD");
        rowMap3.put("DESCRIPTION","44");
        rowMap3.put("APPRCVDDT","1");
        rowMap3.put("POLICYSOP","45");


        rowMap4.put("CODE","FS");
        rowMap4.put("DESCRIPTION","30");
        rowMap4.put("APPRCVDDT","0");
        rowMap4.put("POLICYSOP","30");


        rowMap5.put("CODE","TF");
        rowMap5.put("DESCRIPTION","44");
        rowMap5.put("APPRCVDDT","1");
        rowMap5.put("POLICYSOP","45");


        rowMap6.put("CODE","CC");
        rowMap6.put("DESCRIPTION","30");
        rowMap6.put("APPRCVDDT","0");
        rowMap6.put("POLICYSOP","30");


        rowMap7.put("CODE","WC");
        rowMap7.put("DESCRIPTION","9");
        rowMap7.put("APPRCVDDT","1");
        rowMap7.put("POLICYSOP","10");


        rowMap8.put("CODE","PREGNANCY");
        rowMap8.put("DESCRIPTION","9");
        rowMap8.put("APPRCVDDT","1");
        rowMap8.put("POLICYSOP","10");


        rowMap9.put("CODE","QTRACK");
        rowMap9.put("DESCRIPTION","9");
        rowMap9.put("APPRCVDDT","1");
        rowMap9.put("POLICYSOP","10");


        rowMap10.put("CODE","NADISABLED");
        rowMap10.put("DESCRIPTION","59");
        rowMap10.put("APPRCVDDT","1");
        rowMap10.put("POLICYSOP","60");


        rowMap11.put("CODE","DISABLEDNOTAGED");
        rowMap11.put("DESCRIPTION","59");
        rowMap11.put("APPRCVDDT","1");
        rowMap11.put("POLICYSOP","60");


        rowMap12.put("CODE","AGEDNOTQTRACK");
        rowMap12.put("DESCRIPTION","44");
        rowMap12.put("APPRCVDDT","1");
        rowMap12.put("POLICYSOP","45");


        rowMap13.put("CODE","EXPEDITEDFS");
        rowMap13.put("DESCRIPTION","7");
        rowMap13.put("APPRCVDDT","0");
        rowMap13.put("POLICYSOP","7");


        rowMap14.put("CODE","CU19PARENTCARETAKER");
        rowMap14.put("DESCRIPTION","59");
        rowMap14.put("APPRCVDDT","1");
        rowMap14.put("POLICYSOP","60");


        mRowMap1.put("LI",rowMap1);
        mRowMap1.put("MA",rowMap2);
        mRowMap1.put("LAD",rowMap3);
        mRowMap1.put("FS",rowMap4);
        mRowMap1.put("TF",rowMap5);
        mRowMap1.put("CC",rowMap6);
        mRowMap1.put("WC",rowMap7);
        mRowMap1.put("PREGNANCY",rowMap8);
        mRowMap1.put("QTRACK",rowMap9);
        mRowMap1.put("NADISABLED",rowMap10);
        mRowMap1.put("DISABLEDNOTAGED",rowMap11);
        mRowMap1.put("AGEDNOTQTRACK",rowMap12);
        mRowMap1.put("EXPEDITEDFS",rowMap13);
        mRowMap1.put("CU19PARENTCARETAKER",rowMap14);


        mRowMap2.put("30",rowMap6);
        mRowMap2.put("44",rowMap5);
        mRowMap2.put("59",rowMap11);
        mRowMap2.put("7",rowMap13);
        mRowMap2.put("9",rowMap9);

        testMapAppsopdue.put("unsorteddata",mRowMap1);
        testMapAppsopdue.put("sorteddata",mRowMap2);

        System.out.println("testMapAppsopdue: " + testMapAppsopdue);

        Map resultMapAppsopdue = referenceTable.getReferenceTableData("APPSOPDUE");

        System.out.println("resultMapAppsopdue: " + resultMapAppsopdue);


        Assert.isTrue (testMapAppsopdue.equals(resultMapAppsopdue));

    }

    /**
     * Tests getReferenceTableData Method for Invalid Reference Table name.
     */
    @Test
    void getReferenceTableDataMethodTestWithInvalidRTName() throws Exception {

        Exception testRTException = new Exception("Reference table RAHUL not found");

        System.out.println("testRTException:" + testRTException.getMessage());

        Map resultRTResponse = null;

        Exception resultRTException = null;

        try {
            resultRTResponse = referenceTable.getReferenceTableData("RAHUL");
        } catch (FwApplicationException e){
            resultRTException = e;
            System.out.println("resultRTException: " + e);
        }

        System.out.println("resultRTResponse:" + resultRTResponse);

        System.out.println("resultRTException:" + resultRTException.getMessage());

        Assert.isTrue ((testRTException.getMessage()).equals(resultRTException.getMessage()));

    }

}
