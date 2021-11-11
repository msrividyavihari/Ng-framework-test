package com.deloitte.nextgen.dc.repository;

import com.deloitte.nextgen.dc.entities.DcCaseIndividual;
import com.deloitte.nextgen.dc.entities.DcCaseIndividual_;
import com.deloitte.nextgen.framework.persistence.Specifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DcCaseIndividualRepository extends JpaRepository<DcCaseIndividual, Long>, JpaSpecificationExecutor<DcCaseIndividual> {

    default List<DcCaseIndividual> findActiveIndividuals(final Long caseNum) {
        Specification<DcCaseIndividual> caseNumSpec = (root, query, cb) -> {
            return cb.equal(root.get(DcCaseIndividual_.caseNum), caseNum);
        };

        Specification<DcCaseIndividual> activeSwSpec = (root, query, cb) -> {
            return cb.equal(cb.upper(root.get(DcCaseIndividual_.ACTIVE_IN_CASE_SW)), 'Y');
        };

        Specification<DcCaseIndividual> effectiveDatesSpec = Specifications.forTimestampedBetweenDates(DcCaseIndividual_.EFF_BEGIN_DT, DcCaseIndividual_.EFF_END_DT);
        return findAll(Specification.where(caseNumSpec).and(effectiveDatesSpec).and(activeSwSpec));
    }

    default List<DcCaseIndividual> findAssociatedCases(final Long indvId) {
        Specification<DcCaseIndividual> individualIdSpec = (root, query, cb) -> {
            return cb.equal(root.get(DcCaseIndividual_.indvId), indvId);
        };

        Specification<DcCaseIndividual> activeSwSpec = (root, query, cb) -> {
            return cb.equal(cb.upper(root.get(DcCaseIndividual_.ACTIVE_IN_CASE_SW)), 'Y');
        };

        Specification<DcCaseIndividual> effectiveDatesSpec = Specifications.forTimestampedBetweenDates(DcCaseIndividual_.EFF_BEGIN_DT, DcCaseIndividual_.EFF_END_DT);
        return findAll(Specification.where(individualIdSpec).and(effectiveDatesSpec).and(activeSwSpec));
    }
}
