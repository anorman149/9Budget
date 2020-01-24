package com.ninebudget.model;

public class TokenException extends Exception {
    static final long serialVersionUID = 1L;

    public TokenException(String errorMessage) {
        super(errorMessage);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }
}
