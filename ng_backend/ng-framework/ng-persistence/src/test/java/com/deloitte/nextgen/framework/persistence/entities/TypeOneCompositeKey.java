package com.deloitte.nextgen.framework.persistence.entities;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class TypeOneCompositeKey implements Serializable {
    private String title;

    private Long seqNum;

}
