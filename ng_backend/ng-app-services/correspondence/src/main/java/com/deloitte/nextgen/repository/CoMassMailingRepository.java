package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.CoMassMailingReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoMassMailingRepository extends JpaRepository<CoMassMailingReq,Long> {

    @Query(
            value="FROM com.deloitte.nextgen.entities.CoMassMailingReq WHERE jobProcessedSw = 'N' ORDER BY schdDt DESC")
    List<CoMassMailingReq> findAll();

    @Query(value = "FROM com.deloitte.nextgen.entities.CoMassMailingReq WHERE " +
            " JOB_PROCESSED_SW = 'N' AND MASS_MAILING_SEQ_NUM = (:seqNum)")
    List<CoMassMailingReq>  findByMassMailingReqSeq(long seqNum);

}
