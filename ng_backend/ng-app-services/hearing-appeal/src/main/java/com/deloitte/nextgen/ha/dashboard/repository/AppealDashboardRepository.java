package com.deloitte.nextgen.ha.dashboard.repository;

import com.deloitte.nextgen.ha.dashboard.entity.AppealFiledResolvedStatsEntity;
import com.deloitte.nextgen.ha.dashboard.dto.AppealOpenActionStatsDto;
import com.deloitte.nextgen.ha.entity.AmAppealInfo;
import com.deloitte.nextgen.ha.model.AppealStatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface AppealDashboardRepository extends JpaRepository<AmAppealInfo, Long> {

    @Query(value = "SELECT DAYS_RANGE, COUNT(LAST_STATUS_EFF_DT) AS APPEAL_COUNT\n" +
            "FROM\n" +
            "(SELECT CASE WHEN LAST_STATUS_EFF_DT > SYSDATE -30 THEN 'UNDER_30_DAYS'\n" +
            "            WHEN LAST_STATUS_EFF_DT BETWEEN SYSDATE -60 AND SYSDATE -31 THEN 'BETWEEN_31_AND_60_DAYS'\n" +
            "            WHEN LAST_STATUS_EFF_DT BETWEEN SYSDATE -90 AND SYSDATE -61 THEN 'BETWEEN_61_AND_90_DAYS'\n" +
            "            ELSE 'ABOVE_90_DAYS'\n" +
            "        END AS DAYS_RANGE , LAST_STATUS_EFF_DT\n" +
            "FROM AM_APPEAL_INFO WHERE LAST_STATUS NOT IN (:appealStatusCds)\n" +
            ") T\n" +
            "GROUP BY DAYS_RANGE  " +
            " UNION ALL" +
            " SELECT 'AVG_OPEN_DAYS' as AVG_OPEN_DAYS, trunc(sysdate) - trunc(TO_DATE(ROUND(AVG(TO_NUMBER(TO_CHAR(LAST_STATUS_EFF_DT, 'J')))), 'J')) AS AVG_OPEN_DAYS \n" +
            " FROM AM_APPEAL_INFO WHERE LAST_STATUS NOT IN (:appealStatusCds) "
            , nativeQuery = true)
    List<Object[]> getOpenAppealsStatsInternal(@Param("appealStatusCds") Collection<String> appealStatusCds);

    default Map<String, Integer> getOpenAppealsStats() {
        return this
                .getOpenAppealsStatsInternal(AppealStatusCode.getCompleteAppealStatusCodes())
                .stream()
                .collect(Collectors.toMap(e -> String.valueOf(e[0]), e -> Integer.valueOf(String.valueOf(e[1]))));
    }

    @Query(name=AppealOpenActionStatsDto.OPEN_APPEAL_STATS_QUERY, nativeQuery = true)
    List<AppealOpenActionStatsDto> getOpenAppealsActionStats();

    @Query(name= AppealFiledResolvedStatsEntity.FILED_RESOLVED_STATS_QUERY, nativeQuery = true)
    List<AppealFiledResolvedStatsEntity> getFiledResolvedAppealsStats(@Param("fromDate") LocalDate fromDate, @Param("endDate") LocalDate endDate );

}
