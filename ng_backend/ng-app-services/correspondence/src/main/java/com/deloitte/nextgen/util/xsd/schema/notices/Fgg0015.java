package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for fgg0015 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="fgg0015">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Notice_Salutation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Free_Form_Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Policy_Manual_Reference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Program_Code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fgg0015", propOrder = {
        "noticeSalutation",
        "freeFormText",
        "policyManualReference",
        "programCode"
})

@Data
public class Fgg0015 {
    @XmlElement(name = "Notice_Salutation", required = true)
    protected String noticeSalutation;
    @XmlElement(name = "Free_Form_Text", required = true)
    protected String freeFormText;
    @XmlElement(name = "Policy_Manual_Reference", required = true)
    protected String policyManualReference;
    @XmlElement(name = "Program_Code", required = true)
    protected String programCode;
}
