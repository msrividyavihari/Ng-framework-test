package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cash complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="cash">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CashHowMuch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CashOtherOwners" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CashPercentOtherOwners" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cash", propOrder = {
        "recordStatus",
        "user",
        "cashHowMuch",
        "cashOtherOwners",
        "cashPercentOtherOwners"
})

@Data
public class Cash {
    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "CashHowMuch", required = true)
    protected String cashHowMuch;
    @XmlElement(name = "CashOtherOwners", required = true)
    protected String cashOtherOwners;
    @XmlElement(name = "CashPercentOtherOwners", required = true)
    protected String cashPercentOtherOwners;
}
