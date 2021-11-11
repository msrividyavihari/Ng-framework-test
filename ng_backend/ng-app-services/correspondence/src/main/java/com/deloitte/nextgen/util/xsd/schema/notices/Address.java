package com.deloitte.nextgen.util.xsd.schema.notices;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for address complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NursingHomeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Street1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Street2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Zip5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Zip4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="County" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FaxNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HomePhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CellPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WorkPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Extension" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {
        "company",
        "nursingHomeName",
        "street1",
        "street2",
        "city",
        "state",
        "zip5",
        "zip4",
        "county",
        "faxNumber",
        "homePhone",
        "cellPhone",
        "workPhone",
        "extension",
        "email",
        "userID",
        "recordStatus"
})
@Data
public class Address {

    @XmlElement(name = "Company", required = true)
    protected String company;
    @XmlElement(name = "NursingHomeName", required = true)
    protected String nursingHomeName;
    @XmlElement(name = "Street1", required = true)
    protected String street1;
    @XmlElement(name = "Street2", required = true)
    protected String street2;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "State", required = true)
    protected String state;
    @XmlElement(name = "Zip5", required = true)
    protected String zip5;
    @XmlElement(name = "Zip4", required = true)
    protected String zip4;
    @XmlElement(name = "County", required = true)
    protected String county;
    @XmlElement(name = "FaxNumber", required = true)
    protected String faxNumber;
    @XmlElement(name = "HomePhone", required = true)
    protected String homePhone;
    @XmlElement(name = "CellPhone", required = true)
    protected String cellPhone;
    @XmlElement(name = "WorkPhone", required = true)
    protected String workPhone;
    @XmlElement(name = "Extension", required = true)
    protected String extension;
    @XmlElement(name = "Email", required = true)
    protected String email;
    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
}

