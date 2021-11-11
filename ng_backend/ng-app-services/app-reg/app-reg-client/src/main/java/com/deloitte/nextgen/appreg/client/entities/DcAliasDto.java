package com.deloitte.nextgen.appreg.client.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@NoArgsConstructor
@Data
public class DcAliasDto {
    private Long indvId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Timestamp dobDt;
    private String lastName;
    private String firstName;
    private String sufxName;
    private String midName;
    private Character gender;
    private Character piIndvSw;
    private Character primarySw;
}
