package com.deloitte.nextgen.appreg.client.entities;

import lombok.*;

import javax.validation.constraints.Digits;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ArPhnDetailsDto {
    private Long phnSeqNum;
    private String appNum;
    private String phnTypeCd;
    @Digits(integer = 10, fraction = 0)
    private String phnNum;
    private String phnComments;
    private String phoneExtn;
    private String phoneSrcTyp;
}
