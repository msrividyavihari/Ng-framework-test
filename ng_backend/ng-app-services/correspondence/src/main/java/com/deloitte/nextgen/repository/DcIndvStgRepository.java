package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.DcIndvStg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DcIndvStgRepository extends JpaRepository<DcIndvStg,Long> {
}
