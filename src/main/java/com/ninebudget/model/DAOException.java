package com.ninebudget.model;

public class DAOException extends Exception {
    public DAOException(String errorMessage) {
        super(errorMessage);
    }
}