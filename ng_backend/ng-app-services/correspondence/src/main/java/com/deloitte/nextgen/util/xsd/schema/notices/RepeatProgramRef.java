package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for repeat_program_ref complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="repeat_program_ref">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Repeatable_Person" type="{}repeat_person_ref" maxOccurs="500" minOccurs="0"/>
 *         &lt;element name="Repeatable_Person_ChildCare" type="{}repeat_person_ref" maxOccurs="500" minOccurs="0"/>
 *         &lt;element name="Repeatable_Person_FoodStamps" type="{}repeat_person_ref" maxOccurs="500" minOccurs="0"/>
 *         &lt;element name="Program" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "repeat_program_ref", propOrder = {
        "repeatablePerson",
        "repeatablePersonChildCare",
        "repeatablePersonFoodStamps",
        "program"
})
@Data
public class RepeatProgramRef {

    @XmlElement(name = "Repeatable_Person")
    protected List<RepeatPersonRef> repeatablePerson;
    @XmlElement(name = "Repeatable_Person_ChildCare")
    protected List<RepeatPersonRef> repeatablePersonChildCare;
    @XmlElement(name = "Repeatable_Person_FoodStamps")
    protected List<RepeatPersonRef> repeatablePersonFoodStamps;
    @XmlElement(name = "Program", required = true)
    protected String program;
}