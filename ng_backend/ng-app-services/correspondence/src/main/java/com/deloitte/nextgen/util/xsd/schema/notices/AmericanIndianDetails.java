package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for americanIndianDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="americanIndianDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AmericanIndianBegin" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="AmericanIndianHowOften" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AmericanIndianAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "americanIndianDetails", propOrder = {
        "recordStatus",
        "user",
        "americanIndianBegin",
        "americanIndianHowOften",
        "americanIndianAmount"
})

@Data
public class AmericanIndianDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "AmericanIndianBegin", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar americanIndianBegin;
    @XmlElement(name = "AmericanIndianHowOften", required = true)
    protected String americanIndianHowOften;
    @XmlElement(name = "AmericanIndianAmount", required = true)
    protected String americanIndianAmount;
}
