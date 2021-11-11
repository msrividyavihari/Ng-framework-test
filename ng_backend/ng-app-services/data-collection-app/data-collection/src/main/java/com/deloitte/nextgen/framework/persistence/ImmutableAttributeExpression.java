package com.deloitte.nextgen.framework.persistence;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ImmutableAttributeExpression<Y extends Comparable<? super Y>> implements AttributeExpression<Y> {
    @Getter
    String name;
    Expression<? extends Y> expression;

    @Override
    public Expression<? extends Y> getExpression(CriteriaBuilder cb) {
        return expression;
    }
}
