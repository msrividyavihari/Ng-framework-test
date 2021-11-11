package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for chipLevel3 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="chipLevel3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Care_Management_Organization_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cert_Nbr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DCH_Contact_Person_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DCH_Contact_Phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Floor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Hearing_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Hearing_Time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Payment_Or_HService" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Section" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chipLevel3", propOrder = {
        "careManagementOrganizationName",
        "certNbr",
        "date",
        "dchContactPersonName",
        "dchContactPhone",
        "floor",
        "hearingDate",
        "hearingTime",
        "paymentOrHService",
        "section"
})

@Data
public class ChipLevel3 {
    @XmlElement(name = "Care_Management_Organization_Name", required = true)
    protected String careManagementOrganizationName;
    @XmlElement(name = "Cert_Nbr", required = true)
    protected String certNbr;
    @XmlElement(name = "Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "DCH_Contact_Person_Name", required = true)
    protected String dchContactPersonName;
    @XmlElement(name = "DCH_Contact_Phone", required = true)
    protected String dchContactPhone;
    @XmlElement(name = "Floor")
    protected int floor;
    @XmlElement(name = "Hearing_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar hearingDate;
    @XmlElement(name = "Hearing_Time", required = true)
    protected String hearingTime;
    @XmlElement(name = "Payment_Or_HService", required = true)
    protected String paymentOrHService;
    @XmlElement(name = "Section", required = true)
    protected String section;
}
