package com.deloitte.nextgen.framework.persistence.junit;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * The class <code> TypeZeroGeneratedClassesTests </code> is used
 * to test if all the Generated classes got generated successfully.
 *
 * @author rarathore on 30/09/2021 12:52 PM
 * @project ng-persistence
 */

class TypeZeroGeneratedClassesTests {

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests for TypeZeroEntityPK class.
     */

    @Test
    void TypeZeroEntityPKTest() {

        Class aClass = null;
        Exception aException = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeZeroEntityPK");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            aException = e;
            System.out.println("Exception:" + e);
        }

        Assert.notNull(aClass);
        Assert.isNull(aException);
    }

    /**
     * Tests for TypeZeroEntityA class.
     */

    @Test
    void TypeZeroEntityATest() {

        Class aClass = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeZeroEntityA");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            System.out.println("Exception:" + e);
        }

        Assert.isNull(aClass);
    }

    /**
     * Tests for TypeZeroEntityAuditPK class.
     */

    @Test
    void TypeZeroEntityAuditPKTest() {

        Class aClass = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeZeroEntityAuditPK");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            System.out.println("Exception:" + e);
        }

        Assert.isNull(aClass);
    }

    /**
     * Tests for TypeZeroEntityB class.
     */

    @Test
    void TypeZeroEntityBTest() {

        Class aClass = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeZeroEntityB");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            System.out.println("Exception:" + e);
        }

        Assert.isNull(aClass);
    }

    /**
     * Tests for TypeZeroEntityBasePK class.
     */

    @Test
    void TypeZeroEntityBasePKTest() {

        Class aClass = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeZeroEntityBasePK");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            System.out.println("Exception:" + e);
        }

        Assert.isNull(aClass);
    }
}
