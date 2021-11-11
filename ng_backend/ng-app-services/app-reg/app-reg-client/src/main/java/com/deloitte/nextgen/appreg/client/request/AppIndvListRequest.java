package com.deloitte.nextgen.appreg.client.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AppIndvListRequest {
    @NotNull
    public String appNum;
}
