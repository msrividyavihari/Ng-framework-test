package com.deloitte.nextgen.ha.common.repository;

import com.deloitte.nextgen.ha.entity.AmAddress;
import com.deloitte.nextgen.ha.entity.AmRequestDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface AmAddressRepository extends JpaRepository<AmAddress, BigInteger>{

    @Query("select p from AmAddress p where p.addressId = (:addressId)")
    public List<AmAddress> findByAddressId(@Param("addressId") BigInteger addressId);

}
