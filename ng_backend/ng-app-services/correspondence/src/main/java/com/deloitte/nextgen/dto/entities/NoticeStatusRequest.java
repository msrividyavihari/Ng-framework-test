package com.deloitte.nextgen.dto.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class NoticeStatusRequest {

    private String agency;
    private Date fromDate;
    private Date toDate;
    private List<String> channel;
    private String status;
}
