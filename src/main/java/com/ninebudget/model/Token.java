package com.ninebudget.model;

import java.security.PublicKey;

public interface Token<T> {
    T getToken() throws Exception;
    void verify(PublicKey publicKey) throws Exception;
}
