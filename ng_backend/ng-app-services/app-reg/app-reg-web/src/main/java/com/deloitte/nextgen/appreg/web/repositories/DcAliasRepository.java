package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.web.entities.DcAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DcAliasRepository extends JpaRepository<DcAlias,Long> {

    DcAlias findByIndvId(Long indvId);

    DcAlias findByIndvIdAndAliasIndSeqNum(Long indvId, Long aliasIndSeqNum);

}
