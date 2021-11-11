package com.deloitte.nextgen.dto.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
public class CoEDMSMetadata {
    private long caseNumber;
    private long applicationNumber;
    private long clientId;
    private long referenceNumber;
    private String transactionNumber;
    private String documentType;
    private String documentReceivedBeginDate;
    private String documentReceivedEndDate;

}
