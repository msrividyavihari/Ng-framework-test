package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for physicalAddress complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="physicalAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhyAddress" type="{}address"/>
 *         &lt;element name="Living_Arrangement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LivingStartDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="PreviousState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "physicalAddress", propOrder = {
        "recordStatus",
        "phyAddress",
        "livingArrangement",
        "livingStartDate",
        "previousState"
})

@Data
public class PhysicalAddress {
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "PhyAddress", required = true)
    protected Address phyAddress;
    @XmlElement(name = "Living_Arrangement", required = true)
    protected String livingArrangement;
    @XmlElement(name = "LivingStartDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar livingStartDate;
    @XmlElement(name = "PreviousState", required = true)
    protected String previousState;
}
