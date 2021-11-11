package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for applyingAddress complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="applyingAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApplyAddress" type="{}address"/>
 *         &lt;element name="ApplyingRelationship" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applyingAddress", propOrder = {
        "applyAddress",
        "applyingRelationship"
})

@Data
public class ApplyingAddress {
    @XmlElement(name = "ApplyAddress", required = true)
    protected Address applyAddress;
    @XmlElement(name = "ApplyingRelationship", required = true)
    protected String applyingRelationship;
}
