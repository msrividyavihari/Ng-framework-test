package com.deloitte.nextgen.dc.repository;

import com.deloitte.nextgen.dc.entities.DcCases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DcCasesRepository extends JpaRepository<DcCases,Long> {
    DcCases findByCaseNum(Long caseNum);
}
