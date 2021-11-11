package com.deloitte.nextgen.framework.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class TestEntityCustomRepositoryImpl implements TestEntityCustomRepository {

    @Autowired
    EntityManager em;

    @Override
    public void findBySeqNum(long seqNum) {
        em.createNativeQuery("select top 1 * from test_entity").getFirstResult();
    }
}
