package com.deloitte.nextgen.ha.create.dto;

import com.deloitte.nextgen.dc.common.dto.AddressDto;
import com.deloitte.nextgen.ha.appeals.dto.ContactInfoDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
public class FilingCreateAppealDto {


    private BigInteger primaryAppealNum;
    @NonNull
    @NotNull
    private BigInteger primaryAppellant;

    @Setter(AccessLevel.NONE)
    private final List<BigInteger> secondaryAppellants = new ArrayList<>();

    private ContactInfoDto contactInfo;

    private LocalDate filedDate;
    private String filingMethod;
    private BigInteger caseNum;

}
