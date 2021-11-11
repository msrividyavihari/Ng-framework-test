package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class DcCaseAddressesId implements Serializable {
    private Long caseNum;
    private Long caseAddrSeqNum;
//    private Timestamp effBeginDt;
}
