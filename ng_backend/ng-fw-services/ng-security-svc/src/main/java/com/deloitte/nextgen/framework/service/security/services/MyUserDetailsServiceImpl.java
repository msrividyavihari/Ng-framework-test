package com.deloitte.nextgen.framework.service.security.services;

import com.deloitte.nextgen.framework.commons.exceptions.NgUsernameNotFoundException;
import com.deloitte.nextgen.framework.service.security.entities.SeUserCargo;
import com.deloitte.nextgen.framework.service.security.repository.SeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class MyUserDetailsServiceImpl implements MyUserDetailsService  {

	@Autowired
	private SeUserRepository seUserRepository;
	//private LoginNavigationDAO loginNavigationDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) throws NgUsernameNotFoundException {

		/*AuthenticationObj authenticationObj = new AuthenticationObj();
		authenticationObj.setUserName(userName);
		authenticationObj.setPassword("AuthoriztionOfService");

		ResponseVO res = loginNavigationDAO.getUserDetails(authenticationObj);
		String userExist = res.getStatus();
		if (null != userExist && userExist.equalsIgnoreCase("Success")) {
			return new User(userName, "Success", new ArrayList<GrantedAuthority>());
		} else {
			return new User(userName, "Failure", new ArrayList<GrantedAuthority>());
		}*/
		List<SeUserCargo> seUserCargos = seUserRepository.findByUserId(userName);
		if(seUserCargos!=null && !seUserCargos.isEmpty() && seUserCargos.get(0).getUserId().equalsIgnoreCase(userName)){
			return new User(userName, "Success", new ArrayList<>());
		}
		return new User(userName, "Failure", new ArrayList<>());
	}

}
