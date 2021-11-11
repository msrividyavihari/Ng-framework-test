package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class MoOfficesId implements Serializable {
    private Long officeNum;
    private Timestamp effBeginDt;
}
