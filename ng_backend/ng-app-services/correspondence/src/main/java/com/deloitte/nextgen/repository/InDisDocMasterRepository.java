package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.InDisDocMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InDisDocMasterRepository  extends
        JpaRepository<InDisDocMaster, Integer>,
        InDisDocMasterCustomRepository {

    List<InDisDocMaster> findByCoReqSeq(long coReqNum);

    @Query(value =  "From  com.deloitte.nextgen.entities.InDisDocMaster DMO " +
            "WHERE (delinkInd ='N' OR  delinkInd IS NULL) " +
            "AND EXISTS ( " +
            "Select DMI.docId from com.deloitte.nextgen.entities.InDisDocMaster DMI where DMI.disDocMstrSeqNum = (:inDisDocMasterSeqNum) " +
            "and  (DMI.docId = DMO.docId OR DMI.docuedgeDocumentId = DMO.docuedgeDocumentId)" +
            ") " )
    List<InDisDocMaster> getAllLinkedRecord(Long inDisDocMasterSeqNum);

    @Query(value = "From  com.deloitte.nextgen.entities.InDisDocMaster " +
            "WHERE (delinkInd ='N' OR  delinkInd IS NULL) " +
            "AND disDocMstrSeqNum = :docSequence")
    List<InDisDocMaster> findByDocSequence(Long docSequence);

    List<InDisDocMaster> findByDisDocMstrSeqNum(Long disDocMstrSeqNum);
}
