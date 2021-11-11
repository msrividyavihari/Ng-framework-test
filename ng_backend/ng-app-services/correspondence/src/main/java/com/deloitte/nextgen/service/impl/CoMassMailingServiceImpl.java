package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.CoMassMailingSummaryDTO;
import com.deloitte.nextgen.dto.vo.MassMailingSummaryVO;
import com.deloitte.nextgen.entities.COCorrespondence;
import com.deloitte.nextgen.entities.CoMassMailingReq;
import com.deloitte.nextgen.repository.CoMassMailingRepository;
import com.deloitte.nextgen.service.CoMassMailingService;
import com.deloitte.nextgen.service.ICoOnline;
import com.deloitte.nextgen.util.CoConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoMassMailingServiceImpl implements CoMassMailingService {

    @Autowired
    CoMassMailingRepository coMassMailingRepo;

    @Autowired
    CoDAOServices coDAOServices;

    @Autowired
    ICoOnline iCorrespondence;

    @Override
    public List<MassMailingSummaryVO> fetchSummaryCO() throws Exception {
        List<CoMassMailingReq> summaryList = coMassMailingRepo.findAll();
        List<MassMailingSummaryVO> massSummaryList = new ArrayList<>();
        for(CoMassMailingReq summary : summaryList){
            MassMailingSummaryVO massMailVO = new MassMailingSummaryVO();
            massMailVO.setMassMailingSeqNum(summary.getMassMailingSeqNum());
            massMailVO.setNoticeTitle(summary.getNoticeTitle());
            massMailVO.setNoticeText(summary.getNoticeTxt());
            massMailVO.setPolicyManualRef(summary.getLegalCites());
            String[] programLst = checkboxProgramLst(summary.getProgramLst());
            massMailVO.setProgramLst(programLst);
            massMailVO.setProgramStr(buildStr(summary.getProgramLst()).toString());
            massMailVO.setScheduledDt(summary.getSchdDt());
            massMailVO.setRecptPop(summary.getLogoCd());
            massMailVO.setCountyCd(summary.getCountyCd().split(","));
            massMailVO.setCreateUserId(summary.getCreateUserId());
            massSummaryList.add(massMailVO);
        }
        return massSummaryList;
    }

    public StringBuilder buildStr(String programLst){
        StringBuilder program = new StringBuilder();
        if(programLst.contains("ABD,GMWD")){
            program.append("Adult Medicaid");
            program.append(',');
        }
        if(programLst.contains("FM,MAGI,RMA")){
            program.append("Family Medicaid");
            program.append(',');
        }
        if(programLst.contains("SNAP Works")){
            program.append("SNAP Works");
            program.append(',');
        }
        if(programLst.contains("FS")){
            program.append("Food Stamps");
            program.append(',');
        }
        if(programLst.contains("Abawd")){
            program.append("ABAWD TIME LIMIT WAIVER EXPIRATION NOTICE");
            program.append(',');
        }
        if(programLst.contains("TANF,RCA")){
            program.append("TANF");
            program.append(',');
        }
        if(programLst.contains("TANF ES")){
            program.append("TANF Employment Services");
            program.append(',');
        }
        if(programLst.contains("CC")) {
            program.append("Child Care");
            program.append(',');
        }
        if(programLst.contains("PC")) {
            program.append("Peachcare for Kids");
            program.append(',');
        }
        final int index = program.length() - 1;
        if ( (program.length() > 0) && (program.charAt(index)) == ',') {
            program.deleteCharAt(index);
        }
        return program;
    }

    public String[] checkboxProgramLst(String programLst){
        String[] program = new String[programLst.length()];
        int i=0;
            if(programLst.contains("ABD,GMWD")){
                program[i] = "ABD,GMWD";i++;
            }
            if(programLst.contains("FM,MAGI,RMA")){
                program[i] = "FM,MAGI,RMA";i++;
            }
            if(programLst.contains("SNAP Works")){
                program[i] = "SNAP Works";i++;
            }
            if(programLst.contains("FS")){
                program[i] = "FS";i++;
            }
            if(programLst.contains("Abawd")){
                program[i] = "Abawd";i++;
            }
            if(programLst.contains("TANF,RCA")){
                program[i] = "TANF,RCA";i++;
            }
            if(programLst.contains("TANF ES")){
                program[i] = "TANF ES";i++;
            }
            String[] programs = new String[i];
            for(int j=0;j<i;j++){
                programs[j] = program[j];
            }
        return programs;
    }

    @Override
    public MassMailingSummaryVO deleteMassMailing(CoMassMailingSummaryDTO coMassMailingSummaryDTO) throws Exception {
        CoMassMailingReq coMassMailReq = new CoMassMailingReq();
        String result = "No Updates made";
        ArrayList<String> messages = new ArrayList<>();
        coMassMailReq.setMassMailingSeqNum(coMassMailingSummaryDTO.getMassMailingSeqNum());
        int commitFlag = coDAOServices.deleteMassMailing(coMassMailReq);
        if (commitFlag == 1 ) {
            result = "Delete operation Completed Successfully";
        }
        messages.add(result);
        MassMailingSummaryVO vo = new MassMailingSummaryVO();

        vo.setMessageCodes(messages);
        return vo;
    }

    @Override
    public MassMailingSummaryVO processCOMRO(CoMassMailingSummaryDTO coMassMailingSummaryDTO) throws Exception {

        String result = "No updates made";
        ArrayList<String> messages = new ArrayList<>();
        int commitFlag = 0;
        if(coMassMailingSummaryDTO.getMassMailingSeqNum() != null && coMassMailingSummaryDTO.getMassMailingSeqNum() != 0) {
            commitFlag = updateMassMailing(coMassMailingSummaryDTO);
        } else {
            commitFlag = insertMassMailing(coMassMailingSummaryDTO);
        }

        if (commitFlag <= 0) {
            log.debug(result);
            System.out.println(result);
        } else {
            result = "Insert/Edit operation Completed Successfully";
        }
        messages.add(result);
        MassMailingSummaryVO vo = new MassMailingSummaryVO();

        vo.setMessageCodes(messages);
        return vo;
    }

    private int insertMassMailing(CoMassMailingSummaryDTO dto) throws Exception {
        log.debug("insertMassMailing COBegin");
        String schdDt = dto.getScheduledDt();
        String dateString = String.format(schdDt);
        Date date = null;
        Timestamp scheduleDt = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (final ParseException e) {
            log.debug("Date Format Parse Exception ---" + e.getMessage());
        }
        if (date != null) {
            scheduleDt = new Timestamp(date.getTime());
        }
        final String logoCode = dto.getRecptPop();
        if(dto.getProgramLst()==null || dto.getProgramLst().length==0){
            String[] program = {dto.getRecptPop()};
            dto.setProgramLst(program);
        }
        StringBuilder programList = buildProgramList(dto.getProgramLst(), dto.getRecptPop());
        StringBuilder stdTxtString = buildString(dto.getStandardTextCode());
        final String legalCites = dto.getCoPolicyManualReference();
        final String noticeTitle = dto.getNoticeTitle();
        final String ntcTxt = dto.getNoticeTxt();
        final StringBuilder countyString = buildString(dto.getCountySelect());

        CoMassMailingReq cargo = new CoMassMailingReq();

        cargo.setNoticeTitle(noticeTitle);
        cargo.setNoticeTxt(ntcTxt);
        cargo.setLegalCites(legalCites);
        cargo.setStdTextLst(stdTxtString.toString());
        cargo.setProgramLst(programList.toString());
        cargo.setJobProcessedSw('N');
        cargo.setSchdDt(scheduleDt);
        cargo.setAuthor(dto.getUserName());
        cargo.setCountyCd(countyString.toString());
        cargo.setLogoCd(logoCode);
        final int commitFlag = coDAOServices.insertMassMailing(cargo);
        log.debug("insertMassMailing COEnd");
        return commitFlag;
    }

    private int updateMassMailing(CoMassMailingSummaryDTO dto) throws Exception {
        log.debug("updateMassMailing COBegin");
        List<CoMassMailingReq> coMassMailingReqList = coMassMailingRepo.findByMassMailingReqSeq(dto.getMassMailingSeqNum());
        CoMassMailingReq cargo = coMassMailingReqList.get(0);
        String schdDt = dto.getScheduledDt();
        String dateString = String.format(schdDt);
        Date date = null;
        Timestamp scheduleDt = null;
        try {
            if (dateString.matches("\\d{4}-\\d{2}-\\d{2}")) {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            }
        } catch (final ParseException e) {
            log.debug("Date Format Parse Exception ---" + e.getMessage());
        }
        if (date != null) {
            scheduleDt = new Timestamp(date.getTime());
        }else{
            scheduleDt = new Timestamp(cargo.getSchdDt().getTime());
        }
        String logoCode = dto.getRecptPop();
        if(dto.getProgramLst()==null || dto.getProgramLst().length==0){
            String[] program = {dto.getRecptPop()};
            dto.setProgramLst(program);
        }
        StringBuilder programList = buildProgramList(dto.getProgramLst(), dto.getRecptPop());
        final StringBuilder stdTxtString = buildString(dto.getStandardTextCode());
        final StringBuilder countyString = buildString(dto.getCountySelect());
        final String legalCites = dto.getCoPolicyManualReference();
        final String noticeTitle = dto.getNoticeTitle();
        final String ntcTxt = dto.getNoticeTxt();

        cargo.setNoticeTitle(noticeTitle);
        cargo.setNoticeTxt(ntcTxt);
        cargo.setLegalCites(legalCites);
        cargo.setStdTextLst(stdTxtString.toString());
        cargo.setProgramLst(programList.toString());
        cargo.setSchdDt(scheduleDt);
        cargo.setAuthor(dto.getUserName());
        cargo.setCountyCd(countyString.toString());
        cargo.setLogoCd(logoCode);
        final int commitFlag = coDAOServices.insertMassMailing(cargo);
        log.debug("updateMassMailing COEnd");
        return commitFlag;
    }

    /**
     * This method convert request list of Strings to StringBuilder
    */
    public StringBuilder buildString(String[] list) {
        StringBuilder result = new StringBuilder();
        if(list != null && list.length > 0) {
            for(int i=0; i< list.length; i++) {
                result.append(list[i]);
                if(i < list.length - 1) {
                    result.append(",");
                }
            }
        }
        return result;
    }

    private StringBuilder buildProgramList(String[] programLst, String recptPop) {
        StringBuilder programList = new StringBuilder();
        for(int i=0;i<programLst.length;i++){
            if (CoConstants.FAMILY_MEDICAID.equalsIgnoreCase(programLst[i])) {
                programList.append(CoConstants.FAMILY_MEDICAID);
                programList.append(",");
            }
            if (CoConstants.ADULT_MEDICAID.equalsIgnoreCase(programLst[i])) {
                programList.append(CoConstants.ADULT_MEDICAID);
                programList.append(",");
            }
            if (CoConstants.SNAP_WORKS.equalsIgnoreCase(programLst[i])) {
                programList.append(CoConstants.SNAP_WORKS);
                programList.append(",");
            }
            if (CoConstants.FOOD_STAMPS.equalsIgnoreCase(programLst[i])) {
                programList.append(CoConstants.FOOD_STAMPS);
                programList.append(",");
            }
            if (CoConstants.CO_TANF.equalsIgnoreCase(programLst[i])) {
                programList.append(CoConstants.CO_TANF);
                programList.append(",");
            }
            if (CoConstants.TANF_EMPLOYMENT_SERVICES.equalsIgnoreCase(programLst[i])) {
                programList.append(CoConstants.TANF_EMPLOYMENT_SERVICES);
                programList.append(",");
            }
            if (CoConstants.ABAWD.equalsIgnoreCase(programLst[i])) {
                programList.append(CoConstants.ABAWD);
                programList.append(",");
            }
            if ("PC".equalsIgnoreCase(recptPop)) {
                programList.append(recptPop);
                programList.append(",");
            }
            if ("CC".equalsIgnoreCase(recptPop)) {
                programList.append(recptPop);
                programList.append(",");
            }
        }
        final int index = programList.length() - 1;
        if ( (programList.length() > 0) && (programList.charAt(index)) == ',') {
            programList.deleteCharAt(index);
        }
        return programList;
    }

    public COCorrespondence getCoCorrespondence(CoMassMailingSummaryDTO coMassMailingSummaryDTO) {
        Map<String,String> dataMap = getFormA0014Data(coMassMailingSummaryDTO);
        final String docId = CoConstants.FGGA0014;
        final COCorrespondence coCorrespondence = new COCorrespondence();
        coCorrespondence.setDocId(docId);
        coCorrespondence.setCaseAppNumber("122256186");
        coCorrespondence.setPrintMode(CoConstants.PRINT_MODE_ONLINE);
        coCorrespondence.setPreviewMode(true);
        coCorrespondence.setActionValue(CoConstants.MASS + CoConstants.GENERATECO);
        coCorrespondence.setFormData(dataMap);
        return coCorrespondence;
    }

    private String generateCorrespondence(COCorrespondence coRequest) throws Exception {
        int errorCode = 0;
        String xmlStr = null;
        if (coRequest.isPreviewMode()) {
            xmlStr = iCorrespondence.printXMLPreviewCo(coRequest);
        } else {
            coRequest.setCoReqSeq(0L);
            coRequest.setCoDetSeq(0L);
            errorCode = iCorrespondence.initiateCorrespondence(coRequest);
        }
        return xmlStr;
    }

    public static Map<String, String> getFormA0014Data(CoMassMailingSummaryDTO request) {
        final Map<String, String> formData = new HashMap<>();
        String adultMedicaid="",familyMedicaid="",sNAPWorks = "",foodStamps="",abawd="",tANF="",tANFEmploymentServices="";
        final String schDt = request.getScheduledDt();
        final String[] programLst = request.getProgramLst();
        for(int i=0;i<programLst.length;i++){
            if(programLst[i].equalsIgnoreCase("ABD,GMWD")){
                adultMedicaid = programLst[i];
            }
            if(programLst[i].equalsIgnoreCase("FM,MAGI,RMA")){
                familyMedicaid = programLst[i];
            }
            if(programLst[i].equalsIgnoreCase("SNAP Works")){
                sNAPWorks = programLst[i];
            }
            if(programLst[i].equalsIgnoreCase("FS")){
                foodStamps = programLst[i];
            }
            if(programLst[i].equalsIgnoreCase("Abawd")){
                abawd = programLst[i];
            }
            if(programLst[i].equalsIgnoreCase("TANF,RCA")){
                tANF = programLst[i];
            }
            if(programLst[i].equalsIgnoreCase("TANF ES")){
                tANFEmploymentServices = programLst[i];
            }
        }

        final StringBuilder stdTxtString = new StringBuilder();
        if (request.getStdTextLst() instanceof String) {
            stdTxtString.append(request.getStdTextLst());
            stdTxtString.append(",");
        } else {
            final Object stdTxt = request.getStandardTextCode();
            final String[] stdTxtParams = (String[]) stdTxt;
            if (stdTxtParams != null) {
                for (int index = 0; index < stdTxtParams.length; index++) {
                    stdTxtString.append(stdTxtParams[index]);
                    if (index < (stdTxtParams.length - 1)) {
                        stdTxtString.append(",");
                    }
                }
            }
        }

        formData.put("standardTextCollec", stdTxtString.toString());

        final String recipientPopulation = request.getRecptPop();

        if ((recipientPopulation != null)
                && "DCH".equalsIgnoreCase(recipientPopulation)) {
            if ((adultMedicaid != null)
                    && adultMedicaid
                    .equalsIgnoreCase(CoConstants.ADULT_MEDICAID)) {
                formData.put("adultMedicaid", CoConstants.VALUE_ONE);
            }
            if ((familyMedicaid != null)
                    && familyMedicaid
                    .equalsIgnoreCase(CoConstants.FAMILY_MEDICAID)) {
                formData.put("familyMedicaid", CoConstants.VALUE_ONE);
            }
        } else if ((recipientPopulation != null)
                && "RSM".equalsIgnoreCase(recipientPopulation)) {
            if ((adultMedicaid != null)
                    && adultMedicaid
                    .equalsIgnoreCase(CoConstants.ADULT_MEDICAID)) {
                formData.put("adultMedicaid", CoConstants.VALUE_ONE);
            }
            if ((familyMedicaid != null)
                    && familyMedicaid
                    .equalsIgnoreCase(CoConstants.FAMILY_MEDICAID)) {
                formData.put("familyMedicaid", CoConstants.VALUE_ONE);
            }
        } else if ((recipientPopulation != null)
                && "DHS".equalsIgnoreCase(recipientPopulation)) {
            if ((sNAPWorks != null)
                    && sNAPWorks.equalsIgnoreCase(CoConstants.SNAP_WORKS)) {
                formData.put("sNAPWorks", CoConstants.VALUE_ONE);
            }
            if ((foodStamps != null)
                    && foodStamps.equalsIgnoreCase(CoConstants.FOOD_STAMPS)) {
                formData.put("foodStamps", CoConstants.VALUE_ONE);
            }
            if ((tANF != null) && tANF.equalsIgnoreCase(CoConstants.CO_TANF)) {
                formData.put("tANF", CoConstants.VALUE_ONE);
            }
            if ((abawd != null) && abawd.equalsIgnoreCase(CoConstants.ABAWD)) {
                formData.put("abawd", CoConstants.VALUE_ONE);
            }
            if ((tANFEmploymentServices != null)
                    && tANFEmploymentServices
                    .equalsIgnoreCase(CoConstants.TANF_EMPLOYMENT_SERVICES)) {
                formData.put("tANFEmploymentServices", CoConstants.VALUE_ONE);
            }
            if ((adultMedicaid != null)
                    && adultMedicaid
                    .equalsIgnoreCase(CoConstants.ADULT_MEDICAID)) {
                formData.put("adultMedicaid", CoConstants.VALUE_ONE);
            }
            if ((familyMedicaid != null)
                    && familyMedicaid
                    .equalsIgnoreCase(CoConstants.FAMILY_MEDICAID)) {
                formData.put("familyMedicaid", CoConstants.VALUE_ONE);
            }
        } else if ((recipientPopulation != null)
                && recipientPopulation.equalsIgnoreCase(CoConstants.PEACH_CARE)) {
            formData.put("peachCare", CoConstants.VALUE_ONE);
        } else if ((recipientPopulation != null)
                && recipientPopulation
                .equalsIgnoreCase(CoConstants.CO_CHILD_CARE)) {
            formData.put("childCare", CoConstants.VALUE_ONE);
        }

        formData.put("recipientPopulation", recipientPopulation);
        formData.put("scheduledDate", schDt);
        formData.put("noticeTitle", request.getNoticeTitle());
        formData.put("noticeText", request.getNoticeTxt());
        formData.put("policyManualReference",request.getCoPolicyManualReference());

        return formData;
    }

    @Override
    public String preview(CoMassMailingSummaryDTO dto) throws Exception {
         String xml = null;
         final COCorrespondence coCorrespondence = getCoCorrespondence(dto);
         xml=generateCorrespondence(coCorrespondence);

         return xml;
    }

}
