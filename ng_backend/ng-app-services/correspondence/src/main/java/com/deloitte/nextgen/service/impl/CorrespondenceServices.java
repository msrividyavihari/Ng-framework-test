package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.entities.*;
import com.deloitte.nextgen.repository.CoMasterRepository;
import com.deloitte.nextgen.repository.InDisDocMasterRepository;
import com.deloitte.nextgen.util.*;
import com.itextpdf.barcodes.BarcodeEAN;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.element.Image;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

@Service
@Slf4j
public class CorrespondenceServices implements FormatPrintString {

    @Autowired
    CoDAOServices coDAOServices;

    @Autowired
    CoMasterRepository masterRepository;

    @Autowired
    InDisDocMasterRepository inDisDocMasterRepository;

    public List<InDisDocMaster> getdisId(Long documentId) throws Exception {
        List<InDisDocMaster> inDisDocMasterList;
        try {
            inDisDocMasterList = inDisDocMasterRepository.
                    findByDocSequence(documentId);
            if (inDisDocMasterList != null && inDisDocMasterList.size() > 0) {
                return inDisDocMasterList;
            }
        } catch(Exception e) {
            throw new Exception("Error while extracting data from IN_DIS_DOC_MASTER where " +
                    "documentId = " + documentId
            + "\n" + e);
        }
        return inDisDocMasterList;
    }

    public Long getHohIdFromCaseAppNum(final COCorrespondence coCorrespondence) throws Exception {
        Long indvId = 0L;
        DcHeadOfHousehold hohDetails;
        ArAppIndv arHohDetails;
        if (coCorrespondence.getCaseAppFlag() == CoConstants.CASE) {
            if ((coCorrespondence.getPrintMode() == CoConstants.CHAR_B)
                    || (coCorrespondence.getGenerateDate() == null)) {

                hohDetails = getHoHDetails(Long.parseLong(coCorrespondence
                        .getCaseAppNumber()));

            } else {
                hohDetails = getHoHDetails(
                        Long.parseLong(coCorrespondence.getCaseAppNumber()),
                        coCorrespondence.getGenerateDate());
            }
            if (hohDetails != null) {
                indvId = hohDetails.getIndvId();
            }

        } else if (coCorrespondence.getCaseAppFlag() == CoConstants.APPLICATION) {
            arHohDetails = getHoHDetails(coCorrespondence.getCaseAppNumber());
            indvId = arHohDetails.getIndvId();
        }
        return indvId;
    }

    public DcHeadOfHousehold getHoHDetails(final Long caseNum,
                                           final Timestamp generateDt) {
        DcHeadOfHousehold dcHeadOfHouseholdCargo = null;
        List<DcHeadOfHousehold> dcHeadOfHouseholdCargoArray = coDAOServices.getHoHIndividualName(caseNum, generateDt);
        if (!CoUtil.isEmpty(dcHeadOfHouseholdCargoArray)) {
            dcHeadOfHouseholdCargo = dcHeadOfHouseholdCargoArray.get(0);
        }
        return dcHeadOfHouseholdCargo;
    }

    public ArAppIndv getHoHDetails(final String appNum) throws Exception {
        List<ArAppIndv> arAppIndvs;
        ArAppIndv arAppIndvCargo = new ArAppIndv();

        arAppIndvs = coDAOServices.getHoHIndividual(appNum);
        if (!CoUtil.isEmpty(arAppIndvs)) {
            arAppIndvCargo = arAppIndvs.get(0);
        }
        return arAppIndvCargo;
    }
    public  DcHeadOfHousehold getHoHDetails(final Long caseNum) {
        List<DcHeadOfHousehold> dcHeadOfHouseholdCargoArray;
        DcHeadOfHousehold dcHeadOfHouseholdCargo = null;
        dcHeadOfHouseholdCargoArray = coDAOServices.getHoHIndividual(caseNum);
        if (!CoUtil.isEmpty(dcHeadOfHouseholdCargoArray)) {
            dcHeadOfHouseholdCargo = dcHeadOfHouseholdCargoArray.get(0);
        }

        return dcHeadOfHouseholdCargo;
    }

