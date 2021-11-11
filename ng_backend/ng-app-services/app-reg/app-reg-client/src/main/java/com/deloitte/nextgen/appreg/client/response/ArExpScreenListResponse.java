package com.deloitte.nextgen.appreg.client.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class ArExpScreenListResponse {
    List<ArExpScreenResponse> arExpScreenResponseList;
}
