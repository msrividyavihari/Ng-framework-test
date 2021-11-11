package com.deloitte.nextgen.demo.client.resource;

import com.deloitte.nextgen.demo.client.response.DcAddressResponse;
import com.deloitte.nextgen.demo.client.service.PersonAddressService;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nishmehta
 * @since 15/07/2021 12:40 PM
 */

@RestController
@RequestMapping("/")
public class PersonAddressResource {

    @Autowired
    private final PersonAddressService personAddressService;

    public PersonAddressResource(PersonAddressService personAddressService) {
        this.personAddressService = personAddressService;
    }

    @GetMapping(path = "v1/persons/{id}/addresses")
    public ResponseEntity<ApiResponse<List<DcAddressResponse>>> findAllAddressByPersonId(@NotNull @PathVariable Long id) {
        return ApiResponse.success(200).data(personAddressService.findAllAddressByPersonId(id));
    }

    @GetMapping(path = "v1/addresses/city/{code}")
    public ResponseEntity<ApiResponse<List<DcAddressResponse>>> findAllAddressesByCityCode(@NotNull @PathVariable Long code) {
        return ApiResponse.success(200).data(personAddressService.findAllAddressesByCityCode(code));
    }

}
