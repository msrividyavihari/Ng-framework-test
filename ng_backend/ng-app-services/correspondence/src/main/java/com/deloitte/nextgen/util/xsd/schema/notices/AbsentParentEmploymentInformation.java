package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for absentParentEmploymentInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="absentParentEmploymentInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AbsentParentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AbsentParentInfo" type="{}personalInformation"/>
 *         &lt;element name="AbsentParentAddressInfo" type="{}address"/>
 *         &lt;element name="AbsentParentEmployerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AbsentParentEmpAddressInfo" type="{}address"/>
 *         &lt;element name="ClaimGoodCause" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CourtOrderedChildSupport" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "absentParentEmploymentInformation", propOrder = {
        "recordStatus",
        "user",
        "absentParentName",
        "absentParentInfo",
        "absentParentAddressInfo",
        "absentParentEmployerName",
        "absentParentEmpAddressInfo",
        "claimGoodCause",
        "courtOrderedChildSupport"
})

@Data
public class AbsentParentEmploymentInformation {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "AbsentParentName", required = true)
    protected String absentParentName;
    @XmlElement(name = "AbsentParentInfo", required = true)
    protected PersonalInformation absentParentInfo;
    @XmlElement(name = "AbsentParentAddressInfo", required = true)
    protected Address absentParentAddressInfo;
    @XmlElement(name = "AbsentParentEmployerName", required = true)
    protected String absentParentEmployerName;
    @XmlElement(name = "AbsentParentEmpAddressInfo", required = true)
    protected Address absentParentEmpAddressInfo;
    @XmlElement(name = "ClaimGoodCause", required = true)
    protected String claimGoodCause;
    @XmlElement(name = "CourtOrderedChildSupport", required = true)
    protected String courtOrderedChildSupport;
}