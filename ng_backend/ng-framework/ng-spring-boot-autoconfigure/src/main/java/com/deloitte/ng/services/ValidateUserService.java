package com.deloitte.ng.services;

import com.deloitte.nextgen.framework.commons.exceptions.NgUsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface ValidateUserService {

    public UserDetails validateTokenThroughService(String userName) throws NgUsernameNotFoundException;
}
