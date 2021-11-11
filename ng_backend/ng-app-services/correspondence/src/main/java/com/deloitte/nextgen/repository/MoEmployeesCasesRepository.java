package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.MoEmployeeCases;
import com.deloitte.nextgen.entities.composite.MoEmployeeCasesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoEmployeesCasesRepository extends JpaRepository<MoEmployeeCases, MoEmployeeCasesId> {

    @Query(value=  "FROM com.deloitte.nextgen.entities.MoEmployeeCases "+
                    "WHERE CASE_NUM =(:caseNum) AND ASSIGN_END_DT IS NULL")
    List<MoEmployeeCases> findByCaseNumberWithRowNum(Long caseNum);
}
