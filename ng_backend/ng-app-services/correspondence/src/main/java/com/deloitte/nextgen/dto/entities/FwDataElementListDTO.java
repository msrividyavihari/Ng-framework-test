package com.deloitte.nextgen.dto.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Component
public class FwDataElementListDTO {

    private String deAuthUser;

    private String deDefaultValue;

    private Long deElementId;

    private String deLabelText;

    private Timestamp deLastChanged;

    private Long deMandatory;

    private String deNotes;

    private String dePackage;

    private String deScreenElementName;

    private Integer deWidth;

    private String langCd;

    private Integer rdRefId;
}