    public static void formatCoObject(final VCoRequest vCoRequestCargo, final COCorrespondence aCoObj) {
        final char requestType = aCoObj.getCaseAppFlag();
        if (requestType == 'C') {
            vCoRequestCargo.setCaseNum(Long.parseLong(aCoObj.getCaseAppNumber()));
        } else if (requestType == 'A') {
            vCoRequestCargo.setAppNum(aCoObj.getCaseAppNumber());
        }
        vCoRequestCargo.setRequestTypeCd(requestType);
    }

    public static void formatCoObject(final CoRequestHistory coRequestCargo, final COCorrespondence aCoObj) {
        final char requestType = aCoObj.getCaseAppFlag();
        if (requestType == 'C') {
            coRequestCargo.setCaseNum(Long.parseLong(aCoObj.getCaseAppNumber()));
        } else if (requestType == 'A') {
            coRequestCargo.setAppNum(aCoObj.getCaseAppNumber());
        }
        coRequestCargo.setRequestTypeCd(requestType);
    }

    public IndividualInformationCargo getIndvDetails(final Long indvId) {
        String dob;
        List<DcIndv> names = null;
        final IndividualInformationCargo indivInfoCargo = new IndividualInformationCargo();

        try {
            names = coDAOServices.getDcIndividual(indvId);
        } catch (final Exception e) {
            log.error("CoException", e);
        }
        if (!CoUtil.isEmpty(names)) {
            final DcIndv dcIndvObj = names.get(0);
            final String caseName = CorrespondenceServices.getName(
                    dcIndvObj.getFirstName(), dcIndvObj.getMidName(),
                    dcIndvObj.getLastName(), dcIndvObj.getSufxName());
            indivInfoCargo.setName(caseName);
            final String caseNameFirstLastSuffix = CorrespondenceServices
                    .getName(dcIndvObj.getFirstName(), dcIndvObj.getLastName(),
                            dcIndvObj.getSufxName());
            indivInfoCargo.setNameFirstLastSuffix(caseNameFirstLastSuffix);
            indivInfoCargo.setEthnicityCode(dcIndvObj.getEthnicityCd());
            indivInfoCargo.setSexCode(dcIndvObj.getGenderCd());
            indivInfoCargo.setRaceCode(dcIndvObj.getRaceCd());
            indivInfoCargo.setSsn(dcIndvObj.getSsn());
            if ((dcIndvObj.getDobDt() != null)
                    && (dcIndvObj.getDobDt().toString().length() > 9)) {
                dob = CorrespondenceServices.getDateToPrint(
                        IPrintDate.DATE_MONTH_YEAR,
                        new PrintDate(dcIndvObj.getDobDt()));
            } else {
                dob = CoConstants.SPACE;
            }
            if (dcIndvObj.getDobDt() != null) {
                indivInfoCargo.setDobPrintDate(new PrintDate(dcIndvObj
                        .getDobDt()));
            }
            indivInfoCargo.setDateOfBirth(dob);
            indivInfoCargo.setIndividualID(indvId);
        }
        return indivInfoCargo;
    }

    public static String getYYYYMMDDFormattedDateStr(final String asOfDate,
                                                     final String targetFormat) {
        final SimpleDateFormat sdfSource = new SimpleDateFormat(CoConstants.DATE_FORMAT); // MM/dd/yyyy
        Date parsedDate;
        String strDt = null;
        try {
            parsedDate = sdfSource.parse(asOfDate);
            strDt = (new SimpleDateFormat(targetFormat).format(parsedDate));
        } catch (final Exception ex) {
            //CoDebugger.debugException("Error in formatting Date String...", ex);
        }
        return strDt;
    }

