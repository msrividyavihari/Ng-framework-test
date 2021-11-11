package com.deloitte.nextgen.ha.create.dto;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class FilingUpdateAppealDto {

    private BigInteger appealNum;
    private String programCd;
    private String typeCd;
    private String reasonCd;

    private BigInteger caseNum;

    private Boolean caseAuthRep;
    private List<AuthRepDto> associatedAuthReps = new ArrayList<>();

    private String preferredContactTimeCode;

    private String fillingNotes;
}
