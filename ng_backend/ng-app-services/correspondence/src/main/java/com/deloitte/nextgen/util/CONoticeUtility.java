package com.deloitte.nextgen.util;

import com.deloitte.nextgen.entities.COCorrespondence;
import com.deloitte.nextgen.entities.CoRequestRecipients;
import com.deloitte.nextgen.entities.DcCaseAddresses;
import com.deloitte.nextgen.repository.CoRequestRecipientsRepository;
import com.deloitte.nextgen.service.impl.CoDAOServices;
import com.deloitte.nextgen.service.impl.CorrespondenceServices;
import com.deloitte.nextgen.util.xsd.schema.notices.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
public class CONoticeUtility {

    @Autowired
    private static CoRequestRecipientsRepository requestRecipientsRepository;
    
    @Autowired
    CoDAOServices coDAOServices;

    public static Map<String, String> getRecipientDetail(
            final long caseNumber, final String coReqSeqNum,
            String recipientSeqNum, final long indvId) {
        // 1. Get all correspondence for CoSeqNum
        boolean isHOH = false;
        boolean isAuthRep = false;
        Map<String, String> recipientDetailMap = new HashMap<>();
        String recipientType = null;
        String recipientId = null;
        if ((null != recipientSeqNum) && !"0".equalsIgnoreCase(recipientSeqNum)) {
            final List<CoRequestRecipients> coReqRecArr = requestRecipientsRepository.findByCoReqSeqAndCoRptSeq(Long.parseLong(coReqSeqNum),Long.parseLong(recipientSeqNum));
            if (!CoUtil.isEmpty(coReqRecArr)) {
                recipientType = coReqRecArr.get(0).getRecipientType();
                recipientId = (coReqRecArr.get(0).getRecipientData()
                        .substring(coReqRecArr.get(0).getRecipientData()
                                .lastIndexOf(CoConstants.HYPHEN) + 1)).trim();
            }
        } else if(StringUtils.isNotBlank(coReqSeqNum)
                && !"0".equalsIgnoreCase(coReqSeqNum)){
            final List<CoRequestRecipients> coReqRecArr = requestRecipientsRepository.findByCoReqSeqOrderByCoRptSeq(Long.parseLong(coReqSeqNum));
            if (!CoUtil.isEmpty(coReqRecArr)) {
                for (CoRequestRecipients coRequestRecipients : coReqRecArr) {
                    if (CoConstants.CHAR_O == coRequestRecipients
                            .getRecipientTypeId()) {
                        recipientType = coRequestRecipients
                                .getRecipientType();
                        recipientId = (coRequestRecipients
                                .getRecipientData()
                                .substring(coRequestRecipients
                                        .getRecipientData().lastIndexOf(
                                                CoConstants.HYPHEN) + 1)).trim();
                        recipientSeqNum = String.valueOf(coRequestRecipients
                                .getCoRptSeq());
                        break;
                    }
                }
            }
        }

        // Checking if Authorized Representative
        if (CoConstants.AUTHORISED_REP.equalsIgnoreCase(recipientType)) {
            isAuthRep = true;
        } else if (CoConstants.CLIENT.equalsIgnoreCase(recipientType)) {
            isHOH = true;
        }

        List<DcCaseAddresses> dcCaseAddressesArr = null;
        if (isHOH
                || ((CoConstants.STRING_ZERO.equals(recipientSeqNum)) && (CoConstants.STRING_ZERO
                .equals(coReqSeqNum)))) {
            // Get Address of Head of Household
           /* dcCaseAddressesCargo.setCaseNum(caseNumber);
            dcCaseAddressesCargo.setEffBeginDt(new Timestamp(System
                    .currentTimeMillis()));
            dcCaseAddressesCargo.setEffEndDt(new Timestamp(System
                    .currentTimeMillis()));
            dcCaseAddrColl.setCargo(dcCaseAddressesCargo);
            try {
                dcCaseAddressesArr = (DcCaseAddressesCargo[]) dcCaseAddrColl
                        .select("findByCaseNumber");

                if ((null != dcCaseAddressesArr)
                        && (dcCaseAddressesArr.length > 0)
                        && (null != dcCaseAddressesArr[0])) {
                    recipientDetailMap = getDcCaseAddresses(dcCaseAddressesArr[0]);

                }
            } catch (final ApplicationException e) {

                logger.log(FwConstants.LOGGING_CATEGORY_EXCEPTION,
                        ILog.ERROR, e);
            } catch (final FrameworkException e) {

                logger.log(FwConstants.LOGGING_CATEGORY_EXCEPTION,
                        ILog.ERROR, e);
            }
            Object[] dcIndvCargoArr = null;
            DcIndvCargo dcIndvCargo = null;
            try {
                if ((recipientId != null) && (recipientId.length() > 0)) {
                    dcIndvCargoArr = coDaoServices.getDcIndividual(Long
                            .parseLong(recipientId));
                    if ((null != dcIndvCargoArr)
                            && (dcIndvCargoArr.length > 0)) {
                        dcIndvCargo = (DcIndvCargo) dcIndvCargoArr[0];

                    }
                } else if (indvId != 0) {
                    dcIndvCargo = CONoticeUtility.getIndvDetails(indvId);
                }
                if (dcIndvCargo != null) {
                    final String name = String.valueOf(COFormsUtility
                            .getFormattedName(dcIndvCargo.getFirstName(),
                                    dcIndvCargo.getMidName(),
                                    dcIndvCargo.getLastName()));
                    recipientDetailMap
                            .put(CoConstants.RECIPIENT_NAME, name);
                    recipientDetailMap.put(CoConstants.NOTICE_SALUTATION,
                            String.valueOf(COFormsUtility.getFormattedName(
                                    dcIndvCargo.getFirstName(),
                                    CoConstants.EMPTY_STRING,
                                    dcIndvCargo.getLastName())));
                }
            } catch (final CoException e1) {

                logger.log(FwConstants.LOGGING_CATEGORY_EXCEPTION,
                        ILog.ERROR, e1);
            }*/

        } else if (isAuthRep) {
            String orgName = CoConstants.EMPTY_STRING;
            String recipientName = CoConstants.EMPTY_STRING;
            // String noticeSalutation = CoConstants.EMPTY_STRING;
            // Get Recipients Organization
            /*final DcAuthRepCollection dcAuthRepColl = new DcAuthRepCollection();
            final DcAuthRepCargo dcAuthRepCargo = new DcAuthRepCargo();
            dcAuthRepCargo.setCaseNum(caseNumber);
            dcAuthRepCargo.setAuthrepSeqNum(Long.parseLong(recipientId));
            dcAuthRepColl.setCargo(dcAuthRepCargo);
            long caseAddrSeqNum = 0;
            DcAuthRepCargo[] dcAuthRepArr = null;
            try {
                dcAuthRepArr = (DcAuthRepCargo[]) dcAuthRepColl
                        .select("findByCaseNumAuthSeqNumByEffDates");
            } catch (final ApplicationException e) {

                logger.log(FwConstants.LOGGING_CATEGORY_EXCEPTION,
                        ILog.ERROR, e);
            } catch (final FrameworkException e) {

                logger.log(FwConstants.LOGGING_CATEGORY_EXCEPTION,
                        ILog.ERROR, e);
            }
            // dcAuthRepArr will have multiple records out of which only one
            // is valid.
            // If there is only one record in dcAuthRepArr and HistNavInd is
            // S then take caseAddrSeqNum from dcAuthRepArr[0]
            // Else iterate through dcAuthRepArr and select the cargo for
            // which HistNavInd is P
            if ((null != dcAuthRepArr) && (dcAuthRepArr.length > 0)
                    && (null != dcAuthRepArr[0])) {
                if ((dcAuthRepArr.length == 1)
                        && (dcAuthRepArr[0].getHistNavInd() == 'S')) {
                    caseAddrSeqNum = dcAuthRepArr[0].getCaseAddrSeqNum();
                    orgName = dcAuthRepArr[0].getAuthrepOrgName();
                    try {
                        recipientName = String
                                .valueOf(COFormsUtility.getFormattedName(
                                        dcAuthRepArr[0]
                                                .getAuthrepFirstName(),
                                        dcAuthRepArr[0].getAuthrepMidName(),
                                        dcAuthRepArr[0]
                                                .getAuthrepLastName()));
                        // CH-19523-Kunal-Start
                        *//*
                         * noticeSalutation = String.valueOf(COFormsUtility
                         * .
                         * getFormattedName(dcAuthRepArr[0].getAuthrepFirstName
                         * (), CoConstants.EMPTY_STRING,
                         * dcAuthRepArr[0].getAuthrepLastName()));
                         *//*
                        // CH-19523-End
                    } catch (final CoException e) {
                        logger.log(FwConstants.LOGGING_CATEGORY_EXCEPTION,
                                ILog.ERROR, e);
                    }
                } else {
                    for (int i = 0; i < dcAuthRepArr.length; i++) {
                        if (dcAuthRepArr[i].getHistNavInd() == 'P') {
                            caseAddrSeqNum = dcAuthRepArr[i]
                                    .getCaseAddrSeqNum();
                            orgName = dcAuthRepArr[i].getAuthrepOrgName();
                            try {
                                recipientName = String
                                        .valueOf(COFormsUtility.getFormattedName(
                                                dcAuthRepArr[i]
                                                        .getAuthrepFirstName(),
                                                dcAuthRepArr[i]
                                                        .getAuthrepMidName(),
                                                dcAuthRepArr[i]
                                                        .getAuthrepLastName()));
                                // CH-19523-Kunal-Start
                                *//*
                                 * noticeSalutation =
                                 * String.valueOf(COFormsUtility
                                 * .getFormattedName
                                 * (dcAuthRepArr[i].getAuthrepFirstName(),
                                 * CoConstants.EMPTY_STRING,
                                 * dcAuthRepArr[i].getAuthrepLastName()));
                                 *//*
                                // CH-19523-End
                            } catch (final CoException e) {
                                logger.log(
                                        FwConstants.LOGGING_CATEGORY_EXCEPTION,
                                        ILog.ERROR, e);
                            }
                            break;
                        }
                    }
                }
                // coverShtDtl.setRecipientOrg(dcAuthRepArr[0].getAuthrepOrgName());
            }
            // Get Address of Authorized Representative

            dcCaseAddressesCargo.setCaseNum(caseNumber);
            dcCaseAddressesCargo.setCaseAddrSeqNum(caseAddrSeqNum);
            dcCaseAddressesCargo.setEffEndDt(new Timestamp(System
                    .currentTimeMillis()));
            dcCaseAddrColl.setCargo(dcCaseAddressesCargo);
            try {
                dcCaseAddressesArr = (DcCaseAddressesCargo[]) dcCaseAddrColl
                        .select("findByCaseNumAndCaseAddrSeqNum");
            } catch (final ApplicationException e) {
                logger.log(FwConstants.LOGGING_CATEGORY_EXCEPTION,
                        ILog.ERROR, e);
            } catch (final FrameworkException e) {
            }

            if ((null != dcCaseAddressesArr)
                    && (dcCaseAddressesArr.length > 0)
                    && (null != dcCaseAddressesArr[0])) {
                recipientDetailMap = getDcCaseAddresses(dcCaseAddressesArr[0]);
            }
            // CH-19523-Kunal-Start
            // Notice Salutation should PI Name and not the Auth Repr. name
            // in case of AuthRep.
            Object[] dcIndvCargoArr = null;
            try {
                dcIndvCargoArr = coDaoServices.getDcIndividual(indvId);
            } catch (final CoException e) {
                logger.log(FwConstants.LOGGING_CATEGORY_EXCEPTION,
                        ILog.ERROR, e);
            }
            DcIndvCargo dcIndvCargo = new DcIndvCargo();
            if ((null != dcIndvCargoArr) && (dcIndvCargoArr.length > 0)) {
                dcIndvCargo = (DcIndvCargo) dcIndvCargoArr[0];
            }
            try {
                recipientDetailMap.put(CoConstants.NOTICE_SALUTATION,
                        String.valueOf(COFormsUtility.getFormattedName(
                                dcIndvCargo.getFirstName(),
                                CoConstants.EMPTY_STRING,
                                dcIndvCargo.getLastName())));
            } catch (final CoException e1) {

                logger.log(FwConstants.LOGGING_CATEGORY_EXCEPTION,
                        ILog.ERROR, e1);
            }*/

            recipientDetailMap.put(CoConstants.RECIPIENT_NAME, recipientName);
            recipientDetailMap.put(CoConstants.ORG_NAME, orgName);
        }
        recipientDetailMap.put(CoConstants.HOH_NAME, getHoHName(caseNumber));
        recipientDetailMap.put(CoConstants.RECIPIENT_ID, recipientSeqNum);
        return recipientDetailMap;
    }

