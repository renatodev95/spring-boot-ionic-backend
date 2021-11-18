package com.renatodev.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(String msd, Throwable cause) {
        super(msd, cause);
    }
}
