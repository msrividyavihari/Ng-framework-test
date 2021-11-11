package com.deloitte.nextgen.consumers.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * @author nishmehta on 09/03/2021 4:57 PM
 * @project ng-consumer
 */

@Data
@Entity
@IdClass(ErrorLogDetailsPK.class)
public class ErrorLogDetails {

	@Id
	private String contextId;

	@Id
	private long referenceId;

	@Id
	private long contextNum;

	private String contextType;

	private long exceptionNum;

	private String rowid;

}