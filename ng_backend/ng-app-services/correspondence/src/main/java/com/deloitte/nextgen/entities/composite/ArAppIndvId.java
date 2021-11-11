package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class ArAppIndvId implements Serializable {
    private String appNum;
    private Long indvId;
}
