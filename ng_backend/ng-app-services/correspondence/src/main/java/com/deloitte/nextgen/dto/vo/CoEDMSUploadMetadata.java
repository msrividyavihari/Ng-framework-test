package com.deloitte.nextgen.dto.vo;

import com.deloitte.nextgen.dto.entities.CoEDMSMetadata;
import com.deloitte.nextgen.dto.vo.CoEDMSUploadSystem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoEDMSUploadMetadata {
    private CoEDMSUploadSystem system;
    private CoEDMSMetadata properties;
}
