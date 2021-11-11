package com.deloitte.nextgen.demo.client.repository;

import com.deloitte.nextgen.demo.client.entities.DcAddress;
import com.deloitte.nextgen.demo.client.response.DcAddressResponse;

import java.util.List;

/**
 * @author nishmehta
 * @since 19/07/2021 2:32 PM
 */

public interface PersonAddressRepository {

    List<DcAddressResponse> findAllAddressByPersonId(Long personId);

    List<DcAddress> findAllAddressesByCityCode(Long cityCode);

}
