package com.deloitte.nextgen.consumers.repository;

import com.deloitte.nextgen.consumers.entities.ErrorContext;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nishmehta on 15/03/2021 2:36 PM
 * @project ng-consumers
 */
public interface ErrorContextRepository extends JpaRepository<ErrorContext, Long> {
}
