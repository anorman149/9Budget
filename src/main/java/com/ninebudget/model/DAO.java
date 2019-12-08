package com.ninebudget.model;

import javax.sql.DataSource;

public abstract class DAO<T> {
    protected DataSource dataSource;

    public DAO() {
//        try {
//            Context ic = new javax.naming.InitialContext();
//            Context ctx = (javax.naming.Context) ic.lookup("java:");
//            dataSource = (javax.sql.DataSource) ctx.lookup("<DatasourceName>");
//            Connection cconnection = dataSource.getConnection();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
    }

    protected abstract T get(T object) throws DAOException;
    protected abstract void update(T object) throws DAOException;
    protected abstract void create(T object) throws DAOException;
}
