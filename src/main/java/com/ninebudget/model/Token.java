package com.ninebudget.model;

public interface Token<T> {
    T getToken() throws Exception;
    void extractProperties();
    void verify() throws Exception;
}
