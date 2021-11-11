package com.deloitte.nextgen.framework.commons.support;

import com.querydsl.core.types.Expression;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QSort;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public final class PageableFunctions {

    private PageableFunctions() {
        throw new AssertionError("Creating instance of " + PageableFunctions.class.getName() + " is not allowed.");
    }

    public static Function<Pageable, Pageable> toQSortPageable(Map<String, Expression<?>> expressionMap) {
        return new QSortPageableFunction(MoreFunctions.toMapFunction(expressionMap));
    }

    private static class QSortPageableFunction implements UnaryOperator<Pageable> {

        private final Function<Sort, QSort> qSortFunction;

        private QSortPageableFunction(Function<String, Expression<?>> expressionFunction) {
            this.qSortFunction = QuerydslFunctions.qSortFunction(expressionFunction);
        }

        @Override
        public Pageable apply(@Nullable Pageable pageable) {
            if (pageable == null) return null;
            if (pageable.getSort().isUnsorted()) return pageable;
            QSort qSort = qSortFunction.apply(pageable.getSort());
            return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), qSort);
        }
    }

}
