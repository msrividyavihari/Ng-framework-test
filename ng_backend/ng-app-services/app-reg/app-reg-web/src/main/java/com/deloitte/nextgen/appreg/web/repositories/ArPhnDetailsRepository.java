package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.web.entities.ArPhnDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArPhnDetailsRepository  extends JpaRepository<ArPhnDetails,Long> {

    List<ArPhnDetails> findByAppNumAndPhoneSrcTyp(String appNum, String phoneSrcTyp);

    ArPhnDetails findByAppNumAndPhoneSrcTypAndPhnTypeCd( String appNum, String phoneSrcTyp,String phnTypeCd);
}
