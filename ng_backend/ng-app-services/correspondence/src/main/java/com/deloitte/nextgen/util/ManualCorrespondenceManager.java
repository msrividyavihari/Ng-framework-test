package com.deloitte.nextgen.util;

import com.deloitte.nextgen.dto.entities.CoGenerateManualDTO;
import com.deloitte.nextgen.dto.entities.CoManualDataDTO;
import com.deloitte.nextgen.dto.entities.CoManualFieldsDTO;
import com.deloitte.nextgen.dto.entities.FwDataElementListDTO;
import com.deloitte.nextgen.entities.COCorrespondence;
import com.deloitte.nextgen.entities.CoManualData;
import com.deloitte.nextgen.entities.CoMaster;
import com.deloitte.nextgen.mapper.CoManualDataMapper;
import com.deloitte.nextgen.repository.CoManualDataRepository;
import com.deloitte.nextgen.service.CoAssembler;
import com.deloitte.nextgen.service.ICoOnline;
import com.deloitte.nextgen.service.impl.CoDAOServices;
import com.deloitte.nextgen.service.impl.CorrespondenceServices;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ManualCorrespondenceManager {

    @Autowired
    CoDAOServices coDAOServices;

    @Autowired
    ICoOnline iCorrespondence;

    @Autowired
    CoAssembler assembler;

    private boolean dataFoundInManualData = false;

    @Autowired
    CoManualDataRepository coManualDataRepository;

    @Autowired
    CoManualDataMapper coManualDataMapper;

    @Autowired
    CoManualDataDTO coManualDataDTO;

    public ManualCorrespondenceManager(CoDAOServices coDAOServices) {
        this();
        this.coDAOServices = coDAOServices;
    }

    public ManualCorrespondenceManager() {
        super();
    }


    /**
     * This method is used to save the manual data entered from the screen. The
     * method calls the CoManualDataRepository and persists the data into data
     * base.
     *
     * @param fields CoManualFields[]
     * @param area   CoManualFields[]
     * @return boolean
     * @throws Exception Exception
     */
    public boolean saveFieldData(final CoManualFieldsDTO[] fields,
                                 final CoManualFieldsDTO[] area, COCorrespondence coRequest, FwDataElementListDTO[] fieldsFw, FwDataElementListDTO[] areasFw)
            throws Exception {

        boolean status;
        try {

            // Always a new trigger when come from Case Info Page
            if ((coRequest != null) && coRequest.isManualCorrespondence()) {
                coRequest.setCoReqSeq(0L);
                coRequest.setCoDetSeq(0L);
            }
            if (null != coRequest) {
                coRequest.setDisId(null);
            }
            int errorCode;
            if (null != coRequest && CoConstants.GENERATECO.equals(coRequest.getActionValue())
                    && !coRequest.getDocId().startsWith(
                    String.valueOf(CoConstants.CO_DOC_TYPE_CD_N))
                    && !CoConstants.ZERO_STRING.equals(coRequest
                    .getCaseAppNumber())
                    && !coRequest.getActionValue().equalsIgnoreCase(CoConstants.COVPC)) {


                /*
                 Central Print record(part of Local Print action) insertion
                 logic starts here. The Central Print record will be inserted
                 only
                 */
                coRequest.setActionValue(CoConstants.UPDATE_CPC);
                coRequest.setPrintMode('B');
                coRequest.setHistorySwitch(CoConstants.CHAR_N);
                coRequest.setPendingTrigSw('N');
                coRequest.setDocType(CoMasterDocTypeCd(coRequest));
                errorCode = coDAOServices.insertOriginalRequest(coRequest);
                status = saveManualData(iCorrespondence.isNewCorrespondence(),
                        fields, area, fieldsFw, areasFw, coRequest);
                coRequest.setPrevCoReqSeq(coRequest.getCoReqSeq());
                /*
                 Central Print record(part of Local Print action) insertion
                 logic ends here.
                 */

                /*Local Print record insertion logic starts here */
                coRequest.setActionValue(CoConstants.GENERATECO);
                coRequest.setCoReqSeq(0L);
                coRequest.setCoDetSeq(0L);
                coRequest.setPrintMode('O');
                coRequest.setPendingTrigSw('N');
                coRequest.setDraftSwitch('N');
                coRequest.setDisId(null);
                errorCode = iCorrespondence.initiateCorrespondence(coRequest);
                /* Local Print record insertion logic ends here */
            } else {
                errorCode = iCorrespondence.initiateCorrespondence(coRequest);
                if (errorCode == -1) {
                    status = false;
                } else {
                    assert coRequest != null;
                    status = saveManualData(
                            iCorrespondence.isNewCorrespondence(), fields,
                            area, fieldsFw, areasFw, coRequest);
                }
            }
        } catch (Exception ae) {
            throw new Exception(ae);
        }
        return status;
    }

    private boolean saveManualData(boolean isNewCorrespondence,
                                   CoManualFieldsDTO[] fields,
                                   CoManualFieldsDTO[] area, FwDataElementListDTO[] fieldsFw, FwDataElementListDTO[] areasFw,
                                   COCorrespondence coRequest) {
        boolean status = false;
        CoManualDataDTO finalCoManualFieldsDTO = new CoManualDataDTO();
/*
        HashMap<String,String> map = new HashMap<String,String>(){{
           put("AP","AP");
        }};
*/
       // final Map reqParams = coRequest.getFormData();
        final Map reqParams = coRequest.getFormData().keySet().stream().collect(Collectors.toMap(key -> key.toLowerCase(), key ->coRequest.getFormData().get(key)));
      /*  final CoManualDataDTO[] toSave = new CoManualDataDTO[fields.length
                + area.length];*/
        List<CoManualDataDTO> toSave = new ArrayList<>();

        final long reqSeqNo = coRequest.getCoReqSeq();

        String dataToSave; // added for saving multiple values
        final List<CoManualData> bean1 = new ArrayList<>();
        CoManualData[] myCargos = null;
        int fieldLen = CoUtil.isEmpty(fields)?0:fields.length;
        for (int i = 0; i < fieldLen; i++) {
            final CoManualData myCargo = new CoManualData();
            myCargo.setCoReqSeq(reqSeqNo);
            myCargo.setSeqNum(fields[i].getSeqNum());
            myCargo.setFieldOrderNum(fields[i].getFieldOrderNum());
            bean1.add(myCargo);
            List<CoManualData> caseList = coManualDataRepository.findBySeqNumReqSeqAndFieldOrder(myCargo.getCoReqSeq(), myCargo.getSeqNum(),
                    myCargo.getFieldOrderNum());

            if ((caseList != null) && (caseList.size() > 0)) {
                for (CoManualData data : caseList) {
                    toSave.add(i,coManualDataMapper.toDto(data));
                    dataFoundInManualData = true;
                }
            } else {
                    toSave.add(i ,new CoManualDataDTO());
            }
            toSave.get(i).setCoReqSeq(reqSeqNo);

            String ignoreCaseScreenEle = "";
            // added for saving multiple values
            if (reqParams != null) {
                ignoreCaseScreenEle = fieldsFw[i].getDeScreenElementName().toLowerCase(Locale.ROOT);
                if (reqParams.get(ignoreCaseScreenEle) instanceof String) {

                    dataToSave = ((String) reqParams.get(ignoreCaseScreenEle));
                } else {
                    dataToSave = getMultipleData((String[]) reqParams
                            .get(fieldsFw[i].getDeScreenElementName()));
                }
            } else {
                if (fieldsFw[i].getDeScreenElementName() instanceof String) {

                    dataToSave = fieldsFw[i]
                            .getDeScreenElementName();
                } else {
                    dataToSave = getMultipleData((String[]) reqParams.get(ignoreCaseScreenEle));
                }
            }

            if (dataToSave == null) {
                dataToSave = " ";
            } else if ((dataToSave.trim().length() > 0)
                    && (dataToSave.indexOf("***") != -1)) {
                dataToSave = CorrespondenceServices.replaceAll(dataToSave,
                        "***", CoConstants.EMPTY_STRING);
            }
            if (null == dataToSave) {
                dataToSave = "";
            }
            toSave.get(i).setFieldContent(dataToSave.trim());
            toSave.get(i).setFieldOrderNum(fields[i].getFieldOrderNum());
            toSave.get(i).setSeqNum(fields[i].getSeqNum());
        }
        int index = 0;
        int areaLen = CoUtil.isEmpty(area)?0:area.length;
        for (int i = fieldLen; i < (fieldLen + areaLen); i++) {
            final CoManualData myCargo = new CoManualData();
            myCargo.setCoReqSeq(reqSeqNo);
            myCargo.setSeqNum(area[index].getSeqNum());
            myCargo.setFieldOrderNum(area[index].getFieldOrderNum());
            bean1.add(myCargo);

            List<CoManualData> caseList = coManualDataRepository.findBySeqNumReqSeqAndFieldOrder(myCargo.getCoReqSeq(), myCargo.getSeqNum(),
                    myCargo.getFieldOrderNum());

            if ((caseList != null) && (caseList.size() > 0)) {
                for (CoManualData data : caseList) {
                    toSave.add(i, coManualDataMapper.toDto(data));
                    dataFoundInManualData = true;
                }
            } else {
                    toSave.add(i ,new CoManualDataDTO());
            }
            toSave.get(i).setCoReqSeq(reqSeqNo);
            // added for saving multiple values
            String ignoreCaseScreenEle = "";
            if (reqParams != null) {
                ignoreCaseScreenEle = areasFw[index].getDeScreenElementName().toLowerCase(Locale.ROOT);

                if (reqParams.get(ignoreCaseScreenEle) instanceof String) {
                    dataToSave = ((String) reqParams.get(ignoreCaseScreenEle));
                } else {
                    dataToSave = getMultipleData((String[]) reqParams
                            .get(ignoreCaseScreenEle));
                }
            } else {
                if (fieldsFw[i].getDeScreenElementName() instanceof String) {

                    dataToSave = fieldsFw[i]
                            .getDeScreenElementName();
                } else {
                    dataToSave = getMultipleData((String[]) reqParams
                            .get(ignoreCaseScreenEle));
                }
            }

            if (dataToSave == null) {
                dataToSave = " ";
            } else if ((dataToSave.trim().length() > 0)
                    && (dataToSave.indexOf("***") != -1)) {
                dataToSave = CorrespondenceServices.replaceAll(dataToSave,
                        "***", CoConstants.EMPTY_STRING);
            }
            if (null == dataToSave) {
                dataToSave = "";
            }
            toSave.get(i).setFieldContent(dataToSave.trim());
            // added for saving multiple values
            toSave.get(i).setFieldOrderNum(area[index].getFieldOrderNum());
            toSave.get(i).setSeqNum(area[index].getSeqNum());
            index++;
        }

        if (toSave.size() > 0) {
            final List<CoManualDataDTO> coManualDataList = toSave;
            for (CoManualDataDTO cargo : coManualDataList) {

                if ((isNewCorrespondence == true)
                        && (dataFoundInManualData == false)) {

                    finalCoManualFieldsDTO = insertOrUpdateIntoCoManualData(cargo, true);

                } else if ((isNewCorrespondence == false)
                        && (dataFoundInManualData == false)) {

                    finalCoManualFieldsDTO = insertOrUpdateIntoCoManualData(cargo, true);

                } else if ((isNewCorrespondence == false)
                        && (dataFoundInManualData == true)) {

                    finalCoManualFieldsDTO = insertOrUpdateIntoCoManualData(cargo, false);

                }
            }
            status = true;
        }
        return status;
    }


    public char CoMasterDocTypeCd(final COCorrespondence aCoObj)
            throws Exception {

        char dbType;
        String uDocId = aCoObj.getDocId();
        if ((uDocId == null) || uDocId.equals("")) {
            // 16397 Invalid Document-ID for Correspondence Request
            throw new Exception(
                    "Invalid Document-ID in Correspondence Request");
        } else {
            uDocId = uDocId.toUpperCase();
            aCoObj.setDocId(uDocId);
            List<CoMaster> masterCargo;

            masterCargo = coDAOServices
                    .getCoMasterData(aCoObj.getDocId());

            if ((masterCargo != null) && (masterCargo.size() > 0)) {
                dbType = masterCargo.get(0).getDocTypeCd();
            } else if (aCoObj.getCoReqSeq() == 0) {
                // Suppress this exception call in case of Reprints - Mathew
                // earlier return 317;
                // 16028 No data in CoMaster: CoMaster:: findByDocId
                throw new Exception("No Data for DocId in CoMaster ");
            } else { // Reprint Request
                // Set these as the default values

                dbType = aCoObj.getDocType();
                // We expect this value to always be present in the Co
                // Object in this case
            }
        }
        return dbType;
    }

    /**
     * The method to get multiple data
     *
     * @param allData String[]
     * @return String
     */
    private String getMultipleData(final String[] allData) {
        final StringBuilder data = new StringBuilder();
        if (allData != null) {
            for (int i = 0; i < allData.length; i++) {
                if ((allData[i] != null) && (allData[i].trim().length() > 0)) {
                    data.append(allData[i]);
                    if (i < (allData.length - 1)) {
                        data.append(",");
                    }
                }
            }
        } else {
            data.append(" ");
        }
        return data.toString();
    }


    public CoManualDataDTO insertOrUpdateIntoCoManualData(CoManualDataDTO cargo, boolean isInsert) {
        CoManualData coManualDataResp = new CoManualData();
        CoManualDataDTO coManualDataDTO = new CoManualDataDTO();
        coManualDataResp.setCoReqSeq(cargo.getCoReqSeq());
        coManualDataResp.setFieldContent(cargo.getFieldContent());
        coManualDataResp.setFieldOrderNum(cargo.getFieldOrderNum());
        coManualDataResp.setSeqNum(cargo.getSeqNum());

        List<CoManualData> coManualDataUpdateResp;

        if (isInsert) {
            coManualDataResp = coManualDataRepository.save(coManualDataResp);
            coManualDataDTO = coManualDataMapper.toDto(coManualDataResp);
        } else {
            coManualDataUpdateResp = coManualDataRepository.findBySeqNumReqSeqAndFieldOrder(coManualDataResp.getCoReqSeq(), coManualDataResp.getSeqNum(), coManualDataResp.getFieldOrderNum());
            for (CoManualData c : coManualDataUpdateResp)
                coManualDataResp =  coManualDataRepository.save(c);
            coManualDataDTO = coManualDataMapper.toDto(coManualDataResp);
        }

        return coManualDataDTO;
    }

    public String generateCorrespondence(CoGenerateManualDTO generateManualReqDTO, COCorrespondence coRequest)
    {
        boolean status;
        String xmlStr = null;
        final CoManualFieldsDTO[] fieldsDTO = generateManualReqDTO.getCoManualFieldsMap().get("manualFields");
        final CoManualFieldsDTO[] areasDTO = generateManualReqDTO.getCoManualFieldsMap().get("manualAreas");
        final FwDataElementListDTO[] fieldsFw = generateManualReqDTO.getFwDataElementListMap().get("fieldsFw");
        final FwDataElementListDTO[] areasFw = generateManualReqDTO.getFwDataElementListMap().get("fieldsFw");

        final Map<Long, String> coReqSeqLangCdMap = coRequest.getCoReqSeqLangCdMap();
        if (!coReqSeqLangCdMap.isEmpty()) {
            final Set<Long> coReqSeqSet = new TreeSet<>(coReqSeqLangCdMap.keySet());

            for (final Long key : coReqSeqSet) {
                coRequest.setCoReqSeq(key);
                coRequest.setLangCd(coReqSeqLangCdMap.get(key));
                if (CoConstants.GENERATECO.equals(coRequest.getActionValue())
                        || CoConstants.UPDATE_CPC.equals(coRequest.getActionValue())) {
                    status = saveManualData(iCorrespondence.isNewCorrespondence(), fieldsDTO,areasDTO,fieldsFw,areasFw,coRequest);
                } else {
                    status = true;
                }
                if (status) {
                    xmlStr = generateXML(coRequest);
                }
            }
        }
        return xmlStr;
    }

    private String generateXML(final COCorrespondence coRequest) {
        boolean status = true;
        String pdfPath = CoConstants.EMPTY_STRING;
        try {
            if (coRequest.getPrintMode() == CoConstants.CHAR_B) {
                assembler.generateCentralPrintXML(coRequest);
            } else if (CoConstants.ZERO_STRING.equals(coRequest.getCaseAppNumber())) {
                pdfPath = assembler.generateLocalPrintNoMarkXML(coRequest);
            } else {
                pdfPath = assembler.generateLocalPrintXML(coRequest);
            }
        } catch (IllegalAccessException | InstantiationException
                | ClassNotFoundException ex) {
            log.debug("Failed generating XML for local or Central Print", ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdfPath;
    }

    public String generateCorrespondence(CoGenerateManualDTO generateManualDTO, CoManualFieldsDTO[] fields,
                                           CoManualFieldsDTO[] area, FwDataElementListDTO[] fieldsFw, FwDataElementListDTO[] areasFw, COCorrespondence coRequest) throws Exception {
        String xmlStr=null;
        boolean status = false;
        final String preview = generateManualDTO.getPreview();
        if(CoConstants.ZERO_STRING.equals(coRequest.getCaseAppNumber())
                || (CoConstants.CHAR_I == coRequest.getDocType())
                || ((preview != null) && preview.equalsIgnoreCase("true"))) {
            status = true;
        } else {
            status = saveFieldData(fields, area, coRequest,fieldsFw,areasFw);
        }
        if (fields == null || fields.length == 0) {
            status = true;
        }
        try {
            if (preview != null) {
                if (preview.equalsIgnoreCase("true")) {
                    coRequest.setDraftSwitch('Y');
                    coRequest.setSecuritySw('Y');
                    coRequest.setPreviewMode(true);
                } else {
                    coRequest.setDraftSwitch('N');
                    coRequest.setSecuritySw('N');
                    coRequest.setPrintMode('O');
                    coRequest.setPreviewMode(false);
                }
            } else {
                coRequest.setDraftSwitch('N');
                coRequest.setSecuritySw('N');
            }
            coRequest.setPrintMode('O');
            xmlStr = iCorrespondence.printXMLPreviewCo(coRequest);
            final String pdfPath = iCorrespondence.getPdfPath();
        } catch (final ApplicationException ae) {
            throw ae;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlStr;
    }

    public String initiateLocalPrintNoMark(final CoManualFieldsDTO[] fields,
                                           final CoManualFieldsDTO[] area, CoGenerateManualDTO generateManualDTO, COCorrespondence coCorrespondence)
            throws Exception {
        int errorCode;
        String xml = null;
        try {
            final COCorrespondence coRequest = coCorrespondence;
            final FwDataElementListDTO[] fieldsFw = generateManualDTO.getFwDataElementListMap().get("fieldsFw");
            final FwDataElementListDTO[] areasFw = generateManualDTO.getFwDataElementListMap().get("fieldsFw");

            /* Local Print NoMark triggers insertion */
            coRequest.setCoReqSeq(0L);
            coRequest.setCoDetSeq(0L);
            coRequest.setPrintMode(CoConstants.PRINT_MODE_ONLINE);
            coRequest.setPendingTrigSw(CoConstants.PENDING_TRIGGER_NO);
            coRequest.setDraftSwitch(CoConstants.DRAFT_TRIGGER_NO);
            coRequest.setDisId(null);
            coRequest.setCoReqSeqLangCdMap(new HashMap<>());
            errorCode = coDAOServices.insertOriginalRequest(coRequest);

            if (errorCode != -1) {
                xml = generateCorrespondence(generateManualDTO,fields, area, fieldsFw, areasFw, coRequest);
            }

        } catch (final ApplicationException ae) {
            throw new Exception(ae);
        }
        return xml;
    }

    public String initiateLocalPrint(final CoManualFieldsDTO[] fields,
                                     final CoManualFieldsDTO[] area, final COCorrespondence coCorrespondence, CoGenerateManualDTO generateManualDTO) {
        int errorCode = 0;
        String status = null;
        try {
            final COCorrespondence coRequest =coCorrespondence;
            String parentCen = generateManualDTO.getParentPageId();
            if (null != parentCen && parentCen.equalsIgnoreCase("CO611")
                    && docIdCheck(coCorrespondence) || CoConstants.CHAR_I == coCorrespondence.getDocType()) {
                log.debug("Cannot central print 508A Food Stamp/Medicaid/TANF Renewal Form");
            } else {
                /* Central Print triggers insertion starts */
                status = initiateCentralPrint(fields, area, generateManualDTO,coCorrespondence);
            }

            if (status!=null && null != coRequest) {
                /* Local Print triggers insertion*/
                coRequest.setCoReqSeq(0L);
                coRequest.setCoDetSeq(0L);
                coRequest.setActionValue(CoConstants.GENERATECO);
                coRequest.setPrintMode(CoConstants.PRINT_MODE_ONLINE);
                coRequest.setPendingTrigSw(CoConstants.PENDING_TRIGGER_NO);
                coRequest.setDraftSwitch(CoConstants.DRAFT_TRIGGER_NO);
                coRequest.setDisId(null);
                coRequest.setCoReqSeqLangCdMap(new HashMap<>());
                errorCode = coDAOServices.insertOriginalRequest(coRequest);
            }

            if (errorCode != -1) {
                status = generateCorrespondence(generateManualDTO,coRequest);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    private boolean docIdCheck(final COCorrespondence coRequest) {
        return null != coRequest && null!= coRequest.getDocId() && coRequest.getDocId().equalsIgnoreCase("FGG508A");
    }

    public String initiateCentralPrint(final CoManualFieldsDTO[] fields,
                                       final CoManualFieldsDTO[] area, CoGenerateManualDTO generateManualDTO, COCorrespondence coCorrespondence) {

        String status = null;
        final COCorrespondence coRequest = coCorrespondence;
        coRequest.setActionValue(CoConstants.UPDATE_CPC);
        coRequest.setPrintMode(CoConstants.PRINT_MODE_BATCH);
        coRequest.setHistorySwitch(CoConstants.HISTORY_TRIGGER_NO);
        coRequest.setPendingTrigSw(CoConstants.PENDING_TRIGGER_NO);
        final int errorCode = coDAOServices.insertOriginalRequest(coRequest);
        if (errorCode != -1) {
            status = generateCorrespondence(generateManualDTO,coRequest);
        }
        return status;
    }
}
