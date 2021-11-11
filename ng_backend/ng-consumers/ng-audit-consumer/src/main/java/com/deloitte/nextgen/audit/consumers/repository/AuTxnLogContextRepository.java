package com.deloitte.nextgen.audit.consumers.repository;

import com.deloitte.nextgen.audit.consumers.entities.AuTxnLogContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuTxnLogContextRepository extends JpaRepository<AuTxnLogContext, String> {

    @Query("SELECT au FROM AuTxnLogContext au WHERE au.correlationId =:correlationId ")
    //@Query(value ="SELECT au FROM au_txn_log_context au where au.corelation_id =:CORELATION_ID " , nativeQuery =true)
    List<AuTxnLogContext> findByCorrealtionId(@Param("correlationId") String correlationId);
}
