package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class CoMasterId implements Serializable {
    private String docId;
    private Timestamp effBeginDt;
}
