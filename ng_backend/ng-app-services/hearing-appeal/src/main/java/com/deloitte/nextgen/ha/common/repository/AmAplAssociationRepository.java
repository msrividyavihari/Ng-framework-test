package com.deloitte.nextgen.ha.common.repository;


import com.deloitte.nextgen.ha.entity.AmAplAssociation;
import com.deloitte.nextgen.ha.entity.AmRepresentativeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface AmAplAssociationRepository extends JpaRepository<AmAplAssociation, BigInteger> {

    Optional<AmAplAssociation> findById(BigInteger bigInteger);
}
