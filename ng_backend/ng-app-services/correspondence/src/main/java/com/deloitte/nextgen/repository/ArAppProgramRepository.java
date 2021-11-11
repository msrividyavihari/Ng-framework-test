package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.ArAppProgram;
import com.deloitte.nextgen.entities.composite.ArAppProgramId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArAppProgramRepository extends JpaRepository<ArAppProgram, ArAppProgramId> {

    @Query(value = "SELECT B.* FROM AR_APP_PROGRAM B  WHERE B.PROGRAM_CD ='CC' AND B.APP_NUM = (:appNum) ", nativeQuery = true)
    List<ArAppProgram> findByAppNumberForCC(String appNum);

}
