package com.deloitte.nextgen.framework.rt.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseVO {

	private Object data;

	private String status;

	public ResponseVO(Object data, String status) {
		this.data = data;
		this.status = status;
	}

}
