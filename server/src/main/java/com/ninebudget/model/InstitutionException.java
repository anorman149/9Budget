package com.ninebudget.model;

public class InstitutionException extends RuntimeException {
    public InstitutionException(String message) {
        super(message);
    }

    public InstitutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstitutionException(Throwable cause) {
        super(cause);
    }
}
