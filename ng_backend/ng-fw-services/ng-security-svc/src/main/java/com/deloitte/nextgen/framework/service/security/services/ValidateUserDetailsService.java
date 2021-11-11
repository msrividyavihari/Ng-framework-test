package com.deloitte.nextgen.framework.service.security.services;

import com.deloitte.nextgen.framework.service.security.models.AuthenticationObj;
import com.deloitte.nextgen.framework.service.security.vo.ResponseVO;

import java.util.Map;

public interface ValidateUserDetailsService {

	public Map<String, ResponseVO> loadUserByUsername(AuthenticationObj authenticationObj);
	public Map<String, String> loadUserByUsername(String userName);

}
