package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.VNoticeRequestAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VNoticeRequestAddressRepository  extends JpaRepository<VNoticeRequestAddress, Long> {

    @Query(value = "From com.deloitte.nextgen.entities.VNoticeRequestAddress A " +
            "WHERE A.noticeRequestId IN (:noticeRequestIds) AND A.logRequestId = " +
            "(SELECT MAX(B.logRequestId) FROM com.deloitte.nextgen.entities.VNoticeRequestAddress B " +
            " WHERE A.noticeRequestId = B.noticeRequestId) " +
            " ORDER BY A.logRequestId")
    List<VNoticeRequestAddress> findByNoticeRequestId(List<Long> noticeRequestIds);
}
