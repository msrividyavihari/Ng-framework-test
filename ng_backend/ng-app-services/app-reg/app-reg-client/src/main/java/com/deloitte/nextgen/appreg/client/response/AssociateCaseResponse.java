package com.deloitte.nextgen.appreg.client.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class AssociateCaseResponse {
    @JsonFormat(pattern = "MM/dd/YY")
    private Date lastUpdated;
    private String hoh;
    private String programs;
    private String status;
    @NotNull
    private Long caseNum;
    private String office;
}
