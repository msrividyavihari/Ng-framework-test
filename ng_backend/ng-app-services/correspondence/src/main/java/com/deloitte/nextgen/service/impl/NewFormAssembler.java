package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.entities.*;
import com.deloitte.nextgen.util.CONoticeUtility;
import com.deloitte.nextgen.util.CoCommonCode;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import com.deloitte.nextgen.util.xsd.schema.notices.Address;
import com.deloitte.nextgen.util.xsd.schema.notices.FormData;
import com.deloitte.nextgen.util.xsd.schema.notices.HeadOfHouse;
import com.deloitte.ng.reftables.ReferenceTableManager;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@NoArgsConstructor
@Slf4j
public abstract class NewFormAssembler extends CorrespondenceAssembler {

    private static final String COUNTY2 = "COUNTY";
    private static final String ALBANY = "ALBANY";
    @Autowired
    CoDAOServices coDaoServices;
    @Autowired
    CoCommonCode coCommonCode;
    ReferenceTableManager tableManager;
    protected Long officeNum = 0L;
    private DcIndv individualDcIndvCargo = new DcIndv();

    protected FormData getFormData() throws Exception {
        final FormData formData = new FormData();
        getIndv();
        formData.setSystemDate(CoCommonCode.getAdobeCompliantXmlGregCal(CoUtil.getCurrentDate()));
        formData.setCaseNum(getCoCorrespondence().getCaseAppNumber());
        formData.setCaseName(individualDcIndvCargo.getFirstName()
                + CoConstants.SPACE + individualDcIndvCargo.getLastName());
        formData.setClientId(Long.toString(getCoCorrespondence().getIndvId()));
        formData.setClientName(individualDcIndvCargo.getFirstName()
                + CoConstants.SPACE + individualDcIndvCargo.getLastName());

        formData.setClientDOB(CoCommonCode.getAdobeCompliantXmlGregCal(individualDcIndvCargo.getDobDt()));
        if (null!=getCoCorrespondence().getEmpId() && getCoCorrespondence().getEmpId() > 0) {
            // Worker ID and phone num
            final List<MoEmployees>moEmployeesArray = coDaoServices.getAdvisorInfo(getCoCorrespondence().getEmpId());
            if(!CoUtil.isEmpty(moEmployeesArray)) {
                populateCaseWorkerInfo(formData,moEmployeesArray.get(0));
                officeNum = moEmployeesArray.get(0).getPriOfficeNum();
            }
        }
        // HOH
        populateHeadOfHouse(formData);
        populateMailingAddress(formData);
        // Form Specific Data
        populateFormSpecificData(formData);
        return formData;
    }

    private void getIndv() throws Exception {
        if (getCoCorrespondence()!=null && getCoCorrespondence().getIndvId() > 0) {
            List<DcIndv> dcIndvObj = coDaoServices.getDcIndividual(getCoCorrespondence().getIndvId());
            individualDcIndvCargo = dcIndvObj.get(0);
        }
    }

    protected void populateCaseWorkerInfo(FormData formData, MoEmployees employeesCargo){
        formData.setCaseWorkerID(Long.toString(getCoCorrespondence()
                .getEmpId()));
        formData.setCaseWorkerPhone(employeesCargo.getPhNum());
        formData.setCaseWorkerName(employeesCargo.getFirstName() + " "
                + employeesCargo.getLastName());
        if(employeesCargo.getPhoneNumExt()!=null && employeesCargo.getPhoneNumExt() > 0) {
            String phoneNumExt = String.valueOf(employeesCargo.getPhoneNumExt());
            formData.setCaseWorkerExtension(phoneNumExt);
        }
        formData.setCaseWorkerFax(employeesCargo.getFaxNum());
    }

    protected void populateHeadOfHouse(FormData formData) {
        final HeadOfHouse headOfHouse = new HeadOfHouse();
        headOfHouse.setHohName(getHohIndividualInformation().getName());
        formData.setHeadOfHouse(headOfHouse);
    }

    private void populateMailingAddress(FormData formData) throws Exception {
        if (getCoCorrespondence().getCaseAppFlag() == 'A') {
            formData.setMailingAdd(CoCommonCode
                    .retrieveClientAddrByAppNum(getCoCorrespondence()
                            .getCaseAppNumber()));
        } else {
            Address address = coCommonCode.getClientAddress(getCoCorrespondence());
            formData.setMailingAdd(address);
        }
    }

