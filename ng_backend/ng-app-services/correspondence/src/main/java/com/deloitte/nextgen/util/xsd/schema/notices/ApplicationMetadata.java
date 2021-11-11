package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for applicationMetadata complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="applicationMetadata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApplicationSignatureDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ApplyingFinancialAssistanceIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApplicationTransferDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ApplicationTransferTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applicationMetadata", propOrder = {
        "applicationSignatureDate",
        "applyingFinancialAssistanceIndicator",
        "applicationTransferDate",
        "applicationTransferTime"
})

@Data
public class ApplicationMetadata {
    @XmlElement(name = "ApplicationSignatureDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar applicationSignatureDate;
    @XmlElement(name = "ApplyingFinancialAssistanceIndicator", required = true)
    protected String applyingFinancialAssistanceIndicator;
    @XmlElement(name = "ApplicationTransferDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar applicationTransferDate;
    @XmlElement(name = "ApplicationTransferTime", required = true)
    protected String applicationTransferTime;
}
