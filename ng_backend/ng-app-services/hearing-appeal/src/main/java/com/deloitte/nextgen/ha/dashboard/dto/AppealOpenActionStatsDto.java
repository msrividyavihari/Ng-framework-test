package com.deloitte.nextgen.ha.dashboard.dto;

import lombok.*;

import javax.persistence.*;

@Builder
@Value
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@SqlResultSetMapping(
        name=AppealOpenActionStatsDto.OPEN_APPEAL_STATS_RESULT_SET_MAPPING,
        classes={
                @ConstructorResult(
                        targetClass=AppealOpenActionStatsDto.class,
                        columns={
                                @ColumnResult(name="FILING_TOTAL", type=int.class)
                                ,@ColumnResult(name="REGISTRATION_TOTAL", type=int.class)
                                ,@ColumnResult(name="HEARING_OUTCOME_TOTAL", type=int.class)
                                ,@ColumnResult(name="LEGAL_PACKET_PRP_TOTAL", type=int.class)
                                ,@ColumnResult(name="RESEARCH_VALIDITY_TOTAL", type=int.class)
                        }
                )
        }
)
@NamedNativeQuery(name=AppealOpenActionStatsDto.OPEN_APPEAL_STATS_QUERY, query = "SELECT \n" +
        "    SUM(FILING_IND) AS FILING_TOTAL\n" +
        "    ,SUM(REGISTRATION_IND) AS REGISTRATION_TOTAL\n" +
        "    ,SUM(HEARING_OUTCOME_IND) AS HEARING_OUTCOME_TOTAL\n" +
        "    ,SUM(LEGAL_PACKET_PRP_IND) AS LEGAL_PACKET_PRP_TOTAL\n" +
        "    ,SUM(RESEARCH_VALIDITY_IND) AS RESEARCH_VALIDITY_TOTAL\n" +
        "FROM (\n" +
        "        SELECT \n" +
        "            (CASE WHEN LAST_STATUS = 'FIP' THEN 1 ELSE 0 END) AS FILING_IND\n" +
        "            ,(CASE WHEN LAST_STATUS IN ('FL','RG') THEN 1 ELSE 0 END) AS REGISTRATION_IND\n" +
        "            ,(CASE WHEN LAST_STATUS IN ('RH' ,'PO'  ,'IO', 'POI', 'FOI', 'PJR','PCO','COI','TBR','PE') THEN 1 ELSE 0  END) AS HEARING_OUTCOME_IND\n" +
        "            ,(CASE WHEN LAST_STATUS IN ('TBS','S')  THEN 1 ELSE 0 END) AS LEGAL_PACKET_PRP_IND\n" +
        "            ,(CASE WHEN LAST_STATUS IN ('PCE', 'VF', 'PCD' ,'RIP' ,'PR' ,'RAP') THEN 1 ELSE 0 END) AS RESEARCH_VALIDITY_IND\n" +
        "        FROM \n" +
        "            AM_APPEAL_INFO\n" +
        "    ) A",
        resultSetMapping = "OpenAppealsActionStatsResultSetMapping")
@Entity
public class AppealOpenActionStatsDto {
    public static final String OPEN_APPEAL_STATS_QUERY = "AppealOpenActionStatsDto.getOpenAppealsActionStats";
    protected static final String OPEN_APPEAL_STATS_RESULT_SET_MAPPING = "OpenAppealsActionStatsResultSetMapping";

    // WARNING: Do not change the order of variable declarations.
    // as it's used in the SqlResultSetMapping. Refer above ConstructorResult order of db columns vs attributes mapping.
    @Id
    int filing;
    int registration;
    int hearingOutcome;
    int legalPacketPrep;
    int researchValidity;
}