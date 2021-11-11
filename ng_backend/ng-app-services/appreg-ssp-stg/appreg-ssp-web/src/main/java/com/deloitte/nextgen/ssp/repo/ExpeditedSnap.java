package com.deloitte.nextgen.ssp.repo;

import com.deloitte.nextgen.ssp.entities.entities.T1003_APP_PRIR_SRV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpeditedSnap extends JpaRepository<T1003_APP_PRIR_SRV,String> {

    T1003_APP_PRIR_SRV findByAppNum(String appNum);
}
