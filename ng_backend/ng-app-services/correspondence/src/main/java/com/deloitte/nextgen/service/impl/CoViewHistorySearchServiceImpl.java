package com.deloitte.nextgen.service.impl;


import com.deloitte.nextgen.dto.entities.CoViewHistorySearchDTO;
import com.deloitte.nextgen.dto.vo.ViewPendingVO;
import com.deloitte.nextgen.entities.VCoRequest;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.repository.VCoRequestRepository;
import com.deloitte.nextgen.service.CoViewHistorySearchService;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoViewHistorySearchServiceImpl implements CoViewHistorySearchService {

    @Autowired
    VCoRequestRepository vCoRequestRepository;

    public List<ViewPendingVO> fetchHistoryCO(CoViewHistorySearchDTO coViewHistorySearchDTO) throws FwApplicationException {

        Long caseNum = coViewHistorySearchDTO.getCaseNum();
        String appNum = coViewHistorySearchDTO.getAppNum();
        Long clientId = coViewHistorySearchDTO.getClientId();
        String workerName = coViewHistorySearchDTO.getWorkerName();
        Long workerId = coViewHistorySearchDTO.getWorkerId();
        String reqDt = CoUtil.getDateForWhereClauseANSI(coViewHistorySearchDTO.getReqDt());
        String printDt = CoUtil.getDateForWhereClauseANSI(coViewHistorySearchDTO.getPrintDt());
        String ccProviderCertStartDt = CoUtil.getDateForWhereClauseANSI(coViewHistorySearchDTO.getCcCertStartDt());
        String ccProviderCertEndDt = CoUtil.getDateForWhereClauseANSI(coViewHistorySearchDTO.getCcCertEndDt());
        Long ccProviderId = coViewHistorySearchDTO.getCcProviderId();
        String t1DocId = coViewHistorySearchDTO.getDocId();
        Character printMode = coViewHistorySearchDTO.getPrintMode();
        String programCd = coViewHistorySearchDTO.getProgramCd();
        
        String searchCriteria = coViewHistorySearchDTO.getSearchCriteria();

        List<ViewPendingVO> resultList;
        List<VCoRequest> caseList = new ArrayList<>();

        if(CoConstants.CASE_SER.equals(searchCriteria) && caseNum > 0) {


            caseList = this.fetchByCaseNum(caseNum,
                    reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt,
                    ccProviderId, t1DocId, printMode, programCd);

        } else if(CoConstants.APP_SER.equals(searchCriteria) && appNum != null){
            caseList =this.fetchByAppNum(appNum,
                    reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt,
                    ccProviderId, t1DocId, printMode, programCd);

        } else if(CoConstants.CLIENT_SER.equals(searchCriteria) && clientId > 0){
            caseList =this.fetchByClientId(clientId,
                    reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt,
                    ccProviderId, t1DocId, printMode, programCd);

        } else if(CoConstants.WNAME_SER.equals(searchCriteria) && workerName != null){
            caseList =this.fetchByWorkerName(workerName,
                    reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt,
                    ccProviderId, t1DocId, printMode, programCd);

        } else if(CoConstants.WID_SER.equals(searchCriteria) && workerId != null){
            caseList =this.fetchByWorkerId(workerId,
                    reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt,
                    ccProviderId, t1DocId, printMode, programCd);

        }

        resultList = getViewPendingVO(caseList, searchCriteria);

        return resultList;
    }

    private List<VCoRequest> fetchByCaseNum(Long caseNum, String reqDt, String printDt, String ccProviderCertStartDt, String ccProviderCertEndDt, Long ccProviderId, String t1DocId, Character printMode, String programCd) {
        List<VCoRequest> result = vCoRequestRepository.findByCaseNumAndSort(caseNum, reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd );
        return result;
    }

    public List<VCoRequest> fetchByAppNum(String appNum, String reqDt, String printDt, String ccProviderCertStartDt, String ccProviderCertEndDt, Long ccProviderId, String t1DocId, Character printMode, String programCd) {
        List<VCoRequest> result = vCoRequestRepository.findByAppNumAndSort(appNum, reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd );
        return result;
    }


    public List<VCoRequest> fetchByClientId(Long clientId, String reqDt, String printDt, String ccProviderCertStartDt, String ccProviderCertEndDt, Long ccProviderId, String t1DocId, Character printMode, String programCd) {
        List<VCoRequest> result = vCoRequestRepository.findByClientIdAndSort(clientId, reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd );
        return result;
    }

    public List<VCoRequest> fetchByWorkerName(String workerName, String reqDt, String printDt, String ccProviderCertStartDt, String ccProviderCertEndDt, Long ccProviderId, String t1DocId, Character printMode, String programCd) {
        List<VCoRequest> result = vCoRequestRepository.findByWorkerNameAndSort(workerName, reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd );
        return result;
    }

    public List<VCoRequest> fetchByWorkerId(Long workerId, String reqDt, String printDt, String ccProviderCertStartDt, String ccProviderCertEndDt, Long ccProviderId, String t1DocId, Character printMode, String programCd) {
        List<VCoRequest> result = vCoRequestRepository.findByWorkerIdAndSort(workerId, reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd );
        return result;
    }

    private List<ViewPendingVO> getViewPendingVO(List<VCoRequest> list, String searchCriteria) {
        List<ViewPendingVO> resultList = new ArrayList<>();

        if(list == null) {
            return null;
        }

        for(VCoRequest vo: list) {

            ViewPendingVO vVO = new ViewPendingVO();
            vVO.setIndvId(vo.getIndvId());
            vVO.setDocName(vo.getDocName());
            vVO.setLanguageCd(vo.getLanguageCd());
            vVO.setReqDt(vo.getReqDt());
            vVO.setGenerateDt(vo.getGenerateDt());
            vVO.setPendingTrigSw(vo.getPendingTrigSw());
            vVO.setCoReqSeq(new BigDecimal(String.valueOf(vo.getT2CoReqSeq())).longValueExact());
            vVO.setDocId(vo.getT2DocId());
            if(null != vo.getCcProviderId()) {
                vVO.setCcProviderId(new BigDecimal(String.valueOf( vo.getCcProviderId())).longValueExact());
            } else {
                vVO.setCcProviderId(0L);
            }
            if(null != vo.getCcProviderCertStartDt()){
                vVO.setCcProviderCertStartDt(vo.getCcProviderCertStartDt());
            }
            if(null != vo.getCcProviderCertEndDt()){
                vVO.setCcProviderCertEndDt(vo.getCcProviderCertEndDt());
            }
            vVO.setCoStatusSw(vo.getCoStatusSw());
            vVO.setDraftSw(vo.getDraftSw());
            vVO.setWorkerName(vo.getT2CreateUserId());
            vVO.setPrintMode(vo.getPrintMode());
            vVO.setPrintType(vo.getReprintSw());
            vVO.setPrintDt(vo.getPrintDt());
            vVO.setSearchCriteria(searchCriteria);
            if(vo.getCaseNum() != null) {
                vVO.setCaseAppNumber(vo.getCaseNum().toString());
            } else {
                vVO.setCaseAppNumber(vo.getAppNum());
            }
            vVO.setEmpId(vo.getEmpId());
            vVO.setT3CreateUserId(vo.getT3CreateUserId());
            vVO.setT2CoReqSeq(vo.getT2CoReqSeq());
            vVO.setCoDetSeq(vo.getCoDetSeq());
            vVO.setCoRptSeq(vo.getCoRptSeq());
            vVO.setRePrintSw(vo.getReprintSw());
            vVO.setDisDocMstrSeqNum(vo.getDisDocMstrSeqNum());
            resultList.add(vVO);
        }

        return resultList;
    }

}
