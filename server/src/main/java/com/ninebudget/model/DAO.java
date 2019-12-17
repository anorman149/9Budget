package com.ninebudget.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public abstract class DAO<T> {
    protected static final Logger log = LogManager.getLogger(DAO.class);

    @Autowired
    protected DataSource dataSource;

    protected abstract T get(T object) throws DAOException;
    protected abstract void update(T object) throws DAOException;
    protected abstract void create(T object) throws DAOException;
}
