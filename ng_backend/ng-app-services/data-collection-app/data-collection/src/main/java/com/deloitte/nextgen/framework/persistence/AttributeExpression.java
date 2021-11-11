package com.deloitte.nextgen.framework.persistence;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;

public interface AttributeExpression<Y extends Comparable<? super Y>> {
    String getName();
    Expression<? extends Y> getExpression(CriteriaBuilder cb);
}