    protected void populateCountyAddress(FormData formData) {
        final Address officeAddress = CoCommonCode.getCountyAddress(getCoCorrespondence());
        // County DFCS_NAME
        formData.setCountyOfficeNm(officeAddress.getCounty());
        // County DFCS_Address
        formData.setCntyDFCSAddr(officeAddress);
    }

    protected void populateClientAddresses(FormData formData) throws Exception {
        if (getCoCorrespondence().getCaseAppFlag() == 'A') {
            formData.setClientAddress(CoCommonCode
                    .retrieveClientAddrByAppNum(getCoCorrespondence()
                            .getCaseAppNumber()));
        } else {
            Address address = coCommonCode
                    .getClientAddress(getCoCorrespondence(),
                            CoConstants.PHYSICAL_ADDRESS_TYPE_CD);
            formData.setClientAddress(address);
        }
    }

    protected void populateOfficeAddresses(final FormData formData) throws Exception {
        MoOffices moOfficesObj;
        moOfficesObj = coDaoServices.getOfficeInfo(officeNum).get(0);
        formData.setDfcsFax(moOfficesObj.getFaxNum());

        MoOfficeAddresses moOfficeAddressesObj;
        moOfficeAddressesObj = coDaoServices.getOfficeAddress(officeNum).get(0);
        final Address countyAddress = new Address();
        countyAddress.setStreet1(moOfficeAddressesObj.getAddrLine1());
        countyAddress.setStreet2(moOfficeAddressesObj.getAddrLine());
        countyAddress.setCity(moOfficeAddressesObj.getAddrCity());
        countyAddress.setState(moOfficeAddressesObj.getAddrStateCd());

        if (StringUtils.isNotEmpty(moOfficeAddressesObj.getAddrZip5())) {
            countyAddress.setZip5(moOfficeAddressesObj.getAddrZip5());
            formData.setCountyOfficeNm(moOfficeAddressesObj.getAddrCountyCd());

        }
        formData.setCntyDFCSAddr(countyAddress);
    }

    public String getCountyByCaseNum(
            final COCorrespondence coCorrespondence) throws Exception {
        List<DcCaseAddresses> dcCaseAddressesCargos;
        dcCaseAddressesCargos = coDaoServices.getCaseAddressDetails(Long.valueOf(coCorrespondence
                .getCaseAppNumber()),CoConstants.ADDR_TYPE_CD_PA,coCorrespondence.getGenerateDate());
        String county = null;
        if(!CoUtil.isEmpty(dcCaseAddressesCargos)) {
            String countyCd = dcCaseAddressesCargos.get(0).getAddrCountyCd();
            if ("999".equalsIgnoreCase(countyCd)
                    || "000".equalsIgnoreCase(countyCd) && StringUtils.isEmpty(countyCd)) {
                county = ALBANY;
            } else {
                county =tableManager.getValueByColumn(COUNTY2,countyCd,CoConstants.DESCRIPTION);
            }
        }
        return county;
    }

    /*protected void populateOsahHohAddresses(final FormData formData,
                                            HeRestoreBenefitsCargo result) throws Exception {

        final Address applicantAddress = new Address();
        applicantAddress.setHomePhone(result.getHohPhoneNum());
        applicantAddress.setFaxNumber(result.getHohFaxNumber());
        applicantAddress.setEmail(result.getHohEmailAddr());
        applicantAddress.setStreet1(result.getHohAddressLine1());
        applicantAddress.setStreet2(result.getHohAddressLine());
        applicantAddress.setCity(result.getHohCity());
        applicantAddress.setState(result.getHohState());
        applicantAddress.setZip4(result.getHohZipCode4());
        applicantAddress.setZip5(result.getHohZipCode5());
        formData.setClientAddress(applicantAddress);
        formData.setMailingAdd(applicantAddress);
    }*/

    protected Address getOsahDfcsAddress(final Map<String, String> inputData) {
        final Address localDfcsAddress = new Address();
        localDfcsAddress.setHomePhone(inputData.get("HomePhone"));
        localDfcsAddress.setFaxNumber(inputData.get("Fax2"));
        localDfcsAddress.setStreet1(inputData.get("OfficeStreetAddressLine"));
        localDfcsAddress
                .setStreet2(inputData.get("OfficeStreetAddressLineTwo"));
        localDfcsAddress.setCity(inputData.get("COCity"));
        localDfcsAddress.setState(inputData.get("IqIdState"));
        if (StringUtils.isNotEmpty(inputData.get("ZipCode42"))) {
            localDfcsAddress.setZip5(inputData.get("ZipCode42"));
        }
        return localDfcsAddress;
    }

