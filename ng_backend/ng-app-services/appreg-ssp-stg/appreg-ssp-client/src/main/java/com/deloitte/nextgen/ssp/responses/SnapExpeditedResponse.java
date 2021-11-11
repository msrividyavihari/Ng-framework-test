package com.deloitte.nextgen.ssp.responses;


import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SnapExpeditedResponse {
    @NotNull
    private String appNum;
    private Character migFarmWrkrSw;
    private Double moGrIncmAmt;
    private Double lqdAsetAmt;
    private Double moRentMrtgAmt;
}
