package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for representativeDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="representativeDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authorizedRepresentativePersonalDetails" type="{}personalInformation"/>
 *         &lt;element name="NameOfOrganization" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OrganizationIdentificationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "representativeDetails", propOrder = {
        "authorizedRepresentativePersonalDetails",
        "nameOfOrganization",
        "organizationIdentificationNumber"
})

@Data
public class RepresentativeDetails {
    @XmlElement(required = true)
    protected PersonalInformation authorizedRepresentativePersonalDetails;
    @XmlElement(name = "NameOfOrganization", required = true)
    protected String nameOfOrganization;
    @XmlElement(name = "OrganizationIdentificationNumber", required = true)
    protected String organizationIdentificationNumber;
}
