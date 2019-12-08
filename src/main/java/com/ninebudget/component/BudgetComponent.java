package com.ninebudget.component;

import com.ninebudget.model.Budget;
import com.ninebudget.model.Component;
import com.ninebudget.model.ComponentException;

public class BudgetComponent implements Component<Budget> {
    @Override
    public Budget get(Budget object) throws ComponentException {
        return null;
    }

    @Override
    public void update(Budget object) throws ComponentException {

    }

    @Override
    public void create(Budget object) throws ComponentException {

    }

    @Override
    public void deactivate(Budget object) throws ComponentException {

    }
}
