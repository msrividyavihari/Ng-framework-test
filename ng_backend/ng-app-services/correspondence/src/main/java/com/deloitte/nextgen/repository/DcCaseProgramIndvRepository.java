package com.deloitte.nextgen.repository;


import com.deloitte.nextgen.entities.DcCaseProgramIndv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DcCaseProgramIndvRepository extends JpaRepository<DcCaseProgramIndv, Long> {

    @Query(value = " SELECT DCI.INDV_ID as INDVID,LISTAGG(DCPI.PROG_CD, ',') WITHIN " +
            " GROUP(ORDER BY DCPI.PROG_CD) AS PROGCD FROM DC_CASE_PROGRAM_INDV DCPI, DC_INDV DCI " +
            " WHERE DCPI.CASE_NUM = :caseNum AND DCI.INDV_ID = :indvId " +
            " AND DCPI.PROG_CD in ('MA','TF','FS','WC') " +
            " AND DCPI.HIST_NAV_IND IN ('S', 'P') " +
            " AND DCI.INDV_ID = DCPI.INDV_ID AND MONTHS_BETWEEN(TRUNC(LAST_DAY(:createDt)),TRUNC(DOB_DT))/12 >= 18 " +
            " GROUP BY DCI.INDV_ID ", nativeQuery = true)
    List<Object[]> findProgramCdByCaseIndvForHippa(Long caseNum,
                                                            Long indvId,
                                                            Timestamp createDt);
}
