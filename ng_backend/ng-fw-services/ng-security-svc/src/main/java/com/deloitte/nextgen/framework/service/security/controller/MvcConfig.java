package com.deloitte.nextgen.framework.service.security.controller;

import com.deloitte.nextgen.framework.commons.constants.SecurityConstants;
import com.deloitte.nextgen.framework.commons.exceptions.NgUsernameNotFoundException;
import com.deloitte.nextgen.framework.commons.utils.JwtUtil;
import com.deloitte.nextgen.framework.service.security.models.AuthenticationObj;
import com.deloitte.nextgen.framework.service.security.models.AuthenticationRequest;
import com.deloitte.nextgen.framework.service.security.models.AuthenticationResponse;
import com.deloitte.nextgen.framework.service.security.services.MyUserDetailsService;
import com.deloitte.nextgen.framework.service.security.services.ValidateUserDetailsService;
import com.deloitte.nextgen.framework.service.security.vo.ResponseVO;
import com.deloitte.nextgen.framework.web.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

@Configuration
@RestController
@CrossOrigin(exposedHeaders = "jwt")
@EnableWebMvc
@Slf4j
public class MvcConfig implements WebMvcConfigurer {

	//@Autowired
	//private AuthenticationManager authenticationManager;

	@Autowired
	private ValidateUserDetailsService validateUserDetailsService;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping(path = "/authorizeUser")
	public ResponseEntity<ApiResponse<AuthenticationResponse>> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws NgUsernameNotFoundException {

		final UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		if (userdetails.getPassword().equalsIgnoreCase(SecurityConstants.SUCCESS)) {
			final String jwt = jwtUtil.generateToken(userdetails);
			return ApiResponse.success(100).data(new AuthenticationResponse(jwt));
		} else {
			throw new NgUsernameNotFoundException(403,"User Not Found");
		}

	}

	@PostMapping(path = "/validateUser")
	public ResponseEntity<?> validateUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		final Map<String, ResponseVO> userdetails = validateUserDetailsService
				.loadUserByUsername(new AuthenticationObj(authenticationRequest.getUserName(),
						authenticationRequest.getPassword(), null));

		final UserDetails userdata = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		if (userdata.getPassword().equalsIgnoreCase(SecurityConstants.SUCCESS) && userdetails.get("Data").getStatus().equalsIgnoreCase(SecurityConstants.SUCCESS)) {
			final String jwt = jwtUtil.generateToken(userdata);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("jwt",jwt);
			return ResponseEntity.ok().headers(responseHeaders).body(userdetails);
		} else {
			//return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User Not Found");
			return ResponseEntity.ok(userdetails);
		}

		//return ResponseEntity.ok(userdetails);

	}


	@PutMapping("/refreshToken")
	public ResponseEntity<ApiResponse<String>> refreshToken(@RequestBody  String auth){
		String refToken= jwtUtil.refreshToken(auth);
		return ApiResponse.success(100).data(refToken);
	}

	@PutMapping("/isValidToken")
	public ResponseEntity<ApiResponse<Boolean>> isValidToken(@RequestBody  String auth) throws Exception {
		Boolean valFlg= true;
		try {
			valFlg = jwtUtil.isTokenExpired(auth);
		}catch (Exception e){
			throw new Exception("Invalid Token"+e);
		}
		return ApiResponse.success(100).data(!(valFlg));
	}

	@GetMapping("/valid")
	public ResponseEntity<?> validateToken(@RequestHeader Map<String, String> headers) throws Exception {
		log.info("Token headers: {}", headers.get(SecurityConstants.AUTHORIZATION));
		String authBearerToken=null != headers.get(SecurityConstants.AUTHORIZATION)
				? headers.get(SecurityConstants.AUTHORIZATION)
				: " ";
		String authToken=authBearerToken.substring(7) ;
		Boolean valFlg= true;
		try {
			valFlg = jwtUtil.isTokenExpired(authToken);
		}catch (Exception e){
			throw new Exception("Invalid Token"+e);
		}
		return ResponseEntity.ok(!(valFlg));
	}
	
	@PostMapping("/validateUserName")
	public ResponseEntity<ApiResponse<String>> validateToken(@RequestBody String userName) throws Exception {
		final UserDetails userdata = userDetailsService.loadUserByUsername(userName);
		if (userdata.getPassword().equalsIgnoreCase(SecurityConstants.SUCCESS)){
			return ApiResponse.success(100).data(SecurityConstants.SUCCESS);
		}

		return ApiResponse.unauthorized(401).data("User Not Found");
	}


}