package com.deloitte.nextgen.dto.entities;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class ClientCommunicationResponse {
    private Boolean flag;
    private ResponseEntity<byte[]> responseEntity;
}
