package com.meli.mutant.exception;

/**
 * @author dpanquev
 * @version 2021-07-28
 * */
public class BadRequestException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}
