package com.deloitte.nextgen.ha.repository;

import com.deloitte.nextgen.ha.entity.AmAppealInfo;
import com.deloitte.nextgen.ha.entity.HaAppealInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface HaAppealInfoRepository extends JpaRepository<HaAppealInfo, BigInteger> {

    HaAppealInfo findByAplNum(BigInteger AplNum);
}