    public static String getDISDNoticeType(String docId) throws Exception {
        String noticeType = StringUtils.EMPTY;
        try {
            /*Map<Object, Object> refDataTableNew = FwReferenceTableManager.getValuesForColumnFilter(
                    true, "DISDOCTYPE", "DOCTYPESHORTFORM", docId);

            for (Map.Entry<Object, Object> entry : refDataTableNew.entrySet()) {
                RefTableData refData = (RefTableData) entry.getValue();

                if(refData != null && StringUtils.isNotBlank(refData.getRefrTableCode())) {
                    noticeType = refData.getRefrTableCode();
                }

            }*/

            /*if(StringUtils.isBlank(noticeType)) {
                StringBuilder msg = new StringBuilder();
                msg.append("No DIS Notice Type in RT_DISDOCTYPE_MV for ");
                msg.append(docId);
                throw new Exception(msg.toString());
            }*/

        } catch (Exception exec) {
            String msg = "Error on RT_DISDOCTYPE_MV while looking for code by DOCTYPESHORTFORM " +
                    exec.getMessage();
            throw new Exception(msg);
        }
        return noticeType;
    }

    public static String filterNull(final String text) {
        return text == null ? CoConstants.EMPTY_STRING : text;
    }
    public static char filterNull(final Character text) {
        return text == 0 ? ' ' : text;
    }

