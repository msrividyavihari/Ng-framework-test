package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.ClientCommunicationDTO;
import com.deloitte.nextgen.service.ClientCommunicationService;
import com.deloitte.nextgen.util.CoConstants;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientCommunicationServiceImpl implements ClientCommunicationService {

    public ClientCommunicationDTO triggerElectronicCommunication(ClientCommunicationDTO clientComm){

        if(clientComm.getPreferredCommunication() != null) {
            if (CoConstants.COMM_EMAIL.equalsIgnoreCase(clientComm.getPreferredCommunication())){
                // String emailRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";  //Original email regex
                String emailRegex = "^[A-Za-z0-9+_.-]+@gmail.com$";
                Pattern pattern = Pattern.compile(emailRegex);
                Matcher matcher = pattern.matcher(clientComm.getEmailId());

                if(matcher.matches() == Boolean.FALSE) {
                    clientComm.setClientCommResponse(CoConstants.FAILURE);
                    clientComm.setReasonForFailure(CoConstants.FAILURE_INVALID_EMAIL);
                } else {
                    clientComm.setClientCommResponse(CoConstants.SUCCESS);
                }

            }else if (CoConstants.COMM_MOBILE.equalsIgnoreCase(clientComm.getPreferredCommunication())){
                // String emailRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";  //Original email regex
                String mobileRegex = "^[+0-9]+444$";    //Matches the last 3 digit to be '444'
                Pattern pattern = Pattern.compile(mobileRegex);
                Matcher matcher = pattern.matcher(clientComm.getCellPhoneNumber());

                if(matcher.matches() == Boolean.FALSE) {
                    clientComm.setClientCommResponse(CoConstants.FAILURE);
                    clientComm.setReasonForFailure(CoConstants.FAILURE_INVALID_MOBILE);
                } else {
                    clientComm.setClientCommResponse(CoConstants.SUCCESS);
                }
            }
        } else {
            clientComm.setClientCommResponse(CoConstants.FAILURE);
            clientComm.setReasonForFailure(CoConstants.EMAIL_SERVICE_UNAVAILABLE);
        }

        return clientComm;
    }
}
