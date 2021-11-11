package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for primaryContactDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="primaryContactDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PrimaryContactPersonalInfo" type="{}personalInformation"/>
 *         &lt;element name="NotificationEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PrimaryContactAddressInfo" type="{}address"/>
 *         &lt;element name="CountyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SpokenLanguage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WrittenLanguage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApplicationSignatureDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ContactPreferenceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Extension" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "primaryContactDetails", propOrder = {
        "primaryContactPersonalInfo",
        "notificationEmailAddress",
        "addressTypeCode",
        "primaryContactAddressInfo",
        "countyCode",
        "spokenLanguage",
        "writtenLanguage",
        "applicationSignatureDate",
        "contactPreferenceCode",
        "phoneNumber",
        "extension"
})

@Data
public class PrimaryContactDetails {
    @XmlElement(name = "PrimaryContactPersonalInfo", required = true)
    protected PersonalInformation primaryContactPersonalInfo;
    @XmlElement(name = "NotificationEmailAddress", required = true)
    protected String notificationEmailAddress;
    @XmlElement(name = "AddressTypeCode", required = true)
    protected String addressTypeCode;
    @XmlElement(name = "PrimaryContactAddressInfo", required = true)
    protected Address primaryContactAddressInfo;
    @XmlElement(name = "CountyCode", required = true)
    protected String countyCode;
    @XmlElement(name = "SpokenLanguage", required = true)
    protected String spokenLanguage;
    @XmlElement(name = "WrittenLanguage", required = true)
    protected String writtenLanguage;
    @XmlElement(name = "ApplicationSignatureDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar applicationSignatureDate;
    @XmlElement(name = "ContactPreferenceCode", required = true)
    protected String contactPreferenceCode;
    @XmlElement(name = "PhoneNumber", required = true)
    protected String phoneNumber;
    @XmlElement(name = "Extension", required = true)
    protected String extension;
}
