package com.deloitte.nextgen.appreg.client.response;

import com.deloitte.nextgen.appreg.client.entities.converters.ApplicationStatusConverter;
import com.deloitte.nextgen.appreg.client.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArFetchProgressResponse {

    private ApplicationStatus  applicationStatusCd ;
    private String program_cd;
    private String addrLine;
    private Long caseNum;
    private Long indvId;

}
