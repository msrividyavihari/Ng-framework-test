package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for contributionsIncome complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="contributionsIncome">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContributionsStart" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ContributionsHowOften" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContributionsAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contributionsIncome", propOrder = {
        "recordStatus",
        "user",
        "contributionsStart",
        "contributionsHowOften",
        "contributionsAmount"
})
@Data
public class ContributionsIncome {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "ContributionsStart", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar contributionsStart;
    @XmlElement(name = "ContributionsHowOften", required = true)
    protected String contributionsHowOften;
    @XmlElement(name = "ContributionsAmount", required = true)
    protected String contributionsAmount;
}
