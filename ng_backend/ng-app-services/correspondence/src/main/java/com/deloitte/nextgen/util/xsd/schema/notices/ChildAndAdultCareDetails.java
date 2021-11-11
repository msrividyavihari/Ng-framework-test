package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ChildAndAdultCareDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ChildAndAdultCareDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildAdultCareProviderName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Address" type="{}address"/>
 *         &lt;element name="ChildAdultCareReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildAdultCareWho" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildAdultCareStart" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ChildAdultCareFrequency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildAdultCareAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChildAndAdultCareDetails", propOrder = {
        "recordStatus",
        "user",
        "childAdultCareProviderName",
        "address",
        "childAdultCareReason",
        "childAdultCareWho",
        "childAdultCareStart",
        "childAdultCareFrequency",
        "childAdultCareAmount"
})
@Data
public class ChildAndAdultCareDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "ChildAdultCareProviderName", required = true)
    protected String childAdultCareProviderName;
    @XmlElement(name = "Address", required = true)
    protected Address address;
    @XmlElement(name = "ChildAdultCareReason", required = true)
    protected String childAdultCareReason;
    @XmlElement(name = "ChildAdultCareWho", required = true)
    protected String childAdultCareWho;
    @XmlElement(name = "ChildAdultCareStart", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar childAdultCareStart;
    @XmlElement(name = "ChildAdultCareFrequency", required = true)
    protected String childAdultCareFrequency;
    @XmlElement(name = "ChildAdultCareAmount", required = true)
    protected String childAdultCareAmount;
}
