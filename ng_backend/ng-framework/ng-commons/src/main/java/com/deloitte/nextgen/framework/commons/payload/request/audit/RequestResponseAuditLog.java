package com.deloitte.nextgen.framework.commons.payload.request.audit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponseAuditLog {

    private Long logId;
    private String httpMethod;
    private String requestURI;
    private String remoteAddr;
    private String requestParams;
    private String requestBody;
    private String responseBody;
    private Date createDate;
    private Long uniqueTransId;
    private String message;
    private String errorDetails;
    private String httpStatus;

    @Override
    public String toString() {
        return "RequestResponseAuditLog{" +
                "logId=" + logId +
                ", httpMethod='" + httpMethod + '\'' +
                ", requestURI='" + requestURI + '\'' +
                ", remoteAddr='" + remoteAddr + '\'' +
                ", requestParams='" + requestParams + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", responseBody='" + responseBody + '\'' +
                ", createDate=" + createDate +
                ", uniqueTransId=" + uniqueTransId +
                ", message='" + message + '\'' +
                ", errorDetails='" + errorDetails + '\'' +
                ", httpStatus='" + httpStatus + '\'' +
                '}';
    }
}
