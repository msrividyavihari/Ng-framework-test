package com.deloitte.nextgen.ssp.responses;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class AuthRepResponse {
    @NotNull
    private String appNum;
    ArApplicationForAidResponse arApplicationForAid;
    List<ArPhnDetailsResponse> arPhoneList;
    List<ArEmailDetailsResponse> arEmailList;
    ArAppAddrResponse arAppAddrAA;
    private String source;
}
