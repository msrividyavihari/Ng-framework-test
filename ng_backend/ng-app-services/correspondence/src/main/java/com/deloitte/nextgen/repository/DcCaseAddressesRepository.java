package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.DcCaseAddresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DcCaseAddressesRepository extends JpaRepository<DcCaseAddresses,Long>{

    @Query(value = "FROM com.deloitte.nextgen.entities.DcCaseAddresses " +
            "where CASE_NUM =(:caseNum) AND ADDR_TYPE_CD = 'PA' AND EFF_BEGIN_DT <= TO_DATE(:currDt) "+
            "AND EFF_END_DT IS NULL")
    List<DcCaseAddresses> findByCaseNO(Long caseNum,String currDt);

    @Query(value = "FROM com.deloitte.nextgen.entities.DcCaseAddresses " +
            "where CASE_NUM =(:caseNum) AND ADDR_TYPE_CD = (:addrTypeCd) AND EFF_BEGIN_DT <= TO_DATE(:currDt) "+
            "AND EFF_END_DT IS NULL")
    List<DcCaseAddresses> findByCaseNOAddressType(Long caseNum, String addrTypeCd, String currDt);

    @Query(value = "FROM com.deloitte.nextgen.entities.DcCaseAddresses " +
            "where CASE_NUM =(:caseNum) AND ADDR_TYPE_CD = (:addrTypeCdPa) AND EFF_BEGIN_DT <= TO_DATE(:generateDate) "+
            "AND (EFF_END_DT >=TO_DATE(:generateDate) OR EFF_END_DT IS NULL)"
    )
    List<DcCaseAddresses> findByCaseEffectiveDates(Long caseNum, String addrTypeCdPa, String generateDate);

    @Query(value = "FROM com.deloitte.nextgen.entities.DcCaseAddresses " +
            "where CASE_NUM =(:caseNum) AND ADDR_TYPE_CD = (:addrTypeCdPa) ORDER BY CASE_ADDR_SEQ_NUM DESC"
    )
    List<DcCaseAddresses> findCountyByCaseNumber(Long caseNum, String addrTypeCdPa);
}
