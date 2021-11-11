package com.deloitte.nextgen.ha.common.repository;


import com.deloitte.nextgen.ha.entity.AmRequestDetails;
import com.deloitte.nextgen.ha.entity.AmRequestDetails_;
import org.springframework.data.jpa.domain.Specification;
import com.deloitte.nextgen.ha.persistence.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;


import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Repository
public interface AmRequestDetailsRepository extends JpaRepository<AmRequestDetails, BigInteger>, JpaSpecificationExecutor<AmRequestDetails> {

    default List<AmRequestDetails> findByAplNumIn(@NonNull Collection<BigInteger> AplNums) {

        Specification<AmRequestDetails> aplNumInSpec = Specifications.in(AmRequestDetails_.aplNum, AplNums);
        return findAll(Specification.where(aplNumInSpec));
    }

    default List<AmRequestDetails> findByIndvId(@NonNull BigInteger indvId) {

        Specification<AmRequestDetails> individualIdSpec = (root, query, cb) -> {
            return cb.equal(root.get(AmRequestDetails_.indvId), indvId);
        };
        return  findAll(Specification.where(individualIdSpec));
    }

    default List<AmRequestDetails> findByAppealNum(@NonNull BigInteger appealNum) {

        Specification<AmRequestDetails> appealNumSpec = (root, query, cb) -> {
            return cb.equal(root.get(AmRequestDetails_.aplNum), appealNum);
        };
        return  findAll(Specification.where(appealNumSpec));
    }

}
