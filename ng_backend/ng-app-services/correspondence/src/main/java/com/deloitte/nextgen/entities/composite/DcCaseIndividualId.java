package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class DcCaseIndividualId implements Serializable {
    private Long indvId;
    private Long caseNum;
    private Timestamp effBeginDt;
}
