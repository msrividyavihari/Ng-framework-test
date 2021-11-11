package com.deloitte.nextgen.dto.entities;

import lombok.Data;

@Data
public class NoticeRequest {
    private com.deloitte.nextgen.dto.entities.MetaData metaData;
    private com.deloitte.nextgen.dto.entities.FormData formData;
    private String requestJson;
    private String action;
    private Character origFlag;
}
