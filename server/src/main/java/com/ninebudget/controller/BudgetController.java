package com.ninebudget.controller;

import com.ninebudget.component.BudgetComponent;
import com.ninebudget.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@APIController
public class BudgetController implements BudgetOperations {
    private static final Logger log = LogManager.getLogger(BudgetController.class);

    @Autowired
    private BudgetComponent budgetComponent;

    @Override
    public List<Budget> getAll() throws ServiceException {
        log.info("Retrieving Budget: " + 4);
        List<Budget> budgets = new ArrayList<>();

        try {
            budgets.add(budgetComponent.get(new Budget(4)));
        } catch (ComponentException e) {
            log.error(e);//TODO add more
        }

        return budgets;
    }

    @Override
    public Budget get(int id) throws ServiceException {
        log.info("Retrieving Budget: " + id);

        try {
            return budgetComponent.get(new Budget(id));
        } catch (ComponentException e) {
            log.error(e);//TODO add more
        }

        return null;
    }

    @Override
    public void update(Budget budget) throws ServiceException {

    }

    @Override
    public void create(Budget budget) throws ServiceException {

    }

    @Override
    public void delete(int id) throws ServiceException {

    }
}
