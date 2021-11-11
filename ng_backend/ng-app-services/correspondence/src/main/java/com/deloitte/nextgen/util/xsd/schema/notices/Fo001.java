package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * <p>Java class for fo001 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="fo001">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Application" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransferHeader" type="{}transferHeader"/>
 *         &lt;element name="ApplicationMetadata" type="{}applicationMetadata"/>
 *         &lt;element name="PrimaryContactDetails" type="{}primaryContactDetails"/>
 *         &lt;element name="ApplicantDetails" type="{}applicantDetailsData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TaxReturnData" type="{}taxReturnData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="VerificationDetails" type="{}verificationDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="HouseholdMemberDetails" type="{}householdMemberDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="IncomeDeductionDetails" type="{}incomeDeductionDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AuthorizedRepresentativeDetails" type="{}representativeDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SelfEmploymentDetails" type="{}selfEmploymentDetails" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fo001", propOrder = {
        "application",
        "trackingNumber",
        "transferHeader",
        "applicationMetadata",
        "primaryContactDetails",
        "applicantDetails",
        "taxReturnData",
        "verificationDetails",
        "householdMemberDetails",
        "incomeDeductionDetails",
        "authorizedRepresentativeDetails",
        "selfEmploymentDetails"
})

@Data
public class Fo001 {
    @XmlElement(name = "Application", required = true)
    protected String application;
    @XmlElement(name = "TrackingNumber", required = true)
    protected String trackingNumber;
    @XmlElement(name = "TransferHeader", required = true)
    protected TransferHeader transferHeader;
    @XmlElement(name = "ApplicationMetadata", required = true)
    protected ApplicationMetadata applicationMetadata;
    @XmlElement(name = "PrimaryContactDetails", required = true)
    protected PrimaryContactDetails primaryContactDetails;
    @XmlElement(name = "ApplicantDetails")
    protected List<ApplicantDetailsData> applicantDetails;
    @XmlElement(name = "TaxReturnData")
    protected List<TaxReturnData> taxReturnData;
    @XmlElement(name = "VerificationDetails")
    protected List<VerificationDetails> verificationDetails;
    @XmlElement(name = "HouseholdMemberDetails")
    protected List<HouseholdMemberDetails> householdMemberDetails;
    @XmlElement(name = "IncomeDeductionDetails")
    protected List<IncomeDeductionDetails> incomeDeductionDetails;
    @XmlElement(name = "AuthorizedRepresentativeDetails")
    protected List<RepresentativeDetails> authorizedRepresentativeDetails;
    @XmlElement(name="SelfEmploymentDetails")
    protected List<SelfEmploymentDetails> selfEmploymentDetails;
}
