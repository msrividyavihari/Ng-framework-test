package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.ArAppIndv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArAppIndvRepository extends JpaRepository<ArAppIndv,Long> {

    @Query(
            value="From com.deloitte.nextgen.entities.ArAppIndv WHERE APP_NUM =(:caseAppNumber) AND HEAD_OF_HOUSEHOLD_SW = 'Y' ")
    List<ArAppIndv> findByAppHOH(String caseAppNumber);
}
