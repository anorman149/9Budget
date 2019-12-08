package com.ninebudget.model;

public class DAOException extends Exception {
    public DAOException(String errorMessage) {
        super(errorMessage);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

}