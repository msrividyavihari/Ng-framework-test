package com.deloitte.nextgen.dto.vo;

import lombok.Data;

import java.util.List;

@Data
public class OPgetDocumentVO {
    private String status;
    private List<DataContent> data;
}
