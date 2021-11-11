package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.CoMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoMasterRepository extends JpaRepository<CoMaster,Long> {

    @Query(
            value=  "FROM com.deloitte.nextgen.entities.CoMaster WHERE docId=upper(:docId) AND effBeginDt <=TO_DATE(:effBeginDt) " +
                    "AND (effEndDt >=TO_DATE(:effBeginDt) OR effEndDt IS NULL) ")
    List<CoMaster> findByDocId(String docId, String effBeginDt);

    @Query(
            value=  "FROM com.deloitte.nextgen.entities.CoMaster")
    List<CoMaster> findAll(String docId, String effBeginDt);

    @Query(value = "FROM com.deloitte.nextgen.entities.CoMaster WHERE docId in ( ':docIds' ) ")
    List<CoMaster> findByDocIds(String docIds);
}
