package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fgga0052 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="fgga0052">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Program" type="{}prog"/>
 *         &lt;element name="ApplicationInfo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Sumb" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactInformation" type="{}contactInformation"/>
 *         &lt;element name="ApplyingAddress" type="{}applyingAddress"/>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programsApply" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApplicantDetails" type="{}applicantDetails"/>
 *         &lt;element name="PhysicalAddress" type="{}physicalAddress"/>
 *         &lt;element name="MailingAddress" type="{}address"/>
 *         &lt;element name="PreviousAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BasicContactInformation" type="{}basicContactInformation"/>
 *         &lt;element name="HomePersonalInformation" type="{}homePersonalInformation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DeclarationOfCitizenship" type="{}declarationOfCitizenship" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PregnancyInformation" type="{}pregnancyInformation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RelationshipInformation" type="{}relationshipInformation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PregnancyDetails" type="{}pregnancyDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DisabilityOrBlindness" type="{}disabilityOrBlindness" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TaxReturn" type="{}taxReturn" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PrimaryCaretaker" type="{}care" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AbsentParentDetails" type="{}absentParentEmploymentInformation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OtherHouseholdQuestions" type="{}otherHouseholdQuestions"/>
 *         &lt;element name="ConvictedFelon" type="{}convictedFelon" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OutOfState" type="{}outOfState" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OtherBenefits" type="{}otherBenefits"/>
 *         &lt;element name="MedicareUser" type="{}medicareUser" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="HealthInsuranceDetails" type="{}healthInsuranceDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OtherInsu" type="{}otherInsu" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="HospitalStay" type="{}hospitalStay" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LostHealthInsurance" type="{}insurance" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="HospiceFacilityName" type="{}hospiceFacilityName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="NursingHomeName" type="{}nursingHomeName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CashDetails" type="{}cash" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FinancialAccountDetails" type="{}financialAccountDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OtherLiquidResourcesBankOrCompany" type="{}OtherLiquidResourcesBankOrCompany" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SoldTradedResources" type="{}soldTradedResources" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="VehicleDetails" type="{}vehicleDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RealEstateDetails" type="{}realEstateDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BurialResourceDetails" type="{}burial" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LifeInsuranceDetails" type="{}lifeInsuranceDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OtherResourceDetails" type="{}otherResourceDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="EmployerDetails" type="{}employerDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="EmployerHealthCoverageDetails" type="{}employerHealthCoverageDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelfEmploymentDetails" type="{}selfEmploymentDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="InKindIncomeDetails" type="{}inKindIncomeDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DCSSChildSupportIncomeDetails" type="{}dCSSChildSupportIncomeDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DirectChildSupportIncomeDetails" type="{}directChildSupportIncomeDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Supplemental" type="{}supplemental" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SocialSecurityRSDIIncomeDetails" type="{}SocialSecurityRSDIIncomeDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="VeteransBenefitsIncomeDetails" type="{}VeteransBenefitsIncomeDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ContributionsIncome" type="{}contributionsIncome" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OtherTypesOfIncomeDetails" type="{}OtherTypesOfIncomeDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AmericanIndianDetails" type="{}americanIndianDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AlaskaNativeIncomeDetails" type="{}alaskaNativeDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="HousingAssistanceApply" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HousingAssistanceUtility" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HousingBillsDetails" type="{}HousingBillsDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PropertyTaxDetails" type="{}propertyTaxDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="UtilityDetails" type="{}utilityDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OtherBillsDetails" type="{}otherBillsDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ChildAndAdultCareDetails" type="{}ChildAndAdultCareDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ChildSupportDetails" type="{}ChildSupportDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BeforeTaxDeductDetails" type="{}beforeTaxDeductDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="IncomeTaxDeductDetails" type="{}incomeTaxDeductDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MedicalBillsDetails" type="{}medicalBillsDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SchoolEnrollmentDetails" type="{}SchoolEnrollmentDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AuthorizedRepresentativeDetails" type="{}AuthorizedRepresentativeDetails"/>
 *         &lt;element name="SigningYourApplicationDetails" type="{}signingYourApplicationDetails"/>
 *         &lt;element name="ElectronicallySigned" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SignedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fgga0052", propOrder = {
        "program",
        "applicationInfo",
        "trackingNumber",
        "sumb",
        "contactInformation",
        "applyingAddress",
        "recordStatus",
        "programsApply",
        "applicantDetails",
        "physicalAddress",
        "mailingAddress",
        "previousAddress",
        "basicContactInformation",
        "homePersonalInformation",
        "declarationOfCitizenship",
        "pregnancyInformation",
        "relationshipInformation",
        "pregnancyDetails",
        "disabilityOrBlindness",
        "taxReturn",
        "primaryCaretaker",
        "absentParentDetails",
        "otherHouseholdQuestions",
        "convictedFelon",
        "outOfState",
        "otherBenefits",
        "medicareUser",
        "healthInsuranceDetails",
        "otherInsu",
        "hospitalStay",
        "lostHealthInsurance",
        "hospiceFacilityName",
        "nursingHomeName",
        "cashDetails",
        "financialAccountDetails",
        "otherLiquidResourcesBankOrCompany",
        "soldTradedResources",
        "vehicleDetails",
        "realEstateDetails",
        "burialResourceDetails",
        "lifeInsuranceDetails",
        "otherResourceDetails",
        "employerDetails",
        "employerHealthCoverageDetails",
        "selfEmploymentDetails",
        "inKindIncomeDetails",
        "dcssChildSupportIncomeDetails",
        "directChildSupportIncomeDetails",
        "supplemental",
        "socialSecurityRSDIIncomeDetails",
        "veteransBenefitsIncomeDetails",
        "contributionsIncome",
        "otherTypesOfIncomeDetails",
        "americanIndianDetails",
        "alaskaNativeIncomeDetails",
        "housingAssistanceApply",
        "housingAssistanceUtility",
        "housingBillsDetails",
        "propertyTaxDetails",
        "utilityDetails",
        "otherBillsDetails",
        "childAndAdultCareDetails",
        "childSupportDetails",
        "beforeTaxDeductDetails",
        "incomeTaxDeductDetails",
        "medicalBillsDetails",
        "schoolEnrollmentDetails",
        "authorizedRepresentativeDetails",
        "signingYourApplicationDetails",
        "electronicallySigned",
        "signedBy"
})

