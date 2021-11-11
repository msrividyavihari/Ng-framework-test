package com.deloitte.nextgen.dc.common.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class NameSearchListDto {

    @NonNull
    @NotEmpty
    private final List<NameSearchDto> names = new ArrayList<>();

    public void addName(String name) {
        if (name == null || name.trim().isEmpty()) return;
        this.getNames().addAll(NameSearchDto.withFirstOrLastName(name.trim()));
    }

    public void addName(String firstName, String lastName) {
        firstName = firstName == null || firstName.trim().isEmpty() ? null: firstName.trim();
        lastName = lastName == null || lastName.trim().isEmpty() ? null: lastName.trim();
        if (lastName == null && firstName == null) return;
        this.getNames().add(NameSearchDto.withFirstAndLastName(firstName, lastName));
    }

    public void addFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) return;
        this.getNames().add(new NameSearchDto(firstName.trim(), null));
    }

    public void addLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) return;
        this.getNames().add(new NameSearchDto(null, lastName.trim()));
    }
}
