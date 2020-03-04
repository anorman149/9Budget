package com.ninebudget.model;

import java.security.PublicKey;

public interface Token<T> {
    long AUTH_TOKEN_EXPIRE_TIME = 5400; //In seconds - 90 Minutes

    T getToken();
    void setToken(T token);
    void verify(PublicKey publicKey);
    String getSubject();
    String provide(OAuthToken oAuthToken) throws TokenException;
    void authenticate() throws TokenException;
}
