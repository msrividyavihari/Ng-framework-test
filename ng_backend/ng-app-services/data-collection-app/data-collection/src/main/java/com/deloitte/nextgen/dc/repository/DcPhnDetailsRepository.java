package com.deloitte.nextgen.dc.repository;

import com.deloitte.nextgen.dc.entities.DcPhnDetails;
import com.deloitte.nextgen.dc.entities.DcPhnDetails_;
import com.deloitte.nextgen.dc.entities.DcPhnXref;
import com.deloitte.nextgen.dc.entities.DcPhnXref_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Repository
public interface DcPhnDetailsRepository extends JpaRepository<DcPhnDetails, Long>, JpaSpecificationExecutor<DcPhnDetails> {

    default List<DcPhnDetails> findPhoneDetailsByPhoneSrcId(final Long phnSrcId, @Nullable String phoneSrcTyp) {
        Specification<DcPhnDetails> phoneSrcIdSpec = (root, query, cb) -> {
            Root<DcPhnXref> dcPhnXrefRoot = query.from(DcPhnXref.class);
            Predicate phnSeqNumPredicate = cb.equal(dcPhnXrefRoot.get(DcPhnXref_.phnSeqNum), root.get(DcPhnDetails_.phnSeqNum));
            Predicate phnSrcIdPredicate = cb.equal(dcPhnXrefRoot.get(DcPhnXref_.phnSrcId), phnSrcId);
            Predicate phoneSrcTypPredicate = phoneSrcTyp!=null? cb.equal(root.get(DcPhnDetails_.phoneSrcTyp), phoneSrcTyp): cb.conjunction();
            return cb.and(phnSeqNumPredicate, phnSrcIdPredicate, phoneSrcTypPredicate);
        };
        return findAll(phoneSrcIdSpec);
    }

    default Optional<DcPhnDetails> findAuthRepPhoneDetailsByCaseNum(final Long caseNum) {
        List<DcPhnDetails> phoneDetails = findPhoneDetailsByPhoneSrcId(caseNum, "AA");
        return phoneDetails.stream().findFirst();
    }
}
