package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prog complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="prog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Magi" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Medicaid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FoodStamps" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Tanf" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ChildCare" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="WIC" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prog", propOrder = {
        "magi",
        "medicaid",
        "foodStamps",
        "tanf",
        "childCare",
        "wic"
})

@Data
public class Prog {
    @XmlElement(name = "Magi")
    protected int magi;
    @XmlElement(name = "Medicaid")
    protected int medicaid;
    @XmlElement(name = "FoodStamps")
    protected int foodStamps;
    @XmlElement(name = "Tanf")
    protected int tanf;
    @XmlElement(name = "ChildCare")
    protected int childCare;
    @XmlElement(name = "WIC")
    protected int wic;
}