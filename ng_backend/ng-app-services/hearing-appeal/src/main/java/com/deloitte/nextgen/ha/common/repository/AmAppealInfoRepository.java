package com.deloitte.nextgen.ha.common.repository;

import com.deloitte.nextgen.ha.entity.AmAppealInfo;
import com.deloitte.nextgen.ha.entity.AmDocketDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AmAppealInfoRepository extends JpaRepository<AmAppealInfo, BigInteger> {


  /*  @Query(value ="select APL_NUM,LAST_STATUS from AM_APPEAL_INFO  WHERE  APL_NUM = (:aplNum)  order by CREATE_DT desc",nativeQuery = true)
   // List<Object[]> findByAppealNum(@Param("aplNum") BigInteger aplNum);
    List<Object[]> findByAppealNum(@Param("aplNum") BigInteger aplNum);

    List<AmAppealInfo> findByAplNum(BigInteger aplNum);

    @Query("select p from AmAppealInfo p where p.aplNum = (:appNum)")
    public AmAppealInfo findByAplNum2(@Param("appNum") BigInteger appNum);*/

    AmAppealInfo findByAplNum(BigInteger AplNum);
    List<AmAppealInfo> findByAplNumIn(List<BigInteger> aplNums);



}
