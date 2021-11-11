package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for homePersonalInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="homePersonalInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HomePerInformation" type="{}personalInformation"/>
 *         &lt;element name="OtherLanguage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Interpreter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AssistanceCommunicating" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HelpCommunicating" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Home_Living_Arrangement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OutOfHome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FileTaxes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ClaimedAsDependent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SelectedPrograms" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AltName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AlternativeNameInformation" type="{}personalInformation"/>
 *         &lt;element name="SocialSecurityNumberInformation" type="{}socialSecurityNumberInformation"/>
 *         &lt;element name="EthnicityType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RaceInfo" type="{}raceInfo"/>
 *         &lt;element name="PeopleInHome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PersonalInformationContinued" type="{}personalInformationContinued"/>
 *         &lt;element name="ImmigrationInformation" type="{}immigrationInformation"/>
 *         &lt;element name="MilitaryInformation" type="{}militaryInformation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "homePersonalInformation", propOrder = {
        "recordStatus",
        "user",
        "homePerInformation",
        "otherLanguage",
        "interpreter",
        "assistanceCommunicating",
        "helpCommunicating",
        "homeLivingArrangement",
        "outOfHome",
        "fileTaxes",
        "claimedAsDependent",
        "selectedPrograms",
        "altName",
        "alternativeNameInformation",
        "socialSecurityNumberInformation",
        "ethnicityType",
        "raceInfo",
        "peopleInHome",
        "personalInformationContinued",
        "immigrationInformation",
        "militaryInformation"
})

@Data
public class HomePersonalInformation {
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "HomePerInformation", required = true)
    protected PersonalInformation homePerInformation;
    @XmlElement(name = "OtherLanguage", required = true)
    protected String otherLanguage;
    @XmlElement(name = "Interpreter", required = true)
    protected String interpreter;
    @XmlElement(name = "AssistanceCommunicating", required = true)
    protected String assistanceCommunicating;
    @XmlElement(name = "HelpCommunicating", required = true)
    protected String helpCommunicating;
    @XmlElement(name = "Home_Living_Arrangement", required = true)
    protected String homeLivingArrangement;
    @XmlElement(name = "OutOfHome", required = true)
    protected String outOfHome;
    @XmlElement(name = "FileTaxes", required = true)
    protected String fileTaxes;
    @XmlElement(name = "ClaimedAsDependent", required = true)
    protected String claimedAsDependent;
    @XmlElement(name = "SelectedPrograms", required = true)
    protected String selectedPrograms;
    @XmlElement(name = "AltName", required = true)
    protected String altName;
    @XmlElement(name = "AlternativeNameInformation", required = true)
    protected PersonalInformation alternativeNameInformation;
    @XmlElement(name = "SocialSecurityNumberInformation", required = true)
    protected SocialSecurityNumberInformation socialSecurityNumberInformation;
    @XmlElement(name = "EthnicityType", required = true)
    protected String ethnicityType;
    @XmlElement(name = "RaceInfo", required = true)
    protected RaceInfo raceInfo;
    @XmlElement(name = "PeopleInHome", required = true)
    protected String peopleInHome;
    @XmlElement(name = "PersonalInformationContinued", required = true)
    protected PersonalInformationContinued personalInformationContinued;
    @XmlElement(name = "ImmigrationInformation", required = true)
    protected ImmigrationInformation immigrationInformation;
    @XmlElement(name = "MilitaryInformation", required = true)
    protected MilitaryInformation militaryInformation;
}
