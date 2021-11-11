package com.deloitte.nextgen.framework.service.security.repository;

import com.deloitte.nextgen.framework.service.security.entities.SeUserCargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeUserRepository extends JpaRepository<SeUserCargo,String> {

    List<SeUserCargo> findByUserId(String userId);
}
