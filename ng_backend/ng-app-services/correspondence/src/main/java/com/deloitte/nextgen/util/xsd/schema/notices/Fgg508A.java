package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


/**
 * <p>Java class for fgg508a complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="fgg508a">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Case_Res_Address" type="{}address"/>
 *         &lt;element name="Authorized_Rep" type="{}authorized_rep_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Household_Members" type="{}household_members_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Foster_Care_at_age_18" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Household_Medicaid_Members" type="{}household_members_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Earned_Income" type="{}earned_income_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Business_Name" type="{}business_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Unearned_Income" type="{}unearned_income_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Dependent_Care_Costs" type="{}dependent_care_costs_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Resources" type="{}resources_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Student_in_Higher_Education" type="{}student_in_higher_education_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Child_Support_Deduction" type="{}child_support_deduction_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Medicaid_Applicants" type="{}medicaid_applicants_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FoodStamps_Medical" type="{}foodStamps_medical_ref" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Rent_Mortgage" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Property_Tax" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Property_Insurance" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Utility" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Other_Cost" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="TANF" type="{}tanf_ref" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fgg508a", propOrder = {
        "caseResAddress",
        "authorizedRep",
        "householdMembers",
        "fosterCareAtAge18",
        "householdMedicaidMembers",
        "earnedIncome",
        "businessName",
        "unearnedIncome",
        "dependentCareCosts",
        "resources",
        "studentInHigherEducation",
        "childSupportDeduction",
        "medicaidApplicants",
        "foodStampsMedical",
        "rentMortgage",
        "propertyTax",
        "propertyInsurance",
        "utility",
        "otherCost",
        "tanf"
})

@Data
public class Fgg508A {

    @XmlElement(name = "Case_Res_Address", required = true)
    protected Address caseResAddress;
    @XmlElement(name = "Authorized_Rep")
    protected List<AuthorizedRepRef> authorizedRep;
    @XmlElement(name = "Household_Members")
    protected List<HouseholdMembersRef> householdMembers;
    @XmlElement(name = "Foster_Care_at_age_18", required = true)
    protected String fosterCareAtAge18;
    @XmlElement(name = "Household_Medicaid_Members")
    protected List<HouseholdMembersRef> householdMedicaidMembers;
    @XmlElement(name = "Earned_Income")
    protected List<EarnedIncomeRef> earnedIncome;
    @XmlElement(name = "Business_Name")
    protected List<BusinessRef> businessName;
    @XmlElement(name = "Unearned_Income")
    protected List<UnearnedIncomeRef> unearnedIncome;
    @XmlElement(name = "Dependent_Care_Costs")
    protected List<DependentCareCostsRef> dependentCareCosts;
    @XmlElement(name = "Resources")
    protected List<ResourcesRef> resources;
    @XmlElement(name = "Student_in_Higher_Education")
    protected List<StudentInHigherEducationRef> studentInHigherEducation;
    @XmlElement(name = "Child_Support_Deduction")
    protected List<ChildSupportDeducationRef> childSupportDeduction;
    @XmlElement(name = "Medicaid_Applicants")
    protected List<MedicaidApplicantsRef> medicaidApplicants;
    @XmlElement(name = "FoodStamps_Medical")
    protected List<FoodstampsMedicalRef> foodStampsMedical;
    @XmlElement(name = "Rent_Mortgage")
    protected float rentMortgage;
    @XmlElement(name = "Property_Tax")
    protected float propertyTax;
    @XmlElement(name = "Property_Insurance")
    protected float propertyInsurance;
    @XmlElement(name = "Utility")
    protected float utility;
    @XmlElement(name = "Other_Cost")
    protected float otherCost;
    @XmlElement(name = "TANF")
    protected List<TanfRef> tanf;
}
