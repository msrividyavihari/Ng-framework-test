package com.deloitte.nextgen.entities.composite;

import javax.persistence.Column;
import java.io.Serializable;

public class DcCaseProgramIndvId implements Serializable {
    @Column(name = "CASE_NUM")
    private Long caseNum;
    @Column(name = "INDV_ID")
    private Long indvId;
    @Column(name = "PROG_CD")
    private String progCd;
    @Column(name = "PRIOR_MEDICAID_CD")
    private String priorMedicaidCd;
    @Column(name = "PRIOR_MA_SEQ_NUM")
    private Long priorMaSeqNum;
//    @Column(name = "EFF_BEGIN_DT")
//    private java.sql.Timestamp effBeginDt;
}
