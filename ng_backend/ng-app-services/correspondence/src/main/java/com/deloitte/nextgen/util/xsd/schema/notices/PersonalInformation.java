package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for personalInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="personalInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="First_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Middle_Initial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Last_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Suffix" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SelectedPersonGender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PrimaryLanguage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaritalStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DOB" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Relationship" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personalInformation", propOrder = {
        "firstName",
        "middleInitial",
        "lastName",
        "suffix",
        "selectedPersonGender",
        "primaryLanguage",
        "maritalStatus",
        "dob",
        "ssn",
        "relationship",
        "indvSeqNum"
})

@Data
public class PersonalInformation {
    @XmlElement(name = "First_Name", required = true)
    protected String firstName;
    @XmlElement(name = "Middle_Initial", required = true)
    protected String middleInitial;
    @XmlElement(name = "Last_Name", required = true)
    protected String lastName;
    @XmlElement(name = "Suffix", required = true)
    protected String suffix;
    @XmlElement(name = "SelectedPersonGender", required = true)
    protected String selectedPersonGender;
    @XmlElement(name = "PrimaryLanguage", required = true)
    protected String primaryLanguage;
    @XmlElement(name = "MaritalStatus", required = true)
    protected String maritalStatus;
    @XmlElement(name = "DOB", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dob;
    @XmlElement(name = "SSN", required = true)
    protected String ssn;
    @XmlElement(name = "Relationship", required = true)
    protected String relationship;
    //custom field used for identification of the individual
    protected long indvSeqNum;
}
