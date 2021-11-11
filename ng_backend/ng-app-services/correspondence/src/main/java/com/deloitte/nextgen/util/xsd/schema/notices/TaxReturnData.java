package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for taxReturnData complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="taxReturnData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TaxFilerIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DependentIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MarriedFilingJointlyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taxReturnData", propOrder = {
        "taxFilerIndicator",
        "dependentIndicator",
        "marriedFilingJointlyCode"
})

@Data
public class TaxReturnData {
    @XmlElement(name = "TaxFilerIndicator", required = true)
    protected String taxFilerIndicator;
    @XmlElement(name = "DependentIndicator", required = true)
    protected String dependentIndicator;
    @XmlElement(name = "MarriedFilingJointlyCode", required = true)
    protected String marriedFilingJointlyCode;
}
