package com.deloitte.nextgen.util.xsd.schema.notices;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for basicContactInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="basicContactInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BasicConInformation" type="{}address"/>
 *         &lt;element name="GetInTouch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CallTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basicContactInformation", propOrder = {
        "recordStatus",
        "basicConInformation",
        "getInTouch",
        "contactMethod",
        "callTime"
})

@Data
public class BasicContactInformation {
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "BasicConInformation", required = true)
    protected Address basicConInformation;
    @XmlElement(name = "GetInTouch", required = true)
    protected String getInTouch;
    @XmlElement(name = "ContactMethod", required = true)
    protected String contactMethod;
    @XmlElement(name = "CallTime", required = true)
    protected String callTime;
}
