package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for applicantDetailsData complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="applicantDetailsData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApplicantPersonalInformation" type="{}personalInformation"/>
 *         &lt;element name="AddressTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApplicantAddressInfo" type="{}address"/>
 *         &lt;element name="CountyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PersonRaceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxFilingStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PersonEthnicityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdentificationNumberType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdentificationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USCitizenIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentTypeOtherCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentExpirationDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ForeignPassportCountryIssuance" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PersonAmericanIndianAlaskaNative" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PersonHispanicSpanishIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IndianTribeStateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PersonRecognizedTribeIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TribeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MarriedIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxFilerCodeType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApplicationFilerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NoFixedAddressIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EligibleImmigrationStatusIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FosterCareIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApplicantDisabled" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApplicantLongTermCare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PersonIncarceratedIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NaturalizedCitizenIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SevisId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ArrivalPrior96Indicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReceivedIndianHealthServicesIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StateEmployeeBenefitPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StudentIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VeteranStatusIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerEin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployeeStatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CobraCoverageAvailableIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CoverageOfferedJob" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DateEsiCoverageStart" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="EmployerMinimumValueStandard" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerLowestCostPlanCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployerLowestCostPlanCost" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="RetireePlanCoverageIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PersonLivingIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applicantDetailsData", propOrder = {
        "applicantPersonalInformation",
        "addressTypeCode",
        "applicantAddressInfo",
        "countyCode",
        "personRaceName",
        "taxFilingStatus",
        "personEthnicityName",
        "identificationNumberType",
        "identificationNumber",
        "usCitizenIndicator",
        "documentTypeCode",
        "documentTypeOtherCode",
        "documentNumber",
        "documentExpirationDate",
        "foreignPassportCountryIssuance",
        "personAmericanIndianAlaskaNative",
        "personHispanicSpanishIndicator",
        "indianTribeStateCode",
        "personRecognizedTribeIndicator",
        "tribeName",
        "marriedIndicator",
        "taxFilerCodeType",
        "applicationFilerCode",
        "noFixedAddressIndicator",
        "eligibleImmigrationStatusIndicator",
        "fosterCareIndicator",
        "applicantDisabled",
        "applicantLongTermCare",
        "personIncarceratedIndicator",
        "naturalizedCitizenIndicator",
        "sevisId",
        "arrivalPrior96Indicator",
        "receivedIndianHealthServicesIndicator",
        "stateEmployeeBenefitPlan",
        "studentIndicator",
        "veteranStatusIndicator",
        "employerEin",
        "employerName",
        "employeeStatusCode",
        "cobraCoverageAvailableIndicator",
        "coverageOfferedJob",
        "dateEsiCoverageStart",
        "employerMinimumValueStandard",
        "employerLowestCostPlanCode",
        "employerLowestCostPlanCost",
        "retireePlanCoverageIndicator",
        "personLivingIndicator"
})

