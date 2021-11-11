package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for burial complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="burial">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BurialResourceValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BurialResourceOtherOwner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BurialResourcePercentage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "burial", propOrder = {
        "recordStatus",
        "user",
        "burialResourceValue",
        "burialResourceOtherOwner",
        "burialResourcePercentage"
})

@Data
public class Burial {
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "BurialResourceValue", required = true)
    protected String burialResourceValue;
    @XmlElement(name = "BurialResourceOtherOwner", required = true)
    protected String burialResourceOtherOwner;
    @XmlElement(name = "BurialResourcePercentage", required = true)
    protected String burialResourcePercentage;
}