    public void checkDocumentValidity(final String docId) throws Exception {
        List<CoMaster> masterCargos;
        String curDt = CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate());
        masterCargos=masterRepository.findByDocId(docId,curDt);
        final int cargoLength = masterCargos.size();
        if ((cargoLength == 0)) {
            throw new Exception(
                    "No matching record exists in CO_MASTER for the docId: "
                            + docId);
        }
    }
    public static String getName(String firstName, String midName,
                                 String lastName, String sufxNameCd) {
        final StringBuilder name = new StringBuilder();

        if (StringUtils.isNotBlank(firstName)) {
            name.append(firstName);
            name.append(CoConstants.SPACE);
        }
        if (StringUtils.isNotBlank(midName)) {
            name.append(midName);
            name.append(CoConstants.SPACE);
        }
        if (StringUtils.isNotBlank(lastName)) {
            name.append(lastName);
            name.append(CoConstants.SPACE);
        }
        if (StringUtils.isNotBlank(sufxNameCd)) {
            try {
               // name.append(ReferenceTableAccess.getRefDescription(
                       // sufxNameCd.trim(), CoConstants.REF_TABLE_NAMESUFFIX));
                name.append(CoConstants.SPACE);
            } catch (final Exception e) {

            }
        }
        return name.toString();
    }
    public static String getName(String firstName, String lastName,
                                 String sufxNameCd) {
        final StringBuilder name = new StringBuilder();
        if ((firstName != null)
                && ((firstName = firstName.trim()).length() > 0)) {
            name.append(firstName);
            name.append(CoConstants.SPACE);
        }
        if ((lastName != null) && ((lastName = lastName.trim()).length() > 0)) {
            name.append(lastName);
            name.append(CoConstants.SPACE);
        }
        if ((sufxNameCd != null)
                && ((sufxNameCd = sufxNameCd.trim()).length() > 0)) {
            try {
                //name.append(ReferenceTableAccess.getRefDescription(
                  //      sufxNameCd.trim(), CoConstants.REF_TABLE_NAMESUFFIX));
                name.append(CoConstants.SPACE);
            } catch (final Exception e) {
                log.debug("Invalid suffix name " + sufxNameCd, e);
            }
        }
        return name.toString();
    }

    public static String getDateToPrint(final int format,
                                        final PrintDate printDate) {
        String printable = null;
        final String min = printDate.get(Calendar.MINUTE) < 10 ? "0"
                + printDate.get(Calendar.MINUTE) : printDate
                .get(Calendar.MINUTE) + "";
        switch (format) {

            case IPrintDate.HH_MM:
                printable = printDate.get(Calendar.HOUR) + " : "
                        + printDate.get(Calendar.MINUTE);
                break;
            case IPrintDate.MM_DD_YY:
                printable = printDate.get(Calendar.MONTH) + "/"
                        + printDate.get(Calendar.DATE) + "/"
                        + printDate.get(Calendar.YEAR);
                // FOR MM/DD/YYYY if MM or DD is single digit - this method returns
                // single digit (without zero)
                // This patch takes care of this issue
                try {
                    final Date dt = DateFormatter.getDateFromString(printable);
                    printable = DateFormatter.dateToString(dt);
                    log.debug("CorrespondenceServices --> getDateToPrint for PrintDate.MM_DD_YY printable = "
                                    + printable);
                } catch (final ParseException ex) {
                    log.error("Exception parsing Date :" + ex);
                }
                break;
            case IPrintDate.MM_DD_YY_1:
                printable = printDate.get(Calendar.MONTH) + "-"
                        + printDate.get(Calendar.DATE) + "-"
                        + printDate.get(Calendar.YEAR);
                break;
            case IPrintDate.MM_DD_YY_2:
                String mm;
                String dd;
                mm = printDate.get(Calendar.MONTH) < 10 ? "0"
                        + printDate.get(Calendar.MONTH) : ""
                        + printDate.get(Calendar.MONTH);
                dd = printDate.get(Calendar.DATE) < 10 ? "0"
                        + printDate.get(Calendar.DATE) : ""
                        + printDate.get(Calendar.DATE);
                printable = mm + "-" + dd + "-" + printDate.get(Calendar.YEAR);
                break;
            case IPrintDate.YYYY_MM_DD:
                printable = printDate.get(Calendar.YEAR) + "-"
                        + printDate.get(Calendar.MONTH) + "-"
                        + printDate.get(Calendar.DATE);
                break;
            case IPrintDate.DAY_DATE_MONTH_YEAR:
                printable = printDate.getDay() + " " + printDate.get(Calendar.DATE)
                        + getSuffix(printDate.get(Calendar.DATE)) + ", "
                        + printDate.getMonth() + " " + printDate.get(Calendar.YEAR);
                break;
            case IPrintDate.DATE_MONTH_YEAR:
                printable = printDate.get(Calendar.DATE)
                        + getSuffix(printDate.get(Calendar.DATE)) + ", "
                        + printDate.getMonth() + " " + printDate.get(Calendar.YEAR);
                break;
            case IPrintDate.MONTH_YEAR:
                printable = printDate.getMonth() + " "
                        + printDate.get(Calendar.YEAR);
                break;
            case IPrintDate.MON_YEAR:
                printable = printDate.getMonth().substring(0, 3) + " "
                        + printDate.get(Calendar.YEAR);
                break;
            case IPrintDate.DATE01_MONTH_YEAR:
                printable = "1st" + " " + printDate.getMonth() + " "
                        + printDate.get(Calendar.YEAR);
                break;
            case IPrintDate.HH_MM_SS:
                printable = printDate.get(Calendar.HOUR) + " : " + min + " : "
                        + printDate.get(Calendar.SECOND);
                break;
            case IPrintDate.HH_MM_AMPM:
                printable = printDate.get(Calendar.HOUR)
                        + " : "
                        + min
                        + " "
                        + (printDate.get(Calendar.AM_PM) == Calendar.AM ? "a.m."
                        : "p.m.");
                break;
            default:
                break;
        }
        log.debug("CorrespondenceServices --> getDateToPrint printable = " + printable);
        return printable;
    }

    public static String getSuffix(final int date) {
        String suffix = "th";
        final int quotient = date / 10;
        int reminder;
        if (quotient > 1) {
            reminder = date % 10;
        } else {
            reminder = date;
        }
        switch (reminder) {
            case 0:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                suffix = "th";
                break;
            case 1:
                suffix = "st";
                break;
            case 2:
                suffix = "nd";
                break;
            case 3:
                suffix = "rd";
                break;
            default:
                break;
        }
        return suffix;
    }

    public static String getNameReversed(String firstName, String midName,
                                         String lastName) {
        final StringBuilder name = new StringBuilder();
        if ((lastName != null) && ((lastName = lastName.trim()).length() > 0)) {
            name.append(lastName);
            if ((firstName != null)
                    && ((firstName = firstName.trim()).length() > 0)) {
                name.append(CoConstants.COMMA);
                name.append(CoConstants.SPACE);
                name.append(firstName);
                if ((midName != null)
                        && ((midName = midName.trim()).length() > 0)) {
                    name.append(CoConstants.COMMA);
                    name.append(CoConstants.SPACE);
                    name.append(midName.charAt(0));
                    name.append(CoConstants.DOT);
                }
            } else if ((midName != null)
                    && ((midName = midName.trim()).length() > 0)) {
                name.append(CoConstants.COMMA);
                name.append(CoConstants.SPACE);
                name.append(midName.charAt(0));
                name.append(CoConstants.DOT);
            }
        } else if ((firstName != null)
                && ((firstName = firstName.trim()).length() > 0)) {
            name.append(firstName);
            if ((midName != null) && ((midName = midName.trim()).length() > 0)) {
                name.append(CoConstants.COMMA);
                name.append(CoConstants.SPACE);
                name.append(midName.charAt(0));
                name.append(CoConstants.DOT);
            }
        }
        return name.toString();
    }

    public static void insertDisDocMasterRecord(
            final COCorrespondence coCorrespondence) throws
            Exception {
        try {
            /*final InDisDocMasterCollection inDisDocMasterCollection = new InDisDocMasterCollection();
            final InDisDocMasterCargo inDisDocMasterCargo = populateDocMasterCargo(coCorrespondence);
            inDisDocMasterCollection.add(inDisDocMasterCargo);
            inDisDocMasterCollection.insert();*/
        } catch (final Exception ex) {
            throw new Exception(
                    "Exception occured while persisting record into DOC Master table: "
                            + ex.getMessage());
        }
    }

    public InDisDocMaster populateDocMasterCargo(
            final COCorrespondence coCorrespondence) throws Exception {
        final InDisDocMaster inDisDocMasterCargo = new InDisDocMaster();
        try {
//            final String docType = getDISDNoticeType(coCorrespondence.getDocId());
            final String docType = coCorrespondence.getDocId();
            if (CoConstants.UPDATE_CPC
                    .equals(coCorrespondence.getActionValue())) {
                inDisDocMasterCargo.setTransactionId(CoConstants.IESUW
                        + coCorrespondence.getCoReqSeq());
                inDisDocMasterCargo.setSource(CoConstants.IESUW);
            } else if(CoConstants.PRINT_MODE_ONLINE == coCorrespondence.getPrintMode()
                    || CoConstants.GENERATECO.equals(coCorrespondence
                    .getActionValue())){
                inDisDocMasterCargo.setTransactionId(CoConstants.IESLP
                        + coCorrespondence.getCoReqSeq());
                inDisDocMasterCargo.setSource(CoConstants.IESLP);

            } else {
                inDisDocMasterCargo.setTransactionId(CoConstants.IESUB
                        + coCorrespondence.getCoReqSeq());
                inDisDocMasterCargo.setSource(CoConstants.IESUB);
            }
            inDisDocMasterCargo.setDocType("01");
            inDisDocMasterCargo.setDocUploadType(coCorrespondence
                    .getCaseAppFlag());
            inDisDocMasterCargo.setEntryDt(CoUtil.getCurrentDate());
            inDisDocMasterCargo.setProcessFlag(CoConstants.CHAR_P);
            if (CoConstants.CASE == coCorrespondence.getCaseAppFlag()) {
                inDisDocMasterCargo.setCaseNum(Long.parseLong(coCorrespondence
                        .getCaseAppNumber()));
                inDisDocMasterCargo.setIndvId(getIndvDetails(coCorrespondence.getIndvId()).getIndividualID());
            } else {
                inDisDocMasterCargo.setAppNum(coCorrespondence
                        .getCaseAppNumber());
                inDisDocMasterCargo.setIndvSeqNum(getIndvDetails(coCorrespondence.getIndvId()).getIndividualID());
            }
            inDisDocMasterCargo.setDelinkInd(CoConstants.CHAR_N);
            inDisDocMasterCargo.setCoReqSeq(coCorrespondence.getCoReqSeq());
            inDisDocMasterCargo.setCreateUserId(coCorrespondence
                    .getRequestUserId());

            if (StringUtils.isNotEmpty(coCorrespondence.getDisId())) {
                inDisDocMasterCargo.setDocId(Long.parseLong(coCorrespondence
                        .getDisId()));
            }
            return inDisDocMasterCargo;
        } catch (final Exception ex) {
            throw new Exception(
                    "Exception occured while persisting record into DOC Master table: "
                            + ex.getMessage());
        }
    }

    public static String replaceAll(final String source,
                                    final String toReplace, final String replaceWith) {
        final StringBuilder temp = new StringBuilder();
        final StringTokenizer tokens = new StringTokenizer(source, toReplace);
        while (tokens.hasMoreElements()) {
            temp.append(tokens.nextElement());
            temp.append(replaceWith);
        }
        return temp.substring(0, temp.length() - replaceWith.length());
    }

    public static String giveCaseAppNum(final VCoRequest vVo) {
        final char requestType = vVo.getRequestTypeCd();
        String caseAppNum = null;
        long providerId = 0;
        if (requestType == 'C') {
            // case number
            caseAppNum = String.valueOf(vVo.getCaseNum());
        } else if (requestType == 'A') {
            // application number or log id
            // String docId = vCoRequestCargo.getT1DocId();
            // if(docId == null){
            // docId = vCoRequestCargo.getT2DocId();
            // }
            caseAppNum = vVo.getAppNum();
        } else if (requestType == 'L') {
            caseAppNum = String.valueOf(vVo.getLogId());
        } else if (requestType == 'P') {
            providerId = (vVo.getProviderId());
            return String.valueOf(providerId);
        } else if (requestType == 'S') {
            caseAppNum = vVo.getChipAppNum();
        } else if (requestType == 'D') {
            caseAppNum = vVo.getT1DocId();
        }
        return caseAppNum;
    }

    public  List<InDisDocMaster> getAllLinkedRecord(long inDisDocMasterSeqNum) {
        List<InDisDocMaster> resultList = null;
        resultList = inDisDocMasterRepository.getAllLinkedRecord(inDisDocMasterSeqNum);
        return resultList;
    }

    public static byte[] addQrCode(byte[] fileBytes, String str) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            fileBytes = java.util.Base64.getDecoder().decode(fileBytes);
            PdfWriter writer = new PdfWriter(outputStream);
            PdfReader pdfReader = new PdfReader(new ByteArrayInputStream(fileBytes));
            PdfDocument pdfDocument = new PdfDocument(pdfReader,
                    writer);

            com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);
