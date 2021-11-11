package com.deloitte.nextgen.framework.service.security.dao;

import com.deloitte.nextgen.framework.service.security.models.AuthenticationObj;
import com.deloitte.nextgen.framework.service.security.vo.ResponseVO;

public interface LoginNavigationDAO {

	public ResponseVO getUserDetails(AuthenticationObj authenticationObj);

}
