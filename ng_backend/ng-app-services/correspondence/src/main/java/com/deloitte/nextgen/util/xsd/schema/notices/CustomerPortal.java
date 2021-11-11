package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for customerPortal complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="customerPortal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Inquiry" type="{}inquiry"/>
 *         &lt;element name="FGGA0052" type="{}fgga0052"/>
 *         &lt;element name="Results" type="{}results"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerPortal", propOrder = {
        "inquiry",
        "fgga0052",
        "results"
})

@Data
public class CustomerPortal {
    @XmlElement(name = "Inquiry", required = true)
    protected Inquiry inquiry;
    @XmlElement(name = "FGGA0052", required = true)
    protected Fgga0052 fgga0052;
    @XmlElement(name = "Results", required = true)
    protected Results results;

}
