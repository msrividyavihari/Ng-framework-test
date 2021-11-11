package com.deloitte.nextgen.ha.dashboard.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@SqlResultSetMapping(
        name = AppealFiledResolvedStatsEntity.RESULT_SET_MAPPING_NAME,
        classes = {
                @ConstructorResult(
                        targetClass = AppealFiledResolvedStatsEntity.class,
                        columns = {
                                @ColumnResult(name = "MONTH_YEAR", type = LocalDate.class)
                                , @ColumnResult(name = "FILED_COUNT" , type = BigInteger.class)
                                , @ColumnResult(name = "RESOLVED_COUNT" , type = BigInteger.class)
                        }
                )
        }
)
@NamedNativeQuery(name = AppealFiledResolvedStatsEntity.FILED_RESOLVED_STATS_QUERY, query = "SELECT \n" +
        "    MONTH_YEAR \n" +
        "    ,SUM(FILED_COUNTER) AS FILED_COUNT\n" +
        "    ,SUM(RESOLVED_COUNTER) AS RESOLVED_COUNT\n" +
        "FROM (\n" +
        "    SELECT  \n" +
        "        TRUNC(STATUS_EFF_DT, 'MM') AS MONTH_YEAR \n" +
        "        ,CASE WHEN APL_STATUS='FL' THEN 1 ELSE 0 END AS FILED_COUNTER\n" +
        "        ,CASE WHEN APL_STATUS='PDA' THEN 1 ELSE 0 END AS RESOLVED_COUNTER\n" +
        "    FROM \n" +
        "        am_apl_stat_log     \n" +
        "    WHERE \n" +
        "        TRUNC(STATUS_EFF_DT) BETWEEN (:fromDate) and (:endDate) \n" +
        "        AND APL_STATUS IN ('FL', 'PDA')\n" +
        ") A GROUP BY MONTH_YEAR",
        resultSetMapping = AppealFiledResolvedStatsEntity.RESULT_SET_MAPPING_NAME)
@Builder
@Value
@ToString
@EqualsAndHashCode
@Entity
@AllArgsConstructor
public class AppealFiledResolvedStatsEntity {

    public static final String FILED_RESOLVED_STATS_QUERY = "AppealFiledResolvedStatsEntity.getFiledResolvedAppealsStats";
    protected static final String RESULT_SET_MAPPING_NAME = "AppealFiledResolvedStatsResultSetMapping";

    @Id
    @JsonFormat(pattern = "MM/yy")
    @NonNull
    LocalDate monthYear;
    @Builder.Default
    BigInteger filedCount = BigInteger.ZERO;
    @Builder.Default
    BigInteger resolvedCount = BigInteger.ZERO;

    public static AppealFiledResolvedStatsEntity of(LocalDate monthYear) {
        return AppealFiledResolvedStatsEntity
                .builder()
                .monthYear(monthYear)
                .build();
    }
}
