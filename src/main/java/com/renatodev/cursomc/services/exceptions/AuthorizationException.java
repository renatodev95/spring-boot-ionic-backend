package com.renatodev.cursomc.services.exceptions;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthorizationException(String msg) {
        super(msg);
    }

    public AuthorizationException(String msd, Throwable cause) {
        super(msd, cause);
    }
}
