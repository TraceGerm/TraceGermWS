/**
 * 
 */
package com.tracegerm.tracegermws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author askos
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Data Access Exception while processing request!")
public class PersistenceLayerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersistenceLayerException() {
		super();
	}
	
	public PersistenceLayerException(String message) {
		super(message);
	}

	public PersistenceLayerException(String message, Throwable arg1) {
		super(message, arg1);
	}
}
