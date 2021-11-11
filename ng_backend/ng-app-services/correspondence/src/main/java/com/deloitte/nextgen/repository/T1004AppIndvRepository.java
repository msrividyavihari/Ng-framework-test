package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.T1004AppIndv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface T1004AppIndvRepository extends JpaRepository<T1004AppIndv, String> {

    @Query(value = "FROM com.deloitte.nextgen.entities.T1004AppIndv where appNum = ':appNum' AND primPrsnSw = 'Y' ")
    List<T1004AppIndv> findHeadofHousehold(String appNum);

    @Query(value = "FROM com.deloitte.nextgen.entities.T1004AppIndv where appNum = :appNum  " +
            " ORDER BY INDV_SEQ_NUM")
    List<T1004AppIndv> findByAppNum(String appNum);

    List<T1004AppIndv> findByAppNumAndIndvSeqNum(String appNum, Long indvSeqNum);
}
