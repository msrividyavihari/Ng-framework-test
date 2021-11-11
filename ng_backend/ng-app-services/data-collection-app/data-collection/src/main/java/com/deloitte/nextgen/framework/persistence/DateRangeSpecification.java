package com.deloitte.nextgen.framework.persistence;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.sql.Timestamp;
import java.util.Optional;

@Builder
@Value
@EqualsAndHashCode
@ToString
public class DateRangeSpecification<T, Y extends Comparable<? super Y>> implements Specification<T> {

    AttributeExpression<Y> beginDtAttrExp;
    AttributeExpression<Y> endDtAttrExp;

    public static <T> DateRangeSpecification<T, Timestamp> forTimestampedBetweenDates(String effectiveBeginDtFldName, String effectiveEndDtFldName) {
        return DateRangeSpecification
                .<T, Timestamp>builder()
                .beginDtAttrExp(new TimestampAttributeExpressionImpl(effectiveBeginDtFldName))
                .endDtAttrExp(new TimestampAttributeExpressionImpl(effectiveEndDtFldName))
                .build();
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

        if(beginDtAttrExp == null && endDtAttrExp == null) return null;

        Predicate effBeginDtNullOrLessThanOrEqualToCurrentDate = cb.conjunction();
        Predicate effEndDtDtNullOrGreaterThanOrEqualToCurrentDate = cb.conjunction();

        if (beginDtAttrExp != null) {
            Expression<Y> effBeginDt = root.get(beginDtAttrExp.getName());
            effBeginDtNullOrLessThanOrEqualToCurrentDate = cb.or(cb.isNull(effBeginDt), cb.lessThanOrEqualTo(effBeginDt, beginDtAttrExp.getExpression(cb)));
        }

        if (endDtAttrExp != null) {
            Path<Y> effEndDt = root.get(endDtAttrExp.getName());
            effEndDtDtNullOrGreaterThanOrEqualToCurrentDate = cb.or(cb.isNull(effEndDt), cb.greaterThanOrEqualTo(effEndDt, endDtAttrExp.getExpression(cb)));
        }
        return cb.and(effEndDtDtNullOrGreaterThanOrEqualToCurrentDate, effBeginDtNullOrLessThanOrEqualToCurrentDate);
    }


    private static class TimestampAttributeExpressionImpl implements AttributeExpression<Timestamp>{

        private final String name;
        private final Optional<Expression<? extends Timestamp>> expression;

        private TimestampAttributeExpressionImpl(String name){
            this(name, null);
        }
        private TimestampAttributeExpressionImpl(String name, Expression<? extends Timestamp> expression){
            this.name = Preconditions.checkNotNull(name, "Attribute Name should not be null");
            this.expression = Optional.ofNullable(expression);
        }

        private TimestampAttributeExpressionImpl(SingularAttribute<?, ? extends Timestamp> singularAttribute, Expression<? extends Timestamp> expression){
            this(Preconditions.checkNotNull(singularAttribute, "Singular Attribute should not be null").getName(), expression);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Expression<? extends Timestamp> getExpression(CriteriaBuilder cb) {
            return expression.orElse(cb.currentTimestamp());
        }

        @Override
        public String toString() {
            return "AttributeExpressionImpl{" +
                    "name='" + name + '\'' +
                    ", expression=" + expression +
                    '}';
        }
    }

}
