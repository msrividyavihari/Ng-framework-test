package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class DcAuthRepId implements Serializable {
    private Long caseNum;
    private Long authrepSeqNum;
//    private Timestamp effBeginDt;
}
