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
import java.util.HashSet;
import java.util.Set;

/**
 * The class <code> GetValuesByColumnNameOfReferenceTableManagerTest </code> is used
 * to test getValuesByColumnName Method Of ReferenceTableManager class.
 *
 * @author rarathore on 09/13/2021 3:15 PM
 * @project ng-spring-boot-autoconfigure
 */

@SpringBootTest(classes = {ReferenceTable.class, ReferenceTableAutoConfiguration.class, ApplicationProperties.class})
class GetValuesByColumnNameOfReferenceTableManagerTest {


    @Autowired
    private ReferenceTableManager referenceTableManager;

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests getValuesByColumnName Method for GENDER Reference Table.
     */
    @Test
    void getValuesByColumnNameMethodTestforGenderRT() throws Exception {

        Set testGenderSet = new HashSet();

        testGenderSet.add("2");
        testGenderSet.add("3");
        testGenderSet.add("4");

        System.out.println("testGenderSet: " + testGenderSet);

        Date date = new Date();
        Timestamp timetamp = new Timestamp(date.getTime());

        Set resultGenderSet = referenceTableManager.getValuesByColumnName("GENDER","RANK",timetamp);

        System.out.println("resultGenderSet:" + resultGenderSet);

        Assert.isTrue(testGenderSet.equals(resultGenderSet));
    }

    /**
     * Tests getValuesByColumnName Method for APPSOPDUE Reference Table.
     */
    @Test
    void getValuesByColumnNameMethodTestforAppsopdueRT() throws Exception {

        Set testAppsopdueSet = new HashSet();

        testAppsopdueSet.add("CC");
        testAppsopdueSet.add("DISABLEDNOTAGED");
        testAppsopdueSet.add("EXPEDITEDFS");
        testAppsopdueSet.add("QTRACK");
        testAppsopdueSet.add("TF");

        System.out.println("testAppsopdueSet: " + testAppsopdueSet);

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Set resultAppsopdueSet = referenceTableManager.getValuesByColumnName("APPSOPDUE","CODE",timestamp);

        System.out.println("resultAppsopdueSet:" + resultAppsopdueSet);

        Assert.isTrue(testAppsopdueSet.equals(resultAppsopdueSet));
    }

    /**
     * Tests getValuesByColumnName Method for Invalid column name.
     */
    @Test
    void getValuesByColumnNameMethodTestforInvalidColumnName() throws Exception {

        Set testSet = new HashSet();

        System.out.println("testSet:" + testSet);

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Set resultSet = referenceTableManager.getValuesByColumnName("GENDER","RAHUL",timestamp);

        System.out.println("resultSet:" + resultSet);

        Assert.isTrue(testSet.equals(resultSet));
    }
}
