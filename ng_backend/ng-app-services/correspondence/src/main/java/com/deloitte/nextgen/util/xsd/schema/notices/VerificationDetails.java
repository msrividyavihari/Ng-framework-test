package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for verificationDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="verificationDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LawfulPresenceVerifiedCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QualifiedNonCitizenCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USCitizenCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VerificationTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SSNVerificationIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verificationDetails", propOrder = {
        "lawfulPresenceVerifiedCode",
        "qualifiedNonCitizenCode",
        "usCitizenCode",
        "verificationTypeCode",
        "ssnVerificationIndicator"
})

@Data
public class VerificationDetails {
    @XmlElement(name = "LawfulPresenceVerifiedCode", required = true)
    protected String lawfulPresenceVerifiedCode;
    @XmlElement(name = "QualifiedNonCitizenCode", required = true)
    protected String qualifiedNonCitizenCode;
    @XmlElement(name = "USCitizenCode", required = true)
    protected String usCitizenCode;
    @XmlElement(name = "VerificationTypeCode", required = true)
    protected String verificationTypeCode;
    @XmlElement(name = "SSNVerificationIndicator", required = true)
    protected String ssnVerificationIndicator;
}
