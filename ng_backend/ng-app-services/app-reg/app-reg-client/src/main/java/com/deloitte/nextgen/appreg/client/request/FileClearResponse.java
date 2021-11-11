package com.deloitte.nextgen.appreg.client.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class FileClearResponse {

    private Long indvId;
    private String firstName;
    private String midName;
    private String lastName;
    private String sufxName;
    private String raceCd;
    private String ethnicityCd;
    private java.sql.Timestamp dobDt;
    private Character gender;
    private String aliasFirstName;
    private String aliasMidName;
    private String aliasLastName;
    private String relFirstName;
    private String relMidName;
    private String relLastName;
    private String relationshipTypeCd;
    private Long ssn;


}
