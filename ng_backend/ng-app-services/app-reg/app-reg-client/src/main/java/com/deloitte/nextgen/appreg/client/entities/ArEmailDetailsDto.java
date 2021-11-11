package com.deloitte.nextgen.appreg.client.entities;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ArEmailDetailsDto {
    private Long emailSeqNum;
    private String appNum;
    private String emailTypeCd;
    private String email;
    private String emailComments;
    private String emailSrcTyp;
}
