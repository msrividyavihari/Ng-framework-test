package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.DcHeadOfHousehold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DcHeadOfHouseholdRepository extends JpaRepository<DcHeadOfHousehold,Long> {

    @Query(
            value=  "FROM com.deloitte.nextgen.entities.DcHeadOfHousehold "+
            "WHERE CASE_NUM =(:caseNum) AND HOH_BEGIN_DT <=TO_DATE(:effBeginDt) " +
            "AND (HOH_END_DT IS NULL OR HOH_END_DT >=TO_DATE(:effBeginDt))")
    List<DcHeadOfHousehold> findIndvByCaseNumHohSwNotices(Long caseNum, String effBeginDt);

    @Query(
            value=  "FROM com.deloitte.nextgen.entities.DcHeadOfHousehold "+
                    "WHERE CASE_NUM =(:caseNum) AND HOH_BEGIN_DT <=TO_DATE(:effBeginDt) " +
                    "AND (HOH_END_DT IS NULL OR HOH_END_DT >=TO_DATE(:effBeginDt)) ORDER BY HOH_BEGIN_DT ASC")
    List<DcHeadOfHousehold> findHoHbyCaseNum(Long caseNum, String effBeginDt);
}
