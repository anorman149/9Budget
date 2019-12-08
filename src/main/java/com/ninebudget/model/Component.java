package com.ninebudget.model;

public interface Component<T> {
    T get(T object) throws ComponentException;
    void update(T object) throws ComponentException;
    void create(T object) throws ComponentException;
}
