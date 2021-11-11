package com.deloitte.nextgen.framework.rt.controller;

import com.deloitte.nextgen.framework.rt.controller.ReferenceTableController;
import com.deloitte.nextgen.framework.rt.dao.ReferenceTableDAOImpl;
import com.deloitte.nextgen.framework.rt.service.ReferenceTableServiceImpl;
import com.deloitte.nextgen.framework.rt.vo.ReferenceTableVersion;
import com.deloitte.nextgen.framework.rt.vo.ResponseVO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The class <code> GetReferenceTableMethodOfReferenceTableControllerTest </code> is used
 * to test getReferenceTable Method Of ReferenceTableControllerTest class.
 *
 * @author rarathore on 09/08/2020 3:15 PM
 * @project ng-ref-table-svc
 */
@SpringBootTest(classes = {ReferenceTableController.class, ReferenceTableServiceImpl.class,
ReferenceTableDAOImpl.class,})
public class GetReferenceTableMethodOfReferenceTableControllerTest {

    @Autowired
    private ReferenceTableController referenceTableController;

//    @BeforeEach
    public void prepare() {

    }

    /**
     * Tests getReferenceTable Method for GENDER Reference Table.
     */
    @Test
    public void getReferenceTableMethodTestforGenderRt() throws Exception {

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

        System.out.println("rtGenderMap: " + testGenderMap);

        ResponseVO testResponseGender = new ResponseVO(testGenderMap, "0");

        System.out.println("testResponseGender: " + testResponseGender);


        ResponseVO resultResponseGender = referenceTableController.getReferenceTable("GENDER");

        System.out.println("resultResponseGender: " + resultResponseGender);


        Assert.isTrue (testResponseGender.equals(resultResponseGender));

    }