//            String str = "caseNum : 122256186\n" +
//                    "clientId: 112233445\n" +
//                    "fullName: John Doe\n" +
//                    "Age: 51\n" +
//                    "Gender: M";
            BarcodeQRCode barCode = new BarcodeQRCode(str);
            PdfFormXObject pdfFormXObject = barCode.createFormXObject(pdfDocument);
            Image image = new Image(pdfFormXObject);
            image.setWidth(100);
            PageSize pageSize = new PageSize(pdfDocument.getFirstPage().getPageSize());
            pdfDocument.addNewPage(pageSize);
            image.setFixedPosition(0,0);
            document.add(image);

            document.close();
            pdfDocument.close();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] fileArray = java.util.Base64.getEncoder().encode(outputStream.toByteArray());
        return fileArray;
    }

    public static byte[] addBarCode(byte[] fileBytes, String str)  {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            fileBytes = java.util.Base64.getDecoder().decode(fileBytes);
            PdfWriter writer = new PdfWriter(outputStream);
            PdfReader pdfReader = new PdfReader(new ByteArrayInputStream(fileBytes));
            PdfDocument pdfDocument = new PdfDocument(pdfReader,
                    writer);
            com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);

            BarcodeEAN barcode = new BarcodeEAN(pdfDocument);
            barcode.setCodeType(BarcodeEAN.EAN8);
            barcode.setCode(str);
            PdfFormXObject xObject = barcode.createFormXObject(pdfDocument);
            Image image = new Image(xObject);
            image.setFixedPosition(200,0);
            document.add(image);

            document.close();
            pdfDocument.close();
            writer.close();

        }catch (IOException e) {
            e.printStackTrace();
        }


        return java.util.Base64.getEncoder().encode(outputStream.toByteArray());
    }

}
