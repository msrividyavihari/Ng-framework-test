package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for employerDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="employerDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentJobEmployer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentJobEIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentJobAddress" type="{}address"/>
 *         &lt;element name="CurrentJobStrike" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentJobEnd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentJobStart" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="CurrentJobPayDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="CurrentJobEndDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="CurrentJobFinalPaycheck" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="CurrentJobFinalPayAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentJobPayFreq" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentJobAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentJobHoursWorked" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentJobPayRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentJobComments" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employerDetails", propOrder = {
        "recordStatus",
        "user",
        "currentJobEmployer",
        "currentJobEIN",
        "currentJobAddress",
        "currentJobStrike",
        "currentJobEnd",
        "currentJobStart",
        "currentJobPayDate",
        "currentJobEndDate",
        "currentJobFinalPaycheck",
        "currentJobFinalPayAmount",
        "currentJobPayFreq",
        "currentJobAmount",
        "currentJobHoursWorked",
        "currentJobPayRate",
        "currentJobComments"
})

@Data
public class EmployerDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "CurrentJobEmployer", required = true)
    protected String currentJobEmployer;
    @XmlElement(name = "CurrentJobEIN", required = true)
    protected String currentJobEIN;
    @XmlElement(name = "CurrentJobAddress", required = true)
    protected Address currentJobAddress;
    @XmlElement(name = "CurrentJobStrike", required = true)
    protected String currentJobStrike;
    @XmlElement(name = "CurrentJobEnd", required = true)
    protected String currentJobEnd;
    @XmlElement(name = "CurrentJobStart", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar currentJobStart;
    @XmlElement(name = "CurrentJobPayDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar currentJobPayDate;
    @XmlElement(name = "CurrentJobEndDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar currentJobEndDate;
    @XmlElement(name = "CurrentJobFinalPaycheck", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar currentJobFinalPaycheck;
    @XmlElement(name = "CurrentJobFinalPayAmount", required = true)
    protected String currentJobFinalPayAmount;
    @XmlElement(name = "CurrentJobPayFreq", required = true)
    protected String currentJobPayFreq;
    @XmlElement(name = "CurrentJobAmount", required = true)
    protected String currentJobAmount;
    @XmlElement(name = "CurrentJobHoursWorked", required = true)
    protected String currentJobHoursWorked;
    @XmlElement(name = "CurrentJobPayRate", required = true)
    protected String currentJobPayRate;
    @XmlElement(name = "CurrentJobComments", required = true)
    protected String currentJobComments;
}
