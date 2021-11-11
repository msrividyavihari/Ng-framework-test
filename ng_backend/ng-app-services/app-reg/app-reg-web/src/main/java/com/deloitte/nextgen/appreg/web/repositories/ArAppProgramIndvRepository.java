package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.web.entities.ArAppProgramIndv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ArAppProgramIndvRepository extends JpaRepository<ArAppProgramIndv, String> {

    List<ArAppProgramIndv> findByAppNum(String appNum);

    List<ArAppProgramIndv> findByAppNumAndIndvId(String appNum, Long indvId);


}
