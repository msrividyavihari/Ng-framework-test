package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for declarationOfCitizenship complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="declarationOfCitizenship">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChildNames" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeclarOfCitizenship" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeclareInfo" type="{}personalInformation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "declarationOfCitizenship", propOrder = {
        "recordStatus",
        "childNames",
        "declarOfCitizenship",
        "declareInfo"
})

@Data
public class DeclarationOfCitizenship {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "ChildNames", required = true)
    protected String childNames;
    @XmlElement(name = "DeclarOfCitizenship", required = true)
    protected String declarOfCitizenship;
    @XmlElement(name = "DeclareInfo", required = true)
    protected PersonalInformation declareInfo;
}
