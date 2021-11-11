package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.DcCases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DcCasesRepository extends JpaRepository<DcCases,Long> {
    List<DcCases> findByCaseNum(Long caseNum);
}
