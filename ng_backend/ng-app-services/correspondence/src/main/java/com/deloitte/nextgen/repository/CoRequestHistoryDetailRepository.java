package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.CoRequestHistoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoRequestHistoryDetailRepository extends JpaRepository<CoRequestHistoryDetail,Long> {
    List<CoRequestHistoryDetail> findByCoReqSeq(long coReqSeq);
}
