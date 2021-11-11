import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import com.deloitte.ng.reftables.ReferenceTableManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * The class <code> GetValueByColumnOfReferenceTableManagerTest </code> is used
 * to test getValueByColumn Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/13/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */
@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetValueByColumnOfReferenceTableManagerTest {

    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getValueByColumn Method for GENDER Reference Table.
     */
    @Test
    void getValueByColumnMethodTestforGenderRT() throws Exception {

        String testGenderRt = "M";

        System.out.println("testGenderRt:" + testGenderRt);

        String resultGenderRt = referenceTableManager.getValueByColumn("GENDER","M","CODE");

        System.out.println("resultGenderRt:" + resultGenderRt);

        Assert.isTrue(testGenderRt.equals(resultGenderRt));
    }

    /**
     * Tests getValueByColumn Method for APPSOPDUE Reference Table.
     */
    @Test
    void getValueByColumnMethodTestforAppsopdueRT() throws Exception {

        String testAppsopdueRt = "MA";

        System.out.println("testAppsopdueRt:" + testAppsopdueRt);

        String resultAppsopdueRt = referenceTableManager.getValueByColumn("APPSOPDUE","MA","CODE");

        System.out.println("resultGenderRt:" + resultAppsopdueRt);

        Assert.isTrue(testAppsopdueRt.equals(resultAppsopdueRt));
    }

    /**
     * Tests getValueByColumn Method for Invalid Column Code.
     */
    @Test
    void getValueByColumnMethodTestforInvalidColumnCode() throws Exception {

        String testRt = "";

        System.out.println("testRt:" + testRt);

        String resultRt = referenceTableManager.getValueByColumn("APPSOPDUE","XX","CODE");

        System.out.println("resultRt:" + resultRt);

        Assert.isTrue(testRt.equals(resultRt));
    }
}
