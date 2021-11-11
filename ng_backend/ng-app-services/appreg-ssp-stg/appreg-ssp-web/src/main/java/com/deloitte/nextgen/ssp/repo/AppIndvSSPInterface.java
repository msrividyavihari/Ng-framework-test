package com.deloitte.nextgen.ssp.repo;

import com.deloitte.nextgen.ssp.entities.entities.T1004_App_Indv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppIndvSSPInterface extends JpaRepository<T1004_App_Indv,String> {

    List<T1004_App_Indv> findByAppNum(String appNum);
}
