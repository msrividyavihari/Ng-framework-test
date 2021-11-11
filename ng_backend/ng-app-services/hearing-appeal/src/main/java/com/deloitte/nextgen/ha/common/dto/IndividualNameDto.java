package com.deloitte.nextgen.ha.common.dto;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class IndividualNameDto {
    @NonNull
    private String lastName;
    @NonNull
    private String firstName;
    private String suffixName;
    private String midName;
}
