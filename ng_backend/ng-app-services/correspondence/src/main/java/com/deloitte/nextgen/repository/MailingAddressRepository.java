package com.deloitte.nextgen.repository;


import com.deloitte.nextgen.entities.MailingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingAddressRepository extends JpaRepository<MailingAddress,Long> {

//    @Transactional
//    @Modifying
//    @Query( value = "UPDATE MAILING_ADDRESS SET " +
//            " ADDRESS_UPDATED = 'Y' " +
//            " WHERE LOG_REQUEST_ID = (:logRequestId)", nativeQuery = true)
//    void updateByNoticeRequestId(Long logRequestId);


    MailingAddress findByMailingAddrId(Long mailingAddrId);
}