    /*private static Map<String, String> getDcCaseAddresses(
            final DcCaseAddressesCargo dcCaseAddressesCargo) {

        final Map<String, String> dcaddressMap = new HashMap<>();
        dcaddressMap.put(CoConstants.ADDRESS_MODE,
                CoConstants.ADDRESS_MODE_RECEIVER);
        dcaddressMap.put("addressFormat", dcCaseAddressesCargo.getAddrFormat());
        dcaddressMap.put(CoConstants.ADDRESS_LINE,
                dcCaseAddressesCargo.getAddrLine());
        dcaddressMap.put(CoConstants.ADDRESS_LINE_1,
                dcCaseAddressesCargo.getAddrLine1());
        dcaddressMap.put(CoConstants.ADDRESS_LINE_2,
                dcCaseAddressesCargo.getAddrLine2());
        dcaddressMap.put(CoConstants.ADDRESS_CARE_OF_LINE,
                dcCaseAddressesCargo.getAddrCareOfLine());
        dcaddressMap.put(CoConstants.ADDRESS_ST_DIR_CD,
                dcCaseAddressesCargo.getAddrStDirCd());
        dcaddressMap.put(CoConstants.ADDRESS_ST_TYPE_CD,
                dcCaseAddressesCargo.getAddrStTypeCd());
        dcaddressMap.put(CoConstants.ADDRESS_POST_DIR_CD,
                dcCaseAddressesCargo.getAddrPostDirCd());
        dcaddressMap.put(CoConstants.ADDRESS_DWELLING_TYPE_CD,
                dcCaseAddressesCargo.getAddrDwellingTypeCd());
        dcaddressMap.put(CoConstants.ADDRESS_STATE_CD,
                dcCaseAddressesCargo.getAddrStateCd());
        dcaddressMap.put(CoConstants.ADDRESS_ST_NUM,
                dcCaseAddressesCargo.getAddrStNum());
        dcaddressMap.put(CoConstants.ADDRESS_STREET_NM,
                dcCaseAddressesCargo.getAddrStNm());
        dcaddressMap.put(CoConstants.ADDRESS_ST_NUM_FRAC,
                dcCaseAddressesCargo.getAddrStNumFrac());
        dcaddressMap.put(CoConstants.ADDRESS_APT_NUM,
                dcCaseAddressesCargo.getAddrAptNum());
        dcaddressMap.put(CoConstants.ADDRESS_CITY,
                dcCaseAddressesCargo.getAddrCity());
        dcaddressMap.put(CoConstants.ADDRESS_ZIP_4,
                dcCaseAddressesCargo.getAddrZip4());
        dcaddressMap.put(CoConstants.ADDRESS_ZIP_5,
                dcCaseAddressesCargo.getAddrZip5());
        dcaddressMap.put(CoConstants.ADDRESS_COUNTRY_CD,
                dcCaseAddressesCargo.getAddrCountryCd());
        dcaddressMap.put(CoConstants.ADDRESS_MILITRY_PO_CD,
                dcCaseAddressesCargo.getAddrMilitaryPoCd());
        dcaddressMap.put(CoConstants.ADDRESS_STATE_MILITRY_CD,
                dcCaseAddressesCargo.getAddrStateMilitaryCd());
        return dcaddressMap;
    }*/


