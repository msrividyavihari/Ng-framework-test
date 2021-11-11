package com.deloitte.nextgen.framework.service.security.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseVO {

	private Object userProfile;

	private String status;

	public ResponseVO() {

	}

	public ResponseVO(Object userProfile, String status) {
		this.userProfile = userProfile;
		this.status = status;
	}
}
