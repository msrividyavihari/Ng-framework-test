package com.deloitte.nextgen.appreg.client.response;


import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SnapExpeditedResponse {
    @NotNull
    private String appNum;
    private Character migFarmWrkrSw;
    private Long moGrIncmAmt;
    private Long lqdAsetAmt;
    private Long moRentMrtgAmt;
}
