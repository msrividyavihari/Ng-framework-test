package com.deloitte.nextgen.dc.repository;

import com.deloitte.nextgen.dc.entities.DcAuthRep;
import com.deloitte.nextgen.dc.entities.DcAuthRep_;
import com.deloitte.nextgen.framework.persistence.Specifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DcAuthRepRepository extends JpaRepository<DcAuthRep, Long>, JpaSpecificationExecutor<DcAuthRep> {

    default Optional<DcAuthRep> findByCaseNum(Long caseNum) {
        Specification<DcAuthRep> caseNumSpec = (root, query, cb) -> {
            return cb.equal(root.get(DcAuthRep_.caseNum), caseNum);
        };
        Specification<DcAuthRep> effectiveDatesSpec = Specifications.forTimestampedBetweenDates(DcAuthRep_.EFF_BEGIN_DT, DcAuthRep_.EFF_END_DT);
        return findOne(Specification.where(effectiveDatesSpec).and(caseNumSpec));
    }
}
