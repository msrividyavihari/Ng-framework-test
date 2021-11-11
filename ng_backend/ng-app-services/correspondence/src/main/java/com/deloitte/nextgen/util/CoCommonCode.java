package com.deloitte.nextgen.util;

import com.deloitte.nextgen.entities.ArApplicationForAid;
import com.deloitte.nextgen.entities.COCorrespondence;
import com.deloitte.nextgen.entities.DcCaseAddresses;
import com.deloitte.nextgen.repository.DcCaseAddressesRepository;
import com.deloitte.nextgen.service.impl.CoDAOServices;
import com.deloitte.nextgen.util.xsd.schema.notices.Address;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@NoArgsConstructor
@Service
@Slf4j
public class CoCommonCode {

    @Autowired
    CoDAOServices coDAOServices;
    @Autowired
    DcCaseAddressesRepository dcCaseAddressesRepository;

    private static final String FIND_BY_INDV_ID = "findByIndvId";
    private static final String DC_INDV_ERROR = "Error While generating Indv Details from DC_INDV for indvId :";
    private static final String PROGRAM_ERROR = "Error While generating Indv Details from DC_INDV for indvId :";
    private static final String RETURNADDRESS_REF_TABLE_ERROR = "Error while retrieving address from RETURNADDRESS reference table :";
    private static final String RSM_OFFICETYPE_CD = "RSM";
    private static final String DFCS_OFFICETYPE_CD = "DFCS";
    private static final String RETURNADDRESS_REF_TABLE = "RETURNADDRESS";
    private static final String ADDRESS_COUNTY_CD = "COUNTY";
    private static final String OFFICE_TYPE_CD = "OFFICETYPE";
    private static final String ADDR_LINE1 = "MAILINGADDRESSLINE1";
    private static final String ADDR_LINE2 = "ADDRESSLINE2";
    private static final String ADDR_CITY = "CITY";
    private static final String ADDR_STATE = "STATE";
    private static final String ADDR_ZIP4 = "ZIP4";
    private static final String ADDR_ZIP5 = "ZIP5";
    private static final String OFFICE_NAME = "OFFICENAME";
    private static final String NMA = "NMA";
    private static final String[] PROGRAMARRY = new String[] { "MA", "TF", "FS" };

    /**
     * based on caseAppNum, determine whether the preferred language of HOH as
     * EN or ES.
     *
     * @param caseAppNum caseAppNum
     * @return String
     */
    public String getLangCdDescByCaseAppNum(String caseAppNum) {
        String langCd;
        if (StringUtils.isNumeric(caseAppNum)) {
            langCd = (CoConstants.CONST_SPANISH_LANG_NUM_CD
                    .equals(getPrimaryLangCodeByCaseNum(Long
                            .valueOf(caseAppNum)))) ? CoConstants.CONST_SPANISH_LANG_CD
                    : CoConstants.LANG_ENGLISH;
        } else {
            langCd = (CoConstants.CONST_SPANISH_LANG_NUM_CD
                    .equals(getPrimaryLangCodeByAppNum(caseAppNum))) ? CoConstants.CONST_SPANISH_LANG_CD
                    : CoConstants.LANG_ENGLISH;
        }
        return langCd;
    }

    public String getPrimaryLangCodeByAppNum(String appNum) {
        final ArApplicationForAid arApplicationForAid = getApplicationForAidCargo(appNum);
        if (arApplicationForAid != null) {
            return arApplicationForAid.getPrimLang();
        }
        return CoConstants.EMPTY_STRING;
    }

    public ArApplicationForAid getApplicationForAidCargo(final String caseNumber) {
        ArApplicationForAid arApplicationForAidCargo = null;
        try {
            final List<ArApplicationForAid> arApplicationForAidCargoArr = coDAOServices.getArApplication(Long.parseLong(caseNumber));
            if (!CoUtil.isEmpty(arApplicationForAidCargoArr)) {
                arApplicationForAidCargo = arApplicationForAidCargoArr.get(0);
            }
        } catch (final Exception e) {
            log.error("getApplicationForAidCargo",e);
        }
        return arApplicationForAidCargo;
    }


    public static String getPrimaryLangCodeByCaseNum(Long caseNum) {
        /*DcIndvCargo[] languages = null;
        DcIndvCollection langColl = new DcIndvCollection();
        Object[] params = { caseNum };
        languages = (DcIndvCargo[]) langColl
                .select("findLangByHOH", params);
        if (languages != null && languages.length > 0) {
            if (languages[0].getPrimaryLang() != null) {
                return languages[0].getPrimaryLang();
            }
        }*/
        return CoConstants.EMPTY_STRING;
    }

