package com.deloitte.nextgen.framework.web.services;

import com.deloitte.nextgen.framework.commons.constants.SecurityConstants;
import com.deloitte.nextgen.framework.commons.exceptions.NgUsernameNotFoundException;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.services.ValidateUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Getter
@Setter
@Service
public class ValidateUserServiceImpl implements ValidateUserService {

    private ApplicationProperties.JwtSecurity appProps;

    public ValidateUserServiceImpl(ApplicationProperties applicationProperties) {
        this.appProps = applicationProperties.getJwtSecurity();
    }

    @Override
    public UserDetails validateTokenThroughService(String userName) throws NgUsernameNotFoundException {
        ApiResponse<String> serviceResponse = null;
        boolean userFound = false;
        RestTemplate restTemplate = new RestTemplate();
        //TODO : Implement multiple security model approach
        //TODO : Read from properties file
        if (SecurityConstants.DAO.equalsIgnoreCase(appProps.getUserValidationMode())) {
            serviceResponse = restTemplate.postForObject(appProps.getUserValidationURL(), userName, ApiResponse.class);
            if (serviceResponse != null) {
                userFound = SecurityConstants.SUCCESS.equalsIgnoreCase(serviceResponse.getData());
            }
        }
        if (userFound) {
            return new User(userName, SecurityConstants.SUCCESS, new ArrayList<>());
        }

        throw new NgUsernameNotFoundException(401);
    }
}
