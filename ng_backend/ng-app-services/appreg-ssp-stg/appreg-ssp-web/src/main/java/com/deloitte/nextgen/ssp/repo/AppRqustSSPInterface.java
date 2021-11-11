package com.deloitte.nextgen.ssp.repo;

import com.deloitte.nextgen.ssp.entities.entities.T1001_AppRqst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppRqustSSPInterface extends JpaRepository<T1001_AppRqst,String> {

    T1001_AppRqst findByAppNum(String appNum);
}
