package com.ninebudget.dao;

import com.ninebudget.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BudgetDAO extends DAO<Budget> {
    private static final String GET = "select * from budget where id = ? and active = 1";

    public Budget get(Budget budget) throws DAOException {
        Budget loadedBudget;
        try {
            PreparedStatement stmt = dataSource.getConnection().prepareStatement(GET);
            stmt.setInt(1, 1);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            loadedBudget = createBudget(rs);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return loadedBudget;
    }

    public void update(Budget budget) throws DAOException {

    }

    public void create(Budget budget) throws DAOException {

    }

    private Budget createBudget(ResultSet rs) throws SQLException {
        Budget budget = new Budget();
        budget.setId(rs.getInt(1));
        budget.setName(rs.getString(2));
        budget.setAmount(new BigDecimal(rs.getInt(3)));
        budget.setActive(rs.getBoolean(4));
        budget.setTiming(BudgetTiming.valueOf(rs.getString(5)));
        budget.setUseLeftOver(rs.getBoolean(6));
        budget.setAccount(new Account(rs.getInt(7)));
        budget.setCategory(new Category(rs.getInt(8)));

        return budget;
    }
}
