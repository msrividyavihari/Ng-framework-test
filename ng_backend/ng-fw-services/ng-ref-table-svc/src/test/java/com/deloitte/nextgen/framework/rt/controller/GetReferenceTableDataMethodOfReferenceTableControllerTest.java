package com.deloitte.nextgen.framework.rt.controller;

import com.deloitte.nextgen.framework.rt.dao.ReferenceTableDAOImpl;
import com.deloitte.nextgen.framework.rt.service.ReferenceTableServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.*;


/**
 * The class <code> GetReferenceTableDataMethodOfReferenceTableControllerTest </code> is used
 * to test getReferenceTableData Method Of ReferenceTableControllerTest class.
 *
 * @author rarathore on 09/08/2020 3:15 PM
 * @project ng-ref-table-svc
 */

@SpringBootTest(classes = {ReferenceTableController.class, ReferenceTableServiceImpl.class,
        ReferenceTableDAOImpl.class,})
public class GetReferenceTableDataMethodOfReferenceTableControllerTest {

    @Autowired
    private ReferenceTableController referenceTableController;

    //    @BeforeEach
    public void prepare() {

    }

/**
 * Tests getReferenceTableData Method for GENDER and DATATYPE Reference Tables.
 */
    @Test
    public void getReferenceTableDataMethodTestforGenderAndDataTypeRt() throws Exception {

        Map<String, Set> testRTData = new HashMap<>();
        Set<Map<String, String>> sRowMap1 = new HashSet<>();

        Map<String, String> rowMap1 = new HashMap<>();
        Map<String, String> rowMap2 = new HashMap<>();
        Map<String, String> rowMap3 = new HashMap<>();

        rowMap1.put("CODE","M");
        rowMap1.put("DESCRIPTION","Male");
        rowMap1.put("RANK","2");

        sRowMap1.add(rowMap1);

        rowMap2.put("CODE","F");
        rowMap2.put("DESCRIPTION","Female");
        rowMap2.put("RANK","3");

        sRowMap1.add(rowMap2);

        rowMap3.put("CODE","U");
        rowMap3.put("DESCRIPTION","Unknown");
        rowMap3.put("RANK","4");

        sRowMap1.add(rowMap3);

        testRTData.put("GENDER",sRowMap1);


        Set<Map<String, String>> sRowMap2 = new HashSet<>();

        Map<String, String> rowMap21 = new HashMap<>();
        Map<String, String> rowMap22 = new HashMap<>();
        Map<String, String> rowMap23 = new HashMap<>();
        Map<String, String> rowMap24 = new HashMap<>();
        Map<String, String> rowMap25 = new HashMap<>();

        rowMap21.put("CODE","1");
        rowMap21.put("DESCRIPTION","Text");

        sRowMap2.add(rowMap21);

        rowMap22.put("CODE","2");
        rowMap22.put("DESCRIPTION","Decimal");

        sRowMap2.add(rowMap22);

        rowMap23.put("CODE","3");
        rowMap23.put("DESCRIPTION","Number");

        sRowMap2.add(rowMap23);

        rowMap24.put("CODE","4");
        rowMap24.put("DESCRIPTION","Date");

        sRowMap2.add(rowMap24);

        rowMap25.put("CODE","5");
        rowMap25.put("DESCRIPTION","Variable Decimal");

        sRowMap2.add(rowMap25);

        testRTData.put("DATATYPE",sRowMap2);

        System.out.println("testRTData: " + testRTData);

        List<String> referenceTableNames = new ArrayList<String>();

        referenceTableNames.add("GENDER");
        referenceTableNames.add("DATATYPE");

        Map responseRTData = referenceTableController.getReferenceTableData(referenceTableNames);

        System.out.println("responseRTData: " + responseRTData);

        Assert.isTrue (testRTData.equals(responseRTData));

    }

    /**
     * Tests getReferenceTableData Method for one valid Reference Table and one Invalid Reference Table name.
     */
    @Test
    public void getReferenceTableDataMethodTestforOneValidAndOneInvalidRt() throws Exception {

        Exception testRTException = new Exception("java.lang.NullPointerException");

        System.out.println("testRTException:" + testRTException.getMessage());

        List<String> referenceTableNames = new ArrayList<String>();

        referenceTableNames.add("GENDER");
        referenceTableNames.add("RAHUL");

        Map responseRTData = null;

        Exception responseRTException = null;

        try {
            responseRTData = referenceTableController.getReferenceTableData(referenceTableNames);
        } catch (Exception e){
            responseRTException = e;
            System.out.println("responseRTException: " + e);
        }

        System.out.println("responseRTException:" + responseRTException);
        System.out.println("responseRTData:" + responseRTData);

        Assert.isNull (responseRTException.getMessage());
    }

    /**
     * Tests getReferenceTableData Method for Invalid Reference Table name.
     */
    @Test
    public void getReferenceTableDataMethodTestforInvalidRt() throws Exception {

        Exception testRTException = new Exception("java.lang.NullPointerException");

        System.out.println("testRTException:" + testRTException.getMessage());

        List<String> referenceTableNames = new ArrayList<String>();

        referenceTableNames.add("RAHUL");

        Map responseRTData = null;

        Exception responseRTException = null;

        try {
            responseRTData = referenceTableController.getReferenceTableData(referenceTableNames);
        } catch (Exception e){
            responseRTException = e;
            System.out.println("responseRTException: " + e);
        }

        System.out.println("responseRTException:" + responseRTException);
        System.out.println("responseRTData:" + responseRTData);

        Assert.isNull (responseRTException.getMessage());
    }

}