    public static MetaData getIESMetaData(
            final COCorrespondence coCorrespondence, final String templateId) throws Exception {
        final MetaData metaData = new MetaData();

       /* CoDebugger.debugMessage("getMetaData-->Print Mode:"
                + coCorrespondence.getPrintMode());*/
        // In case of Online Printing, coRptSeq is set to 0
        if (CoConstants.PRINT_MODE_ONLINE == coCorrespondence.getPrintMode()) {
            coCorrespondence.setCoRptSeq(0L);
        }
        metaData.setTemplateId(templateId);

        if (CoConstants.PRINT_MODE_BATCH == coCorrespondence.getPrintMode()) {
            metaData.setRecipientId(String.valueOf(coCorrespondence
                    .getCoRptSeq()));
        }

        metaData.setJobName(coCorrespondence.getJobId());

        if (StringUtils.isEmpty(coCorrespondence.getAsOfDate())) {
            // for online
            metaData.setMailDate(CorrespondenceServices
                    .getYYYYMMDDFormattedDateStr(DateFormatter
                                    .dateToString(CoUtil.getCurrentDate()),
                            CoConstants.FORMS_DATE_FORMAT));
        } else {
            metaData.setMailDate(coCorrespondence.getAsOfDate());// for batch
        }
        metaData.setCorrNum(String.valueOf(coCorrespondence.getCoReqSeq()));
        if (coCorrespondence.isPreviewMode()) {
            metaData.setWatermark(CoConstants.YES_STRING_Y);
        } else {
            metaData.setWatermark(CoConstants.NO_STRING_N);
        }
        if ('Y'==coCorrespondence.getSecuritySw()) {
            metaData.setSecurityFlag(CoConstants.YES_STRING_Y);
        } else {
            metaData.setSecurityFlag(CoConstants.NO_STRING_N);
        }
        metaData.setNoticeType(CorrespondenceServices.
                getDISDNoticeType(coCorrespondence.getDocId()));
        metaData.setFormNo(coCorrespondence.getDocId());
        metaData.setFormVersion("1");
        metaData.setFormTitle(coCorrespondence.getDocName());
        metaData.setEnvelopeId("");
        metaData.setCaseNum(coCorrespondence.getCaseAppNumber());
        metaData.setGoGreen(CoConstants.NO_STRING_N);

        if (CoConstants.CONST_SPANISH_LANG_CD.
                equals(coCorrespondence.getLangCd())) {
            metaData.setAdditionalLanguage(CoConstants.CONST_SPANISH_LANG_CD);
        }

        return metaData;
    }

