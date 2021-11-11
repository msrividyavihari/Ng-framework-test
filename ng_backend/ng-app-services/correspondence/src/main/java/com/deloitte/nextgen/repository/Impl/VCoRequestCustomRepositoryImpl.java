package com.deloitte.nextgen.repository.Impl;

import com.deloitte.nextgen.entities.VCoRequest;
import com.deloitte.nextgen.repository.VCoRequestCustomRepository;
import com.deloitte.nextgen.util.QueryUtilCoViewHistorySearch;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class VCoRequestCustomRepositoryImpl implements VCoRequestCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    QueryUtilCoViewHistorySearch queryObj;

    @Override
    public List<VCoRequest> findByCaseNumAndSort(Long caseNum, String reqDt, String printDt, String ccProviderCertStartDt, String ccProviderCertEndDt, Long ccProviderId, String t1DocId, Character printMode, String programCd) {

        String sql = queryObj.getSQLForCaseNum(caseNum, reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd );
        List<Object[]> results = entityManager.createNativeQuery(sql).getResultList();
        return getVCoRequest(results);
    }

    @Override
    public List<VCoRequest> findByAppNumAndSort(String appNum, String reqDt, String printDt, String ccProviderCertStartDt, String ccProviderCertEndDt, Long ccProviderId, String t1DocId, Character printMode, String programCd) {
        String sql = queryObj.getSQLForAppNum(appNum, reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd );
        List<Object[]> results = entityManager.createNativeQuery(sql).getResultList();
        return getVCoRequest(results);
    }

    @Override
    public List<VCoRequest> findByClientIdAndSort(Long clientId, String reqDt, String printDt, String ccProviderCertStartDt, String ccProviderCertEndDt, Long ccProviderId, String t1DocId, Character printMode, String programCd) {
        String sql = queryObj.getSQLForClientId(clientId, reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd );
        List<Object[]> results = entityManager.createNativeQuery(sql).getResultList();
        return getVCoRequest(results);
    }

    @Override
    public List<VCoRequest> findByWorkerNameAndSort(String workerName, String reqDt, String printDt, String ccProviderCertStartDt, String ccProviderCertEndDt, Long ccProviderId, String t1DocId, Character printMode, String programCd) {
        String sql = queryObj.getSQLForWorkerName(workerName, reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd );
        List<Object[]> results = entityManager.createNativeQuery(sql).getResultList();
        return getVCoRequest(results);
    }

    @Override
    public List<VCoRequest> findByWorkerIdAndSort(Long workerId, String reqDt, String printDt, String ccProviderCertStartDt, String ccProviderCertEndDt, Long ccProviderId, String t1DocId, Character printMode, String programCd) {
        String sql = queryObj.getSQLForWorkerId(workerId, reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd );
        List<Object[]> results = entityManager.createNativeQuery(sql).getResultList();
        return getVCoRequest(results);
    }


    public List<VCoRequest> findByReqSeqAndDetSeq(Long t2CoReqSeq, Long coDetSeq) {
        String sql = queryObj.getSQLForReqSeqAndDetSeq(t2CoReqSeq, coDetSeq);
        List<Object[]> results = entityManager.createNativeQuery(sql).getResultList();
        return getVCoRequest(results);
    }

    private List<VCoRequest> getVCoRequest(List<Object[]> array) {
        List<VCoRequest> list = new ArrayList<>();

        for(Object obj: array) {
            Object[] tempArray = (Object[]) obj;
            VCoRequest vco = new VCoRequest();

            vco.setT1DocId((String) tempArray[0]);
            vco.setHistorySeq(new BigInteger(String.valueOf(tempArray[1])).longValue());
            vco.setDocName((String) tempArray[2]);
            vco.setT1CreateUserId((String) tempArray[3]);
            vco.setT1UpdateUserId((String) tempArray[4]);
            vco.setT1CreateDt((Timestamp) tempArray[5]);
            vco.setT1UpdateDt((Timestamp) tempArray[6]);
            if(null != tempArray[7])
               vco.setT1UniqueTransId(new BigInteger(String.valueOf(tempArray[7])).longValue());
            vco.setEffBeginDt((Timestamp) tempArray[8]);
            vco.setEffEndDt((Timestamp) tempArray[9]);
            vco.setHistNavInd((Character) tempArray[10]);
            vco.setGenerateManuallySw((Character) tempArray[11]);
            vco.setT1DocTypeCd((Character) tempArray[12]);
            vco.setPrintModeCd((Character) tempArray[13]);
            vco.setFreeformSw((Character) tempArray[14]);
            vco.setStufferSw((Character) tempArray[15]);
            vco.setMassEnabledSw((Character) tempArray[16]);
            vco.setRequestCd((Character) tempArray[17]);
            vco.setT1ArchiveDt((Timestamp) tempArray[18]);
            if(null != tempArray[19])
                vco.setT2CoReqSeq(new BigInteger(String.valueOf(tempArray[19])).longValue());
            vco.setT2DocId((String) tempArray[20]);
            if(null != tempArray[21])
                vco.setIndvId(new BigInteger(String.valueOf(tempArray[21])).longValue());
            vco.setActionCd((Character) tempArray[22]);
            vco.setReasonCdList((String) tempArray[23]);
            vco.setDraftSw((Character) tempArray[24]);
            vco.setLanguageCd((String) tempArray[25]);
            if(null != tempArray[26])
                vco.setEmpId(new BigInteger(String.valueOf(tempArray[26])).longValue());
            vco.setGenerateDt((Timestamp) tempArray[27]);
            vco.setMiscParms((String) tempArray[28]);
            vco.setHistorySw((Character) tempArray[29]);
            vco.setPendingTrigSw((Character) tempArray[30]);
//            vco.setHstPrintString((String) tempArray[31]);
            vco.setT2DocTypeCd((Character) tempArray[32]);
            vco.setRequestTypeCd((Character) tempArray[33]);
            vco.setProgramCd((String) tempArray[34]);
            vco.setT2ArchiveDt((Timestamp) tempArray[35]);
            vco.setOrigPrintDt((Timestamp) tempArray[36]);
            if(null != tempArray[37])
                vco.setApptId(new BigInteger(String.valueOf(tempArray[37])).longValue());
            if(null != tempArray[38])
                vco.setOfficeNum(new BigInteger(String.valueOf(tempArray[38])).longValue() );
            if(null != tempArray[39])
                vco.setEdgNum(new BigInteger(String.valueOf(tempArray[39])).longValue());
            vco.setBenefitNum((String) tempArray[40]);
            vco.setManuallyGeneratedSw((Character) tempArray[41]);
            vco.setMassGeneratedSw((Character) tempArray[42]);
            vco.setAssistanceList((String) tempArray[43]);
            if(null != tempArray[44])
                vco.setCaseNum(new BigInteger(String.valueOf(tempArray[44])).longValue());
            vco.setAppNum((String) tempArray[45]);
            if(null != tempArray[46])
                vco.setLogId(new BigInteger(String.valueOf(tempArray[46])).longValue());
            if(null != tempArray[47])
                vco.setEdgTraceId(new BigInteger(String.valueOf(tempArray[47])).longValue());
            vco.setT2CreateUserId((String) tempArray[48]);
            vco.setT2UpdateUserId((String) tempArray[49]);
            vco.setT2UpdateDt((Timestamp) tempArray[50]);
            if(null != tempArray[51])
                vco.setT2UniqueTransId(new BigInteger(String.valueOf(tempArray[51])).longValue());
            vco.setT2CreateDt((Timestamp) tempArray[52]);
            if(null != tempArray[53])
                vco.setT3CoReqSeq(new BigInteger(String.valueOf(tempArray[53])).longValue());
            if(null != tempArray[54])
                vco.setCoDetSeq(new BigInteger(String.valueOf(tempArray[54])).longValue());
            vco.setPrintDt((Timestamp) tempArray[55]);
            vco.setPrintMode((Character) tempArray[56]);
            vco.setReprintSw((Character) tempArray[57]);
            vco.setT3CreateUserId((String) tempArray[58]);
            vco.setReqDt((Timestamp) tempArray[59]);
            vco.setT3ArchiveDt((Timestamp) tempArray[60]);
            if(null != tempArray[61])
                vco.setCoRptSeq(new BigInteger(String.valueOf(tempArray[61])).longValue());
            if(null != tempArray[62])
                vco.setT3UniqueTransId(new BigInteger(String.valueOf(tempArray[62])).longValue());
            vco.setT3UpdateDt((Timestamp) tempArray[63]);
            vco.setT3CreateDt((Timestamp) tempArray[64]);
            vco.setT3UpdateUserId((String) tempArray[65]);
            if(null != tempArray[66])
                vco.setMassMailingId(new BigInteger(String.valueOf(tempArray[66])).longValue());
            if(null != tempArray[67])
                vco.setProviderId(new BigInteger(String.valueOf(tempArray[67])).longValue());
            vco.setChipAppNum((String) tempArray[68]);
            vco.setCoStatusSw((Character) tempArray[69]);
            vco.setDisId((String) tempArray[70]);
            vco.setEdbcRunId((String) tempArray[71]);
            vco.setMedIndvId((String) tempArray[72]);
            vco.setSpecialNotes((String) tempArray[73]);
            if(null != tempArray[74])
                vco.setCcProviderId(new BigInteger(String.valueOf(tempArray[74])).longValue());
            vco.setCcProviderCertStartDt((Timestamp) tempArray[75]);
            vco.setCcProviderCertEndDt((Timestamp) tempArray[76]);
            if(tempArray.length >= 78 && null != tempArray[77])
                vco.setDisDocMstrSeqNum(new BigInteger(String.valueOf(tempArray[77])).longValue());

            list.add(vco);
        }
        return list;
    }
}
