package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class MoEmployeeCasesId implements Serializable {
    private Long caseNum;
    private String programCd;
    private Timestamp assignBeginDt;
    private Long sequenceNum;
}
