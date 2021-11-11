import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import com.deloitte.ng.reftables.ReferenceTableManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The class <code> GetTableOfReferenceTableManagerTest </code> is used
 * to test getTable Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/13/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@Slf4j
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetTableOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTableManager referenceTableManager;

//    @BeforeEach
    void prepare() {

    }

    /**
     * Tests getTable Method for GENDER Reference Table.
     */
    @Test
    void getTableMethodTestforGenderRT() throws Exception {

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

        Map resultGenderMap = referenceTableManager.getTable("GENDER");

        System.out.println("resultGenderMap:" + resultGenderMap);

        Assert.isTrue(testGenderMap.equals(resultGenderMap));
    }
}
