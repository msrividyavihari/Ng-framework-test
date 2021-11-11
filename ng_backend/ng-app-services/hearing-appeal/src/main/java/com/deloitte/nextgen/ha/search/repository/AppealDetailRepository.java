package com.deloitte.nextgen.ha.search.repository;

import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.deloitte.nextgen.ha.entity.AmAppealInfo;

import java.util.*;

@Repository
public interface AppealDetailRepository extends JpaRepository<AmAppealInfo, Long> {

    Integer DEFAULT_IN_CLAUSE_LIMIT = 1000;

    default  List<Object[]>   /*List<AmAppealInfo>*/ findByCaseNum(Long caseNum){
        return findByCaseNumInternal(Collections.singletonList(caseNum));
    }

    /*default  List<AmAppealInfo> findByCaseNumIn(@Param("caseNums") Collection<Long> caseNums){
        Integer MAX_IN_ALLOWED = Ints.tryParse(System.getProperty("DB-IN-CLAUSE-LIMIT", DEFAULT_IN_CLAUSE_LIMIT.toString()));
        MAX_IN_ALLOWED = Optional.ofNullable(MAX_IN_ALLOWED).orElse(DEFAULT_IN_CLAUSE_LIMIT);
        if(caseNums == null || Iterables.size(caseNums) <= MAX_IN_ALLOWED){
            return findByCaseNumInternal(caseNums);
        }
        List<AmAppealInfo> result = new ArrayList<>();
        for(List<Long> list: Iterables.partition(caseNums, MAX_IN_ALLOWED)){
            result.addAll(findByCaseNumInternal(list));
        }
        return result;
    }*/

    /*
     @Mapping(source = "appealInfo.lastStatus", target = "appealStatusCd")
    @Mapping(source = "appealInfo.lastStatusEffDt", target = "appealLastUpdatedDt")
    @Mapping(source = "appealInfo.aplNum", target = "appealNum")

AI.APL_NUM,AI.GROSS_DAYS,AI.NET_DAYS,AI.DELAY_DAYS,AI.AGENCY_CD,AI.CONTINUANCES_DESC,AI.DAYS_LEFT_OPEN,AI.FAD_DUE_DAYS," +
            "AI.FAD_DUE_DT,AI.FAD_ISSUED,AI.FAD_IMPLEMENTED,AI.HO_FAD_DUE_DT,AI.LAST_HEARD_DT,AI.LAST_CONTINUED_DT,AI.FIRST_SCHEDULED_DT," +
            "AI.LAST_SCHEDULED_DT,AI.LAST_STATUS,AI.LAST_STATUS_EFF_DT,AI.FIRST_STATUS_EFF_DT,AI.RECORD_DAYS_NUM," +
            "AI.RESOLVED_DT,AI.CASE_AUTHREP_INCLUDED

     */

    @Query(value="SELECT AI.APL_NUM,AI.LAST_STATUS_EFF_DT,AI.LAST_STATUS FROM  AM_APPEAL_INFO AI   WHERE    1 = 1  AND " +
            "(AI.APL_NUM IN (SELECT APL_NUM FROM AM_REQUEST_DETAILS WHERE KIDS_CASE_ID IN (:caseNums)) " +
            "OR AI.APL_NUM IN (SELECT APL_NUM FROM AM_REQUEST_PROG_DETAILS WHERE IES_CASE_NUM IN (:caseNums)) )  "
            , nativeQuery = true)
    /*List<AmAppealInfo>*/   List<Object[]>  findByCaseNumInternal(@Param("caseNums") Collection<Long> caseNums);
}
