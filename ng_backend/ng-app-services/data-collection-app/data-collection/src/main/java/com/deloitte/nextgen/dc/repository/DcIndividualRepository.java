package com.deloitte.nextgen.dc.repository;

import com.deloitte.nextgen.dc.common.dto.NameSearchDto;
import com.deloitte.nextgen.dc.entities.DcIndv;
import com.deloitte.nextgen.dc.entities.QDcIndv;
import com.deloitte.nextgen.framework.commons.support.QuerydslPredicates;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public interface DcIndividualRepository extends JpaRepository<DcIndv, Long>, QuerydslPredicateExecutor<DcIndv> {

    default List<DcIndv> findByIndvIdIn(List<Long> individualIds) {
        return Lists.newArrayList(findAll(QuerydslPredicates.in(QDcIndv.dcIndv.indvId, individualIds)));
    }

    default List<DcIndv> findByNames(@NonNull Collection<NameSearchDto> nameDtos) {
        final Set<NameSearchDto> nameSearchDtos = nameDtos
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        if (nameSearchDtos.isEmpty()) return new ArrayList<>();
        BooleanBuilder builder = new BooleanBuilder();
        for (NameSearchDto nameDto : nameSearchDtos) {
            BooleanBuilder namePredicate = new BooleanBuilder();
            if (nameDto.isFirstNameExists()) {
                namePredicate = namePredicate.and(QDcIndv.dcIndv.firstName.like(nameDto.getFirstName()));
            }
            if (nameDto.isLastNameExists()) {
                namePredicate = namePredicate.and(QDcIndv.dcIndv.lastName.like(nameDto.getLastName()));
            }
            builder = builder.or(namePredicate);
        }
        return Lists.newArrayList(findAll(builder));
    }
}