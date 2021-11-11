package com.deloitte.nextgen.ssp.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AppIndvConflictPanelResponse {
    @NotNull
    private String appNum;
    @NotNull
    private String indvId;
    private String source;
    @JsonProperty("ssn")
    private String ssnNum;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @JsonFormat(pattern = "MM/dd/yyyy")
    @JsonProperty("dob")
    private java.sql.Timestamp brthDt;
    @JsonProperty("gender")
    private Character sexInd;
}
