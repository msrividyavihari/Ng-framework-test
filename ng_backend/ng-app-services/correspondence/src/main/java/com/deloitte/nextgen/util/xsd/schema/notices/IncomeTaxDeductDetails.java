package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for incomeTaxDeductDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="incomeTaxDeductDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxEducator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxBusiness" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxHealth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxMoving" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxDeduct" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxSEP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxSelfHealth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxPenalty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxAlimony" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxIRA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxStudent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxTuition" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeTaxDomestic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "incomeTaxDeductDetails", propOrder = {
        "recordStatus",
        "user",
        "incomeTaxEducator",
        "incomeTaxBusiness",
        "incomeTaxHealth",
        "incomeTaxMoving",
        "incomeTaxDeduct",
        "incomeTaxSEP",
        "incomeTaxSelfHealth",
        "incomeTaxPenalty",
        "incomeTaxAlimony",
        "incomeTaxIRA",
        "incomeTaxStudent",
        "incomeTaxTuition",
        "incomeTaxDomestic"
})

@Data
public class IncomeTaxDeductDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "IncomeTaxEducator", required = true)
    protected String incomeTaxEducator;
    @XmlElement(name = "IncomeTaxBusiness", required = true)
    protected String incomeTaxBusiness;
    @XmlElement(name = "IncomeTaxHealth", required = true)
    protected String incomeTaxHealth;
    @XmlElement(name = "IncomeTaxMoving", required = true)
    protected String incomeTaxMoving;
    @XmlElement(name = "IncomeTaxDeduct", required = true)
    protected String incomeTaxDeduct;
    @XmlElement(name = "IncomeTaxSEP", required = true)
    protected String incomeTaxSEP;
    @XmlElement(name = "IncomeTaxSelfHealth", required = true)
    protected String incomeTaxSelfHealth;
    @XmlElement(name = "IncomeTaxPenalty", required = true)
    protected String incomeTaxPenalty;
    @XmlElement(name = "IncomeTaxAlimony", required = true)
    protected String incomeTaxAlimony;
    @XmlElement(name = "IncomeTaxIRA", required = true)
    protected String incomeTaxIRA;
    @XmlElement(name = "IncomeTaxStudent", required = true)
    protected String incomeTaxStudent;
    @XmlElement(name = "IncomeTaxTuition", required = true)
    protected String incomeTaxTuition;
    @XmlElement(name = "IncomeTaxDomestic", required = true)
    protected String incomeTaxDomestic;
}
