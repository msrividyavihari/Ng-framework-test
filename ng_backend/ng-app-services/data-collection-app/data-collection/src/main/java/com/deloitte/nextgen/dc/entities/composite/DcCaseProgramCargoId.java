package com.deloitte.nextgen.dc.entities.composite;

import java.io.Serializable;
import java.util.Date;

public class DcCaseProgramCargoId implements Serializable {
    private  long caseNum;
    private String progCd;
    private String priorMedicaidCd;
    private long priorMaSeqNum;
    private Date effBeginDt;

}
