package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.CoManualFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoManualFieldsRepository extends JpaRepository<CoManualFields,Long> {
    List<CoManualFields> findByDocIdOrderByFieldOrderNum(String docId);
}
