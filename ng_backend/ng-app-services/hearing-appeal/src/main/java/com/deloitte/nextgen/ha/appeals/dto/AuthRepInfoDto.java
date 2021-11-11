package com.deloitte.nextgen.ha.appeals.dto;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthRepInfoDto {

    private AuthRepDto caseLevelAuthRep;
    private boolean caseAuthRepIncludedInAppeal;

    @Setter(AccessLevel.NONE)
    private List<AuthRepDto> associatedAuthReps = new ArrayList<>();

    //TODO filling notes and  preferredContactTimeCode; are missing

}
