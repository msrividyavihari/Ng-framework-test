package com.deloitte.nextgen.ssp.repo;

import com.deloitte.nextgen.ssp.entities.entities.T1002_App_Dtl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppDetailRepo extends JpaRepository<T1002_App_Dtl,String> {

}