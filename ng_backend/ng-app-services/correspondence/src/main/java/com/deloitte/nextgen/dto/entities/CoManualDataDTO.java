package com.deloitte.nextgen.dto.entities;


import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class CoManualDataDTO {

    private Long coReqSeq;
    private String fieldContent;
    private Long fieldOrderNum;
    private Long seqNum;

}
