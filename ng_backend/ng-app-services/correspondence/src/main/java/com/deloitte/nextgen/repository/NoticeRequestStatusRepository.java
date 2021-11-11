package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.dto.entities.NoticeStatusRequest;
import com.deloitte.nextgen.entities.NoticeRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

@Repository
public interface NoticeRequestStatusRepository {
    List<Object[]> findByLogRequestId(NoticeStatusRequest dto) throws ParseException;
}
