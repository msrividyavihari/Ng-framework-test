package com.deloitte.nextgen.ha.persistence;

import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public final class Specifications {

    private static final Integer DEFAULT_IN_CLAUSE_LIMIT = 1000;

    public static <X> Specification<X> withDateFieldNames(String beginDtFldName, String endDtFldName) {
        return DatesRangeSpecification
                .<X>builder()
                .endDtFldName(endDtFldName)
                .beginDtFldName(beginDtFldName)
                .build();
    }

    public static <X, Y> Specification<X> in(SingularAttribute<? super X, Y> singularAttribute, Collection<?> values) {
        return (root, query, cb) -> {
            Integer MAX_IN_ALLOWED = Ints.tryParse(System.getProperty("DB-IN-CLAUSE-LIMIT", DEFAULT_IN_CLAUSE_LIMIT.toString()));
            MAX_IN_ALLOWED = Optional.ofNullable(MAX_IN_ALLOWED).orElse(DEFAULT_IN_CLAUSE_LIMIT);
            Path<Y> attribute = root.get(singularAttribute);
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