    protected Address getAttorneyAddress(final Map<String, String> inputData) {
        final Address attorneyAddress = new Address();
        attorneyAddress.setHomePhone(inputData.get("attorneyTelephone"));
        attorneyAddress.setEmail(inputData.get("attorneyEmail"));
        attorneyAddress.setFaxNumber(inputData.get("attorneyFax"));
        attorneyAddress.setStreet1(inputData.get("StreetLine1"));
        attorneyAddress.setStreet2(inputData.get("StreetLine2"));
        attorneyAddress.setState(inputData.get("State"));
        attorneyAddress.setCity(inputData.get("City"));
        if (StringUtils.isNotEmpty(inputData.get("Zip"))) {
            attorneyAddress.setZip5(inputData.get("Zip"));
        }
        return attorneyAddress;
    }

    protected Address getPersonalAddress(final Map<String, String> inputData) {
        final Address personalAddress = new Address();
        personalAddress.setHomePhone(inputData.get("IqTelephone"));
        personalAddress.setFaxNumber(inputData.get("FaxNumber"));
        personalAddress.setEmail(inputData.get("EMail"));
        personalAddress.setStreet1(inputData.get("streetLine1"));
        personalAddress.setStreet2(inputData.get("streetLine2"));
        personalAddress.setState(inputData.get("IdState"));
        personalAddress.setCity(inputData.get("City2"));
        if (StringUtils.isNotEmpty(inputData.get("ZipCode4"))) {
            personalAddress.setZip5(inputData.get("ZipCode4"));
        }
        return personalAddress;
    }

    protected Address getRegionalCoordinatorAddress(final Map<String, String> inputData) {
        final Address regionalCoordinatorAddress = new Address();
        regionalCoordinatorAddress.setEmail(inputData
                .get("hearingCoordinatorEmail"));
        regionalCoordinatorAddress.setHomePhone(inputData
                .get("hearingCoordinatorTelephone"));
        regionalCoordinatorAddress.setFaxNumber(inputData
                .get("hearingCoordinatorFax"));
        regionalCoordinatorAddress.setStreet1(inputData.get("streetLine4"));
        regionalCoordinatorAddress.setStreet2(inputData.get("streetLine5"));
        regionalCoordinatorAddress.setCity(inputData.get("city"));
        regionalCoordinatorAddress.setState(inputData.get("State2"));
        if (StringUtils.isNotEmpty(inputData.get("ZipCode"))) {
            regionalCoordinatorAddress.setZip5(inputData.get("ZipCode"));
        }
        return regionalCoordinatorAddress;
    }

    protected void populateAuthRepDetails(FormData formData) {
        CONoticeUtility.getFormDataWithAuthRepDetails(formData);
        // AuthRep check.
        if (formData.getAuthRep() != null && !formData.getAuthRep().isEmpty()) {
            getIesCorrespondence().getMetaData().setAuthorizedRep(
                    CoConstants.YES_STRING_Y);
        } else {
            getIesCorrespondence().getMetaData().setAuthorizedRep(
                    CoConstants.NO_STRING_N);
        }
    }

    protected XMLGregorianCalendar stringToXMLGregorianCalendar(
            final String strDate) throws Exception {
        XMLGregorianCalendar xmlGregorianCalendar;

        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    CoConstants.DATE_FORMAT_YYYYMMDD);
            Date date = simpleDateFormat.parse(strDate);
            final GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);

            xmlGregorianCalendar = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendarDate(cal.get(Calendar.YEAR),
                            cal.get(Calendar.MONTH) + 1,
                            cal.get(Calendar.DAY_OF_MONTH),
                            DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException | ParseException e) {
            log.error("DatatypeConfigurationException in stringToXMLGregorianCalendar", e);
            throw new Exception("DatatypeConfigurationException/ParseException in stringToXMLGregorianCalendar"+e.getMessage());
        }
        return xmlGregorianCalendar;
    }

    protected abstract void populateFormSpecificData(final FormData formData)
            throws Exception;
}
