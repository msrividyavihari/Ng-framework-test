package com.deloitte.nextgen.dc.repository;


import com.deloitte.nextgen.dc.entities.DcRelationships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DcRelationshipsRepository extends JpaRepository<DcRelationships, String> {
	List<DcRelationships> findByRefIndvId(long indvId);
}
