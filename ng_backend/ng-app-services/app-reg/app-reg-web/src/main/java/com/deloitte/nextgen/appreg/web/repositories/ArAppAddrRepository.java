package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.web.entities.ArAppAddr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArAppAddrRepository extends JpaRepository<ArAppAddr,Long> {

     ArAppAddr findByAppNumAndAddrTypeCd(String appNum, String addrTypeCd);
}

