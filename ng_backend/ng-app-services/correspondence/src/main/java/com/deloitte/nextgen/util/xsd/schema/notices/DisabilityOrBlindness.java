package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for disabilityorBlindness complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="disabilityOrBlindness">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisabilityStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NatureOfDisability" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisabilityBenefitSSA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApprovalSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otherApprovalSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "disabilityOrBlindness", propOrder = {
        "recordStatus",
        "user",
        "disabilityStatus",
        "natureOfDisability",
        "disabilityBenefitSSA",
        "approvalSource",
        "otherApprovalSource"
})

@Data
public class DisabilityOrBlindness {
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "DisabilityStatus", required = true)
    protected String disabilityStatus;
    @XmlElement(name = "NatureOfDisability", required = true)
    protected String natureOfDisability;
    @XmlElement(name = "DisabilityBenefitSSA", required = true)
    protected String disabilityBenefitSSA;
    @XmlElement(name = "ApprovalSource", required = true)
    protected String approvalSource;
    @XmlElement(required = true)
    protected String otherApprovalSource;
}
