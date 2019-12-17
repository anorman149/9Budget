package com.ninebudget.dao;

import com.ninebudget.model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends DAO<Account> {
    private static final String GET = "select * from account where id = ? and active = 1";

    public Account get(Account account) throws DAOException {
        Account loadedAccount = null;

        try(PreparedStatement stmt = dataSource.getConnection().prepareStatement(GET)) {
            stmt.setInt(1, account.getId());

            try(ResultSet rs = stmt.executeQuery()){
                boolean hasItems = false;
                if(rs.isBeforeFirst()){
                    hasItems = rs.next();
                }

                if(hasItems){
                    loadedAccount = createAccount(rs);
                }
            }
        } catch (SQLException e) {
            log.error("");

            throw new DAOException(e);
        }

        return loadedAccount;
    }

    public void update(Account account) throws DAOException {

    }

    public void create(Account account) throws DAOException {

    }

    private Account createAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt(1));
        account.setActive(rs.getBoolean(2));
        account.setPrimaryUser(new User(rs.getInt(3)));

        return account;
    }
}
