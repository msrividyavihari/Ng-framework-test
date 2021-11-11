package com.deloitte.nextgen.demo.client.service.impl;

import com.deloitte.nextgen.demo.client.entities.DcAddress;
import com.deloitte.nextgen.demo.client.mapper.DcAddressReqResMapper;
import com.deloitte.nextgen.demo.client.repository.PersonAddressRepository;
import com.deloitte.nextgen.demo.client.repository.TestJpaRepo;
import com.deloitte.nextgen.demo.client.response.DcAddressResponse;
import com.deloitte.nextgen.demo.client.service.PersonAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nishmehta
 * @since 19/07/2021 2:29 PM
 */

@Service
public class PersonAddressServiceImpl implements PersonAddressService {

    @Autowired
    private PersonAddressRepository personAddressRepository;

    @Autowired
    private DcAddressReqResMapper dcAddressReqResMapper;

    @Autowired
    TestJpaRepo testJpaRepo;


    @Override
    public List<DcAddressResponse> findAllAddressByPersonId(Long personId) {
        return personAddressRepository.findAllAddressByPersonId(personId);
    }

    @Override
    public List<DcAddressResponse> findAllAddressesByCityCode(Long cityCode) {

        List<DcAddress> dcAddresses = personAddressRepository.findAllAddressesByCityCode(cityCode);

        return dcAddressReqResMapper.toResponse(dcAddresses);
    }

}
