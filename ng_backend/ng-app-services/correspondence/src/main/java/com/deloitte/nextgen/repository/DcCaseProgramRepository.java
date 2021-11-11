package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.DcCaseProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DcCaseProgramRepository extends JpaRepository<DcCaseProgram, Long> {
    List<DcCaseProgram> findByCaseNum(Long parseLong);
    List<DcCaseProgram> findByProgCdAndCaseNumOrderByApplicationDt(String progCc, Long CaseNum);
}
