package com.deloitte.nextgen.dc.repository;

import com.deloitte.nextgen.dc.entities.DcCaseProgram;
import com.deloitte.nextgen.dc.entities.DcCaseProgram_;
import com.deloitte.nextgen.framework.persistence.Specifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DcCaseProgramRepository extends JpaRepository<DcCaseProgram, Long>, JpaSpecificationExecutor<DcCaseProgram> {

    List<DcCaseProgram> findByCaseNum(Long caseNum);

    default List<DcCaseProgram> findByCaseNumIn(@NonNull Collection<Long> caseNums) {
        Specification<DcCaseProgram> caseNumInSpec = Specifications.in(DcCaseProgram_.caseNum, caseNums);
        Specification<DcCaseProgram> effectiveDatesSpec = Specifications.forTimestampedBetweenDates(DcCaseProgram_.EFF_BEGIN_DT, DcCaseProgram_.EFF_END_DT);
        return findAll(Specification.where(caseNumInSpec).and(effectiveDatesSpec));
    }
}
