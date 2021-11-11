package com.deloitte.nextgen.ssp.entities.vo;


import com.deloitte.nextgen.ssp.entities.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
public class SSPDataRequest {
    private T1002AppDtlDto appDtl;
    private T1001AppRqstDto appRqst;
    private List<T1004AppIndvDto> appIndv;
    private T1053AppProgramDto appProgram;
    private T1065AppAuthRepDto authRep;
}
