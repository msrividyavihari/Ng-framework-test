package com.deloitte.nextgen.framework.commons.support;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QSort;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public final class QuerydslFunctions {

    private QuerydslFunctions(){
        throw new AssertionError("Creating instance of "+QuerydslFunctions.class.getName()+" is not allowed.");
    }

    public static Function<Sort, QSort> qSortFunction(Map<String, Expression<?>> expressionMap) {
        return qSortFunction(MoreFunctions.toMapFunction(expressionMap));
    }

    public static Function<Sort, QSort> qSortFunction(Function<String, Expression<?>> expressionFunction) {
        return new QSortFunction(expressionFunction);
    }

    private static class QSortFunction implements Function<Sort, QSort> {

        private final Function<Sort.Order, OrderSpecifier<?>> orderSpecifierFunction;

        private QSortFunction(Function<String, Expression<?>> expressionFunction) {
            this.orderSpecifierFunction = new OrderSpecifierFunction(expressionFunction);
        }

        @Override
        public QSort apply(Sort sort) {
            OrderSpecifier<?>[] orderSpecifiers = sort
                    .stream()
                    .map(orderSpecifierFunction)
                    .toArray(OrderSpecifier<?>[]::new);
            return QSort.by(orderSpecifiers);
        }
    }

    private static class OrderSpecifierFunction implements Function<Sort.Order, OrderSpecifier<?>> {

        private final Function<String, Expression<?>> expressionFunction;

        private OrderSpecifierFunction(Function<String, Expression<?>> expressionFunction) {
            this.expressionFunction = Objects.requireNonNull(expressionFunction, "String to Expression function should not be null");
        }

        @SuppressWarnings("rawtypes")
        @Override
        public OrderSpecifier<?> apply(Sort.Order order) {
            Order o = (order.isAscending()) ? Order.ASC : Order.DESC;
            Expression<?> expression = expressionFunction.apply(order.getProperty());
            Objects.requireNonNull(expression, "Unknown sort property "+order.getProperty());
            //noinspection unchecked
            return new OrderSpecifier(o, expression);
        }
    }
}
