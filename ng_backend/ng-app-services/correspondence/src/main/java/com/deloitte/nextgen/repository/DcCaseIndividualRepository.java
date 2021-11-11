package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.DcCaseIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DcCaseIndividualRepository extends JpaRepository<DcCaseIndividual, Long> {

    @Query(value = "FROM DcCaseIndividual WHERE CASE_NUM = (:caseNum) AND EFF_END_DT IS NULL")
    List<DcCaseIndividual> findByCaseNum(Long caseNum);

}
