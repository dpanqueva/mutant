package com.meli.mutant.exception;


/**
 * @author dpanquev
 * @version 2021-07-28
 * */
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
