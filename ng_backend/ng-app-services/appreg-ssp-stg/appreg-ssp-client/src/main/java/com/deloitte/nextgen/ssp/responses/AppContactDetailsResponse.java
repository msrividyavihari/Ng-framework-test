package com.deloitte.nextgen.ssp.responses;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class AppContactDetailsResponse {
    ArApplicationForAidResponse arApplicationForAid;
    List<ArPhnDetailsResponse> arPhoneList;
    List<ArEmailDetailsResponse> arEmailList;
    ArAppAddrResponse arAppAddrPA;
    ArAppAddrResponse arAppAddrMA;
    private String source;
}