@Data
public class Fgga0052 {

    @XmlElement(name = "Program", required = true)
    protected Prog program;
    @XmlElement(name = "ApplicationInfo", required = true)
    protected String applicationInfo;
    @XmlElement(name = "TrackingNumber", required = true)
    protected String trackingNumber;
    @XmlElement(name = "Sumb", required = true)
    protected String sumb;
    @XmlElement(name = "ContactInformation", required = true)
    protected ContactInformation contactInformation;
    @XmlElement(name = "ApplyingAddress", required = true)
    protected ApplyingAddress applyingAddress;
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(required = true)
    protected String programsApply;
    @XmlElement(name = "ApplicantDetails", required = true)
    protected ApplicantDetails applicantDetails;
    @XmlElement(name = "PhysicalAddress", required = true)
    protected PhysicalAddress physicalAddress;
    @XmlElement(name = "MailingAddress", required = true)
    protected Address mailingAddress;
    @XmlElement(name = "PreviousAddress", required = true)
    protected String previousAddress;
    @XmlElement(name = "BasicContactInformation", required = true)
    protected BasicContactInformation basicContactInformation;
    @XmlElement(name = "HomePersonalInformation")
    protected List<HomePersonalInformation> homePersonalInformation;
    @XmlElement(name = "DeclarationOfCitizenship")
    protected List<DeclarationOfCitizenship> declarationOfCitizenship;
    @XmlElement(name = "PregnancyInformation")
    protected List<PregnancyInformation> pregnancyInformation;
    @XmlElement(name = "RelationshipInformation")
    protected List<RelationshipInformation> relationshipInformation;
    @XmlElement(name = "PregnancyDetails")
    protected List<PregnancyDetails> pregnancyDetails;
    @XmlElement(name = "DisabilityOrBlindness")
    protected List<DisabilityOrBlindness> disabilityOrBlindness;
    @XmlElement(name = "TaxReturn")
    protected List<TaxReturn> taxReturn;
    @XmlElement(name = "PrimaryCaretaker")
    protected List<Care> primaryCaretaker;
    @XmlElement(name = "AbsentParentDetails")
    protected List<AbsentParentEmploymentInformation> absentParentDetails;
    @XmlElement(name = "OtherHouseholdQuestions", required = true)
    protected OtherHouseholdQuestions otherHouseholdQuestions;
    @XmlElement(name = "ConvictedFelon")
    protected List<ConvictedFelon> convictedFelon;
    @XmlElement(name = "OutOfState")
    protected List<OutOfState> outOfState;
    @XmlElement(name = "OtherBenefits", required = true)
    protected OtherBenefits otherBenefits;
    @XmlElement(name = "MedicareUser")
    protected List<MedicareUser> medicareUser;
    @XmlElement(name = "HealthInsuranceDetails")
    protected List<HealthInsuranceDetails> healthInsuranceDetails;
    @XmlElement(name = "OtherInsu")
    protected List<OtherInsu> otherInsu;
    @XmlElement(name = "HospitalStay")
    protected List<HospitalStay> hospitalStay;
    @XmlElement(name = "LostHealthInsurance")
    protected List<Insurance> lostHealthInsurance;
    @XmlElement(name = "HospiceFacilityName")
    protected List<HospiceFacilityName> hospiceFacilityName;
    @XmlElement(name = "NursingHomeName")
    protected List<NursingHomeName> nursingHomeName;
    @XmlElement(name = "CashDetails")
    protected List<Cash> cashDetails;
    @XmlElement(name = "FinancialAccountDetails")
    protected List<FinancialAccountDetails> financialAccountDetails;
    @XmlElement(name = "OtherLiquidResourcesBankOrCompany")
    protected List<OtherLiquidResourcesBankOrCompany> otherLiquidResourcesBankOrCompany;
    @XmlElement(name = "SoldTradedResources")
    protected List<SoldTradedResources> soldTradedResources;
    @XmlElement(name = "VehicleDetails")
    protected List<VehicleDetails> vehicleDetails;
    @XmlElement(name = "RealEstateDetails")
    protected List<RealEstateDetails> realEstateDetails;
    @XmlElement(name = "BurialResourceDetails")
    protected List<Burial> burialResourceDetails;
    @XmlElement(name = "LifeInsuranceDetails")
    protected List<LifeInsuranceDetails> lifeInsuranceDetails;
    @XmlElement(name = "OtherResourceDetails")
    protected List<OtherResourceDetails> otherResourceDetails;
    @XmlElement(name = "EmployerDetails")
    protected List<EmployerDetails> employerDetails;
    @XmlElement(name = "EmployerHealthCoverageDetails")
    protected List<EmployerHealthCoverageDetails> employerHealthCoverageDetails;
    @XmlElement(name = "SelfEmploymentDetails")
    protected List<SelfEmploymentDetails> selfEmploymentDetails;
    @XmlElement(name = "InKindIncomeDetails")
    protected List<InKindIncomeDetails> inKindIncomeDetails;
    @XmlElement(name = "DCSSChildSupportIncomeDetails")
    protected List<DCSSChildSupportIncomeDetails> dcssChildSupportIncomeDetails;
    @XmlElement(name = "DirectChildSupportIncomeDetails")
    protected List<DirectChildSupportIncomeDetails> directChildSupportIncomeDetails;
    @XmlElement(name = "Supplemental")
    protected List<Supplemental> supplemental;
    @XmlElement(name = "SocialSecurityRSDIIncomeDetails")
    protected List<SocialSecurityRSDIIncomeDetails> socialSecurityRSDIIncomeDetails;
    @XmlElement(name = "VeteransBenefitsIncomeDetails")
    protected List<VeteransBenefitsIncomeDetails> veteransBenefitsIncomeDetails;
    @XmlElement(name = "ContributionsIncome")
    protected List<ContributionsIncome> contributionsIncome;
    @XmlElement(name = "OtherTypesOfIncomeDetails")
    protected List<OtherTypesOfIncomeDetails> otherTypesOfIncomeDetails;
    @XmlElement(name = "AmericanIndianDetails")
    protected List<AmericanIndianDetails> americanIndianDetails;
    @XmlElement(name = "AlaskaNativeIncomeDetails")
    protected List<AlaskaNativeDetails> alaskaNativeIncomeDetails;
    @XmlElement(name = "HousingAssistanceApply", required = true)
    protected String housingAssistanceApply;
    @XmlElement(name = "HousingAssistanceUtility", required = true)
    protected String housingAssistanceUtility;
    @XmlElement(name = "HousingBillsDetails")
    protected List<HousingBillsDetails> housingBillsDetails;
    @XmlElement(name = "PropertyTaxDetails")
    protected List<PropertyTaxDetails> propertyTaxDetails;
    @XmlElement(name = "UtilityDetails")
    protected List<UtilityDetails> utilityDetails;
    @XmlElement(name = "OtherBillsDetails")
    protected List<OtherBillsDetails> otherBillsDetails;
    @XmlElement(name = "ChildAndAdultCareDetails")
    protected List<ChildAndAdultCareDetails> childAndAdultCareDetails;
    @XmlElement(name = "ChildSupportDetails")
    protected List<ChildSupportDetails> childSupportDetails;
    @XmlElement(name = "BeforeTaxDeductDetails")
    protected List<BeforeTaxDeductDetails> beforeTaxDeductDetails;
    @XmlElement(name = "IncomeTaxDeductDetails")
    protected List<IncomeTaxDeductDetails> incomeTaxDeductDetails;
    @XmlElement(name = "MedicalBillsDetails")
    protected List<MedicalBillsDetails> medicalBillsDetails;
    @XmlElement(name = "SchoolEnrollmentDetails")
    protected List<SchoolEnrollmentDetails> schoolEnrollmentDetails;
    @XmlElement(name = "AuthorizedRepresentativeDetails", required = true)
    protected AuthorizedRepresentativeDetails authorizedRepresentativeDetails;
    @XmlElement(name = "SigningYourApplicationDetails", required = true)
    protected SigningYourApplicationDetails signingYourApplicationDetails;
    @XmlElement(name = "ElectronicallySigned", required = true)
    protected String electronicallySigned;
    @XmlElement(name = "SignedBy", required = true)
    protected String signedBy;
}