    public static Address getWicClinicAddress(String caseAppNumber) {
        Address clinicAddress = null;
        if(StringUtils.isNumeric(caseAppNumber)) {

             /*Address are fetched from DcWicClinics*/

            /*DcNutritionAssessmentCargo cargo = getDcNutritionAssessmentCargo(Long.parseLong(caseAppNumber));
            if(cargo!=null) {

                clinicAddress = getAddress(cargo.getClinicAddrLine1(), cargo.getClinicAddrLine2(), cargo.getClinicAddrCity(),
                        cargo.getClinicAddrStateCd(), StringUtils.EMPTY, cargo.getClinicAddrZip5());
                //setting clinic name into county internal use. it is not displayed in notice
                clinicAddress.setCounty(cargo.getClinicName());
            }else {
                throw new CoException("unable to get WIC clinic address");
            }
         */
        }else {
            /*DcWicClinicsCargo cargo = getDcWicClinicsCargo(caseAppNumber);
            if(cargo!=null) {

                clinicAddress = getAddress(cargo.getClinicAddrLine1(), cargo.getClinicAddrLine2(), cargo.getClinicCity(),
                        cargo.getClinicStateCd(), StringUtils.EMPTY, cargo.getClinicZip());
                //setting clinic name into county internal use. it is not displayed in notice
                clinicAddress.setCounty(cargo.getClinicName());
            }else {
                throw new CoException("unable to get WIC clinic address");
            }*/
        }
        return clinicAddress;
    }

    public static XMLGregorianCalendar getAdobeCompliantXmlGregCal(final Date date) {
        final GregorianCalendar cal = new GregorianCalendar();
        if (null == date){
            return null;
        }
        cal.setTime(date);
        XMLGregorianCalendar defaultXmlDate = null;
        try {
            defaultXmlDate = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendarDate(cal.get(Calendar.YEAR),
                            cal.get(Calendar.MONTH) + 1,
                            cal.get(Calendar.DAY_OF_MONTH),
                            DatatypeConstants.FIELD_UNDEFINED);
        } catch (final DatatypeConfigurationException e) {
            /*logger.log(BvConstants.LOGGER_RECOVERY_CATEGORY, ILog.DEBUG,
                    "Error While generating Date :" + e.getMessage(), e);*/
        }
        return defaultXmlDate;
    }

    public static Address getCountyAddress(
            final COCorrespondence coCorrespondence) {

        String addressCountyCd;
        String officeTypeCd;
        if (StringUtils.isNumeric(coCorrespondence.getCaseAppNumber())) {
            addressCountyCd = getAddressCountyCdByCaseNum(coCorrespondence);
            officeTypeCd = getOfficeTypeCdByCaseNum(coCorrespondence);
        } else {
            addressCountyCd = getAddressCountyCdByAppNum(coCorrespondence);
            officeTypeCd = getOfficeTypeCdByAppNum(coCorrespondence);
        }

        return getReturnAddressRefData(addressCountyCd, officeTypeCd);
    }

    private static String getAddressCountyCdByAppNum(
            final COCorrespondence coCorrespondence) {
        /*ArAppAddrCargo[] arAppAddrCargos = null;
        final ArAppAddrCargo arAppAddrCargo = new ArAppAddrCargo();
        arAppAddrCargo.setAppNum(coCorrespondence.getCaseAppNumber());
        arAppAddrCargo.setAddrTypeCd(CoConstants.ADDR_TYPE_CD_PA);
        arAppAddrCargos = (ArAppAddrCargo[]) coDAOServices
                .findByAppNumAdrrTypPa(arAppAddrCargo);

        return arAppAddrCargos[0].getAddrCountyCd();*/
        return "";
    }

    private static String getOfficeTypeCdByAppNum(final COCorrespondence coCorrespondence) {
        String officeTypeCd = getOfficeTypeCdByAppWorkerAssignDts(coCorrespondence);
        if (StringUtils.isEmpty(officeTypeCd)) {
            officeTypeCd = getOfficeTypeCd(coCorrespondence);
        }
        return officeTypeCd;
    }

