package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

/**
 * <p>Java class for formData complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="formData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{}case" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="caseName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="children" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="childrenDOB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="client" type="{}name"/>
 *         &lt;element name="clientAddress" type="{}address"/>
 *         &lt;element name="clientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clientDOB" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="clientAppointment" type="{}clientAppointments"/>
 *         &lt;element name="anticipatedSNAPEffectiveEndDt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="applicationReceivedDateRefugeeCashAssistance" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="applicationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="assistUnitSize" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authRep" type="{}authReps" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="birthStateOfChild" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseMgr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseMgrAdd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseMgrFax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseMgrOffice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseMgrPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseWorkerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Worker_Title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseWorkerTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseWorkerFullName" type="{}name"/>
 *         &lt;element name="caseWorkerID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseWorkerAdd" type="{}address"/>
 *         &lt;element name="caseWorkerFax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseWorkerPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caseWorkerExtension" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="childSupportAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countyOfficeNm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cnty_DFCS_Addr" type="{}address"/>
 *         &lt;element name="countyPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DFCS_Fax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ebtCard" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailAdd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="generateWorkerCounty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="grossEarnIncAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="grossUnearnIncomeAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="householdSize" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HeadOfHouse" type="{}headOfHouse"/>
 *         &lt;element name="Insurance_Company_Address" type="{}address"/>
 *         &lt;element name="LegalServicesPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mailingAdd" type="{}address"/>
 *         &lt;element name="MassMailing" type="{}massMailing"/>
 *         &lt;element name="monthlyIncomeStandard" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NursingHomeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NursingHomeAddress" type="{}address"/>
 *         &lt;element name="otherParentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otherParentAddress" type="{}address"/>
 *         &lt;element name="paymentRequestDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Pharmacy" type="{}address"/>
 *         &lt;element name="protectivePayeeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="protectivePayeeMailingAdd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="providerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="providerAddress" type="{}address"/>
 *         &lt;element name="recNonMedicalNotice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resAdd" type="{}address"/>
 *         &lt;element name="resInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="redRecDueDt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reviewNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonPersonId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonSSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonDOB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonAlienRegNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonMiddleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonDiffAliasName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonGender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonMailingAdd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonResAdd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonResCounty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonInitialDateOfEntry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedPersonInitialCountyOfOrg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sixMonthReportDueDt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="snapRecent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="snapWorkPart" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Spnsr_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Spnsr_Title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Spnsr_Address" type="{}address"/>
 *         &lt;element name="Sponsoring_Agency_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Sponsoring_Agency_Address" type="{}address"/>
 *         &lt;element name="Spouse_Addr" type="{}address"/>
 *         &lt;element name="Spouse_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SSA_Address" type="{}address"/>
 *         &lt;element name="SSN" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="stateTribeVerified" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="systemDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="tanfRedet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Update_Address" type="{}address"/>
 *         &lt;element name="worcReferralStartDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="worcEmpPlanTotalHours" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="worcEmpPlanMonth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="worcAssmAct" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="worcAssmActivityHrs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="worcAssmActivityEndDt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="worcAssmActivityStartDt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="worcCaseMgr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="worcCaseMgrAgency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="worcCaseMgrFax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="worcCaseMgrPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocAuthor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SecurityGroup" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CaptureSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CompassWebID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AssistanceUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CHIPFormSpecificData" type="{}chipFormSpecificData"/>
 *         &lt;element name="CustomerPortal" type="{}customerPortal"/>
 *         &lt;element name="EarlyCare" type="{}earlyCare"/>
 *         &lt;element name="FamilyAndChildData" type="{}familyAndChildData"/>
 *         &lt;element name="FormSpecificData" type="{}formSpecificData"/>
 *         &lt;element name="FrontOfficeData" type="{}frontOffice"/>
 *         &lt;element name="Medicaid" type="{}medicaid"/>
 *         &lt;element name="Medical" type="{}medical"/>
 *         &lt;element name="SNAPFormSpecificData" type="{}snapFormSpecificData"/>
 *         &lt;element name="TanfFormSpecificData" type="{}tanfFormSpecificData"/>
 *         &lt;element name="TEFRA" type="{}tefra"/>
 *         &lt;element name="NoticesSpecificData" type="{}noticesFormSpecificData"/>
 *         &lt;element name="VASpecificData" type="{}vaSpecificData"/>
 *         &lt;element name="Verification" type="{}verification"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "formData", propOrder = {
        "id",
        "caseName",
        "caseNum",
        "children",
        "childrenDOB",
        "clientName",
        "client",
        "clientAddress",
        "clientId",
        "clientDOB",
        "clientAppointment",
        "anticipatedSNAPEffectiveEndDt",
        "applicationReceivedDateRefugeeCashAssistance",
        "applicationNumber",
        "assistUnitSize",
        "authRep",
        "birthStateOfChild",
        "caseMgr",
        "caseMgrAdd",
        "caseMgrFax",
        "caseMgrOffice",
        "caseMgrPhone",
        "caseWorkerName",
        "workerTitle",
        "caseWorkerTitle",
        "caseWorkerFullName",
        "caseWorkerID",
        "caseWorkerAdd",
        "caseWorkerFax",
        "caseWorkerPhone",
        "caseWorkerExtension",
        "childSupportAmt",
        "countyOfficeNm",
        "cntyDFCSAddr",
        "countyPhoneNum",
        "dfcsFax",
        "ebtCard",
        "emailAdd",
        "emailType",
        "generateWorkerCounty",
        "grossEarnIncAmt",
        "grossUnearnIncomeAmt",
        "householdSize",
        "headOfHouse",
        "insuranceCompanyAddress",
        "legalServicesPhone",
        "mailingAdd",
        "massMailing",
        "monthlyIncomeStandard",
        "nursingHomeName",
        "nursingHomeAddress",
        "otherParentName",
        "otherParentAddress",
        "paymentRequestDate",
        "paymentType",
        "pharmacy",
        "protectivePayeeName",
        "protectivePayeeMailingAdd",
        "providerName",
        "providerAddress",
        "recNonMedicalNotice",
        "resAdd",
        "resInd",
        "redRecDueDt",
        "reviewNumber",
        "selectedPersonPersonId",
        "selectedPersonSSN",
        "selectedPersonDOB",
        "selectedPersonAlienRegNum",
        "selectedPersonName",
        "selectedPersonFirstName",
        "selectedPersonMiddleName",
        "selectedPersonLastName",
        "selectedPersonDiffAliasName",
        "selectedPersonGender",
        "selectedPersonMailingAdd",
        "selectedPersonResAdd",
        "selectedPersonResCounty",
        "selectedPersonInitialDateOfEntry",
        "selectedPersonInitialCountyOfOrg",
        "sixMonthReportDueDt",
        "snapRecent",
        "snapWorkPart",
        "spnsrName",
        "spnsrTitle",
        "spnsrAddress",
        "sponsoringAgencyName",
        "sponsoringAgencyAddress",
        "spouseAddr",
        "spouseName",
        "ssaAddress",
        "ssn",
        "stateTribeVerified",
        "systemDate",
        "tanfRedet",
        "updateAddress",
        "worcReferralStartDate",
        "worcEmpPlanTotalHours",
        "worcEmpPlanMonth",
        "worcAssmAct",
        "worcAssmActivityHrs",
        "worcAssmActivityEndDt",
        "worcAssmActivityStartDt",
        "worcCaseMgr",
        "worcCaseMgrAgency",
        "worcCaseMgrFax",
        "worcCaseMgrPhone",
        "docTitle",
        "docType",
        "docAuthor",
        "securityGroup",
        "docAccount",
        "fileName",
        "captureSource",
        "compassWebID",
        "transactionID",
        "assistanceUnit",
        "chipFormSpecificData",
        "customerPortal",
        "earlyCare",
        "familyAndChildData",
        "formSpecificData",
        "frontOfficeData",
        "medicaid",
        "medical",
        "snapFormSpecificData",
        "tanfFormSpecificData",
        "tefra",
        "noticesSpecificData",
        "vaSpecificData",
        "verification"
})

@Data
public class FormData {
    @XmlElement(name = "ID")
    protected List<Case> id;
    @XmlElement(required = true)
    protected String caseName;
    @XmlElement(required = true)
    protected String caseNum;
    @XmlElement(required = true)
    protected String children;
    @XmlElement(required = true)
    protected String childrenDOB;
    @XmlElement(required = true)
    protected String clientName;
    @XmlElement(required = true)
    protected Name client;
    @XmlElement(required = true)
    protected Address clientAddress;
    @XmlElement(required = true)
    protected String clientId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar clientDOB;
    @XmlElement(required = true)
    protected ClientAppointments clientAppointment;
    @XmlElement(required = true)
    protected String anticipatedSNAPEffectiveEndDt;
    @XmlElement(required = true)
    protected String applicationReceivedDateRefugeeCashAssistance;
    @XmlElement(required = true)
    protected String applicationNumber;
    @XmlElement(required = true)
    protected String assistUnitSize;
    protected List<AuthReps> authRep;
    @XmlElement(required = true)
    protected String birthStateOfChild;
    @XmlElement(required = true)
    protected String caseMgr;
    @XmlElement(required = true)
    protected String caseMgrAdd;
    @XmlElement(required = true)
    protected String caseMgrFax;
    @XmlElement(required = true)
    protected String caseMgrOffice;
    @XmlElement(required = true)
    protected String caseMgrPhone;
    @XmlElement(required = true)
    protected String caseWorkerName;
    @XmlElement(name = "Worker_Title", required = true)
    protected String workerTitle;
    @XmlElement(required = true)
    protected String caseWorkerTitle;
    @XmlElement(required = true)
    protected Name caseWorkerFullName;
    @XmlElement(required = true)
    protected String caseWorkerID;
    @XmlElement(required = true)
    protected Address caseWorkerAdd;
    @XmlElement(required = true)
    protected String caseWorkerFax;
    @XmlElement(required = true)
    protected String caseWorkerPhone;
    @XmlElement(required = true)
    protected String caseWorkerExtension;
    @XmlElement(required = true)
    protected String childSupportAmt;
    @XmlElement(required = true)
    protected String countyOfficeNm;
    @XmlElement(name = "cnty_DFCS_Addr", required = true)
    protected Address cntyDFCSAddr;
    @XmlElement(required = true)
    protected String countyPhoneNum;
    @XmlElement(name = "DFCS_Fax", required = true)
    protected String dfcsFax;
    @XmlElement(required = true)
    protected String ebtCard;
    @XmlElement(required = true)
    protected String emailAdd;
    @XmlElement(required = true)
    protected String emailType;
    @XmlElement(required = true)
    protected String generateWorkerCounty;
    @XmlElement(required = true)
    protected String grossEarnIncAmt;
    @XmlElement(required = true)
    protected String grossUnearnIncomeAmt;
    @XmlElement(required = true)
    protected String householdSize;
    @XmlElement(name = "HeadOfHouse", required = true)
    protected HeadOfHouse headOfHouse;
    @XmlElement(name = "Insurance_Company_Address", required = true)
    protected Address insuranceCompanyAddress;
    @XmlElement(name = "LegalServicesPhone", required = true)
    protected String legalServicesPhone;
    @XmlElement(required = true)
    protected Address mailingAdd;
    @XmlElement(name = "MassMailing", required = true)
    protected MassMailing massMailing;
    @XmlElement(required = true)
    protected String monthlyIncomeStandard;
    @XmlElement(name = "NursingHomeName", required = true)
    protected String nursingHomeName;
    @XmlElement(name = "NursingHomeAddress", required = true)
    protected Address nursingHomeAddress;
    @XmlElement(required = true)
    protected String otherParentName;
    @XmlElement(required = true)
    protected Address otherParentAddress;
    @XmlElement(required = true)
    protected String paymentRequestDate;
    @XmlElement(required = true)
    protected String paymentType;
    @XmlElement(name = "Pharmacy", required = true)
    protected Address pharmacy;
    @XmlElement(required = true)
    protected String protectivePayeeName;
    @XmlElement(required = true)
    protected String protectivePayeeMailingAdd;
    @XmlElement(required = true)
    protected String providerName;
    @XmlElement(required = true)
    protected Address providerAddress;
    @XmlElement(required = true)
    protected String recNonMedicalNotice;
    @XmlElement(required = true)
    protected Address resAdd;
    @XmlElement(required = true)
    protected String resInd;
    @XmlElement(required = true)
    protected String redRecDueDt;
    @XmlElement(required = true)
    protected String reviewNumber;
    @XmlElement(required = true)
    protected String selectedPersonPersonId;
    @XmlElement(required = true)
    protected String selectedPersonSSN;
    @XmlElement(required = true)
    protected String selectedPersonDOB;
    @XmlElement(required = true)
    protected String selectedPersonAlienRegNum;
    @XmlElement(required = true)
    protected String selectedPersonName;
    @XmlElement(required = true)
    protected String selectedPersonFirstName;
    @XmlElement(required = true)
    protected String selectedPersonMiddleName;
    @XmlElement(required = true)
    protected String selectedPersonLastName;
    @XmlElement(required = true)
    protected String selectedPersonDiffAliasName;
    @XmlElement(required = true)
    protected String selectedPersonGender;
    @XmlElement(required = true)
    protected String selectedPersonMailingAdd;
    @XmlElement(required = true)
    protected String selectedPersonResAdd;
    @XmlElement(required = true)
    protected String selectedPersonResCounty;
    @XmlElement(required = true)
    protected String selectedPersonInitialDateOfEntry;
    @XmlElement(required = true)
    protected String selectedPersonInitialCountyOfOrg;
    @XmlElement(required = true)
    protected String sixMonthReportDueDt;
    @XmlElement(required = true)
    protected String snapRecent;
    @XmlElement(required = true)
    protected String snapWorkPart;
    @XmlElement(name = "Spnsr_Name", required = true)
    protected String spnsrName;
    @XmlElement(name = "Spnsr_Title", required = true)
    protected String spnsrTitle;
    @XmlElement(name = "Spnsr_Address", required = true)
    protected Address spnsrAddress;
    @XmlElement(name = "Sponsoring_Agency_Name", required = true)
    protected String sponsoringAgencyName;
    @XmlElement(name = "Sponsoring_Agency_Address", required = true)
    protected Address sponsoringAgencyAddress;
    @XmlElement(name = "Spouse_Addr", required = true)
    protected Address spouseAddr;
    @XmlElement(name = "Spouse_Name", required = true)
    protected String spouseName;
    @XmlElement(name = "SSA_Address", required = true)
    protected Address ssaAddress;
    @XmlElement(name = "SSN")
    protected Long ssn;
    @XmlElement(required = true)
    protected String stateTribeVerified;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar systemDate;
    @XmlElement(required = true)
    protected String tanfRedet;
    @XmlElement(name = "Update_Address", required = true)
    protected Address updateAddress;
    @XmlElement(required = true)
    protected String worcReferralStartDate;
    @XmlElement(required = true)
    protected String worcEmpPlanTotalHours;
    @XmlElement(required = true)
    protected String worcEmpPlanMonth;
    @XmlElement(required = true)
    protected String worcAssmAct;
    @XmlElement(required = true)
    protected String worcAssmActivityHrs;
    @XmlElement(required = true)
    protected String worcAssmActivityEndDt;
    @XmlElement(required = true)
    protected String worcAssmActivityStartDt;
    @XmlElement(required = true)
    protected String worcCaseMgr;
    @XmlElement(required = true)
    protected String worcCaseMgrAgency;
    @XmlElement(required = true)
    protected String worcCaseMgrFax;
    @XmlElement(required = true)
    protected String worcCaseMgrPhone;
    @XmlElement(name = "DocTitle", required = true)
    protected String docTitle;
    @XmlElement(name = "DocType", required = true)
    protected String docType;
    @XmlElement(name = "DocAuthor", required = true)
    protected String docAuthor;
    @XmlElement(name = "SecurityGroup", required = true)
    protected String securityGroup;
    @XmlElement(name = "DocAccount", required = true)
    protected String docAccount;
    @XmlElement(name = "FileName", required = true)
    protected String fileName;
    @XmlElement(name = "CaptureSource", required = true)
    protected String captureSource;
    @XmlElement(name = "CompassWebID", required = true)
    protected String compassWebID;
    @XmlElement(name = "TransactionID", required = true)
    protected String transactionID;
    @XmlElement(name = "AssistanceUnit", required = true)
    protected String assistanceUnit;
    @XmlElement(name = "CHIPFormSpecificData", required = true)
    protected ChipFormSpecificData chipFormSpecificData;
    @XmlElement(name = "CustomerPortal", required = true)
    protected CustomerPortal customerPortal;
    @XmlElement(name = "EarlyCare", required = true)
    protected EarlyCare earlyCare;
    @XmlElement(name = "FamilyAndChildData", required = true)
    protected FamilyAndChildData familyAndChildData;
    @XmlElement(name = "FormSpecificData", required = true)
    protected FormSpecificData formSpecificData;
    @XmlElement(name = "FrontOfficeData", required = true)
    protected FrontOffice frontOfficeData;
    @XmlElement(name = "Medicaid", required = true)
    protected Medicaid medicaid;
    @XmlElement(name = "Medical", required = true)
    protected Medical medical;
    @XmlElement(name = "SNAPFormSpecificData", required = true)
    protected SnapFormSpecificData snapFormSpecificData;
    @XmlElement(name = "TanfFormSpecificData", required = true)
    protected TanfFormSpecificData tanfFormSpecificData;
    @XmlElement(name = "TEFRA", required = true)
    protected Tefra tefra;
    @XmlElement(name = "NoticesSpecificData", required = true)
    protected NoticesFormSpecificData noticesSpecificData;
    @XmlElement(name = "VASpecificData", required = true)
    protected VaSpecificData vaSpecificData;
    @XmlElement(name = "Verification", required = true)
    protected Verification verification;
}
