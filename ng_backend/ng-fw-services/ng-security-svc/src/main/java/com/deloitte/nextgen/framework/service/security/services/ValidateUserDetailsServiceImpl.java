package com.deloitte.nextgen.framework.service.security.services;

import com.deloitte.nextgen.framework.commons.constants.SecurityConstants;
import com.deloitte.nextgen.framework.service.security.dao.LoginNavigationDAO;
import com.deloitte.nextgen.framework.service.security.entities.SeUserCargo;
import com.deloitte.nextgen.framework.service.security.models.AuthenticationObj;
import com.deloitte.nextgen.framework.service.security.repository.SeUserRepository;
import com.deloitte.nextgen.framework.service.security.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class ValidateUserDetailsServiceImpl implements ValidateUserDetailsService {

	@Autowired
	private SeUserRepository seUserRepository;

	@Autowired
	private LoginNavigationDAO loginNavigationDAO;

	@Override
	public Map<String, ResponseVO> loadUserByUsername(AuthenticationObj authenticationObj) {
		ResponseVO res = loginNavigationDAO.getUserDetails(authenticationObj);

		Map<String, ResponseVO> responseMap = new HashMap<>();
		if (res.getStatus().equalsIgnoreCase(SecurityConstants.SUCCESS)) {
			responseMap.put("Data", res);
		} else {
			responseMap.put("Data", res);
		}

		return responseMap;
	}

	@Override
	public Map<String, String> loadUserByUsername(String userName) {
		Map<String, String> responseMap = new HashMap<>();
		List<SeUserCargo> seUserCargos = seUserRepository.findByUserId(userName);
		if(seUserCargos!=null && !seUserCargos.isEmpty() && seUserCargos.get(0).getUserId().equalsIgnoreCase(userName)){
			responseMap.put("Data", SecurityConstants.SUCCESS);
		}
		return responseMap;
	}

}
