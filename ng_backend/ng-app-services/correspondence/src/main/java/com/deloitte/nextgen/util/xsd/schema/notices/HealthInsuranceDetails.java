package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for healthInsuranceDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="healthInsuranceDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HealthInsuranceType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OtherOwners" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "healthInsuranceDetails", propOrder = {
        "recordStatus",
        "user",
        "healthInsuranceType",
        "otherOwners"
})

@Data
public class HealthInsuranceDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "HealthInsuranceType", required = true)
    protected String healthInsuranceType;
    @XmlElement(name = "OtherOwners", required = true)
    protected String otherOwners;
}