    private static String getOfficeTypeCdByAppWorkerAssignDts(final COCorrespondence coCorrespondence) {
        String officeTypeCd = CoConstants.EMPTY_STRING;
        /*MoOfficesCargo[] moOfficesCargos = null;
        moOfficesCargos = (MoOfficesCargo[]) coDAOServices
                .getAppWorkerOffByAssignDts(
                        coCorrespondence.getCaseAppNumber(),
                        coCorrespondence.getGenerateDate());
        if ((moOfficesCargos != null) && (moOfficesCargos.length > 0)) {
            officeTypeCd = moOfficesCargos[0].getOfficeTypeCd();
        } else {
            moOfficesCargos = (MoOfficesCargo[]) coDAOServices
                    .getAppWorkerOffByMaxAssignEndDt(
                            coCorrespondence.getCaseAppNumber(),
                            coCorrespondence.getGenerateDate());
            if ((moOfficesCargos != null) && (moOfficesCargos.length > 0)) {
                officeTypeCd = moOfficesCargos[0].getOfficeTypeCd();
                officeTypeCd = checkRsmAndSetDfcsOfficeTypeCd(officeTypeCd);
            }
        }

        officeTypeCd = checkRsmAndSetDfcsOfficeTypeCd(officeTypeCd);*/
        return officeTypeCd;
    }

    private static String getOfficeTypeCd(final COCorrespondence coCorrespondence) {
        String officeTypeCd = CoConstants.EMPTY_STRING;

        /*ArAppProgramCargo[] arAppProgramCargos = (ArAppProgramCargo[]) coDAOServices
                .getArAppProgram(coCorrespondence.getCaseAppNumber());

        if (CoConstants.DOC_ID_NGGA0023.equals(coCorrespondence.getDocId())) {
            officeTypeCd = getOfficeTypeCd(arAppProgramCargos);
        }

        if (StringUtils.isBlank(officeTypeCd)) {
            officeTypeCd = getOfficeTypeCdByAppWorkerUserId(
                    arAppProgramCargos[0].getCreateUserId());
        }*/
        return officeTypeCd;
    }

    private static String getAddressCountyCdByCaseNum(
            final COCorrespondence coCorrespondence) {
        /*DcCaseAddressesCargo[] dcCaseAddressesCargos = null;
        final DcCaseAddressesCargo dcCaseAddressesCargo = new DcCaseAddressesCargo();
        dcCaseAddressesCargo.setCaseNum(Long.valueOf(coCorrespondence
                .getCaseAppNumber()));
        dcCaseAddressesCargo.setAddrTypeCd(CoConstants.ADDR_TYPE_CD_PA);
        dcCaseAddressesCargo.setEffBeginDt(coCorrespondence.getGenerateDate());
        dcCaseAddressesCargo.setEffEndDt(coCorrespondence.getGenerateDate());
        dcCaseAddressesCargos = (DcCaseAddressesCargo[]) coDAOServices
                .getCaseAddressDetails(dcCaseAddressesCargo);

        return dcCaseAddressesCargos[0].getAddrCountyCd();*/
        return "";
    }

    private static String getOfficeTypeCdByCaseNum(final COCorrespondence coCorrespondence) {
       // MoOfficesCargo[] moOfficesCargos = null;
        String officeTypeCd = null;
        //  getting case office.
        /*moOfficesCargos = (MoOfficesCargo[]) coDAOServices
                .findCaseWorkerByCaseNo(Long.valueOf(coCorrespondence
                        .getCaseAppNumber()));

        if ((moOfficesCargos != null) && (moOfficesCargos.length > 0)) {
            officeTypeCd = moOfficesCargos[0].getOfficeTypeCd();
        } else {
            // Getting current case worker office

            moOfficesCargos = (MoOfficesCargo[]) coDAOServices
                    .getCaseWorkerOffByAssignDts(
                            Long.valueOf(coCorrespondence.getCaseAppNumber()),
                            coCorrespondence.getGenerateDate());

            if ((moOfficesCargos != null) && (moOfficesCargos.length > 0)) {
                officeTypeCd = moOfficesCargos[0].getOfficeTypeCd();
            } else {
                // as there is no current case worker, getting previous case
                // worker office
                moOfficesCargos = (MoOfficesCargo[]) coDAOServices
                        .getCaseWorkerOffByMaxAssignEndDt(Long
                                .valueOf(coCorrespondence.getCaseAppNumber()));
                officeTypeCd = moOfficesCargos[0].getOfficeTypeCd();
            }
            // RSM should be changed to DFCS only if office got from previous
            // assigned case worker or case
            officeTypeCd = checkRsmAndSetDfcsOfficeTypeCd(officeTypeCd);
        }*/

        return officeTypeCd;
    }

