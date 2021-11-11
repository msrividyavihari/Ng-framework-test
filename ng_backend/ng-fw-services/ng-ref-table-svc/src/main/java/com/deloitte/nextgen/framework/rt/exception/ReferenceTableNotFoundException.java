package com.deloitte.nextgen.framework.rt.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class ReferenceTableNotFoundException.
 * Throws Exception when reference table doesn't exist
 */
@Slf4j
public class ReferenceTableNotFoundException extends RuntimeException {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5184005184798646570L;

	/**
	 * Instantiates a new reference table not found exception.
	 *
	 * @param message the message
	 */
	public ReferenceTableNotFoundException(String message) {
		super(message);
		log.error("Exception occured! "+message);
	}

}
