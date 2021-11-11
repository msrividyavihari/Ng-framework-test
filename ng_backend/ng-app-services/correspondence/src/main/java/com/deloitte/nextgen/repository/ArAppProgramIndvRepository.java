package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.ArAppProgramIndv;
import com.deloitte.nextgen.entities.composite.ArAppProgramIndvId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ArAppProgramIndvRepository extends JpaRepository<ArAppProgramIndv, ArAppProgramIndvId> {

    @Query(
            value =	"SELECT DISTINCT INDV_ID as INDV_ID, LISTAGG(PROGRAM_CD, ',') WITHIN GROUP( ORDER BY PROGRAM_CD) as PROGRAM_CD  from ( " +
                    " select DISTINCT AAPI.INDV_ID as INDV_ID, AAPI.PROGRAM_CD " +
                    " FROM AR_APP_PROGRAM_INDV AAPI INNER JOIN  " +
                    " V_AR_APPLICATION_INDV_PROGRAM VA ON (AAPI.APP_NUM=VA.T1_APP_NUM and AAPI.INDV_ID=VA.T2_INDV_ID) " +
                    " WHERE AAPI.APP_NUM = (:appNum) AND AAPI.PROGRAM_CD IN ('FS','TF','MA') AND AAPI.AID_REQUEST_SW='Y' " +
                    " AND (months_between(TRUNC(LAST_DAY(TO_DATE(:tempPrintDate))),TRUNC(VA.DOB_DT))/12 ) >= 18 ) GROUP BY INDV_ID ",nativeQuery = true)
    List<ArAppProgramIndv> findByAppAndProgramCd(String appNum, Timestamp tempPrintDate);
}
