package com.deloitte.nextgen.dto.entities;

import com.deloitte.nextgen.entities.DashboardRespDto;
import com.deloitte.nextgen.entities.NoticeRequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class NoticeStatusResp {

    private Integer emailSuccessCount;
    private Integer emailFailureCount;
    private Integer mailSuccessCount;
    private Integer mailFailureCount;
    private Integer textSuccessCount;
    private Integer textFailureCount;
    private List<DashboardRespDto> emailSuccessDetails;
    private List<DashboardRespDto> emailFailureDetails;
    private List<DashboardRespDto> mailSuccessDetails;
    private List<DashboardRespDto> mailFailureDetails;
    private List<DashboardRespDto> textSuccessDetails;
    private List<DashboardRespDto> textFailureDetails;
}
