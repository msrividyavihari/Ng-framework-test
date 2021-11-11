package com.deloitte.nextgen.ha.appeals.repository;

import com.deloitte.nextgen.framework.commons.support.PageableFunctions;
import com.deloitte.nextgen.ha.dashboard.dto.AppealSearchCriteria;
import com.deloitte.nextgen.ha.dashboard.dto.AppealSearchResponseDto;
import com.deloitte.nextgen.ha.dashboard.dto.QAppealSearchResponseDto;
import com.deloitte.nextgen.ha.entity.*;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Repository
@Slf4j
public class AppealSearchRepository extends QuerydslRepositorySupport {


    public static final String DEFAULT_SORT_FIELD = "lastUpdated";
    //Creating alias...
    private static final QAmAppealInfo AAI = QAmAppealInfo.amAppealInfo;
    private static final QAmRequestProgDetails ARPD = QAmRequestProgDetails.amRequestProgDetails;
    private static final QAmRequestDetails ARD = QAmRequestDetails.amRequestDetails;
    private static final QAmDocketAppeals ADA = QAmDocketAppeals.amDocketAppeals;
    private static final QAmDocketDetails ADD = QAmDocketDetails.amDocketDetails;
    private static final QAmAplStatLog AASL = QAmAplStatLog.amAplStatLog;
    private static final Map<String, Expression<?>> SORT_MAPPING = ImmutableMap.<String, Expression<?>>builder()
            .put(DEFAULT_SORT_FIELD, AAI.createDt)
            .put("age", AAI.firstStatusEffDt)
            .put("individualID", ARD.indvId)
            .put("appealNum", AAI.aplNum)
            .put("caseNum", ARPD.iesCaseNum)
            .put("docketID", ADD.docketId)
            .put("type", ARPD.aplType)
            .put("Status", AAI.lastStatus)
            .build();

    private static final Function<Pageable, Pageable> QSORT_PAGE_FUNC = PageableFunctions.toQSortPageable(SORT_MAPPING);

    public AppealSearchRepository() {
        super(QAmAppealInfo.class);
    }

    public Page<AppealSearchResponseDto> findAppeals(final AppealSearchCriteria appealSearchCriteria, @PageableDefault @SortDefault(AppealSearchRepository.DEFAULT_SORT_FIELD) Pageable pageable) {
        pageable = QSORT_PAGE_FUNC.apply(pageable);
        JPQLQuery<AppealSearchResponseDto> query = from(AAI)
                .select(new QAppealSearchResponseDto(AAI.aplNum
                        , ARPD.aplType
                        , AAI.lastStatus
                        , Expressions.dateOperation(Integer.class, Ops.DateTimeOps.DIFF_DAYS, DateExpression.currentDate(LocalDate.class), AAI.firstStatusEffDt)
                        , ARPD.iesCaseNum
                        , ARD.indvId
                        , AAI.createDt
                        , ADD.docketId)
                )
                .innerJoin(ARPD).on(ARPD.aplNum.eq(AAI.aplNum))
                .innerJoin(ARD).on(ARD.aplNum.eq(AAI.aplNum))
                .leftJoin(ADA).on(ADA.aplNum.eq(AAI.aplNum).and(ADA.appealOutcomeCd.eq("S").or(ADA.appealOutcomeCd.isNull())))
                .leftJoin(ADD).on(ADD.docketId.eq(ADA.docketId).and(ADD.statusCd.notIn("CAN", "COMP")))
                .where(
                        ARD.indvId.isNotNull()
                        , ARD.indvId.gt(0)
                        , isInAppealStatusCds(appealSearchCriteria)
                        , isInAppealTypeCds(appealSearchCriteria)
                        , betweenLastUpdatedDt(appealSearchCriteria)
                        , betweenFiledDt(appealSearchCriteria)
                );

        QueryResults<AppealSearchResponseDto> results = Objects.requireNonNull(getQuerydsl())
                .applyPagination(pageable, query)
                .fetchResults();
        long totalRows = results.getTotal();
        List<AppealSearchResponseDto> matchedAppeals = results.getResults();
        return new PageImpl<>(matchedAppeals, pageable, totalRows);

    }

    private BooleanExpression isInAppealStatusCds(final AppealSearchCriteria appealSearchCriteria) {
        if (appealSearchCriteria.getAppealStatusCds() == null || appealSearchCriteria.getAppealStatusCds().isEmpty())
            return null;
        return AAI.lastStatus.in(appealSearchCriteria.getAppealStatusCds());
    }

    private BooleanExpression isInAppealTypeCds(final AppealSearchCriteria appealSearchCriteria) {
        if (appealSearchCriteria.getAppealTypeCds() == null || appealSearchCriteria.getAppealTypeCds().isEmpty())
            return null;
        return ARPD.aplType.in(appealSearchCriteria.getAppealTypeCds());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private BooleanBuilder betweenLastUpdatedDt(final AppealSearchCriteria appealSearchCriteria) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Range<ChronoLocalDate> lastUpdateDtRange = appealSearchCriteria.getLastUpdateDtRange();
        if (lastUpdateDtRange == null || Range.unbounded().equals(lastUpdateDtRange))
            return null;
        BooleanBuilder builder = new BooleanBuilder();
        if (lastUpdateDtRange.getLowerBound().isBounded()) {
         //   builder.and(AAI.createDt.goe((LocalDate) lastUpdateDtRange.getLowerBound().getValue().get()));
            LocalDate temp =  (LocalDate) lastUpdateDtRange.getLowerBound().getValue().get();
            builder.and(AAI.createDt.goe(  Date.from( temp.atStartOfDay(defaultZoneId).toInstant()) ) );

        }
        if (lastUpdateDtRange.getUpperBound().isBounded()) {
          // builder.and(AAI.updateDt.loe((LocalDate) lastUpdateDtRange.getUpperBound().getValue().get()));

            LocalDate temp =  (LocalDate) lastUpdateDtRange.getUpperBound().getValue().get();
            builder.and(AAI.createDt.goe(  Date.from( temp.atStartOfDay(defaultZoneId).toInstant()) ) );

        }
        return builder;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private BooleanExpression betweenFiledDt(final AppealSearchCriteria appealSearchCriteria) {
        Range<ChronoLocalDate> filedDtRange = appealSearchCriteria.getFiledDtRange();
        if (filedDtRange == null || Range.unbounded().equals(filedDtRange))
            return null;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(AASL.aplNum.eq(AAI.aplNum));
        if (filedDtRange.getLowerBound().isBounded()) {
            builder.and(AASL.statusEffDt.goe((LocalDate) filedDtRange.getLowerBound().getValue().get()));
        }
        if (filedDtRange.getUpperBound().isBounded()) {
            builder.and(AASL.statusEffDt.loe((LocalDate) filedDtRange.getUpperBound().getValue().get()));
        }
        return JPAExpressions
                .selectFrom(AASL)
                .where(builder)
                .exists();
    }
}
