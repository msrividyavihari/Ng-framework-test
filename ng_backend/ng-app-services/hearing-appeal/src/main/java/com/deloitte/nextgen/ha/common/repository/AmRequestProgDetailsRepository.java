package com.deloitte.nextgen.ha.common.repository;

import com.deloitte.nextgen.ha.entity.AmRequestProgDetails;
import com.deloitte.nextgen.ha.entity.AmRequestProgDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AmRequestProgDetailsRepository  extends JpaRepository<AmRequestProgDetails, String>  {


   /* @Query(value="select * from AM_REQUEST_PROG_DETAILS" +
            " WHERE APL_NUM = (:aplNum) ",nativeQuery = true)
    List<AmRequestProgDetails> findByAplNum(@Param("aplNum") Long aplNum);*/

    List<AmRequestProgDetails> findByAplNum(@Param("aplNum") BigInteger aplNum);

}
