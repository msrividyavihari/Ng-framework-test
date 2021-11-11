package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class DcCaseProgramId implements Serializable {
    private Long caseNum;
    private String progCd;
    private String priorMedicaidCd;
    private Long priorMaSeqNum;
//    private Timestamp effBeginDt;
}
