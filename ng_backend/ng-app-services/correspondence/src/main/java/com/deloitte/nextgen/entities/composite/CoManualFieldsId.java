package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class CoManualFieldsId implements Serializable {
    private String docId;
    private Long seqNum;
    private String templateId;
}
