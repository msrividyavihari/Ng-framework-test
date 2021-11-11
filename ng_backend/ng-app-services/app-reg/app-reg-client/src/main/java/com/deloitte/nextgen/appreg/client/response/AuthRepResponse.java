package com.deloitte.nextgen.appreg.client.response;

import com.deloitte.nextgen.appreg.client.entities.ArAppAddrDto;
import com.deloitte.nextgen.appreg.client.entities.ArEmailDetailsDto;
import com.deloitte.nextgen.appreg.client.entities.ArPhnDetailsDto;
import com.deloitte.nextgen.appreg.client.request.ArApplicationForAidReqAndResp;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class AuthRepResponse {
    @NotNull
    private String appNum;
    ArApplicationForAidReqAndResp arApplicationForAid;
    @Valid
    List<ArPhnDetailsDto> arPhoneList;
    List<ArEmailDetailsDto> arEmailList;
    ArAppAddrDto arAppAddrAA;
    private String source;
}
