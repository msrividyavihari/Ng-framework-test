package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for householdMemberDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="householdMemberDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="householdMemberPersonalInfo" type="{}personalInformation"/>
 *         &lt;element name="IncomeTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IncomeAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="IncomeFrequencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DataSourceIncomeFrequencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DataSourcePaymentFrequencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HireDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="TerminationDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="UnempEndDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="TypeofWork" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PayRate" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="DaysPerWeek" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HoursPerWeek" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PayPeriod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PayPeriodEndDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="EmpIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmpName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="householdMemberAddrDetails" type="{}address"/>
 *         &lt;element name="BirthDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="PregnantIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BabiesDue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MemberMedicaidHousehold" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "householdMemberDetails", propOrder = {
        "householdMemberPersonalInfo",
        "incomeTypeCode",
        "incomeAmount",
        "incomeFrequencyCode",
        "dataSourceIncomeFrequencyCode",
        "dataSourcePaymentFrequencyCode",
        "hireDate",
        "terminationDate",
        "unempEndDate",
        "typeofWork",
        "payRate",
        "daysPerWeek",
        "hoursPerWeek",
        "payPeriod",
        "payPeriodEndDate",
        "empIdentifier",
        "empName",
        "householdMemberAddrDetails",
        "birthDate",
        "pregnantIndicator",
        "babiesDue",
        "memberMedicaidHousehold"
})

@Data
public class HouseholdMemberDetails {
    @XmlElement(required = true)
    protected PersonalInformation householdMemberPersonalInfo;
    @XmlElement(name = "IncomeTypeCode", required = true)
    protected String incomeTypeCode;
    @XmlElement(name = "IncomeAmount")
    protected float incomeAmount;
    @XmlElement(name = "IncomeFrequencyCode", required = true)
    protected String incomeFrequencyCode;
    @XmlElement(name = "DataSourceIncomeFrequencyCode", required = true)
    protected String dataSourceIncomeFrequencyCode;
    @XmlElement(name = "DataSourcePaymentFrequencyCode", required = true)
    protected String dataSourcePaymentFrequencyCode;
    @XmlElement(name = "HireDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar hireDate;
    @XmlElement(name = "TerminationDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar terminationDate;
    @XmlElement(name = "UnempEndDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar unempEndDate;
    @XmlElement(name = "TypeofWork", required = true)
    protected String typeofWork;
    @XmlElement(name = "PayRate")
    protected float payRate;
    @XmlElement(name = "DaysPerWeek", required = true)
    protected String daysPerWeek;
    @XmlElement(name = "HoursPerWeek", required = true)
    protected String hoursPerWeek;
    @XmlElement(name = "PayPeriod", required = true)
    protected String payPeriod;
    @XmlElement(name = "PayPeriodEndDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar payPeriodEndDate;
    @XmlElement(name = "EmpIdentifier", required = true)
    protected String empIdentifier;
    @XmlElement(name = "EmpName", required = true)
    protected String empName;
    @XmlElement(required = true)
    protected Address householdMemberAddrDetails;
    @XmlElement(name = "BirthDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDate;
    @XmlElement(name = "PregnantIndicator", required = true)
    protected String pregnantIndicator;
    @XmlElement(name = "BabiesDue")
    protected int babiesDue;
    @XmlElement(name = "MemberMedicaidHousehold", required = true)
    protected String memberMedicaidHousehold;
}
