package com.deloitte.nextgen.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@ToString
@AllArgsConstructor
//@Service
@Component
public class AuthencationForDocuEdge {

    @JsonProperty("username")
    private final String userName = "integration-docuedge-share01";

    @JsonProperty("password")
    private final String password = "#Deloitte@1";

    private final String URL = "https://il4p364yh4.execute-api.us-east-2.amazonaws.com/dev/auth/sign-in";


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public String getURL() {
        return URL;
    }

}
