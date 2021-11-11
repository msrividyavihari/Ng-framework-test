package com.deloitte.nextgen.dto.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoEDMSUploadSystem {
    private String filename;
    private String lastModifiedDate;
    private Long size;
    private String type;

}
