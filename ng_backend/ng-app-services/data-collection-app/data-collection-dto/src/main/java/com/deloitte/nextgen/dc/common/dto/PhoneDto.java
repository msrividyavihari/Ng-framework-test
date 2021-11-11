package com.deloitte.nextgen.dc.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class PhoneDto {

    private String phoneNum1;
    private String phoneExt;

}
