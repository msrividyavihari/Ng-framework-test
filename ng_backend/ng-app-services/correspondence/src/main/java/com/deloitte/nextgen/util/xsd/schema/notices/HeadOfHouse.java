package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for headOfHouse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="headOfHouse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HOHName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HOHFullName" type="{}name"/>
 *         &lt;element name="HOHAddress" type="{}address"/>
 *         &lt;element name="HoH_County" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HOHDoB" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="HOHId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HoH_Sex" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "headOfHouse", propOrder = {
        "hohName",
        "hohFullName",
        "hohAddress",
        "hoHCounty",
        "hohDob",
        "hohId",
        "hoHSex"
})

@Data
public class HeadOfHouse {
    @XmlElement(name = "HOHName", required = true)
    protected String hohName;
    @XmlElement(name = "HOHFullName", required = true)
    protected Name hohFullName;
    @XmlElement(name = "HOHAddress", required = true)
    protected Address hohAddress;
    @XmlElement(name = "HoH_County", required = true)
    protected String hoHCounty;
    @XmlElement(name = "HOHDoB", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar hohDob;
    @XmlElement(name = "HOHId", required = true)
    protected String hohId;
    @XmlElement(name = "HoH_Sex", required = true)
    protected String hoHSex;
}
