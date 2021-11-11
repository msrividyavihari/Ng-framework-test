package com.deloitte.nextgen.ha.common.repository;

import com.deloitte.nextgen.framework.commons.support.QuerydslPredicates;
import com.deloitte.nextgen.ha.dashboard.dto.AppealSearchResponseDto;
import com.deloitte.nextgen.ha.entity.*;
import com.google.common.collect.Lists;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.support.QueryBase;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.server.RequestPredicates;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface AmAppealNotesRepository extends JpaRepository<AmAppealNotes,String> {


  /*  @Query(value="select * from AM_APPEAL_NOTES" +
            " WHERE APL_NUM = (:aplNum) order by CREATE_DT desc",nativeQuery = true)
    List<AmAppealNotes> findByAppealNum1(@Param("aplNum") Long aplNum);*/

    List<AmAppealNotes> findByAplNum(BigInteger aplNum);

}

