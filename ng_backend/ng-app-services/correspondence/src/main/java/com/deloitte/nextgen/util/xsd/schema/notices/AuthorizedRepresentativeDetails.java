package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthorizedRepresentativeDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AuthorizedRepresentativeDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authRepName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authRepOrg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authRepID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Address" type="{}address"/>
 *         &lt;element name="authRepRelationship" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorizedRepresentativeDetails", propOrder = {
        "recordStatus",
        "authRepName",
        "authRepOrg",
        "authRepID",
        "address",
        "authRepRelationship"
})

@Data

public class AuthorizedRepresentativeDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(required = true)
    protected String authRepName;
    @XmlElement(required = true)
    protected String authRepOrg;
    @XmlElement(required = true)
    protected String authRepID;
    @XmlElement(name = "Address", required = true)
    protected Address address;
    @XmlElement(required = true)
    protected String authRepRelationship;
}
