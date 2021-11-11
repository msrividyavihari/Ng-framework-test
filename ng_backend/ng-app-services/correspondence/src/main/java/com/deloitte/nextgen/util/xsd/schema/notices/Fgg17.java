package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for fgg17 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="fgg17">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Organization_Code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Client_SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxPayer2_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxPayer2_Address" type="{}address"/>
 *         &lt;element name="TaxPayer2_Full_SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SetOff_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Date_Notice_Received" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Deadline_to_Return" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Deadline_to_Answer" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fgg17", propOrder = {
        "organizationCode",
        "clientSSN",
        "taxPayer2Name",
        "taxPayer2Address",
        "taxPayer2FullSSN",
        "setOffDate",
        "dateNoticeReceived",
        "deadlineToReturn",
        "deadlineToAnswer"
})

@Data
public class Fgg17 {

    @XmlElement(name = "Organization_Code", required = true)
    protected String organizationCode;
    @XmlElement(name = "Client_SSN", required = true)
    protected String clientSSN;
    @XmlElement(name = "TaxPayer2_Name", required = true)
    protected String taxPayer2Name;
    @XmlElement(name = "TaxPayer2_Address", required = true)
    protected Address taxPayer2Address;
    @XmlElement(name = "TaxPayer2_Full_SSN", required = true)
    protected String taxPayer2FullSSN;
    @XmlElement(name = "SetOff_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar setOffDate;
    @XmlElement(name = "Date_Notice_Received", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateNoticeReceived;
    @XmlElement(name = "Deadline_to_Return", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deadlineToReturn;
    @XmlElement(name = "Deadline_to_Answer", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deadlineToAnswer;
}