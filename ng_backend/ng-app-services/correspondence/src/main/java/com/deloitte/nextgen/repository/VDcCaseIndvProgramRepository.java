package com.deloitte.nextgen.repository;


import com.deloitte.nextgen.entities.VDcCaseIndvProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VDcCaseIndvProgramRepository extends JpaRepository<VDcCaseIndvProgram, Long> {

    String SELECT_SQL = "SELECT * FROM V_DC_CASE_INDV_PROGRAM";

    String whereClause = " WHERE (T1_CASE_NUM = (:caseNum)) AND (trunc(T1_EFF_BEGIN_DT) <= TO_DATE(:curDt))" +
            " AND (trunc(T1_EFF_END_DT) >= TO_DATE(:curDt) OR T1_EFF_END_DT IS NULL) AND (trunc(T2_EFF_BEGIN_DT) <= TO_DATE(:curDt)" +
            " AND (trunc(T2_EFF_END_DT) >= TO_DATE(:curDt)) OR T2_EFF_END_DT IS NULL) AND (ACTIVE_IN_CASE_SW = 'Y') AND  (FILE_CLEARANCE_SW = 'Y')";
    @Query(
            value= SELECT_SQL + whereClause,
            nativeQuery = true)
    List<VDcCaseIndvProgram> findByCaseNum(long caseNum, String curDt);
}
