package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for results complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="results">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MedicalAssistance" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildCare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SNAP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WIC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TANF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NotMedicalAssistance" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NotChildCare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NotSNAP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NotWIC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NotTANF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "results", propOrder = {
        "medicalAssistance",
        "childCare",
        "snap",
        "wic",
        "tanf",
        "notMedicalAssistance",
        "notChildCare",
        "notSNAP",
        "notWIC",
        "notTANF"
})

@Data
public class Results {
    @XmlElement(name = "MedicalAssistance", required = true)
    protected String medicalAssistance;
    @XmlElement(name = "ChildCare", required = true)
    protected String childCare;
    @XmlElement(name = "SNAP", required = true)
    protected String snap;
    @XmlElement(name = "WIC", required = true)
    protected String wic;
    @XmlElement(name = "TANF", required = true)
    protected String tanf;
    @XmlElement(name = "NotMedicalAssistance", required = true)
    protected String notMedicalAssistance;
    @XmlElement(name = "NotChildCare", required = true)
    protected String notChildCare;
    @XmlElement(name = "NotSNAP", required = true)
    protected String notSNAP;
    @XmlElement(name = "NotWIC", required = true)
    protected String notWIC;
    @XmlElement(name = "NotTANF", required = true)
    protected String notTANF;
}
