package com.deloitte.nextgen.appreg.client.request;

import com.deloitte.nextgen.appreg.client.entities.PcRelationShipDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
public class DcIndvFileClearReqAndResp {
    @NotNull
    private Long indvId;
    private Long ssn;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Timestamp dobDt;
    private Integer age;
    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
    private String sufxName;
    private String midName;
    @NotEmpty
    private String raceCd;
    @NotEmpty
    private String ethnicityCd;
    @NotEmpty
    private Character gender;
    private Set<String> alisName = new HashSet<>();
    private Set<PcRelationShipDto> relationShips = new HashSet<>();
    private Long score;
}
