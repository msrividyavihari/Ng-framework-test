package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for raceInfo complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="raceInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Race" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RecognizedTribe" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TribeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "raceInfo", propOrder = {
        "race",
        "recognizedTribe",
        "tribeName"
})

@Data
public class RaceInfo {
    @XmlElement(name = "Race", required = true)
    protected String race;
    @XmlElement(name = "RecognizedTribe", required = true)
    protected String recognizedTribe;
    @XmlElement(name = "TribeName", required = true)
    protected String tribeName;
}
