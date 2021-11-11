package com.deloitte.nextgen.framework.service.security.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileVO {

	private String firstName;
	private String midname;
	private String lastName;
	private String email;
	private String county;

	public UserProfileVO() {

	}

	public UserProfileVO(String firstName, String midname, String lastName, String email, String county) {
		super();
		this.firstName = firstName;
		this.midname = midname;
		this.lastName = lastName;
		this.email = email;
		this.county=county;
	}
}
