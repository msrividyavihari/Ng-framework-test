package com.deloitte.nextgen.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class FormData {
    private String caseName;
    private Long caseNum;
    private String clientName;
    private Long clientId;
    private String SSN;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp clientDOB;
    private String caseWorkerID;
    private HeadOfHouse HeadOfHouse;
    private MailingAdd mailingAdd;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp systemDate;
    private MassHealthMedicaid MASSHealthMedicaid;
}
