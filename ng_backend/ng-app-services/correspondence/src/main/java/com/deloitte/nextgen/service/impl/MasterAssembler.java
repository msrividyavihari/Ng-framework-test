package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.bo.validator.CoXSDSchemaValidator;
import com.deloitte.nextgen.entities.*;
import com.deloitte.nextgen.repository.CoRequestHistoryDetailRepository;
import com.deloitte.nextgen.repository.CoRequestHistoryRepository;
import com.deloitte.nextgen.repository.CoRequestRecipientsRepository;
import com.deloitte.nextgen.service.CoAssembler;
import com.deloitte.nextgen.service.CoIESBaseAssembler;
import com.deloitte.nextgen.util.*;
import com.deloitte.nextgen.util.xsd.schema.notices.FormData;
import com.deloitte.nextgen.util.xsd.schema.notices.HeadOfHouse;
import com.deloitte.nextgen.util.xsd.schema.notices.IESCorrespondence;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.rowset.serial.SerialBlob;
import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
@Slf4j
public class MasterAssembler implements CoAssembler {

    @Autowired
    CoDAOServices coDAOServices;

    @Autowired
    CoCommonCode coCommonCode;

    @Autowired
    CoRequestRecipientsRepository repository;

    @Autowired
    CoRequestHistoryDetailRepository historyDetailRepository;

    @Autowired
    CorrespondenceServices correspondenceServices;

    @Autowired
    CoRequestHistoryRepository requestHistoryRepository;
    private JAXBContext jcNotice;
    public Properties propsCorrespondence = null;
    private static final String IES_CORRESPONDENCE_XML_ELEMENT = "<IESCorrespondence>";
    private final ApplicationContext applicationContext;

