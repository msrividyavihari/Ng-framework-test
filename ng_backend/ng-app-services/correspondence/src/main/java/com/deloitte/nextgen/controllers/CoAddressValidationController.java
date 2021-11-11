package com.deloitte.nextgen.controllers;


import com.deloitte.nextgen.dto.entities.CoAddressDTO;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.CoAddressValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/correspondence/address/")
public class CoAddressValidationController {

    @Autowired
    CoAddressValidationService coAddressValidationService;

    @PostMapping(value = {"/validateAddress"})
    public ResponseEntity<ApiResponse<List<CoAddressDTO>>> validateCoAddress(@RequestBody CoAddressDTO addressDTO) throws Exception {
        List<CoAddressDTO>  coAddressResponseDTO = coAddressValidationService.validateAddress(addressDTO);
        if(!coAddressResponseDTO.isEmpty()) {
            return ApiResponse.success(100).data(coAddressResponseDTO);
        } else {
            return ApiResponse.error(103, "No records found").notify(true).data(null);
        }
    }


}
