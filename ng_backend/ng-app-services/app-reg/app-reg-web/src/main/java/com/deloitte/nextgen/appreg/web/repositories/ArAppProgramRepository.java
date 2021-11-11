package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.web.entities.ArAppProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ArAppProgramRepository extends JpaRepository<ArAppProgram, String> {

    List<ArAppProgram> findByAppNum(String appNum);

}
