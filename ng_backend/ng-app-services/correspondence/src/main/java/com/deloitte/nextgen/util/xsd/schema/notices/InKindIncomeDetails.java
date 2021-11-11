package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for inKindIncomeDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="inKindIncomeDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InKindEmployer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InKindAddress" type="{}address"/>
 *         &lt;element name="InKindStartDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="InKindGoodsValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InKindJobEnd" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inKindIncomeDetails", propOrder = {
        "recordStatus",
        "user",
        "inKindEmployer",
        "inKindAddress",
        "inKindStartDate",
        "inKindGoodsValue",
        "inKindJobEnd"
})

@Data
public class InKindIncomeDetails {

    @XmlElement(name = "RecordStatus", required = true)
    protected String recordStatus;
    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "InKindEmployer", required = true)
    protected String inKindEmployer;
    @XmlElement(name = "InKindAddress", required = true)
    protected Address inKindAddress;
    @XmlElement(name = "InKindStartDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar inKindStartDate;
    @XmlElement(name = "InKindGoodsValue", required = true)
    protected String inKindGoodsValue;
    @XmlElement(name = "InKindJobEnd", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar inKindJobEnd;
}
