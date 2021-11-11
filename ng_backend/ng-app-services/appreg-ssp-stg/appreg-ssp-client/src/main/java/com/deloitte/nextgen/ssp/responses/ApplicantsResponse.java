package com.deloitte.nextgen.ssp.responses;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ApplicantsResponse {
    @NotNull
    private Long indvId;
    private String primaryApplicantSw;
    private String includeApplicantSw;
    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String lastName;
    private String sufxName;
    private Character gender;
    private int age;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp dob;
    private Long ssn;
    @NotEmpty
    private String race;
    @NotEmpty
    private String ethnicity;
    private String aliasSw;
    private String aliasFirstName;
    private String aliasMiddleName;
    private String aliasLastName;
    private String aliasSuffix;
    private Character aliasGender;
    private String interpreterSw;
    private String primaryLanguage;
    private String specificPrimaryLanguage;
    private String accommodationSw;
    private String typeAccommodation;
    private Character authRepresentativeSw;
    private String ebtcardSw;
    private Character registervoteSw;
    private Character indvStatusSw;
}