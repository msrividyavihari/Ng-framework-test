package com.deloitte.nextgen.ha.common.repository;

import com.deloitte.nextgen.ha.entity.AmAplStatLog;
import com.deloitte.nextgen.ha.entity.AmAplStatLogId;
import com.deloitte.nextgen.ha.entity.AmAppealInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
@Repository
public interface AmAplStatLogRepository extends JpaRepository<AmAplStatLog, String> {


    @Query(value="select * from AM_APL_STAT_LOG" +
            " WHERE APL_NUM = (:aplNum) AND (DELETE_IND IS NULL OR DELETE_IND = 'N') order by STATUS_CREATE_DT desc",nativeQuery = true)
    List<AmAplStatLog> findByAplNumActive(@Param("aplNum") Long aplNum);



}
