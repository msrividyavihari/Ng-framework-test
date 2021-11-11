package com.deloitte.nextgen.dto.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import com.deloitte.nextgen.dto.vo.CoEDMSUploadMetadata;

@Data
@NoArgsConstructor
public class CoEDMSUploadVO {
    private String docId;
    private Map<String, String> tags;
    private CoEDMSUploadMetadata metadata;
}
