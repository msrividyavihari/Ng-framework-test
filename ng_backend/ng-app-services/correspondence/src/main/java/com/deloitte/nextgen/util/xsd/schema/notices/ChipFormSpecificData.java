package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * <p>Java class for chipFormSpecificData complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="chipFormSpecificData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChipLevel1" type="{}chiplevel1"/>
 *         &lt;element name="ChipLevel2" type="{}chiplevel2"/>
 *         &lt;element name="ChipLevel3" type="{}chiplevel3"/>
 *         &lt;element name="ChildName" type="{}childNameAppeal" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chipFormSpecificData", propOrder = {
        "chipLevel1",
        "chipLevel2",
        "chipLevel3",
        "childName"
})

@Data
public class ChipFormSpecificData {
    @XmlElement(name = "ChipLevel1", required = true)
    protected ChipLevel1 chipLevel1;
    @XmlElement(name = "ChipLevel2", required = true)
    protected ChipLevel2 chipLevel2;
    @XmlElement(name = "ChipLevel3", required = true)
    protected ChipLevel3 chipLevel3;
    @XmlElement(name = "ChildName")
    protected List<ChildNameAppeal> childName;
}
