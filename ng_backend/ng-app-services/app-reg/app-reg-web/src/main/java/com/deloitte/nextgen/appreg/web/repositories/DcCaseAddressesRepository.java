package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.web.entities.DcAlias;
import com.deloitte.nextgen.appreg.web.entities.DcCaseAddresses;
import com.deloitte.nextgen.appreg.web.entities.generated.DcCaseAddressesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DcCaseAddressesRepository extends JpaRepository<DcCaseAddresses, DcCaseAddressesPK> {

    DcCaseAddresses findByCaseNumAndAddrTypeCd(Long caseNum,String addrTypeCd);
}
