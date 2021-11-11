package com.deloitte.nextgen.ssp.repo;

import com.deloitte.nextgen.ssp.entities.entities.T1065_App_Auth_Rep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepInterface extends JpaRepository<T1065_App_Auth_Rep,String> {
}
