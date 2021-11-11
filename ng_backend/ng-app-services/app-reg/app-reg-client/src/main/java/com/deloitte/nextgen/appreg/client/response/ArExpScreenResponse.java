package com.deloitte.nextgen.appreg.client.response;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class ArExpScreenResponse {
    @NotNull
    private String appNum;
    private String questCd;
    private String response;
    private Date createDto;
}