    // Below method gets ArApplicationForAid details for a given CaseNumber
   /* public static ArApplicationForAidCargo getApplicationForAidCargo(
            final COCorrespondence coCorrespondence) throws CoException {
        Object[] arApplicationForAidCargoArr = null;
        if (String.valueOf(CoConstants.APPLICATION).equalsIgnoreCase(
                String.valueOf(coCorrespondence.getCaseAppFlag()))) {
            arApplicationForAidCargoArr = coDaoServices
                    .getArApplicationByAppNum(coCorrespondence
                            .getCaseAppNumber());
        } else {
            arApplicationForAidCargoArr = coDaoServices
                    .getArApplication(Long.parseLong(coCorrespondence
                            .getCaseAppNumber()));
        }

        return (ArApplicationForAidCargo) arApplicationForAidCargoArr[0];

    }*/

    public static FormData getFormDataWithAuthRepDetails(final FormData formData) {

       /* Object[] dcAuthRepCargosArr = new Object[0];
        Object[] dcPhnDetailsCargoArr = new Object[0];
        Object[] dcEmailDetailsCargoArr = new Object[0];*/
        /*DcCaseAddressesCargo[] dcCaseAddCargoArr = new DcCaseAddressesCargo[0];
        DcAuthRepCargo dcAuthRepCargo = null;
        DcPhnDetailsCargo dcPhnDetailsCargo = null;
        DcEmailDetailsCargo dcEmailDetailsCargo = null;*/
       /* AuthReps authReps = null;
        Address address = null;
        Name name = null;*/

       /* try {
            dcAuthRepCargosArr = coDaoServices.getAuthorizedRepDetails(Long
                    .parseLong(formData.getCaseNum()));

            for (int i = 0; i < dcAuthRepCargosArr.length; i++) {

                dcAuthRepCargo = (DcAuthRepCargo) dcAuthRepCargosArr[i];
                dcCaseAddCargoArr = coDaoServices
                        .getAuthorizedRepAddress(dcAuthRepCargo
                                .getCaseAddrSeqNum());

                final DcCaseAddressesCargo dcCaseAddresCargo = dcCaseAddCargoArr[0];

                authReps = new AuthReps();
                address = new Address();
                address.setStreet1(dcCaseAddresCargo.getAddrLine1());
                address.setStreet2(dcCaseAddresCargo.getAddrLine());
                address.setCity(dcCaseAddresCargo.getAddrCity());
                address.setState(dcCaseAddresCargo.getAddrStateCd());
                if ((dcCaseAddresCargo.getAddrZip4() != null)
                        && !"".equals(dcCaseAddresCargo.getAddrZip4())) {
                    address.setZip4(dcCaseAddresCargo
                            .getAddrZip4());
                }
                if ((dcCaseAddresCargo.getAddrZip5() != null)
                        && !"".equals(dcCaseAddresCargo.getAddrZip5())) {
                    address.setZip5(dcCaseAddresCargo
                            .getAddrZip5());
                }

                dcPhnDetailsCargoArr = coDaoServices
                        .getAuthorizedRepPhone(dcAuthRepCargo
                                .getAuthrepSeqNum());
                if (dcPhnDetailsCargoArr.length > 0) {
                    dcPhnDetailsCargo = (DcPhnDetailsCargo) dcPhnDetailsCargoArr[0];
                    address.setCellPhone(dcPhnDetailsCargo.getPhnNum());
                }

                dcEmailDetailsCargoArr = coDaoServices
                        .getAuthorizedRepEmail(dcAuthRepCargo
                                .getAuthrepSeqNum());
                if (dcEmailDetailsCargoArr.length > 0) {
                    dcEmailDetailsCargo = (DcEmailDetailsCargo) dcEmailDetailsCargoArr[0];
                    address.setEmail(dcEmailDetailsCargo.getEmail());
                }

                authReps.setAuthRepAddress(address);
                name = new Name();
                name.setFirst(dcAuthRepCargo.getAuthrepFirstName());
                name.setMiddle(dcAuthRepCargo.getAuthrepMidName());
                name.setLast(dcAuthRepCargo.getAuthrepLastName());
                authReps.setAuthRepName(name);
                formData.getAuthRep().add(authReps);
            }
        } catch (final NoDataFoundException e) {
            CoDebugger.debugException("NoDataFoundException", e);
        } catch (final CoException e) {
            CoDebugger.debugException("CoException", e);
        } catch (final Exception e) {
            CoDebugger.debugException("Exception", e);
        }*/
        return formData;
    }

