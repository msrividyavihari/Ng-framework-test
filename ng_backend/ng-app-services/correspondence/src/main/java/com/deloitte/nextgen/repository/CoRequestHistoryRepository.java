package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.CoRequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface CoRequestHistoryRepository extends JpaRepository<CoRequestHistory,Long>{

    String SELECT_SQL = "SELECT CO_REQ_SEQ, DOC_ID, INDV_ID , ACTION_CD , REASON_CD_LIST, DRAFT_SW , LANGUAGE_CD, EMP_ID , GENERATE_DT , MISC_PARMS, HISTORY_SW, PENDING_TRIG_SW, HST_PRINT_STRING, DOC_TYPE_CD, REQUEST_TYPE_CD, PROGRAM_CD, ORIG_PRINT_DT, APPT_ID, OFFICE_NUM, EDG_NUM, BENEFIT_NUM, MANUALLY_GENERATED_SW, MASS_GENERATED_SW, ASSISTANCE_LIST, CASE_NUM, APP_NUM, CHIP_APP_NUM, LOG_ID, EDG_TRACE_ID, CREATE_USER_ID, UPDATE_USER_ID, UPDATE_DT, UNIQUE_TRANS_ID, CREATE_DT, MASS_MAILING_ID, PROVIDER_ID, LOCATION_ID, DIS_ID, SPECIAL_NOTES, GO_GREEN, CO_STATUS_SW, EDBC_RUN_ID,PAGE_NUM, CC_PROVIDER_ID, CC_PROVIDER_CERT_START_DT, CC_PROVIDER_CERT_END_DT from CO_REQUEST_HISTORY";
    String PREVIOUS_REQ2 = " AND PENDING_TRIG_SW ='B' AND CO_STATUS_SW ='P' AND HISTORY_SW ='N' " +
                                        " ORDER BY CREATE_DT DESC";

    List<CoRequestHistory> findByCoReqSeqOrderByCoReqSeq(Long coReqSeq);

    List<CoRequestHistory> findByCoReqSeq(Long coReqSeq);


    @Query(
            value= SELECT_SQL +" WHERE DOC_ID = (:docId) AND CASE_NUM = (:caseNum) AND PENDING_TRIG_SW = 'B' " +
                    "AND HISTORY_SW NOT IN ('Y') AND CO_STATUS_SW = 'P' AND trunc(CREATE_DT) = TO_DATE(:curDt)",
            nativeQuery = true)
    List<CoRequestHistory> previousPendingTriggerIfExists(String docId, Long caseNum,String curDt);

    @Query(
            value= SELECT_SQL+" WHERE CASE_NUM =(:caseNum) " +
                    "AND DOC_ID IN ('NGGA0049B') AND TRUNC (CREATE_DT) =TO_DATE(:currentDate) "+PREVIOUS_REQ2,
            nativeQuery = true)

    List<CoRequestHistory> getPrevReqsInTheSameDay49B(String caseNum, Timestamp currentDate);

    @Transactional
    @Modifying
    @Query(
            value= "UPDATE CO_REQUEST_HISTORY SET LOG_ID= (:logId) WHERE CO_REQ_SEQ IN((:coReqSeqIds))",nativeQuery = true)
    void updateCoRequestHistoryLogIds(String coReqSeqIds, Long logId);


    @Transactional
    @Modifying
    @Query(
            value= "UPDATE CO_REQUEST_HISTORY SET CO_STATUS_SW = (:coStatusSw) , UPDATE_USER_ID = (:supUserId) WHERE CO_REQ_SEQ IN((:coReqSeq))",nativeQuery = true)
    void updateCoStatusSwitch( char coStatusSw, long coReqSeq, String supUserId);
}
