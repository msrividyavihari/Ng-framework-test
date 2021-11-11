package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.web.entities.DcIndv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface DcIndvRepository extends JpaRepository<DcIndv,Long> {

    DcIndv findByIndvId(Long indvId);
}
