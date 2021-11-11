package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.DcAuthRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DcAuthRepRepository extends JpaRepository<DcAuthRep,Long> {
}
