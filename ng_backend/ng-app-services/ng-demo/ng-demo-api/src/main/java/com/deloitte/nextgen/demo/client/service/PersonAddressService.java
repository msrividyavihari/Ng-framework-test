package com.deloitte.nextgen.demo.client.service;

import com.deloitte.nextgen.demo.client.response.DcAddressResponse;

import java.util.List;


/**
 * @author nishmehta
 * @since 19/07/2021 2:30 PM
 */

public interface PersonAddressService {

    List<DcAddressResponse> findAllAddressByPersonId(Long personId);

    List<DcAddressResponse> findAllAddressesByCityCode(Long cityCode);
}
