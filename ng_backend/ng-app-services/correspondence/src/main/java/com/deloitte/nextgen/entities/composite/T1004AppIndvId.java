package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class T1004AppIndvId  implements Serializable {
    private String appNum;
    private Long indvSeqNum;
}
