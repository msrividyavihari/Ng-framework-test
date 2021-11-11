package com.deloitte.nextgen.appreg.web.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.deloitte.nextgen.appreg.web.entities.ArAppIndv;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArAppIndvRepository extends JpaRepository<ArAppIndv, String> {

     ArAppIndv findByAppNumAndIndvId(String appNum, Long indvId);

}