    // Author:GAACCESS - Below method gets Head Of House Hold details from
    // V_DC_INDV_HOH.
    private static String getHoHName(final Long caseNum) {
        String hoHName = "";
        /*
        Object[] vdcIndvCaseHohCargoArr = null;
        VDcIndvCaseHohCargo vdcIndvcaseHohCargo = new VDcIndvCaseHohCargo();
        try {
            vdcIndvCaseHohCargoArr = coDAOServices.getVDcIndvHOH(caseNum);
            if ((null != vdcIndvCaseHohCargoArr)
                    && (vdcIndvCaseHohCargoArr.length > 0)) {
                vdcIndvcaseHohCargo = (VDcIndvCaseHohCargo) vdcIndvCaseHohCargoArr[0];
            }
            hoHName = String.valueOf(COFormsUtility.getFormattedName(
                    vdcIndvcaseHohCargo.getFirstName(),
                    vdcIndvcaseHohCargo.getMidName(),
                    vdcIndvcaseHohCargo.getLastName()));
        } catch (final NoDataFoundException e) {
            CoDebugger.debugException("NoDataFoundException", e);
        } catch (final CoException e) {
            CoDebugger.debugException("CoException", e);
        } catch (final Exception e) {
            CoDebugger.debugException("Exception", e);
        }*/
        return hoHName;
    }