    /**
     * Tests getReferenceTable Method for APPSOPDUE Reference Table.
     */
    @Test
    public void getReferenceTableMethodTestforAppsopdueRt() throws Exception {

        Map<String, ReferenceTableVersion> testAppsopdueMap = new HashMap<String, ReferenceTableVersion>();

        String version = "1";

        ReferenceTableVersion referenceTableVersion1 = new ReferenceTableVersion(version);

        referenceTableVersion1.setEffectiveBeginDate("2016-06-01 00:00:00");

        referenceTableVersion1.setEffectiveEndDate("2017-01-25 00:00:00");

        Set<Map<String, String>> refData1 = new HashSet<>();

        Map<String, String> rowMap11 = new HashMap<>();
        Map<String, String> rowMap12 = new HashMap<>();
        Map<String, String> rowMap13 = new HashMap<>();
        Map<String, String> rowMap14 = new HashMap<>();
        Map<String, String> rowMap15 = new HashMap<>();
        Map<String, String> rowMap16 = new HashMap<>();


        rowMap11.put("CODE","MA");
        rowMap11.put("DESCRIPTION","45");

        refData1.add(rowMap11);

        rowMap12.put("CODE","FS");
        rowMap12.put("DESCRIPTION","30");

        refData1.add(rowMap12);

        rowMap13.put("CODE","TF");
        rowMap13.put("DESCRIPTION","45");

        refData1.add(rowMap13);

        rowMap14.put("CODE","CC");
        rowMap14.put("DESCRIPTION","45");

        refData1.add(rowMap14);

        rowMap15.put("CODE","WC");
        rowMap15.put("DESCRIPTION","10");

        refData1.add(rowMap15);

        rowMap16.put("CODE","LI");
        rowMap16.put("DESCRIPTION","45");

        refData1.add(rowMap16);

        referenceTableVersion1.setRefData(refData1);

        testAppsopdueMap.put("1",referenceTableVersion1);



        version = "2";

        ReferenceTableVersion referenceTableVersion2 = new ReferenceTableVersion(version);

        referenceTableVersion2.setEffectiveBeginDate("2017-01-26 00:00:00");

        Set<Map<String, String>> refData2 = new HashSet<>();

        Map<String, String> rowMap21 = new HashMap<>();
        Map<String, String> rowMap22 = new HashMap<>();
        Map<String, String> rowMap23 = new HashMap<>();
        Map<String, String> rowMap24 = new HashMap<>();
        Map<String, String> rowMap25 = new HashMap<>();
        Map<String, String> rowMap26 = new HashMap<>();
        Map<String, String> rowMap27 = new HashMap<>();
        Map<String, String> rowMap28 = new HashMap<>();
        Map<String, String> rowMap29 = new HashMap<>();
        Map<String, String> rowMap210 = new HashMap<>();
        Map<String, String> rowMap211 = new HashMap<>();
        Map<String, String> rowMap212 = new HashMap<>();
        Map<String, String> rowMap213 = new HashMap<>();
        Map<String, String> rowMap214 = new HashMap<>();


        rowMap21.put("CODE","LI");
        rowMap21.put("DESCRIPTION","44");
        rowMap21.put("APPRCVDDT","1");
        rowMap21.put("POLICYSOP","45");

        refData2.add(rowMap21);

        rowMap22.put("CODE","MA");
        rowMap22.put("DESCRIPTION","44");
        rowMap22.put("APPRCVDDT","1");
        rowMap22.put("POLICYSOP","45");
        refData2.add(rowMap22);

        rowMap23.put("CODE","LAD");
        rowMap23.put("DESCRIPTION","44");
        rowMap23.put("APPRCVDDT","1");
        rowMap23.put("POLICYSOP","45");

        refData2.add(rowMap23);

        rowMap24.put("CODE","FS");
        rowMap24.put("DESCRIPTION","30");
        rowMap24.put("APPRCVDDT","0");
        rowMap24.put("POLICYSOP","30");

        refData2.add(rowMap24);

        rowMap25.put("CODE","TF");
        rowMap25.put("DESCRIPTION","44");
        rowMap25.put("APPRCVDDT","1");
        rowMap25.put("POLICYSOP","45");

        refData2.add(rowMap25);

        rowMap26.put("CODE","CC");
        rowMap26.put("DESCRIPTION","30");
        rowMap26.put("APPRCVDDT","0");
        rowMap26.put("POLICYSOP","30");

        refData2.add(rowMap26);


        rowMap27.put("CODE","WC");
        rowMap27.put("DESCRIPTION","9");
        rowMap27.put("APPRCVDDT","1");
        rowMap27.put("POLICYSOP","10");

        refData2.add(rowMap27);

        rowMap28.put("CODE","PREGNANCY");
        rowMap28.put("DESCRIPTION","9");
        rowMap28.put("APPRCVDDT","1");
        rowMap28.put("POLICYSOP","10");
        refData2.add(rowMap28);

        rowMap29.put("CODE","QTRACK");
        rowMap29.put("DESCRIPTION","9");
        rowMap29.put("APPRCVDDT","1");
        rowMap29.put("POLICYSOP","10");

        refData2.add(rowMap29);

        rowMap210.put("CODE","NADISABLED");
        rowMap210.put("DESCRIPTION","59");
        rowMap210.put("APPRCVDDT","1");
        rowMap210.put("POLICYSOP","60");

        refData2.add(rowMap210);

        rowMap211.put("CODE","DISABLEDNOTAGED");
        rowMap211.put("DESCRIPTION","59");
        rowMap211.put("APPRCVDDT","1");
        rowMap211.put("POLICYSOP","60");

        refData2.add(rowMap211);

        rowMap212.put("CODE","AGEDNOTQTRACK");
        rowMap212.put("DESCRIPTION","44");
        rowMap212.put("APPRCVDDT","1");
        rowMap212.put("POLICYSOP","45");

        refData2.add(rowMap212);


        rowMap213.put("CODE","EXPEDITEDFS");
        rowMap213.put("DESCRIPTION","7");
        rowMap213.put("APPRCVDDT","0");
        rowMap213.put("POLICYSOP","7");

        refData2.add(rowMap213);

        rowMap214.put("CODE","CU19PARENTCARETAKER");
        rowMap214.put("DESCRIPTION","59");
        rowMap214.put("APPRCVDDT","1");
        rowMap214.put("POLICYSOP","60");

        refData2.add(rowMap214);

        referenceTableVersion2.setRefData(refData2);

        testAppsopdueMap.put("1",referenceTableVersion1);
        testAppsopdueMap.put("2",referenceTableVersion2);


        ResponseVO testResponseAppsopdue = new ResponseVO(testAppsopdueMap, "0");

        System.out.println("testResponseAppsopdue: " + testResponseAppsopdue);



        ResponseVO resultResponseAppsopdue = referenceTableController.getReferenceTable("APPSOPDUE");

        System.out.println("resultResponseAppsopdue: " + resultResponseAppsopdue);


        Assert.isTrue (testResponseAppsopdue.equals(resultResponseAppsopdue));

    }

    /**
     * Tests getReferenceTable Method for Invalid Reference Table name.
     */
    @Test
    public void getReferenceTableMethodTestWithInvalidValue() throws Exception {

        ResponseVO testRTResponse = new ResponseVO("Reference Table not found - RAHUL", "1");

        System.out.println("testRTResponse: " + testRTResponse);

        ResponseVO resultRTResponse = referenceTableController.getReferenceTable("RAHUL");

        System.out.println("resultRTResponse: " + resultRTResponse);

        Assert.isTrue (testRTResponse.equals(resultRTResponse));

    }
}
