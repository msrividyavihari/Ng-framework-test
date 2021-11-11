package com.deloitte.nextgen.service.impl;


import com.deloitte.nextgen.service.AuthService;
import com.deloitte.nextgen.util.AuthenticationDetails;
import com.deloitte.nextgen.util.AuthencationForDocuEdge;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthencationForDocuEdge authorization;


    public String getToken(String username, String password) {
        String requestJson = getRequestBodyForSignIn(username, password);
        String responseJson = getResponseFromSignIn(requestJson);
        AuthenticationDetails details = getAuthentication(responseJson);
        String token = details.getToken();
        return token;
    }

    private String getRequestBodyForSignIn(String username, String password) {
        String request = "{\"username\" : \"" + username + "\", \"password\" : \"" + password + "\"}";
        return request;
    }

    private String getResponseFromSignIn(String requestJson) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        String responseJson = restTemplate.postForObject(authorization.getURL(), entity, String.class);
        return responseJson;
    }

    private AuthenticationDetails getAuthentication(String responseJson) {
        AuthenticationDetails details = new AuthenticationDetails();
        try {
            details = new ObjectMapper().readValue(responseJson, AuthenticationDetails.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  details;
    }


}
