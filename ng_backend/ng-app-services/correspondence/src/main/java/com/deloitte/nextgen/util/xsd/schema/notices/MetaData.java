package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for metaData complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="metaData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="templateId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recipientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recipientAdd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="jobName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NoticeType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mailDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="corrNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="watermark" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="securityFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="formNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="formVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="formTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="envelopeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="goGreen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="additionalLanguage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authorizedRep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="assistanceUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="classOfAssistance" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="medicaidCount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tanfCount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fsCount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "metaData", propOrder = {
        "templateId",
        "recipientId",
        "recipientAdd",
        "jobName",
        "noticeType",
        "mailDate",
        "corrNum",
        "watermark",
        "securityFlag",
        "formNo",
        "formVersion",
        "formTitle",
        "envelopeId",
        "caseNum",
        "goGreen",
        "additionalLanguage",
        "authorizedRep",
        "assistanceUnit",
        "programType",
        "classOfAssistance",
        "medicaidCount",
        "tanfCount",
        "fsCount"
})
@Data
public class MetaData {
    @XmlElement(required = true)
    protected String templateId;
    @XmlElement(required = true)
    protected String recipientId;
    @XmlElement(required = true)
    protected String recipientAdd;
    @XmlElement(required = true)
    protected String jobName;
    @XmlElement(name = "NoticeType", required = true)
    protected String noticeType;
    @XmlElement(required = true)
    protected String mailDate;
    @XmlElement(required = true)
    protected String corrNum;
    @XmlElement(required = true)
    protected String watermark;
    @XmlElement(required = true)
    protected String securityFlag;
    @XmlElement(required = true)
    protected String formNo;
    @XmlElement(required = true)
    protected String formVersion;
    @XmlElement(required = true)
    protected String formTitle;
    @XmlElement(required = true)
    protected String envelopeId;
    @XmlElement(required = true)
    protected String caseNum;
    @XmlElement(required = true)
    protected String goGreen;
    @XmlElement(required = true)
    protected String additionalLanguage;
    @XmlElement(required = true)
    protected String authorizedRep;
    @XmlElement(required = true)
    protected String assistanceUnit;
    @XmlElement(required = true)
    protected String programType;
    @XmlElement(required = true)
    protected String classOfAssistance;
    @XmlElement(required = true)
    protected String medicaidCount;
    @XmlElement(required = true)
    protected String tanfCount;
    @XmlElement(required = true)
    protected String fsCount;
}
