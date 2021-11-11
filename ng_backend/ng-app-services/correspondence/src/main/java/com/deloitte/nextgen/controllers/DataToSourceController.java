package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.entities.DataToSourceDTO;
import com.deloitte.nextgen.service.DataToSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/correspondence/data-to-source/")
public class DataToSourceController {

    @Autowired
    DataToSourceService dataToSourceService;

    @PostMapping("/save-address")
    public ResponseEntity<byte[]> saveAddressToFile(@RequestBody DataToSourceDTO dataToSourceDTO) throws Exception {
        return dataToSourceService.saveToFile(dataToSourceDTO);
    }

    @PostMapping("/save-edata")
    public ResponseEntity<byte[]> saveEDataToFile(@RequestBody DataToSourceDTO dto) throws Exception {
        return dataToSourceService.saveEDataToFile(dto);
    }
}
