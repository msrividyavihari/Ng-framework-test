package com.deloitte.nextgen.dto.vo;

import lombok.Data;

@Data
public class OPLogInResponseVO {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private Long expires_in;
}
