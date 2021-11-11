package com.deloitte.nextgen.dto.entities;

import lombok.*;

@Data
@NoArgsConstructor
public class ViewHistoryDetailDTO {
    private Long t2CoReqSeq;
    private Long coDetSeq;
    private Long rptSeq;
    private Long coRecipientSelected;
    private Long disDocMstrSeqNum;
}
