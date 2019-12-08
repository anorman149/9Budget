package com.ninebudget.controller;

import com.ninebudget.model.Budget;
import com.ninebudget.model.BudgetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController implements BudgetOperations {

    @Override
    public List<Budget> getAll() {
        return null;
    }

    @Override
    public Budget get(int id) {
        Budget budget = new Budget();
        budget.setId(id);
        budget.setName("BudgetDemo");
        return budget;
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
