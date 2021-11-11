package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.CoNodRequestDetail;
import com.deloitte.nextgen.entities.composite.CoNodRequestDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoNodRequestDetailRepository extends JpaRepository<CoNodRequestDetail, CoNodRequestDetailId> {
}
