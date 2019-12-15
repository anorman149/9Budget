package com.ninebudget.component;

import com.ninebudget.dao.BudgetDAO;
import com.ninebudget.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class BudgetComponent implements Component<Budget> {
    protected static final Logger log = LogManager.getLogger(BudgetComponent.class);

    @Autowired
    private BudgetDAO budgetDAO;

    @Override
    public Budget get(Budget budget) throws ComponentException {
        try {
            return budgetDAO.get(budget);
        } catch (DAOException e) {
            log.error(e);
        }

        return null;
    }

    @Override
    public void update(Budget budget) throws ComponentException {

    }

    @Override
    public void create(Budget budget) throws ComponentException {

    }
}
