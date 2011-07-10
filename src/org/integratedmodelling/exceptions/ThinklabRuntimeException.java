package org.integratedmodelling.exceptions;

public class ThinklabRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 4432900820629531550L;

	public ThinklabRuntimeException() {
	}

	public ThinklabRuntimeException(String message) {
		super(message);
	}

	public ThinklabRuntimeException(Throwable cause) {
		super(cause);
	}

	public ThinklabRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
