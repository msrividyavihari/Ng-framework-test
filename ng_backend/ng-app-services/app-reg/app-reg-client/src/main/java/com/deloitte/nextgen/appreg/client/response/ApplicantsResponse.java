package com.deloitte.nextgen.appreg.client.response;

import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import com.deloitte.nextgen.appreg.client.enums.Active;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Convert;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @NotNull
    private Character gender;
    private int age;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp dob;
    private Long ssn;
    @NotEmpty
    private String race;
    @NotEmpty
    private String ethnicity;
    @Convert(converter = ActiveConverter.class)
    private Active aliasSw;
    private String aliasFirstName;
    private String aliasMiddleName;
    private String aliasLastName;
    private String aliasSuffix;
    private Character aliasGender;
    @Convert(converter = ActiveConverter.class)
    private Active interpreterSw;
    private String primaryLanguage;
    private String specificPrimaryLanguage;
    @Convert(converter = ActiveConverter.class)
    private Active accommodationSw;
    private String typeAccommodation;
    @Convert(converter = ActiveConverter.class)
    private Active authRepresentativeSw;
    @Convert(converter = ActiveConverter.class)
    private Active ebtcardSw;
    @Convert(converter = ActiveConverter.class)
    private Active registervoteSw;
    private Character indvStatusSw;
    private Long aliasIndSeqNum;
}
