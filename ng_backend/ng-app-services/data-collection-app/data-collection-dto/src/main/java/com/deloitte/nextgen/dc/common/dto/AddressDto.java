package com.deloitte.nextgen.dc.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class AddressDto {
    private String addrLine1;
    private String addrLine2;
    @NonNull
    private String addrCity;
    @NonNull
    private String addrStateCd;
    @NonNull
    private String addrZip5;
    private String addrZip4;

    private String phoneNum1;
    private String phoneNum2;

    private String addrCountyCd;
}
