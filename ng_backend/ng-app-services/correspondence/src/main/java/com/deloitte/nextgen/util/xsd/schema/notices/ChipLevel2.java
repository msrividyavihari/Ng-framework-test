package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for chipLevel2 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="chipLevel2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Section" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Application_Month_Year" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Verification_Recvd_Month_year" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Application_Completed_Month" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Ineligible_Month_Year" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="New_Payment_Amt" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chipLevel2", propOrder = {
        "section",
        "applicationMonthYear",
        "verificationRecvdMonthYear",
        "applicationCompletedMonth",
        "ineligibleMonthYear",
        "newPaymentAmt"
})

@Data
public class ChipLevel2 {
    @XmlElement(name = "Section", required = true)
    protected String section;
    @XmlElement(name = "Application_Month_Year", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar applicationMonthYear;
    @XmlElement(name = "Verification_Recvd_Month_year", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar verificationRecvdMonthYear;
    @XmlElement(name = "Application_Completed_Month", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar applicationCompletedMonth;
    @XmlElement(name = "Ineligible_Month_Year", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar ineligibleMonthYear;
    @XmlElement(name = "New_Payment_Amt")
    protected int newPaymentAmt;
}
