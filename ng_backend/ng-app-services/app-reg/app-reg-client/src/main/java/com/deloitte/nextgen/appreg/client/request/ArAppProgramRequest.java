package com.deloitte.nextgen.appreg.client.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class ArAppProgramRequest {
    @NotNull
    String appNum;
    List<String> programCd;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private java.sql.Timestamp requestDt;
}
