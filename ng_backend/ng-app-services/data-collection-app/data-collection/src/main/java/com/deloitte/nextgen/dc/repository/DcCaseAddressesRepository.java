package com.deloitte.nextgen.dc.repository;

import com.deloitte.nextgen.dc.entities.DcCaseAddresses;
import com.deloitte.nextgen.dc.entities.DcCaseAddresses_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface DcCaseAddressesRepository extends JpaRepository<DcCaseAddresses, Long>, JpaSpecificationExecutor<DcCaseAddresses> {

    default List<DcCaseAddresses> findByCaseNumAndAddressTypeCd(@NonNull final Long caseNum, final String addrTypeCd) {
        Specification<DcCaseAddresses> specification = (root, query, cb) -> {
            Predicate caseNumEqPredicate = cb.equal(root.get(DcCaseAddresses_.caseNum), caseNum);
            Predicate addrTypeCdPredicate = addrTypeCd != null ? cb.equal(root.get(DcCaseAddresses_.addrTypeCd), addrTypeCd) : cb.conjunction();
            Expression<Timestamp> curTimeStampExpression = cb.currentTimestamp();
            // Don't use SingularAttribute for effBeginDt .. read the comment below using EFF_END_DT
            Path<Timestamp> effBeginDtPath = root.get(DcCaseAddresses_.EFF_BEGIN_DT);
            Predicate effBeginDtPredicate = cb.or(cb.isNull(effBeginDtPath), cb.lessThanOrEqualTo(effBeginDtPath, curTimeStampExpression));
            // TODO:Nishant Using DcCaseAddresses_.effEndDt is throwing NullPointer Exception  since it's extending TypeTwoBaseEntity which already have efftBeginDt and effEndDt,
            //  JPA metamodel was unable to initialize the effEndDt. Fall backing to use String instead of SingularAttribute although we will loose type safety.
            //Path<Timestamp> effEndDtPath = root.get(DcCaseAddresses_.effEndDt);
            Path<Timestamp> effEndDtPath = root.get(DcCaseAddresses_.EFF_END_DT);
            Predicate effEndDtPredicate = cb.or(cb.isNull(effEndDtPath), cb.greaterThanOrEqualTo(effEndDtPath, curTimeStampExpression));

            return cb.and(caseNumEqPredicate, addrTypeCdPredicate, effBeginDtPredicate, effEndDtPredicate);
        };

        return findAll(specification);
    }

    default List<DcCaseAddresses> findByCaseNum(final Long caseNum) {
        return findByCaseNumAndAddressTypeCd(caseNum, null);
    }

    default Optional<DcCaseAddresses> findCaseAddressByCaseNum(final Long caseNum) {
        return findByCaseNumAndAddressTypeCd(caseNum, "PA").stream().findFirst();
    }

    default Optional<DcCaseAddresses> findAuthRepAddressByCaseNum(final Long caseNum) {
        return findByCaseNumAndAddressTypeCd(caseNum, "AA").stream().findFirst();
    }
}
