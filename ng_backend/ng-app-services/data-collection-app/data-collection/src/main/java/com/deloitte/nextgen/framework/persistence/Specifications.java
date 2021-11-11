package com.deloitte.nextgen.framework.persistence;

import java.util.Collection;
import java.util.Optional;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public final class Specifications {

    private static final Integer DEFAULT_IN_CLAUSE_LIMIT = 1000;

    public static <X> Specification<X> forTimestampedBetweenDates(String beginDtFldName, String endDtFldName) {
        return DateRangeSpecification.forTimestampedBetweenDates(beginDtFldName, endDtFldName);
    }

    public static <X, Y> Specification<X> in(SingularAttribute<? super X, Y> singularAttribute, Collection<?> values) {
        return (root, query, cb) -> {
            Integer MAX_IN_ALLOWED = Ints.tryParse(System.getProperty("DB-IN-CLAUSE-LIMIT", DEFAULT_IN_CLAUSE_LIMIT.toString()));
            MAX_IN_ALLOWED = Optional.ofNullable(MAX_IN_ALLOWED).orElse(DEFAULT_IN_CLAUSE_LIMIT);
            Path<Y> attribute = root.get(singularAttribute);
            // even collection is null .. we should prepare the expression.
            if(values == null || Iterables.size(values) <= MAX_IN_ALLOWED){
                return attribute.in(values);
            }
            Predicate p =  cb.conjunction();
            for(List<?> list: Iterables.partition(values, MAX_IN_ALLOWED)){
               p = cb.or(p, attribute.in(list));
            }
            return p;
        };
    }
}