@Data
public class ApplicantDetailsData {
    @XmlElement(name = "ApplicantPersonalInformation", required = true)
    protected PersonalInformation applicantPersonalInformation;
    @XmlElement(name = "AddressTypeCode", required = true)
    protected String addressTypeCode;
    @XmlElement(name = "ApplicantAddressInfo", required = true)
    protected Address applicantAddressInfo;
    @XmlElement(name = "CountyCode", required = true)
    protected String countyCode;
    @XmlElement(name = "PersonRaceName", required = true)
    protected String personRaceName;
    @XmlElement(name = "TaxFilingStatus", required = true)
    protected String taxFilingStatus;
    @XmlElement(name = "PersonEthnicityName", required = true)
    protected String personEthnicityName;
    @XmlElement(name = "IdentificationNumberType", required = true)
    protected String identificationNumberType;
    @XmlElement(name = "IdentificationNumber", required = true)
    protected String identificationNumber;
    @XmlElement(name = "USCitizenIndicator", required = true)
    protected String usCitizenIndicator;
    @XmlElement(name = "DocumentTypeCode", required = true)
    protected String documentTypeCode;
    @XmlElement(name = "DocumentTypeOtherCode", required = true)
    protected String documentTypeOtherCode;
    @XmlElement(name = "DocumentNumber", required = true)
    protected String documentNumber;
    @XmlElement(name = "DocumentExpirationDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar documentExpirationDate;
    @XmlElement(name = "ForeignPassportCountryIssuance", required = true)
    protected String foreignPassportCountryIssuance;
    @XmlElement(name = "PersonAmericanIndianAlaskaNative", required = true)
    protected String personAmericanIndianAlaskaNative;
    @XmlElement(name = "PersonHispanicSpanishIndicator", required = true)
    protected String personHispanicSpanishIndicator;
    @XmlElement(name = "IndianTribeStateCode", required = true)
    protected String indianTribeStateCode;
    @XmlElement(name = "PersonRecognizedTribeIndicator", required = true)
    protected String personRecognizedTribeIndicator;
    @XmlElement(name = "TribeName", required = true)
    protected String tribeName;
    @XmlElement(name = "MarriedIndicator", required = true)
    protected String marriedIndicator;
    @XmlElement(name = "TaxFilerCodeType", required = true)
    protected String taxFilerCodeType;
    @XmlElement(name = "ApplicationFilerCode", required = true)
    protected String applicationFilerCode;
    @XmlElement(name = "NoFixedAddressIndicator", required = true)
    protected String noFixedAddressIndicator;
    @XmlElement(name = "EligibleImmigrationStatusIndicator", required = true)
    protected String eligibleImmigrationStatusIndicator;
    @XmlElement(name = "FosterCareIndicator", required = true)
    protected String fosterCareIndicator;
    @XmlElement(name = "ApplicantDisabled", required = true)
    protected String applicantDisabled;
    @XmlElement(name = "ApplicantLongTermCare", required = true)
    protected String applicantLongTermCare;
    @XmlElement(name = "PersonIncarceratedIndicator", required = true)
    protected String personIncarceratedIndicator;
    @XmlElement(name = "NaturalizedCitizenIndicator", required = true)
    protected String naturalizedCitizenIndicator;
    @XmlElement(name = "SevisId", required = true)
    protected String sevisId;
    @XmlElement(name = "ArrivalPrior96Indicator", required = true)
    protected String arrivalPrior96Indicator;
    @XmlElement(name = "ReceivedIndianHealthServicesIndicator", required = true)
    protected String receivedIndianHealthServicesIndicator;
    @XmlElement(name = "StateEmployeeBenefitPlan", required = true)
    protected String stateEmployeeBenefitPlan;
    @XmlElement(name = "StudentIndicator", required = true)
    protected String studentIndicator;
    @XmlElement(name = "VeteranStatusIndicator", required = true)
    protected String veteranStatusIndicator;
    @XmlElement(name = "EmployerEin", required = true)
    protected String employerEin;
    @XmlElement(name = "EmployerName", required = true)
    protected String employerName;
    @XmlElement(name = "EmployeeStatusCode", required = true)
    protected String employeeStatusCode;
    @XmlElement(name = "CobraCoverageAvailableIndicator", required = true)
    protected String cobraCoverageAvailableIndicator;
    @XmlElement(name = "CoverageOfferedJob", required = true)
    protected String coverageOfferedJob;
    @XmlElement(name = "DateEsiCoverageStart", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateEsiCoverageStart;
    @XmlElement(name = "EmployerMinimumValueStandard", required = true)
    protected String employerMinimumValueStandard;
    @XmlElement(name = "EmployerLowestCostPlanCode", required = true)
    protected String employerLowestCostPlanCode;
    @XmlElement(name = "EmployerLowestCostPlanCost")
    protected float employerLowestCostPlanCost;
    @XmlElement(name = "RetireePlanCoverageIndicator", required = true)
    protected String retireePlanCoverageIndicator;
    @XmlElement(name = "PersonLivingIndicator", required = true)
    protected String personLivingIndicator;
}
