package com.deloitte.nextgen.entities.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class MoOfficeAddressesId implements Serializable {
    private Long officeNum;
    private String addressTypeCd;
}
