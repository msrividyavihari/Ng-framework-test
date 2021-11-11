package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for fgg6a complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="fgg6a">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NursingFacility" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="GAPP" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Tefra" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MedicaidNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Age" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DateOfBirth" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Review" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AppDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fgg6a", propOrder = {
        "nursingFacility",
        "gapp",
        "tefra",
        "medicaidNumber",
        "ssn",
        "sex",
        "age",
        "dateOfBirth",
        "review",
        "appDate"
})

@Data
public class Fgg6A {
    @XmlElement(name = "NursingFacility")
    protected int nursingFacility;
    @XmlElement(name = "GAPP")
    protected int gapp;
    @XmlElement(name = "Tefra")
    protected int tefra;
    @XmlElement(name = "MedicaidNumber", required = true)
    protected String medicaidNumber;
    @XmlElement(name = "SSN", required = true)
    protected String ssn;
    @XmlElement(name = "Sex", required = true)
    protected String sex;
    @XmlElement(name = "Age")
    protected int age;
    @XmlElement(name = "DateOfBirth", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfBirth;
    @XmlElement(name = "Review")
    protected int review;
    @XmlElement(name = "AppDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar appDate;
}
