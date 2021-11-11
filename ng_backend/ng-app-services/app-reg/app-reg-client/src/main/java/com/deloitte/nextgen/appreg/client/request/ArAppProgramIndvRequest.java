package com.deloitte.nextgen.appreg.client.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class ArAppProgramIndvRequest {
    @NotNull
    String appNum;
    @NotNull
    Long indvId;
    List<String> programCd;
    List<String> priorMedicaidCd;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp requestDt;
}
