package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.CoAddressDTO;

import java.util.List;

public interface CoAddressValidationService {

    List<CoAddressDTO> validateAddress(CoAddressDTO addressDTO);

}
