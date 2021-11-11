package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for massMailing complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="massMailing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Notice_Title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Notice_Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Mass_Mailing_Seq_Num" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Mass_Mailing_Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Legal_Cities" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Std_Text_List" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Types_Of_Info" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Program_List" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Schedule_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Author" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Job_Processed_SW" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Create_User_Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Create_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Unique_Transaction_Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Archive_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="History_Sequence" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="County_Code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "massMailing", propOrder = {
        "noticeTitle",
        "noticeText",
        "massMailingSeqNum",
        "massMailingId",
        "legalCities",
        "stdTextList",
        "typesOfInfo",
        "programList",
        "scheduleDate",
        "author",
        "jobProcessedSW",
        "createUserId",
        "createDate",
        "uniqueTransactionId",
        "archiveDate",
        "historySequence",
        "countyCode"
})

@Data
public class MassMailing {
    @XmlElement(name = "Notice_Title", required = true)
    protected String noticeTitle;
    @XmlElement(name = "Notice_Text", required = true)
    protected String noticeText;
    @XmlElement(name = "Mass_Mailing_Seq_Num", required = true)
    protected String massMailingSeqNum;
    @XmlElement(name = "Mass_Mailing_Id", required = true)
    protected String massMailingId;
    @XmlElement(name = "Legal_Cities", required = true)
    protected String legalCities;
    @XmlElement(name = "Std_Text_List", required = true)
    protected String stdTextList;
    @XmlElement(name = "Types_Of_Info", required = true)
    protected String typesOfInfo;
    @XmlElement(name = "Program_List", required = true)
    protected String programList;
    @XmlElement(name = "Schedule_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar scheduleDate;
    @XmlElement(name = "Author", required = true)
    protected String author;
    @XmlElement(name = "Job_Processed_SW", required = true)
    protected String jobProcessedSW;
    @XmlElement(name = "Create_User_Id", required = true)
    protected String createUserId;
    @XmlElement(name = "Create_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar createDate;
    @XmlElement(name = "Unique_Transaction_Id", required = true)
    protected String uniqueTransactionId;
    @XmlElement(name = "Archive_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar archiveDate;
    @XmlElement(name = "History_Sequence", required = true)
    protected String historySequence;
    @XmlElement(name = "County_Code", required = true)
    protected String countyCode;
}
