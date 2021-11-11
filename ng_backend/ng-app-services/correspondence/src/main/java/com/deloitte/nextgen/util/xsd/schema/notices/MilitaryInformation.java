package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for militaryInformation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="militaryInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VeteranOrActiveDuty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VetOrActiveDutySpouse" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildOfVet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "militaryInformation", propOrder = {
        "veteranOrActiveDuty",
        "vetOrActiveDutySpouse",
        "childOfVet"
})

@Data
public class MilitaryInformation {

    @XmlElement(name = "VeteranOrActiveDuty", required = true)
    protected String veteranOrActiveDuty;
    @XmlElement(name = "VetOrActiveDutySpouse", required = true)
    protected String vetOrActiveDutySpouse;
    @XmlElement(name = "ChildOfVet", required = true)
    protected String childOfVet;
}
