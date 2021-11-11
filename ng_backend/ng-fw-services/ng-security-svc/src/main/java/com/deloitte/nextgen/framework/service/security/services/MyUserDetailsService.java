package com.deloitte.nextgen.framework.service.security.services;

import com.deloitte.nextgen.framework.commons.exceptions.NgUsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface MyUserDetailsService {
    public UserDetails loadUserByUsername(String userName) throws NgUsernameNotFoundException;
}
