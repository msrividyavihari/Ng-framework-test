package com.deloitte.nextgen.repository;


import com.deloitte.nextgen.entities.VCoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VCoRequestRepository extends JpaRepository<VCoRequest,Long>, VCoRequestCustomRepository {

    String sel_SQL ="SELECT INDV_ID,DOC_NAME,LANGUAGE_CD,REQ_DT,GENERATE_DT,PENDING_TRIG_SW,T2_CO_REQ_SEQ,T2_DOC_ID" +
            ",CC_PROVIDER_ID,CC_PROVIDER_CERT_START_DT,CC_PROVIDER_CERT_END_DT,CO_STATUS_SW,DRAFT_SW,T2_CREATE_USER_ID" +
            ",CO_DET_SEQ, PRINT_MODE,REPRINT_SW FROM V_CO_REQUEST";
    String pend_where_SQL =" AND T1_DOC_ID <> 'FGGA0014' AND HISTORY_SW = 'N' AND PENDING_TRIG_SW in ('B','N') " +
            "AND (EFF_BEGIN_DT <= REQ_DT AND (EFF_END_DT >= REQ_DT OR EFF_END_DT IS NULL)) ";
    @Query(
            value= sel_SQL +" WHERE CASE_NUM=(:caseNum) "+pend_where_SQL +
                    "AND trunc(GENERATE_DT)<=TO_DATE (:curDt) ORDER BY GENERATE_DT DESC",
            nativeQuery = true)
    List<Object[]> findByCaseNumAndSort(Long caseNum, String curDt);

    @Query(
            value= sel_SQL +" WHERE APP_NUM=(:appNum) "+ pend_where_SQL +
                    "AND trunc(GENERATE_DT)<= TO_DATE (:curDt) ORDER BY GENERATE_DT DESC",
            nativeQuery = true)
    List<Object[]> findByAppNumAndSort(String appNum, String curDt);

    @Query(
            value= sel_SQL +" WHERE INDV_ID=(:indvId) "+ pend_where_SQL +
                    "AND trunc(GENERATE_DT)<= TO_DATE (:curDt) ORDER BY GENERATE_DT DESC",
            nativeQuery = true)
    List<Object[]> findByClientIdAndSort(Long indvId, String curDt);

    @Query(
            value= sel_SQL +" WHERE EMP_ID=(:empId) "+ pend_where_SQL +
                    "AND trunc(GENERATE_DT)<= TO_DATE (:curDt) ORDER BY GENERATE_DT DESC",
            nativeQuery = true)
    List<Object[]> findByWorkerIdAndSort(Long empId, String curDt);

    @Query(
            value= sel_SQL +" WHERE T2_CREATE_USER_ID=(:workerName) "+ pend_where_SQL +
                    "AND trunc(GENERATE_DT)<= TO_DATE (:curDt) ORDER BY GENERATE_DT DESC",
            nativeQuery = true)
    List<Object[]> findByWorkerNameAndSort(String workerName, String curDt);

}
