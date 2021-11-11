package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HousingBillsDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="HousingBillsDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HousingBillType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HousingBillFrequency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HousingBillAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HousingBillAmountPaid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HousingBillLandlordName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Address" type="{}address"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HousingBillsDetails", propOrder = {
        "recordStatus",
        "user",
        "housingBillType",
        "housingBillFrequency",
        "housingBillAmount",
        "housingBillAmountPaid",
        "housingBillLandlordName",
        "address"
})

@Data
public class HousingBillsDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "HousingBillType", required = true)
    protected String housingBillType;
    @XmlElement(name = "HousingBillFrequency", required = true)
    protected String housingBillFrequency;
    @XmlElement(name = "HousingBillAmount", required = true)
    protected String housingBillAmount;
    @XmlElement(name = "HousingBillAmountPaid", required = true)
    protected String housingBillAmountPaid;
    @XmlElement(name = "HousingBillLandlordName", required = true)
    protected String housingBillLandlordName;
    @XmlElement(name = "Address", required = true)
    protected Address address;
}
