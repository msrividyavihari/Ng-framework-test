package com.deloitte.nextgen.dc.common.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameSearchDto {
    @Size(min=3)
    private String firstName;
    @Size(min=3)
    private String lastName;

    public static NameSearchDto withFirstAndLastName(String firstName, String lastName){
       return new NameSearchDto(firstName, lastName);
    }

    public static List<NameSearchDto> withFirstOrLastName(String firstName){
        List<NameSearchDto> names = new ArrayList<>();
        names.add(withFirstName(firstName));
        names.add(withLastName(firstName));
        return names;
    }

    public static NameSearchDto withFirstName(String firstName){
        return new NameSearchDto(firstName, null);
    }

    public static NameSearchDto withLastName(String lastName){
        return new NameSearchDto(null, lastName);
    }

    public boolean isFirstNameExists() {
        return this.getFirstName()!=null && !this.getFirstName().trim().isEmpty();
    }

    public boolean isLastNameExists(){
        return this.getLastName()!=null && !this.getLastName().trim().isEmpty();
    }
}
