package com.deloitte.nextgen.ha.common.repository;

import com.deloitte.nextgen.ha.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AmAppealNotesAmendRepository  extends JpaRepository<AmAppealNotesAmend, AmAppealNotesAmendId> {

    @Query(value="select * from AM_APPEAL_NOTES_AMEND" +
            " WHERE APL_NUM = (:aplNum) order by CREATE_DT desc",nativeQuery = true)
    List<AmAppealNotesAmend> findByAppealNum(@Param("aplNum") Long aplNum);
}