    public static String getHoHCounty(final Long caseNum) {
        long hoHIndvId = 0;
        String hohCountyCd = "";
        /*
        Object[] vdcIndvCaseHohCargoArr = null;
        Object[] dcIndvCargoArr = null;
        VDcIndvCaseHohCargo vdcIndvcaseHohCargo = new VDcIndvCaseHohCargo();
        DcIndvCargo dcIndvCargo = new DcIndvCargo();
        try {
            vdcIndvCaseHohCargoArr = coDAOServices.getVDcIndvHOH(caseNum);
            if ((null != vdcIndvCaseHohCargoArr)
                    && (vdcIndvCaseHohCargoArr.length > 0)) {
                vdcIndvcaseHohCargo = (VDcIndvCaseHohCargo) vdcIndvCaseHohCargoArr[0];
            }

            hoHIndvId = vdcIndvcaseHohCargo.getT2IndvId();
            dcIndvCargoArr = coDAOServices.getDcIndividualDetails(hoHIndvId);
            if ((null != dcIndvCargoArr) && (dcIndvCargoArr.length > 0)) {
                dcIndvCargo = (DcIndvCargo) dcIndvCargoArr[0];
            }
            hohCountyCd = dcIndvCargo.getAddrCountyCd();

        } catch (final NoDataFoundException e) {
            CoDebugger.debugException("NoDataFoundException", e);
        } catch (final CoException e) {
            CoDebugger.debugException("CoException", e);
        } catch (final Exception e) {
            CoDebugger.debugException("Exception", e);
        }*/
        return hohCountyCd;
    }

    /**
     * @author siva Krishna Aluri New correspondence method retrieves records
     *         based upon the indvId from Table DC_RELATIONSHIPS & DC_INDV table
     * @param indvId
     *            ID
     * @return Children Array List
     */
    public static ArrayList<String> getChildNames(final long indvId) {
        
        final ArrayList<String> childNames = new ArrayList<>();
        /*String firstName = null;
        String lastName = null;
        String fullName = null;
        DcIndvCargo dcIndvCargo[] = null;
        final DcRelationshipsCargo dcRelationshipsObj[] = (DcRelationshipsCargo[]) coDAOServices
                .getRelationshipType(indvId);
        final long[] childIndvList = new long[100];
        int indvCount = 0;

        if ((dcRelationshipsObj.length > 0) && (dcRelationshipsObj != null)) {

            for (int i = 0; i < dcRelationshipsObj.length; i++) {

                childIndvList[indvCount] = dcRelationshipsObj[i].getRefIndvId();
                indvCount++;
            }
            dcIndvCargo = ((DcIndvCargo[]) coDAOServices
                    .getIndividualDetails(childIndvList));
        } else {
            return childNames;
        }

        if ((dcIndvCargo != null) && (dcIndvCargo.length > 0)) {

            for (int i = 0; i < dcIndvCargo.length; i++) {

                if (dcIndvCargo[i].getFirstName() != null) {
                    firstName = dcIndvCargo[i].getFirstName();
                }
                if (dcIndvCargo[i].getLastName() != null) {
                    lastName = dcIndvCargo[i].getLastName();
                }
                fullName = firstName + " " + lastName;
                if (fullName != null) {
                    childNames.add(fullName);
                }
            }
        }
*/
        return childNames;
    }

