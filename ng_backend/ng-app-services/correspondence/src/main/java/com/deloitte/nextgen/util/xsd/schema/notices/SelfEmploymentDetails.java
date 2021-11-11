package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for selfEmploymentDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="selfEmploymentDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SelfEmploymentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SelfEmploymentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SelfEmploymentMonthlyIncome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SelfEmploymentHours" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SelfEmploymentExpenseType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SelfEmploymentMonthlyAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selfEmploymentDetails", propOrder = {
        "recordStatus",
        "user",
        "selfEmploymentName",
        "selfEmploymentType",
        "selfEmploymentMonthlyIncome",
        "selfEmploymentHours",
        "selfEmploymentExpenseType",
        "selfEmploymentMonthlyAmount"
})

@Data
public class SelfEmploymentDetails {
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "SelfEmploymentName", required = true)
    protected String selfEmploymentName;
    @XmlElement(name = "SelfEmploymentType", required = true)
    protected String selfEmploymentType;
    @XmlElement(name = "SelfEmploymentMonthlyIncome", required = true)
    protected String selfEmploymentMonthlyIncome;
    @XmlElement(name = "SelfEmploymentHours", required = true)
    protected String selfEmploymentHours;
    @XmlElement(name = "SelfEmploymentExpenseType", required = true)
    protected String selfEmploymentExpenseType;
    @XmlElement(name = "SelfEmploymentMonthlyAmount", required = true)
    protected String selfEmploymentMonthlyAmount;
}
