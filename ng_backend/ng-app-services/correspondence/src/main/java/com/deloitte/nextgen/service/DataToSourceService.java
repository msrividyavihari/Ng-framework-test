package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.DataToSourceDTO;
import org.springframework.http.ResponseEntity;


public interface DataToSourceService {
    ResponseEntity<byte[]> saveToFile(DataToSourceDTO dto) throws Exception;
    ResponseEntity<byte[]> saveEDataToFile(DataToSourceDTO dto) throws Exception;
}
