package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
        * <p>Java class for anonymous complex type.
        *
        * <p>The following schema fragment specifies the expected content contained within this class.
        *
        * <pre>
 * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="metaData" type="{}metaData"/>
         *         &lt;element name="formData" type="{}formData"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
        *
        *
        */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "metaData",
        "formData"
})
@XmlRootElement(name = "IESCorrespondence")
@Data
public class IESCorrespondence{
    @XmlElement(required = true)
    protected MetaData metaData;
    @XmlElement(required = true)
    protected FormData formData;
}

