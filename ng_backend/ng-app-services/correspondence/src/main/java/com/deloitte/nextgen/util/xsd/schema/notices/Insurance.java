package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for insurance complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="insurance">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LostHealthInsuranceReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LostHealthInsuranceLastDay" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insurance", propOrder = {
        "recordStatus",
        "user",
        "lostHealthInsuranceReason",
        "lostHealthInsuranceLastDay"
})

@Data
public class Insurance {
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "LostHealthInsuranceReason", required = true)
    protected String lostHealthInsuranceReason;
    @XmlElement(name = "LostHealthInsuranceLastDay", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lostHealthInsuranceLastDay;
}
