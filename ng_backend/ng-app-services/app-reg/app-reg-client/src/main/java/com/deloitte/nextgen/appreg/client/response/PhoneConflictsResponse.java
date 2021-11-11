package com.deloitte.nextgen.appreg.client.response;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PhoneConflictsResponse {
    private String panelName;
    @NotNull
    private String appNum;
    private String primPhnTypeCd;
    @Digits(integer=10, fraction=0)
    private String primPhnNum;
    private String primPhnComments;
    private String primPhoneExtn;
    private String primPhoneSrcTyp;
    private String wrkPhnTypeCd;
    @Digits(integer=10, fraction=0)
    private String wrkPhnNum;
    private String wrkPhnComments;
    private String wrkPhoneExtn;
    private String wrkPhoneSrcTyp;
    private String altPhnTypeCd;
    @Digits(integer=10, fraction=0)
    private String altPhnNum;
    private String altPhnComments;
    private String altPhoneExtn;
    private String altPhoneSrcTyp;
}

