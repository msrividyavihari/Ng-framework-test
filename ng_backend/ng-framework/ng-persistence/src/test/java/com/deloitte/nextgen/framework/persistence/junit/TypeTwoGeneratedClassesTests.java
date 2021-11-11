package com.deloitte.nextgen.framework.persistence.junit;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * The class <code> TypeTwoGeneratedClassesTests </code> is used
 * to test if all the Generated classes got generated successfully.
 *
 * @author rarathore on 30/09/2021 12:52 PM
 * @project ng-persistence
 */


class TypeTwoGeneratedClassesTests {

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests for TypeTwoEntityPK class.
     */

    @Test
    void TypeTwoEntityPKTest() {

        Class aClass = null;
        Exception aException = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeTwoEntityPK");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            aException = e;
            System.out.println("Exception:" + e);
        }

        Assert.notNull(aClass);
        Assert.isNull(aException);
    }

    /**
     * Tests for TypeTwoEntityA class.
     */

    @Test
    void TypeTwoEntityATest() {

        Class aClass = null;
        Exception aException = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeTwoEntityA");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            aException = e;
            System.out.println("Exception:" + e);
        }

        Assert.notNull(aClass);
        Assert.isNull(aException);
    }

    /**
     * Tests for TypeTwoEntityAuditPK class.
     */

    @Test
    void TypeTwoEntityAuditPKTest() {

        Class aClass = null;
        Exception aException = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeTwoEntityAuditPK");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            aException = e;
            System.out.println("Exception:" + e);
        }

        Assert.notNull(aClass);
        Assert.isNull(aException);
    }

    /**
     * Tests for TypeTwoEntityB class.
     */

    @Test
    void TypeTwoEntityBTest() {

        Class aClass = null;
        Exception aException = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeTwoEntityB");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            aException = e;
            System.out.println("Exception:" + e);
        }

        Assert.notNull(aClass);
        Assert.isNull(aException);
    }

    /**
     * Tests for TypeTwoEntityBasePK class.
     */

    @Test
    void TypeTwoEntityBasePKTest() {

        Class aClass = null;
        Exception aException = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeTwoEntityBasePK");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            aException = e;
            System.out.println("Exception:" + e);
        }

        Assert.notNull(aClass);
        Assert.isNull(aException);
    }
}
