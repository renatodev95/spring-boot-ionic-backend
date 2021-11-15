package com.renatodev.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    public ObjectNotFoundException(String msd, Throwable cause) {
        super(msd, cause);
    }
}
