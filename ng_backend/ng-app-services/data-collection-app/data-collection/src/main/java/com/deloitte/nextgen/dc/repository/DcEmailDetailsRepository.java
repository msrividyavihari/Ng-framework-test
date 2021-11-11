package com.deloitte.nextgen.dc.repository;

import com.deloitte.nextgen.dc.entities.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public interface DcEmailDetailsRepository extends JpaRepository<DcEmailDetails, Long>, JpaSpecificationExecutor<DcEmailDetails> {

    default List<DcEmailDetails> findEmailDetailsByEmailSrcId(final Long emailSrcId, @Nullable String emailSrcTyp) {
        Specification<DcEmailDetails> emailSrcIdSpec = (root, query, cb) -> {
            Root<DcEmailXref> dcEmailXrefRoot = query.from(DcEmailXref.class);
            Predicate emailSeqNumPredicate = cb.equal(dcEmailXrefRoot.get(DcEmailXref_.emailSeqNum), root.get(DcEmailDetails_.emailSeqNum));
            Predicate emailSrcIdPredicate = cb.equal(dcEmailXrefRoot.get(DcEmailXref_.emailSrcId), emailSrcId);
            Predicate emailSrcTypPredicate = emailSrcTyp!=null? cb.equal(root.get(DcEmailDetails_.emailSrcTyp), emailSrcTyp): cb.conjunction();
            return cb.and(emailSeqNumPredicate, emailSrcIdPredicate, emailSrcTypPredicate);
        };
        return findAll(emailSrcIdSpec);
    }

    default Optional<DcEmailDetails> findAuthRepEmailDetailsByCaseNum(final Long caseNum) {
        List<DcEmailDetails> emailDetails = findEmailDetailsByEmailSrcId(caseNum, "AA");
        return emailDetails.stream().findFirst();
    }

}
