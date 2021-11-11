package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.web.entities.ArEmailDetails;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ArEmailDetailsRepository  extends JpaRepository<ArEmailDetails,Long> {

    List<ArEmailDetails> findByAppNumAndEmailSrcTyp(String appNum, String emailSrcTyp);

    ArEmailDetails findByAppNumAndEmailSrcTypAndEmailSeqNum(String appNum,String emailSrcTyp,Long emailSeqNum);

}

