package com.deloitte.nextgen.framework.persistence.junit;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * The class <code> TypeOneGeneratedClassesTests </code> is used
 * to test if all the Generated classes got generated successfully.
 *
 * @author rarathore on 30/09/2021 12:52 PM
 * @project ng-persistence
 */

class TypeOneGeneratedClassesTests {

    //    @BeforeAll
    void prepare() {

    }

    /**
     * Tests for TypeOneEntityPK class.
     */

    @Test
    void TypeOneEntityPKTest() {

        Class aClass = null;
        Exception aException = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeOneEntityPK");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            aException = e;
            System.out.println("Exception:" + e);
        }

        Assert.notNull(aClass);
        Assert.isNull(aException);
    }

    /**
     * Tests for TypeOneEntityA class.
     */

    @Test
    void TypeOneEntityATest() {

        Class aClass = null;
        Exception aException = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeOneEntityA");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            aException = e;
            System.out.println("Exception:" + e);
        }

        Assert.notNull(aClass);
        Assert.isNull(aException);
    }

    /**
     * Tests for TypeOneEntityAuditPK class.
     */

    @Test
    void TypeOneEntityAuditPKTest() {

        Class aClass = null;
        Exception aException = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeOneEntityAuditPK");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            aException = e;
            System.out.println("Exception:" + e);
        }

        Assert.notNull(aClass);
        Assert.isNull(aException);
    }

    /**
     * Tests for TypeOneEntityB class.
     */

    @Test
    void TypeOneEntityBTest() {

        Class aClass = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeOneEntityB");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            System.out.println("Exception:" + e);
        }

        Assert.isNull(aClass);
    }

    /**
     * Tests for TypeOneEntityBasePK class.
     */

    @Test
    void TypeOneEntityBasePKTest() {

        Class aClass = null;

        try {

            aClass = Class.forName("com.deloitte.nextgen.framework.persistence.junit.generated.TypeOneEntityBasePK");
            System.out.println("aClass: " + aClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            System.out.println("Exception:" + e);
        }

        Assert.isNull(aClass);
    }
}
