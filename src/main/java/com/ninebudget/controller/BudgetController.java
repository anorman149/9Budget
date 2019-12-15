package com.ninebudget.controller;

import com.ninebudget.component.BudgetComponent;
import com.ninebudget.model.Budget;
import com.ninebudget.model.BudgetOperations;
import com.ninebudget.model.ComponentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController implements BudgetOperations {
    private static final Logger log = LogManager.getLogger(BudgetController.class);

    @Autowired
    private BudgetComponent budgetComponent;

    @Override
    public List<Budget> getAll() {
        return null;
    }

    @Override
    public Budget get(int id) {
        try {
            return budgetComponent.get(new Budget(id));
        } catch (ComponentException e) {
            log.error(e);
        }

        return null;
    }

    @Override
    public void update(Budget budget, int id) {

    }

    @Override
    public void create(Budget budget, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
