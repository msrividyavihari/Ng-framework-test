package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.entities.ClientCommunicationDTO;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.ClientCommunicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author rahmaurya
 */

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping("/api/communication")
public class ClientCommunicationController {

    @Autowired
    ClientCommunicationService clientCommunicationService;

    @PostMapping("/electronicTrigger")
    public ResponseEntity<ApiResponse<ClientCommunicationDTO>> createElectronicTrigger(@RequestBody ClientCommunicationDTO clientComm)  {
        ClientCommunicationDTO str = clientCommunicationService.triggerElectronicCommunication(clientComm);
        return  ApiResponse.success(100).data(str);
//        return null;
    }

}
