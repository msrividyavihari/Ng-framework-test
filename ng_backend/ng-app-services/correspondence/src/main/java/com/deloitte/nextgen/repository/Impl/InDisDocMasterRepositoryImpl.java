package com.deloitte.nextgen.repository.Impl;

import com.deloitte.nextgen.dto.entities.DocumentManagementSearchHistoryDTO;
import com.deloitte.nextgen.entities.InDisDocMaster;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.repository.InDisDocMasterCustomRepository;
import com.deloitte.nextgen.util.QueryUtilForDocumentManagement;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class InDisDocMasterRepositoryImpl implements InDisDocMasterCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    QueryUtilForDocumentManagement queryUtilForDocumentManagement;

    @SuppressWarnings("unchecked")
    @Override
    public List<InDisDocMaster> findByDisQuickSearch(DocumentManagementSearchHistoryDTO documentManagementSearchHistoryDTO, boolean isShinesCcCase, boolean isIndvSearch, String indvListStr, boolean isCcCaseApp) {
        String SQL = queryUtilForDocumentManagement.getSQLForSearchByCase(documentManagementSearchHistoryDTO, isShinesCcCase, isIndvSearch, indvListStr, isCcCaseApp);
        List<Object[]> results = entityManager.createNativeQuery(SQL).getResultList();
        return getInDisDocMaster(results);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<InDisDocMaster> findByIndividualDetails(DocumentManagementSearchHistoryDTO documentManagementSearchHistoryDTO) throws FwApplicationException {
        String SQL = queryUtilForDocumentManagement.getSQLforSearchByOther(documentManagementSearchHistoryDTO);
        List<Object[]> results = entityManager.createNativeQuery(SQL).getResultList();
        return getInDisDocMaster(results);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<InDisDocMaster> findDuplicate(InDisDocMaster inDisDocMasterCargo) {
        String SQL = queryUtilForDocumentManagement.getSQLForFindDuplicate(inDisDocMasterCargo);
        List<Object[]> results = entityManager.createNativeQuery(SQL).getResultList();
        return getInDisDocMaster(results);
    }

    private List<InDisDocMaster> getInDisDocMaster(List<Object[]> array) {
        List<InDisDocMaster> resultList = new ArrayList<>();
        for(Object obj: array) {
            Object[] tempArray = (Object[]) obj;
            InDisDocMaster disDocMaster = new InDisDocMaster();

            disDocMaster.setDisDocMstrSeqNum(new BigDecimal(String.valueOf(tempArray[0])).longValueExact());
            disDocMaster.setTransactionId((String) tempArray[1]);
            if(null != tempArray[2])
                disDocMaster.setDocId(new BigDecimal(String.valueOf(tempArray[2])).longValueExact());
            disDocMaster.setDocType((String) tempArray[3]);
            disDocMaster.setDocUploadType((Character) tempArray[4]);
            disDocMaster.setEntryDt((Timestamp) tempArray[5]);
            disDocMaster.setProcessFlag((Character) tempArray[6]);
            if(null != tempArray[7])
                disDocMaster.setCaseNum(new BigDecimal(String.valueOf(tempArray[7])).longValueExact());
            if(null != tempArray[8])
                disDocMaster.setIndvId(new BigDecimal(String.valueOf(tempArray[8])).longValueExact());
            if(null != tempArray[9])
                disDocMaster.setAppNum((String) tempArray[9]);
            if(null != tempArray[10])
                disDocMaster.setIndvSeqNum(new BigDecimal(String.valueOf(tempArray[10])).longValueExact());
            if(null != tempArray[11])
                disDocMaster.setTaskNum(new BigDecimal(String.valueOf(tempArray[11])).longValueExact());
            if(null != tempArray[12])
                disDocMaster.setCpHistoryFlag((Character) tempArray[12]);
            if(null != tempArray[13])
                disDocMaster.setDelinkInd((Character) tempArray[13]);
            if(null != tempArray[14])
                disDocMaster.setDisUpdInd((Character) tempArray[14]);
            disDocMaster.setSource((String) tempArray[15]);
            disDocMaster.setUniqueTransId(new BigDecimal(String.valueOf(tempArray[16])).longValueExact());
            disDocMaster.setCreateUserId((String) tempArray[17]);
//            disDocMaster.setCreateDt((Timestamp) tempArray[18]);
            if(null != tempArray[19])
//                disDocMaster.setUpdateUserId((String) tempArray[19]);
//            if(null != tempArray[20])
//                disDocMaster.setUpdateDt((Timestamp) tempArray[20]);
            disDocMaster.setHistorySeq(new BigDecimal(String.valueOf(tempArray[21])).longValueExact());
            if(null != tempArray[22])
                disDocMaster.setArchiveDt((Timestamp) tempArray[22]);
            if(null != tempArray[23])
                disDocMaster.setProgram((String) tempArray[23]);
            if(null != tempArray[24])
                disDocMaster.setCoReqSeq(new BigDecimal(String.valueOf(tempArray[24])).longValueExact());
            if(null != tempArray[25])
                disDocMaster.setFilePath((String) tempArray[25]);
            if(null != tempArray[26])
                disDocMaster.setDateOfReceipt((Timestamp) tempArray[26]);
            if(null != tempArray[27])
                disDocMaster.setCommentCd((String) tempArray[27]);
            if(null != tempArray[28])
                disDocMaster.setDocuedgeDocumentId((String) tempArray[28]);

            resultList.add(disDocMaster);
        }

        return resultList;
    }
}
