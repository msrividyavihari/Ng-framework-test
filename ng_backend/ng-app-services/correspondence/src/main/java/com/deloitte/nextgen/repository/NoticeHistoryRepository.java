package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.NoticeRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NoticeHistoryRepository extends JpaRepository<NoticeRequestStatus,Long>{

    String SELECT_DISTINCT_SQL = "SELECT distinct NOTICE_REQUEST_ID FROM NOTICE_REQUEST_STATUS";

    @Query( value= SELECT_DISTINCT_SQL + " WHERE CASE_NUM=(:caseNum)", nativeQuery = true )
    Long[] findUniqueRequestId(Long caseNum);
    List<NoticeRequestStatus> findByNoticeRequestIdAndCaseNumOrderByLogRequestIdDesc(Long noticeRequestId, Long caseNum);

    @Query(value="from com.deloitte.nextgen.entities.NoticeRequestStatus WHERE noticeRequestId=(:noticeRequestId) " +
            "AND TRUNC (createDt) >= (:startDt) AND TRUNC (createDt) <= (:endDt) " +
            "ORDER BY LOG_REQUEST_ID DESC")
    List<NoticeRequestStatus> findByNoticeRequestId(Long noticeRequestId, LocalDateTime startDt, LocalDateTime endDt);

    List<NoticeRequestStatus> findByLogRequestId(Long logRequestId);

    List<NoticeRequestStatus> findByNoticeRequestIdOrderByLogRequestIdDesc(Long noticeRequestId);

    @Query( value = "FROM com.deloitte.nextgen.entities.NoticeRequestStatus WHERE " +
            "NOTICE_REQUEST_ID = (:noticeRequestId) AND STATUS = 'PP' AND  REQUEST_JSON IS NOT NULL " +
            "")
    List<NoticeRequestStatus> findByNoticeRequestIdAndStatus(long noticeRequestId);

    @Query(value = "From com.deloitte.nextgen.entities.NoticeRequestStatus A WHERE " +
            "A.agencyId = (:agencyId) AND TRUNC (A.createDt) BETWEEN (:startDate) " +
            "AND (:endDate) AND (A.addressUpdated = 'Y' OR A.status IN ('IA', 'AU','PP', 'CF')) " +
            " AND A.logRequestId = (SELECT MAX(B.logRequestId) FROM " +
            "com.deloitte.nextgen.entities.NoticeRequestStatus B " +
            " WHERE A.noticeRequestId = B.noticeRequestId)" +
            "ORDER BY A.logRequestId")
    List<NoticeRequestStatus> findByCaseNumAndRange(String agencyId, LocalDateTime startDate,
                                                    LocalDateTime endDate);

    @Query(value = "From com.deloitte.nextgen.entities.NoticeRequestStatus A WHERE " +
            "A.agencyId = (:agencyId) AND TRUNC (A.createDt) BETWEEN (:startDate) " +
            "AND (:endDate) AND (A.emailDeliveryStatus = 'F' OR A.textNotificationStatus = 'F') " +
            "AND A.logRequestId = (SELECT MAX(B.logRequestId) FROM " +
            "com.deloitte.nextgen.entities.NoticeRequestStatus B " +
            "WHERE A.noticeRequestId = B.noticeRequestId)" +
            "ORDER BY A.logRequestId")
    List<NoticeRequestStatus> findByEmailId(String agencyId, LocalDateTime startDate,
                                            LocalDateTime endDate);

//    List<>

    @Query(value = "SELECT A.* FROM NOTICE_REQUEST_STATUS A\n" +
            "JOIN(\n" +
            "SELECT TEMPLATE_ID,MAX(LOG_REQUEST_ID) AS LOG_REQUEST_ID1, NOTICE_REQUEST_ID FROM NOTICE_REQUEST_STATUS \n" +
            "WHERE AGENCY_ID =(:agency) AND TRUNC(CREATE_DT) >= (:fromDate) AND TRUNC(CREATE_DT) <= (:toDate)\n" +
            "GROUP BY TEMPLATE_ID,NOTICE_REQUEST_ID) B\n" +
            "ON A.TEMPLATE_ID = B.TEMPLATE_ID AND\n" +
            "A.LOG_REQUEST_ID = B.LOG_REQUEST_ID1 AND\n" +
            "A.NOTICE_REQUEST_ID = B.NOTICE_REQUEST_ID ", nativeQuery = true)
    List<NoticeRequestStatus> fetchTrackingReport(@Param("agency") String agency, String fromDate, String toDate);

}

