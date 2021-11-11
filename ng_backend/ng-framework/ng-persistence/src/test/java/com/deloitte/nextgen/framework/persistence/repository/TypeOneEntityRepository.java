package com.deloitte.nextgen.framework.persistence.repository;

import com.deloitte.nextgen.framework.persistence.junit.TypeOneEntity;
import com.deloitte.nextgen.framework.persistence.junit.generated.TypeOneEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rarathore on 30/09/2021 12:52 PM
 * @project ng-persistence
 */

@Repository
public interface TypeOneEntityRepository extends JpaRepository<TypeOneEntity, TypeOneEntityPK> {

    List<TypeOneEntity> findByTitle(String title);
}
