package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for directChildSupportIncomeDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="directChildSupportIncomeDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DirectChildSupportPaymetBegin" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DirectChildSupportFrequency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DirectChildSupportAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "directChildSupportIncomeDetails", propOrder = {
        "recordStatus",
        "user",
        "directChildSupportPaymetBegin",
        "directChildSupportFrequency",
        "directChildSupportAmount"
})

@Data
public class DirectChildSupportIncomeDetails {
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "DirectChildSupportPaymetBegin", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar directChildSupportPaymetBegin;
    @XmlElement(name = "DirectChildSupportFrequency", required = true)
    protected String directChildSupportFrequency;
    @XmlElement(name = "DirectChildSupportAmount", required = true)
    protected String directChildSupportAmount;
}
