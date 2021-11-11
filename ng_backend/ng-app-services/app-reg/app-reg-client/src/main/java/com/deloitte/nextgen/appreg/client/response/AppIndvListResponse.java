package com.deloitte.nextgen.appreg.client.response;

import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;


import lombok.*;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppIndvListResponse {
    private String indvName;
    @Convert(converter = ActiveConverter.class)
    private Active headOfHousehold;
    @NotNull
    private Long indvId;
    private int age;
}
