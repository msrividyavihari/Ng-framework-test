package com.deloitte.nextgen.ssp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class T1053AppProgramDto {
    private String appNum;
    private Character request_status_ind;
    private String program_cd;
}
