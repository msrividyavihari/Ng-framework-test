package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class CoManualDataId implements Serializable {
    private Long coReqSeq;
    private Long seqNum;
}
