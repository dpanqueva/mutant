package com.meli.mutant.exception;

public class ForbiddenException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
