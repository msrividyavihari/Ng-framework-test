package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.VDcCaseIndvDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VDcCaseIndvDetailsRepository extends JpaRepository<VDcCaseIndvDetails,Long> {


    @Query("FROM com.deloitte.nextgen.entities.VDcCaseIndvDetails where " +
            "((EFF_END_DT IS NULL AND ACTIVE_IN_CASE_SW = 'Y' ) AND (CASE_NUM = " +
            " (:caseNum) ))")
    List<VDcCaseIndvDetails> findByCaseNumber(Long caseNum);
}
