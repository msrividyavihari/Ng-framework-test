package com.deloitte.nextgen.demo.client.repository.impl;

import com.deloitte.nextgen.demo.client.entities.DcAddress;
import com.deloitte.nextgen.demo.client.entities.QDcAddress;
import com.deloitte.nextgen.demo.client.entities.QDcIndividual;
import com.deloitte.nextgen.demo.client.generated.repository.DcAddressRepository;
import com.deloitte.nextgen.demo.client.repository.PersonAddressRepository;
import com.deloitte.nextgen.demo.client.response.DcAddressResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nishmehta
 * @since 19/07/2021 2:33 PM
 */

@Repository
public class PersonAddressRepositoryImpl implements PersonAddressRepository {

    @Autowired
    private JPQLQueryFactory queryFactory;

    @Autowired
    private DcAddressRepository addressRepository;

    @Override
    public List<DcAddressResponse> findAllAddressByPersonId(Long personId) {

        QDcIndividual person = QDcIndividual.dcIndividual;
        QDcAddress address = QDcAddress.dcAddress;
        return queryFactory.select(address.addressLineOne, address.addressLineTwo,
                address.city, address.state, address.zipCode)
                .from(person)
                .innerJoin(address)
                .on(person.individualId.eq(address.dcIndividual.individualId))
                .where(person.individualId.eq(personId))
                .fetch()
                .stream()
                .map(tuple -> DcAddressResponse.builder()
                        .addressLineOne(tuple.get(address.addressLineOne))
                        .addressLineTwo(tuple.get(address.addressLineTwo))
                        .city(tuple.get(address.city))
                        .state(tuple.get(address.state))
                        .zipCode(tuple.get(address.zipCode))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<DcAddress> findAllAddressesByCityCode(Long cityCode) {
        QDcAddress address = QDcAddress.dcAddress;

        Iterable<DcAddress> addresses = addressRepository.findAll(address.city.eq(cityCode));

        return IterableUtils.toList(addresses);
    }
}
