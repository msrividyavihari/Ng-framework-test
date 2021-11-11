package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChildSupportDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ChildSupportDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildSupportAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildSupportPaid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildSupportFrequency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildSupportChild" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChildSupportDetails", propOrder = {
        "recordStatus",
        "user",
        "childSupportAmount",
        "childSupportPaid",
        "childSupportFrequency",
        "childSupportChild"
})
@Data
public class ChildSupportDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "ChildSupportAmount", required = true)
    protected String childSupportAmount;
    @XmlElement(name = "ChildSupportPaid", required = true)
    protected String childSupportPaid;
    @XmlElement(name = "ChildSupportFrequency", required = true)
    protected String childSupportFrequency;
    @XmlElement(name = "ChildSupportChild", required = true)
    protected String childSupportChild;
}
