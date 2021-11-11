package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class CoRequestHistoryDetailId implements Serializable {
    private Long coReqSeq;
    private Long coDetSeq;
}
