package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for fgg111c complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="fgg111c">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cause" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ParentCaseNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ParentalAuthorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReturnDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fgg111c", propOrder = {
        "cause",
        "parentCaseNumber",
        "ssn",
        "parentalAuthorName",
        "startDate",
        "endDate",
        "amount",
        "reason",
        "returnDate"
})

@Data
public class Fgg111C {

    @XmlElement(name = "Cause")
    protected int cause;
    @XmlElement(name = "ParentCaseNumber", required = true)
    protected String parentCaseNumber;
    @XmlElement(name = "SSN", required = true)
    protected String ssn;
    @XmlElement(name = "ParentalAuthorName", required = true)
    protected String parentalAuthorName;
    @XmlElement(name = "StartDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "Amount")
    protected float amount;
    @XmlElement(name = "Reason", required = true)
    protected String reason;
    @XmlElement(name = "ReturnDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar returnDate;
}
