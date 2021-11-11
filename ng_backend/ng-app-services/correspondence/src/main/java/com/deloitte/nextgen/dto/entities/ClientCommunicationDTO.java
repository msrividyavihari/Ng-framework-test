package com.deloitte.nextgen.dto.entities;

import lombok.*;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCommunicationDTO {
    private String emailId;
    private String cellPhoneNumber;
    private String preferredCommunication;
    private boolean optInIndicator;
    private boolean goGreenIndicator;
    private boolean emailDelivered;
    private boolean smsDelivered;
    private String reasonForFailure;
    private String clientCommResponse;
}