    /*public static EdEligibilityCargo getEligibilityInfo(final String caseNum) {
        Object[] edCargoArr = null;
        EdEligibilityCargo edCargo = new EdEligibilityCargo();
        
        try {
            edCargoArr = coDAOServices.getEligibilityInfo(Long
                    .parseLong(caseNum));
        } catch (NumberFormatException | CoException e) {
            CoDebugger.debugException(
                    "NoDataFoundException while getting EligibilityInfo", e);
        }

        if ((null != edCargoArr) && (edCargoArr.length > 0)) {
            edCargo = (EdEligibilityCargo) edCargoArr[0];
        }
        return edCargo;
    }*/

    /*public static String getValueForDescription(final String tableName,
                                                final String code, final String columnName) {

        String value = "";
        try {
            value = FwReferenceTableManager.getValueByColumn(true, tableName,
                    code, columnName);

        } catch (final Exception e) {
        }

        return value;
    }*/

    /*public static DcIndvCargo getIndvDetails(final long indvId) {

        
        Object[] dcIndvCargoArr = null;
        DcIndvCargo dcIndvCargo = null;
        try {
            dcIndvCargoArr = coDaoServices.getDcIndividual(indvId);

            if ((null != dcIndvCargoArr) && (dcIndvCargoArr.length > 0)) {
                dcIndvCargo = (DcIndvCargo) dcIndvCargoArr[0];

            }
        } catch (final CoException e) {
            CoDebugger.debugException(
                    "Error occured while getting Individual Name:", e);
        }
        return dcIndvCargo;
    }*/

    /**
     * @author ssurasani Method to generate Base64Encoded String.
     * @param xmlString xmlString
     * @return String
     */
    public static String getBase64EncodedString(final String xmlString) {

        byte[] message;
        String encoded;
        message = xmlString.getBytes(StandardCharsets.UTF_8);
        encoded = DatatypeConverter.printBase64Binary(message);
        return encoded;
    }


    /**
     * This method will replace the hex value with its corresponding character
     *
     * @param value value
     * @return STring
     */
    public static String getDecodedString(String value) {
        String[] spStrArr = new String[] { "&#39;", "&#63;" };
        String[] replaceStrArr = new String[] { "'", "?" };
        String returnValue = CoConstants.EMPTY_STRING;
        int count = 0;
        for (String spStr : spStrArr) {
            String replaceStr = replaceStrArr[count];
            returnValue = value.replaceAll(spStr, replaceStr);
            count++;
        }

        return returnValue;
    }

    /*public static Map<String, String> getCasePhyscialAddress(long caseNum,
                                                             String addressType) throws CoException {
        DcCaseAddressesCargo dcCaseAddressesCargo = new DcCaseAddressesCargo();

        DcCaseAddressesCargo[] dcCaseAddressesCargoArr = (DcCaseAddressesCargo[]) coDaoServices
                .getPhysicalAddresseeAddress(caseNum,
                        addressType);

        if (dcCaseAddressesCargoArr != null && dcCaseAddressesCargoArr.length > 0) {
            dcCaseAddressesCargo = (DcCaseAddressesCargo) dcCaseAddressesCargoArr[0];

        }
        return getDcCaseAddresses(dcCaseAddressesCargo);
    }*/

    /*public static EdCaseRecertDatesCargo getCloseDate(
            final EdCaseRecertDatesCargo[] edCaseRecertDatesCargoAry, final String pc) {

        EdCaseRecertDatesCargo edCaseRecertDatesCargoObj = null;
        for(EdCaseRecertDatesCargo edCaseRecertDatesCargo: edCaseRecertDatesCargoAry){
            if(pc.equalsIgnoreCase(edCaseRecertDatesCargo.getProgramCd())){
                edCaseRecertDatesCargoObj = edCaseRecertDatesCargo;
                break;
            }
        }

        return edCaseRecertDatesCargoObj;
    }*/