    private static Address getReturnAddressRefData(final String addressCountyCd, final String officeTypeCd) {

        final String[] colNames = new String[2];
        final String[] colValues = new String[2];
        Address address = new Address();

        // If the mailing address is out of state (999) Return Address should be
        // Albany Call Center address
        if ("999".equalsIgnoreCase(addressCountyCd)
                || "000".equalsIgnoreCase(addressCountyCd)
                || StringUtils.isEmpty(addressCountyCd)) {
            colNames[0] = ADDR_CITY;
            colNames[1] = OFFICE_TYPE_CD;

            colValues[0] = "ALBANY";
            colValues[1] = "DFCS";
        } else {
            colNames[0] = ADDRESS_COUNTY_CD;
            colNames[1] = OFFICE_TYPE_CD;

            colValues[0] = addressCountyCd;
            colValues[1] = officeTypeCd;
        }

        /*Map<String, RefTableData> addressMap;
        try {
            addressMap = FwReferenceTableManager
                    .getValuesForMultipleColumnFilter(RETURNADDRESS_REF_TABLE,
                            colNames, colValues);
        } catch (final Exception ex) {
            final String errMsg = RETURNADDRESS_REF_TABLE_ERROR
                    + ADDRESS_COUNTY_CD + " - " + addressCountyCd + ", "
                    + OFFICE_TYPE_CD + " - " + officeTypeCd + " - "
                    + ex.getMessage();
            CoDebugger.debugException(errMsg, ex);
            throw new CoException(errMsg);
        }
        for (final Map.Entry<String, RefTableData> entry : addressMap
                .entrySet()) {
            final RefTableData refTableData = entry.getValue();
            address = getAddressWithOffName(refTableData.getRefrTableAddiData());
        }
        if (StringUtils.isBlank(address.getCounty())) {
            throw new CoException("Unable to get Return Address "
                    + ADDRESS_COUNTY_CD + " - " + addressCountyCd
                    + OFFICE_TYPE_CD + " - " + officeTypeCd);
        }*/
        return address;
    }

    public static Address retrieveNursingHomeProviderAddr(long recipientId) {
        /*VmDetailsCollection vmDetailsCollection = new VmDetailsCollection();
        VmDetailsCargo vmDetailsCargo = new VmDetailsCargo();
        vmDetailsCargo.setVendorSeqNum(recipientId);

        Object[] parms = {vmDetailsCargo};
        VmDetailsCargo[] vmDetailsCargoArray ;
        try {
            vmDetailsCargoArray = (VmDetailsCargo[])vmDetailsCollection.select("findByVendorSeqNum", parms);
        } catch (final Exception e) {

            CoDebugger.debugException(e.getMessage(), e);
            throw new CoException("Select failed for VM_DETAILS", 16081);
        }

        if(vmDetailsCargoArray!=null && vmDetailsCargoArray.length>0){
            Address address = getAddressWithCareof(CoConstants.EMPTY_STRING,
                    vmDetailsCargoArray[0].getVendorAddr(), vmDetailsCargoArray[0].getVendorAddrAptNum(),
                    vmDetailsCargoArray[0].getVendorCity(), vmDetailsCargoArray[0].getVendorState(),
                    null, vmDetailsCargoArray[0].getVendorZip());

            address.setNursingHomeName(vmDetailsCargoArray[0].getVendorName());

            return address;
        }else{
            throw new NoDataFoundException("No data in VM_DETAILS", 16016);
        }*/
        return null;
    }

    public static Address retrieveClientAddrByAppNum(final String appNumber) {
        /*final ArAppAddrCargo arAppAddrCargo = getClientAddr(appNumber);
        return CoCommonCode.getAddress(arAppAddrCargo.getAddrLine1(),
                arAppAddrCargo.getAddrLine(), arAppAddrCargo.getAddrCity(),
                arAppAddrCargo.getAddrStateCd(), arAppAddrCargo.getAddrZip4(),
                arAppAddrCargo.getAddrZip5());*/
        return null;
    }

    public Address getClientAddress(
            final COCorrespondence coCorrespondence) throws Exception {
        return getClientAddress(coCorrespondence, null);
    }

