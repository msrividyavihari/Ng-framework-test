package com.deloitte.nextgen.appreg.client.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class AppSearchResponse {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @JsonFormat(pattern = "M/d/YY")
    private Date appRecvdDt;
    private String source;
    @NotNull
    private String appNum;
    private String appStatCd;
    private String flag;
}
