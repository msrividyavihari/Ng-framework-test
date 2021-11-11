package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for inquiry complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="inquiry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Inquiry_Number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Name" type="{}name"/>
 *         &lt;element name="Contact" type="{}address"/>
 *         &lt;element name="Date_Submit" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Inquiry_Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Program_Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Inquiry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inquiry", propOrder = {
        "inquiryNumber",
        "name",
        "contact",
        "dateSubmit",
        "inquiryType",
        "programType",
        "inquiry"
})

@Data
public class Inquiry {
    @XmlElement(name = "Inquiry_Number", required = true)
    protected String inquiryNumber;
    @XmlElement(name = "Name", required = true)
    protected Name name;
    @XmlElement(name = "Contact", required = true)
    protected Address contact;
    @XmlElement(name = "Date_Submit", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateSubmit;
    @XmlElement(name = "Inquiry_Type", required = true)
    protected String inquiryType;
    @XmlElement(name = "Program_Type", required = true)
    protected String programType;
    @XmlElement(name = "Inquiry", required = true)
    protected String inquiry;
}
