package com.deloitte.nextgen.dto.entities;

import lombok.*;

@Data
@NoArgsConstructor
public class CoManualFieldsDTO {
    private Long deElementId;
    private String deElementType;
    private String docId;
    private Long fieldOrderNum;
    private String refTableName;
    private Character requiredSw;
    private Long seqNum;
    private String templateId;
    private String x2Coord;
    private String xCoord;
    private String y2Coord;
    private String yCoord;
}
