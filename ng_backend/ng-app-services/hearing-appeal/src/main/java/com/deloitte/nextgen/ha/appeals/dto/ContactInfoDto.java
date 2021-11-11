package com.deloitte.nextgen.ha.appeals.dto;

import com.deloitte.nextgen.dc.common.dto.AddressDto;
import lombok.Data;

@Data
public class ContactInfoDto {

    private String preferredLanguageCode;
    private String preferredContactTimeCode;
    private String phone1;
    private String phone2;
    private AddressDto address;
    
}