    public MasterAssembler(ApplicationContext applicationContext) {
        init();
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init(){
        try {
            if(null == jcNotice) {
                jcNotice = JAXBContext.newInstance("com.deloitte.nextgen.util.xsd.schema.notices");
            }
        } catch (JAXBException e) {

        }
    }

    @Override
    public StringBuilder generateDocument(COCorrespondence coCorrespondence, long templateSeqNo) throws Exception {
        log.debug("generateDocument-->CASE_APP_NUMBER"
                + coCorrespondence.getCaseAppNumber() + "-->DOC_ID"
                + coCorrespondence.getDocId() + "-->PrintMode"
                + coCorrespondence.getPrintMode());

        if (coCorrespondence.getDocName() == null) {
            List<CoMaster> coMasterCargoArr;
            coMasterCargoArr = coDAOServices.getCoMasterData(coCorrespondence.getDocId());
            CoMaster coMasterCargo = new CoMaster();
            if (!CoUtil.isEmpty(coMasterCargoArr)) {
                coMasterCargo = coMasterCargoArr.get(0);
            }
            coCorrespondence.setDocName(coMasterCargo.getDocName());
        }

        StringBuilder sb = new StringBuilder();
        String xmlFileName;
        String xmlString;
        String pdfUrl = CoConstants.EMPTY_STRING;
        if (CoConstants.GENERATECO
                .equals(coCorrespondence.getActionValue())
                || (CoConstants.COVPC + CoConstants.GENERATECO)
                .equals(coCorrespondence.getActionValue())
                || (CoConstants.MASS + CoConstants.GENERATECO)
                .equals(coCorrespondence.getActionValue())) {
            if (CoUtil.isEmpty(coCorrespondence.getLangCd())) {
                coCorrespondence.setLangCd(coCommonCode
                        .getLangCdDescByCaseAppNum(coCorrespondence
                                .getCaseAppNumber()));
            }
            xmlString = generateXML(getIESCorrespondence(coCorrespondence));
            xmlString = xmlString.substring(xmlString
                    .indexOf(IES_CORRESPONDENCE_XML_ELEMENT));
            if (coCorrespondence.isPreviewMode()) {
                return sb.append(xmlString);
            } else {
                xmlFileName = getXMLFileNameForRecipient(coCorrespondence);
                if (CoConstants.ZERO_STRING.equals(coCorrespondence.getCaseAppNumber())
                        || (CoConstants.CHAR_I == coCorrespondence.getDocType())) {
                   // pdfUrl = invokeAdobeNoMarkService(xmlFileName,xmlString, coCorrespondence);
                } else {
                    //pdfUrl = getAdobeLocalPrintUrl(xmlFileName, xmlString, coCorrespondence);
                    if ((coCorrespondence.getPrevCoReqSeq() > 0) && !coCorrespondence.getDocId()
                            .startsWith(String.valueOf(CoConstants.CO_DOC_TYPE_CD_N))) {
                        coCorrespondence.setCoReqSeq(coCorrespondence.getPrevCoReqSeq());
                        xmlString = xmlString.replace(xmlString.substring(
                                        xmlString.indexOf("<corrNum>"),
                                        xmlString.indexOf("<watermark>")),
                                "<corrNum>" + coCorrespondence.getPrevCoReqSeq()
                                        + "</corrNum>");
                        xmlFileName = getXMLFileNameForRecipient(coCorrespondence);

                        coCorrespondence.setDisId(CoConstants.ZERO_STRING);
                        coCorrespondence.setActionValue(CoConstants.UPDATE_CPC);
                       /* invokeAdobeCentralPrint(xmlString, xmlFileName, CoConstants.FILE_TYPE_APPLICATION_XML,
                                coCorrespondence);*/

                        updateCoRequestHistory(coCorrespondence, xmlString);
                        final String saveDirPath = "C:/CORRESPONDENCE/"; //propsCorrespondence.getProperty(CoConstants.CO_TEMP_XML_PATH);
                        coCorrespondence.setFileLocation(saveDirPath
                                + xmlFileName);
                        coDAOServices.updateCoReqRecipientFileName(coCorrespondence.getCoReqSeq(),
                                coCorrespondence.getFileLocation());
                        return sb.append(xmlString);
                    }
                }
            }
            sb.append(pdfUrl);
        } else if (CoConstants.UPDATE_CPC.equals(coCorrespondence
                .getActionValue())) {
            //Step 1 : Generating the XML
            xmlString = generateXML(getIESCorrespondence(coCorrespondence));
            /*
             Step 2 : Update CO_REQUEST_HIST with xmlString as
             HST_PRINT_STRING
             */
            updateCoRequestHistory(coCorrespondence, xmlString);
            xmlFileName = getXMLFileNameForRecipient(coCorrespondence);
            /*invokeAdobeCentralPrint(xmlString, xmlFileName,
                    CoConstants.FILE_TYPE_APPLICATION_XML, coCorrespondence);*/
            final String saveDirPath = "C:/CORRESPONDENCE/"; //propsCorrespondence.getProperty(CoConstants.CO_TEMP_XML_PATH);
            coCorrespondence.setFileLocation(saveDirPath + xmlFileName);
            coDAOServices.updateCoReqRecipientFileName(coCorrespondence.getCoReqSeq(),
                    coCorrespondence.getFileLocation());
            log.debug("Returning XML");
            return sb.append(xmlString);
        }
        return sb;
    }

    @Override
    public String generateDocumentForBatch(COCorrespondence coCorrespondence) {
        return null;
    }

    @Override
    public void generateCentralPrintXML(final COCorrespondence coCorrespondence)
            throws Exception {

        /*Step 1 : Generating the XML */

        final String xmlString = generateXML(getIESCorrespondence(coCorrespondence));

        /*Step 2 : Update CO_REQUEST_HIST with xmlString as HST_PRINT_STRING*/
        updateCoRequestHistory(coCorrespondence, xmlString);
        final String xmlFileName = getXMLFileName(coCorrespondence);
      //  invokeAdobeCentralPrint(xmlString, xmlFileName,
        //        CoConstants.FILE_TYPE_APPLICATION_XML, coCorrespondence);

        final String saveDirPath = "C:/CORRESPONDENCE/";//propsCorrespondence.getProperty(CoConstants.CO_TEMP_XML_PATH);
        coCorrespondence.setFileLocation(saveDirPath + xmlFileName);
        coDAOServices.updateCoReqRecipientFileName(
                coCorrespondence.getCoReqSeq(),
                coCorrespondence.getFileLocation());
    }

    private String getXMLFileName(final COCorrespondence coCorrespondence) throws Exception {
        Long indvId;
        indvId = correspondenceServices.getHohIdFromCaseAppNum(coCorrespondence);
        return prepareXMLFileNameForRecipient(indvId,
                coCorrespondence.getCoReqSeq(),
                coCorrespondence.getGenerateDate());
    }

    @Override
    public String generateLocalPrintXML(COCorrespondence coCorrespondence) throws Exception {
        String xmlString = generateXML(getIESCorrespondence(coCorrespondence));
        xmlString = xmlString.substring(xmlString.indexOf(IES_CORRESPONDENCE_XML_ELEMENT));
        final String xmlFileName = getXMLFileNameForRecipient(coCorrespondence);

        /*if (!CoConstants.ZERO_STRING
                .equals(coCorrespondence.getCaseAppNumber())) {
            updateDisId(xmlFileName, xmlString, coCorrespondence, data);
        }*/
        System.out.println("generateXML<--" + xmlString);
        return xmlString;

    }

    @Override
    public String generateLocalPrintNoMarkXML(
            final COCorrespondence coCorrespondence) throws Exception {
        String xmlString = generateXML(getIESCorrespondence(coCorrespondence));
        xmlString = xmlString.substring(xmlString
                .indexOf(IES_CORRESPONDENCE_XML_ELEMENT));
        updateCoRequestHistory(coCorrespondence, xmlString);
        final String xmlFileName = getXMLFileNameForRecipient(coCorrespondence);

        final String pdfUrl = null;
                //invokeAdobeNoMarkService(xmlFileName, xmlString, coCorrespondence);

        final String saveDirPath = "C:/CORRESPONDENCE/";//propsCorrespondence.getProperty(CoConstants.CO_TEMP_XML_PATH);
        coCorrespondence.setFileLocation(saveDirPath + xmlFileName);
        coDAOServices.updateCoReqRecipientFileName(
                coCorrespondence.getCoReqSeq(),
                coCorrespondence.getFileLocation());

        return pdfUrl;
    }

    private String generateXML(Object cc) throws Exception {
        String xmlString;
        StringWriter writer;
        try {
            writer = new StringWriter();

            // Marshal object into file.
            if (cc instanceof com.deloitte.nextgen.util.xsd.schema.notices.IESCorrespondence) {
                com.deloitte.nextgen.util.xsd.schema.notices.IESCorrespondence iesCorrespondence =
                        (com.deloitte.nextgen.util.xsd.schema.notices.IESCorrespondence) cc;
                new CoXSDSchemaValidator().validate(iesCorrespondence);
                jcNotice = JAXBContext.newInstance("com.deloitte.nextgen.util.xsd.schema.notices");
                Marshaller m = jcNotice.createMarshaller();
                CharacterEscapeHandler escapeHandler = new JaxbCharacterEscapeHandler();
                m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                m.setProperty(CoConstants.XML_CHAR_ESC_HANDLER, escapeHandler);
                m.marshal(
                        new JAXBElement<>(
                                new QName(CoConstants.IES_CORRESPONDENCE),
                                com.deloitte.nextgen.util.xsd.schema.notices.IESCorrespondence.class,
                                (com.deloitte.nextgen.util.xsd.schema.notices.IESCorrespondence) cc),
                        writer);
            }

            xmlString = writer.toString();
            xmlString = xmlString.replace(CoConstants.AND, CoConstants.AND_SP);
            System.out.println("generateXML<--" + xmlString);
            return xmlString;

        } catch (final JAXBException e) {
            throw new Exception("CoException while generating XML: "+ e.getMessage());
        } catch (Exception e) {
            throw new Exception("CoException while generating XML: " + e.getMessage(),e);
        }
    }

    private IESCorrespondence getIESCorrespondence(final COCorrespondence coCorrespondence) throws Exception {
        Long indvId;
        IndividualInformationCargo indvInfoCargo = null;
        final CoIESBaseAssembler assembler = getAssembler(coCorrespondence.getDocId());
        final IESCorrespondence iesCorrespondence = assembler.getIESCorrespondence(coCorrespondence);
        final FormData formData = iesCorrespondence.getFormData();
        if (!(CoConstants.MASS + CoConstants.GENERATECO)
                .equals(coCorrespondence.getActionValue())
                && (!coCorrespondence.getCaseAppNumber().equalsIgnoreCase(
                CoConstants.ZERO_STRING))) {
            final HeadOfHouse headofHouse = new HeadOfHouse();

            indvId = correspondenceServices.getHohIdFromCaseAppNum(coCorrespondence);
            indvInfoCargo = correspondenceServices.getIndvDetails(indvId);

            headofHouse.setHohName(indvInfoCargo.getName());
            headofHouse.setHohId(Long.toString(indvInfoCargo.getIndividualID()));
            formData.setHeadOfHouse(headofHouse);
        }
        /* DIS MetaData start here. */
        if (!coCorrespondence.isPreviewMode()) {
            formData.setDocTitle(coCorrespondence.getDocName());
            formData.setDocType(CoConstants.IESPDF);
            formData.setDocAuthor(CoConstants.IES);
            formData.setSecurityGroup(CoConstants.DHS);
            formData.setDocAccount(CoConstants.ALL_DHS);
            formData.setFileName(coCorrespondence.getXmlFileName());
            formData.setCaptureSource(CoConstants.IES_UPLOAD);
            if (coCorrespondence.getCaseAppFlag() == CoConstants.APPLICATION) {
                formData.setCompassWebID(coCorrespondence.getCaseAppNumber());
            }
            if (!coCorrespondence.getCaseAppNumber().equals(CoConstants.ZERO_STRING)) {
                formData.setClientId(formData.getHeadOfHouse().getHohId());
                if (indvInfoCargo != null) {
                    formData.setSsn(indvInfoCargo.getSsn());
                }
                formData.setTransactionID(CoConstants.IESUW + coCorrespondence.getCoReqSeq());
            }
            if (coCorrespondence.getCaseAppFlag() == CoConstants.CASE) {
                formData.setAssistanceUnit(coCorrespondence.getCaseAppNumber());
            }
        }
        /* DIS MetaData ends here. */
        iesCorrespondence.setFormData(formData);
        return iesCorrespondence;
    }

    private String getXMLFileNameForRecipient(final COCorrespondence coCorrespondence) throws Exception {
        List<CoRequestRecipients> coReqRptCargos;
        Long indvId;
        String xmlFileName = "";
        coReqRptCargos = repository.findByCoReqSeqOrderByCoRptSeq(coCorrespondence.getCoReqSeq());
        if (!CoUtil.isEmpty(coReqRptCargos)) {
            final CoRequestRecipients coReqRptCargoObj = coReqRptCargos.get(0);
            indvId = correspondenceServices.getHohIdFromCaseAppNum(coCorrespondence);
            xmlFileName = prepareXMLFileNameForRecipient(indvId,
                    coReqRptCargoObj.getCoReqSeq(),
                    coCorrespondence.getGenerateDate());
        }
        return xmlFileName;
    }

    private CoIESBaseAssembler getAssembler(final String docID) {
        final String className = docID + "Assembler";
        return (CoIESBaseAssembler) this.applicationContext.getBean(className);
    }

    private void updateCoRequestHistory(
            final COCorrespondence coCorrespondence, final String xmlString) throws Exception {
        try {
            CoRequestHistory coRequestCargo;
            final List<CoRequestHistory> coRequestHistoryCargoArray = requestHistoryRepository.findByCoReqSeq(coCorrespondence.getCoReqSeq());
            if (!CoUtil.isEmpty(coRequestHistoryCargoArray)) {
                coRequestCargo = coRequestHistoryCargoArray.get(0);
                // convert String into InputStream
                Blob bd = new SerialBlob(xmlString.getBytes());
                coRequestCargo.setHstPrintString(bd);
                if (StringUtils.isNotEmpty(coCorrespondence.getDisId())) {
                    coRequestCargo.setDisId(coCorrespondence.getDisId());
                }
                if (CoConstants.UPDATE_CPC.equals(coCorrespondence
                        .getActionValue())) {
                    final Timestamp fwCalDt = CoUtil.getCurrentDate();
                    coRequestCargo.setPendingTrigSw(CoConstants.CHAR_N);
                    coRequestCargo.setOrigPrintDt(fwCalDt);
                    updateCoReqHistDetail(coCorrespondence.getCoReqSeq(),
                            fwCalDt);
                }
                requestHistoryRepository.save(coRequestCargo);
            }
        } catch (final Exception e) {
            throw new Exception("Exception while generating document in MasterAssembler.generateDocument(): "
                            + e.getMessage());
        }
    }

    private void updateCoReqHistDetail(final long coReqSeq,
                                       final Timestamp fwCalDt){

        CoRequestHistoryDetail coRequestHistoryDetailCargo;
        final List<CoRequestHistoryDetail> coReqHistDetailCargoArray = historyDetailRepository.findByCoReqSeq(coReqSeq);
        if (!CoUtil.isEmpty(coReqHistDetailCargoArray)) {
            coRequestHistoryDetailCargo = coReqHistDetailCargoArray.get(0);
            coRequestHistoryDetailCargo.setPrintDt(fwCalDt);
            historyDetailRepository.save(coRequestHistoryDetailCargo);
        }
    }

    private String prepareXMLFileNameForRecipient(final Long hohId,
                                                  final Long coRptSeq, final Date generateDate) {

        final StringBuilder sbFileName = new StringBuilder();
        sbFileName.append(hohId);
        sbFileName.append("_");
        sbFileName.append(coRptSeq);
        sbFileName.append("_");
        final String strDt = new SimpleDateFormat(
                CoConstants.DATE_FORMAT_MMDDYYYY).format(generateDate);
        sbFileName.append(strDt);
        sbFileName.append(CoConstants.XML_FILE_EXTN);
        return sbFileName.toString();
    }

    private void updateDisId(final String xmlFileName, final String xmlString,
                             final COCorrespondence coCorrespondence, final byte[] data)
            throws Exception {
        final String saveDirPath = propsCorrespondence
                .getProperty(CoConstants.CO_TEMP_XML_PATH);
        coCorrespondence.setFileLocation(saveDirPath + xmlFileName);
        /*Update CO_REQUEST_RECIPIENTS table with file location.*/
        coDAOServices.updateCoReqRecipientFileName(
                coCorrespondence.getCoReqSeq(),
                coCorrespondence.getFileLocation());
        final String pdfFileName = xmlFileName.replace(".xml", ".pdf");
        final long docId = uploadDocToDIS(data, pdfFileName, coCorrespondence);
        coCorrespondence.setDisId(String.valueOf(docId));
        /*Update CO_REQUEST_HISTORY with disId & xmlString as HST_PRINT_STRING*/
        updateCoRequestHistory(coCorrespondence, xmlString);
        /*Insert record into Doc Master table for corresponding CO_REQ_SEQ.*/
        CorrespondenceServices.insertDisDocMasterRecord(coCorrespondence);
    }

    private long uploadDocToDIS(final byte[] data, final String pdfFileName,
                                final COCorrespondence coCorrespondence) {
        /*Long indvId = null;
        IndividualInformationCargo indvInfoCargo = null;
        indvId = CorrespondenceServices
                .getHohIdFromCaseAppNum(coCorrespondence);
        indvInfoCargo = CorrespondenceServices.getIndvDetails(indvId);
        final UpdateDocInfoBO updateDocInfoBO = new UpdateDocInfoBO();
        final CustomDocMetaData customDocMetaData = new CustomDocMetaData();
        final List<Property> prop3Lst = new ArrayList<>();

        addProperty("caseAppNum",coCorrespondence.getCaseAppNumber(),prop3Lst);
        addProperty("personId",String.valueOf(indvId),prop3Lst);
        addProperty("xCaptureID",String.valueOf(coCorrespondence.getEmpId()),prop3Lst);
        addProperty("xCaptureSource",CoConstants.IES_UPLOAD,prop3Lst);
        if (coCorrespondence.getCaseAppFlag() == CoConstants.APPLICATION) {
            addProperty("xCompassWebID",coCorrespondence.getCaseAppNumber(),prop3Lst);
        }

        final SimpleDateFormat formatter = new SimpleDateFormat(
                "MM/dd/yyyy HH:mm:ss");

        final String dateOfEntry = formatter.format(FwCalendar.getInstance()
                .getDate().getTimestamp());

        addProperty("xDateofEntry",dateOfEntry,prop3Lst);
        addProperty("xDocumentType",CoConstants.IESPDF,prop3Lst);
        addProperty("xCRSClientID",String.valueOf(indvId),prop3Lst);
        addProperty("xSSN",String.valueOf(indvInfoCargo.getSsn()),prop3Lst);
        addProperty("xTransactionID",CoConstants.IESLP
                + coCorrespondence.getCoReqSeq(),prop3Lst);
        addProperty("xClientID",String.valueOf(indvId),prop3Lst);
        addProperty("xIdcProfile","Adobe",prop3Lst);
        if (coCorrespondence.getCaseAppFlag() == CoConstants.CASE) {
            addProperty("xAssistanceUnit",coCorrespondence.getCaseAppNumber(),prop3Lst);
        }
        addProperty("xNoticeType",CorrespondenceServices.
                getDISDNoticeType(coCorrespondence.getDocId()),prop3Lst);

        customDocMetaData.getProperty().addAll(prop3Lst);

        final CheckInUniversal checkInUniversal = new CheckInUniversal();
        checkInUniversal.setDocTitle("IES Notice");
        checkInUniversal.setDocName(pdfFileName);
        checkInUniversal.setDocType(pdfFileName.substring(pdfFileName
                .lastIndexOf(".pdf")));
        final PrimaryFile uploadedFile = new PrimaryFile();
        uploadedFile.setFileName(pdfFileName);
        uploadedFile.setFileContent(data);
        checkInUniversal.setPrimaryFile(uploadedFile);
        checkInUniversal.setCustomDocMetaData(customDocMetaData);

        Holder<CheckInUniversalResponse> checkInUniversalResponseHolder = new Holder<>();
        final Holder<Fault> fault = new Holder<>();
        CheckInUniversalResult checkInUniversalResult = null;
        try {
            checkInUniversalResponseHolder = updateDocInfoBO.checkInDIS(
                    checkInUniversal, checkInUniversalResponseHolder, fault);
            if (checkInUniversalResponseHolder.value != null) {
                checkInUniversalResult = checkInUniversalResponseHolder.value
                        .getCheckInUniversalResult();

                if (checkInUniversalResult != null) {
                    docId = checkInUniversalResult.getID();
                }
            }
        } catch (ApplicationException e) {
            CoDebugger
                    .debugMessage("Error while invoking CheckInDIS webservice API: "
                            + e.getMessage());
            throw new CoException(CoConstants.DIS_UPLOAD_DOC_EXCEPTION + " : "
                    + e.getMessage());
        }
        if (docId < 1) {
            CoDebugger
                    .debugMessage("Error inside uploadDocToDIS method (docId): "
                            + docId);
            throw new CoException(CoConstants.DIS_UPLOAD_DOC_EXCEPTION + " : "
                    + checkInUniversalResult);
        }*/
        return 0;
    }
}