    /**
     * Method to get AsOfDate or GenerateDate based on PrintMode
     *
     *
     * @return Timestamp
     */
    public static Timestamp getNoticeDueDate(COCorrespondence correspondence) {
        Timestamp noticeCreationDate;
        if (CoConstants.PRINT_MODE_ONLINE == correspondence
                .getPrintMode()) {
            noticeCreationDate = correspondence.getGenerateDate();
        } else {
            noticeCreationDate = CoCommonCode.getTimeStampFromString(
                    correspondence.getAsOfDate(), CoConstants.DATE_FORMAT_YYYYMMDD);
        }
        return noticeCreationDate;
    }

    /**
     * Method addDays is used to add/subtract days.
     *
     * @param aOriginalDt - Timestamp
     * @param aDays - No.of days to increase/decrease
     * @return Timestamp - Timestamp with days either increased or decreased
     */
    public static Timestamp addDays(Timestamp aOriginalDt,int aDays) {
        if (aOriginalDt == null) {
            return null;
        }
        Calendar cal=Calendar.getInstance();
        cal.setTime(aOriginalDt);
        cal.add(Calendar.DATE,aDays);

        return  new Timestamp(cal.getTime().getTime());
    }

    /**
     * Method getFirstDayOfNextMonth returns the first day of next month for the given Timestamp.    *
     * <p>The Timestamp is typecasted to java.util.Date and set the time of the
     * calandar class instance to get year, month and day of the passed Timestamp.
     * and 01 is set to the day part of the date which is passed as a parameter
     * </p>
     *
     * @param tsTimestamp1 - Timestamp
     * @return Timestamp - First Day of month timestamp for the next month of the given date
     */

    public static Timestamp getFirstDayOfNextMonth(Timestamp tsTimestamp1) {
        int iFirstDate = 1;
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(tsTimestamp1);
        int iNextMonth = gregorianCalendar.get(Calendar.MONTH)+1;
        gregorianCalendar.set((Calendar.DATE), iFirstDate);
        gregorianCalendar.set((Calendar.MONTH), iNextMonth);
        return (new Timestamp(gregorianCalendar.getTimeInMillis()));
    }


    /**
     * this method Adds or Subtracts given SOP business day from the given date
     *
     * @param asOfDate asOfDate
     * @param sop sop
     * @return Timestamp
     * @throws Exception Exception
     */
    public static Timestamp getSOPDateByBusinessDay(Timestamp asOfDate, int sop)
            throws Exception {
        int counter = 1;
        int dayCounter = 1;
        Timestamp newDate = asOfDate;
        boolean holiday = false;
        String description = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        while(counter<=sop){
            if(dayCounter>1){
                newDate=addDays(newDate,1);
                holiday = false;
            }
            /* Loop to determine how many days to add */
            try {
                /*description = FwReferenceTableManager.getValueByColumn(true,
                        "HOLIDAY", sdf.format(newDate),
                        "Description");
                if (null != description && description.length() > 1) {
                    holiday = true;
                }*/
            } catch (Exception e) {
                throw new Exception("unable to access HOLIDAY ", e);
            }
            calendar.setTime(newDate);
            if (Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK)
                    && Calendar.SATURDAY != calendar.get(Calendar.DAY_OF_WEEK)
                    && !holiday) {
                counter++;
            }
            dayCounter++;
        }
        return newDate;
    }

    /**
     * This method adds given business days as per given date
     *
     * @param asOfDate asOfDate
     * @param sop sop
     * @return Timestamp
     * @throws Exception Exception
     */
    public static Timestamp getDateByNoOfBusinessDay(Timestamp asOfDate, int sop)
            throws Exception {

        int counter = 1;
        Timestamp newDate = asOfDate;
        boolean holiday = false;
        String description = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();

        while(counter<=sop){
            newDate=addDays(newDate,1);
            holiday = false;

            /* Loop to determine how many days to add */
            try {
                /*description = FwReferenceTableManager.getValueByColumn(true,
                        "HOLIDAY", sdf.format(newDate),
                        "Description");
                if (null != description && description.length() > 1) {
                    holiday = true;
                }*/
            } catch (Exception e) {
                throw new Exception("unable to access HOLIDAY ", e);
            }
            calendar.setTime(newDate);
            if (Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK)
                    && Calendar.SATURDAY != calendar.get(Calendar.DAY_OF_WEEK)
                    && !holiday ) {
                counter++;
            }

        }
        return newDate;
    }

    public static Timestamp getFirstDayOfMonth(Timestamp tsTimestamp1) {
        int iFirstDate = 1;
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(tsTimestamp1);
        gregorianCalendar.set((Calendar.DATE), iFirstDate);
        return (new Timestamp(gregorianCalendar.getTimeInMillis()));
    }
}
