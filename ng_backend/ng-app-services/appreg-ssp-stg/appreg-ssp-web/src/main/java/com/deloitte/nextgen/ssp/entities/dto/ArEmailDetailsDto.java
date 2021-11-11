package com.deloitte.nextgen.ssp.entities.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ArEmailDetailsDto {
    private Long emailSeqNum;
    @NotNull
    private String appNum;
    private String emailTypeCd;
    private String email;
    private String emailComments;
    private String emailSrcTyp;
}
