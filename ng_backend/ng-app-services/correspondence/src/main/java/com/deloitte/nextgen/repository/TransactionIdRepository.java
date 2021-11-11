package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.TransactionIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionIdRepository extends JpaRepository<TransactionIdEntity, String> {
    @Query(
            value= "SELECT LPAD(CO_DIS_DOC_UPLOAD_TXN_SQ.NEXTVAL,9,'0') AS coDisDocUploadTxnSeq FROM DUAL",
            nativeQuery = true)
    String findByTransactionId();
}