    public Address getClientAddress(
            final COCorrespondence coCorrespondence, final String addrTypeCd) throws Exception {

        DcCaseAddresses caseNoAddress = getDcCaseAddressesCargo(
                Long.parseLong(coCorrespondence.getCaseAppNumber()), addrTypeCd);

        return getAddressWithCareOf(caseNoAddress.getAddrCareOfLine(),
                caseNoAddress.getAddrLine1(), caseNoAddress.getAddrLine(),
                caseNoAddress.getAddrCity(), caseNoAddress.getAddrStateCd(),
                caseNoAddress.getAddrZip4(), caseNoAddress.getAddrZip5());
    }

    public static Address getAddress(final String address1,
                                     final String address2, final String city, final String state,
                                     final String zip4, final String zip5) {
        final Address address = new Address();
        if (StringUtils.isNotBlank(address1)) {
            address.setStreet1(address1.toUpperCase());
        }
        if (StringUtils.isNotBlank(address2)) {
            address.setStreet2(address2.toUpperCase());
        }
        if (StringUtils.isNotBlank(city)) {
            address.setCity(city.toUpperCase() + CoConstants.SPACE);
        }
        if (StringUtils.isNotBlank(state)) {
            address.setState(state.toUpperCase());
        }
        if (StringUtils.isNotBlank(zip4) && StringUtils.isNumeric(zip4)) {
            address.setZip4(zip4);
        }
        if (StringUtils.isNotBlank(zip5) && StringUtils.isNumeric(zip5)) {
            address.setZip5(zip5);
        }
        return address;
    }

    public static Address getAddressWithCareOf(final String careOfLine,
                                               final String address1, final String address2, final String city,
                                               final String state, final String zip4, final String zip5) {

        final Address address = getAddress(address1, address2, city, state,
                zip4, zip5);

        if (StringUtils.isNotBlank(careOfLine)) {
            address.setCompany(CoConstants.CARE_OF + careOfLine);
        }
        return address;
    }


    public DcCaseAddresses getDcCaseAddressesCargo(
            final long caseNum, final String addrTypeCd) throws Exception {
        List<DcCaseAddresses> caseAddress;
        DcCaseAddresses caseNoAddress;
        try {
            String currDt=CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate());
            if (addrTypeCd == null) {
                caseAddress = dcCaseAddressesRepository.findByCaseNO(caseNum, currDt);
            } else {
                caseAddress = dcCaseAddressesRepository.findByCaseNOAddressType(caseNum,addrTypeCd,currDt);
            }
            caseNoAddress = getAddressCargo(caseAddress);
        } catch (final Exception e) {

            log.error(e.getMessage(), e);
            throw new Exception("Select failed for DC_CASE_ADDRESSES", e);
        }

        if (caseNoAddress == null) {
            throw new Exception("No data in DC_CASE_ADDRESSES");
        }

        return caseNoAddress;
    }
    private static DcCaseAddresses getAddressCargo(
            final List<DcCaseAddresses> caseAddress) {
        DcCaseAddresses dcCaseAddressesCargo = null;

        for (final DcCaseAddresses dcCaseAddresses : caseAddress) {
            if ("MA".equalsIgnoreCase(dcCaseAddresses.getAddrTypeCd())) {
                dcCaseAddressesCargo = dcCaseAddresses;
                break;
            } else if ("PA".equalsIgnoreCase(dcCaseAddresses.getAddrTypeCd())) {
                dcCaseAddressesCargo = dcCaseAddresses;
            }
        }

        return dcCaseAddressesCargo;
    }

    public static Timestamp getTimeStampFromString(String strDate, String format) {
        java.sql.Timestamp timeStampDate = null;
        try {
            DateFormat formatter = new SimpleDateFormat(format);
            Date date = formatter.parse(strDate);
            timeStampDate = new Timestamp(date.getTime());
        } catch (ParseException e) {
            log.error("Exception while getting Timestamp from String ", e);
        }
        return timeStampDate;
    }

    public static XMLGregorianCalendar addDays(final Timestamp tsTimestamp1,
                                               final int iNoOfDays) {
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(tsTimestamp1);
        calendar.add(Calendar.DAY_OF_MONTH, iNoOfDays);
        return getAdobeCompliantXmlGregCal((new Timestamp(
                calendar.getTimeInMillis())));
    }

}

