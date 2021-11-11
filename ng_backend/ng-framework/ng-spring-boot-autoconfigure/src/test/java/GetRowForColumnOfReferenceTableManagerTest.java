import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import com.deloitte.ng.reftables.ReferenceTableManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The class <code> GetRowForColumnOfReferenceTableManagerTest </code> is used
 * to test getRowForColumn Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/13/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */

@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetRowForColumnOfReferenceTableManagerTest {


    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getRowForColumn Method for GENDER Reference Table.
     */
    @Test
    void getRowForColumnMethodTestforGenderRT() throws Exception {

        Map<String, String> testGenderRt = new HashMap<String,String>();

        testGenderRt.put("CODE","F");
        testGenderRt.put("DESCRIPTION","Female");
        testGenderRt.put("RANK","3");

        System.out.println("testGenderRt: " + testGenderRt);


        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Map resultGenderRt = referenceTableManager.getRowForColumn("GENDER","CODE","F",timestamp);

        System.out.println("resultGenderRt:" + resultGenderRt);

        Assert.isTrue(testGenderRt.equals(resultGenderRt));
    }

    /**
     * Tests getRowForColumn Method for APPSOPDUE Reference Table.
     */
    @Test
    void getRowForColumnMethodTestforAppsopdueRT() throws Exception {

        Map<String, String> testAppsopdueRt = new HashMap<String,String>();

        testAppsopdueRt.put("CODE","TF");
        testAppsopdueRt.put("DESCRIPTION","44");
        testAppsopdueRt.put("APPRCVDDT","1");
        testAppsopdueRt.put("POLICYSOP","45");

        System.out.println("testAppsopdueRt: " + testAppsopdueRt);


        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Map resultAppsopdueRt = referenceTableManager.getRowForColumn("APPSOPDUE","DESCRIPTION","44",timestamp);

        System.out.println("resultAppsopdueRt:" + resultAppsopdueRt);

        Assert.isTrue(testAppsopdueRt.equals(resultAppsopdueRt));
    }
}
