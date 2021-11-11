package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ngga0034 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ngga0034">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Repeatable_Program" type="{}repeat_program_ref" maxOccurs="500" minOccurs="0"/>
 *         &lt;element name="Repeatable_Program_ChildCare" type="{}repeat_program_ref" maxOccurs="500" minOccurs="0"/>
 *         &lt;element name="Repeatable_Program_FS" type="{}repeat_program_ref" maxOccurs="500" minOccurs="0"/>
 *         &lt;element name="Due_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Fax_Number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Assistancetype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Activity_Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WIC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FS_Switch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Special_Notes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FAX_NUMBER_ASGN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WORKER_NAME_ASGN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FAX_NUMBER_GEN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WORKER_NAME_GEN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CaseWorkerAssignedSw" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Generate_Date_plus_10Days" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Cert_End_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="VCL_Due_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ngga0034", propOrder = {
        "repeatableProgram",
        "repeatableProgramChildCare",
        "repeatableProgramFS",
        "dueDate",
        "faxNumber",
        "assistancetype",
        "activityType",
        "wic",
        "fsSwitch",
        "specialNotes",
        "faxnumberasgn",
        "workernameasgn",
        "faxnumbergen",
        "workernamegen",
        "fs",
        "tf",
        "ma",
        "li",
        "caseWorkerAssignedSw",
        "generateDatePlus10Days",
        "certEndDate",
        "vclDueDate"
})

@Data
public class Ngga0034 {

    @XmlElement(name = "Repeatable_Program")
    protected List<RepeatProgramRef> repeatableProgram;
    @XmlElement(name = "Repeatable_Program_ChildCare")
    protected List<RepeatProgramRef> repeatableProgramChildCare;
    @XmlElement(name = "Repeatable_Program_FS")
    protected List<RepeatProgramRef> repeatableProgramFS;
    @XmlElement(name = "Due_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dueDate;
    @XmlElement(name = "Fax_Number", required = true)
    protected String faxNumber;
    @XmlElement(name = "Assistancetype", required = true)
    protected String assistancetype;
    @XmlElement(name = "Activity_Type", required = true)
    protected String activityType;
    @XmlElement(name = "WIC", required = true)
    protected String wic;
    @XmlElement(name = "FS_Switch", required = true)
    protected String fsSwitch;
    @XmlElement(name = "Special_Notes", required = true)
    protected String specialNotes;
    @XmlElement(name = "FAX_NUMBER_ASGN", required = true)
    protected String faxnumberasgn;
    @XmlElement(name = "WORKER_NAME_ASGN", required = true)
    protected String workernameasgn;
    @XmlElement(name = "FAX_NUMBER_GEN", required = true)
    protected String faxnumbergen;
    @XmlElement(name = "WORKER_NAME_GEN", required = true)
    protected String workernamegen;
    @XmlElement(name = "FS", required = true)
    protected String fs;
    @XmlElement(name = "TF", required = true)
    protected String tf;
    @XmlElement(name = "MA", required = true)
    protected String ma;
    @XmlElement(name = "LI", required = true)
    protected String li;
    @XmlElement(name = "CaseWorkerAssignedSw", required = true)
    protected String caseWorkerAssignedSw;
    @XmlElement(name = "Generate_Date_plus_10Days", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar generateDatePlus10Days;
    @XmlElement(name = "Cert_End_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar certEndDate;
    @XmlElement(name = "VCL_Due_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar vclDueDate;
}
