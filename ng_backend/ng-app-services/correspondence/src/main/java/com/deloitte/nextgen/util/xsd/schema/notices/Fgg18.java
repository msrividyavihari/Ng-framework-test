package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
        * <p>Java class for fgg18 complex type.
        *
        * <p>The following schema fragment specifies the expected content contained within this class.
        *
        * <pre>
 * &lt;complexType name="fgg18">
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Client_SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="TaxPayer2_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="TaxPayer2_Address" type="{}address"/>
         *         &lt;element name="TaxPayer2_Full_SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Program_Tanf" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Program_FoodStamps" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SetOff_Error_Opt_1" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SetOff_Error_Opt_2" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SetOff_Error_Opt_3" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SetOff_Error_Opt_4" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SetOff_Error_Opt_5" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SetOff_Error_Opt_6" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SetOff_Error_Opt_7" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SetOff_Error_Opt_8" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SetOff_Error_Opt_9" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SetOff_Error_Opt_10" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SetOff_Error_Opt_11" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Adequate_Comments" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Other_Circumstances_Comments" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Not_Sign_Repay_Agreement_Comments" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Client_Agreed_Comments" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Other_Comments" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Entire_Amnt" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="Return_Amnt" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="Tax_Payer_Debt_Amnt" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="Total_Amnt" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="Section" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
        *
        *
        */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fgg18", propOrder = {
        "clientSSN",
        "taxPayer2Name",
        "taxPayer2Address",
        "taxPayer2FullSSN",
        "programTanf",
        "programFoodStamps",
        "setOffErrorOpt1",
        "setOffErrorOpt2",
        "setOffErrorOpt3",
        "setOffErrorOpt4",
        "setOffErrorOpt5",
        "setOffErrorOpt6",
        "setOffErrorOpt7",
        "setOffErrorOpt8",
        "setOffErrorOpt9",
        "setOffErrorOpt10",
        "setOffErrorOpt11",
        "adequateComments",
        "otherCircumstancesComments",
        "notSignRepayAgreementComments",
        "clientAgreedComments",
        "otherComments",
        "entireAmnt",
        "returnAmnt",
        "taxPayerDebtAmnt",
        "totalAmnt",
        "section"
})

@Data
public class Fgg18 {

    @XmlElement(name = "Client_SSN", required = true)
    protected String clientSSN;
    @XmlElement(name = "TaxPayer2_Name", required = true)
    protected String taxPayer2Name;
    @XmlElement(name = "TaxPayer2_Address", required = true)
    protected Address taxPayer2Address;
    @XmlElement(name = "TaxPayer2_Full_SSN", required = true)
    protected String taxPayer2FullSSN;
    @XmlElement(name = "Program_Tanf", required = true)
    protected String programTanf;
    @XmlElement(name = "Program_FoodStamps", required = true)
    protected String programFoodStamps;
    @XmlElement(name = "SetOff_Error_Opt_1", required = true)
    protected String setOffErrorOpt1;
    @XmlElement(name = "SetOff_Error_Opt_2", required = true)
    protected String setOffErrorOpt2;
    @XmlElement(name = "SetOff_Error_Opt_3", required = true)
    protected String setOffErrorOpt3;
    @XmlElement(name = "SetOff_Error_Opt_4", required = true)
    protected String setOffErrorOpt4;
    @XmlElement(name = "SetOff_Error_Opt_5", required = true)
    protected String setOffErrorOpt5;
    @XmlElement(name = "SetOff_Error_Opt_6", required = true)
    protected String setOffErrorOpt6;
    @XmlElement(name = "SetOff_Error_Opt_7", required = true)
    protected String setOffErrorOpt7;
    @XmlElement(name = "SetOff_Error_Opt_8", required = true)
    protected String setOffErrorOpt8;
    @XmlElement(name = "SetOff_Error_Opt_9", required = true)
    protected String setOffErrorOpt9;
    @XmlElement(name = "SetOff_Error_Opt_10", required = true)
    protected String setOffErrorOpt10;
    @XmlElement(name = "SetOff_Error_Opt_11", required = true)
    protected String setOffErrorOpt11;
    @XmlElement(name = "Adequate_Comments", required = true)
    protected String adequateComments;
    @XmlElement(name = "Other_Circumstances_Comments", required = true)
    protected String otherCircumstancesComments;
    @XmlElement(name = "Not_Sign_Repay_Agreement_Comments", required = true)
    protected String notSignRepayAgreementComments;
    @XmlElement(name = "Client_Agreed_Comments", required = true)
    protected String clientAgreedComments;
    @XmlElement(name = "Other_Comments", required = true)
    protected String otherComments;
    @XmlElement(name = "Entire_Amnt")
    protected float entireAmnt;
    @XmlElement(name = "Return_Amnt")
    protected float returnAmnt;
    @XmlElement(name = "Tax_Payer_Debt_Amnt")
    protected float taxPayerDebtAmnt;
    @XmlElement(name = "Total_Amnt")
    protected float totalAmnt;
    @XmlElement(name = "Section", required = true)
    protected String section;
}
