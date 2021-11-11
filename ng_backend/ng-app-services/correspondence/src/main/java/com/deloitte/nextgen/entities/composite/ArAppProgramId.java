package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class ArAppProgramId implements Serializable {
    private String appNum;
    private String programCd;
    private String priorMedicaidCd;
}
