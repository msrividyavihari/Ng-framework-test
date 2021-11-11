package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for employerHealthCoverageDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="employerHealthCoverageDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovCOBRA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovRetiree" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovStateBen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovEnrolled" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovPolicyNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovEmployer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovThreeMo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovEligibleStDt" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="EmployerHealthCovMinValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovHowOften" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovChange" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovPremium" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovOften" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="EmployerHealthCovOtherOwner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerHealthCovPercent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employerHealthCoverageDetails", propOrder = {
        "recordStatus",
        "user",
        "employerHealthCovCOBRA",
        "employerHealthCovRetiree",
        "employerHealthCovStateBen",
        "employerHealthCovEnrolled",
        "employerHealthCovName",
        "employerHealthCovPolicyNum",
        "employerHealthCovEmployer",
        "employerHealthCovPhone",
        "employerHealthCovEmail",
        "employerHealthCovThreeMo",
        "employerHealthCovEligibleStDt",
        "employerHealthCovMinValue",
        "employerHealthCovHowOften",
        "employerHealthCovChange",
        "employerHealthCovPremium",
        "employerHealthCovOften",
        "employerHealthCovDate",
        "employerHealthCovOtherOwner",
        "employerHealthCovPercent"
})

@Data
public class EmployerHealthCoverageDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "EmployerHealthCovCOBRA", required = true)
    protected String employerHealthCovCOBRA;
    @XmlElement(name = "EmployerHealthCovRetiree", required = true)
    protected String employerHealthCovRetiree;
    @XmlElement(name = "EmployerHealthCovStateBen", required = true)
    protected String employerHealthCovStateBen;
    @XmlElement(name = "EmployerHealthCovEnrolled", required = true)
    protected String employerHealthCovEnrolled;
    @XmlElement(name = "EmployerHealthCovName", required = true)
    protected String employerHealthCovName;
    @XmlElement(name = "EmployerHealthCovPolicyNum", required = true)
    protected String employerHealthCovPolicyNum;
    @XmlElement(name = "EmployerHealthCovEmployer", required = true)
    protected String employerHealthCovEmployer;
    @XmlElement(name = "EmployerHealthCovPhone", required = true)
    protected String employerHealthCovPhone;
    @XmlElement(name = "EmployerHealthCovEmail", required = true)
    protected String employerHealthCovEmail;
    @XmlElement(name = "EmployerHealthCovThreeMo", required = true)
    protected String employerHealthCovThreeMo;
    @XmlElement(name = "EmployerHealthCovEligibleStDt", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar employerHealthCovEligibleStDt;
    @XmlElement(name = "EmployerHealthCovMinValue", required = true)
    protected String employerHealthCovMinValue;
    @XmlElement(name = "EmployerHealthCovHowOften", required = true)
    protected String employerHealthCovHowOften;
    @XmlElement(name = "EmployerHealthCovChange", required = true)
    protected String employerHealthCovChange;
    @XmlElement(name = "EmployerHealthCovPremium", required = true)
    protected String employerHealthCovPremium;
    @XmlElement(name = "EmployerHealthCovOften", required = true)
    protected String employerHealthCovOften;
    @XmlElement(name = "EmployerHealthCovDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar employerHealthCovDate;
    @XmlElement(name = "EmployerHealthCovOtherOwner", required = true)
    protected String employerHealthCovOtherOwner;
    @XmlElement(name = "EmployerHealthCovPercent", required = true)
    protected String employerHealthCovPercent;
}
