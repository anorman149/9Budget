package com.ninebudget.model;

public class DAOException extends Exception {
    static final long serialVersionUID = -3387516993124229948L;

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