package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for repeat_person_ref complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="repeat_person_ref">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Person_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Verification_Item" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Due_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Verification_List" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Person_Name_FS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Verification_Item_FS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Time_Period_FS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Due_Date_FS" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Verification_Value_FS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "repeat_person_ref", propOrder = {
        "personName",
        "verificationItem",
        "dueDate",
        "verificationList",
        "personNameFS",
        "verificationItemFS",
        "timePeriodFS",
        "dueDateFS",
        "verificationValueFS"
})

@Data
public class RepeatPersonRef {
    @XmlElement(name = "Person_Name", required = true)
    protected String personName;
    @XmlElement(name = "Verification_Item", required = true)
    protected String verificationItem;
    @XmlElement(name = "Due_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dueDate;
    @XmlElement(name = "Verification_List", required = true)
    protected String verificationList;
    @XmlElement(name = "Person_Name_FS", required = true)
    protected String personNameFS;
    @XmlElement(name = "Verification_Item_FS", required = true)
    protected String verificationItemFS;
    @XmlElement(name = "Time_Period_FS", required = true)
    protected String timePeriodFS;
    @XmlElement(name = "Due_Date_FS", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dueDateFS;
    @XmlElement(name = "Verification_Value_FS", required = true)
    protected String verificationValueFS;
}