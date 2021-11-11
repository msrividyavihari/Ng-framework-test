package com.deloitte.nextgen.appreg.client.entities;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PcRelationShipDto {
    private String name;
    private String relation;
}
