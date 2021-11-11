package com.deloitte.nextgen.framework.commons.support;

import com.google.common.collect.Iterables;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.SimpleExpression;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collection;
import java.util.List;

public final class QuerydslPredicates {

    private static final Integer DEFAULT_IN_CLAUSE_LIMIT = 1000;

    private QuerydslPredicates(){
        throw new AssertionError("Creating instance of "+QuerydslPredicates.class.getName()+" is not allowed.");
    }

    public static <T> Predicate in(SimpleExpression<T> expression, Collection<? extends T> values) {
        int maxInAllowed = NumberUtils.toInt(System.getProperty("DB-IN-CLAUSE-LIMIT"), DEFAULT_IN_CLAUSE_LIMIT);
        // even collection is null .. we should prepare the expression.
        if(values == null || Iterables.size(values) <= maxInAllowed){
            return expression.in(values);
        }
        BooleanBuilder predicate = new BooleanBuilder();
        for(List<? extends T> list: Iterables.partition(values, maxInAllowed)){
            predicate.or(expression.in(list));
        }
        return predicate;
    }
}
