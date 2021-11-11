package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for personalInformationContinued complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="personalInformationContinued">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LiveInGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BlindOrDisabled" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BreastOrCervicalCancer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UnitedStatesCitizen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personalInformationContinued", propOrder = {
        "liveInGA",
        "blindOrDisabled",
        "breastOrCervicalCancer",
        "unitedStatesCitizen"
})

@Data
public class PersonalInformationContinued {
    @XmlElement(name = "LiveInGA", required = true)
    protected String liveInGA;
    @XmlElement(name = "BlindOrDisabled", required = true)
    protected String blindOrDisabled;
    @XmlElement(name = "BreastOrCervicalCancer", required = true)
    protected String breastOrCervicalCancer;
    @XmlElement(name = "UnitedStatesCitizen", required = true)
    protected String unitedStatesCitizen;
}
