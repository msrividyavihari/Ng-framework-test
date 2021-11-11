package com.deloitte.nextgen.framework.persistence.repository;

import com.deloitte.nextgen.framework.persistence.junit.TypeZeroEntity;
import com.deloitte.nextgen.framework.persistence.junit.generated.TypeZeroEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rarathore on 30/09/2021 12:52 PM
 * @project ng-persistence
 */

@Repository
public interface TypeZeroEntityRepository extends JpaRepository<TypeZeroEntity, TypeZeroEntityPK> {

    List<TypeZeroEntity> findByName(String name);
}
