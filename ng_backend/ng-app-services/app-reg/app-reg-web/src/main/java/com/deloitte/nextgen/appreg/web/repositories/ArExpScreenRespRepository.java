package com.deloitte.nextgen.appreg.web.repositories;


import com.deloitte.nextgen.appreg.web.entities.ArExpScreenResp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArExpScreenRespRepository extends JpaRepository<ArExpScreenResp,String> {

    List<ArExpScreenResp> findByAppNum(String appNum);
    ArExpScreenResp findByAppNumAndQuestCd(String appNum, String questCd );
}
