package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for collateral complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="collateral">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Verify_Month" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Verify_Year" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Verify_Month_Year" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "collateral", propOrder = {
        "verifyMonth",
        "verifyYear",
        "verifyMonthYear"
})

@Data
public class Collateral {
    @XmlElement(name = "Verify_Month", required = true)
    protected String verifyMonth;
    @XmlElement(name = "Verify_Year", required = true)
    protected String verifyYear;
    @XmlElement(name = "Verify_Month_Year", required = true)
    protected String verifyMonthYear;
}

