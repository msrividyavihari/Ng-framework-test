package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class CoRequestRecipientsId implements Serializable {
    private Long coReqSeq;
    private Long coRptSeq;
}
