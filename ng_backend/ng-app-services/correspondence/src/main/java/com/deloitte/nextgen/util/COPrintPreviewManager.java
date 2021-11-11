package com.deloitte.nextgen.util;

import com.deloitte.nextgen.dto.entities.ViewHistoryDetailDTO;
import com.deloitte.nextgen.entities.COCorrespondence;
import com.deloitte.nextgen.entities.InDisDocMaster;
import com.deloitte.nextgen.entities.VCoRequest;
import com.deloitte.nextgen.repository.InDisDocMasterRepository;
import com.deloitte.nextgen.repository.VCoRequestRepository;
import com.deloitte.nextgen.service.impl.CoDAOServices;
import com.deloitte.nextgen.service.impl.CorrespondenceServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class COPrintPreviewManager {

    @Autowired
    CoDAOServices coDAOServices;

    @Autowired
    VCoRequestRepository vCoRequestRepository;

    @Autowired
    InDisDocMasterRepository inDisDocMasterRepository;

    @Autowired
    CorrespondenceServices correspondenceService;

    public boolean reprintHistory(ViewHistoryDetailDTO viewHistoryDetailDTO) throws Exception {
        final COCorrespondence co = new COCorrespondence();
        Long rptSeq = viewHistoryDetailDTO.getRptSeq();
        final Long reqReqSeq = viewHistoryDetailDTO.getT2CoReqSeq();
        final Long detSeq = viewHistoryDetailDTO.getCoDetSeq();
        Long coRecipientsFromSelection = viewHistoryDetailDTO.getCoRecipientSelected();

        List<VCoRequest> vCoRequestList = vCoRequestRepository.findByReqSeqAndDetSeq(reqReqSeq, detSeq);
        VCoRequest primaryCargo = vCoRequestList.get(0);

        this.mapCargoToCo(primaryCargo, co);

        co.setDraftSwitch(CoConstants.NO_CHAR);
        co.setSecuritySw(CoConstants.NO_CHAR);
        co.setPendingTrigSw(CoConstants.NO_CHAR);
        co.setReprintSw(CoConstants.PRINT_REPRINT);
        co.setHistorySwitch(CoConstants.YES_CHAR);
        co.setDisId(primaryCargo.getDisId());
        co.setPrintMode(CoConstants.PRINT_MODE_BATCH);

        if ((rptSeq != null) && (rptSeq != 0)) {
            // this will happen for pending reprints if Reprint of a Reprint is
            // initiated
            co.setCoRptSeq(Long.parseLong(String.valueOf(rptSeq)));

        } else if (coRecipientsFromSelection != null && coRecipientsFromSelection != 0) {
            // this will happen for pending reprints if reprint of a Original
            // trigger is initiated
            co.setCoRptSeq(coRecipientsFromSelection);
        }
        int rtnCode = coDAOServices.insertOriginalRequest(co);

        boolean value = populateDisDocMaster(co, primaryCargo);

        if (rtnCode != 1) {

            throw new Exception(
                    "Insert of Original/Reprint Request Failed");
        }

        return value;

    }

    private void mapCargoToCo(VCoRequest vVo, COCorrespondence co) {

        if (vVo.getRequestTypeCd() != null && vVo.getRequestTypeCd() == 'P') {
            co.setProviderId(Long.parseLong(CorrespondenceServices
                    .giveCaseAppNum(vVo)));
        }

        if ((vVo.getRequestTypeCd() != null &&  vVo.getProviderId() != null)  && (vVo.getRequestTypeCd() == 'C') && (vVo.getProviderId() != 0)) {
            co.setProviderId(vVo.getProviderId());
        }

        if (vVo.getRequestTypeCd() != null && vVo.getRequestTypeCd() == 'S') {
            co.setChipAppNum(vVo.getChipAppNum());
            if ( vVo.getProviderId() != null && vVo.getProviderId() != 0) {
                co.setProviderId(vVo.getProviderId());
            }
        }
        co.setRunId(vVo.getEdbcRunId());
        co.setCaseAppNumber(CorrespondenceServices.giveCaseAppNum(vVo));
        co.setCaseAppFlag(vVo.getRequestTypeCd());
        co.setCoReqSeq(vVo.getT2CoReqSeq());
        co.setCoDetSeq(vVo.getCoDetSeq());
        co.setDocId(vVo.getT1DocId());
        co.setIndvId(vVo.getIndvId());
        co.setLangCd(vVo.getLanguageCd());
        co.setMiscParameters(vVo.getMiscParms());
        co.setDraftSwitch(vVo.getDraftSw());
        co.setHistorySwitch(vVo.getHistorySw());
        co.setGenerateDate(vVo.getGenerateDt());
        co.setRequestUserId(vVo.getT3CreateUserId());
        co.setPrintMode(vVo.getPrintMode());
        co.setAssistanceProgramCode(vVo.getProgramCd());
        co.setEmpId(vVo.getEmpId());
        co.setActionCode(vVo.getActionCd());
        co.setReasonCdList(vVo.getReasonCdList());
        co.setDocType(vVo.getT2DocTypeCd());
        co.setPendingTrigSw(vVo.getPendingTrigSw());
        co.setEdgeNumber(vVo.getEdgNum());
        co.setBenefitNumber(vVo.getBenefitNum());
        co.setApptId(vVo.getApptId());
        co.setOfficeNumber(vVo.getOfficeNum());
        co.setLogId(vVo.getLogId());
        co.setSpecialNotes(vVo.getSpecialNotes());
        co.setCcProviderId(vVo.getCcProviderId());
        co.setCcCertStartDate(vVo.getCcProviderCertStartDt());
        co.setCcCertEndDate(vVo.getCcProviderCertEndDt());
        co.setManuallyGenerated(vVo.getManuallyGeneratedSw() == 'Y');
        co.setEdgTraceId(vVo.getEdgTraceId());
        if ( co.getEmpId() != null && co.getEmpId() != 0 && co.getEmpId() != -1 ) {
            co.setPrintReturnName(true);
        }
        co.setCoRptSeq(vVo.getCoRptSeq());
        co.setTypeOfAssistanceList(vVo.getAssistanceList());
        co.setMassGeneratedSw(vVo.getMassGeneratedSw());

        if (vVo.getMassMailingId() != null && vVo.getMassMailingId() != 0) {
            co.setMassMailingId(vVo.getMassMailingId());
        }
    }

    /**
     * New co request entry made should make an entry into dis doc master table with old TransactionId, FilePath and DocId
     * @param co co
     * @param primaryCargo primaryCargo
     * @throws Exception exception
     */
    private boolean populateDisDocMaster(COCorrespondence co, VCoRequest primaryCargo) throws Exception {
        InDisDocMaster inDisDocMaster = correspondenceService.populateDocMasterCargo(co);
        List<InDisDocMaster> inDisDocMasterList = getInDisDocMasterCargo(primaryCargo);
        if(inDisDocMasterList.size() > 0 ) {
            InDisDocMaster temp = inDisDocMasterList.get(0);
            inDisDocMaster.setTransactionId(temp.getTransactionId());
            inDisDocMaster.setFilePath(temp.getFilePath());
            inDisDocMaster.setDocId(temp.getDocId());
            inDisDocMaster.setDocuedgeDocumentId(temp.getDocuedgeDocumentId());
            inDisDocMaster.setUniqueTransId(temp.getUniqueTransId());
            inDisDocMaster.setCreateDt(temp.getCreateDt());
            inDisDocMaster.setHistorySeq(temp.getHistorySeq());
            inDisDocMaster.setArchiveDt(Timestamp.valueOf("2999-12-31 22:22:22"));
        }

        inDisDocMasterRepository.save(inDisDocMaster);
        return true;
    }

    /**
     * gets old dis doc master entry
     * @param primaryCargo primaryCargo
     * @return List<InDisDocMaster>
     */
    private List<InDisDocMaster> getInDisDocMasterCargo(
            VCoRequest primaryCargo) {
        return inDisDocMasterRepository.findByCoReqSeq(primaryCargo.getT2CoReqSeq());

    }
}
