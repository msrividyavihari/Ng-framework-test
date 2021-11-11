package com.deloitte.nextgen.dc.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class IndividualNameDto {
    @NonNull
    @NotBlank
    @Size(min = 2)
    private String lastName;
    @NonNull
    @NotBlank
    @Size(min = 3)
    private String firstName;
    private String suffixName;
    private String midName;
}
