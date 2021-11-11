package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.ArApplicationForAid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArApplicationForAidRepository extends JpaRepository<ArApplicationForAid,String> {

    List<ArApplicationForAid> findByAppNum(String caseAppNumber);

    List<ArApplicationForAid> findByCaseNum(Long caseNum);
}
