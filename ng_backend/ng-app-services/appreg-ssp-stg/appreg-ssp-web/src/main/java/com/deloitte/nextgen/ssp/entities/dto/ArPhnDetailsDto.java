package com.deloitte.nextgen.ssp.entities.dto;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ArPhnDetailsDto {
    private Long phnSeqNum;
    @NotNull
    private String appNum;
    private String phnTypeCd;
    @Digits(integer=10, fraction=0)
    private String phnNum;
    private String phnComments;
    private String phoneExtn;
    private String phoneSrcTyp;
}
