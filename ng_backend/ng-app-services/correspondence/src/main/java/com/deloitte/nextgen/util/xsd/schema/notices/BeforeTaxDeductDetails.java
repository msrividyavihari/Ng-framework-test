package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for beforeTaxDeductDeatils complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="beforeTaxDeductDeatils">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BeforeTaxMedical" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BeforeTaxDental" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BeforeTaxVision" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BeforeTaxFelx" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BeforeTaxDeferred" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BeforeTaxPreTax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BeforeTaxOther" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "beforeTaxDeductDeatils", propOrder = {
        "recordStatus",
        "user",
        "beforeTaxMedical",
        "beforeTaxDental",
        "beforeTaxVision",
        "beforeTaxFelx",
        "beforeTaxDeferred",
        "beforeTaxPreTax",
        "beforeTaxOther"
})

@Data
public class BeforeTaxDeductDetails {
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "BeforeTaxMedical", required = true)
    protected String beforeTaxMedical;
    @XmlElement(name = "BeforeTaxDental", required = true)
    protected String beforeTaxDental;
    @XmlElement(name = "BeforeTaxVision", required = true)
    protected String beforeTaxVision;
    @XmlElement(name = "BeforeTaxFelx", required = true)
    protected String beforeTaxFelx;
    @XmlElement(name = "BeforeTaxDeferred", required = true)
    protected String beforeTaxDeferred;
    @XmlElement(name = "BeforeTaxPreTax", required = true)
    protected String beforeTaxPreTax;
    @XmlElement(name = "BeforeTaxOther", required = true)
    protected String beforeTaxOther;
}
