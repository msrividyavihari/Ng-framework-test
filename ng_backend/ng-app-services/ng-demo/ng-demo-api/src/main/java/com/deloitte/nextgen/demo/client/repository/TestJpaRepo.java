package com.deloitte.nextgen.demo.client.repository;

import com.deloitte.nextgen.demo.client.entities.PersonDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestJpaRepo  extends JpaRepository<PersonDetail,String> {
}
