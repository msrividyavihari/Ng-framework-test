package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.entities.*;
import com.deloitte.nextgen.repository.CoRequestHistoryRepository;
import com.deloitte.nextgen.util.*;
import com.deloitte.nextgen.util.xsd.schema.notices.Address;
import com.deloitte.nextgen.util.xsd.schema.notices.FormData;
import com.deloitte.nextgen.util.xsd.schema.notices.HeadOfHouse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public abstract class NoticeAssembler extends CorrespondenceAssembler {

    @Autowired
    CoDAOServices services;

    @Autowired
    CoRequestHistoryRepository repository;

    @Autowired
    CoCommonCode coCommonCode;
    protected MoEmployees moEmployeesCargo = null;

    protected FormData getFormData() throws Exception {
        final FormData formData = new FormData();
        // System_Date
        if ((getCoCorrespondence().getAssistanceProgramCode()!=null) &&
                !(getCoCorrespondence().getAssistanceProgramCode().contains(CoConstants.CC))) {
            // for non Child Care Programs
            formData.setSystemDate(CoCommonCode
                    .getAdobeCompliantXmlGregCal(getCoCorrespondence().getGenerateDate()));
        } else {
            // for ChildCare Program
            formData.setSystemDate(CoCommonCode.getAdobeCompliantXmlGregCal(CoUtil.getCurrentDate()));
        }
        // Case No
        formData.setCaseNum(getCoCorrespondence().getCaseAppNumber());
        formData.setCaseName(getHohIndividualInformation().getName());

        // Client ID
        formData.setClientId(String.valueOf(getCoCorrespondence()
                .getIndvId()));
        //Client name
        IndividualInformationCargo individualInformationCargo=getIndividualDetails(getCoCorrespondence());
        formData.setClientName(individualInformationCargo.getName().toUpperCase());

        final Address officeAddress = CoCommonCode.getCountyAddress(getCoCorrespondence());
        // County DFCS_NAME
        formData.setCountyOfficeNm(officeAddress.getCounty());
        // County DFCS_Address
        formData.setCntyDFCSAddr(officeAddress);
        // clientAddress
        populateMailingAddress(formData);
        // Head of House
        final HeadOfHouse hoH = new HeadOfHouse();
        updateHohDetails(hoH);
        formData.setHeadOfHouse(hoH);
        populateCaseWorkerInfo(formData);
        populateNoticesSpecificData(formData);
        return formData;
    }

    private void populateCaseWorkerInfo(FormData formData) throws Exception {
        List<MoEmployees> moEmployeesCargoList = getMoEmployeesCargo(
                getCoCorrespondence().getCaseAppNumber(),getCoCorrespondence().getRequestUserId());

        if (!CoUtil.isEmpty(moEmployeesCargoList)) {
            moEmployeesCargo = moEmployeesCargoList.get(0);
            populateCaseWorkerInfo(formData,moEmployeesCargo);
        }

    }

    protected void populateCaseWorkerInfo(FormData formData, MoEmployees employeesCargo){
        formData.setCaseWorkerID(employeesCargo.getEmpId() + "");
        formData.setCaseWorkerName(employeesCargo
                .getFirstName().charAt(0)
                + "."
                + employeesCargo.getLastName());
        formData.setCaseWorkerPhone(employeesCargo.getPhNum());
        if(employeesCargo.getPhoneNumExt() > 0) {
            String phoneNumExt = String.valueOf(employeesCargo.getPhoneNumExt());
            formData.setCaseWorkerExtension(phoneNumExt);
        }
        formData.setCaseWorkerFax(employeesCargo.getFaxNum());
    }

    private void updateHohDetails(final HeadOfHouse hoh) {
        hoh.setHohId(getHohIndividualInformation().getIndividualID() + "");
        hoh.setHohName(getHohIndividualInformation().getName());
    }

    protected void populateMailingAddress(FormData formData) throws Exception{
        if(CoConstants.RECIPIENT_NH_PROVIDER.equalsIgnoreCase(getCoCorrespondence().getRecipientType())){
            formData.setMailingAdd(CoCommonCode.retrieveNursingHomeProviderAddr(getCoCorrespondence()
                    .getProviderId()));
        }else if (getCoCorrespondence().getCaseAppFlag() == 'A') {
            formData.setMailingAdd(CoCommonCode.retrieveClientAddrByAppNum(getCoCorrespondence()
                            .getCaseAppNumber()));
        } else {
            Address address = coCommonCode.getClientAddress(getCoCorrespondence());
            formData.setMailingAdd(address);
            formData.setClientAddress(address);
        }
    }

    public IndividualInformationCargo getIndividualDetails(final Long indvId) throws Exception {
        String name;
        String dob;
        String nameReverse;
        final IndividualInformationCargo indvInformationCargo = new IndividualInformationCargo();
        final DcIndv dcIndvObj = services.getDcIndividual(indvId).get(0);
        name = dcIndvObj.getFirstName()+ " " + dcIndvObj.getLastName();
        indvInformationCargo.setName(name);
        nameReverse = CorrespondenceServices.getNameReversed(
                dcIndvObj.getFirstName(), dcIndvObj.getMidName(),
                dcIndvObj.getLastName());
        indvInformationCargo.setNameReverse(nameReverse);
        if ((dcIndvObj.getDobDt() != null)
                && (dcIndvObj.getDobDt().toString().length() > 9)) {
            dob = CorrespondenceServices.getDateToPrint(IPrintDate.MM_DD_YY,
                    new PrintDate(dcIndvObj.getDobDt()));
        } else {
            dob = CoConstants.SPACE;
        }
        indvInformationCargo.setDateOfBirth(CorrespondenceServices.filterNull(dob));
        indvInformationCargo.setSsn(dcIndvObj.getSsn());
        indvInformationCargo.setIndividualID(indvId);
        indvInformationCargo.setEthnicityCode(CorrespondenceServices.filterNull(dcIndvObj.getEthnicityCd()));
        indvInformationCargo.setSexCode(CorrespondenceServices.filterNull(dcIndvObj.getGenderCd()));
        indvInformationCargo.setRaceCode(CorrespondenceServices.filterNull(dcIndvObj.getRaceCd()));
        return indvInformationCargo;
    }

    protected CoRequestHistory getCoRequestHistory(final Long coReqSeq) throws Exception {
        List<CoRequestHistory> resultSet;
        CoRequestHistory coRequestHistory = null;
        try {
            resultSet = repository.findByCoReqSeq(coReqSeq);
            if (!CoUtil.isEmpty(resultSet)) {
                coRequestHistory = resultSet.get(0);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return coRequestHistory;
    }


    @SuppressWarnings("unchecked")
    protected Map<String, String> getMiscParamsMap(final String miscParams) throws Exception {
        Map<String, String> miscParamsMap = null;
        if (StringUtils.isNotBlank(miscParams)) {
            try {
                List<Object> misc = services.getDataFromMiscParameters(miscParams);
                if(!misc.isEmpty()) {
                    miscParamsMap = (Map<String, String>) misc.get(0);
                }
            } catch (final Exception e) {
                String errorMessage = "Error while reading miscParams:"
                        + e.getMessage();
                throw new Exception(errorMessage);
            }
        }

        return miscParamsMap;
    }

    protected String getMiscParam(Map<String, String> miscParamsMap, String key) {
        String value = CoConstants.EMPTY_STRING;
        if ( miscParamsMap.containsKey(key)) {
            value = miscParamsMap.get(key);
        }
        return value;
    }

    public IndividualInformationCargo getIndividualDetails(final COCorrespondence coCorrespondence) throws Exception {
        String name;
        String dob;
        String nameReverse;
        DcIndv dcIndvObj = new DcIndv();
        final IndividualInformationCargo indvInformationCargo = new IndividualInformationCargo();
        int length = String.valueOf(coCorrespondence.getIndvId()).length();
        if(length>9 && CoConstants.APPLICATION ==coCorrespondence.getCaseAppFlag()){
            //dcIndvObj=  services.getIndividualID(coCorrespondence.getCaseAppNumber()).get(0);
        }else {
            dcIndvObj = services.getDcIndividual(coCorrespondence.getIndvId()).get(0);
        }
        name = dcIndvObj.getFirstName()+ " " + dcIndvObj.getLastName();
        indvInformationCargo.setName(name);
        nameReverse = CorrespondenceServices.getNameReversed(
                dcIndvObj.getFirstName(), dcIndvObj.getMidName(),
                dcIndvObj.getLastName());
        indvInformationCargo.setNameReverse(nameReverse);
        if ((dcIndvObj.getDobDt() != null)
                && (dcIndvObj.getDobDt().toString().length() > 9)) {
            dob = CorrespondenceServices.getDateToPrint(IPrintDate.MM_DD_YY,
                    new PrintDate(dcIndvObj.getDobDt()));
        } else {
            dob = CoConstants.SPACE;
        }
        indvInformationCargo.setDateOfBirth(CorrespondenceServices
                .filterNull(dob));
        indvInformationCargo.setSsn(dcIndvObj.getSsn());
        indvInformationCargo.setIndividualID(dcIndvObj.getIndvId());
        indvInformationCargo.setEthnicityCode(CorrespondenceServices
                .filterNull(dcIndvObj.getEthnicityCd()));
        indvInformationCargo.setSexCode(CorrespondenceServices
                .filterNull(dcIndvObj.getGenderCd()));
        indvInformationCargo.setRaceCode(CorrespondenceServices
                .filterNull(dcIndvObj.getRaceCd()));
        return indvInformationCargo;
    }

    protected void setWICClinicAddress(FormData formData){
        Address clinicAddress = CoCommonCode.getWicClinicAddress(getCoCorrespondence().getCaseAppNumber());
        formData.setCountyOfficeNm(clinicAddress.getCounty());
        formData.setCntyDFCSAddr(clinicAddress);
    }

    protected abstract void populateNoticesSpecificData(final FormData formData)
            throws Exception;
}
