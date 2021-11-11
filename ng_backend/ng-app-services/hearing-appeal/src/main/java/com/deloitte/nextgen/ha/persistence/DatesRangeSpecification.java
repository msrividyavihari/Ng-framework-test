package com.deloitte.nextgen.ha.persistence;

import com.google.common.base.Optional;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Value
@EqualsAndHashCode
public class DatesRangeSpecification<T> implements Specification<T> {

    @Builder.Default
    String beginDtFldName = "effBeginDt";

    @Builder.Default
    String endDtFldName = "effEndDt";

    Timestamp dateValue;
    boolean ignoreBeginDt;
    boolean ignoreEndDt;

    public static <T> DatesRangeSpecification<T> withEffectiveDateFieldNames() {
        return DatesRangeSpecification.<T>builder().build();
    }

    public static <T> DatesRangeSpecification<T> withDateFieldNames(String effectiveBeginDtFldName, String effectiveEndDtFldName) {
        return DatesRangeSpecification
                .<T>builder()
                .endDtFldName(effectiveEndDtFldName)
                .beginDtFldName(effectiveBeginDtFldName)
                .build();
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        if (StringUtils.isBlank(endDtFldName) && StringUtils.isBlank(beginDtFldName))
            throw new IllegalStateException("Both beginDtFldName and endDtFldName configured to be ignored. At least one of them needed");

        final Timestamp currentDt = Optional
                .fromNullable(dateValue)
                .or(Timestamp.valueOf(LocalDateTime.now()));

        Predicate effBeginDtNullOrLessThanOrEqualToCurrentDate = cb.conjunction();
        Predicate effEndDtDtNullOrGreaterThanOrEqualToCurrentDate = cb.conjunction();

        if (!StringUtils.isBlank(endDtFldName)) {
            Path<Timestamp> effBeginDt = root.get(beginDtFldName);
            effBeginDtNullOrLessThanOrEqualToCurrentDate = cb.or(cb.isNull(effBeginDt), cb.lessThanOrEqualTo(effBeginDt, currentDt));
        }

        if (!StringUtils.isBlank(beginDtFldName)) {
            Path<Timestamp> effEndDt = root.get(endDtFldName);
            effEndDtDtNullOrGreaterThanOrEqualToCurrentDate = cb.or(cb.isNull(effEndDt), cb.greaterThanOrEqualTo(effEndDt, currentDt));
        }
        return cb.and(effEndDtDtNullOrGreaterThanOrEqualToCurrentDate, effBeginDtNullOrLessThanOrEqualToCurrentDate);
    }

    @SuppressWarnings("unused")
    public static class DatesRangeSpecificationBuilder<T> {
        @SuppressWarnings("unused")
        public DatesRangeSpecificationBuilder<T> ignoreBeginDt() {
            this.beginDtFldName(null);
            return this;
        }

        @SuppressWarnings("unused")
        public DatesRangeSpecificationBuilder<T> ignoreEndDt() {
            this.endDtFldName(null);
            return this;
        }
    }
}
