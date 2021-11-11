package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.ClientCommunicationDTO;

public interface ClientCommunicationService {
    ClientCommunicationDTO triggerElectronicCommunication(ClientCommunicationDTO clientComm);
}
