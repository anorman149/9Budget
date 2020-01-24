package com.ninebudget.model;

import java.security.PublicKey;

public interface Token<T> {
    T getToken();
    void setToken(T token);
    void verify(PublicKey publicKey);
    String provide(OAuthToken oAuthToken) throws TokenException;
    void authenticate() throws TokenException;
}
