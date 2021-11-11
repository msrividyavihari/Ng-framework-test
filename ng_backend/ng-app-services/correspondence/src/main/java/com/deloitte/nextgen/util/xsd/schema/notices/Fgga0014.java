package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for fgga0014 complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="fgga0014">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Notice_Title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Notice_Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Policy_Manual_Reference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StandardText" type="{}standardText" maxOccurs="500" minOccurs="0"/>
 *         &lt;element name="FoodStamps" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Medicaid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TANF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PeachCare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildCare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Logo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fgga0014", propOrder = {
        "noticeTitle",
        "noticeText",
        "policyManualReference",
        "standardText",
        "foodStamps",
        "medicaid",
        "tanf",
        "peachCare",
        "childCare",
        "logo"
})

@Data
public class Fgga0014 {

    @XmlElement(name = "Notice_Title", required = true)
    protected String noticeTitle;
    @XmlElement(name = "Notice_Text", required = true)
    protected String noticeText;
    @XmlElement(name = "Policy_Manual_Reference", required = true)
    protected String policyManualReference;
    @XmlElement(name = "StandardText")
    protected List<StandardText> standardText;
    @XmlElement(name = "FoodStamps", required = true)
    protected String foodStamps;
    @XmlElement(name = "Medicaid", required = true)
    protected String medicaid;
    @XmlElement(name = "TANF", required = true)
    protected String tanf;
    @XmlElement(name = "PeachCare", required = true)
    protected String peachCare;
    @XmlElement(name = "ChildCare", required = true)
    protected String childCare;
    @XmlElement(name = "Logo", required = true)
    protected String logo;

}

