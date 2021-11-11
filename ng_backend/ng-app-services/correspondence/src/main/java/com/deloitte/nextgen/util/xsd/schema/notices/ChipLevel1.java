package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

/**
 * <p>Java class for chipLevel1 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="chipLevel1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Section" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Payment_Amt" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Due_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Service_Month_Year" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Acct_Credit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Amt_Due" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ChildTurning6" type="{}childNameAppeal" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Birth_Month" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Change_Effective_Month_Year" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="New_Payment_Amt" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="New_Due_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chipLevel1", propOrder = {
        "section",
        "paymentAmt",
        "dueDate",
        "serviceMonthYear",
        "acctCredit",
        "amtDue",
        "childTurning6",
        "birthMonth",
        "changeEffectiveMonthYear",
        "newPaymentAmt",
        "newDueDate"
})

@Data
public class ChipLevel1 {
    @XmlElement(name = "Section", required = true)
    protected String section;
    @XmlElement(name = "Payment_Amt")
    protected int paymentAmt;
    @XmlElement(name = "Due_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dueDate;
    @XmlElement(name = "Service_Month_Year", required = true)
    protected String serviceMonthYear;
    @XmlElement(name = "Acct_Credit")
    protected int acctCredit;
    @XmlElement(name = "Amt_Due")
    protected int amtDue;
    @XmlElement(name = "ChildTurning6")
    protected List<ChildNameAppeal> childTurning6;
    @XmlElement(name = "Birth_Month", required = true)
    protected String birthMonth;
    @XmlElement(name = "Change_Effective_Month_Year", required = true)
    protected String changeEffectiveMonthYear;
    @XmlElement(name = "New_Payment_Amt")
    protected int newPaymentAmt;
    @XmlElement(name = "New_Due_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar newDueDate;
}
