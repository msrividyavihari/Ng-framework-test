package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for clientAppointments complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="clientAppointments">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApptDay" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApptDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ApptTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Due_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="OfficeLocationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OfficeLocation" type="{}address"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clientAppointments", propOrder = {
        "apptDay",
        "apptDate",
        "apptTime",
        "dueDate",
        "officeLocationName",
        "officeLocation"
})
@Data
public class ClientAppointments {
    @XmlElement(name = "ApptDay", required = true)
    protected String apptDay;
    @XmlElement(name = "ApptDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar apptDate;
    @XmlElement(name = "ApptTime", required = true)
    protected String apptTime;
    @XmlElement(name = "Due_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dueDate;
    @XmlElement(name = "OfficeLocationName", required = true)
    protected String officeLocationName;
    @XmlElement(name = "OfficeLocation", required = true)
    protected Address officeLocation;
}
