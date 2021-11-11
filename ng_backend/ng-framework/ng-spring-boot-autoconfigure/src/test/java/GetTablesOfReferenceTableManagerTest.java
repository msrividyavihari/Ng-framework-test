import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import com.deloitte.ng.reftables.ReferenceTableManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.*;

/**
 * The class <code> GetTablesOfReferenceTableManagerTest </code> is used
 * to test getTables Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/13/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetTablesOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getTables Method for GENDER Reference Table.
     */
    @Test
    void getTablesMethodTestforGenderRT() throws Exception {

        Map<String, ReferenceTableVersionTest> testGenderMap = new HashMap<String, ReferenceTableVersionTest>();

        String version = "1";

        ReferenceTableVersionTest referenceTableVersionTest = new ReferenceTableVersionTest(version);

        referenceTableVersionTest.setEFFBEGINDATE("2001-01-01 00:00:00");

        Set<Map<String, String>> refData = new HashSet<>();

        Map<String, String> rowMap1 = new HashMap<>();
        Map<String, String> rowMap2 = new HashMap<>();
        Map<String, String> rowMap3 = new HashMap<>();

        rowMap1.put("CODE","M");
        rowMap1.put("DESCRIPTION","Male");
        rowMap1.put("RANK","2");

        refData.add(rowMap1);

        rowMap2.put("CODE","F");
        rowMap2.put("DESCRIPTION","Female");
        rowMap2.put("RANK","3");

        refData.add(rowMap2);

        rowMap3.put("CODE","U");
        rowMap3.put("DESCRIPTION","Unknown");
        rowMap3.put("RANK","4");

        refData.add(rowMap3);

        referenceTableVersionTest.setDATA(refData);

        testGenderMap.put("1",referenceTableVersionTest);

        System.out.println("testGenderMap: " + testGenderMap);



        Map<String, ReferenceTableVersionTest> testDataTypeMap = new HashMap<String, ReferenceTableVersionTest>();

        String version1 = "1";

        ReferenceTableVersionTest referenceTableVersionTestDt = new ReferenceTableVersionTest(version1);

        referenceTableVersionTestDt.setEFFBEGINDATE("2001-01-01 00:00:00");

        Set<Map<String, String>> refDataDt = new HashSet<>();

        Map<String, String> rowMapDt1 = new HashMap<>();
        Map<String, String> rowMapDt2 = new HashMap<>();
        Map<String, String> rowMapDt3 = new HashMap<>();
        Map<String, String> rowMapDt4 = new HashMap<>();
        Map<String, String> rowMapDt5 = new HashMap<>();

        rowMapDt1.put("CODE","1");
        rowMapDt1.put("DESCRIPTION","Text");

        refDataDt.add(rowMapDt1);

        rowMapDt2.put("CODE","2");
        rowMapDt2.put("DESCRIPTION","Number");

        refDataDt.add(rowMapDt2);

        rowMapDt3.put("CODE","3");
        rowMapDt3.put("DESCRIPTION","Decimal");

        refDataDt.add(rowMapDt3);

        rowMapDt4.put("CODE","4");
        rowMapDt4.put("DESCRIPTION","Date");

        refDataDt.add(rowMapDt4);

        rowMapDt5.put("CODE","5");
        rowMapDt5.put("DESCRIPTION","Variable Decimal");

        refDataDt.add(rowMapDt5);

        referenceTableVersionTestDt.setDATA(refData);

        testDataTypeMap.put("1",referenceTableVersionTestDt);

        System.out.println("testDataTypeMap: " + testDataTypeMap);


        Map<String, Map> testMapRt = new HashMap<String, Map>();

        testMapRt.put("GENDER",testGenderMap);
        testMapRt.put("DATATYPE",testDataTypeMap);

        System.out.println("testMapRt: " + testMapRt);

        List<String> aRefTableNames = new ArrayList<>();

        aRefTableNames.add("GENDER");
        aRefTableNames.add("DATATYPE");

        Map resultMapRt = referenceTableManager.getTables(aRefTableNames);

        System.out.println("resultMapRt:" + resultMapRt);

        Assert.isTrue(testMapRt.equals(resultMapRt));
    }

    /**
     * Tests getTables Method for Invalid Reference Table name.
     */
    @Test
    void getTablesMethodTestforInvalidRT() throws Exception {

        Map<String, Map> testMapRT = new HashMap<String, Map>();

        testMapRT.put("RAHUL",null);

        System.out.println("testMapRT:" + testMapRT);


        List<String> aRefTableNames = new ArrayList<>();

        aRefTableNames.add("RAHUL");

        Map resultMapRT = referenceTableManager.getTables(aRefTableNames);

        System.out.println("resultMapRT:" + resultMapRT);

        Assert.isTrue(testMapRT.equals(resultMapRT));
    }
}
