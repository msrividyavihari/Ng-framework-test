package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for financialAccountDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="financialAccountDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FinAccountType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FinAccountValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FinAccountBank" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BankAddressInfo" type="{}address"/>
 *         &lt;element name="FinAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FinAccountOtherOwners" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FinAccountOtherOwnersPercent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "financialAccountDetails", propOrder = {
        "recordStatus",
        "user",
        "finAccountType",
        "finAccountValue",
        "finAccountBank",
        "bankAddressInfo",
        "finAccountNumber",
        "finAccountOtherOwners",
        "finAccountOtherOwnersPercent"
})

@Data
public class FinancialAccountDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "FinAccountType", required = true)
    protected String finAccountType;
    @XmlElement(name = "FinAccountValue", required = true)
    protected String finAccountValue;
    @XmlElement(name = "FinAccountBank", required = true)
    protected String finAccountBank;
    @XmlElement(name = "BankAddressInfo", required = true)
    protected Address bankAddressInfo;
    @XmlElement(name = "FinAccountNumber", required = true)
    protected String finAccountNumber;
    @XmlElement(name = "FinAccountOtherOwners", required = true)
    protected String finAccountOtherOwners;
    @XmlElement(name = "FinAccountOtherOwnersPercent", required = true)
    protected String finAccountOtherOwnersPercent;
}
