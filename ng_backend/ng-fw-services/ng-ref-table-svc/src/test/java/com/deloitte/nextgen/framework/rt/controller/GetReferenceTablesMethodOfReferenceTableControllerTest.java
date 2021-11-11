package com.deloitte.nextgen.framework.rt.controller;

import com.deloitte.nextgen.framework.rt.controller.ReferenceTableController;
import com.deloitte.nextgen.framework.rt.dao.ReferenceTableDAOImpl;
import com.deloitte.nextgen.framework.rt.service.ReferenceTableServiceImpl;
import com.deloitte.nextgen.framework.rt.vo.ReferenceTableVersion;
import com.deloitte.nextgen.framework.rt.vo.ResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.*;

/**
 * The class <code> GetReferenceTablesMethodOfReferenceTableControllerTest </code> is used
 * to test getReferenceTables Method Of ReferenceTableControllerTest class.
 *
 * @author rarathore on 09/08/2020 3:15 PM
 * @project ng-ref-table-svc
 */
@SpringBootTest(classes = {ReferenceTableController.class, ReferenceTableServiceImpl.class,
        ReferenceTableDAOImpl.class,})
public class GetReferenceTablesMethodOfReferenceTableControllerTest {


    @Autowired
    private ReferenceTableController referenceTableController;

    //    @BeforeEach
    public void prepare() {

    }
    /**
     * Tests getReferenceTables Method for GENDER and DATATYPE Reference Tables.
     */
    @Test
    public void getReferenceTablesMethodTestforGenderAndDataTypeRt() throws Exception {

        Map<String, ReferenceTableVersion> testGenderMap = new HashMap<String, ReferenceTableVersion>();

        String version = "1";

        ReferenceTableVersion referenceTableVersion = new ReferenceTableVersion(version);

        referenceTableVersion.setEffectiveBeginDate("2001-01-01 00:00:00");

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

        referenceTableVersion.setRefData(refData);

        testGenderMap.put("1",referenceTableVersion);

        System.out.println("testGenderMap: " + testGenderMap);


        Map<String, ReferenceTableVersion> testDataTypeMap = new HashMap<String, ReferenceTableVersion>();

        String version2 = "1";

        ReferenceTableVersion referenceTableVersion2 = new ReferenceTableVersion(version2);

        referenceTableVersion2.setEffectiveBeginDate("2001-01-01 00:00:00");

        Set<Map<String, String>> refData2 = new HashSet<>();

        Map<String, String> rowMap21 = new HashMap<>();
        Map<String, String> rowMap22 = new HashMap<>();
        Map<String, String> rowMap23 = new HashMap<>();
        Map<String, String> rowMap24 = new HashMap<>();
        Map<String, String> rowMap25 = new HashMap<>();

        rowMap21.put("CODE","1");
        rowMap21.put("DESCRIPTION","Text");

        refData2.add(rowMap21);

        rowMap22.put("CODE","2");
        rowMap22.put("DESCRIPTION","Decimal");

        refData2.add(rowMap22);

        rowMap23.put("CODE","3");
        rowMap23.put("DESCRIPTION","Number");

        refData2.add(rowMap23);


        rowMap24.put("CODE","4");
        rowMap24.put("DESCRIPTION","Date");

        refData2.add(rowMap24);

        rowMap25.put("CODE","5");
        rowMap25.put("DESCRIPTION","Variable Decimal");

        refData2.add(rowMap25);

        referenceTableVersion2.setRefData(refData2);

        testDataTypeMap.put("1",referenceTableVersion2);

        System.out.println("testDataTypeMap: " + testDataTypeMap);

        Map<String,Map> testMap = new HashMap<String, Map>();

        testMap.put("GENDER",testGenderMap);
        testMap.put("DATATYPE",testDataTypeMap);

        ResponseVO testResponseMap = new ResponseVO(testMap, "0");

        System.out.println("testResponseMap: " + testResponseMap);


        List<String> referenceTableNames = new ArrayList<String>();

        referenceTableNames.add("GENDER");
        referenceTableNames.add("DATATYPE");

        ResponseVO resultResponseMap = referenceTableController.getReferenceTables(referenceTableNames);

        System.out.println("resultResponseMap: " + resultResponseMap);


        Assert.isTrue (testResponseMap.equals(resultResponseMap));

    }

    /**
     * Tests getReferenceTables Method for one valid Reference Table name and one invalid Reference Table name.
     */
    @Test
    public void getReferenceTablesMethodTestforOneValidAndOneInvalidRt() throws Exception {

        Map<String, ReferenceTableVersion> testGenderMap = new HashMap<String, ReferenceTableVersion>();

        String version = "1";

        ReferenceTableVersion referenceTableVersion = new ReferenceTableVersion(version);

        referenceTableVersion.setEffectiveBeginDate("2001-01-01 00:00:00");

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

        referenceTableVersion.setRefData(refData);

        testGenderMap.put("1",referenceTableVersion);

        System.out.println("testGenderMap: " + testGenderMap);


        Map<String, ReferenceTableVersion> testInvalidRTMap = new HashMap<String, ReferenceTableVersion>();

        testInvalidRTMap = null;

        System.out.println("testInvalidRTMap: " + testInvalidRTMap);

        Map<String,Map> testMap = new HashMap<String, Map>();

        testMap.put("GENDER",testGenderMap);
        testMap.put("RAHUL",null);

        ResponseVO testResponseMap = new ResponseVO(testMap, "2");

        System.out.println("testResponseMap: " + testResponseMap);


        List<String> referenceTableNames = new ArrayList<String>();

        referenceTableNames.add("GENDER");
        referenceTableNames.add("RAHUL");

        ResponseVO resultResponseMap = referenceTableController.getReferenceTables(referenceTableNames);

        System.out.println("resultResponseMap: " + resultResponseMap);


        Assert.isTrue (testResponseMap.equals(resultResponseMap));

    }
    /**
     * Tests getReferenceTables Method for invalid Reference Table name.
     */
    @Test
    public void getReferenceTablesMethodTestforInvalidRt() throws Exception {

        Map<String, ReferenceTableVersion> testInvalidRTMap = new HashMap<String, ReferenceTableVersion>();

        System.out.println("testInvalidRTMap: " + testInvalidRTMap);

        Map<String,Map> testMap = new HashMap<String, Map>();

        testMap.put("GENTER",null);
        testMap.put("RAHUL",null);

        ResponseVO testResponseMap = new ResponseVO(testMap, "2");

        System.out.println("testResponseMap: " + testResponseMap);


        List<String> referenceTableNames = new ArrayList<String>();

        referenceTableNames.add("GENTER");
        referenceTableNames.add("RAHUL");

        ResponseVO resultResponseMap = referenceTableController.getReferenceTables(referenceTableNames);

        System.out.println("resultResponseMap: " + resultResponseMap);

        Assert.isTrue (testResponseMap.equals(resultResponseMap));

    }

}
