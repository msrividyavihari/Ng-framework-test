package com.deloitte.nextgen.entities.composite;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class CoNodRequestDetailId implements Serializable {
    private Long caseNum;
    private Long coNodSeq;
    private Long coReqSeq;
}
