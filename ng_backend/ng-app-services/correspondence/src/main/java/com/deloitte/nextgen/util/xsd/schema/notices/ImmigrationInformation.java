package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for immigrationInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="immigrationInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ImmigrationStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DateToUS" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ImmigrationDocumentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AlienRegistrationNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "immigrationInformation", propOrder = {
        "immigrationStatus",
        "dateToUS",
        "immigrationDocumentType",
        "alienRegistrationNum"
})

@Data
public class ImmigrationInformation {
    @XmlElement(name = "ImmigrationStatus", required = true)
    protected String immigrationStatus;
    @XmlElement(name = "DateToUS", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateToUS;
    @XmlElement(name = "ImmigrationDocumentType", required = true)
    protected String immigrationDocumentType;
    @XmlElement(name = "AlienRegistrationNum", required = true)
    protected String alienRegistrationNum;
}
