package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.MoOfficeAddresses;
import com.deloitte.nextgen.entities.composite.MoOfficeAddressesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoOfficeAddressesRepository extends JpaRepository<MoOfficeAddresses, MoOfficeAddressesId> {
    List<MoOfficeAddresses> findByOfficeNum(Long officeNo);
}
