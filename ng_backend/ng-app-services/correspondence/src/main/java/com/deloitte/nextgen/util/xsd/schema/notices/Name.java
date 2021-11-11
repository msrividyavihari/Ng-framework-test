package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
        * <p>Java class for name complex type.
        *
        * <p>The following schema fragment specifies the expected content contained within this class.
        *
        * <pre>
 * &lt;complexType name="name">
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="first" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="middle" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="last" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="wholeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
        *
        *
        */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "name", propOrder = {
        "first",
        "middle",
        "last",
        "wholeName"
})

@Data
public class Name {
    @XmlElement(required = true)
    protected String first;
    @XmlElement(required = true)
    protected String middle;
    @XmlElement(required = true)
    protected String last;
    @XmlElement(required = true)
    protected String wholeName;
}
