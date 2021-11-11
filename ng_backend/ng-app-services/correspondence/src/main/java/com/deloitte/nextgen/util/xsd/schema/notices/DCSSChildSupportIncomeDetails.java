package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dCSSChildSupportIncomeDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="dCSSChildSupportIncomeDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildSupportStart" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ChildSupportHowOften" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildSupportGrossIncome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dCSSChildSupportIncomeDetails", propOrder = {
        "recordStatus",
        "user",
        "childSupportStart",
        "childSupportHowOften",
        "childSupportGrossIncome"
})
@Data
public class DCSSChildSupportIncomeDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "ChildSupportStart", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar childSupportStart;
    @XmlElement(name = "ChildSupportHowOften", required = true)
    protected String childSupportHowOften;
    @XmlElement(name = "ChildSupportGrossIncome", required = true)
    protected String childSupportGrossIncome;
}
