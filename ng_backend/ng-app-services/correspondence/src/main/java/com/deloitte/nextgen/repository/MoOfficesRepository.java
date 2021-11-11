package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.MoOffices;
import com.deloitte.nextgen.entities.composite.MoOfficesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoOfficesRepository extends JpaRepository<MoOffices, MoOfficesId> {

    @Query(
            value = "FROM org.deloitte.com.entities.MoOffices where office_num=(:aOfficeNum)"+
                    "AND TRUNC(EFF_BEGIN_DT) <= TRUNC(TO_DATE(:curDt))"+
                    "AND (TRUNC(EFF_END_DT) >= TRUNC(TO_DATE(:curDt)) OR EFF_END_DT IS NULL)"
            ,nativeQuery = true
    )
    List<MoOffices> findByOfficeNumber(Long aOfficeNum, String curDt);
}
