package com.deloitte.nextgen.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
//@Service
//@Component
public class AuthenticationDetails {
    @JsonProperty("id")
    private String id;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("token")
    private String token;
    @JsonProperty("expiryTime")
    private long expiryTime;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
