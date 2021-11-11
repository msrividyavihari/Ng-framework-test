package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.NoticeRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoticeDashboardRepository extends JpaRepository<NoticeRequestStatus,Long> {
    @Query(value = "SELECT A.* FROM NOTICE_REQUEST_STATUS A\n" +
            "JOIN(\n" +
            "SELECT TEMPLATE_ID,MAX(LOG_REQUEST_ID) AS LOG_REQUEST_ID1, NOTICE_REQUEST_ID FROM NOTICE_REQUEST_STATUS \n" +
            "WHERE AGENCY_ID =(:agency) AND CREATE_DT >= (:fromDate) AND CREATE_DT <= (:toDate)\n" +
            "GROUP BY TEMPLATE_ID,NOTICE_REQUEST_ID) B\n" +
            "ON A.TEMPLATE_ID = B.TEMPLATE_ID AND\n" +
            "A.LOG_REQUEST_ID = B.LOG_REQUEST_ID1 AND\n" +
            "A.NOTICE_REQUEST_ID = B.NOTICE_REQUEST_ID ", nativeQuery = true)
    List<Object[]> fetchTrackingReport(@Param("agency") String agency, String fromDate, String toDate);
}
